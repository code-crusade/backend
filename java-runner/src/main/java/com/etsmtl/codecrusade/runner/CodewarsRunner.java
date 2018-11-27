package com.etsmtl.codecrusade.runner;

import com.etsmtl.codecrusade.runner.exceptions.AbnormalRunnerExitException;
import com.etsmtl.codecrusade.runner.exceptions.IncorrectRunnerEnvironmentException;
import com.etsmtl.codecrusade.runner.exceptions.MissingLanguageSupportException;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.exceptions.ImageNotFoundException;
import com.spotify.docker.client.exceptions.NotFoundException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.ExecCreation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

public class CodewarsRunner {

    // TODO: Add all languages supported by codewars-runner-cli
    public enum KnownLanguage {
        C("c", "systems-runner", "criterion"),
        CPLUSPLUS("cpp", "systems-runner", "igloo"),
        CSHARP("csharp", "dotnet-runner", "nunit"),
        JAVA("java", "java-runner", "junit"),
        JAVASCRIPT("javascript", "node-runner", "cw-2"),
        PYTHON3("python3", "python-runner", "cw-2"),
        ;

        private final String runnerName;
        private final String containerName;
        private final String testFormat;

        KnownLanguage(String runnerName, String containerName, String testFormat) {
            this.runnerName = runnerName;
            this.containerName = containerName;
            this.testFormat = testFormat;
        }
    }

    // Code and tests must be equivalent to full files
    public static String runCode(KnownLanguage language, Duration timeout, String code, Optional<String> tests) {
        final DockerClient docker = new DefaultDockerClient("unix:///var/run/docker.sock");

        try {
            docker.ping(); // Just to check if the client is ok
        } catch (Exception e) {
            throw new IncorrectRunnerEnvironmentException(e);
        }


        ArrayList<String> containerCommand = new ArrayList<>();

        containerCommand.add("run");
        containerCommand.add("-l");
        containerCommand.add(language.runnerName);
        containerCommand.add("-c");
        containerCommand.add(code);

        tests.ifPresent(testFixtures -> {
            containerCommand.add("-t");
            containerCommand.add(language.testFormat);
            containerCommand.add("-f");
            containerCommand.add(testFixtures);
        });

        String[] containerCommandArray = new String[containerCommand.size()];
        containerCommand.toArray(containerCommandArray);

        try {
            final ContainerConfig containerConfig = ContainerConfig.builder()
                                                                   .image(language.containerName)
                                                                   .build();

            ContainerCreation creation = docker.createContainer(containerConfig);
            final String id = creation.id();
            docker.startContainer(id);

            final ExecCreation execCreation = docker.execCreate(
                    id, containerCommandArray,
                    DockerClient.ExecCreateParam.attachStdout(),
                    DockerClient.ExecCreateParam.attachStderr());
            final LogStream output = docker.execStart(execCreation.id());

            docker.stopContainer(id, (int) timeout.getSeconds());

            final String execOutput = output.readFully();

            return execOutput;
        } catch (ImageNotFoundException e) {
            throw new MissingLanguageSupportException(language.runnerName, e);
        } catch (DockerException e) {
            throw new AbnormalRunnerExitException(e);
        } catch (InterruptedException e) {
            throw new AbnormalRunnerExitException(e);
        }
    }
}

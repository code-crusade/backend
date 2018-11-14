/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.3.2).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.model.RFC7807Body;
import com.etsmtl.codecrusade.model.RunnerArguments;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-14T14:33:02.883045400-05:00[America/New_York]")

@Validated
@Api(value = "runner", description = "the runner API")
public interface RunnerApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Runs provided code", nickname = "runCodeForExercise", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Api problem response", response = RFC7807Body.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/runner/{exerciseId}",
        produces = { "application/problem+json", "application/problem+xml" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> runCodeForExercise(@ApiParam(value = "",required=true) @PathVariable("exerciseId") Long exerciseId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody RunnerArguments runnerArguments) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

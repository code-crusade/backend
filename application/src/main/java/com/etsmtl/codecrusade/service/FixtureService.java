package com.etsmtl.codecrusade.service;

import java.util.Map;

public interface FixtureService {
    Map<String,String> getFixturesForExercise(Integer exerciseId);
}

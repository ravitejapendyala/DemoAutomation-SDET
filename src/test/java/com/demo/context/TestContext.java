package com.demo.context;

public class TestContext {
    ScenarioContext scenarioContext;

    public TestContext() {
        scenarioContext = new ScenarioContext();
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}

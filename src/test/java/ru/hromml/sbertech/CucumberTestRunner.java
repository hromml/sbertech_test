package ru.hromml.sbertech;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;

@CucumberOptions(features = "src/test/resources/cucumber.feature")
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
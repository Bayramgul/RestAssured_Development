package com.hrms.runners;

import org.junit.runner.RunWith;

/**
 * APIRunner class which we have specified path to all feature files in features
 * and specified path to our API steps practice package
 * so that we dont execute our hooks which will initialize our WebDriver and open browser
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			features = "src/test/resources/features", 
			glue = {"com.hrms.API.steps.practice" }, 
			dryRun = false,
			tags = "@GetAllEmployees")

public class APIRunner {

}

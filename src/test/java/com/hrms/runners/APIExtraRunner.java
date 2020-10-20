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
			features = "src/test/resources/features/api", 
			glue = {"com.hrms.API.improved" }, 
			dryRun = false,
			monochrome = true,
			tags = "@API_0001")

public class APIExtraRunner {

}

package salesforce.Runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/salesforcefeatures/LogIn.feature" 
,monochrome = true, glue = "SalesforceStepDefination"
, plugin = {"pretty","html:target/Reports/Samplereport.html"})
public class SuiteRunner {


}


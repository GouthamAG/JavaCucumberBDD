package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
//@CucumberOptions(features="src/test/java/features", glue={"stepDefinitions"}, tags="@deletePlace")
@CucumberOptions(features="src/test/java/features",plugin="json:target/jsonReports/cucumber-report.json", glue={"stepDefinitions"})
public class TestRunner {

	
	
}

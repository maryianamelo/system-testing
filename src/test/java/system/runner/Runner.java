package system.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static system.helpers.Constants.FEATURES_PATH;
import static system.helpers.Constants.STEPS_DEFINITIONS_PATH;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = FEATURES_PATH,
    glue = STEPS_DEFINITIONS_PATH,
    tags = "@expressions",
    monochrome = false,
    plugin = {"pretty","json:target/cucumber-json/cucumber.json","junit:target/cucumber-reports/Cucumber.xml","html:target/cucumber-reports"},
    snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class Runner {
}




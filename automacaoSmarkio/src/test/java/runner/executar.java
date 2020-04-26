package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/gherkin",
		glue={"steps"},
		snippets = SnippetType.CAMELCASE,
		plugin = {"pretty"},
		monochrome = true
		)

public class executar {

}

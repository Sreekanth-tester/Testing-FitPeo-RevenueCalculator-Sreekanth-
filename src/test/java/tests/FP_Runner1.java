package tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src\\test\\resources\\FeaturesFiles"},
		glue={"gluecode"},
		monochrome=true,
		plugin={"pretty","html:target/cucumber-reports"})
public class FP_Runner1 {

}

package tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"C:\\PNR255\\org.FitPeo\\src\\test\\resources\\FeatureFiles\\FP-FeatureFile.feature"},
		glue={"gluecode"},
		monochrome=true,
		plugin={"pretty","html:target/cucumber-reports"}
		)
public class FP_Runner 
{

}

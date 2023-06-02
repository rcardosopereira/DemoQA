# DemoQA
This is a repository for the DemoQA project. It uses Java, Selenium, Retrofit2, JUnit and Maven as a build automation tool.


## Setup
To run the project, you will need to have the following installed on your computer:<br/>
-Java (Version 20.0.1 2023-04-18);<br/>
-Retrofit (Version 2.9.0);<br/>
-Selenium (Version 4.9.0);<br/>
-JUnit (Version 5.9.3);<br/>
-Google Chrome Browser (Version 113.0.5672.127 - Official Build 64-bit);<br/>
-Maven (build automation tool);<br/>

## Running the project (Installation)
To run the project, we can use IntelliJ and run the tests there.<br/>



### Testing(features) and WebDriver (ChromeDriver 113.0.5672.63) 
The Webdriver is located in the **src/test/resources/driver** directory.<br />

## Design decisions
The project was designed to follow the page object model, separating the packages (pages, runner and steps) and pages of the application into individual classes, and using them to interact with the application.

## Project limitations
There is an error to correct in the part: <br />
java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $<br />
Maven is configured to run, according to the pom.xml but an error is occurring. It is necessary to validate this for a future version.

# Open Trivia DB Challenge

### The scope of the challenge is to implement a Java application that consumes the [Open Trivia DB API](https://opentdb.com/api_config.php) and export the result to a CSV File.

As the challenge already had the parameters defined the execution of the bullet points was implemented as an Integrated Test in the class [ChallengeExecutionTest](src/test/java/com/alianza/exercise/opentrivia/challenge/ChallengeExecutionTest.java).

## Getting Started

### Check the configuration
Before executing the challenge methods, is recommended to check and adjust the configurations in the file [application.yml](src/main/resources/application.yml)

The configuration file is designed with the following  structure:
* **"open-trivia"** are the configuration of the API (should not be updated);
* **"export"** are the configurations used for generate the CSV File and export it;
  * **"field-delimiter"** is the character used to separate the fields/columns in the file; As the initial extension is CSV, the default delimiter is comma (,).
  * **"path"** is the absolute path where the file will be created in your OS.
    * Ensure that you have the "write"permission in the path you are adding in this configuration.
    * As it is only the PATH configuration, always remember to add the "slash" or "backslash" in the end of the configuration;
    * **Windows ONLY**: Remember to use the "double backslash" pattern to inform the path configuration. (C:\\\\Home\\\\your\\\\path\\\\)
  * **"name"** is the default File Name. It is used when the name of the exported file is not sent in the method.

### Execute the methods in the class [ChallengeExecutionTest](src/test/java/com/alianza/exercise/opentrivia/challenge/ChallengeExecutionTest.java)
* **First Challenge**:
  * "10 medium difficulty multiple choice questions about computers";
  * Execute the method `void firstChallenge()`;
  * It will execute a `GET` request in the Open Trivia API and export the result into a CSV File initially called "TenMediumQuestions.csv" in the path you configured previously;
* **Second Challenge**:
  * "12 easy difficulty multiple choice questions about a subject of your choice, encoded according to RFC 3986";
  * Execute the method `void secondChallenge()`;
  * It will execute a `GET` request in the Open Trivia API and export the result into a CSV File initially called "TwelveEasyQuestions.csv" in the path you configured previously;
* **Extra Challenge**:
  * If you want to generate an extra set of questions you can use one of the methods implemented or create a copy of any method;
  * To change the parameters of the API, change the values in the HashMap called "params";
  * You can also change the variable "fileName" or simply remove it. If you opt for remove this variable, the name of the file exported will be the name configured in the configuration file, in the property "export.file.name";

## Known Backlog
* Create a REST endpoint to call the API and return the questions;
* Convert the Map of parameters into a resource class to be sent even in the REST Request;
* Increase the percentage of the coverage of the Unit Tests;
* Implement an option to execute the call to the Open Trivia API with the Session Token;
* Implement a flow to handle the different API "Response Codes";
* Create Enums to convert the parameters "Difficulty", "Type" and "Encoding";

## Tech Debts
* Implement a Custom Exception to handle all the exceptions thrown over the application;
* Create an Exception Handler in the Open Trivia API Client;
* Create an Exception Handler in the Export Service;

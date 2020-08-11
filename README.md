# RestfullAssignmentProject
Assignment project for peer evaluation.


Please follow the below guideliness to execute the project :-

1. Ensure to have JDK1.8
2. Build the project using mvn clean install
3. If you getting the error , check the build path and change JRE to JDK.
4. Again building the project using mvn clean install
5. Finally run as Spring boot.
6. open the postman and hit the url http://localhost:8080/validateReport


Logic behind the working of code:-

1.Initially {CustomerStatementController.java} receiving the records.json 
as a list of requestBean in RequestBody.

2.Using @JSONProperty , @Valid , @NotNull annotation provide by javax , throwing the Bad request .
 Functionality behind that is validating the property in requestBody itself. 
 
3.In case .json files contains the invalid parameter values as like
Scenario 1:-
	String in place of Double, or 
	Integer contains any alpha values it will throw HttpMessageNotReadableException.
Scenario 2:-
	Places where reference is null, or Mutation is null or other parameters it will throw ConstraintViolationException.

4. For the above Controller exceptions , {CustomerStatementExceptionController.java} will handle the exception.
Since Spring boot provides the @ControllerAdvice and @ExceptionHandler annotation we can easily handle the exception.

5.After all the json values are validated ,Conversion of json into RequestBean will automatically takeplace.
	Note:-I am not directly consuming the .json and parsing it into the corresponding object , since RequestBody takes care it.

6.Passing the list of RequestBean to the service layer{CustomerStatementServiceImpl.java}.

7.Initiating isDuplicate, isIncorrectvalue as a boolean value {isDuplicate=false,isIncorrectvalue=false };
 
8.Checking the duplicate reference by using the HashMap.  Reference as a key and count as a value.
	If,value available for the given key , i would say it is a duplicate reference.
	Otherwise i am adding the key value pairs in hashmap and checking the incorrect balance.
	
9.Both the StartBalance, Mutation(after conversion)  are in double , calculation render 10 digit decimal number  .To avoid that,
I used the BigDecimal to scale it upto 2 digit in order to compare the end value.

10.Based on the boolean values which is mentioned earlier {isDuplicate=false,isIncorrectvalue=false }, 
i will driven the status .

11.Wherever i am getting the values as incorrectvalue and duplicate reference I am using the {StatementError.Java} to capture the Refernce and AccountNumber.
Then I adding the statement error in  a list.

12.Finally in ResponseBean i am setting the status and statementerror.

13.Output of my service will be ResponseBean.

14.Assigning the response bean in ResponseEntity and redirecting it to the post call response.

15. In any case it there is a runtimeException , it will be handle by InternalServerException<userDefiendException>;
15.Default ResponseEntity will produce .json response. So that i avoided the using of producer annotation in the Controller.

16. In this project i used three level testing.

a. Service layer unit testing
b. Controller layer webmvc testing by mocking service.
c.Integration testing.

Note : Since testing is pretty straight forward ,I hope you could easily understand the coding logics.
 
Postman input file:-
[
  {
    "Reference": 194261,
    "AccountNumber": "NL91RABO0315273637",
    "Description": "Clothes from Jan Bakker",
    "Start Balance": 21.6,
    "Mutation": -41.83,
    "End Balance": -20.23
  },
  {
    "Reference": 112806,
    "AccountNumber": "NL27SNSB0917829871",
    "Description": "Clothes for Willem Dekker",
    "Start Balance": 91.23,
    "Mutation": "+15.57",
    "End Balance": 106.8
  }]


OUTPUT Images for your reference:-

Successful:-

![Successfull](https://user-images.githubusercontent.com/54101562/89869397-ec759c80-dbd1-11ea-9c89-23a9f24bd1eb.PNG)

Duplciate Reference:-

![DuplicateReference](https://user-images.githubusercontent.com/54101562/89868974-46299700-dbd1-11ea-8373-e31f45671227.PNG)

Incorrect Balance:-

![IncorrectBalance](https://user-images.githubusercontent.com/54101562/89869240-b506f000-dbd1-11ea-8811-d162c6bbcdae.PNG)

Duplicate Reference and Incorrect Balance:-

![Duplicatereference_incorrectbalance](https://user-images.githubusercontent.com/54101562/89869253-b9cba400-dbd1-11ea-96d1-f6d772396883.PNG)

Bad request:-

![BadRequest](https://user-images.githubusercontent.com/54101562/89869217-ab7d8800-dbd1-11ea-8876-2738a9c53d72.PNG)


THANKS FOR YOUR PATIENCE :-
BR,
MEGANATHAN.



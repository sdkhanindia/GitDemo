Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
	|name	| language	|	address			|
	|AAHouse|Hindi		|World Cross Center	|
#	|BBHouse|Bengali	|Kolkata			|
	

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"

	
	
	
	
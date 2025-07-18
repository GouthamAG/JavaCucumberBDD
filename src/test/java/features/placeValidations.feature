Feature: Validating Place API's

@addPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" HTTP request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"

	Examples:
		| name    | language | address 						|
		| AAHouse1 | English1  | World Cross Center1 |
		| BBHouse1 | Spanish1  | Sea Cross Center1   |
		
		
@deletePlace
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" HTTP request
	Then the API call got success with status code 200
	And "status" in response body is "OK"	
		
		
			
Feature: Validating Place API's

Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" HTTP request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"

	Examples:
		| name    | language | address 						|
		| AAHouse | English  | World Cross Center |
		| BBHouse | Spanish  | Sea Cross Center   |	
Feature: Validating place API's

@AddPlace1
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add place payload with "<name>" "<language>" "<address>"
When User call "AddPlaceAPI" with "Post" HTTP
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify the "<name>" and placeid using "GetPlaceAPI"

Examples: 
			|name       |language|     address                  |
			|Chandrakant|English |Hoodi Whietfield Bangalore    |
#			|Kumar      |Hindi   |Silkboard Whietfield Bangalore|

@DeletePlace
Scenario: Verify DeletePalce API
Given DeletePlace payload
When User call "DeletePlaceAPI" with "Post" HTTP
Then the API call got success with status code 200
And "status" in response body is "OK"
#And verify the placeid using "GetPlaceAPI" has been deleted

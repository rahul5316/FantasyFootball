<h1><strong> Fantasy Football</strong></h1>

<h2> Fantasy Football working and Summary</h2>


- <em> What will the application do? </em>

This application is a <strong>Fantasy Football</Strong> application that <strong>constructs a team</strong> of user's choice, <strong>adds Player to the team</strong>, <strong>upgrade statistics</strong> of the players,
 and <stromg>lists all the players</strong> in a particular team.
- <em> Who will use it?</em>
Anyone who has knowledge of football and knows how to use a computer can use this application
- <em> Why is this project of interest  to you?</em>
This project is of interest to me because I have been following football my entire life, and I thought it would be amazing if I made something related to that.

- <em> Working prototype </em>
This Fantasy Football uses Swing Gui and Json. User can add n number of players in a team, and the application will show how many players are there in total.
Sound effects in the application makes it more realistic.

## Description of phase 4
- Made use of map interface so for JSON reader and writer which uses JSON objects which in-turn uses map interface

## Phase 4: Task 3
The fantasy football app has objects of Tournament, Player, Team all which implements the interface writeable. It also extends JsonWriter and JsonReader, but they do not extend the writeable interface.  As the Tournament, Player and Team class implements the writeable interface, it becomes a dependent relationship.
 The team class has got a list of players inside of it which concludes that players are a part of team and hence there is a association relationship that exists between the two. I also used try catch blocks for my setPace method and extended to Exception class to full fill the requirments. It catches an error whenever a pace which is less than zero is used.
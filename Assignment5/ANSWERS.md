# Assignment 5

## Part 1
Note: For this test we are using the code from our gitHub which was modified at some point so that all the classes are called "<name>_ex1". The modification also changed some variable names, but as these are consistent within the sections they are used in, this simply results in some peculiar variable names, so we decided not to comb through the entire code to change them back...
Before even starting to write the test suite we decided to fix up the User input, as we had been made aware of the fact that entering incorrect data caused our system to end in an error.
So we changed any instance of "scanner.nextInt()" to "scanner.nextLine()" and instead used a "matches("\\d+")" to make sure we were recieving a positive integer, asking for another input if this was not the case.
In addition to that we also used ".isEmpty" for checking the entered player name so one could not simply press the enter key and give the player an empty string as a name.
In addition to that we also decided to remove any methods that had been implemented but never used, as there is no reason to keep them in there.
One thing we did not know how to test properly were print statements, but if all else was stored correctly those should not prove to be a problem…
Due to us using quite a few ‘get’ and ‘set’ methods which do nothing else but store and retrieve a specific variable, we decided only to make a single test for one of these (while testing the player). If anything special was being done with the variable, when storing it, we did try to test that. We also didn’t make a separate Test for Ladder_ex1’s putOn() method, as that is basically the same as Snake_ex1’s, moving it to a specific ‘destination’ rather than its own storage.
We did not test the Die_ex1 class, as the only thing that does is make a random number and we’re not doing to test java’s inbuilt functions…
Bug Fixes/changes in testing:
•	We changed how player names were accepted. Instead of having the Player_ex1 class ask for the name we handled that in Game_ex1 (in initialValues()). This was so we could first store the names in an arraylist and verify that there were no duplicates. Due to this player now requires a string in its constructor, instead of a number, which was previously only used to ask the player for their name.
•	Due to us being unable to test several aspects of the code (as they were private) we made some of those protected, so we could test them properly. We also made the “addPlayer()” method in the Field_ex1 class so that it couldn’t be easily messed with, but still inherited.
•	setAllLadders() in Board_ex1 contained an error that caused stack overflows, thanks to finding that in bug testing and then doing a slight modification that should no longer be a problem


## Part 2

### GameBoardTest

| Test Name | Why did it fail? | How was it fixed? |
|-----------|------------------|-------------------|
|markOnBoard & markTwice|mark method actually returned true if the field was still empty, and when it was actually possible to set mark the field, it returned false.| Both was fixed by switching the false and true statements inside the mark function of Game.|
|getOpenPositions & getOpenPositionsAll|When checking for open positions the getOpenPositions function did not check fields in the first column, and since the tests test for empty values in the first column, they failed.|It was fixed by starting the second for-loop at index 0 aswell.|

### TicTacToeGameStateTest

| Test Name | Why did it fail? | How was it fixed? |
|-----------|------------------|-------------------|
|startingPlayerIsX|Since the function getCurrentPlayer returned 'O' in every single case, it was in fact totally irrelevant that the currentPlayer field was actually set correctly all the time. The test failed because it expects the current player to be X at the very start.|Fixed by returning the currentPlayer field value.|
|isOverWin & hasWinCol & hasWinRow|The function hasWin returned false when the player had a complete row or column. It obviously should have returned true in that case because this is a winning condition.| By changing the return value to true (in the for loop) of the hasWin function.|
|hasWinDiagonal|The completesDiagonal function did not check for the winning condition correctly. While it is true that to win with a diagonal you need to have the center, in the case of also having (0,0), the third field must be (2,2) and not (1,2).|By correctly adjusting the diagonal winning condition to the combination of (0,0),(1,1),(2,2).|


## Part 3

####1. a & b & c
"Game", "Player" and "Card" should have getters. They work as expected.
#####The game-class should accept offer an interface of multiple mehtodes:
- constructor(ArrayList<Player> list,Player startingPlayer)
   Should not accept an arraylist of too many or too few players. Should not accept multiple instances of the player in the list. startingPlayer should also be in the list
   Creates a shuffled Deck, an empty discard pile. Gives each player 7 cards. Puts the top cards of the Deck onto the empty discard Pile. Stores the starting Player as current Player.
   Should return the created Game.
- startTurn(Player player)
   Should only accept Players that are in the Game, and whos turn it is.
   Applies any effect from the top card to the Player (e.g. give him extra cards, skip his turn).
   returns nothing
- playabel(Card card, Player player)
   Should not accept Players who aren't in the game. Is only calleable if the turn has started.
   Checks if it is this players turn to play. Checks if this card is playable at this moment (number/color correct, special rules with +4 card).
   Should return true if it is this players turn, and the card is playable. Else returns False.
- playCard(Card card, player player)
   Should only accept Players who are in this game. Should only accept cards that are playable. It must be Players turn, Player can't have played a card this turn. Is only calleable if the turn has started.
   Puts the card on top of the discard pile.
   returns nothing
- sayUno(Player player)
   The player should be in the game, they should only have one card in hand and should have played a card already and no-one else has played a card since.
   Informs all other player about the uno-statement. Sets the players uno-statement to true.
   returns nothing
- accuseUno(Player player,Player notSaidPlayer)
   Both Players should be in this game. NotSaidPlayer should have only one card in hand and another card has been played since. NotSaidPlayer should have its uno-statement = false.
   Informs all Players about the foul. Hands the player two new cards.
   Returns nothing
- drawCard(Player player)
   Player should be in this games. It should be Players turn, and they can't have drawn or played a card already this turn. Is only calleable if the turn has started.
   Adds another card to the players hand. If the deck is empty, shuffles the discard pile (except for the top card) and puts it on the deck.
   returns nothing
- endTurn(Player player)
   Player should be in this games. It should be Players turn and they should have played a card or/and have drawn a card.
   If this player has 0 Card, it should end the game
   returns nothing.



   

 


# Assignment 5

## Part 1

## Part 2

### GameBoardTest

| Test Name | Why did it fail? | How was it fixed? |
|-----------|------------------|-------------------|
|markOnBoard & markTwice|mark method actually returned true if the field was still empty, and when it was actually possible to set mark the field, it returned false.| Both was fixed by switching the false and true statements inside the mark function of Game.|
|getOpenPositions & getOpenPositionsAll|When checking for open positions the getOpenPositions function did not check fields in the first column, and since the tests test for empty values in the first column, they failed.|It was fixed by starting the second for-loop at index 0 aswell.|
|

### TicTacToeGameStateTest

| Test Name | Why did it fail? | How was it fixed? |
|-----------|------------------|-------------------|
|startingPlayerIsX|Since the function getCurrentPlayer returned 'O' in every single case, it was in fact totally irrelevant that the currentPlayer field was actually set correctly all the time. The test failed because it expects the current player to be X at the very start.|Fixed by returning the currentPlayer field value.|
|isOverWin & hasWinCol & hasWinRow|The function hasWin returned false when the player had a complete row or column. It obviously should have returned true in that case because this is a winning condition.| By changing the return value to true (in the for loop) of the hasWin function.|
|hasWinDiagonal|The completesDiagonal function did not check for the winning condition correctly. While it is true that to win with a diagonal you need to have the center, in the case of also having (0,0), the third field must be (2,2) and not (1,2).|By correctly adjusting the diagonal winning condition to the combination of (0,0),(1,1),(2,2).|


## Part 3
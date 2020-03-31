SlimeSweeper Challenge by Benjamin Crall

The purpose of the puzzle is to discover all the slime in the room without stepping in any of it.
To search for slime, specify an X and Y location in the game board to reveal that space.
Once revealed, the space will show the number of slimes within 1 space of it including diagonals.
If the space is not adjacent to any slimes, all adjacent spaces will also be revealed continuing until all empty connecting spaces have been revealed.
The game is won once all non-slime spaces have been revealed.
Spaces can be marked, but it is not required for the game to be won.

Robots must be compiled and packaged in a jar to be able to play the game.
Multiple robots can be stored in each jar, and the robot will be selected by the user at runtime.
An example robot class can be found in the BlankRobot.java file.
Robots may use any Java or external libraries and modify external files.
Robots may not by any means gain access to any part of the game other than what is supplied to it in the makeMove and cleanup methods.
The exception is that you may use Main.scan which is a Scanner of System.in

To run the challenge file run the following command from terminal (Win/Linux/Mac) replacing %version% with the program version in the M.m.r format.
X is the width, Y is the height, and S is a number 0-1 representing the percent of slimes.
X,Y, and S should all be specified if any of them are to be specified.
java -jar SlimesweeperChallange-%versoin%.jar <robotfile>
java -jar SlimesweeperChallange-%versoin%.jar <robotfile> [x] [y] [s]
java -jar SilmesweeperChallenge-%version%.jar [x] [y] [x]

If no valid robot is specified, the default robot will be used.
It prints the game board to System.out and looks on System.in for the user to supply a command.
Its commands are
 > r: reveal a space
 > m: toggle marking on a space
The format is: <r|m> <x> <y>
The board is printed using the following characters
 > -: Hidden space
 > #: Marked space
 > X: Slimy space
 > Blank: Revealed space
 > Numbers: Adjacent slimes



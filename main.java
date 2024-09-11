/*
-Random coordinates for computer (have another board that stores the computer's hits so the computer can add/subtract x/y coordinates)
-Some kind of logic that prevents the computer from guessing in the same spot twice. 2 2d arrays (one storess all the computer's guesses, one stores the computer's hits)
-One board that gets printed for the user
-Another board to store the computer's random coordinates
-Function to check hit or miss
-Not going over the edge (index out of range)
-The coordinate is not a number
-Make the AI code as efficient as possible (use more functions)
Work on Advanced Battleship with an AI Player
-organize code into logical functions
-Finish coding setup for the Human player
-Get Computer’s coordinates done
-Plan out and write pseudo code for AI guesser
-Try to make AI as efficient and as good of a guesser as possible, think about edge cases ;)
*/
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
class Main {
  public static Random rand = new Random();
  public static void main(String[] args) {
    Scanner input = new Scanner (System.in);
    System.out.println("Welcome to Battleship!");
    System.out.println(" ");
    System.out.println("You will be competing with the computer to fire all of the other's ships. ");
    System.out.println(" ");
    String[][] battleshipBoard = new String [11][11];
    String[][] userBoard = new String[11][11];
    String[][] aIBoard = new String [11][11];
    for(int i = 0;i<battleshipBoard.length;i++){
      for(int  e = 0; e < battleshipBoard[0].length;e++){
        battleshipBoard[i][e] = "-";
        userBoard[i][e] = "-";
        aIBoard[i][e] = "-";
      }
    }
    battleshipBoard[0][0] = " ";
    userBoard[0][0] = " ";
    aIBoard[0][0] = " ";
    for(int i = 1; i<battleshipBoard.length;i++){
      battleshipBoard[i][0] = Integer.toString(i-1);
      battleshipBoard[0][i] = Integer.toString(i-1);
      userBoard[i][0] = Integer.toString(i-1);
      userBoard[0][i] = Integer.toString(i-1);
      aIBoard[i][0] = Integer.toString(i-1);
      aIBoard[0][i] = Integer.toString(i-1);
    }
    //User coordinates
    int userX = 0;
    int userY = 0;
    int userX2 = 0;
    int userY2 = 0;
    System.out.println("Please pick the coordinates for your 2-space ship (The coordinates have to be conjoined vertically or horizontally (0-9)): ");
    for(int i = 0;i< 2;i++){
      System.out.println("Enter row: ");
      userX = input.nextInt(); 
      if (userX > 9 || userX < 0){
        System.out.println("The row ha to be between 0-9, please try again: ");
        userX = input.nextInt();
      }
      System.out.println("Enter column: ");
      userY = input.nextInt();
      if (userY > 9|| userY < 0){
        System.out.println("The column has to be between 0-9, please try again: ");
        userY = input.nextInt();
      }
      userBoard[userX+1][userY+1] = "x";
      print(userBoard);
    }
    System.out.println("Please pick the coordinates for your 3-space ship (The coordinates have to be conjoined vertically or horizontally (0-9))");
    for(int i = 0;i<3;i++){
      System.out.println("Enter row: ");
      userX2 = input.nextInt(); 
      if (userX2 > 9 || userX2 < 0){
        System.out.println("The row has to be between 0-9, please try again: ");
        userX2 = input.nextInt();
      }
      System.out.println("Enter column: ");
      userY2 = input.nextInt();
      if (userY2 > 9|| userY2 < 0){
        System.out.println("The column has to be between 0-9, please try again: ");
        userY2 = input.nextInt();
      }
      else if (userBoard[userX2+1][userY2+1] == "x"){
        System.out.println("You already picked this coordinate for one of your previous ships. Please try again.");
        System.out.println("Enter row: ");
        userX2 = input.nextInt(); 
        if (userX2 > 9 || userX2 < 0){
          System.out.println("The row has to be between 0-9, please try again: ");
          userX2 = input.nextInt();
        }
        System.out.println("Enter column: ");
        userY2 = input.nextInt();
        if (userY2 > 9|| userY2 < 0){
          System.out.println("The column has to be between 0-9, please try again: ");
          userY2 = input.nextInt();
        }
      }
        userBoard[userX2+1][userY2+1] = "x";
        print(userBoard);
    }
    // Computer board and user checking
    String[][] checkBoard = new String[11][11];
    int randomX = rand.nextInt(8-1) + 2;
    int randomY = rand.nextInt(8-1) + 2;
    checkBoard[randomX][randomY] = "true";
    checkBoard[randomX][randomY+1] = "true";
    checkBoard[randomX][randomY-1] = "true";
    randomX = rand.nextInt(8-1) + 2;
    randomY = rand.nextInt(8-1) + 2;
    checkBoard[randomY][randomX] = "true";
    checkBoard[randomY+1][randomX] = "true";
    System.out.println("Please guess the coordinates of the computer's ships: ");
    //While loop stuff for user and computer's turn
    int count = 0;
    int strikes = 0;
    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int x3 = 0;
    int y3 = 0;
    int x4 = 0;
    int y4 = 0;
    while (count < 5 && strikes < 5){
      System.out.println("Your board: ");
      print(battleshipBoard);
      System.out.println("The computer's board: ");
      print(aIBoard);
      System.out.println("Enter a row: ");
      x = input.nextInt();
      if (x > 9 || x < 0){
        System.out.println("The row has to be between 0-9, please try again: ");
        x  = input.nextInt();
      }
      System.out.println("Enter column: ");
      y = input.nextInt();
      if (y > 9 || y < 0){
        System.out.println("The column has to be between 0-9, please try again: ");
        y = input.nextInt();
      }
      if (battleshipBoard[x+1][y+1] == "x"){
        System.out.println("You have already fired in this spot and hit.");
      }
      else if(checkBoard[x+1][y+1] == "true"){
        count = count + 1;
        System.out.println("It's a hit!");
        battleshipBoard[x+1][y+1] = "x";
      }
      else{
        System.out.println("You missed!");
        battleshipBoard[x+1][y+1] = ".";
      }
      if(userBoard[x2][y2] == "x"){
        if (x2 == 0){
          x3 = x2;
          x4 = x2 + 1;
        }
        else if (x2 == 10){
          x3 = x2 - 1;
          x4 = x2;
        }
        else{
          x3 = x2 - 1;
          x4 = x2+1;
        }
        if (y2 == 0){
          y3 = y3;
          y4 = y2 + 1;
        }
        else if (y2 == 10){
          y3 = y2 -1;
          y4 = y2;
        }
        else{
          y3 = y2 - 1;
          y4 = y2+1;
        }
        if (userBoard[x3][y2] == "x"){
          System.out.println("The computer had a hit on "+Integer.toString(x3-1)+","+Integer.toString(y2-1)+"!");
          aIBoard[x3][y2] = "x";
          strikes = strikes + 1;
        }
        else if (userBoard[x2][y3] == "x"){
          System.out.println("The computer had a hit on "+Integer.toString(x2-1)+","+Integer.toString(y3-1)+"!");
          aIBoard[x2][y3] = "x";
          strikes = strikes + 1;
        }
        else if (userBoard[x4][y2] == "x"){
          System.out.println("The computer had a hit on "+Integer.toString(x4-1)+","+Integer.toString(y2-1)+"!");
          aIBoard[x4][y2] = "x";  
          strikes = strikes + 1;
        }
        else if (userBoard[x2][y4]== "x"){
          System.out.println("The computer had a hit on "+Integer.toString(x2-1)+","+Integer.toString(y4-1)+"!");;
          aIBoard[x2][y4] = "x";  
          strikes = strikes + 1;
        }
        x2 = 0;
        y2 = 0;
      }
      else{
        x2 = rand.nextInt(10)+1;
        y2 = rand.nextInt(10)+1;
        if (aIBoard[x2][y2] == "x"){
          while(aIBoard[x2][y2] != "x"){
            x2 = rand.nextInt(10)+1;
            y2 = rand.nextInt(10)+1;
          }
        }
        if (userBoard[x2][y2] == "x"){
          System.out.println("The computer had a hit on "+Integer.toString(x2-1)+","+Integer.toString(y2-1)+"!");
          aIBoard[x2][y2] = "x";
          strikes = strikes + 1;
        }
        else if (aIBoard[x2][y2] == "."){
          while(aIBoard[x2][y2] != "."){
            x2 = rand.nextInt(10) + 1;
            y2 = rand.nextInt(10) + 1;
          }
          if (userBoard[x2][y2] == "x"){
            System.out.println("The computer had a hit on "+Integer.toString(x2-1)+","+Integer.toString(y2-1)+"!");
            aIBoard[x2][y2] = "x";
            strikes = strikes + 1;
          }
          else{
            System.out.println("The computer missed on "+Integer.toString(x2-1)+","+Integer.toString(y2-1)+"!");
            aIBoard[x2][y2] = ".";
          }
        }
        else{
          System.out.println("The computer missed on "+Integer.toString(x2-1)+","+Integer.toString(y2-1)+"!");
          aIBoard[x2][y2] = ".";
        }
      }
    }
    System.out.println(" ");
    System.out.println(" ");
    if (strikes == 5){
      System.out.println("The computer won!");
    }
    else{
      System.out.println("You won!");
    }
    System.out.println("The computer's board: ");
    print(aIBoard);
    System.out.println("Your board:");
    print(battleshipBoard);
  }
  public static void print(String[][] array){
    for(int i = 0; i < array.length; i++){
      System.out.println(Arrays.toString(array[i]).replace("[","").replace("]","").replace(",",""));
    }
  }
}

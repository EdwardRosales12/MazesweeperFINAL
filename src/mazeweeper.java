

import java.util.Random;  // Needed for generating Random numbers
import java.util.Scanner; // Needed for Scanner
import java.util.*;		  // Needed for Stacks

/**
*
* @author Mohamed Aish, Edward Rosales, Dave Zamora
* @version 1.0
*
*/
public class mazeweeper { 
	char end = 1;
	char start = 2;

  int mWidth = 8; // width of the minefield
  int mHeight = 8; // height of the minefield
  int mMines = 10; // number of mines
  char[][] mMinefield; // 2-dimensional array of chars for our board
Stack<String> stack = new Stack<String>(); // Stack of Strings for Last move used
Scanner input = new Scanner(System.in); // Scanner
Scanner scanner = new Scanner(System.in); // Scanner


/**
* The Method Mazeweeper declares the start of the game
* It has a loop that repeats the game until someone will win or someone will lose
* After every direction inputted by the user, it will be put into the reveal method
* After revealing the direction, it will print how many mines are near
* It will also print the stack
*/
  public mazeweeper() { //Start of Game
   

    mMinefield = new char[mHeight][mWidth]; // Creating of 8 by 8 array of chars for our minefield
	System.out.println();
	System.out.println();
	System.out.println();
  	mMinefield[7][0] = 2; // Start
	mMinefield[0][7] = 1; // End
    placeMines();
    drawMinefield();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("Welcome to MazeWeeper! You are " + start + " Your goal is to reach the end "+ end + " Without 	Encountering any hidden bombs. Use W A S D to navigate. There are 10 random bombs placed. Goodluck!");
	System.out.println();	
	System.out.println();
	System.out.println();
	System.out.println("Press Enter To Continue");
	String a = scanner.nextLine();
	drawMinefield();
	System.out.println();
	System.out.println();
	System.out.println();
	do{ // Loop
	
					System.out.println("");
					System.out.print("Direction: ");					 
					String direction = input.nextLine();
					reveal(direction);
					System.out.println();		
					System.out.println();
					System.out.println();
					minesAround(direction);
					printStack(stack);
					System.out.println();
					System.out.println();
					

	} // End of Loop
while(true);
  }
 /**
 * The method placemines is used to place 10 random mines in the minefield 
 */

  public void placeMines() { // Placing of Mines in the minefield
    int minesPlaced = 0;
    Random random = new Random(); // this generates random numbers for us
    while(minesPlaced < mMines) {
      int y = random.nextInt(mWidth); 
      int x = random.nextInt(mHeight);     
      if(mMinefield[x][y] != ' ') { // Initializes an array into a bomb
        mMinefield[x][y] = ' ';		// Initializes an array into a bomb
        minesPlaced ++;
      }
  }
  }

	
/**
* The Method reveal will call the string direction which is inputted by the user
* It will go through a switch statement
* If the user's input is not equal to a mine
* It will be pushed to the stack
* If the user's input is equal to a mine
* The game will be over
* @param direction is the inputted direction
*/
		
		
	public void reveal(String direction) { // Reveal Direction
		boolean humanaLihok = false;
		int totalmines = 0;
		try{
		switch(direction){ 
				case "w":
				
					for(int x = 0; x < mHeight; x++){
						for(int y = 0; y < mWidth; y++){
							if(mMinefield[x][y] == 2){
									if(mMinefield[x - 1][y] != ' '){ // If There is no mines, Initialize to 2
										mMinefield[x - 1][y] = 2;	 // If There is no mines, Initialize to 2
										stack.push(direction); 		// Pushes direction into stack
											
									
									}
									else{ // If there is mines, Gameover
										calculateHints();
										mMinefield[x - 1][y] = '*';
										drawMinefield();
										gameover(x, y);
											
									}
									if(mMinefield[x - 1][y] == mMinefield[0][7]) // If direction reached the end, Win
									{
										winner(x, y);
									}
										
									
									mMinefield[x][y] = 0; // Last direction Initialized to 0, to continue the direction
									
									humanaLihok = true;
								break;
								
							}
								}

							if(humanaLihok){
							break;
							}
						}
							System.out.println();
						
						
					break;
				case "a": // For questions on a, s and d, refer to w
				
					for(int x = 0; x < mHeight; x++){
						for(int y = 0; y < mWidth; y++){
							if(mMinefield[x][y] == 2){
									if(mMinefield[x][y - 1] != ' '){
										mMinefield[x][y - 1] = 2;
										stack.push(direction);
									
									}
									else{
										calculateHints();
										mMinefield[x][y - 1] = '*';
										drawMinefield();
										gameover(x, y);
											
									}
									if(mMinefield[x][y - 1] == mMinefield[0][7])
									{
										winner(x, y);
									}
									mMinefield[x][y] = 0;
									
									humanaLihok = true;
								break;
								
							}
								}

							if(humanaLihok){
							break;
							}
						}
							System.out.println();
						
						
					break;
				case "s":
					for(int x = 0; x < mHeight; x++){
						for(int y = 0; y < mWidth; y++){
							if(mMinefield[x][y] == 2){
									if(mMinefield[x + 1][y] != ' '){
										mMinefield[x + 1][y] = 2;
										stack.push(direction);
							
									}
									else{
										calculateHints();
										mMinefield[x + 1][y] = '*';
										drawMinefield();
										gameover(x, y);
											
									}
									if(mMinefield[x + 1][y] == mMinefield[0][7])
									{
										winner(x, y);
									}
									mMinefield[x][y] = 0;
									
									humanaLihok = true;
								break;
								
							}
								}

							if(humanaLihok){
							break;
							}
						}
							System.out.println();
					
					break;
				case "d":
					for(int x = 0; x < mHeight; x++){
						for(int y = 0; y < mWidth; y++){
							if(mMinefield[x][y] == 2){
									if(mMinefield[x][y + 1] != ' '){
										mMinefield[x][y + 1] = 2;
										stack.push(direction);
										
									}
									else{
										calculateHints();
										mMinefield[x][y + 1] = '*';
										drawMinefield();
										gameover(x, y);
											
									}
									if(mMinefield[x][y + 1] == mMinefield[0][7])
									{
										winner(x, y);
									}
									mMinefield[x][y] = 0;
									
									humanaLihok = true;
								break;
								
							}
								}

							if(humanaLihok){
							break;
							}
						}
							System.out.println();
					break;
				default:
					System.out.println("Enter Valid Direction.");
			}
		
			drawMinefield();
		}
	catch(ArrayIndexOutOfBoundsException e) //If direction reached end of array, Instead of error, print enter valid direction
		{
			drawMinefield();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Enter Valid Direction.");
		}
	
	}

/**
* If the user's input is equal to [0][7] or the end of the maze 
* It will call the method calculateHints and drawMineField
* You win
* @param x is the row of direction 
* @param y is the column of direction
*/	

	public void winner(int x, int y){ // If direction hits the end
		System.out.println();
		calculateHints();
		drawMinefield();
		System.out.println();
    	System.out.println("YOU WIN!!!");
    	
    	System.exit(0);
		
		}

		
/**
* If the user's input is equal to a mine, then the game will be over or you lose
* @param x is the row of direction 
* @param y is the column of direction
*/		
		
		
	public void gameover(int x, int y){ // If direction hits the mine
		
			
		
		System.out.println();
		System.out.println();
    	System.out.println("		You Stepped on a Mine! Gameover!!! ");
		System.out.println();
		System.out.println();
    	System.exit(0);
	

	}
	
/**
* This method prints the stack
* First it calls the string inputted by the use
* Then it prints the stack
* After printing, it then pops out the pushed stack
* @param s is the stack
*/
	public void printStack(Stack<String> s){ //Printing of stack or the last move used
	if(s.isEmpty()){
		
	}
	else{
		System.out.println("Last Move: " + s);
		stack.pop();
	}
	}
	
	
/**
* This Method prints the array of characters or the minefield
*/	
	
  public void drawMinefield() { // Printing of array of chars or printing of minefield
	  int y = 0;
	int x = 0;
	  System.out.println();	
	  System.out.println();
	  System.out.println();
	  System.out.println();
	  System.out.println();
	  
	  System.out.println();
	  System.out.println();
	  System.out.println();
	  System.out.println();									
	  System.out.println();
	  System.out.println();
	  System.out.println();
	  System.out.println();
    System.out.println(" ");
				System.out.println(" ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
					for(x = 0; x < mHeight; x ++){	
						System.out.print(x + " ");
						}
				System.out.println();
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
				System.out.print("   ");
					for(x = 0; x < mHeight; x ++){
						System.out.print("_ ");
						
						}
				System.out.println();
					for(x = 0; x < mHeight; x ++){
						System.out.print("   ");
						System.out.print("   ");
						System.out.print("   ");
						System.out.print("   ");
						System.out.print("   ");
						System.out.print("   ");
						System.out.print(x + "| ");
							for(y = 0; y < mWidth; y ++){
								System.out.print(mMinefield[x][y] + " ");
								
						}
						System.out.println();
						}
						
						
        
      }
     
    
  /**
  * This method calculate the Hints of the minefield
  * It calls the method minesNear
  */
  

  public void calculateHints() { // to calculate hints 
     for(int x = 0; x < mHeight; x ++) {
      for(int y = 0; y < mWidth; y ++) {
        if(mMinefield[x][y] != ' ') {
          mMinefield[x][y] = minesNear(x, y);
        }
      }
    }
  }

/** This method is used for the calculateHints method 
* 	This determines where are the mines in the minefield
* @param x is the row of direction 
* @param y is the column of direction
* @return returns character
*/
  
  public char minesNear(int x, int y) { // to determine if there is mines near
    int mines = 0;
	
    // check mines in all directions
    mines += mineAt(x - 1, y - 1);  // NW
    mines += mineAt(x - 1, y);      // N
    mines += mineAt(x - 1, y + 1);  // NE
    mines += mineAt(x, y - 1);      // W
    mines += mineAt(x, y + 1);      // E
    mines += mineAt(x + 1, y - 1);  // SW
    mines += mineAt(x + 1, y);      // S
    mines += mineAt(x + 1, y + 1);  // SE
    if(mines > 0) {
      return (char)(mines + 48); // returns char so that the only blank cells are the bombs
    } else {
      return 1; //mga walay bombs nga duol
    }
  }
  
  
  /**
  * This method determines if there are mines around the inputted direction by the user
  * If there are mines around the inputted direction, it will add up the total of mines and print the total mines
  * @param direction is the inputted direction by the user
  */
  
  public void minesAround(String direction){ // To determine if there are mines arround the direction
		
		int totalmines = 0;
		int mineNW, mineN, mineNE, mineW, mineE, mineSW, mineS, mineSE = 0;
		
		for(int x = 0; x < mHeight; x++){
			for(int y = 0; y < mWidth; y++){
				if(mMinefield[x][y] == 2){	
			if((x + 1 == 8) || (x - 1 < 0) || (y + 1 == 8) || (y - 1 < 0)){
			try
			{
				
				if(mMinefield[x-1][y-1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x-1][y] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x-1][y+1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x][y-1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x][y+1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x+1][y-1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x+1][y] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x+1][y+1] == ' ') {
					 totalmines++;
				 }
				
				 
       }
	   
       catch(ArrayIndexOutOfBoundsException e)
       {
		   
          System.out.println("Mines Near: " + totalmines);
       }
		}

		else{
		if(mMinefield[x-1][y-1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x-1][y] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x-1][y+1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x][y-1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x][y+1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x+1][y-1] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x+1][y] == ' ') {
					 totalmines++;
				 }
				 if(mMinefield[x+1][y+1] == ' ') {
					 totalmines++;
				 }
				 
				 System.out.println("Mines Near: " + totalmines);
	   
		}
		

		
				
															}
  }
		}
  }
	/**
	* This method determines if there is a mine at a certain cell
	* returns blank if there is a mine and returns 1 if there is no mine
	* @param x is the row of direction 
	* @param y is the column of direction
	* @return returns integer
	*/

  
  public int mineAt(int x, int y) { // To determine if there is a mine at a certain cell
	// returns blank if there is a mine and return 1 if there is no mine
    // we need to check also that we're not out of array bounds as that would be an error
    
	
    if(x > 0 && x < mHeight && y > 0 && y < mWidth && mMinefield[x][y] == ' ') {
		
      return 1;
    } else {
      return 1;
    }
	
  }
  
  

  // this starts the command line application
  public static void main(String[] args) { // Main method
    Mazeweeper mazeweeper = new Mazeweeper();
	Scanner input = new Scanner(System.in);
	
}
}

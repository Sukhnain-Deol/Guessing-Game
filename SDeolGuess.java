// Programmer: Sukhnain Deol
// Date: 11/1/22
// Class: Computer Science 141
// Assignment: Lab 4: Guessing Game

// this program will play a guessing game with the user. The user can input which numbers will be the largest and smallest guessable.
// Then, they will guess what the random number is. The game loops until they guess correctly. 
// After that, the program asks if they want to play again. Once they say don't want to play anymore,
// the program prints the total games played, total guesses, lowest guesses in a game and everage guesses per game.

// for extra credit, I allowed the user to choose the guessable number range (including negatives)

import java.util.*;

public class SDeolGuess 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in); // scanner for input

        int randomNum; // randomly generated number

        // Stats
        int singleGameGuesses = 0; // used to track guesses for a sigle game
        int totalGuesses = 0; // total number of guesses across all games
        double avgGameGuesses = 0; // average number of guesses across all games
        int lowestGuessCount = 0; // lowest guesses in a game across all games
        int totalGames = 0; // total games played 

        boolean playerWantsToPlay = true; // true if player wants to play
        

        while (playerWantsToPlay == true)
        {
            // getting input for lowest and highest guesses
            int lowestGuess = askForLowestGuess(in); // method asks for and returns lowest guess
            in.nextLine(); // clears lines before asking for new input 
            int highestGuess = askForHighestGuess(in, lowestGuess);// method asks for and returns highest guess
            in.nextLine(); // clears lines before asking for new input (which is "What is your Guess?: ")

            // prints instructions with inputted highest and lowest numbers possible
            printInstructions(lowestGuess, highestGuess);

            // generates random number
            randomNum = (int)(Math.floor(Math.random() * (highestGuess-lowestGuess+ 1)+lowestGuess));

            singleGameGuesses = 0; // resets single game guesses each game

            singleGameGuesses = playGuessingGame(randomNum, in); // plays Game

            // updates stats
            totalGames++; // keeps tracks of number of games
            totalGuesses += singleGameGuesses; // increases total guesses after each 

            // checks if this game has lowest guess count (if it hasnt been set yet, the first game's count is taken)
            if (lowestGuessCount > singleGameGuesses | totalGames == 1) // if this game took less guesses than the last
                {lowestGuessCount = singleGameGuesses;} // updates lowest guesses
            
            // asks player if they want to play another game
            System.out.println("\nDo you want to play again? (y/n): ");
            String playerChoice = in.next().toLowerCase(); // lowercases and assigns output
            if(playerChoice.charAt(0)  == 'y') // if player wants to play again (answer stats with y)
            {   // clears lines before asking for new inputs
                in.nextLine();
            } 
            else // if player doesnt wanna play again
                {playerWantsToPlay = false;} // ends game loop

        } // end of outermost while loop

        avgGameGuesses = (double)totalGuesses/totalGames; // calculates average guesses per game
        
        // gives player summary of each of the statistics stated below
        printSummary(totalGames, totalGuesses, lowestGuessCount, avgGameGuesses);

        in.close(); // closes scanner
    } // end of main




    // prints instructions for guessing game, variables are for lowest and highest number that is guessable
    public static void printInstructions(int lowestNum, int highestNum)
    {
        System.out.println("\nThis program allows you to play a guessing game.");
        System.out.println("I will think of a number between " + lowestNum + " and " + highestNum + ".");
        System.out.println("You will be able to guess what number I am thinking of.");
        System.out.println("Each time you guess, I will tell you if your guess is");
        System.out.println("correct, lower, higher than my number.\n");
    }// end of printInstructions



    // uses scanner to ask user for the lowest guessable number in the game
    public static int askForLowestGuess(Scanner in)
    {
        boolean correctLowestNum = false; // if input for lowest number is an int
        int lowestGuess = 0; // stores lowest number chosen 

        while (correctLowestNum == false) // loops until a usable input is given
        {
            System.out.println("What is the lowest whole number you want to be guessable?: ");
            if(in.hasNextInt() == true) // if input is an int
            {
                lowestGuess = in.nextInt();
                correctLowestNum = true; // ends loop
            }
            else 
            {
                System.out.println("Error: Incorrect Input");
                in.nextLine(); // goes next line to allow for player to give another input
            }
        }
        
        return lowestGuess;
    }



    // uses scanenr to ask user for the highest guessable number in the game & makes sure its higher than the lowest
    public static int askForHighestGuess(Scanner in, int lowestGuess)
    {
        boolean correctHighestNum = false; // if input for highest number is an int
        int highestGuess = 0; // stores highest number chosen

        while (correctHighestNum == false)  // loops until a usable input is given
        {
            System.out.println("What is the highest whole number you want to be guessable?: ");
            if(in.hasNextInt() == true) // if input is an int
            {
                highestGuess = in.nextInt(); // stores input
                if (highestGuess > lowestGuess) // if highest number input is higher than lowest number input
                    {correctHighestNum = true;} // ends loop
                else 
                {
                    System.out.println("Error: Number Is Not Higher Than Previous Input");
                    in.nextLine(); // goes next line to allow for player to give another input
                }
            }
            else 
            {
                System.out.println("Error: Incorrect Input");
                in.nextLine(); // goes next line to allow for player to give another input
            }
        }

        return highestGuess;
    }


    // main body of the game.
    // takes in randomly generated number and scanner
    // output number of gueses required to guess correclty
    public static int playGuessingGame(int randomNum, Scanner in)
    {
        boolean playerIsGuessing = true; // allows while loop until player guesses correctly
        boolean correctGuessType = false; // if guess is an int

        int guesses = 0; // used to track guesses for a sigle game
        int playerGuess = 0; // stores players guess (placeholder value)

        while (playerIsGuessing) 
        {
            correctGuessType = false;; // resets to make sure correct input is given each time
            guesses++; // keeps tracks of number of guesses in a single game

            while (correctGuessType == false) // loops until a usable input is given
            {
                System.out.println("Your Guess?: ");
                if(in.hasNextInt() == true) // if input is an int
                {
                    playerGuess = in.nextInt();
                    correctGuessType = true; // ends loop
                }
                else 
                {
                    System.out.println("Error: Incorrect Input");
                    in.nextLine(); // goes next line to allow for player to give another input
                }
            }// end of inner while loop

            if(playerGuess > randomNum) // guess too high
                {System.out.println("It's lower.");}
            else if (playerGuess < randomNum) // guess too low
                {System.out.println("It's higher.");}
            else // correct guess
            {
                System.out.println("You got it correct in " + guesses + " guesses!"); // tells player they got correct number 
                playerIsGuessing = false; // ends guessing loop
            }

        }// end of outer while loop
        return guesses; // returns number of guesses to guess correct
    }



    // prints the summary of the results of the game(s) played
    public static void printSummary(int totalGames, int totalGuesses, int lowestGuessCount, double averageGameGuesses)
    {
        System.out.println("\nOverall Results:");
        System.out.println(" - Total Games: " + totalGames);
        System.out.println(" - Total Guesses: " + totalGuesses );
        System.out.printf(" - Average Guesses Per Game: %.1f\n", averageGameGuesses); // rounds to first decimal
        System.out.println(" - Lowest Guesses in a Game: " + lowestGuessCount);
    }
}// end of class

namespace MyFirstConsoleGame;

class Program
{
    static void Main(string[] args)
    {
        guessTheNumber();
    }

    static void guessTheNumber(){
        Console.WriteLine("Guess the number between 1 and 10");
        Random r = new Random();
        int myNumber = r.Next(1,10);
        int userGuess = 0;
        int attemps = 0;

        while(userGuess != myNumber){

            attemps++;
            userGuess = getInput();
            if (userGuess>myNumber)
            {
                Console.WriteLine("The number is smaller.");
            }else if (userGuess<myNumber)
            {
                Console.WriteLine("The number is greater.");
            }
        }
        Console.BackgroundColor = ConsoleColor.Blue;
        Console.ForegroundColor = ConsoleColor.White;
        Console.Write($"Congratulations!! You got it in {attemps} attemps.");
        Console.ResetColor();
    }

    static int getInput(){
        Console.WriteLine("Type your guess:");
        string input = Console.ReadLine()?? "#";
        try
        {
            return int.Parse(input);
        }
        catch (System.Exception)
        {
            Console.WriteLine("Invalid input! Please type a number.");
            return getInput();
        }
    }



}

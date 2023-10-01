#include <iostream>
#include <ctime>
using namespace std;

int main()
{
    srand(time(0));

    int minNum = 1;
    int maxNum = 100;
    int numberToGuess = rand() % (maxNum - minNum + 1) + minNum;

    int numberOfTries = 0;
    int guess;

    cout << "Welcome to the Guess the Number Game!" << endl;
    cout << "I'm thinking of a number between " << minNum << " and " << maxNum << "." << endl;

    do
    {
        cout << "Enter your guess: ";
        cin >> guess;

        numberOfTries++;

        if (guess < numberToGuess) // Check if the guessed number if lower than the expected number
            cout << "Too low. Try again!" << endl;
        else if (guess > numberToGuess) // Check if the guessed number if higher than the expected number
            cout << "Too High. Try again" << endl;
        else // Check if the guessed number equals the expected number
            cout << "Congratulations! You have guessed the number." << endl;
    } while (guess != numberToGuess);

    return 0;
}
#include <iostream>
#include <ctime>
using namespace std;

int main()
{
    // Seed the random number generator with the current time
    srand(time(0));

    int computerChoice;
    int playerChoice;

    cout << "Let's play Rock, Paper and Scissors: " << endl;
    cout << "1. Rock" << endl;
    cout << "2. Paper" << endl;
    cout << "3. Scissors" << endl;
    cout << "Enter your choice (1/2/3): " << endl;
    cin >> playerChoice;

    // Check if the choice entered by the player is valid or not.
    if (playerChoice < 1 || playerChoice > 3)
    {
        cout << "Invalid Choice. Please choose 1, 2, or 3." << endl;
        return 1;
    }

    // Generate a random number for computer (1 for Rock, 2 for Paper and 3 for Scissors)
    computerChoice = rand() % 3 + 1;

    cout << "Computer chose: ";
    switch (computerChoice)
    {
    case 1:
        cout << "Rock";
        break;
    case 2:
        cout << "Paper";
        break;
    case 3:
        cout << "Scissors";
        break;
    }
    cout << endl;

    cout << "Player chose: ";
    switch (playerChoice)
    {
    case 1:
        cout << "Rock";
        break;
    case 2:
        cout << "Paper";
        break;
    case 3:
        cout << "Scissors";
        break;
    }
    cout << endl;

    // Determine the winner
    if (computerChoice == playerChoice)
        cout << "It's a tie!" << endl;
    else if ((computerChoice == 1 && playerChoice == 3) || (computerChoice == 2 && playerChoice == 1) || computerChoice == 3 && playerChoice == 2)
        cout << "Computer won!" << endl;
    else
        cout << "Player won!" << endl;

    return 0;
}
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <conio.h>
#include <time.h>
#include <Windows.h>

using namespace std;

class Hangman
{
private:
	int guessCount;
	bool gameOver;
	string guessedWord;
	string wordToGuess;
public:
	Hangman()
	{
		guessCount = 0;
		gameOver = true;
		guessedWord = "";
		wordToGuess = "";
	}
	void PrintMessage(string message, bool printTop = true, bool printBottom = true)
	{
		if (printTop)
		{
			cout << "+------------------------------------+" << endl;
			cout << "|";
		}
		else
			cout << "|";
		bool flag = true;
		for (int i = message.length(); i < 36; i++)
		{
			if (flag)
				message = message + " ";
			else if (!flag)
				message = " " + message;

			flag = !flag;
		}
		cout << message;
		if (printBottom)
		{
			cout << "|" << endl;
			cout << "+------------------------------------+" << endl;
		}
		else
			cout << "|" << endl;
	}
	void DrawHangman()
	{
		if (guessCount >= 1)
			PrintMessage("!", 0, 0);
		else
			PrintMessage("", 0, 0);

		if (guessCount >= 2)
			PrintMessage("!", 0, 0);
		else
			PrintMessage("", 0, 0);

		if (guessCount == 10)
			PrintMessage("@", 0, 0);
		else if (guessCount >= 3)
			PrintMessage("O", 0, 0);
		else
			PrintMessage("", 0, 0);

		if (guessCount == 4)
			PrintMessage("|", 0, 0);
		else if (guessCount == 5)
			PrintMessage("\\| ", 0, 0);

		if (guessCount >= 6)
			PrintMessage("\\|/", 0, 0);
		else
			PrintMessage("", 0, 0);

		if (guessCount >= 7)
			PrintMessage("|", 0, 0);
		else
			PrintMessage("", 0, 0);

		if (guessCount == 8)
			PrintMessage("/  ", 0, 0);

		if (guessCount >= 9)
			PrintMessage("/ \\", 0, 0);
		else
			PrintMessage("", 0, 0);
	}
	void RandomWord(string path = "words.txt")
	{
		srand(time(0));
		vector<string> v;
		ifstream file(path);

		if (file.is_open())
			while (getline(file, wordToGuess))
				v.push_back(wordToGuess);
		file.close();

		wordToGuess = v.at(rand() % v.size());
	}
	void PrintLetters(char from, char to)
	{
		string S;
		for (char i = from; i <= to; i++)
		{
			if (guessedWord.find(i) == string::npos)
			{
				S += i;
				S += " ";
			}
			else
			{
				S += "  ";
			}
		}
		PrintMessage(S, 0, 0);
	}
	void RemainingLetters()
	{
		PrintMessage("REMAINING LETTERS");
		PrintLetters('a', 'm');
		PrintLetters('n', 'z');
	}
	void PrintWord()
	{
		PrintMessage("GUESS THE WORD");
		string S;
		for (int i = 0; i < wordToGuess.length(); i++)
		{
			if (guessedWord.find(wordToGuess[i]) == string::npos)
			{
				gameOver = false;
				S += "_ ";
			}
			else
			{
				S += wordToGuess[i];
				S += " ";
			}
		}
		PrintMessage(S, 0);
	}
	void CountGuess(string newGuess)
	{
		for (int i = 0; i < newGuess.length(); i++)
			if (wordToGuess.find(newGuess[i]) == string::npos)
				guessCount++;
	}
	void Setup()
	{
		RandomWord();
		do
		{
			system("cls");
			PrintMessage("MADE BY SIDHARTH");
			DrawHangman();
			RemainingLetters();
			PrintWord();

			string ch;
			cout << ">"; cin >> ch;
			if (guessedWord.find(ch) == string::npos)
			{
				guessedWord += ch;
				CountGuess(ch);
			}

			if (gameOver)
				break;

			if (_kbhit())
			{
				char ch = _getch();
				if (ch == '0')
					break;
			}

		} while (guessCount < 10);

		if (gameOver)
			cout << "You Win" << endl;;
		
		cout << "WORD: " << wordToGuess << endl;
		cout << "Game Over" << endl;;
	}
};

int main()
{
	Hangman h;
	h.Setup();
	return 0;
}
#include <iostream>
#include <conio.h>
#include <windows.h>
using namespace std;

// For game type and game over
int gameType;
bool gameOver;

// For box height and width
const int height = 25;
const int width = 60;

// For position of smake's head & fruit and for score
int x, y, fruitX, fruitY, score;

// For all snake's tail parts position
int tailX[100], tailY[100];

// For tail length
int nTail;

// For directions for the snake
enum direction { STOP = 0, LEFT, RIGHT, UP, DOWN };
direction dir;

// function to create setup for the start of game
void Setup()
{
	// will game have killer or transparent walls
	cout << "1 | killer walls\n2 | transparent walls\n-->";
	cin >> gameType;

	// Initial values are set
	gameOver = false;
	dir = STOP;
	x = width / 2;
	y = height / 2;
	fruitX = rand() % width;
	fruitY = rand() % height;
	score = 0;
	nTail = 0;
}

// functions to draw the game
void Draw()
{
	// for moving cursor to pposition (0,0) to overwrite the screen and stop blinking
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), { 0, 0 });

	// for upper wall
	for (int i = 0; i < width + 2; i++)
		cout << "\xDC"; // hex code for the lower half block

	cout << endl;

	// for snake's head & tail, side walls, fruit and score
	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			// for left walls
			if (j == 0)
				cout << "\xDB"; // hex code for the full block

			// for snake's head
			if (i == y && j == x)
				cout << "O";
			// for fruit
			else if (i == fruitY && j == fruitX)
				cout << "$";
			// for snake's tail
			else
			{
				bool print = false;
				for (int k = 0; k < nTail; k++)
				{
					if (tailX[k] == j && tailY[k] == i)
					{
						cout << "0";
						print = true;
					}
				}
				// empty space between the walls
				if (!print)
					cout << " ";
			}

			// for right wall
			if (j == width - 1)
				cout << "\xDB";
		}
		cout << endl;
	}

	// for bottom walls
	for (int i = 0; i < width + 2; i++)
		cout << "\xDF"; // hex code for the upper half block

	// for score
	cout << "\nScore = " << score;
}

// function to control the snake
void Input()
{
	// if keyboard botton is hitted
	if (_kbhit())
	{
		// taking the output from the keyboard
		switch (_getch())
		{
		case 'a':
			dir = LEFT; // left direction
			break;
		case 'd':
			dir = RIGHT; // right direction
			break;
		case 'w':
			dir = UP; // up direction
			break;
		case 's':
			dir = DOWN; // down direction
			break;
		case 'x':
			gameOver = true; // gameover
			break;
		default:   // any other key
			break;
		}
	}
}

// function for game logic
void Logic()
{
	int prevX = tailX[0];
	int prevY = tailY[0];
	int tempX, tempY;
	tailX[0] = x;
	tailY[0] = y;

	// for each tails swapping the position of previous tail with it's forward tail
	for (int i = 1; i < nTail; i++)
	{
		tempX = tailX[i];
		tempY = tailY[i];

		tailX[i] = prevX;
		tailY[i] = prevY;

		prevX = tempX;
		prevY = tempY;
	}

	// making snake walk in the direction according the user
	switch (dir)
	{
	case LEFT:
		x--;
		break;
	case RIGHT:
		x++;
		break;
	case UP:
		y--;
		break;
	case DOWN:
		y++;
		break;
	case 'x':
		gameOver = true;
		break;
	default:
		break;
	}

	// for killer walls if snake touch the walls game will over
	if (gameType == 1)
	{
		if (x > width || y > height || x < 0 || y < 0)
			gameOver = true;
	}

	// for invisible walls if snake touch the walls game will not over and snake will start walking from opposite walls
	else if (gameType == 2)
	{
		if (x > width)
			x = 0;

		else if (x < 0)
			x = width - 1;

		else if (y > height)
			y = 0;

		else if (y < 0)
			y = height - 1;
	}

	// if snake's head touches the snake's tail game will over
	for (int i = 0; i < nTail; i++)
	{
		if (tailX[i] == x && tailY[i] == y)
			gameOver = true;
	}

	// each time snake eats the fruit, new fruit generated, score is updated and one extra tail is added
	if (x == fruitX && y == fruitY)
	{
		score += 10;
		fruitX = rand() % width;
		fruitY = rand() % height;
		nTail++;
	}
}

int main()
{
	system("cls");
	Setup();

	// running the game
	while (!gameOver)
	{
		Draw();
		Input();
		Logic();
	}

	// Printing final score
	if (gameOver)
	{
		system("cls");
		cout << "____________GAME OVER____________\n";
		cout << "      Better Luck Next Time\n";
		cout << "SCORE: " << score;
		cout << "\npress 'y' to play again";

		if (_kbhit)
		{
			switch (_getch())
			{
			case 'y':
				main();
				break;
			default:
				break;
			}
		}
	}

	return 0;
}
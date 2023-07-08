#include <iostream>
#include <conio.h>
#include <time.h>
#include <Windows.h>
using namespace std;

// for directions of ball and paddles
enum eDirection{STOP, RIGHT, UPRIGHT, DOWNRIGHT, LEFT, UPLEFT, DOWNLEFT};
enum eControler{PAUSE, UP, DOWN};

// class for pong ball
class cBall
{
private:
	int x, y;
	int originalX, originalY;
	eDirection dir;

public:
	// constructor to set starting position & direction of the ball
	cBall(int posX, int posY)
	{
		originalX = posX;
		originalY = posY;
		x = posX; y = posY;
		dir = STOP;
	}
	// function to reset the position & direction of ball to the starting position
	void reset()
	{
		x = originalX;
		y = originalY;
		dir = STOP;
	}
	// function to change direction of the ball
	void changeDirection(eDirection direction)
	{
		dir = direction;
	}
	// function to set a random direction to the ball
	void randomDirection()
	{
		srand(time(0));
		dir = (eDirection)((rand() % 6) + 1);
	}
	// functions to get current x and y co-ordinates of the ball
	inline int getX() { return x; }
	inline int getY() { return y; }
	// function to get the current direction of the ball
	inline int getDir() { return dir; }
	// function to move the ball according to the direction
	void move()
	{
		switch (dir)
		{
		case STOP:
			break;
		case RIGHT:
			x++;
			break;
		case UPRIGHT:
			x++; y--;
			break;
		case DOWNRIGHT:
			x++; y++;
			break;
		case LEFT:
			x--;
			break;
		case UPLEFT:
			x--; y--;
			break;
		case DOWNLEFT:
			x--; y++;
			break;
		default:
			break;
		}
	}
};

// class for the paddles
class cPaddle
{
private:
	int x, y;
	int originalX, originalY;
	eControler joyStick;

public:
	// constructor to set the position and direction of paddles to the starting position
	cPaddle(int posX, int posY)
	{
		originalX = posX;
		originalY = posY;
		x = posX;
		y = posY;
		joyStick = PAUSE;
	}
	// function to reset the position and direction of the paddles to the starting positions
	void reset()
	{
		x = originalX;
		y = originalY;
		joyStick = PAUSE;
	}
	// functions to get current x and y co-ordinates of the paddles
	inline int getX() { return x; }
	inline int getY() { return y; }
	// function to get current direction of paddles
	void changeDirection(eControler dir) { joyStick = dir; }
	void move()
	{
		switch (joyStick)
		{
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case PAUSE:
			break;
		default:
			break;
		}
	}
};

// class to manage the game
class cGameManager
{
private:
	int width, height;
	int score1, score2;
	bool quit;
	// dynamically initializing ball, player1 & player2
	cBall* ball;
	cPaddle* player1;
	cPaddle *player2;

public:
	// constructor to set the height, width of the box and starting positions
	cGameManager()
	{
		quit = false;
		height = 15; width = 50;
		score1 = score2 = 0;
		ball = new cBall(width / 2, height / 2);
		player1 = new cPaddle(1, height / 2);
		player2 = new cPaddle(width - 2, height / 2);
	}
	// destructor to delete dynamically created class objects
	~cGameManager()
	{
		delete ball, player1, player2;
	}
	// function to increasing score then reset to starting positions
	void ScoreUp(cPaddle* player)
	{
		if (player == player1)
			score1++;
		else if (player == player2)
			score2++;

		ball->reset();
		player1->reset();
		player2->reset();
	}
	// function to draw the layout of the box
	void Draw()
	{
		// to set cursor to the position (0,0) of the screen to overwrite all things and stop blinking of the screen
		SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), { 0, 0 });

		// top wall
		for (int i = 0; i < width + 2; i++)
			cout << "\xB2";
		cout << endl;

		// right and left walls
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				int ballX = ball->getX();
				int ballY = ball->getY();
				int player1x = player1->getX();
				int player1y = player1->getY();
				int player2x = player2->getX();
				int player2y = player2->getY();

				if (j == 0)
					cout << "\xB1";

				// ball, right and left paddle
				if (ballX == j && ballY == i)
					cout << "o";
				else if (player1x == j && (player1y >= i && player1y < i + 4))
					cout << "\xDB";
				else if (player2x == j && (player2y >= i && player2y < i + 4))
					cout << "\xDB";
				else
					cout << " ";

				if (j == width - 1)
					cout << "\xB1";
			}
			cout << endl;
		}
		
		// bottom wall
		for (int i = 0; i < width + 2; i++)
			cout << "\xB2";
		cout << endl;

		// score
		for (int i = 0; i < width / 2 - 3; i++)
			cout << " ";
		cout << score1 << " : " << score2 << endl;
	}
	// function to control the paddles
	void Input()
	{
		int player1y = player1->getY();
		int player2y = player2->getY();

		if (_kbhit())
		{
			// if any key is pressed game will start
			if (ball->getDir() == STOP)
				ball->randomDirection();

			switch (_getch())
			{
			case 'w':
				if (player1y - 3 > 0) // if paddle not hit the top wall
					player1->changeDirection(UP);
				break;
			case 's':
				if (player1y + 1 < height) // if paddle not hit the bottom wall
					player1->changeDirection(DOWN);
				break;
			case 'i':
				if (player2y - 3 > 0) // if paddle not hit the top wall
					player2->changeDirection(UP);
				break;
			case 'j':
				if (player2y + 1 < height) // if paddle not hit the bottom wall
					player2->changeDirection(DOWN);
				break;
			case 'q':
				quit = true;
				break;
			default:
				break;
			}
		}
	}
	// function for the logic of the game
	void Logic()
	{
		// moving ball and paddles
		ball->move();
		player1->move();
		player2->move();

		// getting the positions of ball and paddles
		int ballX = ball->getX();
		int ballY = ball->getY();
		int player1x = player1->getX();
		int player1y = player1->getY();
		int player2x = player2->getX();
		int player2y = player2->getY();

		// reflecting direction of ball of ball hits the paddle
		if (ballX == player1x + 1)
		{
			if (ballY == player1y - 3)
				ball->changeDirection(UPRIGHT);
			else if (ballY == player1y - 2 || ballY == player1y - 1)
				ball->changeDirection((eDirection)(rand()%3 + 1)); // random right directions
			else if (ballY == player1y)
				ball->changeDirection(DOWNRIGHT);
		}
		else if (ballX == player2x - 1)
		{
			if (ballY == player2y - 3)
				ball->changeDirection(UPLEFT);
			else if (ballY == player2y - 2 || ballY == player2y - 1)
				ball->changeDirection((eDirection)(rand() % 3 + 4)); // random left directions
			else if (ballY == player2y)
				ball->changeDirection(DOWNLEFT);
		}

		// reflecting ball direction of ball if ball hits the top or bottom wall 
		// and increasing score of player if ball goes through the right or left wall
		if (ballY == height - 1)
			ball->changeDirection(ball->getDir() == DOWNRIGHT ? UPRIGHT : UPLEFT);
		else if (ballY == 0)
			ball->changeDirection(ball->getDir() == UPRIGHT ? DOWNRIGHT : DOWNLEFT);
		else if (ballX == width - 1)
			ScoreUp(player1);
		else if (ballX == 0)
			ScoreUp(player2);

		// stop moving paddle if any paddle hits the top or bottom wall
		if (player1y - 4 < 0)
			player1->changeDirection(PAUSE);
		else if (player1y + 2 > height)
			player1->changeDirection(PAUSE);
		
		if (player2y - 4 < 0)
			player2->changeDirection(PAUSE);
		else if (player2y + 2 > height)
			player2->changeDirection(PAUSE);

		// gameOver if any player scored 10 points
		if (score1 == 10)
			quit = true;
		else if (score2 == 10)
			quit = true;
	}
	// function to setup the game	
	void Game()
	{
		string p1, p2;
		cout << "Player1: ";
		cin >> p1;
		cout << "Player2: ";
		cin >> p2;

		while (!quit)
		{
			Draw();
			Input();
			Logic();
		}
		
		if (score1 != score2)
			score1 > score2 ? cout << p1 << " Won" : cout << p2 << " Won";
		else
			cout << "Tie";
	}
};

int main()
{
	cGameManager c;
	c.Game();
	return 0;
}
#ifndef MAIN_H
#define MAIN_H

#include "gba.h"

// TODO: Create any necessary structs

/*
* For example, for a Snake game, one could be:
*
* struct snake {
*   int heading;
*   int length;
*   int row;
*   int col;
* };
*
* Example of a struct to hold state machine data:
*
* struct state {
*   int currentState;
*   int nextState;
* };
*
*/

enum gba_state {
  START,
  HOLDSTART,
  INIT,
  PLAY,
  LOSE,
};

struct player {
    int row;
    int col;
    int speed;
    int width;
    int height;
};

struct enemy {
    int row;
    int col;
    int speed;
    int width;
    int height;
    int x;
    int y;
};

struct stateMachine {
  enum gba_state gameState;
  struct player player;
  struct enemy enemy;
  int score;
  int highScore;
};
#endif

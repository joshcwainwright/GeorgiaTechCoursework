#include "work.h"
#include <stdio.h>
#include <stdlib.h>
#include "gba.h"
#include "main.h"
#include "images/background.h"
#include "images/princess.h"
#include "images/king.h"

struct stateMachine startWork(struct stateMachine currentState) {
    waitForVBlank();
    drawFullScreenImageDMA(background);
    drawCenteredString(10, WIDTH/2 - 40, 80, 20, "DONT GET ZAPPED!", BLACK);
    drawCenteredString(80, WIDTH/2 - 40, 80, 20, "USE ARROW KEYS", BLACK);
    drawCenteredString(130, WIDTH/2 - 30, 60, 20, "PRESS START", BLACK);
    currentState.gameState = HOLDSTART;
    return currentState;
}

struct stateMachine holdStartWork(struct stateMachine currentState, u32 previousButtons, u32 currentButtons) {
    if (KEY_JUST_PRESSED(BUTTON_START, currentButtons, previousButtons)) {
        currentState.gameState = INIT;
    }
    return currentState;
}

struct stateMachine initWork(struct stateMachine currentState) {
    struct player player;
    player.speed = 1;
    player.width = 22;
    player.height = 30;
    player.row = 100;
    player.col = 120 - (player.width / 2);
    currentState.player = player;
    struct enemy enemy;
    enemy.speed = 1;
    enemy.width = 30;
    enemy.height = 30;
    enemy.row = 20;
    enemy.col = 120 - (player.width / 2);
    enemy.x = 1;
    enemy.y = 1;
    currentState.enemy = enemy;
    currentState.score = 0;
    currentState.gameState = PLAY;
    waitForVBlank();
    fillScreenDMA(BLACK);
    drawImageDMA(currentState.player.row, currentState.player.col, player.width, player.height, princess);
    drawImageDMA(currentState.enemy.row, currentState.enemy.col, enemy.width, enemy.height, king);
    return currentState;
}


struct stateMachine playWork(struct stateMachine previousState, struct stateMachine currentState, u32 currentButtons) {
    //user input

    if (KEY_DOWN(BUTTON_UP, currentButtons)) {
        currentState.player.row -= currentState.player.speed;
    }
    if (KEY_DOWN(BUTTON_DOWN, currentButtons)) {
        currentState.player.row += currentState.player.speed;
    }
    if (KEY_DOWN(BUTTON_LEFT, currentButtons)) {
        currentState.player.col -= currentState.player.speed;
    }
    if (KEY_DOWN(BUTTON_RIGHT, currentButtons)) {
        currentState.player.col += currentState.player.speed;
    }

    //player movement
    if (currentState.player.col >= WIDTH - currentState.player.width) {
        currentState.player.col = WIDTH - currentState.player.width;
    } else if (currentState.player.col <= 0) {
        currentState.player.col = 0;
    }
    if (currentState.player.row >= HEIGHT - currentState.player.height) {
        currentState.player.row = HEIGHT - currentState.player.height;
    } else if (currentState.player.row <= 0) {
        currentState.player.row = 0;
    }

    // enemy movement
    currentState.enemy.col+= currentState.enemy.speed * currentState.enemy.x;
    currentState.enemy.row += currentState.enemy.speed * currentState.enemy.y;
    if (currentState.enemy.col >= WIDTH - currentState.enemy.width) {
        currentState.enemy.col = WIDTH - currentState.enemy.width - 1;
        currentState.enemy.x = -1;
    } else if (currentState.enemy.col <= 0) {
        currentState.enemy.col = 0;
        currentState.enemy.x = 1;
    }
    if (currentState.enemy.row >= HEIGHT - currentState.enemy.height) {
        currentState.enemy.row = HEIGHT - currentState.enemy.height - 1;
        currentState.enemy.y = -1;
    } else if (currentState.enemy.row <= 0) {
        currentState.enemy.row = 0;
        currentState.enemy.y = 1;
    }

    //check enemy collision
    if (vertC(currentState) + horzC(currentState) == 2) {
        currentState.gameState = LOSE;
    }

    //score board
    currentState.score++;
    if (currentState.score % 300 == 0) {
        currentState.enemy.speed += 1;
        currentState.player.speed += 1;
    }


    //drawing
    waitForVBlank();
    drawRectDMA(previousState.player.row, previousState.player.col, previousState.player.width, previousState.player.height, BLACK);
    drawRectDMA(previousState.enemy.row, previousState.enemy.col, previousState.enemy.width, previousState.enemy.height, BLACK);
    drawRectDMA(150, 10, 100, 15, BLACK);
    drawImageDMA(currentState.player.row, currentState.player.col, currentState.player.width, currentState.player.height, princess);
    drawImageDMA(currentState.enemy.row, currentState.enemy.col, currentState.enemy.width, currentState.enemy.height, king);
    char scoreString[20];
    sprintf(scoreString, "SCORE: %i", currentState.score);
    drawString(150, 10, scoreString, WHITE);
    return currentState;
}

int vertC(struct stateMachine currentState) {
    if (currentState.player.row > currentState.enemy.row && currentState.player.row < currentState.enemy.row + currentState.enemy.height) {
        return 1;
    }
    if (currentState.player.row + currentState.player.height > currentState.enemy.row && currentState.player.row + currentState.player.height < currentState.enemy.row + currentState.enemy.height) {
        return 1;
    }
    return 0;
}

int horzC(struct stateMachine currentState) {
    if (currentState.player.col > currentState.enemy.col && currentState.player.col < currentState.enemy.col + currentState.enemy.width) {
        return 1;
    }
    if (currentState.player.col + currentState.player.width > currentState.enemy.col && currentState.player.col + currentState.player.width < currentState.enemy.col + currentState.enemy.width) {
        return 1;
    }
    return 0;
}

struct stateMachine loseWork(struct stateMachine currentState) {
    waitForVBlank();
    fillScreenDMA(BLACK);
    drawCenteredString(10, WIDTH/2 - 40, 80, 20, "YOU LOST :(", RED);
    char scoreString[20];
    sprintf(scoreString, "SCORE: %i", currentState.score);
    drawCenteredString(50, WIDTH/2 - 40, 80, 20, scoreString, RED);
    if (currentState.highScore < currentState.score) {
        currentState.highScore = currentState.score;
    }
    char highScoreString[20];
    sprintf(highScoreString, "HIGH SCORE: %i", currentState.highScore);
    drawCenteredString(90, WIDTH/2 - 40, 80, 20, highScoreString, RED);
    drawCenteredString(130, WIDTH/2 - 30, 60, 20, "PRESS SELECT TO TRY AGAIN", RED);
    currentState.gameState = HOLDSTART;
    return currentState;
}

#ifndef WORK_H
#define WORK_H
#include "main.h"
#include "gba.h"
struct stateMachine startWork(struct stateMachine currentState);
struct stateMachine holdStartWork(struct stateMachine currentState, u32 previousButtons, u32 currentButtons);
struct stateMachine initWork(struct stateMachine currentState);
struct stateMachine playWork(struct stateMachine previousState, struct stateMachine currentState, u32 currentButtons);
struct stateMachine loseWork(struct stateMachine currentState);
int vertC(struct stateMachine currentState);
int horzC(struct stateMachine currentState);
#endif

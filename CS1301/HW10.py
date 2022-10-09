#!/usr/bin/env python3
"""
Georgia Institute of Technology - CS1301
HW10 - Object Oriented Programming
"""

class Room: # entire class provided
    def __init__(self, name): 
        self.name = name

    def __eq__(self, other):
        return self.name == other.name
        
    def __repr__(self): 
        return "Room(name: {})".format(self.name)

class Task:

    def __init__(self,name):
        self.name=name
        self.isCompleted=False

    def __eq__(self,other):
        return self.name==other.name and self.isCompleted==other.isCompleted

    def __repr__(self): # provided
        return "Task(name: {}, isCompleted: {})".format(self.name, self.isCompleted)

class Crewmate:

    def __init__(self,name,color,accessories=()):
        self.name=name
        self.color=color
        self.accessories=accessories
        self.isAlive=True
        self.tasksDone=0
        

    def doTask(self,Task):
        if Task.isCompleted==False:
            Task.isCompleted==True
            self.tasksDone+=1
        else:
            return('Nothing to do here.')

    def vote(self,AmongUs):
        objlist=[]
        for obj in AmongUs.crewmates:
            objlist.append(obj)
        for obj in AmongUs.impostors:
            objlist.append(obj)
        for obj in objlist:
            if self.name[0]==obj.name[0]:
                if obj.isAlive==True:
                    if obj.name!=self.name:
                        return obj
    
    def callMeeting(self,AmongUs):
        playerdict={}
        maxvalue=0
        for obj in AmongUs.impostors:
            playerdict[obj.name]=0
        for obj in AmongUs.crewmates:
            playerdict[obj.name]=0
        for obj in AmongUs.impostors:
            playerdict[obj.vote(AmongUs).name]+=1
        for obj in AmongUs.crewmates:
            playerdict[obj.vote(AmongUs).name]+=1
        for key in playerdict:
            if playerdict[key]>maxvalue:
                maxvalue=playerdict[key]
        for key in playerdict:
            if playerdict[key]==maxvalue:
                name=key
        for obj in AmongUs.impostors:
            if obj.name==name:
                obj.isAlive=False
                return name+ ' was An Impostor.'
        for obj in AmongUs.crewmates:
            if obj.name==name:
                obj.isAlive=False
                return name+' was not An Impostor.'
            
    def __repr__(self): # provided 
        return "Crewmate(name: {}, color: {})".format(self.name, self.color)

    def __eq__(self, other): # provided
        return (self.name, self.color, self.accessories) == (other.name, other.color, other.accessories)

class Impostor:

    def __init__(self,name,color,accessories=()):
        self.name=name
        self.color=color
        self.accessories=accessories
        self.isAlive=True
        self.eliminateCount=0

    def eliminate(self,player):
        if type(player)==Crewmate:
            player.isAlive=False
            self.eliminateCount+=1
        else:
            return "They're on your team -_-"

    def vote(self,AmongUs):
        objlist=[]
        for obj in AmongUs.crewmates:
            objlist.append(obj)
        for obj in AmongUs.impostors:
            objlist.append(obj)
        for obj in objlist:
            if self.name[0]==obj.name[0]:
                if obj.isAlive==True:
                    if obj.name!=self.name:
                        return obj

    def __str__(self):
        return "My name is "+self.name+" and I'm an impostor."
            
    def __repr__(self): # provided
        return "Impostor(name: {}, color: {})".format(self.name, self.color)

    def __eq__(self, other): # provided 
        return (self.name, self.color, self.accessories) == (other.name, other.color, other.accessories)

class AmongUs:

    def __init__(self,maxPlayers):
        self.maxPlayers=maxPlayers
        self.rooms={}
        self.crewmates=[]
        self.impostors=[]

    def registerPlayer(self,player):
        if len(self.crewmates)+len(self.impostors)==self.maxPlayers:
            return "Lobby is full."
        if type(player)==Crewmate:
            if self.crewmates==[]:
                self.crewmates.append(player)
            else:   
                namelist=[]
                for obj in self.crewmates:
                    namelist.append(obj.name)
                for obj in self.impostors:
                    namelist.append(obj.name)
                if player.name not in namelist:
                    self.crewmates.append(player)
                else:
                    return "Player with name: "+player.name+" exists."
        if type(player)==Impostor:
            if self.impostors==[]:
                self.impostors.append(player)
            else:
                namelist=[]
                for obj in self.impostors:
                    namelist.append(obj.name)
                for obj in self.crewmates:
                    namelist.append(obj.name)
                if player.name not in namelist:
                    self.impostors.append(player)
                else:
                    return "Player with name: "+player.name+" exists."
           
    def registerTask(self,Task,Room):
        roomlist=[]
        tasklist=[]
        for key in self.rooms.keys():
            tasklist+=self.rooms[key]
        if Task in tasklist:
            return "This task has already been registered."
        else:
            for key in self.rooms.keys():
                roomlist.append(key)
            if Room.name not in roomlist:
                self.rooms[Room.name]=[Task]
            else: 
                self.rooms[Room.name].append(Task)

    def gameOver(self):
        crewlist=[]
        implist=[]
        for obj in self.crewmates:
            crewlist.append(obj.isAlive)
        for obj in self.impostors:
            implist.append(obj.isAlive)
        if True in crewlist and True in implist:
            return "Game is not over yet!"
        if True not in crewlist and True in implist:
            return "Defeat! All crewmates have been eliminated."
        if True in crewlist and True not in implist:
            return "Victory! All impostors have been eliminated."
                
    def __repr__(self): # provided
        return "AmongUs(maxPlayers: {})".format(self.maxPlayers)

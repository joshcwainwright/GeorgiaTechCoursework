

"""
Georgia Institute of Technology - CS1301
HW03 - Iteration
"""

#########################################

"""
Function Name: movieNight()
Parameters: a caption (str)
Returns: the fixed caption (str)
"""

sentence="Mr. and M4rs. Dursley of nu28mber four, Privet Drive, wer903e proud to say th6at they we6re perfectly norm3al, tha894nk you ve89ry much."
def movieNight(caption):
    sent=""
    for ch in caption:
        str(ch)
        if ch.isdigit():
            continue
        sent += ch
    return sent
        
#########################################

"""
Function Name: iceCream()
Parameters: flavor (str), number of vowels (int)
Returns: a sentence (str)
"""

def iceCream(flavor,nov):
    total=0
    flavor=flavor.lower()
    for ch in flavor:
        if ch in "aeiou":
            total+=1
        continue
    if total > nov:
        sentence="Yes, {} ice cream has more than {} vowels!".format(flavor,nov)
        return(sentence)
    else:
        sentence="No, {} ice cream doesn't have more than {} vowels!".format(flavor,nov)
        return(sentence)
    

#########################################

"""
Function Name: dreamCar()
Parameters: car price (float), bank balance(float), interest rate (float)
Returns: number of years (int)
"""

def dreamCar(p,b,i):
    t=0
    while 1==1:
        A=b*(1+(i/100))**t
        if A>=p:
            return t
        t+=1
        
#########################################

"""
Function Name: battleship()
Parameters: board size (int)
Returns: None (NoneType)
"""

def battleship(size):
    alpha="abcdefghijklmnopqrstuvwxyz"
    for num in range(0,size):
        string=""
        for num1 in range(1,size+1):
            if num1==size:
                string = string + str(alpha[num])+ str(num1)
            else:
                string = string + str(alpha[num])+ str(num1)+ " "
        print(string)

#########################################

"""
Function: tennisMatch()
Parameters: player 1 (str), player 2 (str), match record (str)
Returns: winner (str)
"""

def tennisMatch(p1,p2,record):
    p2point=0
    p1point=0
    p2game=0
    p1game=0
    for ch in record:
        if ch in '1':
            p1point+=1
            continue
        elif ch in '2':
            p2point+=1
            continue
        else:
            if p1point>p2point:
                p1game+=1
                p1point=0
                p2point=0
            elif p1point<p2point:
                p2game+=1
                p1point=0
                p2point=0
            else:
                p1point=0
                p2point=0
                continue
    if p1game>p2game:
        sent="{} won! The score was {}-{}.".format(p1,p1game,p2game)
        return(sent)
    elif p2game>p1game:
        sent="{} won! The score was {}-{}.".format(p2,p2game,p1game)
        return(sent)
    else:
        return("It's a tie!")
        






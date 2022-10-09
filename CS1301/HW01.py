

"""
Georgia Institute of Technology - CS1301
HW01 - Functions and Expressions
"""

#########################################

"""
Function Name: listen()
Parameters: N/A
Returns: None
"""
def listen():
    songnum=int(input("How many songs did you listen to?"))
    podnum=int(input("How many podcasts did you listen to?"))
    totalmin=25*podnum+3*songnum
    hours=int(totalmin//60)
    minutes=int(totalmin%60)
    print("By listening to {} songs and {} podcasts, you have spent {} hours and {} minutes on Spotify.".format(songnum,podnum,hours,minutes))   
   

#########################################

"""
Function Name: dominosTime()
Parameters: N/A
Returns: None
"""
def dominosTime():
    pizza=int(input("How many pizzas do you want?"))
    pasta=int(input("How many orders of pasta do you want?"))
    chickenwings=int(input("How many orders of chicken wings do you want?"))
    dominomoney=12*pizza+pasta*6+chickenwings*8
    print("By ordering {} pizzas, {} orders of pasta, and {} orders of chicken wings, your order total comes to ${}.".format(pizza,pasta,chickenwings,dominomoney))

#########################################

"""
Function Name: tipAndSplit()
Parameters: N/A
Returns: None
"""
def tipAndSplit():
    ordertotal=int(input("What was the order total?"))
    tipperc=int(input("What percentage would you like to tip?"))
    people=int(input("How many people are splitting the order?"))
    tip=round(((float(tipperc))/100)*ordertotal,2)
    split=round((tip+ordertotal)/people,2)
    print("The driver got a tip of ${}. Each person paid ${}.".format(tip,split))

#########################################

"""
Function Name: youtuber()
Parameters: N/A
Returns: None
"""
def youtuber():
    videonum=float(input("How many videos have you made?"))
    payview=float(input("How much do you get paid per view?"))
    view=float(input("How many views do your videos have?"))
    income=round(view*payview*videonum,2)
    print("You have made ${} by making YouTube videos!".format(income))

#########################################

"""
Function Name: bathBomb()
Parameters: N/A
Returns: None
"""
def bathBomb():
    radius=float(input("What is the radius of the bath bomb?"))
    volume=round((4/3)*3.14*(radius**3),2)
    print("The volume of a bath bomb with radius {} is {}.".format(radius,volume))
tipAndSplit()
    



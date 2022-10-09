

"""
Georgia Institute of Technology - CS1301
HW02 - Conditionals
"""

#########################################

"""
Function Name: skillLevel()
Parameters: passRate (int)
Returns: "Beginner" or "Moderate" or "Advanced" (str)
"""
def skillLevel(passRate):
    if passRate <= 25:
        return "Beginner"
    elif passRate > 25 and passRate <= 75:
        return "Moderate"
    else:
        return "Advanced"
    

#########################################

"""
Function Name: bookStore()
Parameters: item (str), walletAmount (float), quantity (int)
Returns: moneyLeftOver (float)
"""
def bookStore(item,walletAmount,quantity):
    if item=="Shirt" and walletAmount>= 15.50*quantity:
        return round(walletAmount-(15.50*quantity),2)
    elif item=="Lanyard" and walletAmount>=4.25*quantity:
        return round(walletAmount-(4.25*quantity),2)
    elif item=="Sweatshirt" and walletAmount>=25*quantity:
        return round(walletAmount-(25*quantity),2)
    elif item=="Mug" and walletAmount>=10.50*quantity:
        return round(walletAmount-(10.50*quantity),2)
    else:
        return "Not enough money!"
           

#########################################

"""
Function Name: dinnerPlans()
Parameters: distance (int), hungerLevel (str)
Returns: transportMode (str)
"""
def dinnerPlans(distance,hungerLevel):
    if hungerLevel == "Not Hungry":
        if distance <= 7:
            return "Walk"
        else:
            return "Uber"
    elif hungerLevel == "Slightly Hungry":
        if distance <= 5:
            return "Walk"
        else:
            return "Uber"
    elif hungerLevel == "Hungry":
        if distance <= 3:
            return "Walk"
        else:
            return "Uber"
    else:
        if distance <= 1:
            return "Walk"
        else:
            return "Uber"

#########################################

"""
Function Name: weekendTrip()
Parameters: distance (float), speed (float), freeTime (float)
Returns: transportMode (str)
"""
def weekendTrip(distance,speed,freeTime):
    travelTime =distance/speed
    if (travelTime/freeTime) <= 0.2:
        if speed >= 2.5 and speed <= 15:
            return "walking"
        elif speed > 15 and speed <= 20:
            return "biking"
        else:
            return "driving"
    else:
        return "Going to this destination would take too much time."
    
    

#########################################

"""
Function Name: textFriends()
Parameters: distance (float), speed (float), freeTime (float), numSnacks (int), numFriends (int)
Returns: textMsg (str)
"""
def textFriends(distance,speed,freeTime,numSnacks,numFriends):
    transport = weekendTrip(distance,speed,freeTime)
    if  transport != "Going to this destination would take too much time.":
        snacksper=numSnacks//numFriends
        snacksleft=numSnacks%numFriends
        return "If each of us gets {} snack(s), there will be {} left. I will be {}, who else is doing the same?".format(snacksper,snacksleft,transport)
    else:
        return "Going to this destination would take too much time."





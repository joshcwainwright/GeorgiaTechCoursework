#!/usr/bin/env python3
"""
Georgia Institute of Technology - CS1301
HW05 - Lists, Tuples, and Modules
"""

"""
Function Name: maskOrders()
Parameters: list organizations (list), list of members (list), mask price (int)
Returns: list of tuples (list)
"""

def maskOrders(orgs,memb,price):
    lst=[]
    for num in range(len(orgs)):
        lst+=[(orgs[num],memb[num]*price)]
    return lst

"""
Function Name: coffeeBreak()
Parameters: list of drinks (list), budget (float)
Returns: name of drink (str)
"""

def coffeeBreak(lst,budg):
    lst1=[]
    for (name,caf,price) in lst:
        if price>budg:
            continue
        if price<=budg:
            lst1+=[(price,name)]
            continue
    lst1.sort()
    if lst1==[]:
       return None
    else:
        return lst1[len(lst1)-1][1]

"""
Function Name: myBirthday()
Parameters: gifts (list)
Returns: total balance and gifts (tuple)
"""

def myBirthday(gifts):
    import convert as c
    total=0
    tup2=()
    for item in gifts:
        if item[0]=='$':
            total+=c.conversion(item)
        else:
            tup2+=(item,)
    total=c.conversion(total)
    tup1=(total[:],)
    return (tup1+tup2)

"""
Function Name: roadTrip()
Parameters: state (str), list of tuples (list)
Returns: list of cities (list)
"""

def roadTrip(state,cities):
    lst=[]
    for (city,ST) in cities:
        if ST==state and city not in lst:
            lst+=[city]
    return(lst)
    
"""
Function Name: safeLocation()
Parameters: list of tuples (list)
Returns: safe locations (list)
"""

def safeLocation(lst):
    lst1=[]
    for (name,cap,tot) in lst:
        if cap<=(tot/2) and tot>0:
            lst1+=[name]
    return lst1
        
"""
Function Name: eventScheduler()
Parameters: list of tuples (list)
Returns: events (list)
"""

def eventScheduler(lst):
    lst1=[]
    import calendar as c
    for (name,mon,day)in lst:
        if str(c.weekday(2020,mon,day)) in "56":
            lst1+=[name]
    return(lst1)

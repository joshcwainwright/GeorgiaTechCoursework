#!/usr/bin/env python3
"""
Georgia Institute of Technology - CS1301
HW09 - Recursion
"""

"""
Function Name: pickyEater()
Parameters: food list (list)
Returns: number of food items that can be eaten (int)
"""

def pickyEater(lst):
    if lst==[]:
        return 0
    else:
        if len(lst[0])%2==0 and lst[0]!='':
            return 1+pickyEater(lst[1:])
        else:
            return 0+pickyEater(lst[1:])

"""
Function Name: inviteFriends()
Parameters: list of invitees (list)
Returns: flattened list of all invitees (list)
"""

def inviteFriends(lst):
    if lst==[]:
        return []
    if type(lst[0])==list:
        return inviteFriends(lst[0])+inviteFriends(lst[1:])
    return [lst[0]]+inviteFriends(lst[1:])

"""
Function Name: friendsgiving()
Parameters: stores (list), budget (float), maxDistance (int)
Returns: price of sauce at each store (dict)
"""

def friendsgiving(lst,bud,dist):
    if lst==[]:
        return{}
    dic=friendsgiving(lst[1:],bud,dist)
    if lst[0][1]<bud:
        if lst[0][2]<dist:
            dic[lst[0][0]]=lst[0][1]
    return dic
    
"""
Function Name: palindrome()
Parameters: string (str), guess (int)
Returns: Whether the string contains a number of palindromes (bool)
"""

def palindrome(string,g):
    if len(string)<3:
        count=0
        if count==g:
            return True
        return False
    count=palindrome(string[1:],g)
    if string[0:3]==string[2::-1]:
        count+=1
    if count==g:
        return True
    return False


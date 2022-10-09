
#!/usr/bin/env python3
"""
Georgia Institute of Technology - CS1301
HW06 - Dictionaries and Try/Except
"""

"""
Function Name: vowelCounter()
Parameters: cities (list)
Returns: number of vowels in each city (dict)
"""
def vowelCounter(lst):
    dct={}
    total=0
    for item in lst:
        for ch in item:
            if ch in "aeiouAEIOU":
                total+=1
        dct[item]=total
        total=0
    return dct

"""
Function Name: maskDetector()
Parameters: message (list)
Returns: tuple with the location of the mask (str) and the number of errors (int)
"""

def maskDetector(lst):
    lst1=''
    typ=0
    for item in lst:
        try:
            lst1+=item
        except TypeError:
            typ+=1
            continue
    return (lst1,typ)

"""
Function Name: beautifulDay()
Parameters: dictionary of cities mapped to list of tuples (dict), ideal weather (str), ideal temperature (int)
Returns: a list of places (list)
"""

def beautifulDay(dct,wth,tmp):
    lst=[]
    for key in dct:
        for tup in dct[key]:
            if tup[0]==wth:
                if tmp in range(tup[1],tup[2]+1):
                    lst+=[key]
                    break
    lst.sort()
    return lst

"""
Function Name: flightFinder()
Parameters: flight prices by month for different cities (dict), city (str)
Returns: the month with the cheapest flight to the customer's desired city (str) or None
"""

def flightFinder(finfo,city):
    for key in finfo:
        if key==city:
            if finfo[key]!={}:
                bp=[]
                for key1 in finfo[key]:
                    bp+=[finfo[key][key1]]
                bp.sort()
                bestp=bp[0]
                for key1 in finfo[key]:
                    if finfo[key][key1]==bestp:
                        return key1
    return('No Flights')    


"""
Function Name: courseRosters()
Parameters: student info as a list of tuples (list)
Returns: a dictionary containing course rosters (dict)
"""

def courseRosters(lst):
    lst1=[]
    lst2=[]
    majdic={}
    edic={}
    for tup in lst:
        for course in tup[2]:
            if course not in lst1:
                lst1+=[course[:]]
    for tup in lst:
        if tup[1] not in lst2:
            lst2+=[tup[1][:]]
    for crs in lst1[:]:
        for maj in lst2[:]:
            lst3=[]
            for tup in lst:
                if tup[1]==maj and crs in tup[2]:
                    lst3+=[tup[0][:]]
            majdic[maj[:]]=lst3[:]
            for key in majdic.copy():
                if majdic[key]==[]:
                    del majdic[key]
        edic[crs[:]]=majdic.copy()
    return edic
        
   
        






#!/usr/bin/env python3
"""
Georgia Institute of Technology - CS1301
HW08 - APIs
"""

"""
Function Name: meetNewPeople()
Parameters: house (str)
Returns: list of people (list)
"""

def meetNewPeople(house):
    import requests
    apiKey='?key=$2a$10$rA2b4OklKCjurCpkzJTq7uxT6YWMz7GjcsrswT77f3YfymIuWjzDu'
    baseUrl='https://www.potterapi.com/v1/'
    r1=requests.get(baseUrl+'houses'+apiKey)
    d1=r1.json()
    houseids=[]
    for item in d1:
        houseids.append((item['name'],item['_id']))
    for name, id_ in houseids:
        if house==name:
            houseid=id_
    r2=requests.get(baseUrl+'houses/'+houseid+apiKey)
    d2=r2.json()
    memberdict=d2[0]['members']
    lst=[]
    for item in memberdict:
        r3=requests.get(baseUrl+'characters/'+item['_id']+apiKey)
        d3=r3.json()
        if d3['deathEater']==False and d3['bloodStatus']=='pure-blood':
            lst.append(d3['name'])
    return lst
          
"""
Function Name: matchingStudents()
Parameters: character name (str)
Returns: list of students (list)
"""

def matchingStudents(name):
    import requests
    apiKey='?key=$2a$10$rA2b4OklKCjurCpkzJTq7uxT6YWMz7GjcsrswT77f3YfymIuWjzDu'
    baseUrl='https://www.potterapi.com/v1/'
    r1=requests.get(baseUrl+'characters/'+apiKey)
    d1=r1.json()
    lst=[]
    for item in d1:
        if item['name']==name:
            house=item['house']
            blood=item['bloodStatus']
    for item in d1:
        if item['name']!=name:
            try:
                if item['house']==house:
                    if item['bloodStatus']==blood:
                        if item['role']=='student':
                            lst.append(item['name'])
            except:
                continue
            
    return lst
                                  
"""
Function Name: similarCharacters()
Parameters: movieID1 (str), movieID2 (str)
Returns: number of people (int)
"""

def similarCharacters(mov1,mov2):
    import requests
    baseUrl='https://swapi.dev/api/'
    str1=baseUrl+'films/'+mov1+'/'
    str2=baseUrl+'films/'+mov2+'/'
    try:
        r1=requests.get(str1)
        r2=requests.get(str2)
        d1=r1.json()
        d2=r2.json()
        character1=d1['characters']
        character2=d2['characters']
        lst1=[]
        lst2=[]
        for item in character1:
            r=requests.get(item)
            d=r.json()
            lst1.append(d['name'])
        for item in character2:
            r=requests.get(item)
            d=r.json()
            lst2.append(d['name'])
        count=0
        for item in lst1:
            if item in lst2:
                count+=1
        return count
    except:
        return 0
  
"""
Function Name: spaceDrifting()
Parameters: passengers(int), max price(int)
Returns: list of valid starships (list)
"""

def spaceDrifting(pas,price):
    import requests
    baseUrl='https://swapi.dev/api/'
    r1=requests.get(baseUrl+'starships')
    d1=r1.json()
    ships=d1['results']
    lst=[]
    for item in ships:
        try:
            if int(item['passengers'])>=pas:
                try:
                    if int(item['cost_in_credits'])<price:
                        lst.append((item['name'],item['manufacturer']))
                except:
                    continue
        except:
            continue
    return lst

"""
Function Name: perfectMatch()
Parameters: list of candidates (list)
Returns: list of potential matches (list)
"""

def perfectMatch(lst1):
    import requests
    baseUrl='https://swapi.dev/api/'
    r1=requests.get(baseUrl+'people')
    d1=r1.json()
    people=d1['results']
    lst2=['Darth Vader','Luke Skywalker']
    lst3=[]
    for item in people:
        if item['name'] in lst1 and item['name'] not in lst2:
            try:
                if int(item['height'])>=180:
                    try:
                        if item['gender']=='male':
                            lst3.append(item['name'])
                    except:
                        continue
            except:
                continue
    return(lst3)

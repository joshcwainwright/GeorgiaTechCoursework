#!/usr/bin/envfindCuisine('restaurants.txt', 'American') python3
"""
Georgia Institute of Technology - CS1301
HW07 - File I/O & CSV
"""

"""
Function Name: findCuisine()
Parameters: filename (str), cuisine (str)
Returns: list of restaurants (list)
"""

def findCuisine(filename,cuis):
    lst=[]
    myfile=open(filename,'r')
    lines=myfile.readlines()
    myfile.close()
    for num in range(0,len(lines)):
        if cuis in lines[num]:
            lst+=[lines[num-1][0:-1]]
    return lst

"""
Function Name: restaurantFilter()
Parameters: filename (str)
Returns: dictionary that maps cuisine type (str) to a 
list of restaurants of the same cuisine type (list)
"""

def restaurantFilter(filename):
    nlst=[]
    ndict={}
    myfile=open(filename,'r')
    lines=myfile.readlines()
    myfile.close()
    namelst=lines[1::4]
    for item in namelst:
        item=item[0:-1]
        if item not in nlst:
            nlst+=[item]
    for item in nlst:
        lst=[]
        for num in range(0,len(lines)):
            if lines[num][0:-1]==item:
                lst+=[lines[num-1][0:-1]]
        ndict[item]=lst[:]
    return ndict

"""
Function Name: createDirectory()
Parameters: filename (str), output filename (str)
Returns: None (NoneType)
"""

def createDirectory(filename,nfilename):
    lstf=[]
    lsts=[]
    numf=1
    nums=1
    txts='Sit-down\n'
    txtf='Fast Food\n'
    myfile=open(filename,'r')
    lines=myfile.readlines()
    myfile.close()
    for num in range(0,len(lines)):
        if 'Fast Food' in lines[num]:
            lstf+=[lines[num-2][0:-1]]+[lines[num-1][0:-1]]
    for num in range(0,len(lines)):
        if 'Sit-down' in lines[num]:
            lsts+=[lines[num-2][0:-1]]+[lines[num-1][0:-1]]
    for num in range(0,len(lstf),2):
        lstf[num]=lstf[num]+' - '+lstf[num+1]
    for num in range(1,len(lstf),2):
        lstf[num]=' '
    for num in range(0,len(lstf)):
        try:
            lstf.remove(' ')
        except:
            break
    lstf.sort()
    for num in range(0,len(lstf)):
        lstf[num]=str(numf)+'. '+lstf[num]
        numf+=1
    for num in range(0,len(lsts),2):
        lsts[num]=lsts[num]+' - '+lsts[num+1]
    for num in range(1,len(lsts),2):
        lsts[num]=' '
    for num in range(0,len(lsts)):
        try:
            lsts.remove(' ')
        except:
            break
    lsts.sort()
    for num in range(0,len(lsts)):
        lsts[num]=str(nums)+'. '+lsts[num]
        nums+=1
    for item in lstf:
        if item!=lstf[len(lstf)-1]:
            txtf+=item+'\n'
        else:
            txtf+=item
    for item in lsts:
        if item!=lsts[len(lsts)-1]:
            txts+=item+'\n'
        else:
            txts+=item
    fintxt='Restaurant Directory\n\n'+txtf+'\n\n'+txts
    newfile=open(nfilename,'w')
    newfile.write(fintxt)
    newfile.close
    
"""
Function Name: infectedPercentage()
Parameters: country list(list), filename(str)
Returns: country and percentage (tuple)
"""

def infectedPercentage(cntrylst,filename):
    inflst=[]
    myfile=open(filename,'r')
    lines=myfile.readlines()
    lst=[]
    dicta={}
    for item in lines:
        item=item.strip()
        lst.append(item.split(','))
    for index,item in enumerate(lst):
        if index>0:
            dicta[item[0]]=(float(item[1]),float(item[2]))
    for key in dicta:
        if key in cntrylst:
            infperc=(float(dicta[key][1]))/(float(dicta[key][0]))*100
            inflst+=[(infperc,key)]
    topval=0
    for (perc,name) in inflst:
        if perc>topval:
            topval=perc
    for (perc,name) in inflst:
        if perc==topval:
            return (name,round(perc,2))
    
"""
Function Name: countryStatus()
Parameters: country list (list), filename(str)
Returns: risk dictionary (dict)
"""

def countryStatus(clist,filename):
    inflst=[]
    myfile=open(filename,'r')
    lines=myfile.readlines()
    lst=[]
    dicta={}
    for item in lines:
        item=item.strip()
        lst.append(item.split(','))
    for index,item in enumerate(lst):
        if index>0:
            dicta[item[0]]=(float(item[1]),float(item[2]))
    lrlst=[]
    mrlst=[]
    hrlst=[]
    rdict={}
    for key in dicta:
        if key in clist:
            infperc=((float(dicta[key][1]))/(float(dicta[key][0])))*100
            inflst+=[(infperc,key)]
    for item in inflst:
        if item[0]<=25:
            lrlst+=[item[1]]
        if item[0]>25 and item[0]<=65:
            mrlst+=[item[1]]
        if item[0]>65:
            hrlst+=[item[1]]
    rdict['Low risk']=lrlst
    rdict['Medium risk']=mrlst
    rdict['High risk']=hrlst
    return rdict

"""
Function Name: compareRisk()
Parameters: country to compare (str), country list (list), filename(str)
Returns: compared countries (list)
"""

def compareRisk(c,clst,filename):
    myfile=open(filename,'r')
    lines=myfile.readlines()
    myfile.close()
    dicta={}
    inflst=[]
    lst=[]
    for item in lines:
        item=item.strip()
        lst.append(item.split(','))
    for index,item in enumerate(lst):
        if index>0:
            dicta[item[0]]=(float(item[1]),float(item[2]))
    for key in dicta:
        if key in clst:
            if float(dicta[key][0])<float(dicta[c][0]) and float(dicta[key][1])>float(dicta[c][1]):
                inflst+=[key]
    if inflst==[]:
        return 'No countries'
    return inflst
        
    


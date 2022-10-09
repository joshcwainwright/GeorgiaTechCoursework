"""
Georgia Institute of Technology - CS1301
HW04 - Strings, Indexing and Lists
"""

#########################################

"""
Function Name: findMax()
Parameters: a caption list of numbers (list), start index (int), stop index (int)
Returns: highest number (int)
"""
def findMax(lst,strtind,stopind):
    start=lst[strtind]
    for obj in lst[strtind:stopind+1]:
        if start>=obj:
            continue
        if start<obj:
            start=obj
            continue
    return(start)
            
#########################################

"""
Function Name: fruitPie()
Parameters: list of fruits (list), minimum quantity (int)
Returns: list of fruits (list)
"""
def fruitPie(frtlst,minq):
    buylist=[]
    for obj in range(0,len(frtlst)):
        num=str(frtlst[obj])
        if num.isdigit()==True:
            num=int(num)
            if num>=minq:
                buylist+=[frtlst[obj-1]]
            continue
        else:
            continue
    return(buylist)

#########################################

"""
Function Name: replaceWord()
Parameters: initial sentence (str), replacement word (str)
Returns: corrected sentence (str)
"""
def replaceWord(intsent,repint):
    intlist=intsent.split()
    for num in range(0,len(intlist)):
        if len(intlist[num])>=5:
            intlist[num]=repint
        else:
            continue
    return(' '.join(intlist))
    
#########################################

"""
Function Name: highestSum()
Parameters: list of strings (strings)
Returns: index of string with the highest sum (int)
"""
def highestSum(numlist):
    sumitem=0
    sumlist=[]
    for item in numlist:
        for ch in item:
            if ch in '1234567890':
                sumitem+=float(ch)
            else:
                continue
        sumlist+=[sumitem]
        sumitem=0
    maxvalue=(max(sumlist))
    return(sumlist.index(maxvalue))
    return(maxvalue)

#########################################

"""
Function: sublist()
Parameters: alist (list), blist (list)
Returns: True or False (`boolean`)
"""
def sublist(alist,blist):
    if blist==[] or alist==[]:
        return(True)
    for numa in range(0,len(alist)):
        if alist[numa]==blist[0]:
            for numb in range(0,len(blist)):
                if alist[numa+numb]==blist[numb]:
                    continue
                else:
                    return(False)
            return(True)        
        else:
            continue
    return(False)



        
    



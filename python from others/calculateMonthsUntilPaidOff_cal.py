import math
def calculate(balance, APR, payment):
    n = 0
    i=APR/36500
    a=math.ceil(balance/payment*(1-(1+i)**30)*100)/100
    print(a)
    if balance<0 or APR<0 or payment<0 or 1+a<=0:
        n=-1
    elif payment<balance:
        n=math.ceil(-1/30*math.log(1+a)/math.log(1+i))+1
    elif payment==balance:
        n=2
    return n

if __name__=='__main__':
    balance=int(input("balance"))
    APR=int(input("apr"))
    payment=int(input("payment"))
    n=calculate(balance,APR,payment)
    print(n)
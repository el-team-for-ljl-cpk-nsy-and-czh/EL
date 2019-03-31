"""write your code in method sove"""
def solve():
    a=[1,2,3,4]
    i=0
    while i<4:
        a[i]=int(input())
        i=i+1
    d=int(input())
    sum=0
    for j in range(1,d+1):
        check=True
        for i in range(0,4):
            mode=j % (a[i])
            if mode==0:
                check=False
                break
        if check:
            sum=sum+1
    print(d-sum)
    return

if __name__=='__main__':
    solve()
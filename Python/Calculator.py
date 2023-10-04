import Funtion

origin = input("연산식을 입력하시오: ")
origin = list(origin)
n1 = []
n2 = []
op = ''
origin = [i.strip() for i in origin if i.strip() != '']
for i in range(len(origin)):
   
    if '0' <= origin[i] and '9' >= origin[i]:
        n1.append(origin[i])
    if origin[i] == '+' or origin[i] == '-' or origin[i] == '*' or origin[i] =='/' or origin[i] == '%':
        op = origin[i]
    if op != '':
        n2.append(origin[i + 1:])
        n2 = [i for k in n2 for i in k]
        break
        
if op == '+':
    result = Funtion.Plus(n1, n2)
elif op == '-':
    result = Funtion.Minus(n1, n2)
elif op == '*':
    result = Funtion.Multiply(n1, n2)
elif op == '/':
    result = Funtion.Division(n1, n2)
elif op == '%':
    result = Funtion.Reminder(n1, n2)
else:
    print("잘못 입력하셨습니다. ")     
    
result = [str(i) for i in result]
result = ''.join(result)
print(result)

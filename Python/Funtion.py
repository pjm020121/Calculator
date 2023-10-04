def Plus(n1, n2):
    
    hap = 0
    n1 = [int(i) for i in n1]
    n2 = [int(i) for i in n2]
    
    if len(n1) > len(n2):
        length = len(n2)
        result_len = len(n1)
    elif len(n2) > len(n1):
        length = len(n1)
        result_len = len(n2)
    elif len(n2) == len(n1):
        length = len(n1)
        result_len = len(n1)
    
    result = [0] * (result_len + 1)
    
    for i in range(1,length + 1):
        hap = (n1[len(n1) - i]) + (n2[len(n2) - i])
        result[len(result) - i] = hap 
        
    if len(n1) > len(n2):
        for i in range(0, (len(n1)) - len(n2)):
            result[len(n1) - len(n2) - i] = n1[((len(n1) - 1) - i) - len(n2)]
        
    elif len(n2) > len(n1):
        for i in range(0, (len(n2)) - len(n1)):
            result[(len(n2)) - len(n1) - i] = n2[((len(n2) - 1) - i) - len(n1)]
        
    for i in range(1, len(result) + 1):
        if result[len(result) - i] > 9:
            result[len(result) - i] -= 10 
            result[len(result) - (i + 1)] += 1
        
    if result[0] == 0:
        result.pop(0)
        
    result = Reverse(result)
    return result

def Minus(n1, n2):
    
    n1 = [int(i) for i in n1]
    n2 = [int(i) for i in n2]
    
    # 더 length가 긴 리스트는 length로 짧은 리스트는 num으로 넣어주는 if/elif문
    if len(n1) > len(n2):
        length = len(n1)
        num = len(n2)
        
    elif len(n2) > len(n1):
        length = len(n2)
        num = len(n1)
        
    elif len(n1) == len(n2):
        length = len(n1)
        num = len(n1)
    
    result = [0] * length
    minus = 0
    
    # 한자리 수 if문 ( O )
    if length <= 1: 
        minus = n1[len(n1) - 1] - n2[len(n2) - 1]
        result[0] = minus
        
    # 같은 자리 수 if문     
    if len(n1) == len(n2) and length > 1: 
    
        # n1 첫자리가 n2 첫자리보다 작을 때 ( O )
        if n1[0] < n2[0]: # 
            
            for i in range(length):
                if n2[len(n2) - (i + 1)] < n1[len(n1) - (i + 1)]:
                    n2[len(n2) - (i + 1)] += 10
                    n2[len(n2) - (i + 2)] -= 1
                
                minus = n2[len(n2) - (i + 1)] - n1[len(n2) - (i + 1)]
                result[len(result) - (i + 1)] = minus
            
            result.append('-')
            re_pop = result.pop()
            result.insert(0, re_pop)
        
        # n1 첫자리가 n2 첫자리와 같을 때 ( 마이너스 구현 O )
        elif n2[0] == n1[0]:    
            
            for i in range(length):
                
                if n1[len(n1) - (i + 1)] < n2[len(n2) - (i + 1)]:
                
                    n1[len(n1) - (i + 1)] += 10
                    
                    n1[len(n1) - (i + 2)] -= 1
                    
                    if n1[0] < n2[0]:
                        
                        n1[len(n1) - (i + 1)] -= 10
                        n1[len(n1) - (i + 2)] += 1
                        
                    minus = n1[len(n1) - (i + 1)] - n2[len(n2) - (i + 1)]
                    result[len(result) - (i + 1)] = minus 
            
                elif n1[len(n1) - (i + 1)] >= n2[len(n2) - (i + 1)]: 
                    
                    minus = n1[len(n1) - (i + 1)] - n2[len(n2) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
           
        # n1의 첫자리가 n2 첫자리와 같을 때 result의 값이 -이면 실행하는 if문 ( O )
        if result[1] < 0:
            
            result = [0 for i in result]
            
            for i in range(length):
                
                if n2[len(n2) - (i + 1)] < n1[len(n1) - (i + 1)]:
                
                    n2[len(n2) - (i + 1)] += 10
                    
                    n2[len(n2) - (i + 2)] -= 1
                    
                    if n2[0] < n1[0]:
                        
                        n2[len(n2) - (i + 1)] -= 10
                        n2[len(n2) - (i + 2)] += 1
            
                    minus = n2[len(n2) - (i + 1)] - n1[len(n1) - (i + 1)]
                    result[len(result) - (i + 1)] = minus 
    
                elif n1[len(n1) - (i + 1)] <= n2[len(n2) - (i + 1)]: 
            
                    minus = n2[len(n2) - (i + 1)] - n1[len(n1) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
            
            result.append('-')
            re_pop = result.pop()
            result.insert(0, re_pop)
            
        
        # n1의 0번 인덱스가 n2의 0번 인덱스보다 클 때의 elif문 ( O )
        elif n1[0] > n2[0]:
            
            for i in range(length):
            
                if n1[len(n1) - (i + 1)] < n2[len(n2) - (i + 1)]:
                
                    n1[len(n1) - (i + 1)] += 10
                    
                    n1[len(n1) - (i + 2)] -= 1
                
                minus = n1[len(n1) - (i + 1)] - n2[len(n2) - (i + 1)]
                result[len(result) - (i + 1)] = minus
        
                
        # result 첫번째 원소가 0일때 제거해주는 while문( O )
        while result[0] == 0:
            result.pop(0)
            if len(result) < 2 or result[0] != 0:
                break             
                    
        # 마이너스 뒤에 숫자가 나오기전에 0이 껴있는 경우 제거해주는 while문( O )
        if result[0] == '-':
            while result[1] < 1:
                result.pop(1)
                if result[1] > 1:
                    break
            
            
    # n1이 n2보다 숫자갯수가 더 많고 n1의 length가 1보다 큰 elif문       
    elif len(n1) > len(n2) and length > 1: 
        
        # n2의 lenght가 1보다 큰 elif문
        if num > 1:
            
            for i in range(num):
                
                if n1[len(n1) - (i + 1)] < n2[len(n2) - (i + 1)]:
                    
                    n1[len(n1) - (i + 1)] += 10
                    n1[len(n1) - (i + 2)] -= 1
                
                minus = n1[len(n1) - (i + 1)] - n2[len(n2) - (i + 1)]
                result[len(result) - (i + 1)] = minus
                
            for i in range(len(n1) - len(n2)):
                result[len(n1) - len(n2) - (i + 1)] = n1[len(n1) - len(n2) - (i + 1)]
        
        
        # n2의 length가 1보다 작거나 같은 elif문 ( O )
        elif num <= 1:
            for i in range(num + 1):
                if n1[len(n1) - (i + 1)] < n2[len(n2) - (i + 1)]:
                    n1[len(n1) - (i + 1)] += 10
                    n1[len(n1) - (i + 2)] -= 1
                
                    minus = n1[len(n1) - (i + 1)] - n2[len(n2) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
               
                    while n1[len(n1) - (i + 2)] < 0 and len(n1) - (len(n1) - (i + 2)) > 1:
                        n1[len(n1) - (i + 2)] += 10
                        n1[len(n1) - (i + 3)] -= 1
                        i += 1
                    
                        if n1[len(n1) - (i + 2)] >= 0 and len(n1) - (len(n1) - (i + 2)) <= 1:
                            break
                
                else :         
                    minus = n1[len(n1) - (i + 1)] - n2[len(n2) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
               
                
            # 맨 앞 수를 넣어주는 if문                  
            if n1[len(n1) - len(n2) - 1] > 0: 
                for i in range((len(n1) - len(n2)) - 1, -1, -1):
                    result[i] = n1[i]
                
        # 0을 빼주는 while문        
        while result[0] == 0  : 
            result.pop(0)
            if len(result) < 2 or result[0] != 0:
                break                
            
    # n2가 n1보다 숫자갯수가 더 많고 n2의 length가 1보다 큰 elif문
    elif len(n2) > len(n1) and length > 1:
        
        # num이 1보다 클 경우
        if num > 1:
            
            for i in range(num + 1):
                
                if n2[len(n2) - (i + 1)] < n1[len(n1) - (i + 1)]:
                    n2[len(n2) - (i + 1)] += 10
                    n2[len(n2) - (i + 2)] -= 1
                    
                    minus = n2[len(n2) - (i + 1)] - n1[len(n1) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
                
                    while n2[len(n2) - (i + 2)] < 0 and len(n2) - (len(n2) - (i + 2)) > 1:
                        n2[len(n2) - (i + 2)] += 10
                        n2[len(n2) - (i + 3)] -= 1
                        i += 1
                    
                        if n2[len(n2) - (i + 2)] >= 0 and len(n2) - (len(n2) - (i + 2)) <= 1:
                            break
                
                else:
                    minus = n2[len(n2) - (i + 1)] - n1[len(n1) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
                
            # 맨 앞 수를 넣어주는 if문                  
            if n2[len(n2) - len(n1) - 1] > 0: 
                for i in range((len(n2) - len(n1)) - 1, -1, -1):
                    result[i] = n2[i]
                
          
        # num이 1보다 작거나 같은 경우
        elif num <= 1:
            
            for i in range(num + 1):
                
                if n2[len(n2) - (i + 1)] < n1[len(n1) - (i + 1)]:
                    n2[len(n2) - (i + 1)] += 10
                    n2[len(n2) - (i + 2)] -= 1
                    
                    minus = n2[len(n2) - (i + 1)] - n1[len(n1) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
                
                    while n2[len(n2) - (i + 2)] < 0 and len(n2) - (len(n2) - (i + 2)) > 1:
                        n2[len(n2) - (i + 2)] += 10
                        n2[len(n2) - (i + 3)] -= 1
                        i += 1
                    
                        if n2[len(n2) - (i + 2)] >= 0 and len(n2) - (len(n2) - (i + 2)) <= 1:
                            break
                
                else:
                    minus = n2[len(n2) - (i + 1)] - n1[len(n1) - (i + 1)]
                    result[len(result) - (i + 1)] = minus
                
                
                
            
            # 맨 앞 수를 넣어주는 if문                  
            if n2[len(n2) - len(n1) - 1] > 0: 
                for i in range((len(n2) - len(n1)) - 1, -1, -1):
                    result[i] = n2[i]
        
        # 0을 빼주는 while문        
        while result[0] == 0  : 
            result.pop(0)
            if len(result) < 2 or result[0] != 0:
                break             
        
        result.append('-')
        re_pop = result.pop()
        result.insert(0, re_pop)
        

    return result

def Multiply(n1, n2):
    
    n1 = [int(i) for i in n1]
    n2 = [int(i) for i in n2]
    result = [0] * (len(n1) + len(n2))

    # 크기가 더 큰 리스트의 크기를 length에 더 작은 리스트는 size에 크기할당
    if len(n1) > len(n2):
        length = len(n1)
        size = len(n2)
    elif len(n2) > len(n1):
        length = len(n2)
        size = len(n1)
    elif len(n1) == len(n2):
        length = len(n1)
        size = len(n2)
      
    count = (len(result) - 1)   
    mul = 1
    
    for i in range(len(n1)):
        
        n1[len(n1) - (i + 1)] *= mul
        
        mul *= 10
        
    mul = 1
    
    for i in range(len(n2)):
        
        n2[len(n2) - (i + 1)] *= mul
        
        mul *= 10
    
    mul = 0
    
    for i in range(size):
        
        for k in range(length):
           
            if len(n1) >= len(n2):
           
                mul += n1[len(n1) - (k + 1)] * n2[len(n2) - (i + 1)]
                
            
            elif len(n2) > len(n1):
                
                mul += n2[len(n2) - (k + 1)] * n1[len(n1) - (i + 1)]
            
                
        result[count] += mul
        
    # result 첫번째 원소가 0일때 제거해주는 while문( O )
    while result[0] == 0:
        result.pop(0)
        if len(result) < 2 or result[0] != 0:
            break
    
    
    return result

def Division(n1, n2):
    result = int(n1) / int(n2)
    return result
def Reminder(n1, n2):
    result = int(n1) % int(n2)
    return result
def Reverse(result):
    temp = [0] * (len(result))
    num = len(result) - 1
    for i in range(len(result)):
        temp[i] = result[num - i]
        
    return result

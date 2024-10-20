import sys

if __name__ == '__main__':
    N = int(input())
    queue = list()
    for i in range(N):
        code = sys.stdin.readline()
        if (code[:4] == 'push'):
            num = int(code[5:])
            queue.append(num)
        elif (code[:3] == "pop"):
            if (len(queue) == 0):
                print(-1)
            else:
                a = queue[0]
                queue.pop(0)
                print(a)
        elif (code[:4] == "size"):
            print(len(queue))
        elif (code[:5] == "empty"):
            if (len(queue) == 0):
                print(1)
            else:
                print(0)
        elif (code[:5] == "front"):
            if (len(queue) == 0):
                print(-1)
            else:
                print(queue[0])
        elif (code[:4] == "back"):
            if (len(queue) == 0):
                print(-1)
            else:
                print(queue[-1])
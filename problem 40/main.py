intString = ''
for i in range(1000001):
    intString += str(i)

print(
int(intString[1])
* int(intString[10])
* int(intString[100])
* int(intString[1000])
* int(intString[10000])
* int(intString[100000])
* int(intString[1000000]))
import math
def is_prime(num):
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True

print(sum([num for num in range(2, 2000000) if is_prime(num)]))
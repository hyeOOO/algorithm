def solution(wallet, bill):
    answer = 0
    while max(wallet) < max(bill) or min(wallet) < min(bill):
        bill = [max(bill) // 2, min(bill)]
        answer += 1
    return answer
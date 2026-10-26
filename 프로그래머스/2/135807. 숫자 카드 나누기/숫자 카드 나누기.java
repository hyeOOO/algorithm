
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        int answer = 0;

        if (canDivideNone(gcdA, arrayB)) {
            answer = Math.max(answer, gcdA);
        }

        if (canDivideNone(gcdB, arrayA)) {
            answer = Math.max(answer, gcdB);
        }

        return answer;
    }

    // 유클리드 호제법
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // 배열의 어떤 원소도 나누지 않는지 확인
    private boolean canDivideNone(int divisor, int[] arr) {
        if (divisor == 1) return false;

        for (int num : arr) {
            if (num % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}

public class hw5 {
    public void summation(int x, int result, int[] mem) {
        int answer = 0;
        while (x > 0) {
            answer += x;
            x--;
        }
        mem[result] = answer;
    }

    public int[] maxArray(int[] A, int[] B) {
        int i = 0;
        int[] C = new int[A.length];
        while (i < A.length) {
            if (A[i] > B[i]) {
                C[i] = A[i];
            } else {
                C[i] = B[i];
            }
            i++;
        }
        return C;
    }

    public void binary(String binary, int result, int[] mem) {
        int length = binary.length();
        int base = 1;
        int value = 0; 
        while (length > 0) {
            int y = binary.charAt(length - 1) - 48;
            if (y == 1) {
                value += base;
            }
            base += base;
            length--;
        }
        mem[result] = value;
    }

    public void fourCharacter(String given, int answer, int[] mem) {
        int count = 0;
        int chars = 0;
        int i = 0;
        while (given.charAt(i) != '\0') {
            if (given.charAt(i) != ' ') {
                chars++;
            } else {
                if (chars == 4) {
                    count ++;
                }
                chars = 0;
            }
            i++;
        }
        mem[answer] = count;
    }

}

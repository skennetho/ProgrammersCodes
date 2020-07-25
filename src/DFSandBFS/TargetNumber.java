package DFSandBFS;

/**
 * 문제 설명
 * n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한사항
 * 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
 * 각 숫자는 1 이상 50 이하인 자연수입니다.
 * 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 */
//백트래킹 사용
public class TargetNumber {
    public static void main(String args[]) {
        int[] numbers = {1, 1, 1, 1, 5};
        int target = 3;
        System.out.println("answer: " + solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        plusMinus = new boolean[numbers.length];
        run(numbers, target, 0, 0);
        return answer = count;
    }

    static int count = 0;
    static boolean[] plusMinus;

    private static void run(int[] num, int tar, int r, int ans) {
//        if (ans > tar) {
//            System.out.println(" =" + ans + "!!! \n");
//            return;
//        }
        if (r < num.length) {
            plusMinus[r] = true;
            run(num, tar, r + 1, 0);
            plusMinus[r] = false;
            run(num, tar, r + 1, 0);
        } else if (r >= num.length) {

            for (int i = 0; i < num.length; i++) {
                if (plusMinus[i]) {
                    System.out.print("+"+num[i]);
                    ans += num[i];
                } else {
                    System.out.print("-"+num[i]);
                    ans -= num[i];
                }
            }
            System.out.println("=" + ans + "\n");
            if (ans == tar) {
                count++;
                System.out.println("COUNT:" + count);
                return;
            }
        }
    }
}

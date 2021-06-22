package KakaoInternship2020;

import java.util.LinkedList;

/**
 * [문제설명]
 * 이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서 숫자만을 입력하려고 합니다.
 * 맨 처음 왼손 엄지손가락은 * 키패드에 오른손 엄지손가락은 # 키패드 위치에서 시작하며, 엄지손가락을 사용하는 규칙은 다음과 같습니다.
 * <p>
 * 엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
 * 왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다.
 * 오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다.
 * 가운데 열의 4개의 숫자 2, 5, 8, 0을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.
 * 4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.
 * 순서대로 누를 번호가 담긴 배열 numbers, 왼손잡이인지 오른손잡이인 지를 나타내는 문자열 hand가 매개변수로 주어질 때, 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * [제한사항]
 * numbers 배열의 크기는 1 이상 1,000 이하입니다.
 * numbers 배열 원소의 값은 0 이상 9 이하인 정수입니다.
 * hand는 "left" 또는 "right" 입니다.
 * "left"는 왼손잡이, "right"는 오른손잡이를 의미합니다.
 * 왼손 엄지손가락을 사용한 경우는 L, 오른손 엄지손가락을 사용한 경우는 R을 순서대로 이어붙여 문자열 형태로 return 해주세요.
 * <p>
 * [입출력 예]
 * numbers	hand	result
 * [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]	"right"	"LRLLLRLLRRL"
 * [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]	"left"	"LRLLRRLLLRR"
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]	"right"	"LLRLLRLLRL"
 */

/*
::첫번째 시도::
손가락은 4방향이니깐 각위치에서 움직일수 있는 수는 연산으로 가능한가?
예: n의 좌우 == n-1, n+1; n의 상하 n-3, n+3
구현해야하는 함수: 오른손이 r위치, 왼손이 l위치일때 n으로 가기위해 누가더 빠른가 비교;
                <- 여기서 둘다 가까우면 우선순위 손가락으로 먼저 이동.

구조:
n이 눌렀던 숫자 n+1이 눌러야 하는 숫자라면
오른손과왼손중 누가 적합한지 판단한다음 L과R중에 하나를 기록
이걸 반복
* */

public class Keypad {
    int[] numbers;
    String hand;
    String expect;

    public Keypad(int[] numbers, String hand, String expect) {
        this.numbers = numbers;
        this.hand = hand;
        this.expect = expect;

    }

    int[][] keypad = new int[][]{
            {3, 1},  //0
            {0, 0},  //1
            {0, 1},  //2
            {0, 2},  //3
            {1, 0},  //4
            {1, 1},  //5
            {1, 2},  //6
            {2, 0},  //7
            {2, 1},  //8
            {2, 2},  //9
    };

    public String solution(int[] numbers, String hand) {
        String answer = "";

        char majorHand = hand.equals("left") ? 'L' : 'R';
        int[] left = new int[]{3, 0};
        int[] right = new int[]{3, 2};

        for (int num : numbers) {
            if (keypad[num][1] == 2) {          //3,6,9
                right = keypad[num];
                answer += "R";
            } else if (keypad[num][1] == 0) {   //1,4,7
                left = keypad[num];
                answer += "L";
            } else {
                int leftDiff = Math.abs(keypad[num][0] - left[0]) + Math.abs(keypad[num][1] - left[1]);
                int rightDiff = Math.abs(keypad[num][0] - right[0]) + Math.abs(keypad[num][1] - right[1]);
                if (leftDiff - rightDiff > 0) { //
                    right = keypad[num];
                    answer += "R";
                } else if (leftDiff - rightDiff < 0) {
                    left = keypad[num];
                    answer += "L";
                } else {
                    if (majorHand == 'L') {
                        left = keypad[num];
                    } else {
                        right = keypad[num];
                    }
                    answer += majorHand;
                }
            }
        }
        return answer;
    }

    public void test() {
        System.out.println("result: " + this.solution(numbers, hand) + ", expected: " + expect + ", passed=" +
                this.solution(numbers, hand).equals(expect));
    }
}

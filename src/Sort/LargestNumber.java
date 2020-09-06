package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 문제 설명
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 * <p>
 * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
 * <p>
 * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어
 * return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한 사항
 * numbers의 길이는 1 이상 100,000 이하입니다.
 * numbers의 원소는 0 이상 1,000 이하입니다.
 * 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
 * 입출력 예
 * numbers	return
 * [6, 10, 2]	6210
 * [3, 30, 34, 5, 9]	9534330
 */
public class LargestNumber {
    public static void main(String[] args) {
//        int[] p ={6,10,2};
        int[] p = {10, 101};
//        int[] p = {90, 91,92,93,94,95,96,97,98,99};

        Solution sl = new Solution();
        String str = sl.solution2(p);
        System.out.println("ans: " + str);
    }

    static class Solution {

        ArrayList<int[]> list = new ArrayList<>();

        public String solution(int[] prices) {
            String answer = new String();
            String strPrices[] = new String[prices.length];

            for (int i = 0; i < prices.length; i++) {
                strPrices[i] = String.valueOf(prices[i]);
            }

            Arrays.sort(strPrices, new Comparator<String>() {
                public int compare(String st1, String st2) {
                    return (st2 + st1).compareTo(st1 + st2);
                }
            });

            if (strPrices[0].startsWith("0")) {
                answer += "0";
            } else {
                for (String str : strPrices) {
                    answer += str;
                }
            }
            return answer;
        }

        public String solution2(int[] prices) {
            String answer = "";

            for (int i = 0; i < prices.length; i++) {                //int into int[] of digit
                list.add(intToArray(prices[i]));
            }


            int[] key;
            int j = 1;
            for (int i = 1; i < prices.length; i++) {

                j = i - 1;
                while (j >= 0) {
                    if (!isHigher(list.get(i), list.get(j)))
                        break;
                    j--;
                }
                key = list.get(i);
                list.remove(i);
                list.add(j + 1,key);


                System.out.print("for: ");
                for (int[] arr : list) {
                    System.out.print(Arrays.toString(arr));
                }
                System.out.println();
            }
            System.out.print("sorted : ");
            for (int[] arr : list) {
                System.out.print(Arrays.toString(arr));
            }
            System.out.println();

            if(list.get(0)[0] ==0){
                return "0";
            }
            for(int[] arr :list){
                for(int n  :arr){
                    answer+=n;
                }
            }


            return answer;
        }

        public int[] intToArray(int n) {
            int j = 0;
            int  len = (int) Math.log10((double) n) + 1;
            if(n==0)len =1;
            int[] arr = new int[len];
            while (n != 0) {
                arr[len - j - 1] = n % 10;
                n = n / 10;
                j++;
            }
            return arr;
        }

        //get the higher priority between num1 and num2. if num1 is higher TRUE
        public boolean isHigher(int[] num1, int[] num2) {
            int[] shortNum = (num1.length < num2.length) ? num1 : num2;
            int[] longNum = (num1.length > num2.length) ? num1 : num2;

            //이렇게 하면 엄청 틀림 그리고 어디가 원인인지 알기가 어려움(너무많은 조건때문에
            //str형식으로 num1+num2 와 num2+num1중 무엇이 더큰지를 통해 비교하게 해야함
            //아래는 수정전
            for (int i = 0; i < shortNum.length; i++) {
                if (num1[i] > num2[i]) {
                    return true;
                } else if (num1[i] < num2[i]) {
                    return false;
                } else {
                    if (i == shortNum.length - 1 && num1.length != num2.length) {
                        if (shortNum[i] < longNum[i + 1]) {
                            return true;
                        }
                    } else
                        continue;
                }
            }
            return false;
        }
    }
}

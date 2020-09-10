package BruteForce;

import java.util.Arrays;
import java.util.TreeSet;


/**
 * 문제 설명
 * 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
 * <p>
 * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return
 * 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * numbers는 길이 1 이상 7 이하인 문자열입니다.
 * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
 * 013은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 * 입출력 예
 * numbers	return
 * 17	3
 * 011	2
 *
 * 일지:
 * 1. 처음엔 모든 소수를 구한뒤 해결하려 했으나 소수를 구하는과정이 아무리 줄여도 오래걸린다는걸 깨달음
 * 그래서 2번 방법을 시도
 * 2. isPrimeNum을 사용해 소수판별후 set에 집어 넣어 numbers로 이루어지는 조합에 대해 소수판별을 시도 그러나 실패
 * 조합을 만들때 visited으로 시도했으나 이문제는 조합이아니라 수열임을 깨달음.
 * 3. 조합이 아닌 순열을 구하는것으로 solve재귀함수를 수정.
 */
public class PrimNumber {
    public static void main(String[] args) {
        Solution sl = new Solution();
        int ans = sl.solution("17");
        System.out.println("ans:" + ans);


//        int i =64;
//        while(i++<74){
//            str +=(char)i;
//            System.out.println(str);
//        }

//        sl.getAllPrimeNum(500000);
//        System.out.println("start");
    }

    static class Solution {

        boolean visited[];
        private TreeSet<String> set = new TreeSet<>();  //만들수 있는 모든조합
        private char[] chars;                           //numbers를 char[]로 바꿈


        public int solution(String numbers) {
            int answer = 0;
            chars = new char[numbers.length()];
            visited = new boolean[numbers.length()];

            for (int i = 0; i < numbers.length(); i++)
                chars[i] = numbers.charAt(i);

            for (int i = 1; i <= numbers.length(); i++) {
//                System.out.println("npr = " + i);
                solve(i, new StringBuilder());
            }

            answer = set.size();
            return answer;
        }

        public void solve(int r, StringBuilder sb){
            //숫자를 다 골랐다면
            if(r == 0){
                //첫글자가 0인 경우는 제외
                if(sb.charAt(0) == '0') {
//                    System.out.println("    "+sb+"starts with 0");
                    return;
                }

                //저장안된 수이고 소수이면 add
                String str = sb.toString();
//                System.out.println("    str = "+str);
                if(!set.contains(str) &&isPrimeNum(str)) {
                    set.add(str);
//                    System.out.println("        ^^^ its Prime ^^^");
                }

            }else{
                for(int i = 0 ; i<chars.length;i++) {
//                    System.out.println("    loop : r, i = "+r+", "+i+"      "+Arrays.toString(visited));
                    //이미 고른놈이면 패스
                    if(visited[i]) continue;

                    //하나를 고르고 다음 수 고르기
                    visited[i]= true;
                    sb.append(chars[i]);
                    solve(r-1,sb);

                    //다고르고 나면 sb에서 해제
                    visited[i]= false;
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }

        public boolean isPrimeNum(String str) {
            int num = Integer.parseInt(str);
            if (num == 0 || num == 1)
                return false;
            int sqrt = (int) Math.sqrt((double) num) + 1;

            if (num == 2)
                return true;

            if (num % 2 == 0 || num == 1)
                return false;

            for (int i = 3; i < sqrt; i += 2) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

//        boolean[] primeNum = new boolean[10000000]; //

//        public void getAllPrimeNum(int num) {
//            Arrays.fill(primeNum, true);
//            System.out.println("start");
//            primeNum[0] = false;
//            primeNum[1] = false;
//            int n = (int) Math.sqrt((double) num) + 1;
//            for (int i = 2; i < n; i++) {
//                for (int j = 2; j < i; j += 2) {
//                    if (i % j == 0) {
//                        primeNum[i] = false;
//                        break;
//                    }
//                }
//            }
//            System.out.println("start");
//            int x;
//            for (int i = 2; i < n; i++) {
//                System.out.println("i: " + i + "num:" + num + "======================");
//                if (primeNum[i]) {
//                    for (int j = i + i; j < num; j += i) {
//                        System.out.println("    j :" + j);
//                        primeNum[j] = false;
//                    }
//                }
//            }
//            System.out.println("start");
//            for (int i = 1; i < num; i++) {
//                if (primeNum[i] == true) {
//                    System.out.println(i);
//                }
//            }
//        }

//        public void solve(int r, int n, int tar) {
//            if (r == 0) {
//                String str = String.valueOf(0);
//                int size = 0;
//                System.out.println(Arrays.toString(visited));
//                for (int i = 0; i < tar; i++) {
//                    if (visited[i]) {
//                        str += chars[i];
//                        size++;
//                        System.out.println("aaa");
//                    }
//                    if (size == tar)
//                        System.out.println("bbb");
//                    break;
//                }
//
//
//                if (isPrimeNum(str) && !set.contains(str)) {
//                    set.add(str);
//                    System.out.println("solve: " + str);
//                } else
//                    return;
//            } else {
//                for (int i = n; i < visited.length; i++) {
//                    visited[i] = true;
//                    solve(r - 1, n + 1, tar);
//                    visited[i] = false;
//                }
//            }
//        }
    }
}

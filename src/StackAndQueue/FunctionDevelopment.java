package StackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 문제 설명
 * 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
 *
 * 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은
 * 앞에 있는 기능이 배포될 때 함께 배포됩니다.
 *
 * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가
 * 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
 *
 * 제한 사항
 * 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
 * 작업 진도는 100 미만의 자연수입니다.
 * 작업 속도는 100 이하의 자연수입니다.
 * 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가
 * 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
 * 입출력 예
 * progresses	speeds	return
 * [93, 30, 55]	[1, 30, 5]	[2, 1]
 * [95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
 * */
public class FunctionDevelopment {
    public static void main(String[] args){
        int[] arr = {93,30,55};
        int[] arr2 = {1,30,5};

        arr = new Solution().solution(arr,arr2);
        System.out.println(Arrays.toString(arr));

        int[] arr3 = {95,90,99,99,80,99};
        int[] arr4 = {1,1,1,1,1,1};

        arr = new Solution().solution(arr3,arr4);
        System.out.println(Arrays.toString(arr));
    }
    static class Solution{
        public int[] solution(int[] progresses , int[] speeds){
            int n;

            //걸리는 날짜 계산 (100-현재작업략)/속도를 통해 구한다.
            for(int i =0; i<progresses.length ; i++){
                n=100 - progresses[i];
                progresses[i] = n/speeds[i];
                if(n%speeds[i] !=0){
                    progresses[i]++;
                }
            }
            System.out.println(Arrays.toString(progresses));

            //
            ArrayList<Integer> answer = new ArrayList<>();
            n=1;
            int x=0;
            for(int i =1 ; i<progresses.length;i++){
                System.out.print("progress["+i+"] = ");
                if(progresses[x]<progresses[i]){
                    answer.add(n);
                    n=1;
                    x=i;

                    System.out.println(" if = "+"n:"+n+", x:"+x);
                }else{
                    n++;

                    System.out.println("else= "+"n:"+n+", x:"+x);
                }
            }
            if(progresses[x]<101){
                System.out.println(" if = "+"n:"+n+", x:"+x);
                answer.add(n);

                x=progresses.length-1;
            }else
            if(x==progresses.length-1) {
                answer.add(1);
            }

            int[] ans =new int[answer.size()];
            for(int i =0 ; i<ans.length;i++){
                ans[i]= answer.get(i);
            }
            return ans;
        }
    }
    /*
    * n=0;
            int x =0;
            for(int i =0;i<progresses.length; i++){
                if(progresses[x]<progresses[i]){
                    answer.add(n);
                    n=0;
                    x= i;
                }else{
                    n++;
                }
            }
            * */
}


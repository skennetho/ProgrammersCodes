package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;
/**
 * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
 *
 * 제한사항
 * prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
 * prices의 길이는 2 이상 100,000 이하입니다.
 *
 * 입출력 예
 * prices	return
 * [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
* */
public class StockPrice {
    public static void main(String[] args){
        int[] arr = {1,2,3,2,3};
        System.out.println(Arrays.toString(arr));

        Solution sol = new Solution();
        arr = new Solution().solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution{

        public int[] solution(int[] prices){
            int[] answer = new int[prices.length];
            Arrays.fill(answer,0);

            //answer의 마지막칸은 항상 0
            //병렬적으로
            for(int i=0 ; i<prices.length-1; i++){
                System.out.println("i == "+i);
                for(int j =i; j<prices.length-1; j++){
                    if(prices[i]<= prices[j]){
                        System.out.println("[i],[j] : "+prices[i]+","+prices[j]);
                        answer[i]++;
                    }else{
                        break;
                    }
                }
            }
            return answer;
        }

    }
}

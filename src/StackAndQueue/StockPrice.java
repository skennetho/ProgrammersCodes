package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

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

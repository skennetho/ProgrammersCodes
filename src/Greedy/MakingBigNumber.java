package Greedy;
//문제설명
/*
어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.

예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.

문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.

제한 조건
number는 1자리 이상, 1,000,000자리 이하인 숫자입니다.
k는 1 이상 number의 자릿수 미만인 자연수입니다.
입출력 예
number	k	return
1924	2	94
1231234	3	3234
* */

/*문제 풀이
풀이1. sort한후 큰수를 차례대로 나열하기
-> 가장큰 자리에 가장큰수가 와야하기에 이렇게 생각했지만, 단순히 작은수를 먼저 제거하는 식은 수의자리를 고려하기 힘들다.

풀이2. 결과 문자열의 길이를 알아내고 0번째부터 들어갈수 있는수중에 큰수를 고르는 과정으로하자.
-> 예시 1234567, k=3 일경우 결과문자열은 length(7) - k(3) = 4이다. == len
    len 의 n의 자리수를 하나씩 정하면서 수를 제거해 나간다.
    여기서는 4번 자리수부터 정해야한다. 여기에 가능한수는 입력값(1234567)중 1~4까지만 가능하다. 이는 1~3번 수에 들어갈 수는 남겨둔것이다.
    // 오류:범위내에서 가장큰수를 찾는다. ->
    위의 방식을 반복하여 k개를 제거하면 답을 뱉는다.
-> 입력값은 n자리인데 k도 n인경우도 있다면 if문으로 바로 0을 뱉는다.
* */

import java.util.Arrays;

public class MakingBigNumber {
    public String solution2(String number, int k){
        int bigLoc = 0;
        int answerLength = number.length() - k;
        System.out.println("SOLUTION START WITH "+number+", "+k+"\t\t outLength="+answerLength);

        int loopUntil =k+1;
        // 012345  k=2, outlen = size-2 = 4             0~2,3,4,5
        // 0123456789 k=4  outlen = 10:size -2 = 6      0~4,5,6,7,8,9

        StringBuilder sb = new StringBuilder();

         while(loopUntil<= number.length()){
             System.out.println("LOOP K: "+k +"\t\tstart From "+bigLoc +" until "+loopUntil+"\t\t"+
                     number);
             char big = number.charAt(bigLoc);

             for(int i =bigLoc; i<loopUntil;i++){
                 if(number.charAt(i)>big) {
                     bigLoc = i;
                     big = number.charAt(i);
                 }
             }
             System.out.println("big number is "+number.charAt(bigLoc));

             sb.append(number.charAt(bigLoc));
             bigLoc++;
             loopUntil++;
         }

        return sb.toString();
    }

    public String solution(String number, int k){
        String answer = "";
        char[] chars = number.toCharArray();
        int[] loc = new int [number.length()];
        for(int i =0 ; i < loc.length;i++)loc[i]= i;

        int idx = 0, temp =0, key;
        int j;
        for(int i =1 ; i <chars.length; i++){
            key = chars[i];
            temp = loc[i];
            j = i-1;
            while(j>=0 && chars[j]> key){
                chars[j+1] = chars[j];
                j--;
            }
            chars[j+1] = (char)key;
        }


        System.out.println(Arrays.toString(chars));
        return answer;
    }
}

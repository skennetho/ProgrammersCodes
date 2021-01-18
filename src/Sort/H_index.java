package Sort;

import java.util.Arrays;
//문제설명
/*
H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.

어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.

어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

제한사항
과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
입출력 예
citations	return
[3, 0, 6, 1, 5]	3
입출력 예 설명
이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.
* */

/*문제풀이
배열을 오름차순으로 정렬
최댓값이라고 생각한 숫자 num가 조건에 맞는지 따지려면
-> num보다 작은수는  num개 이하, num보다 큰수들은  num개 이상
-> 정렬이 되었으므로 큰수의 개수나 작은수의 개수 둘중하나만 만족하면 나머지도 만족이된다.
숫자를 정렬된 배열의 첫 수부터 하나씩 오르며 탐색
탐색 실패한지점이 곧 최댓값
**/

public class H_index {
    public int solution(int[] citations){
        //오름차순 정렬
        Arrays.sort(citations);
        int answer = citations[0];
        int len = citations.length;

        for(int i = len ; i>0 ; i--){
            int num = i;
            if(num<=citations[len -num]  ) {
                answer = num;
                break;
            }
        }
        return answer;
    }
    public int solution2(int[] citations){

        int len = citations.length;
        Arrays.sort(citations);
        int answer = citations[0];

//        for(int i = len-1 ; i>=0 ; i--){
//            int num = citations[i];
//            if( num <= len - i  && num> i ) {
//                answer = num;
//                break;
//            }
//        }

        int log = 0;
        for(int i = answer ; i< citations[len-1]; i++){
            int num=0;
            log =i;
            for(int x = len-1 ; x >= 0 ; x--){
                if(citations[x]>= i){
                    num = citations[x];
                }else
                    break;
            }
//            System.out.println("num, idx: "+num+", "+i);
            if( num <= len - i  && num>= i ) {
                answer = num;
            }else{
                if(num == 0 ) continue;
                break;
            }
        }
//        System.out.println("log:"+log);

        return answer;
    }
}
/*
0 1 2 3 4 5
1 3 4 5 6 7
i = 5, len = 6
len - i =1
i = 5
*/
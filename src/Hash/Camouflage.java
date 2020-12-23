package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
 * <p>
 * 예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.
 * <p>
 * 종류	이름
 * 얼굴	동그란 안경, 검정 선글라스
 * 상의	파란색 티셔츠
 * 하의	청바지
 * 겉옷	긴 코트
 * 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한사항
 * clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
 * 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
 * 같은 이름을 가진 의상은 존재하지 않습니다.
 * clothes의 모든 원소는 문자열로 이루어져 있습니다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
 * 스파이는 하루에 최소 한 개의 의상은 입습니다.
 */
public class Camouflage {
    public static void main(String[] args) {
        /*
        가: 1 2 3
        나: a b
        다: ㄱ ㄴ ㄷ

        1개: 8
        2개: 가,나= 6      가,다= 9      나,다 =6
        3개: 가,나,다 = 18
        */


//        String[][] clothes = {{"1", "pant"}, {"2", "pant"},{"1", "head"},{"2", "head"},{"3", "head"},{"3", "eye"}};//5
//        int answer = new Solution().solution(clothes);
//        System.out.println(answer);

        String[][] test = new String[30][2];
        int kind = 0;
        for(int i =0 ; i<30; i++){
            if(i%3==0)kind++;

            test[i][0] = kind+"";
            test[i][1] = i+"";
        }
        for(String[] arr : test) {
            System.out.println(Arrays.toString(arr));
        }
        int answer = new Solution().solution(test);
        System.out.println(answer);


//        count=0;
//        int[] arr = {1,2,3,4,5,6,7,8,9,10};
//        counter(arr,0,2);
//        System.out.println("start");
//        for(int i =0 ;i<arr.length; i++){
//            System.out.println("count("+i+")");
//            counter(arr,0,i+1);
//        }
//        System.out.println("final count: "+count);

    }

//    static ArrayList<Integer> list = new ArrayList<>();
//    public static int count =0;
//    public static void counter(int[] arr,  int r, int n){
//        if (n==0) {
//            int x =1;
//            for(int i : list){
//                x*= i;
//            }
//            count+=x;
//            System.out.println("count : "+count+"       ||  "+list.toString());
//            return;
//        }else{
//            for (int i = r; i < arr.length; i++) {
//                list.add(arr[i]);
//                counter(arr, i + 1, n - 1);
//                list.remove(list.indexOf(arr[i]));
//
//                if(!(arr.length -i>n)){     //무의미한 재귀를 막기위한 부분 예(3개남았는데3개뽑으려할때는 한번돌면 끝나기때문)
//                    break;
//                }
//            }
//        }
//    }

    static class Solution {
        int count;
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] visited;

        //가장빠른 풀이 (식을 활용함)
        public int solution(String[][] clothes) {
            int answer = 1; //곱셈을 위해 1로 선언
            HashMap<String,Integer> map = new HashMap<>();
            for(String[] arr : clothes) {
                map.put(arr[1], map.getOrDefault(arr[1],0)+1);
            }
            Set<String> set = map.keySet();

            for(String kind: set){
                answer*= map.get(kind)+1;
            }
            return answer -1;
        }

        //재귀를 활용한풀이
        //그러나 30가지 옷이 생기면 시간이 오버 된다.
        public int solution2(String[][] clothes) {
            int answer = 0;
            visited = new boolean[clothes.length];
            Arrays.fill(visited,false);
            HashMap<String, Integer> cloth = new HashMap<>();   //종류이름, 갯수

            //해시맵으로 변환 (무슨 종류에 몇개가 존재하는지를 추출)
            for(String[] c : clothes){
                if(cloth.containsKey(c[1])) {
                    cloth.put(c[1], cloth.get(c[1])+1);
                }else{
                    cloth.put(c[1],1);
                }
            }

            count =0;
            int[] size = new int[cloth.keySet().size()];
            for(String key: cloth.keySet()){
                size[count++]=cloth.get(key);
            }
            System.out.println("size : "+ Arrays.toString(size));

            count =0;
            for(int i =0 ; i<size.length ;i++){
                System.out.println("counter start ("+i+")");
                counter(size,0, i+1);
            }
//            System.out.println("final count : "+count);
            return answer=count;
        }

        //r=0(시작위치), n = 뽑을 개수, n=1 시작
        public  void counter(int[] arr,  int r, int n){
            if (n==0) {
                int x =1;
                for(int i =0 ; i< visited.length;i++){
                    if(visited[i]){
                        x*=arr[i];
                    }
                }
                count+=x;
//                System.out.println("count : "+count+"       ||  "+Arrays.toString(visited));
                return;
            }else{
                for (int i = r; i < arr.length; i++) {
//                    list.add(arr[i]);
                    visited[i] = true;
                    counter(arr, i + 1, n - 1);
//                    list.remove(list.indexOf(arr[i]));
                    visited[i] = false;
                    if(!(arr.length -i>n)){     //무의미한 재귀를 막기위한 부분 예(3개남았는데3개뽑으려할때는 한번돌면 끝나기때문)
                        break;
                    }
                }
            }
        }
    }
}

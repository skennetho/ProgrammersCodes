package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 문제 설명
 * 고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.
 * 고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 모든 차량이 한 번은
 * 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.
 *
 * 제한사항
 * 차량의 대수는 1대 이상 10,000대 이하입니다.
 * routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점,
 * routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
 * 차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
 * 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.
 */

/*
첫번째시도:
모두 뒤지면서 최적을 찾기?...일단 이걸로 시작
카메라를 새로 둬야 하는 시점이 생기면 answer 를 ++해가는 방식으로 가자.
경우1: x_in, x_out, y_in, y_out ==> 카메라 2개
경우2: x_in, y_in, x_out, y_out ==> 카메라 1개
경우3: x_in, y_in, y_out , x_out ==> 카메라1개
경우의 수는 항상 이것밖에 없나? x_in<=x_out, y_in<=y_out이니까 이것밖에 없다.

음.. 근데 다른 방식을 찾은것 같다.
경우1: x_in < cam1 < x_out, y_in<cam2<y_out
경우2: y_in < cam1 < x_out
경우3: y_in < cam1 < y_out
를 보면 max_in, min_out  가 겹치면 카메라를 새로 만들어야 한다는사실을 알 수 있다.

그러므로 단순히 앞에서부터 차례로 max_in과 min_out을 옮겨가며 겹칠때마다 answer를 ++하면 될까?
앞에서 부터 하려면 정렬도 해줘야한다..
이게 greedy의 해결방식인가...

첫번째시도 결과: 정확도 1/5, 효율성 1/5.... 뭐가 문젤까..
원인: 아무래도 routes를 sorting하는 과정에서 in지점이 오름차순으로 하는건 좋았으나 in지점이 같은 경우는 생각하지 못했다.
이 경우를 추가 sorting하는 과정에서 추가해주었다.
결과: 근데 여전히 1/5, 1/5... 뭐가 문젤까...

원인: 첫 번째 입장한 차를 sorting이전의 routes를 사용함... 멍청했다...
결과: 정확도 5/5, 효율성 4/5... 뭐가 잘못되었을까...
sorting하는 과정에서오래 걸린걸까? 그렇기엔 기본 주어진 Arrays의 sort는 빠른편이라고 했다. 그럼
이거 말고 다른부분은...
??? 근데 다시 돌리니 또 된다... 애매하게 성공... ㅎㅎ

* */
public class SpeedTrap {
    int[][] routes;
    int expected;

    public SpeedTrap(int[][] routes, int expected){
        this.routes = routes;
        this.expected =expected;
    }

    public int solution(int[][] routes) {
        int answer = 1;                                                            //조건에 차량이 한대이상이니 먼저 넣어 줄수 있다.
        //int max_in=routes[0][0], min_out =routes[0][1];   <-실수

        Arrays.sort(routes, (int[] a, int[] b)-> (a[0]!=b[0]? a[0]-b[0]: a[1]-b[1]));
        //Arrays.sort(routes, (int[] a, int[] b)->(a[0] - b[0]));  <-실수
        printRoutes();
        int max_in=routes[0][0], min_out =routes[0][1];

        for(int i =1 ; i< routes.length; i++){
            max_in = Math.max(max_in,routes[i][0]);
            min_out = Math.min(min_out,routes[i][1]);
            if(min_out < max_in){
                answer++;
                max_in = routes[i][0];
                min_out = routes[i][1];
            }
        }

        return answer;
    }

    public void printRoutes(){
        System.out.println("sorted routes:");
        for(int[] arr: routes){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("--------------");
    }

    public void test() {
        System.out.println("expected: "+ expected+",  result: " + this.solution(routes));
    }
}

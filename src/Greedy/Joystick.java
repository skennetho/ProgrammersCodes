package Greedy;

/*
조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동
예를 들어 아래의 방법으로 JAZ를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

제한 사항
name은 알파벳 대문자로만 이루어져 있습니다.
name의 길이는 1 이상 20 이하입니다.
입출력 예
name	return
JEROEN	56
JAN	    23
* */

import java.util.Arrays;
import java.util.LinkedList;

/*
변하지 않는 수: 각 알파벳을 만들기위한 up이나 down의 수
최솟값에 영향을 주는수: 각 알파벳의 자리를 이동하기 위한 수
-> 커서의 이동을 최소화 해야함
-> 커서의 이동은 한방향으로만, 오른쪽이나 왼쪽, 둘중하나가 최소경로일듯.
        -> BACAAAAAD는 C를 찍고 D를 찍는게 최소경로.

탐욕법이라는 카테고리에 존재하는 문제이기에 이를 이용하여
당장 현재위치에서 좌우 중 가까운 곳을 먼저 들리는 방식을 이용해 보자!
->좌우 각각의 위치까지의 거리를 구한뒤 더 작은 수의 위치로 이동하자. == 가까운곳으로 이동., 거리는 총거리에 포함하자.
->이동한 자리에 0을 두고 그다음 가까운 곳을 탐색
->만약 A가아닌자리에 1이 있는 배열이 전부 0 이라면( == 전체 다 들림) 반복끝

* */

public class Joystick {
    public int solution2(String name) {
        int cnt = 0;
        int[] alpha_loc = new int[name.length()];
        LinkedList<Integer> notALoc = new LinkedList<>(); // not 'A' location
        int len = 0;
        for (char c : name.toCharArray()) {
            if (c != 'A') {
                cnt += Math.min(c - 'A', 'Z' - c + 1);
                alpha_loc[len] = 1;
                notALoc.add(len);
            }
            len++;
        }

        int loc = 0;
        alpha_loc[loc] = 0;
        int left , right ;
        while (!isZero(alpha_loc)) {

//            System.out.println(Arrays.toString(alpha_loc) +"  loc: "+loc);

            left = (loc + len - 1) % len;
            right =  (loc + 1)%len;

//            System.out.print("L ");
            while ( left!= loc && alpha_loc[left] == 0) {
                left = (left + len - 1) % len;
//                System.out.print(">[" + left + "]: ");
            }
//            System.out.println();
//            System.out.print("R ");
            while (right != loc && alpha_loc[right] == 0) {
                right = (right + 1)%len ;
//                System.out.print(">" + right + "  ");
            }
//            System.out.println();
//            System.out.println(String.format("loc: %d, left: %d, right:%d, cnt: %d", loc, left, right, cnt));
            int distance_L = loc > left ? loc - left : loc + len - left;
            int distance_R = loc < right ? right - loc : right + len - loc;
//            System.out.println(String.format("DISTANCE... LEFT: "+distance_L+", RIGHT: "+distance_R));
            loc = distance_L < distance_R ? left : right;
            alpha_loc[loc] = 0;
//            System.out.println(Arrays.toString(alpha_loc) +"  loc: "+loc);

            if(distance_L!=0 && distance_R!=0){
//                System.out.println("true");
                cnt+= Math.min(distance_L, distance_R);
            }else {
//                System.out.println("false");
                cnt += distance_R == 0 ? distance_L : distance_R;
            }
        }

        return cnt;
    }

    public int solution(String name) {
        int answer = 0;
        int cnt = -1;
        int[] route = new int[name.length()];

        for (char character : name.toCharArray()) {
            if (character != 'A')
                route[answer] = 1;
            answer++;

            if (character - 'A' <= 'Z' - character) {
                cnt += character - 'A';      //ex. B-A = 1
            } else {
                cnt += 'Z' - character + 1;  //ex. Z-Z = 1
            }
        }
        System.out.println(Arrays.toString(route));

        int leftCnt = 0, rightCnt = 0, leftIdx = 0, rightIdx = 0;
        int[] rightRoute = route.clone(), leftRoute = route.clone();
        while (!isZero(rightRoute)) {
            if (rightRoute[rightIdx] != 0) {
                rightRoute[rightIdx] = 0;
            }
            rightIdx++;
            rightCnt++;
        }

        while (!isZero(leftRoute)) {
            if (leftRoute[leftIdx] != 0) {
                leftRoute[leftIdx] = 0;
            }
            leftIdx = (leftRoute.length + leftIdx - 1) % leftRoute.length;
            leftCnt++;
        }
        System.out.println(Arrays.toString(leftRoute));
        System.out.println(Arrays.toString(rightRoute));
        System.out.println("cnt: " + cnt + "    <" + leftCnt + ", " + rightCnt + ">");

        if (leftCnt <= rightCnt) {
            return cnt + leftCnt;
        } else {
            return cnt + rightCnt;
        }
    }

    public boolean isZero(int[] arr) {
        for (int i : arr) {
            if (i != 0) return false;
        }
        return true;
    }
}

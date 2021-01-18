package Greedy;

//문제설명
/*
n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.

다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다. 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.

제한사항

섬의 개수 n은 1 이상 100 이하입니다.
costs의 길이는 ((n-1) * n) / 2이하입니다.
임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
연결할 수 없는 섬은 주어지지 않습니다.
입출력 예

n	costs	return
4	[[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]	4
* */

//문제풀이
/*
cost별로 정렬을 하고
가장 적은 cost순으로 하나씩  다리를 만든다. 모든 섬들이 서로 통행가능할때까지.
    다리건설조건 입력값 섬a,섬b, 다리n
        a에서 b로 갈수 있느냐 (통행가능여부)

//통행이 불가능/가능한 섬 판별을 하려면
//    1. 전수조사 :
//        건설이 된 섬들에서 연결된 섬들을 조사한다. 연결되지 않았으면 통행이 불가한것, 연결이 되면 통행가능. ( 최악복잡도인경우 너무 오래걸릴듯)
//    2. 현재 섬들의 상태를 담는 해시의 리스트를 만듬:
//        각 섬들의 다리유무를 담는 배열과, 각 섬들에 어떤 섬이 연결되어있는지를 나타내는 리스트를 생성.
//        더이상 연결이 필요없는 섬은 다리설치불가라는 상태를 알수 있어야한다.
//        if 섬이 다리가 없다면
//            if d
//

a섬이 b섬에 연결이 통행가능여부 판별하기 위해선:
    다리를 건설할때마다 현재 만들어진 섬들의 connection을 알고 있어야 할 필요가있다.
    이것을 알기위해선:
        1. 재귀를 이용하여 a와 연결된 모든 섬에 직접 b로 갈수 있는지 물어보는방법과
        2. 다리를 건설할떄마다 a가 갈수 있는 섬의 리스트를 항상 업데이트한다.
        -> a에서 b로 갈수있고 b는 c로 갈수 있다면 a는 c로 갈수 있다.
        상삼각2차원배열 사용?

나중에 알게된 사실

* */

import java.util.Arrays;
import java.util.LinkedList;

public class ConnectIslands {
    int[][] connected ;

    public int solution(int n , int[][] costs){
        int costNeed = 0;
        connected = new int[n][n];

        Arrays.sort(costs, (int[] a, int[] b)-> a[2]- b[2]);
        System.out.print("<n: "+n+"> ");
        printCost(costs);

        for(int[] cost: costs){
            System.out.println("=="+Arrays.toString(cost) + " connection start");
            if(isAllConnected()){
                System.out.println("all islands are connected");
                break;
            }
            //새로운 지역인지 아닌지 판별후 다리건설
            if(connected[cost[0]][cost[1]]==0 ){
                updateConnected(cost[0],cost[1]);
                costNeed += cost[2];
            }
        }
        System.out.println("costNeed:"+ costNeed+ ", allConnected? "+isAllConnected());
        return costNeed;
    }
    public void updateConnected(int x, int y){
        if(connected[x][y] ==1) {
            System.out.println("already connected: "+x+"<->"+y);
            return;
        }
        System.out.println("connect "+x+" ->"+y);

        connected[x][y] =1;
        connected[y][x] =1;
        for(int i =0 ; i< connected.length ; i++){
            if (connected[x][i] == 1&& i!=y) {  //x섬이랑 i섬이 연결되어있으면 y랑도 연결
                updateConnected(y,i);
//                connected[y][i]=1;
//                connected[i][y]=1;
            }
            if(connected[y][i]==1 && i!=x){     //y섬이랑 i섬이 연결되어있으면 x랑도 연결
                updateConnected(x,i);
//                connected[x][i] =1;
//                connected[i][x] =1;
            }
        }
        printArray(connected);
    }
    public boolean isAllConnected(){
        for(int i=1; i< connected[0].length; i++){
            if(connected[0][i] == 0)return false;
        }
        return true;
    }
    public void printArray(int[][] arr){
        System.out.print("ARR>");
        for(int i =0 ; i <arr.length ; i++) System.out.print( "--"+i);
        System.out.println();
        for(int i =0 ; i<arr.length ; i++){
            System.out.println(i+"    "+Arrays.toString(arr[i]));
        }
    }
    public void printCost(int[][] cost){
        System.out.print("COSTS: ");
        for(int[] c : cost){
            System.out.print(", "+Arrays.toString(c));
        }
        System.out.println();
    }
}
/*
0    [0, 1, 1, 0, 0]
1    [1, 0, 1, 0, 0]
2    [1, 1, 0, 0, 0]
3    [0, 0, 0, 0, 1]
4    [0, 0, 0, 1, 0]

* */
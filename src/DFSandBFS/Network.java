package DFSandBFS;
import java.util.*;
/**
 * 문제 설명
 * 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고,
 * 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
 * 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
 *
 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때,
 * 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
 *
 * 제한사항
 * n은 1 이상 200 이하인 자연수입니다.
 * 각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
 * i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
 * computer[i][i]는 항상 1입니다.
* */

/*사용한 방법
::첫번째 시도::
먼저 상삼각의 숫자들을 돌며 하나의 컴과 다른하나의 컴이 연결된 경우들을 찾는다. 그다음
네트워크에 포함시켜 간다.
예를들어 computers[0][1] ==1 이라면 연결된 것이므로 추가해줘야한다. 다만 먼저
    0과 1이 현재 bfs라는 트리에 등록이 되어있는지 확인한다. 등록이안되어있다면 새로운 arraylist를 만들어 0과1을 포함시키고 등록을 한다.
    만약 0이나 1 둘다 이미 등록이 되어있다면? == 그럴리는 없을 것이다. 상삼각을 지나면서 0,3을 지났다고 한다면 3,0을 지나진 않으니깐
    둘중 하나가 네트워크에 등록이 되어있다면?
        등록된 수가 0 이라고 한다면 0이 포함되어있는 리스트에 1도 포함시켜주고 networkedNumbers에 해당 위치에 1을 넣음으로써 등록을 해준다.
    위 과정을 반복한다 n-1, n까지
 */
public class Network {
    int n;
    int[][] computers;

    public  void testSolution(){
        System.out.println("n= "+n);
        for(int[] arr: computers){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("::result: "+solution(n, computers));
        System.out.println("-------------------");
    }

    public int solution(int n, int[][] computers) {
        ArrayList<ArrayList<Integer>> bfs = new ArrayList<>();
        int[] visited = new int[n];

        for(int i =0 ; i< n ;i++){                                      //상삼각의 1찾기
            if(visited[i] != 1){
                visited[i]=1;
                ArrayList<Integer> arr1 = new ArrayList<Integer>();
                arr1.add(i);
                bfs.add(arr1);
            }
            for (int j=i+1 ; j<n; j++){

                if(visited[j] != 1){
                    visited[j]=1;
                    ArrayList<Integer> arr2 = new ArrayList<Integer>();
                    arr2.add(j);
                    bfs.add(arr2);
                }
//
                if(computers[i][j] ==1 || computers[j][i] ==1){                                //찾았다면

                    int index_i = findIndexOfArrayWithNum(bfs, i);
                    int index_j = findIndexOfArrayWithNum(bfs, j);
                    connectArrayList(bfs, index_i, index_j);

                }
                System.out.println("test:"+bfs.size());
            }
        }
        return bfs.size();
    }

    public int findIndexOfArrayWithNum(ArrayList<ArrayList<Integer>> list, int number){     //visited[number] ==1 이어야 가능.

        for(int i=0 ; i< list.size(); i++){
            if(list.get(i).contains(number)) return i;
        }
        return -1;
    }

    public void connectArrayList(ArrayList<ArrayList<Integer>> list, int index1, int index2){
        if(index1 == index2) return;
        if(index1<0 || index2<0){
            System.out.println("::error!");
            return;
        }

        list.get(index1).addAll(list.get(index2));
        list.remove(index2);
    }

    public Network(int n, int[][] computers){
        this.n = n;
        this.computers = computers;
    }
}

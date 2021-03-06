import BruteForce.Carpet;
import Greedy.ConnectIslands;
import Greedy.Joystick;
import Greedy.MakingBigNumber;
import Heap.DiscController;
import Heap.DoublePriorityQueue;
import Sort.H_index;
import StackAndQueue.Printer;
import StackAndQueue.TruckCrossingBridge;

import java.util.*;

public class test {
    public static void main(String[] args) {
        test_ConnectIslnads();
    }

    public static void test_ConnectIslnads() {
        LinkedList<int[][]> costList = new LinkedList<>();
        LinkedList<Integer> nList = new LinkedList<>();
        LinkedList<Integer> answerList = new LinkedList<>();

        nList.add(4);
        costList.add(new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}});
        answerList.add(4);

        nList.add(5);
        costList.add(new int[][]{{0,1,1},{3,4,1},{1,2,2},{2,3,4}});
        answerList.add(0);

        nList.add(5);
        costList.add(new int[][]{{0,1,1},{0,4,5},{2,4,1},{2,3,1},{3,4,1}});
        answerList.add(0);

        nList.add(5);
        costList.add(new int[][]{{0, 1, 1}, {3, 1, 1}, {0, 2, 2}, {0, 3, 2}, {0, 4, 100}});
        answerList.add(104);

        nList.add(4);
        costList.add(new int[][] {{0,1,1},{0,2,2},{2,3,1}});
        answerList.add(4);

        nList.add(6);
        costList.add(new int[][] {{0, 1, 5}, {0, 3, 2}, {0, 4, 3}, {1, 4, 1}, {3, 4, 10}, {1, 2, 2}, {2, 5, 3}, {4, 5, 4}} );
        answerList.add(11);

        ConnectIslands m = new ConnectIslands();
        for (int i = 0; i < nList.size(); i++) {
            System.out.println("SOLUTION -- result >> " + m.solution(nList.get(i), costList.get(i)) + " ==? "
                    + answerList.get(i));
            System.out.println("-------------------------------------");
//            break;  // 제거
        }


    }

    public static void test_MakingBigNumber() {

        HashMap<String, Integer> caseList = new HashMap<>();

        caseList.put("1924", 2); // 94
        caseList.put("1231234", 3); // 3234
        caseList.put("4177252841", 4);  //775814
        caseList.put("1234567890", 0);
        caseList.put("1", 0);

        MakingBigNumber m = new MakingBigNumber();
        for (Map.Entry<String, Integer> casex : caseList.entrySet()) {
            System.out.println("SOLUTION -- result >> " + m.solution2(casex.getKey(), casex.getValue()));
            System.out.println("-------------------------------------");
        }
    }

    public static void test_Joystick() {
        LinkedList<String> caseList = new LinkedList<>();
        caseList.add("JAZ");     //11
        caseList.add("JAN");    //23
        caseList.add("JEROEN"); //56
        caseList.add("BAAAAABBB");  //7
        int i = 0;
        while (i != 0) {
            System.out.println("endless");
        }

        Joystick j = new Joystick();
        for (String casex : caseList) {
            System.out.println(casex + " : " + j.solution2(casex));
            System.out.println("\n\n\n");
        }
    }

    public static void test_Carpet() {

        LinkedList<int[]> caseList = new LinkedList<>();
        caseList.add(new int[]{10, 2});
        caseList.add(new int[]{8, 1});
        caseList.add(new int[]{8, 1});
        Carpet c = new Carpet();
        for (int[] casex : caseList) {
            System.out.println(Arrays.toString(c.solution(casex[0], casex[1]))
                    + " ... input:" + Arrays.toString(casex));
        }

    }

    public static void test_H_Index() {
        int[] citations = {3, 0, 6, 1, 5}; // 3
        int[] case1 = {0};
        int[] case2 = {3, 3, 5, 5, 5}; //4
        int[] case3 = {7, 7, 7, 7, 7, 7, 7};  //7
        int[] case4 = {0, 0, 0, 0, 0}; //...? 0인가?
        LinkedList<int[]> caseList = new LinkedList<>();
        caseList.add(citations);
        caseList.add(case1);
        caseList.add(case2);
        caseList.add(case3);

        H_index h_index = new H_index();
        for (int[] caseX : caseList) {
            System.out.println(h_index.solution(caseX) + " : " + Arrays.toString(caseX));
        }
    }

    public static void test_DoublePriorityQ() {
//        String[] operation = {"I 15", "D 1"};    //0,0
//        String[] operation = {"I 7", "I 5","I -5","D -1"};    //7,5
//        String[] operation = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};    //0,0
        String[] operation = {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"};
//        String[] operation = {"D -1", "D 1", "D -1" , "D 1"};
        System.out.println(new DoublePriorityQueue().solution(operation));
        System.out.println(new DoublePriorityQueue().solution2(operation));

    }

    public static void test_DistController() {
        int[][] job = {{0, 3}, {1, 9}, {2, 6}};    //9
//        int[][] job ={{0, 10}, {2, 10}, {9, 10}, {15, 2}};    //14
//        int[][] job = {{0, 10}, {2, 12}, {9, 19}, {15, 17}};    //25
//        int[][] job = {{10,10}, {30,10}, {50,2}, {51,2}};     // 6
//        int[][] job = {{0,3}, {7,4}};

        System.out.println(new DiscController().solution(job));
        System.out.println(new DiscController().solution2(job));
        System.out.println(new DiscController().solution3(job));
    }

    public static void test_Printer() {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

//        int[] priorities = {1, 1, 9, 1, 1, 1};
//        int location = 0;
        System.out.println(new Printer().solution(priorities, location));
    }

    public static void test_TruckCrossingBridge() {
//        int length=100 ;
//        int weight= 100;
//        int[] trucks = {10,10,10,10,10,10,10,10,10,10};

//        int length=100 ;
//        int weight= 100;
//        int[] trucks = {10};

        int length = 2;
        int weight = 10;
        int[] trucks = {7, 4, 5, 6};

        TruckCrossingBridge t = new TruckCrossingBridge();
        int answer = t.solution(length, weight, trucks);
        System.out.println(answer);
    }
}

package StackAndQueue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TruckCrossingBridge {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridgeQ = new LinkedList<>();
        Queue<Integer> truckQ = new LinkedList<>();
        int capacity = weight;
        int dayCount = 0;

        for(int i : truck_weights){
            truckQ.add(i);
        }

        for(int i = 0; i <bridge_length; i++){
            if(!truckQ.isEmpty() && capacity>=truckQ.peek()){
                bridgeQ.add(truckQ.peek());
                capacity -=truckQ.poll();
                dayCount++;
            }else{
                bridgeQ.add(0);
                dayCount++;
            }
        }
//        print(bridgeQ,capacity, dayCount);

        if(truckQ.isEmpty()){
            return dayCount+truck_weights.length;
        }

        while(!truckQ.isEmpty()){
            capacity+=bridgeQ.poll();
            dayCount++;
            if(capacity>=truckQ.peek()){
                bridgeQ.add(truckQ.peek());
                capacity-=truckQ.poll();
            }else{
                bridgeQ.add(0);
            }
//            print(bridgeQ,capacity, dayCount);
        }
        while(!bridgeQ.isEmpty()){
            bridgeQ.poll();
            dayCount++;
        }
        return dayCount;
    }

    public void print(Queue q, int cap, int day){
        System.out.println("day"+day+", capacity:" +cap+ "  : "+q);
    }
}
/*
[ 7 2 4 1 10 ] l:3, w:10
0 0 0
0 0 7
0 7 2
7 2 0
2 0 4
0 4 1
4 1 0
1 0 0
0 0 10
0 10 0
10 0 0
0 0 0
시작과 종료는 큐의 empty를 이용 -> 시작과 동시에 트럭넣고 카운트다운시작
남은시간은 다리에서 가장 앞 트럭의 남은시간
큐 peek가 0 이면 숫자가 나올때까지 시간카운트
큐의 총합은 다리의 capacity

트럭들에게 시간을 알려주는방법은?
7 2 0 4 1 0 0 10 0 0 0
* */
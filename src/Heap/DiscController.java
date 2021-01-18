package Heap;
//문제설명
/*
각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때,
작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return
하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)

입출력 예
jobs	return
[[0, 3], [1, 9], [2, 6]]	9
입출력 예 설명
문제에 주어진 예와 같습니다.

0ms 시점에 3ms 걸리는 작업 요청이 들어옵니다.
1ms 시점에 9ms 걸리는 작업 요청이 들어옵니다.
2ms 시점에 6ms 걸리는 작업 요청이 들어옵니다.
* */

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
        일단, 작업의 처리 시간을 오름차순으로 정렬하는 우선순위 큐(PriorityQueue)를 만듭니다(아직 삽입 x).
 정렬되지 않은 작업들(jobs)을 요청시간이 빠른순으로 정렬합니다.
 정렬된 작업을 보면서 작업을 시작할 수 있는지 확인합니다.
 시작할 수 있는 작업이 있다면 해당 작업을 처리합니다.
 시작할 수 있는 작업이 없다면 해야 할 작업 중 가장 먼저 해야 할 작업의 요청 시간으로 현재 시간을 바꾸고, 큐에 삽입합니다.
 더 이상 작업할 것이 없을 때까지 반복하며 이후 평균 시간을 구해 return 합니다.
        * */
public class DiscController {
    public int solution(int[][] jobs) {
        int answer = 0;
        LinkedList<Job> jobList = new LinkedList<>();
        jobList.sort((Job j1 ,Job j2 ) -> j1.requestTime - j2.requestTime);

        LinkedList<Job> dropList =new LinkedList<>();
        LinkedList<Integer> logging = new LinkedList<>();       //log
        PriorityQueue<Job> jobQ = new PriorityQueue<>((Job j1, Job j2) -> j1.job - j2.job);
        for (int[] job : jobs){
            jobList.add(new Job(job[0], job[1]));
        }

        int time = 0;
        while (!jobList.isEmpty()) {
            dropList.clear();

            for (Job j :jobList) {
                System.out.println(j.requestTime+" : "+j.job);
                if (j.requestTime <= time) {
                    jobQ.add(j);
                    dropList.add(j);
                }
            }

            System.out.println("jobQ = "+jobQ);
            if(!dropList.isEmpty())
                for (Job j : dropList) {
                    System.out.println("delete= "+j.requestTime+" : "+j.job);
                    jobList.remove(j);
                }

            if (jobQ.isEmpty()) {
                time++;
                logging.add(0);
                continue;
            } else {
                while(!jobQ.isEmpty()) {
                    Job j = jobQ.poll();
                    time += j.job;
                    answer+=time -j.requestTime;
                    for (int i = 0; i < j.job; i++) logging.add(j.job);
                }
            } System.out.println("logging: "+logging);
        }
        return answer/jobs.length;
    }
    public int solution3(int[][] jobs){
        int time = 0;
        int answer =0;
        int idx = 0 ;
        PriorityQueue<Job> jobList = new PriorityQueue<>((Job j1, Job j2)-> j1.requestTime- j2.requestTime);
        PriorityQueue<Job> jobQ = new PriorityQueue<>((Job j1, Job j2 )-> j1.job- j2.job);

        for(int[] job : jobs){
            jobList.offer(new Job(job[0], job[1]));
        }

        while(!jobList.isEmpty() || !jobQ.isEmpty()){
            System.out.println("while start... "+time +" --- job:"+jobList);
            while( !jobList.isEmpty() &&  jobList.peek().requestTime<=time){
                System.out.println("while : "+jobList.peek());
                jobQ.offer(jobList.poll());
            }

            if(jobQ.isEmpty()){
                System.out.print("   if : " + time +" -> ");
                time = jobList.peek().requestTime;
                System.out.println(time);
            }else{
                Job j = jobQ.poll();
                System.out.println("  else: "+j +" ... ans: "+(time -j.requestTime  + j.job));
                answer+= time - j.requestTime  + j.job;
                time+= j.job;
            }
        }

        return answer / jobs.length;
    }

    public int solution2(int[][] jobs) {
        int answer = 0;
        int len = jobs.length;
        int time = 0;
        int idx = 0;
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        for(int[] arr :jobs){
            System.out.println(arr[0]+","+arr[1]);
        }

        while (idx < len || !q.isEmpty()) {
            System.out.println("while start .... "+time);
            while (idx < len && jobs[idx][0] <= time) {
                System.out.println("while : "+jobs[idx][0]+","+jobs[idx][1]+" .... idx:"+idx);
                q.offer(jobs[idx++]);
            }

            if (q.isEmpty()) {
                time = jobs[idx][0];
                System.out.println("   if : " + time +" .... idx:"+idx);
            }else {
                int[] job = q.poll();
                System.out.println("  else: "+job[0]+","+job[1] +"...ans: "+(time - job[0] + job[1]));
                answer += time - job[0] + job[1];
                time += job[1];
            }
        }
        return answer / len;
    }

    public int solution4(int[][] jobs){
        int time= 0;
        int answer = 0;
        int idx = 0;
        int len = jobs.length;
        PriorityQueue<int[]> jobQ = new PriorityQueue<>((int[] x1, int[] x2) -> x1[1] - x2[1]);
        Arrays.sort(jobs, (int[] x1, int[] x2) -> x1[0] - x2[0]);

        while(idx<len || !jobQ.isEmpty()){
            while(idx<len && jobs[idx][0]<= time){
                jobQ.offer(jobs[idx++]);
            }
            if(jobQ.isEmpty()){
                time= jobs[idx][0];
            }else{
                int[] job = jobQ.poll();
                answer+= time - job[0] + job[1];
                time+=job[1];
            }
        }
        return answer;
    }

    class Job {
        int requestTime;
        int job;
        public Job(int requestTime, int job) {
            this.requestTime = requestTime;
            this.job = job;
        }
        public String toString(){
            return "["+requestTime+", "+job+"]";
        }
    }
}

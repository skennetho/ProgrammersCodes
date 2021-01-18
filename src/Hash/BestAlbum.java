package Hash;

import java.util.*;

/*
* 문제 설명 레벨3
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
입출력 예
genres	plays	return
[classic, pop, classic, classic, pop]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.*/
public class BestAlbum {
    public static void main(String[] args){
        String[] genres = {"classic", "classic", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] answer = Solution.solution(genres,plays);
        System.out.println(Arrays.toString(answer));
    }

    static class Solution{
        static public int[] solution(String[] genres, int[] plays){
            int[] answer;

            HashMap<String,Genre> g_map = new HashMap<>();
            for(int i =0 ;i<genres.length ; i++) {
                String g_name = genres[i];
                if (!g_map.containsKey(g_name)) {
                    g_map.put(g_name, new Genre(g_name));
                }
                g_map.get(g_name).add(i, plays[i]);
            }

            LinkedList<Map.Entry<String, Genre>> entries = new LinkedList<>(g_map.entrySet());
            Collections.sort(entries,(o1, o2)->o1.getValue().length <o2.getValue().length? 1:-1);

            ArrayList<Integer> ans_list = new ArrayList<>();
            for(Map.Entry<String, Genre> entry : entries){
                System.out.println(entry.getValue().name+" : "+ entry.getValue().length);
                System.out.println(entry.getValue().ids);
                System.out.println(entry.getValue().plays);
                Genre g = entry.getValue();
                if(g.plays.size() ==0) continue;
                ans_list.add(g.ids.get(0));
                if(g.plays.size()>=2)
                    ans_list.add(g.ids.get(1));
            }

            answer =new int[ans_list.size()];
            for(int i =0; i<answer.length;i++){
                answer[i] = ans_list.get(i);
            }

            return answer;
        }
    }
    static class Genre {
        int length = 0;
        ArrayList<Integer> plays = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        String name;

        public Genre(String name){
            this.name = name;
        }

        public void add(int id, int play){
            length+=play;
            if(ids.isEmpty()){
                plays.add(play);
                ids.add(id);
                return;
            }
            for(int i = 0 ; i< plays.size() ;i++){
                if(plays.get(i) < play){
                    plays.add(i,play);
                    ids.add(i,id);
                    break;
                }
                if(i+1 == plays.size()){
                    plays.add(play);
                    ids.add(id);
                    break;
                }
            }
        }
    }

    static class Solution2{
        /*
         * 장르별로 객체를 만들고, 장르들을 담을수 있는 변수가 필요.
         * 각장르는 트랙의 고유번호와 많이 플레이한 순서대로 출력할수 있어야함.
         * */
        public static int[] solution(String[] genres, int[] plays) {
            int[] answer = new int[plays.length];
            /*
             * 1. 장르별로 총값을 구하고
             * 2. 장르의 길이순으로 sort한다.
             * 3. 장르내에서 길이별로 sort한다.
             * */
            HashMap<String , Genre> genreHash = new HashMap<>();
            for(int i =0; i<genres.length; i++){
                if(!genreHash.containsKey(genres[i])) genreHash.put(genres[i],new Genre(genres[i]));
                genreHash.get(genres[i]).addTrack(i, plays[i]);
            }
            answer = new int[genreHash.keySet().size()*2];                                                              //항상 각장르마다 두개의 노래가 나오는게 아니기에 이부분은 에러가 생김.

            LinkedList<Map.Entry<String, Genre>> entries = new LinkedList<>(genreHash.entrySet());
            Collections.sort(entries,(o1, o2)->o1.getValue().length <o2.getValue().length? 1:-1);

            int i=0;
            for(Map.Entry<String,Genre> entry : entries){
                for(int x : entry.getValue().getIntArray())
                    answer[i++] = x;
            }

            return answer;
        }
        static class Genre{
            int length = 0;
            ArrayList<Integer> sorted = new ArrayList<>();
            HashMap<Integer,Integer> tracks;
            String name;
            public Genre( String name){
                tracks = new HashMap<>();
                this.name = name;
            }
            public void addTrack(int number, int length) {
                tracks.put(number, length);
                this.length+=length;
                if(sorted.isEmpty()){
                    sorted.add(number);
                    return;
                }
                for(int i =0; i<sorted.size(); i++){
                    if(tracks.get(sorted.get(i)) < length){
                        sorted.add(i,number);
                        return;
                    }else if(tracks.get(sorted.get(i)) == length){
                        if(sorted.get(i)> number)
                            sorted.add(i,number);
                        else
                            sorted.add(i+1,number);
                    }
                }
                sorted.add(number);
                return;
            }
            public int[] getIntArray(){
//                System.out.println("log: "+name + ", " +sorted);
                int[] arr;
                if(sorted.size()>=2) {
                    arr = new int[2];
                    arr[0] = sorted.get(0);
                    arr[1] = sorted.get(1);
                }else{
                    arr = new int[1];
                    arr[0] = sorted.get(0);
                }
                return arr;
            }
        }

    }
}

package DFSandBFS;
/**
 * 문제 설명
 * 두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여
 * begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
 *
 * 1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
 * 2. words에 있는 단어로만 변환할 수 있습니다.
 * 예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.
 *
 * 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐
 * begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 각 단어는 알파벳 소문자로만 이루어져 있습니다.
 * 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
 * words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
 * begin과 target은 같지 않습니다.r
 * 변환할 수 없는 경우에는 0를 return 합니다.
* */
/*
음.. 어떻게 풀어야할까.. 모든 경우의 수를 뒤지면서 하면 쉽게 하겠지만 효율성이 떨어질것같다.
그리고 변환 할 수 있는지 여부를 판단 할수 있을까? 트리의 속성을 이용해야겠다. 넓이부터 뒤져가야겠다.

::첫번째 시도: BFS방법, 맨위가 begin이고 아래로 트리를 확장해가면서 target을 찾는 과정으로 가자.
먼저 begin에서 한개만 바뀌어서 도달할 수 있는 단어들을 찾는다. available characters.
그 단어들마다 다시 또
    visit하지 않은 단어중에서 한개만 바뀌어서 도달할 수 있는 단어를 찾는다.
    <- visit하지 않은 단어를 체크하기 위해 boolean배열을 이용하자.
    <- 이동할 수 있는 단어들의 리스트에 추가.
    재귀의 종료조건:
        이동할 단어 리스트에서 target이 없는 경우 무의미 하니  count = 최대값 넣어주고 종료.
        이동할 단어 리스트가 0인경우 더이상 더나아 갈수 없으니 종료.
        begin 단어가 target이 된경우 count를 반환하고 종료;
이런식으로 모든 word에 대해서 실행을 했는데도 target 되지 못했다면 0을 리턴하자
    <- 맨 아래 원소들의 배열에서 target이 없는 경우.

::효율성을 위해 생각해봐야할건...
트리를 확장해 나갈때 불필요한 노드는 생성할 필요가 없다는것. 아 이게  위에서 <- 표시한 부분이구나.. 제꼈으니..?
그럼 각 노드마다 visited를 따로 기억해둬야 한다는건데...
<-list를 쌍으로 만들어 줘야겠다... 아니면 dfs로 가야하나. 사실 최악복잡도는 둘다 똑같은데... 일단 코딩해보지뭐

::풀다보니...
dfs와 bfs중 뭘사용하던 같은 결과를 뱉을수있다. 우선순위의 차이인데

경우 
aaa->bbb => [aac aab abc bbc bbb] == aaa-aac-abc-bbc-bbb
* */
import java.util.*;
public class TransformWords {
    String begin;
    String target;
    String[] words;
    int expected;
   
    public int solution(String begin, String target, String[] words) {
        ArrayList<char[]> wordArrayList = new ArrayList<>();
        for(String word : words){
            char[] str = word.toCharArray();
            wordArrayList.add(str);
        }

        boolean[] visited = new boolean[words.length];
        int count = findCapability(begin.toCharArray(), target.toCharArray(),wordArrayList, visited, 0);
        if(count ==-1 ) return 0;
        else return count;
    }

    public int findCapability(char[] begin,char[] target, ArrayList<char[]> words, boolean[] visited, int count){
        if(Arrays.equals(begin,target)) return count;
        if(!isContains(words, target, visited)) return -1;

        int min =words.size();
        //이동할 수있는 단어들 리스트 생성, 지나간곳 체크
        ArrayList<char[]> next = new ArrayList<>();
        for(int i =0 ; i< words.size(); i++){
            if(visited[i]==true) continue;
            if(isAvailable(begin,words.get(i))) {
                next.add(words.get(i));
                visited[i] = true;
                int out = findCapability(words.get(i), target, words, visited, count + 1);
                if (out > 0) min = Math.min(min, out);
                visited[i] = false;
            }
        }
        //다음으로 넘어갈 단어가 없다면 실패코드 리턴
        if(next.size()==0) return -1;

        return min;
    }

    public boolean isContains(ArrayList<char[]> words, char[] word, boolean[] visited){
        for(int i =0; i<words.size(); i++){
            if(visited[i]) continue;
            if(Arrays.equals(word, words.get(i))) return true;
        }
        return false;
    }

    public boolean isAvailable(char[] word, char[] word2){
        int different_words =0;
        for(int i =0 ; i< word.length ; i++){
            if(word[i] != word2[i]){
                different_words++;
            }
            if(different_words>1) return false;
        }
        return true;
    }

    public void test(){
        System.out.println("result: "+this.solution(begin,target,words)+", expected: "+expected);
    }

    public void test_available(String word1, String word2){
        System.out.println("["+word1+"] and [" +word2 +"] : "+isAvailable(word1.toCharArray(), word2.toCharArray()) );
    }

    public TransformWords(String begin, String target, String[] words, int expected){
        this.expected = expected;
        this.begin  = begin;
        this.target = target;
        this.words = words;
    }
}

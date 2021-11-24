package other;

import java.util.Collections;
import java.util.*;


public class Lotto {

    public ArrayList<Integer> asList(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public int[] solution(int[] lottos, int[] win_nums) {
        ArrayList<Integer> myNumbers = asList(lottos);
        ArrayList<Integer> winNumbers = asList(win_nums);

        int zeros = 0;
        for (int num : lottos) {
            if (num == 0) {
                zeros++;
            }
        }

        myNumbers.retainAll(winNumbers);

        int best = 7 - zeros - myNumbers.size();
        int worst = 7 - myNumbers.size();
        if (worst >= 6) {
            worst = 6;
        }
        if (best >= 6) {
            best = 6;
        }
        return new int[]{best, worst};
    }

    public static class TestCase {

        public int[] lottos;
        public int[] win_nums;
        public int[] answer;

        public TestCase(int[] lottos, int[] win_nums, int[] answer) {
            this.lottos = lottos;
            this.win_nums = win_nums;
            this.answer = answer;
        }
    }

}

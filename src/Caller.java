import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.util.Scanner;


public class Caller {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MyCallback myCallback = new MyCallback();
        myCallback.setTarget(5);
        myCallback.setInterface(new MyCallback.mInterface() {
            @Override
            public void onInputN(MyCallback m) {
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("스레드 시작");
                        while (true) {
                            System.out.print("--"+m.getInput() + ":" + m.getTarget());
                            if (m.getInput() == m.getTarget()) {
                                System.out.println("정답입니다!");
                                break;
                            }
                            try {
                                Thread.sleep(1000); //1초 대기
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
                th.start();
            }
        });
        myCallback.startInput();
    }
}

class MyCallback {
    public MyCallback() {
        this.input = 0;
        this.target = 10;
        this.callback = null;
    }

    public interface mInterface {
        public void onInputN(MyCallback m);
    }

    mInterface callback;

    public void setInterface(mInterface callback) {
        this.callback = callback;
    }

    int input;

    public int getInput() {
        return input;
    }

    public void setGameEnd() {
        System.out.println("종료");
        this.input = -1;
    }

    int target;

    public int getTarget() {
        return this.target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void startInput() {
        if (callback != null) {
            Scanner scan = new Scanner(System.in);
            System.out.println("숫자 맞추기 게임 시작!!      범위 1~10 (종료는 -1 입력)");
            this.startCallback();
            do {
                System.out.print("\n숫자를 입력해주세요 :");
                input = scan.nextInt();
                System.out.println("입력된수: " + input);
            } while (input != -1);
        }
    }

    public void startCallback() {
        callback.onInputN(MyCallback.this);
    }

}


/////////////////////////////////////////////////////////////////////////////////////////////
class Broccoli {

    public interface CallBack {
        public void onInputOne(Broccoli broccoli);

        public void onInputTwo(Broccoli broccoli);
    }

    private Integer num;
    private CallBack callback;

    public Broccoli() {
        this.num = 0;
        this.callback = null;
    }

    public void setCallBack(CallBack callback) {
        this.callback = callback;
    }

    public Integer getNum() {
        return this.num;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1과 2중 하나를 입력하세요.");
        this.num = scanner.nextInt();

        if (this.callback != null) {
            switch (this.num) {
                case 1:
                    this.callback.onInputOne(Broccoli.this);
                    break;
                case 2:
                    this.callback.onInputTwo(Broccoli.this);
                    break;
                default:
                    System.out.println("not 1 or 2...");
                    break;
            }
        }
    }
}



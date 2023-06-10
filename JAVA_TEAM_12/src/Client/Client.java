package Client;

import Gui.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    public static void main(String[] args) {
        try {
            // GUI 생성
            GUI window = new GUI();
            window.initialize();
            // GUI 실행
            window.getFrame().setVisible(true);

            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // 시작 화면
            final boolean[] isButtonPressed = {false}; // gui의 시작버튼이 눌렸는지 체크하는 client의 변수
            // 버튼을 누를 때까지 대기
            while (!isButtonPressed[0]) {
                // 버튼 상태 확인
                if (window.isButtonClick()) {// gui의 시작버튼이 눌렸는지 체크
                    isButtonPressed[0] = true;
                } else {
                    try {
                        Thread.sleep(500); // 일정 시간 대기 후 다시 확인
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            isButtonPressed[0] = false; // 원상 복귀

            // 닉네임 입력
            String name = window.getInputNickname().getText();

            // 분야 입력
            String area = window.getComboBox();
            String msg = name + ";" + area;
            out.println(msg); // 닉네임과 분야 전송

//1차 텀

            // 상대방 닉네임 받아오기
            String oppName = null;
            while (oppName == null || oppName.isEmpty()) {
                oppName = in.readLine();
            }
            // 누구랑 매치되었는지 띄우고 ok sign 보내기
            window.setLblUserName(name); // 자신의 이름 설정
            window.setLblCounterUserName(oppName); // 상대방 이름 설정
            out.println("ok");
            String isReady = in.readLine();
            if (!isReady.equals("ok")) {
            } // ok 싸인이 아니면

            System.out.println("게임 시작");
            int score = 0;// 현재 점수
            // 상대방이 들아올 때까지 대기
            for (int i = 0; i < 10; i++) {
                int roundNo = i + 1; // 현재 라운드

                window.setRoundNo(roundNo); // 라운드 정보 넘기기
                window.setScore(score);

                String problem = in.readLine(); // 문제 받기
                String pname = problem.split(";")[0]; // 문제의 답
                String pphoto = problem.split(";")[1]; // 사진

                window.setGameImage(pphoto);
                window.isReady = true; // 매칭화면 넘어가기

                final int[] count = {10};
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (count[0] > 0) {
                            window.setLblTime(String.valueOf(count[0]));
                            count[0]--;
                        } else {
                            timer.cancel();
                            window.clickButton();
                        }
                    }
                };
                timer.schedule(timerTask, 0, 1000);
                // 정답버튼 누를 때까지 멈추기
                while (!isButtonPressed[0]) {
                    // 버튼 상태 확인
                    if (window.getButtonCount() == i + 1) {
                        isButtonPressed[0] = true;
                    } else {
                        try {
                            Thread.sleep(100); // 일정 시간 대기 후 다시 확인
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                isButtonPressed[0] = false;// 원상복귀
                timer.cancel();

                String answer = window.getInputAnswer(); // 시용자 입력값 가져오기
                // String answer = consoleReader.readLine();
                out.println(answer); // 답 보내기
                String scoreResponse = in.readLine();
                score = Integer.parseInt(scoreResponse); // 현재 점수 저장
            }
            String[] scores = in.readLine().split(";");
            int myScore = Integer.parseInt(scores[0]); // 나의 최종 점수 받아오기
            int oppScore = Integer.parseInt(scores[1]); // 상대방 최종 점수 받아오기
            window.setMyName(name); // 내 이름 넘기기
            window.setScore(myScore); // 내 점수 넘기기
            window.setOppScore(String.valueOf(oppScore)); // 상대방 점수 넘기기
            window.setOppName(oppName); // 상대방 이름 넘기기
            if (myScore > oppScore) { // 내가 이긴 경우
                window.setWinner(name);
                System.out.println(name + "님이 승리하였습니다.");
            } else if (myScore < oppScore) { // 상대방이 이긴 경우
                window.setWinner(oppName);
                System.out.println(oppName + "님이 승리하였습니다.");

            } else { // 비긴 경우
                window.setWinner("none");
                System.out.println("비겼습니다");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import Client.Category;

public class Server {
    static int clientNo = 0;
    static Category category = new Category();
    static Object lock = new Object(); // 락 객체

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("서버가 시작되었습니다.");
            HashMap<Integer, String[]> readyPlayer = new HashMap<>(); // 현재 게임시작을 대기중인 플레이어의 번호와 [닉네임, 분야]

            HashMap<String, Integer> scores = new HashMap<>(); // 플레이어들의 닉네임, 게임점수
            while (true) {
                Socket clientSocket = serverSocket.accept(); // 클라이언트 받기
                int currentClientNo = ++clientNo; // 새로운 클라이언트의 번호
                System.out.println(currentClientNo + "번 클라이언트가 접속했습니다.");

                Thread clientThread = new Thread(() -> { // 새 클라이언트에 새 쓰레드 할당
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        boolean gameOn = false; // game 중이면 true;
                        boolean startOn = false; // start 버튼을 클라이언트가 누르면 true;
                        boolean isReady =false; //둘 다 준비되었는지

                        String clientMessage = in.readLine(); // ;로 끊어서 닉네임과 분야 전송 받음
                        String[] msg = clientMessage.split(";"); // msg[0] = 닉네임 ; msg[1] = 분야
                        System.out.println(currentClientNo + " " + clientMessage);
                        String myName = msg[0];
                        String myArea = msg[1];
                        startOn = true; // 시작버튼을 누름
                        synchronized (readyPlayer) {
                            readyPlayer.put(currentClientNo, msg); // 자신의 번호와 분야를 대기 명단에 추가
                            System.out.println(readyPlayer.size());

                            System.out.println(currentClientNo + "번 클라이언트 선택 분야: " + clientMessage);

                            if (readyPlayer.get(currentClientNo) == null || readyPlayer.values().contains(null)) { // 두 개의 클라이언트가 준비되지 않은 경우
                                readyPlayer.wait(); // 대기 중인 클라이언트 스레드를 중지시킴
                            } else {
                                readyPlayer.notifyAll(); // 모든 대기 중인 클라이언트 스레드에게 알림을 보냄
                            }
                        }

/*
                        System.out.println(currentClientNo+"대기 끝");
                        for (Integer key : readyPlayer.keySet()) {
                            String[] value = readyPlayer.get(key);
                            System.out.println(currentClientNo+"Key: " + key);
                            System.out.println(currentClientNo+"Value: " + Arrays.toString(value));
                            System.out.println(currentClientNo+"-------------------------");
                        }
                        System.out.println(currentClientNo+"-------------------------");*/
                        // 게임 시작
                        // 0) 상대방 알아내기
                        int oppNum; // 상대방 클라이언트의 숫자
                        String oppName = ""; // 상대방 닉네임
                        String oppArea = ""; // 상대방이 선택한 분야
                        int score = 0; // 게임 점수

                        Set<Integer> keys = readyPlayer.keySet();
                        for (Integer key : keys) {
                            if (!key.equals(currentClientNo)) {// 클라이언트 번호가 내 번호가 아니면(상대방이면)
                                oppNum = key;
                                oppName = readyPlayer.get(key)[0];
                                oppArea = readyPlayer.get(key)[1];
                            }
                        }
                        
                        System.out.println(currentClientNo+"내꺼: "+myArea+" 상대꺼: "+oppArea);
                        // 1) 카테고리 합치기
                        
                        if (category.area.equals("")) { // 카테고리가 추가가 안됐으면
                            if (myArea.equals(oppArea)) { // 두 사람이 선택한 분야가 같으면
                                category.peopleList = category.getArea(myArea, 10);
                                // 10명 가져오기
                            } else {
                                // 5명씩 가져오기
                                category.peopleList.addAll(category.getArea(myArea, 5));
                                category.peopleList.addAll(category.getArea(oppArea, 5));
                            }
                        }
                        System.out.println(category.area);

                        // 2) 닉네임 서로 전송
                        out.println(oppName); // 닉네임 전송
                        String ok = in.readLine();
                        if (ok.equals("ok")) { // ok싸인이 제대로 왔으면
                            out.println("ok"); // ok 싸인 보내기
                        	// 3) 10문제 내기
                            for (int i = 0; i < 10; i++) {
                            	System.out.println((i+1)+"회차");
                                String pname = category.peopleList.get(i).name; // 이름(답)
                                String pphoto = category.peopleList.get(i).photo;// 사진
                                String problem = pname + ";" + pphoto;
                                out.println(problem); // 문제 보내기 (이름;사진)
//기다리기
                                /*조잡하지만 일단은 이렇게라도 해봄*/
                                String answer;
                                while (true) {
                                    synchronized (lock) {
                                        if (isReady) {
                                            answer = in.readLine(); // 답 받아오기
                                            break;
                                        }
                                    }
                                    try {
                                        Thread.sleep(500); // 0.5초 대기 후 다시 확인
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                
                                //answer = in.readLine(); // 답 받아오기
                                System.out.println(answer+"aaa");
                                if (answer.equals(pname)) { // 답이 맞으면
                                    score += 10; // 10점 더하기
                                    out.println(score); // 점수를 보내기

                                } else // 답이 틀리면
                                    out.println(score); // 점수를 더하지 않고 그대로 보내기
                             
                                
                            }// for문 끝(문제 끝)
                         //4) 내점수 입력
                            scores.put(myName, score); // 내 점수 입력
                            
                            System.out.println(currentClientNo + "번 "+scores.size());
                         //5) 상대방 끝날 때까지 대기
                            synchronized (lock) {
                                while (scores.size() < 2) {
                                    lock.wait(); // 상대방도 점수를 입력할 때까지 대기
                                    System.out.println(currentClientNo + "번 qq " + scores.size());
                                }

                                int oppScore = scores.get(oppName); // 상대방 점수
                                out.println(score + ";" + oppScore);
                                lock.notifyAll(); // 대기 중인 스레드를 깨움
                            }

                        }
                        //6) 통신 종료
                        clientSocket.close();
                        System.out.println(currentClientNo + "번 클라이언트와의 연결이 종료되었습니다.");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                synchronized (lock) {
                    readyPlayer.put(currentClientNo, null);
                    lock.notifyAll(); // 대기 중인 클라이언트 스레드를 깨움
                }

                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

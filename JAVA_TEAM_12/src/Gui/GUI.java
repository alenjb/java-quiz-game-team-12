package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DropMode;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class GUI {

	private JFrame frame;
	private JTextField inputNickname;
	private JTextField txtGameStartInfo;
	private JTextField inputAnswer;
	private JLabel lblMatchViewBackGround;
	private JLabel lblVS;
	private JLabel lblUserName1;
	private JLabel lblUserName2;
	private JLabel lblCounterUserName1;
	private JLabel lblCounterUserName2;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private Timer timer;
	private String comboBox;
	
	JLabel lblGameImage;
	JLabel lblRound;
	JLabel lblScore;
	JLabel lblUserResult;
	JLabel lblCounterUserResult;
	JLabel lblWinerName;
	JLabel lblNewLabel_2_1;
	private int buttonCount=0; //버튼을 볓번 눌렀나
	private String gameImage="/image/sonny.jpg"; //게임 이미지 주소
	private int roundNo=1; //현재 라운드 정보
	private int score=0; //현재 점수
	private String myName=""; //자신의 닉네임
	private String oppName=""; //상대방 닉네임
	private String winner =""; //승자
	private String oppScore=""; //상대방 점수
	private boolean buttonClick =false; //시작버튼이 눌렸는지 체크
	public boolean isReady =false; //client에서 매칭화면으로 넘어가도 되는지 신호를 받아서 주는 것
//	public int answNum = 0; //몇번 정답 버튼을 눌렀는지 체크
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 729, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 카드 레이아웃 생성
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		JLabel lblTime = new JLabel("10 초");

		// 1. 시작화면
		JPanel startView = new JPanel();
		cardPanel.setLayout(cardLayout);
		startView.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(startView, BorderLayout.CENTER);
		startView.setLayout(null);

		JLabel lblFaceImage = new JLabel("");
		lblFaceImage.setIcon(new ImageIcon(GUI.class.getResource("/image/face.png")));
		lblFaceImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaceImage.setBounds(214, 93, 285, 187);
		startView.add(lblFaceImage);

		JLabel lblGameCategory = new JLabel("게임 분야");
		lblGameCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameCategory.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblGameCategory.setBounds(304, 419, 87, 31);
		startView.add(lblGameCategory);

		JButton btnGameStart = new JButton("게임 시작");
		btnGameStart.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnGameStart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnGameStart.setBounds(570, 419, 113, 35);

		startView.add(btnGameStart);

		inputNickname = new JTextField();
		inputNickname.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		inputNickname.setBounds(138, 419, 134, 31);
		startView.add(inputNickname);
		inputNickname.setColumns(10);

		JLabel lblNickname = new JLabel("닉네임");
		lblNickname.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNickname.setHorizontalAlignment(SwingConstants.CENTER);
		lblNickname.setBounds(36, 419, 97, 31);
		startView.add(lblNickname);
		
		JComboBox comboBoxGameCategory = new JComboBox();
		comboBoxGameCategory.setBackground(Color.WHITE);
		comboBoxGameCategory.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboBoxGameCategory.setModel(new DefaultComboBoxModel(new String[] { "스포츠 선수", "배우", "가수", "정치인" }));
		comboBoxGameCategory.setToolTipText("");
		comboBoxGameCategory.setBounds(390, 419, 120, 31);
		startView.add(comboBoxGameCategory);
		


		JLabel lblGameInfo = new JLabel("인물퀴즈게임");
		lblGameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		lblGameInfo.setBounds(252, 178, 208, 102);
		startView.add(lblGameInfo);

		JLabel lblStartViewBackGround = new JLabel("");
		lblStartViewBackGround.setIcon(new ImageIcon(GUI.class.getResource("/image/sketch.png")));
		lblStartViewBackGround.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartViewBackGround.setBounds(0, 0, 713, 518);
		startView.add(lblStartViewBackGround);
		


		// 2. 매칭화면
		JPanel matchView = new JPanel();
		frame.getContentPane().add(matchView, BorderLayout.CENTER);
		matchView.setLayout(null);

		lblUserName1 = new JLabel("사용자이름");
		lblUserName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName1.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblUserName1.setBackground(Color.BLACK);
		lblUserName1.setBounds(12, 233, 244, 33);
		matchView.add(lblUserName1);

		lblVS = new JLabel("V S");
		lblVS.setHorizontalAlignment(SwingConstants.CENTER);
		lblVS.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblVS.setBackground(new Color(0, 0, 0));
		lblVS.setBounds(329, 237, 55, 23);
		matchView.add(lblVS);

		lblCounterUserName1 = new JLabel("상대사용자이름");
		lblCounterUserName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounterUserName1.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblCounterUserName1.setBackground(Color.BLACK);
		lblCounterUserName1.setBounds(461, 233, 240, 33);
		matchView.add(lblCounterUserName1);

		txtGameStartInfo = new JTextField();
		txtGameStartInfo.setBackground(new Color(250, 235, 215));
		txtGameStartInfo.setForeground(new Color(0, 0, 0));
		txtGameStartInfo.setEditable(false);
		txtGameStartInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameStartInfo.setText("잠시 후 게임이 시작됩니다. ");
		txtGameStartInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		txtGameStartInfo.setBounds(152, 422, 409, 33);
		matchView.add(txtGameStartInfo);
		txtGameStartInfo.setColumns(10);

		lblMatchViewBackGround = new JLabel("");
		lblMatchViewBackGround.setIcon(new ImageIcon(GUI.class.getResource("/image/sketch.png")));
		lblMatchViewBackGround.setBounds(0, 0, 713, 518);
		matchView.add(lblMatchViewBackGround);
		
		// 3. 게임화면
		JPanel gameView = new JPanel();
		frame.getContentPane().add(gameView, BorderLayout.CENTER);
		gameView.setLayout(null);
		


		lblGameImage = new JLabel("");
		lblGameImage.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblGameImage.setIcon(new ImageIcon(GUI.class.getResource(gameImage)));
		lblGameImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameImage.setForeground(new Color(0, 0, 0));
		lblGameImage.setBounds(203, 55, 306, 327);
		gameView.add(lblGameImage);

		JButton btnAnswer = new JButton("정답");
		btnAnswer.setBackground(new Color(255, 153, 102));
		btnAnswer.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnAnswer.setBounds(313, 452, 86, 39);
		gameView.add(btnAnswer);

		JLabel lblRoundLabel = new JLabel("현재 라운드");
		lblRoundLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		lblRoundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoundLabel.setBounds(32, 95, 121, 40);
		gameView.add(lblRoundLabel);

		inputAnswer = new JTextField();
		inputAnswer.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		inputAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		inputAnswer.setBounds(203, 403, 306, 39);
		gameView.add(inputAnswer);
		inputAnswer.setColumns(10);
		
		lblRound = new JLabel(roundNo + " / 10");
		lblRound.setHorizontalAlignment(SwingConstants.CENTER);
		lblRound.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		lblRound.setBounds(32, 145, 121, 40);
		gameView.add(lblRound);

		JLabel lblTimeLable = new JLabel("남은 시간");
		lblTimeLable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeLable.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		lblTimeLable.setBounds(32, 230, 121, 40);
		gameView.add(lblTimeLable);

//		JLabel lblTime = new JLabel(" 초");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		lblTime.setBounds(32, 280, 121, 40);
		gameView.add(lblTime);

		JLabel lblScoreLabel = new JLabel("현재 점수");
		lblScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		lblScoreLabel.setBounds(560, 129, 121, 40);
		gameView.add(lblScoreLabel);

		lblScore = new JLabel(score+"");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		lblScore.setBounds(560, 204, 121, 40);
		gameView.add(lblScore);

		JLabel lblGameViewBackGround = new JLabel("");
		lblGameViewBackGround.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblGameViewBackGround.setIcon(new ImageIcon(GUI.class.getResource("/image/sketch.png")));
		lblGameViewBackGround.setBounds(0, 0, 713, 518);
		gameView.add(lblGameViewBackGround);

		
		
		
		// 4.종료화면
		JPanel endView = new JPanel();
		frame.getContentPane().add(endView, BorderLayout.CENTER);
		endView.setLayout(null);
		buttonClick = false; //버튼 안눌린 것으로 초기화


		JLabel lblNewLabel = new JLabel("\t님");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(213, 155, 28, 22);
		endView.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\t점");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(474, 155, 28, 22);
		endView.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\t점");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(474, 260, 28, 22);
		endView.add(lblNewLabel_1_1);
		
		lblNewLabel_2_1 = new JLabel("\t님이 승리하셨습니다.");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(328, 374, 205, 22);
		endView.add(lblNewLabel_2_1);			
			
		lblWinerName = new JLabel("승리자");
		lblWinerName.setHorizontalAlignment(SwingConstants.LEFT);
		lblWinerName.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblWinerName.setBounds(210, 374, 189, 22);
		
		endView.add(lblWinerName);
		

		JButton btnExitButton = new JButton("게임 종료");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitButton.setBackground(new Color(153, 153, 153));
		btnExitButton.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		btnExitButton.setBounds(269, 445, 174, 39);
		endView.add(btnExitButton);

		JLabel lblNewLabel_2 = new JLabel("\t님");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(213, 260, 28, 22);
		endView.add(lblNewLabel_2);

		lblCounterUserName2 = new JLabel(oppName);
		lblCounterUserName2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCounterUserName2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblCounterUserName2.setBounds(12, 155, 189, 22);
		endView.add(lblCounterUserName2);

		lblUserName2 = new JLabel("이름");
		lblUserName2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblUserName2.setBounds(12, 260, 189, 22);
		endView.add(lblUserName2);

		lblCounterUserResult = new JLabel(oppScore+"");
		lblCounterUserResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCounterUserResult.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblCounterUserResult.setBounds(420, 155, 53, 22);
		endView.add(lblCounterUserResult);

		lblUserResult = new JLabel(score+"");
		lblUserResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserResult.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblUserResult.setBounds(420, 260, 53, 22);
		endView.add(lblUserResult);


		JLabel lblGameResultLabel = new JLabel("게임 결과");
		lblGameResultLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 33));
		lblGameResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameResultLabel.setBounds(257, 41, 198, 72);
		endView.add(lblGameResultLabel);

		JLabel lblEndViewBackGround = new JLabel("");
		lblEndViewBackGround.setIcon(new ImageIcon(GUI.class.getResource("/image/sketch.png")));
		lblEndViewBackGround.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndViewBackGround.setBounds(0, 0, 713, 518);
		endView.add(lblEndViewBackGround);

		cardPanel.add(startView, "start");
		cardPanel.add(matchView, "match");
		cardPanel.add(gameView, "game");
		cardPanel.add(endView, "end");
		frame.getContentPane().add(cardPanel);
		// 초기에 보여줄 패널 설정
		cardLayout.show(cardPanel, "start");
		
		
		btnGameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox= comboBoxGameCategory.getSelectedItem().toString();
				// 다음 패널로 이동
				buttonClick= true; //시작 버튼 눌림
				cardLayout.next(cardPanel);
	            while (!isReady) {
                    try {
                        Thread.sleep(500); // 일정 시간 대기 후 다시 확인
                    } catch (InterruptedException ee) {
                        ee.printStackTrace();
                    }
	            }
	            /*
	            timer = new Timer(2000, new ActionListener() {
	            	@Override
	            	public void actionPerformed(ActionEvent e) {
	            		cardLayout.show(cardPanel, "game");
	            		timer.stop();
	            	}
	            });*/
	            updateGamePanel(gameView);
	            cardLayout.next(cardPanel);
//	            buttonClick = false; //버튼 안눌린 것으로 초기화
				// 2초 후에 세 번째 패널로 이동
				
				//timer.start();
			}
		});
		
		btnAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("정답버튼을 누름");
				// 다음 패널로 이동
				if(buttonCount ==9) {
					buttonCount++;
					System.out.println("업데이트");
					System.out.println(winner);
	                //데이터가 다 오면 넘어가기
					while (true) {
	                    // 버튼 상태 확인 
	                    if (!winner.equals("")) {
	                    	break;
	                    } else {
	                        try {
	                            Thread.sleep(100); // 일정 시간 대기 후 다시 확인
	                        } catch (InterruptedException ea) {
	                            ea.printStackTrace();
	                        }
	                    }
	                }
					updateEndPanel(endView);
					System.out.println(winner);
					cardLayout.next(cardPanel);					
				}
				buttonCount++;
                try {
                    Thread.sleep(100); // 일정 시간 대기 후 다시 확인
                } catch (InterruptedException eeee) {
                    eeee.printStackTrace();
                }
				updateGamePanel(gameView);

			}
		});


	}
	//게임 패널 내용 업데이트
	public void updateGamePanel(JPanel gameView) {
		lblGameImage.setIcon(new ImageIcon(GUI.class.getResource(gameImage))); 
		lblRound.setText(roundNo + " / 10");
		lblScore.setText(score+"");
		
	}
	//마지막 패널 내용 업데이트
	public void updateEndPanel(JPanel EndView) {
		lblCounterUserName2.setText(oppName);
		lblUserName2.setText(getMyName());
		lblUserResult.setText(score+"");
		lblCounterUserResult.setText(oppScore+"");
		lblWinerName.setText(winner);
		if(!winner.equals("none")) {
			lblWinerName.setText(winner);
						
		}else {// 비긴 경우
			lblWinerName.setText(myName+"\t\t님과 "+oppName);			
			lblNewLabel_2_1.setText("님이 비겼습니다.");
		}
		
		
	}
	
	public JTextField getInputNickname() {
		return inputNickname;
	}

	public void setInputNickname(JTextField inputNickname) {
		this.inputNickname = inputNickname;
	}

	public JTextField getTxtGameStartInfo() {
		return txtGameStartInfo;
	}

	public void setTxtGameStartInfo(JTextField txtGameStartInfo) {
		this.txtGameStartInfo = txtGameStartInfo;
	}

	public String getInputAnswer() {
		
		return inputAnswer.getText();
	}

	public void setInputAnswer(JTextField inputAnswer) {
		this.inputAnswer = inputAnswer;
	}

	public JLabel getLblMatchViewBackGround() {
		return lblMatchViewBackGround;
	}

	public void setLblMatchViewBackGround(JLabel lblMatchViewBackGround) {
		this.lblMatchViewBackGround = lblMatchViewBackGround;
	}

	public JLabel getLblVS() {
		return lblVS;
	}

	public void setLblVS(JLabel lblVS) {
		this.lblVS = lblVS;
	}

	public JLabel getLblUserName() {
		return lblUserName1;
	}

	public void setLblUserName(String lblUserName1) {
		JLabel label = new JLabel(lblUserName1);
		this.lblUserName1 = label;
	}

	public JLabel getLblCounterUserName() {
		return lblCounterUserName2;
	}

	public void setLblCounterUserName(String lblCounterUserName) {
		JLabel label = new JLabel(lblCounterUserName);
		this.lblUserName1 = label;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getCardPanel() {
		return cardPanel;
	}

	public void setCardPanel(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setComboBox(String comboBox) {
		this.comboBox = comboBox;
	}
	public String getComboBox() {
		return this.comboBox;
	}
	public int getButtonCount() {
		return buttonCount;
	}

	public void setButtonCount(int buttonCount) {
		this.buttonCount = buttonCount;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public void setLblUserName(JLabel lblUserName1) {
		this.lblUserName1 = lblUserName1;
	}

	public void setLblCounterUserName(JLabel lblCounterUserName) {
		this.lblCounterUserName2 = lblCounterUserName;
	}

	public int getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
		System.out.println("바뀜"+this.myName);
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

	public String getOppScore() {
		return oppScore;
	}

	public void setOppScore(String oppScore) {
		this.oppScore = oppScore;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public boolean isButtonClick() {
		return buttonClick;
	}

	public void setButtonClick(boolean buttonClick) {
		this.buttonClick = buttonClick;
	}
/*
	public boolean isAnswBtn() {
		return answBtn;
	}

	public void setAnswBtn(boolean answBtn) {
		this.answBtn = answBtn;
	}
*/	
	
	
}

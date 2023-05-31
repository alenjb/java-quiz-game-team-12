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
	private JLabel lblUserName;
	private JLabel lblCounterUserName;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private Timer timer;
	

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

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		btnGameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 다음 패널로 이동
				cardLayout.next(cardPanel);

				// 2초 후에 세 번째 패널로 이동
				timer = new Timer(2000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(cardPanel, "game");
						timer.stop();
					}
				});
				timer.start();
			}
		});
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
		comboBoxGameCategory.setModel(new DefaultComboBoxModel(new String[] { "스포츠", "배우", "가수", "정치인" }));
		comboBoxGameCategory.setToolTipText("");
		comboBoxGameCategory.setBounds(402, 419, 97, 31);
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

		lblUserName = new JLabel("사용자이름");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblUserName.setBackground(Color.BLACK);
		lblUserName.setBounds(12, 233, 244, 33);
		matchView.add(lblUserName);

		lblVS = new JLabel("V S");
		lblVS.setHorizontalAlignment(SwingConstants.CENTER);
		lblVS.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblVS.setBackground(new Color(0, 0, 0));
		lblVS.setBounds(329, 237, 55, 23);
		matchView.add(lblVS);

		lblCounterUserName = new JLabel("상대사용자이름");
		lblCounterUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounterUserName.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblCounterUserName.setBackground(Color.BLACK);
		lblCounterUserName.setBounds(461, 233, 240, 33);
		matchView.add(lblCounterUserName);

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

		JLabel lblGameImage = new JLabel("");
		lblGameImage.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblGameImage.setIcon(new ImageIcon(GUI.class.getResource("/image/sonny.jpg")));
		lblGameImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameImage.setForeground(new Color(0, 0, 0));
		lblGameImage.setBounds(203, 55, 306, 327);
		gameView.add(lblGameImage);

		JButton btnAnswer = new JButton("정답");
		btnAnswer.setBackground(new Color(255, 153, 102));
		btnAnswer.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 다음 패널로 이동
				cardLayout.next(cardPanel);
			}
		});
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

		JLabel lblRound = new JLabel("15 / 20");
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

		JLabel lblScore = new JLabel("80");
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

		JLabel lblNewLabel_2_1 = new JLabel("\t님이 승리하셨습니다.");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(328, 374, 205, 22);
		endView.add(lblNewLabel_2_1);

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

		JLabel lblCounterUserName = new JLabel("상대 사용자");
		lblCounterUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCounterUserName.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblCounterUserName.setBounds(12, 155, 189, 22);
		endView.add(lblCounterUserName);

		JLabel lblUserName = new JLabel("사용자");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblUserName.setBounds(12, 260, 189, 22);
		endView.add(lblUserName);

		JLabel lblCounterUserResult = new JLabel("25");
		lblCounterUserResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCounterUserResult.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblCounterUserResult.setBounds(420, 155, 53, 22);
		endView.add(lblCounterUserResult);

		JLabel lblUserResult = new JLabel("25");
		lblUserResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserResult.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblUserResult.setBounds(420, 260, 53, 22);
		endView.add(lblUserResult);

		JLabel lblWinerName = new JLabel("사용자");
		lblWinerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWinerName.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblWinerName.setBounds(63, 374, 248, 22);
		endView.add(lblWinerName);

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

		
	}
	
}


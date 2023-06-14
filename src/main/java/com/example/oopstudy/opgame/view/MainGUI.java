package com.example.oopstudy.opgame.view;
import com.example.oopstudy.opgame.service.OpGame;
import com.example.oopstudy.opgame.service.PlusOperatorLevelOne;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    // 캐릭터 이미지를 저장하기 위한 Map 객체
    private Map<String, ImageIcon> charImages = new HashMap<>();
    // 키패드 버튼을 저장하기 위한 Map 객체
    private Map<String, JButton> keyButtons = new HashMap<>();
    // 각각 문제, 답, 캐릭터 이미지, 격려 문구를 표시하기 위한 JLabel 객체
    private JLabel lblQuestion, lblAnswer, lblChar, lblCherringUp;
    // OpGame 객체를 저장하고 OpGame은 게임의 로직을 담당하는 클래스
    private OpGame game;

    // 창의 크기를 설정하고 레이아웃을 지정
    public MainGUI() {
        // 격려 캐릭터 이미지 준비 (로드)
        loadCharImages();

        // 프레임(윈도우) 창 크기 고정
        setSize(new Dimension(300, 500));
        setResizable(false);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // 문제와 답
        JPanel panelQuestion = new JPanel();
        panelQuestion.setPreferredSize(new Dimension(200, 40));
        panelQuestion.setSize(new Dimension(200, 80));
        getContentPane().add(panelQuestion);

        lblQuestion = new JLabel("1 + 1 = ");
        lblQuestion.setFont(new Font("굴림", Font.PLAIN, 24));
        panelQuestion.add(lblQuestion);

        lblAnswer = new JLabel("?");
        lblAnswer.setFont(new Font("굴림", Font.PLAIN, 24));
        panelQuestion.add(lblAnswer);

        // 격려 캐릭터
        lblChar = new JLabel("");
        lblChar.setPreferredSize(new Dimension(200, 100));
        getContentPane().add(lblChar);

        changeCharImage("normal");

        // 격려 문구
        lblCherringUp = new JLabel("참 잘했어요~");
        lblCherringUp.setFont(new Font("굴림", Font.PLAIN, 22));
        lblCherringUp.setHorizontalAlignment(SwingConstants.CENTER);
        lblCherringUp.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCherringUp.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(lblCherringUp);

        // 키패드 초기화
        initKeypadButtons();

        // 프레임(윈도우) 보이기
        setVisible(true);

        // 게임 레벨 초기화와 문제 출제
        game = new OpGame(new PlusOperatorLevelOne());
        nextQuestion();
    }
    // 격려 캐릭터 이미지 변경
    // @param key 변경할 캐릭터 이름 : "normal"|"success"|"fail
    private void changeCharImage(String key) {
        lblChar.setIcon(charImages.get(key));
    }
    // 격려 캐릭터 이미지 준비 (로드) 하고 크기를 조정하여 charImages 맵에 저장
    private void loadCharImages() {
//        String[][] imgResources = {
//                {"normal", "src/main/resources/images/character_men.gif"},
//                {"success", "src/main/resources/images/character_men_a.gif"},
//                {"fail", "src/main/resources/images/character_men_b.gif"}
//        };
//        for(int i = 0; i < imgResources.length; i++) {
//            //ImageIcon 객체 생성
//            ImageIcon originIcon = new ImageIcon(MainGUI.class.getResource(imgResources[i][1]));
//            // ImageIcon에서 Image를 추출
//            Image originImg = originIcon.getImage();
//            // 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
//            Image changedImg = originImg.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
//            // 새로운 Image로 ImageIcon 객체를 생성
//            ImageIcon icon = new ImageIcon(changedImg);
//            charImages.put(imgResources[i][0], icon);
//        }
        String[][] imgResources = {
                {"normal", "/images/character_men.gif"},
                {"success", "/images/character_men_a.gif"},
                {"fail", "/images/character_men_b.gif"}
        };
        for (int i = 0; i < imgResources.length; i++) {
            try (InputStream stream = getClass().getResourceAsStream(imgResources[i][1])) {
                if (stream != null) {
                    // InputStream에서 Image로 변환
                    Image originImg = ImageIO.read(stream);
                    // 변환된 Image 크기 조정
                    Image changedImg = originImg.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                    // 새로운 Image로 ImageIcon 객체 생성
                    ImageIcon icon = new ImageIcon(changedImg);
                    charImages.put(imgResources[i][0], icon);
                } else {
                    System.err.println("Failed to load image: " + imgResources[i][1]);
                }
            } catch (IOException e) {
                System.err.println("Failed to load image: " + imgResources[i][1]);
                e.printStackTrace();
            }
        }
    }
    // 키패드 초기화 하고 이벤트 리스너 등록
    private void initKeypadButtons() {
        MouseAdapter clickLstener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton btn = (JButton)e.getSource();
                String btnNum = btn.getActionCommand();
                lblAnswer.setText(btnNum);
                if(game.isAnswer(Integer.parseInt(btnNum))) {
                    lblCherringUp.setText("참 잘했어요");
                    changeCharImage("success");
                    nextQuestion();
                } else {
                    lblCherringUp.setText(game.getCheeringUpMsg());
                    changeCharImage("fail");
                }
            }
        };

        for(int i = 0; i < 10; i++) {
            String bunNum = i + "";
            JButton button = new JButton(bunNum);
            button.setPreferredSize(new Dimension(80, 60));
            button.addMouseListener(clickLstener);
            button.setActionCommand(bunNum);
            keyButtons.put(bunNum, button);
        }

        JPanel panelKeypad = new JPanel();
        getContentPane().add(panelKeypad);
        panelKeypad.setLayout(new GridLayout(4, 3, 5, 5));

        panelKeypad.add(keyButtons.get("1"));
        panelKeypad.add(keyButtons.get("2"));
        panelKeypad.add(keyButtons.get("3"));
        panelKeypad.add(keyButtons.get("4"));
        panelKeypad.add(keyButtons.get("5"));
        panelKeypad.add(keyButtons.get("6"));
        panelKeypad.add(keyButtons.get("7"));
        panelKeypad.add(keyButtons.get("8"));
        panelKeypad.add(keyButtons.get("9"));
        panelKeypad.add(new JButton("메뉴"));
        panelKeypad.add(keyButtons.get("0"));
        panelKeypad.add(new JButton("다음"));
    }
    // 다음 문제를 생성하고 해당 문제를 lblQuestion에 표시
    private void nextQuestion() {
        game.makeQuestion(10);
        String question = game.getQuestion();
        lblQuestion.setText(question);
        lblAnswer.setText("?");
    }

    // MainGUI 객체를 생성하여 게임 시작
    public static void main(String[] args) {
        new MainGUI();
    }
}

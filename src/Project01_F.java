import javax.swing.*;
import java.awt.*;

public class Project01_F {
    // 위도와 경도를 추출하여 지도를 보여주는 프로그래밍 GUI Version
    JTextField address;
    JLabel resAddress, resX, resY, jibunAddress;
    JLabel imageLabel;

    public static void main(String[] args) {
        new Project01_F().initGUI();
    }

    public void initGUI(){
        JFrame frm = new JFrame("Map View");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x자 버튼 누르면 종료되게
        Container c = frm.getContentPane(); // 프레임 영역
        imageLabel = new JLabel("지도보기");
        JPanel pan = new JPanel();
        JLabel addressLbl = new JLabel("주소입력");
        address = new JTextField(50);
        JButton btn = new JButton("클릭");
        pan.add(addressLbl);
        pan.add(address);
        pan.add(btn);
        btn.addActionListener(new NaverMap(this)); // 버튼이 눌렸을 때 처리되는 네이버 맵!

        JPanel pan1 = new JPanel(); // 하단 ~
        pan1.setLayout(new GridLayout(4,1));
        resAddress = new JLabel("도로명");
        jibunAddress = new JLabel("지번주소");
        resX = new JLabel("경도");
        resY = new JLabel("위도");
        pan1.add(resAddress);
        pan1.add(jibunAddress);
        pan1.add(resX);
        pan1.add(resY);
        c.add(BorderLayout.NORTH, pan);
        c.add(BorderLayout.CENTER, imageLabel);
        c.add(BorderLayout.SOUTH, pan1);
        frm.setSize(730,660);
        frm.setVisible(true);
    }

}

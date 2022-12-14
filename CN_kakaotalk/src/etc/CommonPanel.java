package etc;

import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CommonPanel extends JPanel {

  protected JLabel logoLabel;

  //로고사진 이용
  protected Image logoImg = UsingImage.getImage("src/Image/label.png");

  //배경색 설정
  protected CommonPanel() {

    setLayout(null);

  }

}

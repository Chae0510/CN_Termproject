package etc;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class UserBtn extends JButton{

  public UserBtn(ImageIcon imgIcon) {
    setIcon(imgIcon);
    setFont(new Font("굴림", Font.BOLD, 18));


    setHorizontalAlignment(SwingConstants.LEFT);
    setFocusPainted(false);

    Border emptyBorder = BorderFactory.createEmptyBorder();
    setBorder(emptyBorder);

  }
}


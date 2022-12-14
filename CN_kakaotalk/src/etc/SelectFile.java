package etc;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectFile {
  public static File showFile() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter =
            new FileNameExtensionFilter("png");
    chooser.setFileFilter(filter);
    int ret = chooser.showOpenDialog(null);
    if (ret != JFileChooser.APPROVE_OPTION) {
      JOptionPane.showMessageDialog(null, "File is not selected", "Error!", JOptionPane.WARNING_MESSAGE);
    }
    File file = chooser.getSelectedFile();

    return file;
  }

}

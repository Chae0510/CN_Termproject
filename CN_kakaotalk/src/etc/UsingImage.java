package etc;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class UsingImage {
  public static Image img = null;

  public static Image getImage(String FilePath) {

    try {
      File sourceImage = new File(FilePath);
      img = ImageIO.read(sourceImage);
      return img;
    } catch (Exception e) {
      System.out.println("Image file does not exist.");
      return null;
    }
  }
}

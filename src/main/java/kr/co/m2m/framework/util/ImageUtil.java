package kr.co.m2m.framework.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {

  /**
   * 이미지 리사이즈
   */
  public static void resizeImage(File imageFile, String imageType, int width, int height)
      throws Exception {
    BufferedImage orgImg = ImageIO.read(imageFile);
    BufferedImage resizeImage = createResized(orgImg, width, height, true);
    ImageIO.write(resizeImage, imageType, imageFile);
  }

  private static BufferedImage createResized(Image org_img, int width, int height, boolean alpha)
      throws Exception {
    int imgType = alpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    BufferedImage scale = new BufferedImage(width, height, imgType);
    Graphics2D g = scale.createGraphics();

    if (alpha) {
      g.setComposite(AlphaComposite.Src);
    }

    g.drawImage(org_img, 0, 0, width, height, null);
    g.dispose();

    return scale;
  }

}

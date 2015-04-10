package daniel.view.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class ImageConvertor
{
    /**
     * change ImageIcon to BufferedImage
     *
     * @param icon
     * @return
     */
    public static BufferedImage getBufferedImage(ImageIcon icon)
    {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        ImageObserver observer = icon.getImageObserver();
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics gc = bufferedImage.createGraphics();
        gc.drawImage(icon.getImage(), 0, 0, observer);
        return bufferedImage;
    }

    /**
     * change BufferedImage to ImageData
     *
     * @param bufferedImage
     * @return
     */
    public static ImageData getImageData(BufferedImage bufferedImage)
    {
        DirectColorModel colorModel = (DirectColorModel) bufferedImage
                .getColorModel();
        PaletteData palette = new PaletteData(colorModel.getRedMask(),
                colorModel.getGreenMask(), colorModel.getBlueMask());
        ImageData data = new ImageData(bufferedImage.getWidth(),
                bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
        WritableRaster raster = bufferedImage.getRaster();
        int[] pixelArray = new int[3];
        for (int y = 0; y < data.height; y++) {
            for (int x = 0; x < data.width; x++) {
                raster.getPixel(x, y, pixelArray);
                int pixel = palette.getPixel(new RGB(pixelArray[0],
                        pixelArray[1], pixelArray[2]));
                data.setPixel(x, y, pixel);
            }
        }
        return data;
    }

    /**
     * convert javax.swing.ImageIcon to org.eclipse.swt.graphics.Image
     *
     * @param imageIcon
     * @return
     */
    public static ImageData convertImageIconToImageData(ImageIcon imageIcon)
    {
        BufferedImage bufferedImage = getBufferedImage(imageIcon);
        return getImageData(bufferedImage);
    }

}

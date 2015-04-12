package daniel.controller;

import daniel.view.util.ImageConvertor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by daniel chiu on 2015/4/8.
 */
public class IconDetect
{

    /**
     * 获得该文件/文件夹的本地小图标
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static ImageIcon getSmallIcon(File file) throws FileNotFoundException
    {
        if (!file.exists())
            throw new FileNotFoundException("文件夹" + file + "没有找到");
        FileSystemView view = FileSystemView.getFileSystemView();
        return (ImageIcon) view.getSystemIcon(file);
    }

    /**
     * 获得该文件/文件夹的本地大图标
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static ImageIcon getBigIcon(File file) throws FileNotFoundException
    {
        ShellFolder shellFolder = ShellFolder.getShellFolder(file);
        return new ImageIcon(shellFolder.getIcon(true));
    }

    /**
     * 为file对象找到操作系统本地的文件小图片并转换成 org.eclipse.swt.graphics.Image 对象
     * <p>
     * 不推荐使用，因为这样获得的图标四周有黑边
     *
     * @param display
     * @param file
     * @return
     */
    @Deprecated
    public static Image getSmallLocalFileImage(Display display, File file)
    {
        Image image = null;
        try {
            ImageIcon imageIcon = IconDetect.getSmallIcon(file);
            ImageData imageData = ImageConvertor.convertImageIconToImageData(imageIcon);
            image = new Image(display, imageData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 为file对象找到操作系统本地的文件大图片并转换成 org.eclipse.swt.graphics.Image 对象
     * <p>
     * 不推荐使用，因为这样获得的图标四周有黑边
     *
     * @param display
     * @param file
     * @return
     */
    @Deprecated
    public static Image getBigLocalFileImage(Display display, File file)
    {
        Image image = null;
        try {
            ImageIcon imageIcon = IconDetect.getBigIcon(file);
            ImageData imageData = ImageConvertor.convertImageIconToImageData(imageIcon);
            image = new Image(display, imageData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 将本地系统的文件图标获取并保存在本地
     *
     * @param localFile  需要获取图标的文件（写全路径和文件名）
     * @param outputIcon 获得之后的图标（写全路径和文件名）
     * @param bigIcon    是否保存文件的大图标
     */
    public static void saveLocalFileIconToDisk(File localFile, File outputIcon, boolean bigIcon)
    {
        try {
            ImageIcon imageIcon = null;
            if (bigIcon)
                imageIcon = IconDetect.getBigIcon(localFile);
            else imageIcon = IconDetect.getSmallIcon(localFile);

            try {
                BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
                g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
                int alpha = 0;
                for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                    for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                        int rgb = bufferedImage.getRGB(j2, j1);

                        int R = (rgb & 0xff0000) >> 16;
                        int G = (rgb & 0xff00) >> 8;
                        int B = (rgb & 0xff);
                        if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                            rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                        }
                        bufferedImage.setRGB(j2, j1, rgb);
                    }
                }

                g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
                ImageIO.write(bufferedImage, "png", outputIcon);

            } catch (Exception e) {
            } finally {

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将Swing的图标转换成SWT需要的图标，彻底解决图标四周黑框还有颜色不正的情况
     *
     * @param display
     * @param file
     * @return
     */
    public static Image getSWTImageFromSwing(Display display, File file)
    {
        //得到文件图标
        ImageIcon imageIcon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file);

        if (imageIcon.getImage() instanceof BufferedImage) {
            BufferedImage bufferedImage = (BufferedImage) imageIcon.getImage();
            DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
            PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(), colorModel.getBlueMask());
            ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
            //设置每个像素点的颜色与Alpha值
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int rgb = bufferedImage.getRGB(x, y);
                    int pixel = palette.getPixel(new RGB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF));
                    data.setPixel(x, y, pixel);
                    if (colorModel.hasAlpha()) {
                        data.setAlpha(x, y, (rgb >> 24) & 0xFF);
                    }
                }
            }
            // 生成Image对象
            Image swtImage = new Image(display, data);

            return swtImage;
        } else return null;
    }
}
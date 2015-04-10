package daniel.controller;

import daniel.view.util.ImageConvertor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import sun.awt.shell.ShellFolder;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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
     *
     * @param display
     * @param file
     * @return
     */
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
     *
     * @param display
     * @param file
     * @return
     */
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
}

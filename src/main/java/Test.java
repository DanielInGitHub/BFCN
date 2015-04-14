import daniel.Business.ChangeFile;
import daniel.controller.DiskDetect;
import daniel.controller.IconDetect;
import daniel.exception.NeedFolderException;
import daniel.view.util.ImageConvertor;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/9.
 */
public class Test
{
    public static void main(String[] args) throws IOException
    {
        /*1*/
//        System.out.println(System.getProperty("user.home"));
//        String string = System.getProperty("user.home");
//        String[] strings = string.split(":");
//        System.out.println(strings[0]);
        /*2*/
//        File file = new File("c:/Windows");
//        for (File file1 : file.listFiles())
//            System.out.println(file1);
        /*3*/
//        File file = new File("D:/迅雷下载");
//        List<File> files = DiskDetect.getChildFiles(file);
//        for (File file1 : files) {
//            String s = null;
//            try {
//                s = DiskDetect.getFileExtensionName(file1);
//            } catch (NeedFolderException e) {
//                e.printStackTrace();
//            }
//            System.out.println(s);
//            System.out.println(file1.getName());
//        }
        /*4*/
//        ImageIcon imageIcon = IconDetect.getSmallIcon(new File("d:/"));
//        BufferedImage bufferedImage = ImageConvertor.getBufferedImage(imageIcon);
//        BufferedImage bufferedImage2 = ImageConvertor.transferAlpha(imageIcon);
//        System.out.println(bufferedImage);
//        System.out.println(bufferedImage2);
        /*5*/
//        File file = new File("D:\\测试\\坚信 - 副本 (22)测试.txt");
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalPath());
//        System.out.println(file.getPath());
//        System.out.println(file.getParent());
//        List<File> files = DiskDetect.getChildFiles(file);
//        List<String> stringlist = new ArrayList<String>();
//        for (int i = 0; i < files.size(); i++) {
//            stringlist.add("ceshi");
//        }
//        ChangeFile changeFile = new ChangeFile(files, stringlist, true);
//        changeFile.execute();
        /*6*/
        File file = new File("D:\\测试\\坚信 - 副本 (22)测试.txt");
        File newFile = new File("D:\\测试\\坚信 - 副本 (22)测试.ini");
        file.renameTo(newFile);
    }
}

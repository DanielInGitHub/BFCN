package daniel.view.util;

import daniel.controller.DiskDetect;
import daniel.exception.FolderUnreachableException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/8.
 */
public class Test
{
    public static void main(String[] args) throws FolderUnreachableException
    {
//        File file = new File("C://");
////        DiskDetect detect = new DiskDetect();
//        List<File> files = DiskDetect.getChildFolders(file);
////        File[] files = file.listFiles();
//        for (File file1 : files) {
//            System.out.println(file1);
//        }

        FileSystemView fsv = new JFileChooser().getFileSystemView();
        Icon icon = fsv.getSystemIcon(new File("D://"));
//        try {
//            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(icon);
//            File file = new File("d:/迅雷下载");
//            FileInputStream inputStream = new FileInputStream(file);
//            byte[] bytes= new byte[1024];
//            while (bytes.length>0)
//            {
//                imageOutputStream.write(bytes);
//                inputStream.read(bytes,0,bytes.length);
//            }
//            imageOutputStream.close();
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

package daniel.controller;

import daniel.exception.FolderUnreachableException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/8.
 */
public class DiskDetect
{
    /**
     * 获得本操作系统中所有的可操作盘符（不包括不能读取的磁盘，比如没有放入光盘的光驱）
     *
     * @return
     */
    public static List<File> getEffectiveDisks()
    {
        File[] tmpFiles = File.listRoots();
        List<File> files = new ArrayList<File>();
        for (File file : tmpFiles) {
            if (file.canExecute())
                files.add(file);
        }
        return files;
    }

    /**
     * 获得指定文件夹下所有的子文件夹，不包含文件
     *
     * @param parentFolders
     * @return
     */
    public static List<File> getChildFolders(File parentFolders) throws FolderUnreachableException
    {
        //判断该文件是否为文件夹，同时判断该文件夹是否可操作
        if (!parentFolders.isDirectory() && !parentFolders.canExecute())
            return null;

        List<File> folders = new ArrayList<File>();
        File[] files = parentFolders.listFiles();
        //判断有没有parentFolders文件夹的访问权限，如果没有，parentFolders为null
        if (files != null)
            for (File file : files) {
                if (file != null && file.isDirectory() && file.canExecute())
                    folders.add(file);
            }
        else
            //当没有该文件夹的访问权限时报FolderUnreachableException/* + "文件夹无法访问"*/
            throw new FolderUnreachableException(parentFolders.toString());

        return folders;
    }

    /**
     * 获得指定文件夹下所有的子文件，不包含文件夹
     *
     * @param parentFolders
     * @return
     */
    public static List<File> getChildFiles(File parentFolders)
    {
        if (!parentFolders.isFile())
            return null;

        List<File> files = new ArrayList<File>();
        File[] tmpFiles = parentFolders.listFiles();
        for (File file : tmpFiles) {
            if (file.isFile())
                files.add(file);
        }
        return files;
    }

    /**
     * 获得当前系统的系统盘符
     *
     * @return
     */
    public static String getSystemDisk()
    {
        String string = System.getProperty("user.home");
        String[] strings = string.split(":");
        return strings[0];
    }
}

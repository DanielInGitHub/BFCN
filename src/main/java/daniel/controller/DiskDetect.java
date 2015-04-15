package daniel.controller;

import daniel.exception.FolderUnreachableException;
import daniel.exception.NeedFolderException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类属于工具类，但也是本程序运行的基础类，用来获取文件，文件夹信息
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
        /*列出当前系统所有的磁盘*/
        File[] tmpFiles = File.listRoots();
        List<File> files = new ArrayList<File>();

        for (File file : tmpFiles)
            if (checkFolder(file))
                files.add(file);
        return files;
    }

    /**
     * 获得指定文件夹下所有的子文件夹，不包含文件
     *
     * @param parentFolderPath
     * @return
     */
    public static List<File> getChildFolders(String parentFolderPath) throws FolderUnreachableException
    {
        File parentFolder = new File(parentFolderPath);
        //判断该文件是否为文件夹，同时判断该文件夹是否可操作
        if (!parentFolder.isDirectory() && !parentFolder.canExecute())
            return null;

        List<File> folders = null;
        File[] files = parentFolder.listFiles();
        if (files != null) {
            folders = new ArrayList<File>();
            //判断有没有parentFolders文件夹的访问权限，如果没有，parentFolders为null
            for (File file : files) {
                if (checkFolder(file))
                    folders.add(file);
            }
        } else
            //当没有该文件夹的访问权限时报FolderUnreachableException/* + "文件夹无法访问"*/
            throw new FolderUnreachableException(parentFolder.toString());

        return folders;
    }

    /**
     * 获得指定文件夹下所有的子文件，不包含文件夹
     *
     * @param parentFolder
     * @return
     */
    public static List<File> getChildFiles(File parentFolder)
    {
        if (parentFolder.isFile())
            return null;

        List<File> files = new ArrayList<File>();
        File[] tmpFiles = parentFolder.listFiles();
        if (tmpFiles != null)
            for (File file : tmpFiles) {
                if (file.isFile())
                    files.add(file);
            }
        return files;
    }

    /**
     * 获得文件的后缀名，若文件没有后缀，则返回 ""
     *
     * @param file
     * @return
     * @throws NeedFolderException
     */
    public static String getFileExtensionName(File file) throws NeedFolderException
    {
        if (checkFolder(file))
            throw new NeedFolderException(file.toString());
        String fileName = file.getName();
        int i = fileName.lastIndexOf(".");
        String fileExtensionName = "";
        if (i != -1)
            fileExtensionName = fileName.substring(i + 1);
        return fileExtensionName;
    }

    /**
     * 得到文件不带后缀的文件名
     *
     * @param file
     * @return
     * @throws NeedFolderException
     */
    public static String getFilePureName(File file) throws NeedFolderException
    {
        if (checkFolder(file))
            throw new NeedFolderException(file.toString());
        String fileName = file.getName();
        int i = fileName.lastIndexOf(".");
        if (i != -1)
            fileName = fileName.substring(0, i);
        return fileName;
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

    /**
     * 获得特殊的本地系统的文件夹
     *
     * @param folder
     * @return
     */
    public static File getSpecialFolder(String folder)
    {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                    + "wscript.echo WshShell.SpecialFolders(\""
                    + folder
                    + "\")\n" + "Set WSHShell = Nothing\n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            result = input.readLine();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(result);
    }

    /**
     * 检查是否File对象是否为有效的文件夹
     *
     * @param file
     * @return
     */
    public static boolean checkFolder(File file)
    {
        return file.isDirectory() && file.canExecute();
    }

    public static String SF_ALLUSERSDESKTOP = "AllUsersDesktop";
    public static String SF_ALLUSERSSTARTMENU = "AllUsersStartMenu";
    public static String SF_ALLUSERSPROGRAMS = "AllUsersPrograms";
    public static String SF_ALLUSERSSTARTUP = "AllUsersStartup";
    public static String SF_DESKTOP = "Desktop";
    public static String SF_FAVORITES = "Favorites";
    public static String SF_MYDOCUMENT = "MyDocuments";
    public static String SF_PROGRAMS = "Programs";
    public static String SF_RECENT = "Recent";
    public static String SF_SENDTO = "SendTo";
    public static String SF_STARTMENU = "StartMenu";
    public static String SF_STARTUP = "Startup";
}

//public enum SpecialFolder
//{
//    AllUsersDesktop, AllUsersStartMenu, AllUsersPrograms, AllUsersStartup, Desktop, Favorites, MyDocuments, Programs, Recent, SendTo, StartMenu, Startup
//}
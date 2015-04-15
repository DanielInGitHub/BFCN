package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类主要是对原有的文件名添加字符串
 * 1）在原有字符串的前后各添加相应的字符串
 * 2）或者从某个位置开始添加字符串
 * Created by daniel chiu on 2015/4/13.
 */
public class AddChars
{
    /*需要更改文件名的文件序列*/
    private List<File> files;
    /*需要在前面加的字符串*/
    private String before;
    /*需要在后面加的字符串*/
    private String after;
    /*添加字符串的开始位置*/
    private int start;
    /*从开始位置添加的字符串*/
    private String chars;

    public AddChars(List<File> files, String before, String after)
    {
        this.files = files;
        this.before = before;
        this.after = after;
    }

    public AddChars(List<File> files, String chars, int start)
    {
        this.files = files;
        this.chars = chars;
        this.start = start;
    }

    public List<String> rule()
    {
        List<String> list = new ArrayList<String>();

        if (start != 0)
            //表明是从某位置开始添加字符串
            for (File file : files) {
                StringBuffer buffer = new StringBuffer();
                try {
                    String fileName = DiskDetect.getFilePureName(file);
                    buffer.append(fileName.substring(0, start));
                    buffer.append(chars);
                    buffer.append(fileName.substring(start, fileName.length()));
                } catch (NeedFolderException e) {
                    e.printStackTrace();
                }
                list.add(buffer.toString());
            }
        else {
            //表示在原文件名的前后添加字符串
            for (File file : files) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(before);
                try {
                    buffer.append(DiskDetect.getFilePureName(file));
                } catch (NeedFolderException e) {
                    e.printStackTrace();
                }
                buffer.append(after);
                list.add(buffer.toString());
            }
        }
        return list;
    }
}

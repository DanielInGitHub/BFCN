package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类主要是对原有的文件名删除字符串
 * 1）要么指定删除某个字符串
 * 2）要么删除从某位置开始某长度的字符串
 * Created by daniel chiu on 2015/4/13.
 */
public class DeleteChars
{
    /*需要更改文件名的文件序列*/
    private List<File> files;
    /*需要删除的字符串*/
    private String rule;
    /*从start位置开始删除字符串*/
    private int start;
    /*删除指定长度的字符串*/
    private int length;
    /*是否将开始位置倒着数*/
    private boolean reverse;

    public DeleteChars(List<File> files, String rule)
    {
        this.files = files;
        this.rule = rule;
    }

    public DeleteChars(List<File> files, int start, int length, boolean reverse)
    {
        this.files = files;
        this.start = start;
        this.length = length;
        this.reverse = reverse;
    }

    public List<String> rule()
    {
        List<String> list = new ArrayList<String>();
        if (files != null) {
            for (File file : files) {
                StringBuffer buffer;
                if (rule != null && !rule.equals(""))
                    buffer = deleteSpecificChars(file, rule);
                else {
                    buffer = deleteLengthChars(file, start, length, reverse);
                }
                list.add(buffer.toString());
            }
        }
        return list;
    }

    /**
     * 删除特定长度的字符串
     *
     * @param file
     * @param start
     * @param length
     * @param reverse
     * @return
     */
    private StringBuffer deleteLengthChars(File file, int start, int length, boolean reverse)
    {
        StringBuffer buffer = new StringBuffer();
        String str;
        try {
            str = DiskDetect.getFilePureName(file);
            if (start >= 0 && start <= str.length())
                try {
                    if (!reverse) {
                        buffer.append(str.substring(0, start));
                        buffer.append(str.substring(start + length, str.length()));
                    } else {
                        buffer.append(str.substring(0, str.length() - start - length));
                        buffer.append(str.substring(str.length() - start, str.length()));
                    }
                } catch (StringIndexOutOfBoundsException e) {
//                            StatusBar.setStatusMessage(e.getMessage());
                }
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 删除指定的字符串
     *
     * @param file
     * @param rule
     * @return
     */
    private StringBuffer deleteSpecificChars(File file, String rule)
    {
        StringBuffer buffer = new StringBuffer();
        String str;
        try {
            str = DiskDetect.getFilePureName(file);
            int start = str.indexOf(rule);
            if (start != -1) {
                buffer.append(str.substring(0, start));
                buffer.append(str.substring(start + rule.length(), str.length()));
            } else buffer.append(str);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}

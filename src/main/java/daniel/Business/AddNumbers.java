package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class AddNumbers
{
    private List<File> files;
    private String format;
    private int startNumber;
    private boolean DIYZero;
    private int zeroNumber;

    public AddNumbers(List<File> files, String format, int startNumber, boolean DIYZero)
    {
        this.files = files;
        this.format = format;
        this.startNumber = startNumber;
        this.DIYZero = DIYZero;
    }

    public AddNumbers(List<File> files, String format, int startNumber, boolean DIYZero, int zeroNumber)
    {
        this(files, format, startNumber, true);
        this.zeroNumber = zeroNumber;
    }

    public List<String> rule()
    {
        int numFlag = format.indexOf("#");
        int fileNameFlag = format.indexOf("<SELF>");
        if (fileNameFlag == -1)
            //表明舍弃原来的文件名
            return addNumberWithAbandon(files, numFlag, format);
        else if (numFlag < fileNameFlag)
            //说明#号在<SELF>号的左边
            return addNumberToLeft(files, format, numFlag, fileNameFlag);
        else
            return addNumberToRight(files, format, numFlag);
    }

    /**
     * 将序号添加到原文件名的右侧，分两种情况：
     * 1）直接在最右边添加序号
     * 2）在右边添加序号之后还有其他符号添加
     *
     * @param files
     * @param format
     * @param numFlag
     * @return
     */
    private List<String> addNumberToRight(List<File> files, String format, int numFlag)
    {
        List<String> strings = new ArrayList<String>();

        int fileNameFlag = format.indexOf(">");
        //说明#号在<SELF>号的右边:将序号添加在文件名右侧
        if (numFlag == format.length() - 1) {
            //说明序号的右侧没有符号
            String between = format.substring(fileNameFlag + 1, numFlag);
            for (File file : files) {
                StringBuffer buffer = new StringBuffer();
                try {
                    buffer.append(DiskDetect.getFilePureName(file));
                } catch (NeedFolderException e) {
                }
                buffer.append(between);
                if (DIYZero)
                    buffer.append(zeroNumber());
                buffer.append(startNumber);
                strings.add(buffer.toString());
                startNumber++;
            }
        } else {
            //说明序号的右侧还有符号
            fileNameFlag = format.indexOf(">");
            String right = format.substring(numFlag + 1, format.length());
            String between = format.substring(fileNameFlag + 1, numFlag);
            for (File file : files) {
                StringBuffer buffer = new StringBuffer();
                try {
                    buffer.append(DiskDetect.getFilePureName(file));
                } catch (NeedFolderException e) {
                    e.printStackTrace();
                }
                buffer.append(between);
                if (DIYZero)
                    buffer.append(zeroNumber());
                buffer.append(startNumber);
                buffer.append(right);
                strings.add(buffer.toString());
                startNumber++;
            }
        }
        return strings;
    }

    /**
     * 将序号添加到原文件名的左侧
     *
     * @param files
     * @param format
     * @param numFlag
     * @param fileNameFlag
     * @return
     */
    private List<String> addNumberToLeft(List<File> files, String format, int numFlag, int fileNameFlag)
    {
        List<String> strings = new ArrayList<String>();

        String between = format.substring(numFlag + 1, fileNameFlag);
        for (File file : files) {
            StringBuffer buffer = new StringBuffer();
            if (DIYZero)
                buffer.append(zeroNumber());
            buffer.append(startNumber);
            buffer.append(between);
            try {
                buffer.append(DiskDetect.getFilePureName(file));
            } catch (NeedFolderException e) {
                e.printStackTrace();
            }
            strings.add(buffer.toString());
            startNumber++;
        }
        return strings;
    }

    /**
     * 舍弃原来的文件名，根据format格式更名
     *
     * @param files
     * @param position #的位置
     * @param format
     * @return
     */
    private List<String> addNumberWithAbandon(List<File> files, int position, String format)
    {
        List<String> strings = new ArrayList<String>();

        //表明舍弃原来的文件名
        String tmp = null;
        boolean left = true;
        if (position != 0) {
            //表明序号在右边加
            tmp = format.substring(0, position - 1);
            left = false;
        } else {
            //表明序号在右边加
            tmp = format.substring(position, format.length());
        }

        for (int i = 0; i < files.size(); i++) {
            StringBuffer buffer = new StringBuffer();
            if (left) {
                if (DIYZero)
                    buffer.append(zeroNumber());
                buffer.append(startNumber);
                buffer.append(tmp);
            } else {
                buffer.append(tmp);
                if (DIYZero)
                    buffer.append(zeroNumber());
                buffer.append(startNumber);
            }
            startNumber++;
            strings.add(buffer.toString());
        }
        return strings;
    }

    public String zeroNumber()
    {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < zeroNumber; i++)
            buffer.append("0");
        return buffer.toString();
    }
}

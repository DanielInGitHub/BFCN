package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
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
        List<String> list = new ArrayList<String>();
        String tmp = null;
        int tmp1 = format.indexOf("#");
        int tmp2 = format.indexOf("<");

        if (tmp2 == -1) {
            //表明舍弃原来的文件名
            tmp = format.substring(0, tmp1);

            for (int i = 0; i < files.size(); i++) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(tmp);
                buffer.append(startNumber);
                startNumber++;
                list.add(buffer.toString());
            }
        } else if (tmp1 < tmp2) {
            //说明#号在<号的左边
            tmp = format.substring(tmp1 + 1, tmp2);
            for (File file : files) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(startNumber);
                buffer.append(tmp);
                try {
                    buffer.append(DiskDetect.getFilePureName(file));
                } catch (NeedFolderException e) {
                    e.printStackTrace();
                }
                list.add(buffer.toString());
                startNumber++;
            }
        } else {
            tmp2 = format.indexOf(">");
            //说明#号在<号的又边:将序号添加在文件名右侧
            if (tmp1 == format.length() - 1) {
                //说明序号的右侧没有符号
                tmp = format.substring(tmp2 + 1, tmp1);
                for (File file : files) {
                    StringBuffer buffer = new StringBuffer();
                    try {
                        buffer.append(DiskDetect.getFilePureName(file));
                    } catch (NeedFolderException e) {
                        e.printStackTrace();
                    }
                    buffer.append(tmp);
                    buffer.append(startNumber);
                    list.add(buffer.toString());
                    startNumber++;
                }
            } else {
                tmp2 = format.indexOf(">");
                String tmp4 = format.substring(tmp1 + 1, format.length());
                tmp = format.substring(tmp2 + 1, tmp1);
                for (File file : files) {
                    StringBuffer buffer = new StringBuffer();
                    try {
                        buffer.append(DiskDetect.getFilePureName(file));
                    } catch (NeedFolderException e) {
                        e.printStackTrace();
                    }
                    buffer.append(tmp);
                    buffer.append(startNumber);
                    buffer.append(tmp4);
                    list.add(buffer.toString());
                    startNumber++;
                }
            }
        }

        return list;
    }

}

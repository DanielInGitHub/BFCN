package daniel.Business;

import daniel.util.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类主要是对原有的文件名替换字符串
 * Created by daniel chiu on 2015/4/13.
 */
public class ReplaceChars
{
    /*需要更改文件名的文件序列*/
    private List<File> files;
    /*需要被替换的字符串*/
    private String original;
    /*替换的字符串*/
    private String newString;

    public ReplaceChars(List<File> files, String original, String newString)
    {
        this.files = files;
        this.original = original;
        this.newString = newString;
    }

    public List<String> rule()
    {
        List<String> list = new ArrayList<String>();
        for (File file : files) {
            StringBuffer buffer = new StringBuffer();
            String str = "";
            try {
                String fileName = DiskDetect.getFilePureName(file);
                if (fileName.contains(original)) {
                    int start = fileName.indexOf(original);
                    buffer.append(fileName.substring(0, start));
                    buffer.append(newString);
                    buffer.append(fileName.substring(start + original.length(), fileName.length()));
                }
            } catch (NeedFolderException e) {
                e.printStackTrace();
            }
            list.add(buffer.toString());
        }
        return list;
    }
}

package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class AddChars
{
    private List<File> files;
    private String before;
    private String after;
    private int start;
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

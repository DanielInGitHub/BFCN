package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class ReplaceChars
{
    private List<File> files;
    private String original;
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

package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class OriginalNameTableColumn extends TableColumnA
{

    public OriginalNameTableColumn(String tableHeader)
    {
        super(tableHeader);
    }

    @Override
    public String getColumn(File file)
    {
        try {
            return DiskDetect.getFilePureName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        return "";
    }
}

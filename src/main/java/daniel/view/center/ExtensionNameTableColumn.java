package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class ExtensionNameTableColumn extends TableColumnA
{
    public ExtensionNameTableColumn(String tableHeader)
    {
        super(tableHeader);
    }

    @Override
    public String getColumn(File file)
    {
        try {
            return DiskDetect.getFileExtensionName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        return "";
    }
}

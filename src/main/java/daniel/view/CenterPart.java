package daniel.view;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;
import daniel.view.center.ColumnData;
import daniel.view.center.FileTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class CenterPart
{
    private FileTable fileTable;

    public CenterPart(Composite composite)
    {
        File file = DiskDetect.getSpecialFolder(DiskDetect.SF_DESKTOP);
        List<File> folders = new ArrayList<File>();
        folders.add(file);
        try {
            fileTable = new FileTable(composite, SWT.CHECK, null, null);
            fileTable.defaultTableShow(folders);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
    }

    public FileTable getFileTable1()
    {
        return fileTable;
    }
}

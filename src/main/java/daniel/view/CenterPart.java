package daniel.view;

import daniel.exception.NeedFolderException;
import daniel.view.center.FileTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class CenterPart
{
    private FileTable fileTable;

    public CenterPart(Composite composite)
    {
        //表格表头
        String[] tableHeaders = {"原文件名", "新文件名", "后缀", "状态"};
        try {
            fileTable = new FileTable(composite, SWT.CHECK, null, tableHeaders);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
    }

    public FileTable getFileTable()
    {
        return fileTable;
    }
}

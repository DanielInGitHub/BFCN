package daniel.view;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;
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
        /*获取桌面文件夹对象*/
        File file = DiskDetect.getSpecialFolder(DiskDetect.SF_DESKTOP);
        List<File> folders = new ArrayList<File>();
        folders.add(file);
        try {
            /**
             * 没有采取直接实例化FileTable，而采取defaultTableShow方法
             * 是因为FileTable实例化需要准备各列的数据，避免麻烦的情况下直接制定了FileTable需要显示的文件夹
             */
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

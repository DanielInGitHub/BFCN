package daniel.view;

import daniel.controller.DiskDetect;
import daniel.view.leftside.FolderTree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class LeftPart
{
    private FolderTree folderTree;

    public LeftPart(Composite composite)
    {
        //获得所有可操作的磁盘
        List<File> files = DiskDetect.getEffectiveDisks();
//        File file = DiskDetect.getSpecialFolder(DiskDetect.SF_FAVORITES);
//        files.add(file);
        folderTree = new FolderTree(composite, SWT.SINGLE, files);
    }
}

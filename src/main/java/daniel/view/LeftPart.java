package daniel.view;

import daniel.controller.DiskDetect;
import daniel.view.bottomside.StatusBar;
import daniel.view.leftside.FolderTree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

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
        folderTree = new FolderTree(composite, SWT.MULTI, files);

    }

    public FolderTree getFolderTree()
    {
        return folderTree;
    }
}

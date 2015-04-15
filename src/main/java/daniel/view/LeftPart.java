package daniel.view;

import daniel.util.DiskDetect;
import daniel.view.leftside.FolderTree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.util.List;

/**
 * 做本部分只有一个目录树，本来还打算再做几个控件上去，但是看起来没有需要，就没有添加
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

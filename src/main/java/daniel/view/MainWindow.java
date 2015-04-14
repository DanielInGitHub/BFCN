package daniel.view;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;
import daniel.view.center.ColumnData;
import daniel.view.center.FileTable;
import daniel.view.leftside.FolderTree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class MainWindow
{
    private static LeftPart leftPart;
    private static RightPart rightPart;
    private static SashForm sashForm;

    public MainWindow(Composite composite)
    {
        sashForm = new SashForm(composite, SWT.HORIZONTAL | SWT.BORDER);

        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.bottom = new FormAttachment(100, -25);
        sashForm.setLayoutData(formData);

        leftPart = new LeftPart(sashForm);
        rightPart = new RightPart(sashForm);
        sashForm.setWeights(new int[]{30, 70});

        FolderTree folderTree = leftPart.getFolderTree();
        final FileTable fileTable = rightPart.getFileTable();

        folderTree.addTreeSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Tree tree = (Tree) e.widget;
                TreeItem[] items = tree.getSelection();

                //获取所有选中的文件夹
                List<File> folders = new ArrayList<File>();
                for (TreeItem item : items) {
                    String folderPath = (String) item.getData();
                    folders.add(new File(folderPath));
                }

                fileTable.defaultTableShow(folders);
            }
        });
    }

}

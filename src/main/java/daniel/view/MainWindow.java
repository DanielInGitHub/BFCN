package daniel.view;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;
import daniel.view.center.ColumnData;
import daniel.view.center.FileTable;
import daniel.view.center.FileTable_1;
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
        final FileTable fileTable1 = rightPart.getFileTable();

        folderTree.addTreeSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Tree tree = (Tree) e.widget;
                TreeItem[] items = tree.getSelection();
                List<File> folders = new ArrayList<File>();
                for (TreeItem item : items) {
                    String folderPath = (String) item.getData();
                    folders.add(new File(folderPath));
                }
                List<File> childFiles = new ArrayList<File>();
                for (File file : folders)
                    childFiles.addAll(DiskDetect.getChildFiles(file));
//                List<File> childFiles = DiskDetect.getChildFiles(file);
                String[] tableHeaders = {"原文件名", "新文件名", "后缀", "状态"};
                List<String> list1 = new ArrayList<String>();
                List<String> list2 = new ArrayList<String>();
                List<String> list3 = new ArrayList<String>();
                List<String> list4 = new ArrayList<String>();
                for (File file1 : childFiles) {
                    try {
                        list1.add(DiskDetect.getFilePureName(file1));
                        list2.add("");
                        list3.add(DiskDetect.getFileExtensionName(file1));
                        list4.add("");
                    } catch (NeedFolderException e1) {
                        e1.printStackTrace();
                    }

                }
                ColumnData[] columnDatas = new ColumnData[tableHeaders.length];
                columnDatas[0] = new ColumnData(tableHeaders[0], list1);
                columnDatas[1] = new ColumnData(tableHeaders[1], list2);
                columnDatas[2] = new ColumnData(tableHeaders[2], list3);
                columnDatas[3] = new ColumnData(tableHeaders[3], list4);
                fileTable1.changeTableContent(columnDatas, childFiles);
            }
        });
    }
}

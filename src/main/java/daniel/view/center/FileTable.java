package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.controller.IconDetect;
import daniel.exception.NeedFolderException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class FileTable
{
    private Table table;
    private Button selectAll, selectInverse;
    private Composite centerComposite;
    private Composite buttonComposite;
    private List<File> folders = new ArrayList<File>();
    private String[] tableHeaders = {};

    public FileTable(Composite composite, int style, List<File> folders, String[] tableHeaders) throws NeedFolderException
    {
        centerComposite = new Composite(composite, SWT.NONE);
        FormLayout formLayout = new FormLayout();
        centerComposite.setLayout(formLayout);

//        centerComposite.setLayout();
//        setTable(composite, style);

        //表格布局
//        GridData gridData = new GridData();
//        gridData.horizontalAlignment = SWT.FILL;
//        gridData.grabExcessHorizontalSpace = true;
//        gridData.grabExcessVerticalSpace = true;
//        gridData.verticalAlignment = SWT.FILL;

        FormData formData = new FormData();
//        formData.height = 20;
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, -20);
//        statusbar.setLayoutData(formData);

        //创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行
        table = new Table(centerComposite, style);
        table.setHeaderVisible(true);//设置显示表头
        table.setLayout(new FillLayout());
        table.setLayoutData(formData);
        table.setLinesVisible(true);//暂时设置不显示表格线

        // 设置工具栏在Shell中的形状为水平抢占充满，并高位20像素
        FormData formData1 = new FormData();
//        formData.height = 20;
        formData1.bottom = new FormAttachment(100, 0);
//        statusbar.setLayoutData(formData);

        buttonComposite = new Composite(centerComposite, SWT.NONE);
        buttonComposite.setLayoutData(formData1);

        // 设置为用行列式布局管理状态栏里的组件
        RowLayout layout = new RowLayout();
        layout.marginLeft = layout.marginTop = 0; // 无边距
        buttonComposite.setLayout(layout);

        selectAll = new Button(buttonComposite, SWT.CHECK);
        selectAll.setText("全选");
        selectInverse = new Button(buttonComposite, SWT.CHECK);
        selectInverse.setText("反选");

        if (tableHeaders != null)
            this.tableHeaders = tableHeaders;

        setTableHeaders();

        if (folders != null)
            this.folders = checkFolders(folders);

        setTableItems();

    }

    private void setTableItems()
    {
        List<File> files = getFiles();

        for (File file : files) {
            setDefaultTableItem(file);
        }

        rePackTable();
    }

    private List<File> getFiles()
    {
        List<File> files = new ArrayList<File>();
        for (File folder : folders)
            files.addAll(DiskDetect.getChildFiles(folder));
        return files;
    }

    private void setDefaultTableItem(File file)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        String fileExtensionName = null;
        String filePureName = null;
        try {
            fileExtensionName = DiskDetect.getFileExtensionName(file);
            filePureName = DiskDetect.getFilePureName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        item.setText(new String[]{filePureName, "", fileExtensionName, ""});
        item.setImage(IconDetect.getSWTImageFromSwing(table.getDisplay(), file));
    }

    /**
     * 点了预览之后显示的表格元素
     *
     * @param file
     */
    private void setRenameTableItem(File file)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        String fileExtensionName = null;
        try {
            fileExtensionName = DiskDetect.getFileExtensionName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        item.setText(new String[]{file.getName(), "", fileExtensionName, ""});
        item.setImage(IconDetect.getSWTImageFromSwing(table.getDisplay(), file));
    }

    /**
     * 根据表格的内容重新整理列宽
     */
    private void rePackTable()
    {
        //重新布局表格
        for (int i = 0; i < this.tableHeaders.length; i++) {
            table.getColumn(i).pack();
        }
    }

    /**
     * 设置表头信息
     */
    private void setTableHeaders()
    {
        //创建表头的字符串数组
        for (int i = 0; i < tableHeaders.length; i++) {
            TableColumn tableColumn = new TableColumn(table, SWT.NONE);
            tableColumn.setText(tableHeaders[i]);
            //设置表头可移动，默认为false
            tableColumn.setMoveable(true);
        }
    }

    private void setTable(Composite composite, int style)
    {
        //表格布局
//        GridData gridData = new GridData();
//        gridData.horizontalAlignment = SWT.FILL;
//        gridData.grabExcessHorizontalSpace = true;
//        gridData.grabExcessVerticalSpace = true;
//        gridData.verticalAlignment = SWT.FILL;

        //创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行
        table = new Table(composite, style);
        table.setHeaderVisible(true);//设置显示表头
        table.setLayout(new FillLayout());
//        table.setLayoutData(gridData);//设置表格布局
        table.setLinesVisible(true);//暂时设置不显示表格线
    }

    public void refreshFiles(List<File> folders)
    {
        if (folders != null)
            this.folders = checkFolders(folders);
        table.removeAll();
        setTableItems();
        table.redraw();
    }

    private List<File> checkFolders(List<File> folders)
    {
        for (File folder : folders)
            if (!DiskDetect.checkFolder(folder))
                folders.remove(folder);
        return folders;
    }
}

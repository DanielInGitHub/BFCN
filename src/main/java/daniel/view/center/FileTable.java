package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.controller.IconDetect;
import daniel.exception.NeedFolderException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class FileTable
{
    private Table table;
    private List<File> folders = new ArrayList<File>();
    private String[] tableHeaders = {};

    public FileTable(Composite composite, List<File> folders, String[] tableHeaders) throws NeedFolderException
    {
        setTable(composite);

        if (tableHeaders != null)
            this.tableHeaders = tableHeaders;

        setTableHeaders();

        if (folders != null)
            this.folders = checkFolders(folders);

        setTableItems();

    }

    private void setTableItems()
    {
        List<File> files = new ArrayList<File>();
        for (File folder : folders)
            files.addAll(DiskDetect.getChildFiles(folder));

        for (File file : files) {
            setDefaultTableItem(file);
        }

        rePackTable();
    }

    private void setDefaultTableItem(File file)
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

    private void setTable(Composite composite)
    {
        //表格布局
        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = SWT.FILL;

        //创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行
        table = new Table(composite, SWT.CHECK);
        table.setHeaderVisible(true);//设置显示表头
        table.setLayoutData(gridData);//设置表格布局
        table.setLinesVisible(true);//设置显示表格线
    }

    public void setFolders(List<File> folders)
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

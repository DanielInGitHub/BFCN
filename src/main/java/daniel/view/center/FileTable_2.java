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
public class FileTable_2
{
    private Table table;
    private List<File> folders = new ArrayList<File>();
    private FileTableInfo tableInfo;
    private String[] tableHeaders = {};

    public FileTable_2(Composite composite, int style, List<File> folders, FileTableInfo tableInfo)
    {
        this.folders = folders;
        this.tableInfo = tableInfo;
        tableHeaders = tableInfo.tableHeaders();

        setTable(composite, style);
        setTableHeaders();
        setTableItems();
    }

    private void setTableItems()
    {
        List<File> files = new ArrayList<File>();
        for (File folder : folders)
            files.addAll(DiskDetect.getChildFiles(folder));

        for (File file : files) {
            setTableItem(file);
        }

        rePackTable();
    }

    private void setTableItem(File file)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        String[] data = new String[tableHeaders.length];
        for (int i = 0; i < tableHeaders.length; i++) {
            data[i] = tableInfo.getColumnData(tableHeaders[i], file);
        }
        item.setText(data);
        item.setImage(IconDetect.getSmallLocalFileImage(table.getDisplay(), file));
    }

    /**
     * 根据表格的内容重新整理列宽
     */
    private void rePackTable()
    {
        //重新布局表格
        for (int i = 0; i < tableHeaders.length; i++) {
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
        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = SWT.FILL;

        //创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行
        table = new Table(composite, style);
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

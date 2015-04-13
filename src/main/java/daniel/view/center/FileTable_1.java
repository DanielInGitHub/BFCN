package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.controller.IconDetect;
import daniel.exception.NeedFolderException;
import daniel.view.bottomside.StatusBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class FileTable_1
{
    private Table table;

    /**
     * 全选和反选按钮
     */
    private Button selectAll, selectInverse;

    /**
     * 将FileTable分成上下两部分，中间部分就是Table，两个Button放在表格下方
     */
    private Composite centerComposite;
    private Composite buttonComposite;

    /**
     * folders文件夹里的所有文件都可以显示在表格里，所以可以同时指定多个文件夹
     * 在这里直接进行初始化是因为如果没有给定文件夹表格只是不显示内容而不至于出错
     */
    private List<File> folders = new ArrayList<File>();

    /**
     * 对表头进行指定
     */
    private String[] tableHeaders = {};

    public FileTable_1(Composite composite, int style, List<File> folders, String[] tableHeaders) throws NeedFolderException
    {
        setLayout(composite, style);

        if (tableHeaders != null)
            this.tableHeaders = tableHeaders;

        setTableHeaders();

        if (folders != null)
            this.folders = checkFolders(folders);

        setTableItems();

        buttonAction();
    }

    /**
     * 给两个按钮添加动作（全选、反选）
     */
    private void buttonAction()
    {
        selectAll.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {

                //操作一个按钮的时候另外一个按钮必须取消选中
                selectInverse.setSelection(false);
                TableItem[] tableItems = table.getItems();

                /**
                 * checkCount表示点击按钮之前选中的行数
                 */
                int checkCount = 0;
                for (TableItem tableItem : tableItems)
                    if (tableItem.getChecked())
                        checkCount++;

                if (checkCount == table.getItemCount()) {
                    //表明所有的行都已被选中了。
                    for (TableItem tableItem : tableItems)
                        tableItem.setChecked(false);
                    StatusBar.setStatusMessage("选中了" + 0 + "行");
                } else {
                    for (TableItem tableItem : tableItems)
                        tableItem.setChecked(true);
                    StatusBar.setStatusMessage("选中了" + table.getItemCount() + "行");
                }
            }
        });

        selectInverse.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                selectAll.setSelection(false);
                int checkCount = 0;
                TableItem[] tableItems = table.getItems();
                for (TableItem tableItem : tableItems)
                    if (tableItem.getChecked()) {
                        checkCount++;
                        tableItem.setChecked(false);
                    } else
                        tableItem.setChecked(true);
                StatusBar.setStatusMessage("选中了" + (table.getItemCount() - checkCount) + "行");
            }
        });
    }


    /**
     * 根据文件夹的文件设置表格的内容
     */
    private void setTableItems()
    {
        List<File> files = getFiles();
        for (File file : files) {
            setDefaultTableItem(file);
        }

        rePackTable();
    }

    /**
     * 按照需要改变的列内容更改列
     *
     * @param list
     */
    @Deprecated
    private void changeTable(List<String> list)
    {
        List<File> files = getFiles();
        int index = 0;
        for (File file : files) {

            setRenameTableItem(file, list.get(index++));
        }
    }

    /**
     * 获得此时表格显示的所有文件
     *
     * @return
     */
    public List<File> getFiles()
    {
        List<File> files = new ArrayList<File>();
        for (File folder : folders)
            files.addAll(DiskDetect.getChildFiles(folder));
//        TableItem[] tableItems = table.getItems();
//        for (int i = 0; i < tableItems.length; i++) {
//            if (!tableItems[i].getChecked())
//                files.remove(i);
//        }
        return files;
    }

    public List<File> getCheckedFiles()
    {
        List<File> files = new ArrayList<File>();
        for (File folder : folders)
            files.addAll(DiskDetect.getChildFiles(folder));
//        TableItem[] tableItems = table.getItems();
//        for (int i = 0; i < tableItems.length; i++) {
//            if (!tableItems[i].getChecked())
//                files.remove(i);
//        }
        return files;
    }

    /**
     * 表格的默认展现方式，但是这里有一点不好在于，不能将列的生成方式解耦
     *
     * @param file
     */
    @Deprecated
    private void setDefaultTableItem(File file)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setData(file);
        String fileExtensionName = null;
        String filePureName = null;
        try {
            fileExtensionName = DiskDetect.getFileExtensionName(file);
            filePureName = DiskDetect.getFilePureName(file);
        } catch (NeedFolderException e) {

        }
        item.setText(new String[]{filePureName, "", fileExtensionName, "就绪"});
        item.setImage(IconDetect.getSWTImageFromSwing(table.getDisplay(), file));
    }

    /**
     * 点了预览之后显示的表格元素
     *
     * @param file
     */
    @Deprecated
    private void setRenameTableItem(File file, String string)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        String fileExtensionName = null;
        try {
            fileExtensionName = DiskDetect.getFileExtensionName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        try {
            item.setText(new String[]{DiskDetect.getFilePureName(file), string, fileExtensionName, ""});
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        item.setImage(IconDetect.getSWTImageFromSwing(table.getDisplay(), file));
    }

    @Deprecated
    private void setRenameTableItem(String[] columns, Image image)
    {
        TableItem item = new TableItem(table, SWT.NONE);
//        String fileExtensionName = null;
//        try {
//            fileExtensionName = DiskDetect.getFileExtensionName(file);
//        } catch (NeedFolderException e) {
//            e.printStackTrace();
//        }
//        try {
//            item.setText(new String[]{DiskDetect.getFilePureName(file), string, fileExtensionName, ""});
//        } catch (NeedFolderException e) {
//            e.printStackTrace();
//        }
        item.setText(columns);
        item.setImage(image);
    }

    /**
     * 更改指定列的所有数据
     *
     * @param columnData1s
     */
    private void changeColumn(ColumnData_1[] columnData1s)
    {
        TableItem[] tableItems = table.getItems();

        for (ColumnData_1 columnData1 : columnData1s) {
            List<String> list = columnData1.getList();
            String columnName = columnData1.getColumnName();
            int index = getHeaderIndex(columnName);
            if (index != -1)
                for (int i = 0; i < tableItems.length; i++)
                    tableItems[i].setText(index, list.get(i));
        }
    }

    /**
     * 根据列名获得在表头中的位置
     *
     * @param columnName
     * @return
     */
    private int getHeaderIndex(String columnName)
    {
        for (int i = 0; i < tableHeaders.length; i++)
            if (tableHeaders[i].equals(columnName))
                return i;
        return -1;
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

    @Deprecated
    public void refreshFiles(List<File> folders)
    {
        if (folders != null)
            this.folders = checkFolders(folders);
        table.removeAll();
        setTableItems();
        table.redraw();
    }

    @Deprecated
    public void change(ColumnData_1[] columnData1s)
    {
        if (folders != null)
            this.folders = checkFolders(folders);
//        table.removeAll();
        changeColumn(columnData1s);
//        changeTable(list);
        rePackTable();
        table.redraw();
    }

    /**
     * 检查传递进来的文件夹是否都为文件夹，将不是的剔除
     * <p>
     * 但是也考虑到不需要检查，因为控件的使用者传递了错误的参数就运行不起来。
     * 所以理论上来讲，传递进来的一定是文件夹，在这里多检查一遍有点浪费资源
     *
     * @param folders
     * @return
     */
    private List<File> checkFolders(List<File> folders)
    {
        for (File folder : folders)
            if (!DiskDetect.checkFolder(folder))
                folders.remove(folder);
        return folders;
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

    /**
     * 初始化FileTable的基本组件并设置布局信息
     *
     * @param composite
     * @param style
     */
    private void setLayout(Composite composite, int style)
    {
        centerComposite = new Composite(composite, SWT.NONE);
        FormLayout formLayout = new FormLayout();
        centerComposite.setLayout(formLayout);

        FormData tableFormData = new FormData();
        tableFormData.left = new FormAttachment(0, 0);
        tableFormData.right = new FormAttachment(100, 0);
        tableFormData.top = new FormAttachment(0, 0);
        tableFormData.bottom = new FormAttachment(100, -20);

        table = new Table(centerComposite, style);
        table.setHeaderVisible(true);
        table.setLayout(new FillLayout());
        table.setLayoutData(tableFormData);
        table.setLinesVisible(true);

        FormData buttonFormData = new FormData();
        buttonFormData.bottom = new FormAttachment(100, 0);

        buttonComposite = new Composite(centerComposite, SWT.NONE);
        buttonComposite.setLayoutData(buttonFormData);

        // 设置为用行列式布局管理状态栏里的组件
        RowLayout layout = new RowLayout();
        layout.marginLeft = layout.marginTop = 0; // 无边距
        buttonComposite.setLayout(layout);
        selectAll = new Button(buttonComposite, SWT.CHECK);
        selectAll.setText("全选");
        selectInverse = new Button(buttonComposite, SWT.CHECK);
        selectInverse.setText("反选");
    }
}

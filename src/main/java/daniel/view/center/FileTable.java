package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.controller.IconDetect;
import daniel.exception.NeedFolderException;
import daniel.view.bottomside.StatusBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class FileTable
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
    private List<File> folders;
    private List<Integer> indexes = new ArrayList<Integer>();

    /**
     * 将每列数据都封装在ColumnData中
     */
    private ColumnData[] columnDatas;

    public FileTable(Composite composite, int style, ColumnData[] columnDatas, List<File> folders) throws NeedFolderException
    {
        setLayout(composite, style);

        this.columnDatas = columnDatas;
        this.folders = folders;

        setTableHeaders();

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
        List<File> files = new ArrayList<File>();
        if (folders != null)
            for (File folder : folders)
                files.addAll(DiskDetect.getChildFiles(folder));

        if (columnDatas != null) {
            //将columnDatas的每列list数据转换成二维数组
            String[][] tmp = new String[columnDatas[0].getList().size()][columnDatas.length];
            for (int i = 0; i < columnDatas.length; i++) {
                ColumnData columnData = columnDatas[i];
                for (int j = 0; j < columnData.getList().size(); j++)
                    //这里转置的一个关键点就在下面的j和i位置
                    tmp[j][i] = columnData.getList().get(j);
            }

            for (int i = 0; i < tmp.length; i++) {
                Image image = IconDetect.getSWTImageFromSwing(table.getDisplay(), files.get(i));
                setTableItem(tmp[i], image, files.get(i));
            }

            rePackTable();
        }
    }

    private void setTableItem(String[] rowData, Image image, File file)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(rowData);
        item.setImage(image);
        item.setData(file);
    }

    /**
     * 当更改了表格中显示的文件后，必须刷新这些文件（因为跟TableItem绑定的File已经过期），
     * 不然会出现第二次更改失效的问题
     */
    public void refreshItems()
    {
        List<File> files = new ArrayList<File>();
        for (File folder : folders)
            files.addAll(DiskDetect.getChildFiles(folder));
        TableItem[] tableItems = table.getItems();
        for (int i = 0; i < files.size(); i++)
            tableItems[i].setData(files.get(i));
    }

    /**
     * 获得此时表格显示的所有文件
     *
     * @return
     */
    public List<File> getFolders()
    {
        return this.folders;
    }

    /**
     * 返回序号和文件对应的map
     *
     * @return
     */
    public List<File> getCheckedFiles()
    {
        List<File> files = new ArrayList<File>();
        //每次重新获得表格中的文件时必须重新建立索引
        indexes.clear();
        TableItem[] tableItems = table.getItems();
        for (int i = 0; i < tableItems.length; i++) {
            if (tableItems[i].getChecked()) {
                files.add((File) tableItems[i].getData());
                indexes.add(i);
            }
        }
        return files;
    }

    /**
     * 更改指定列的所有数据
     *
     * @param columnDatas
     */
    public void changeColumns(ColumnData[] columnDatas)
    {
        TableItem[] tableItems = table.getItems();

        for (ColumnData columnData : columnDatas) {
            List<String> list = columnData.getList();
            String columnName = columnData.getColumnName();
            int index = getHeaderIndex(columnName);
            if (index != -1)
                //如果操作的是所有行
                if (indexes.size() == tableItems.length)
                    for (int i = 0; i < tableItems.length; i++)
                        tableItems[i].setText(index, list.get(i));
                else {
                    //这个的目的在于。如果预览的时候更改了选择的个数，必须在现实预览或者结果之前将之前的结果清空
                    for (int i = 0; i < tableItems.length; i++) {
                        tableItems[i].setText(index, "");
//                        if (columnData.getColumnName().equals("新文件"))
//                            tableItems[i].setData();
                    }
                    int j = 0;
                    for (int i : indexes) {
                        //i表示的是表中哪一项需要更改，index表示那一列需要更改，j表示从list中一个个拿出数据拿出
                        String s = list.get(j++);
                        tableItems[i].setText(index, s);
                        //这里的代码有点不符合控件的组件身份，这表明实现知道了各列的逻辑，实际中可以删去，由用户决定
                        if (index == 3 && s.equals("文件同名")) {
                            tableItems[i].setForeground(new Color(table.getDisplay(), 255, 0, 0));
                            tableItems[i].setChecked(false);
                        }
                    }
                }
        }
        rePackTable();
    }

    /**
     * 如果要更改表格的整个内容（包括列）使用这个方法
     *
     * @param columnDatas
     * @param folders
     */
    public void changeTableContent(ColumnData[] columnDatas, List<File> folders)
    {
        this.folders = folders;
        this.columnDatas = columnDatas;
        table.removeAll();
        setTableItems();
        table.redraw();
    }

    /**
     * 默认表格显示四列："原文件名", "新文件名", "后缀", "状态"
     * 如果采用默认的显示形式，只需要传递需要展示的文件夹就可以
     *
     * @param folders
     */
    public void defaultTableShow(List<File> folders)
    {
        List<File> childFiles = new ArrayList<File>();
        for (File file : folders)
            childFiles.addAll(DiskDetect.getChildFiles(file));

        this.folders = folders;
        //表格表头
        String[] tableHeaders = {"原文件名", "新文件名", "后缀", "状态"};
        try {
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            List<String> list3 = new ArrayList<String>();
            List<String> list4 = new ArrayList<String>();
            for (File file1 : childFiles) {
                list1.add(DiskDetect.getFilePureName(file1));
                list2.add("");
                list3.add(DiskDetect.getFileExtensionName(file1));
                list4.add("");
            }
            ColumnData[] columnDatas = new ColumnData[tableHeaders.length];
            columnDatas[0] = new ColumnData(tableHeaders[0], list1);
            columnDatas[1] = new ColumnData(tableHeaders[1], list2);
            columnDatas[2] = new ColumnData(tableHeaders[2], list3);
            columnDatas[3] = new ColumnData(tableHeaders[3], list4);
            this.columnDatas = columnDatas;
            table.removeAll();

            setTableHeaders();

            setTableItems();
//            table.redraw();
        } catch (NeedFolderException e) {
            e.printStackTrace();
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
        for (int i = 0; i < columnDatas.length; i++)
            if (columnDatas[i].getColumnName().equals(columnName))
                return i;
        return -1;
    }

    /**
     * 根据表格的内容重新整理列宽
     */
    private void rePackTable()
    {
        //重新布局表格
        for (int i = 0; i < this.columnDatas.length; i++) {
            table.getColumn(i).pack();
        }
    }

    /**
     * 设置表头信息
     */
    private void setTableHeaders()
    {
        //创建表头的字符串数组.只有数据满足条件并且需要改变默认的列数的时候才重新添加列表头
        if (columnDatas != null && table.getColumnCount() != 4)
            for (int i = 0; i < columnDatas.length; i++) {
                TableColumn tableColumn = new TableColumn(table, SWT.NONE);
                tableColumn.setText(columnDatas[i].getColumnName());
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

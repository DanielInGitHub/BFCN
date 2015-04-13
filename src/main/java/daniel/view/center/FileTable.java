package daniel.view.center;

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
    private List<File> files = new ArrayList<File>();

    /**
     * 对表头进行指定
     */
    private ColumnData[] columnDatas;

    public FileTable(Composite composite, int style, ColumnData[] columnDatas, List<File> files) throws NeedFolderException
    {
        setLayout(composite, style);

        this.columnDatas = columnDatas;
        this.files = files;

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

    private void setTableItem(String[] rowData, Image image, File file)
    {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(rowData);
        item.setImage(image);
    }

    /**
     * 获得此时表格显示的所有文件
     *
     * @return
     */
    public List<File> getFiles()
    {
        return this.files;
    }

    public List<File> getCheckedFiles()
    {
        List<File> files = new ArrayList<File>();
        TableItem[] tableItems = table.getItems();
        for (int i = 0; i < tableItems.length; i++) {
            if (tableItems[i].getChecked())
                files.add((File) tableItems[i].getData());
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
                for (int i = 0; i < tableItems.length; i++)
                    tableItems[i].setText(index, list.get(i));
        }
    }


    public void changeTableContent(ColumnData[] columnDatas, List<File> files)
    {
        this.files = files;
        this.columnDatas = columnDatas;
        table.removeAll();
        setTableItems();
        table.redraw();
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
        //创建表头的字符串数组
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

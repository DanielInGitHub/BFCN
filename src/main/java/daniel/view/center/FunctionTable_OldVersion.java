package daniel.view.center;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class FunctionTable_OldVersion
{
    private Table table;

    public FunctionTable_OldVersion(Composite composite)
    {

        //表格布局
        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = SWT.FILL;

        //创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行
        table = new Table(composite, SWT.MULTI);
        table.setHeaderVisible(true);//设置显示表头
        table.setLayoutData(gridData);//设置表格布局
        table.setLinesVisible(true);//设置显示表格线/*

        //创建表头的字符串数组
        String[] tableHeader = {"原文件名", "新文件名", "后缀", "状态"};
        for (int i = 0; i < tableHeader.length; i++) {
            TableColumn tableColumn = new TableColumn(table, SWT.NONE);
            tableColumn.setText(tableHeader[i]);
            //设置表头可移动，默认为false
            tableColumn.setMoveable(true);
        }

        //添加三行数据
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"张三", "男", "123", "zhangsan@sina.com"});
        //设置图标
        //item.setImage( ImageFactory.loadImage( table.getDisplay(),ImageFactory.ICON_BOY));

        item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"李四", "男", "4582", "lisi@sina.com"});
        //设置图标
        //item.setImage( ImageFactory.loadImage( table.getDisplay(),ImageFactory.ICON_BOY));

        item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"周五", "女", "567", "zhouwu@sina.com"});
        //设置图标
        //item.setImage( ImageFactory.loadImage( table.getDisplay(),ImageFactory.ICON_GIRL));

        //重新布局表格
        for (int i = 0; i < tableHeader.length; i++) {
            table.getColumn(i).pack();
        }
    }
}

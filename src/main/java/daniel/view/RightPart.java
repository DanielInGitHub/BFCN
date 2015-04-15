package daniel.view;

import daniel.Business.*;
import daniel.view.center.ColumnData;
import daniel.view.center.FileTable;
import daniel.view.upside.FunctionTab;
import daniel.view.upside.SuperTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.io.File;

/**
 * 程序的右半部分，主要是上面的TabFolder和中间的FileTable
 * Created by daniel chiu on 2015/4/12.
 */
public class RightPart
{
    private TopPart topPart;
    private CenterPart centerPart;
    private SashForm sashForm;

    public RightPart(Composite composite)
    {
        sashForm = new SashForm(composite, SWT.VERTICAL);
        topPart = new TopPart(sashForm);
        centerPart = new CenterPart(sashForm);
        sashForm.setWeights(new int[]{20, 80});

        final FunctionTab functionTab = topPart.getFunctionTab();
        final TabFolder tabFolder = functionTab.getTabFolder();
        Button preView = topPart.getPreViewButton();
        Button execute = topPart.getExecuteButton();
        final FileTable fileTable = getFileTable();

        preView.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                /*获取当前被选中的TabItem*/
                TabItem item = tabFolder.getSelection()[0];

                /*获取当前FileTable的所有被选中的文件*/
                java.util.List<File> stringlist = fileTable.getCheckedFiles();

                /*因为TabItem只是我定义的SuperTab的一个子类，所以必须根据特定的TabItem找到我定义的SuperTab，当然这里用到了多态*/
                SuperTab superTab = functionTab.getTab(item.getText());
                /*每个SuperTab的子类都实现了execute方法，传递出更改之后的信息*/
                ColumnData[] columnDatas = superTab.execute(stringlist);
                fileTable.changeColumns(columnDatas);
            }
        });

        execute.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                TabItem item = tabFolder.getSelection()[0];

                java.util.List<File> files = fileTable.getCheckedFiles();

                SuperTab superTab = functionTab.getTab(item.getText());
                ColumnData[] columnDatas = superTab.execute(files);

                ChangeFile changeFile = null;
                for (ColumnData columnData : columnDatas)
                    if (columnData.getColumnName().equals("新文件名")) {
                        //这里表明改的是文件名
                        changeFile = new ChangeFile(files, columnData.getList(), true);
                        columnDatas = new ColumnData[]{columnData, changeFile.execute()};
                    } else if (columnData.getColumnName().equals("后缀")) {
                        //表明改后缀
                        changeFile = new ChangeFile(files, columnData.getList(), false);
                        columnDatas = new ColumnData[]{columnData, changeFile.execute()};
                    }

                fileTable.changeColumns(columnDatas);
                //更改文件名之后一定要刷新FileTable，因为之前每个TableItem绑定的File对象都因为改名之后变得不存在了
                //所以，如果不刷新，当第二次重新更改的时候就会出现更改不生效的现象
                fileTable.refreshItems();
            }
        });
    }

//    public java.util.List<File> convertMaptoFileList(Map<Integer, File> map)
//    {
//        java.util.List<File> files = new ArrayList<File>();
//        for (File file : map.values())
//            files.add(file);
//        return files;
//    }
//
//    public java.util.List<Integer> convertMaptoIntegerList(Map<Integer, File> map)
//    {
//        java.util.List<Integer> integers = new ArrayList<Integer>();
//        for (Integer integer : map.keySet())
//            integers.add(integer);
//        return integers;
//    }

    public FileTable getFileTable()
    {
        return centerPart.getFileTable1();
    }
}

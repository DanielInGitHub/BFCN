package daniel.view;

import daniel.Business.*;
import daniel.controller.StatesChecker;
//import daniel.view.center.ColumnData_1;
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
import java.util.*;

/**
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
                TabItem item = tabFolder.getSelection()[0];

                java.util.List<File> stringlist = fileTable.getCheckedFiles();

                SuperTab superTab = functionTab.getTab(item.getText());
                ColumnData[] columnDatas = superTab.execute(stringlist);
                fileTable.changeColumns(columnDatas);
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

package daniel.view;

import daniel.Business.*;
import daniel.controller.StatesChecker;
//import daniel.view.center.ColumnData_1;
import daniel.view.center.ColumnData;
import daniel.view.center.FileTable;
import daniel.view.upside.FunctionTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

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
                java.util.List<String> list = null;
                TabItem item = tabFolder.getSelection()[0];
                if (item.getText().equals("删除字符")) {
                    Text[] texts = functionTab.getTexts("删除字符");
                    String string1 = texts[0].getText();
                    String string2 = texts[1].getText();
                    String string3 = texts[2].getText();
                    boolean inverse = functionTab.getDelete().getButton().getSelection();
                    if (string1 != null && !string1.equals(""))
                        list = new DeleteChars(fileTable.getCheckedFiles(), string1).rule();
                    else
                        list = new DeleteChars(fileTable.getCheckedFiles(), Integer.valueOf(string2), Integer.valueOf(string3), inverse).rule();

//                    ColumnData_1 columnChangeData = new ColumnData_1("新文件名", list);
//                    columnChangeData.setColumnName("新文件名");
//                    columnChangeData.setList(list);
//                    columnChangeData.setColumnName("后缀");
//                    columnChangeData.setList(StatesChecker.checkFileName(list));
                    fileTable.changeColumns(new ColumnData[]{new ColumnData("新文件名", list), new ColumnData("状态", StatesChecker.checkFileName(list))});

                } else if (item.getText().equals("添加序号")) {
                    Text[] texts = functionTab.getTexts("添加序号");
                    String string1 = texts[0].getText();
                    String string2 = texts[1].getText();
                    String format = functionTab.getAddNumber().getCombo().getText();
                    list = new AddNumbers(fileTable.getCheckedFiles(), format, Integer.valueOf(string1), false).rule();

                    ColumnData columnData1 = new ColumnData();
                    columnData1.setColumnName("新文件名");
                    columnData1.setList(list);
                    fileTable.changeColumns(new ColumnData[]{columnData1});
                } else if (item.getText().equals("添加字符")) {
                    Text[] texts = functionTab.getTexts("添加字符");
                    String string1 = texts[0].getText();
                    String string2 = texts[1].getText();
                    String string3 = texts[2].getText();
                    String string4 = texts[3].getText();
                    if (string3 != null && !string3.equals("")) {
                        int start = Integer.valueOf(string3);
                        list = new AddChars(fileTable.getCheckedFiles(), string4, start).rule();
                    } else
                        list = new AddChars(fileTable.getCheckedFiles(), string1, string2).rule();

                    ColumnData columnData1 = new ColumnData();
                    columnData1.setColumnName("新文件名");
                    columnData1.setList(list);
                    fileTable.changeColumns(new ColumnData[]{columnData1});
                } else if (item.getText().equals("替换字符")) {
                    Text[] texts = functionTab.getTexts("替换字符");
                    String string1 = texts[0].getText();
                    String string2 = texts[1].getText();
                    list = new ReplaceChars(fileTable.getCheckedFiles(), string1, string2).rule();

                    ColumnData columnData1 = new ColumnData();
                    columnData1.setColumnName("新文件名");
                    columnData1.setList(list);
                    fileTable.changeColumns(new ColumnData[]{columnData1});
                } else if (item.getText().equals("更改拓展名")) {
                    Text[] texts = functionTab.getTexts("更改拓展名");
                    String string1 = texts[0].getText();
                    list = new ChangeExtension(string1, false, fileTable.getCheckedFiles().size()).rule();

                    ColumnData columnData1 = new ColumnData();
                    columnData1.setColumnName("后缀");
                    columnData1.setList(list);
                    fileTable.changeColumns(new ColumnData[]{columnData1});
                }
//                for (String string : list)
//                    System.out.println(string);

            }
        });
    }

    public FileTable getFileTable()
    {
        return centerPart.getFileTable1();
    }
}

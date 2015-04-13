package daniel.view;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;
import daniel.view.center.ColumnData;
import daniel.view.center.FileTable;
import daniel.view.center.FileTable_1;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class CenterPart
{
    private FileTable_1 fileTable1;
    private FileTable fileTable;

    public CenterPart(Composite composite)
    {
        //表格表头
        String[] tableHeaders = {"原文件名", "新文件名", "后缀", "状态"};
        try {
//            fileTable1 = new FileTable_1(composite, SWT.CHECK, null, tableHeaders);
            File file = new File("D:\\测试");
            List<File> childFiles = DiskDetect.getChildFiles(file);
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
            fileTable = new FileTable(composite, SWT.CHECK, columnDatas, childFiles);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
    }

    public FileTable getFileTable1()
    {
        return fileTable;
    }
}

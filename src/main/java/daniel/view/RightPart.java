package daniel.view;

import daniel.exception.NeedFolderException;
import daniel.view.center.FileTable;
import daniel.view.upside.FunctionTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class RightPart
{
    private FunctionTab functionTab;
    private FileTable fileTable;
    private Composite rightComposite;

    public RightPart(Composite composite)
    {
        //右边界面的布局采用GridLayout，采用上下排列的方式
        rightComposite = new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        rightComposite.setLayout(gridLayout);

        functionTab = new FunctionTab(rightComposite);

        //表格布局
        String[] tableHeaders = {"原文件名", "新文件名", "后缀", "状态"};
        try {
            fileTable = new FileTable(rightComposite, null, tableHeaders);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
    }

    public FileTable getFileTable()
    {
        return fileTable;
    }
}

package daniel.view;

import daniel.exception.NeedFolderException;
import daniel.view.center.FileTable;
import daniel.view.upside.FunctionButtons;
import daniel.view.upside.FunctionTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by daniel chiu on 2015/4/10.
 */
@Deprecated
public class RightPart_1
{
    private FunctionTab functionTab;
    private FileTable fileTable;
    private Composite rightComposite;
    private SashForm sashForm;
    private FunctionButtons functionButtons;

    public RightPart_1(Composite composite)
    {
        //右边界面的布局采用GridLayout，采用上下排列的方式
//        rightComposite = new Composite(composite, SWT.NONE);
//        GridLayout gridLayout = new GridLayout();
//        gridLayout.numColumns = 1;
//        rightComposite.setLayout(gridLayout);
        sashForm = new SashForm(composite, SWT.VERTICAL);
//        sashForm.setLayout(new FillLayout());


        SashForm sashForm1 = new SashForm(sashForm, SWT.HORIZONTAL | SWT.SMOOTH);
        functionTab = new FunctionTab(sashForm1);
        functionButtons = new FunctionButtons(sashForm1);
        sashForm1.setWeights(new int[]{80, 20});

        //表格布局
        String[] tableHeaders = {"原文件名", "新文件名", "后缀", "状态"};
        try {
            fileTable = new FileTable(sashForm, SWT.CHECK, null, tableHeaders);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        sashForm.setWeights(new int[]{30, 70});
    }

    public FileTable getFileTable()
    {
        return fileTable;
    }
}

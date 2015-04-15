package daniel.view.upside;

import daniel.Business.ReplaceChars;
import daniel.util.StatesChecker;
import daniel.view.center.ColumnData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import java.io.File;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabReplace extends SuperTab
{
    private Label label1;
    private Label label2;

    private Text text1;
    private Text text2;


    public TabReplace(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();

        setSecondLine();
    }

    public ColumnData[] execute(java.util.List<File> fileList)
    {
        String before = text1.getText();
        String after = text2.getText();
        java.util.List<String> list = new ReplaceChars(fileList, before, after).rule();
        return new ColumnData[]{new ColumnData("新文件名", list), new ColumnData("状态", StatesChecker.checkFileName(list))};
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("替换文件名中的：");
        text1 = new Text(firstLine, SWT.SINGLE | SWT.BORDER);
    }

    @Override
    protected void setSecondLine()
    {
        label2 = new Label(secondLine, SWT.CENTER);
        label2.setText("为：");
        text2 = new Text(secondLine, SWT.SINGLE | SWT.BORDER);
    }

    public Text getText1()
    {
        return text1;
    }

    public void setText1(Text text1)
    {
        this.text1 = text1;
    }

    public Text getText2()
    {
        return text2;
    }

    public void setText2(Text text2)
    {
        this.text2 = text2;
    }
}

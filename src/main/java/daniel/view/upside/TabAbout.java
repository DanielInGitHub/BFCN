package daniel.view.upside;

import daniel.view.center.ColumnData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.List;

import java.io.File;
import java.util.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabAbout extends SuperTab
{

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
//    private Text text2;
//    private Text text3;

    private Button button;


    public TabAbout(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("作者：Daniel Chiu");
        text1 = new Text(firstLine, SWT.MULTI);
        text1.setText("开源地址：https://github.com/DanielInGitHub/BFCN");
    }

    @Override
    protected void setSecondLine()
    {

    }

    @Override
    public ColumnData[] execute(java.util.List<File> fileList)
    {
        return null;
    }

//    @Override
//    public String[] getTexts()
//    {
//        return new String[0];
//    }

    public Text getText1()
    {
        return text1;
    }

    public void setText1(Text text1)
    {
        this.text1 = text1;
    }

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }
}

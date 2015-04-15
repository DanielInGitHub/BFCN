package daniel.view.upside;

import daniel.Business.AddChars;
import daniel.util.StatesChecker;
import daniel.view.center.ColumnData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.io.File;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabAdd extends SuperTab
{

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;

    private Text text1;
    private Text text2;
    private Text text3;


    private TextWithArrows textWithArrows1;

    public TabAdd(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();
        setSecondLineLayout();
        setSecondLine();
    }

    public ColumnData[] execute(java.util.List<File> fileList)
    {
        String before = text1.getText();
        String after = text2.getText();
        String chars = text3.getText();

        java.util.List<String> list = null;
        int startNumber = textWithArrows1.getText();
        if (startNumber != 0)
            list = new AddChars(fileList, chars, startNumber).rule();
        else
            list = new AddChars(fileList, before, after).rule();
        return new ColumnData[]{new ColumnData("新文件名", list), new ColumnData("状态", StatesChecker.checkFileName(list))};
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("在文件名前添加:");
        text1 = new Text(firstLine, SWT.SINGLE | SWT.BORDER);

        label2 = new Label(firstLine, SWT.CENTER);
        label2.setText("在文件名后添加:");
        text2 = new Text(firstLine, SWT.SINGLE | SWT.BORDER);
    }

    @Override
    protected void setSecondLine()
    {
        label3 = new Label(secondLine, SWT.CENTER);
        label3.setText("在文件名中的第:");
        textWithArrows1 = new TextWithArrows(secondLine);
        label4 = new Label(secondLine, SWT.CENTER);
        label4.setText("个字符后添加：");
        text3 = new Text(secondLine, SWT.SINGLE | SWT.BORDER);
    }

//    @Override
//    public String[] getTexts()
//    {
//        return new String[]{text1.getText(), text2.getText(), textWithArrows1.getText().getText(), text3.getText()};
//    }

    private void setSecondLineLayout()
    {
        GridLayout gridLayout = new GridLayout(10, false);
        secondLine.setLayout(gridLayout);
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

    public Text getText3()
    {
        return text3;
    }

    public void setText3(Text text3)
    {
        this.text3 = text3;
    }

    public TextWithArrows getTextWithArrows1()
    {
        return textWithArrows1;
    }

    public void setTextWithArrows1(TextWithArrows textWithArrows1)
    {
        this.textWithArrows1 = textWithArrows1;
    }
}

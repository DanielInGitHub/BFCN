package daniel.view.upside;

import daniel.Business.DeleteChars;
import daniel.controller.StatesChecker;
import daniel.view.center.ColumnData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabDelete extends SuperTab
{
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
    private Button button;


    private TextWithArrows textWithArrows1, textWithArrows2;

    public TabDelete(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setSecondLineLayout();
        setFirstLine();
        setSecondLine();
    }

    public ColumnData[] execute(java.util.List<File> fileList)
    {
        String deleteText = text1.getText();
        int startNumber = textWithArrows1.getText();
        int length = textWithArrows2.getText();

        boolean reverse = button.getSelection();

        java.util.List<String> list = null;
        if (deleteText != null && !deleteText.equals(""))
            list = new DeleteChars(fileList, deleteText).rule();
        else
            list = new DeleteChars(fileList, startNumber, length, reverse).rule();
        return new ColumnData[]{new ColumnData("新文件名", list), new ColumnData("状态", StatesChecker.checkFileName(list))};
    }

    protected void setSecondLine()
    {
        label2 = new Label(secondLine, SWT.CENTER);
        label2.setText("从文件名中的第:");
        textWithArrows1 = new TextWithArrows(secondLine);
        label3 = new Label(secondLine, SWT.CENTER);
        label3.setText("个字符开始，共删除");
        textWithArrows2 = new TextWithArrows(secondLine);
        label4 = new Label(secondLine, SWT.CENTER);
        label4.setText("个字符");
        button = new Button(secondLine, SWT.CHECK);
        label5 = new Label(secondLine, SWT.CENTER);
        label5.setText("是否倒着数");
    }

//    @Override
//    public String[] getTexts()
//    {
//        return new String[]{text1.getText(), textWithArrows1.getText().getText(), textWithArrows2.getText().getText()};
//    }

    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("删除文件中的:");
        text1 = new Text(firstLine, SWT.SINGLE | SWT.BORDER);
    }

    private void setSecondLineLayout()
    {
        GridLayout gridLayout = new GridLayout(10, false);
        secondLine.setLayout(gridLayout);
    }


    public Button getButton()
    {
        return button;
    }

    public Text getText1()
    {
        return text1;
    }

    public void setText1(Text text1)
    {
        this.text1 = text1;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }

    public TextWithArrows getTextWithArrows1()
    {
        return textWithArrows1;
    }

    public void setTextWithArrows1(TextWithArrows textWithArrows1)
    {
        this.textWithArrows1 = textWithArrows1;
    }

    public TextWithArrows getTextWithArrows2()
    {
        return textWithArrows2;
    }

    public void setTextWithArrows2(TextWithArrows textWithArrows2)
    {
        this.textWithArrows2 = textWithArrows2;
    }
}

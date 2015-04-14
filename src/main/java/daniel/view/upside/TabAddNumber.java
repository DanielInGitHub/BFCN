package daniel.view.upside;

import daniel.Business.AddNumbers;
import daniel.controller.StatesChecker;
import daniel.view.center.ColumnData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.io.File;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabAddNumber extends SuperTab
{
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;
    private Label label6;


    private Group group;
    private Button button, button2;
    private Combo combo;
    private Text text;

    private TextWithArrows textWithArrows1, textWithArrows2;

    public TabAddNumber(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);

        setFirstLine();

        setLineLayout();

        setSecondLine();
        combo.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Combo combo = (Combo) e.widget;
                String format = combo.getText();
                if (format.equals("自定义格式#")) {
                    text.setEditable(true);
                    text.setForeground(new Color(label1.getDisplay(), 255, 0, 0));
                    text.setText("请输入自定义的格式。注意：# 号表示序号，<SELF>表示原文件名");
                } else {
                    text.setEditable(false);
                    text.setForeground(new Color(label1.getDisplay(), 106, 134, 89));
                    text.setText("   # 号表示序号，<SELF>表示原文件名");
                }
            }
        });
    }

    public ColumnData[] execute(java.util.List<File> fileList)
    {
        int startNumber = textWithArrows1.getText();
        int zeroNumber = textWithArrows2.getText();
        String format = combo.getText();
        if (format.equals("自定义格式#"))
            format = text.getText();

        java.util.List<String> list = null;
        if (!button2.getSelection())
            list = new AddNumbers(fileList, format, startNumber, false).rule();
        else list = new AddNumbers(fileList, format, startNumber, true, zeroNumber).rule();
        return new ColumnData[]{new ColumnData("新文件名", list), new ColumnData("状态", StatesChecker.checkFileName(list))};
    }

    protected void setSecondLine()
    {
        label3 = new Label(secondLine, SWT.NONE);
        label3.setText("起始序号:");
        textWithArrows1 = new TextWithArrows(secondLine);
        group = new Group(secondLine, SWT.SHADOW_NONE);
        group.setLayout(new GridLayout(4, false));
        button = new Button(group, SWT.RADIO);
        button.setSelection(true);
        label4 = new Label(group, SWT.NONE);
        label4.setText("自动补零");
        button2 = new Button(group, SWT.RADIO);
        label5 = new Label(group, SWT.NONE);
        label5.setText("自定义补零");
        label6 = new Label(secondLine, SWT.NONE);
        label6.setText("补零个数:");
        textWithArrows2 = new TextWithArrows(secondLine);
    }

    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.NONE);
        label1.setText("格式:");
        combo = new Combo(firstLine, SWT.DROP_DOWN);
        String[] format = new String[]{"#.<SELF>", "#_<SELF>", "#<SELF>", "<SELF>#", "<SELF>_#", "<SELF>[#]", "Pic_#", "File_#", "图片#", "文件_#", "自定义格式#"};
        combo.setItems(format);
        combo.select(0);
        text = new Text(firstLine, SWT.MULTI);
        text.setForeground(new Color(label1.getDisplay(), 106, 134, 89));
        text.setText("   # 号表示序号，<SELF>表示原文件名");
        text.setEditable(false);
    }

    private void setLineLayout()
    {
        GridLayout gridLayout = new GridLayout(10, false);
        secondLine.setLayout(gridLayout);
        firstLine.setLayout(gridLayout);
    }

    public Combo getCombo()
    {
        return combo;
    }

    public Button getButton()
    {
        return button;
    }

    public Button getButton2()
    {
        return button2;
    }

    public TextWithArrows getTextWithArrows1()
    {
        return textWithArrows1;
    }

    public TextWithArrows getTextWithArrows2()
    {
        return textWithArrows2;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }

    public void setButton2(Button button2)
    {
        this.button2 = button2;
    }

    public void setCombo(Combo combo)
    {
        this.combo = combo;
    }

    public void setTextWithArrows1(TextWithArrows textWithArrows1)
    {
        this.textWithArrows1 = textWithArrows1;
    }

    public void setTextWithArrows2(TextWithArrows textWithArrows2)
    {
        this.textWithArrows2 = textWithArrows2;
    }
}

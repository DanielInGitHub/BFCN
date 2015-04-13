package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

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

    private TextWithArrows textWithArrows1, textWithArrows2;

    public TabAddNumber(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);

        setFirstLine();

        setLineLayout();

        setSecondLine();
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

    @Override
    public Text[] getTexts()
    {
        return new Text[]{textWithArrows1.getText(), textWithArrows2.getText()};
    }

    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.NONE);
        label1.setText("格式:");
        combo = new Combo(firstLine, SWT.DROP_DOWN);
        String[] format = new String[]{"#.<SELF>", "#_<SELF>", "#<SELF>", "<SELF>#", "<SELF>_#", "<SELF>[#]", "Pic_#", "File_#", "图片#", "文件_#", "自定义格式#"};
        combo.setItems(format);
        combo.select(0);
        label2 = new Label(firstLine, SWT.NONE);
        label2.setForeground(new Color(label2.getDisplay(), 106, 134, 89));
        label2.setText("# 号表示序号，<SELF>表示原文件名");
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
}

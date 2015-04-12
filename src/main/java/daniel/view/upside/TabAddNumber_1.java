package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabAddNumber_1
{
    private TabItem addNumber;

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
    private Text text2;
    private Text text3;

    private Group group;
    private Button button, button2;

    private FormLayout formLayout;
    private TextWithArrows textWithArrows1, textWithArrows2;

    public TabAddNumber_1(TabFolder tabFolder)
    {
        formLayout = new FormLayout();
        formLayout.marginHeight = 5;
        formLayout.marginWidth = 5;

        addNumber = new TabItem(tabFolder, SWT.NONE);
        addNumber.setText("添加序号");

        Composite composite = new Composite(tabFolder, SWT.NONE);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        composite.setLayout(gridLayout);

        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙

        Composite composite1 = new Composite(composite, SWT.NONE);
        composite1.setLayout(rowLayout);

        label1 = new Label(composite1, SWT.NONE);
        label1.setText("格式:");
        Combo combo = new Combo(composite1, SWT.DROP_DOWN);
        String[] format = new String[]{"#.<SELF>", "#_<SELF>", "#<SELF>", "<SELF>#", "<SELF>_#", "<SELF>[#]", "Pic_#", "File_#", "图片#", "文件_#", "自定义格式#"};
        combo.setItems(format);
        label2 = new Label(composite1, SWT.NONE);
        label2.setForeground(new Color(tabFolder.getDisplay(), 106, 134, 89));
        label2.setText("# 号表示序号，<SELF>表示原文件名");

        Composite composite2 = new Composite(composite, SWT.NONE);
        composite2.setLayout(rowLayout);
        label3 = new Label(composite2, SWT.NONE);
        label3.setText("起始序号:");
//        text1 = new Text(composite2, SWT.BORDER | SWT.SINGLE);
//        text1.setText("1");
        textWithArrows1 = new TextWithArrows(composite2);
        group = new Group(composite2, SWT.SHADOW_NONE);
        group.setLayout(new FillLayout());
        button = new Button(group, SWT.RADIO);
        button.setSelection(true);
        label4 = new Label(group, SWT.NONE);
        label4.setText("自动补零");
        button2 = new Button(group, SWT.RADIO);
        label5 = new Label(group, SWT.NONE);
        label5.setText("自定义补零");
//        text2 = new Text(composite2, SWT.BORDER | SWT.SINGLE);
        textWithArrows2 = new TextWithArrows(composite2);
        addNumber.setControl(composite);
    }
}

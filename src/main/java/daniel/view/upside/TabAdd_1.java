package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
@Deprecated
public class TabAdd_1
{
    private TabItem add;

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
    private Text text2;
    private Text text3;
    private Text text4;

    private Button button;

    private FormLayout formLayout;
    private TextWithArrows textWithArrows1;

    public TabAdd_1(TabFolder tabFolder)
    {
        formLayout = new FormLayout();
        formLayout.marginHeight = 5;
        formLayout.marginWidth = 5;

        add = new TabItem(tabFolder, SWT.NONE);
        add.setText("添加字符");

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

        label1 = new Label(composite1, SWT.CENTER);
        label1.setText("在文件名前添加");
        text1 = new Text(composite1, SWT.SINGLE | SWT.BORDER);

        label2 = new Label(composite1, SWT.CENTER);
        label2.setText("在文件名后添加");
        text2 = new Text(composite1, SWT.SINGLE | SWT.BORDER);

        Composite composite2 = new Composite(composite, SWT.NONE);
        composite2.setLayout(rowLayout);

        label3 = new Label(composite2, SWT.CENTER);
        label3.setText("在文件名中的第:");
        textWithArrows1 = new TextWithArrows(composite2);
//        text3 = new Text(composite2, SWT.SINGLE | SWT.BORDER);
        label4 = new Label(composite2, SWT.CENTER);
        label4.setText("个字符后添加：");
        text4 = new Text(composite2, SWT.SINGLE | SWT.BORDER);

        add.setControl(composite);
    }
}

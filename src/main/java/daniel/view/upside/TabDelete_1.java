package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
@Deprecated
public class TabDelete_1
{
    private TabItem delete;

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
    private Button button;

    private FormLayout formLayout;

    private TextWithArrows textWithArrows1, textWithArrows2;

    public TabDelete_1(TabFolder tabFolder)
    {

        delete = new TabItem(tabFolder, SWT.NONE);
        delete.setText("删除字符");

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
        rowLayout.spacing = 2;// 控件的间隙

        Composite composite1 = new Composite(composite, SWT.NONE);
        composite1.setLayout(rowLayout);

        label1 = new Label(composite1, SWT.CENTER);
        label1.setText("删除文件中的:");
        text1 = new Text(composite1, SWT.SINGLE | SWT.BORDER);

        Composite composite2 = new Composite(composite, SWT.NONE);
        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 10;
        composite2.setLayout(gridLayout1);

        label2 = new Label(composite2, SWT.CENTER);
        label2.setText("从文件名中的第:");
        textWithArrows1 = new TextWithArrows(composite2);
        label3 = new Label(composite2, SWT.CENTER);
        label3.setText("个字符开始，共删除");
        textWithArrows2 = new TextWithArrows(composite2);
        label4 = new Label(composite2, SWT.CENTER);
        label4.setText("个字符");
        button = new Button(composite2, SWT.CHECK);
        label5 = new Label(composite2, SWT.CENTER);
        label5.setText("是否倒着数");

        delete.setControl(composite);
    }
}

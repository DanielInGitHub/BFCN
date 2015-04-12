package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
@Deprecated
public class TabExtension_1
{
    private TabItem extension;

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
    private Text text2;
    private Text text3;
    private Text text4;

    private Button button, button2;
    private Group group;

    private FormLayout formLayout;

    public TabExtension_1(TabFolder tabFolder)
    {
        formLayout = new FormLayout();
        formLayout.marginHeight = 5;
        formLayout.marginWidth = 5;

        extension = new TabItem(tabFolder, SWT.NONE);
        extension.setText("更改拓展名");

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
        label1.setText("更改为：");
        text1 = new Text(composite1, SWT.SINGLE | SWT.BORDER);

        label2 = new Label(composite1, SWT.CENTER);
        label2.setText("注意：不需加点");
        text2 = new Text(composite1, SWT.SINGLE | SWT.BORDER);

        Composite composite2 = new Composite(composite, SWT.NONE);
        composite2.setLayout(rowLayout);

        group = new Group(composite2, SWT.SHADOW_NONE);
        group.setLayout(new FillLayout());
        button = new Button(group, SWT.RADIO);
        button.setSelection(true);
        label3 = new Label(group, SWT.NONE);
        label3.setText("全部大写");
        button2 = new Button(group, SWT.RADIO);
        label4 = new Label(group, SWT.NONE);
        label4.setText("全部小写");


        extension.setControl(composite);
    }
}

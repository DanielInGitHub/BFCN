package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabExtension extends SuperTab
{
    private TabItem extension;

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;

    private Text text1;

    private Button button, button2;
    private Group group;

    public TabExtension(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();
        setSecondLine();
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("更改为：");
        text1 = new Text(firstLine, SWT.SINGLE | SWT.BORDER);

        label2 = new Label(firstLine, SWT.CENTER);
        label2.setForeground(new Color(label2.getDisplay(), 106, 134, 89));
        label2.setText("注意：不需加点");
    }

    @Override
    protected void setSecondLine()
    {
        group = new Group(secondLine, SWT.SHADOW_NONE);
        group.setLayout(new GridLayout(4, false));
        button = new Button(group, SWT.RADIO);
        button.setSelection(true);
        label3 = new Label(group, SWT.NONE);
        label3.setText("全部大写");
        button2 = new Button(group, SWT.RADIO);
        label4 = new Label(group, SWT.NONE);
        label4.setText("全部小写");
    }

    @Override
    public Text[] getTexts()
    {
        return new Text[]{text1};
    }

}

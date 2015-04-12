package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

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
    private Text text4;


    private TextWithArrows textWithArrows1;

    public TabAdd(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();
        setSecondLineLayout();
        setSecondLine();
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
        text4 = new Text(secondLine, SWT.SINGLE | SWT.BORDER);
    }

    private void setSecondLineLayout()
    {
        GridLayout gridLayout = new GridLayout(10, false);
        secondLine.setLayout(gridLayout);
    }
}

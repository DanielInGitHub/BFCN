package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabAbout extends SuperTab
{

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;

    private Text text1;
    private Text text2;
    private Text text3;

    private Button button;


    public TabAbout(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("作者：Daniel Chiu");
        text1 = new Text(firstLine, SWT.MULTI);
        text1.setText("开源地址：https://github.com/DanielInGitHub/BFCN");
    }

    @Override
    protected void setSecondLine()
    {

    }

    @Override
    public Text[] getTexts()
    {
        return new Text[0];
    }

}

package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

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

    @Override
    public Text[] getTexts()
    {
        return new Text[]{text1, textWithArrows1.getText(), textWithArrows2.getText()};
    }

    public Button getButton()
    {
        return button;
    }
}

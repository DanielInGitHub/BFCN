package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabReplace extends SuperTab
{
    private Label label1;
    private Label label2;

    private Text text1;
    private Text text2;


    public TabReplace(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();

        setSecondLine();
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.CENTER);
        label1.setText("替换文件名中的：");
        text1 = new Text(firstLine, SWT.SINGLE | SWT.BORDER);
    }

    @Override
    protected void setSecondLine()
    {
        label2 = new Label(secondLine, SWT.CENTER);
        label2.setText("为：");
        text2 = new Text(secondLine, SWT.SINGLE | SWT.BORDER);
    }

    @Override
    public Text[] getTexts()
    {
        return new Text[]{text1, text2};
    }

//    public TabReplace(TabFolder tabFolder)
//    {
//        formLayout = new FormLayout();
//        formLayout.marginHeight = 5;
//        formLayout.marginWidth = 5;
//
//        replace = new TabItem(tabFolder, SWT.NONE);
//        replace.setText("替换字符");
//
//        Composite composite = new Composite(tabFolder, SWT.NONE);
//
//        GridLayout gridLayout = new GridLayout();
//        gridLayout.numColumns = 1;
//        composite.setLayout(gridLayout);
//
//        RowLayout rowLayout = new RowLayout();
//        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
//        rowLayout.marginLeft = 5;// 左补白
//        rowLayout.marginTop = 5;// 上补白
//        rowLayout.marginRight = 5;// 右补白
//        rowLayout.marginBottom = 5;// 下补白
//        rowLayout.spacing = 5;// 控件的间隙
//
//        Composite composite1 = new Composite(composite, SWT.NONE);
//        composite1.setLayout(rowLayout);
//
//        label1 = new Label(composite1, SWT.CENTER);
//        label1.setText("替换文件名中的：");
//        text1 = new Text(composite1, SWT.SINGLE | SWT.BORDER);
//
//        Composite composite2 = new Composite(composite, SWT.NONE);
//        composite2.setLayout(rowLayout);
//        label2 = new Label(composite2, SWT.CENTER);
//        label2.setText("为：");
//        text2 = new Text(composite2, SWT.SINGLE | SWT.BORDER);
//
//        replace.setControl(composite);
//    }
}

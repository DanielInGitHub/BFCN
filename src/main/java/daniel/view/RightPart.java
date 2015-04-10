package daniel.view;

import daniel.view.center.FunctionTable;
import daniel.view.upside.FunctionTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class RightPart
{
    private FunctionTab functionTab;
    private FunctionTable functionTable;
    private Composite rightComposite;

    public RightPart(Composite composite)
    {
        //右边界面的布局采用GridLayout，采用上下排列的方式
        rightComposite = new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        rightComposite.setLayout(gridLayout);

        functionTab = new FunctionTab(rightComposite);
        functionTable = new FunctionTable(rightComposite);
    }
}

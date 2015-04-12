package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class FunctionButtons
{
    private Button preView;
    private Button execute;

    public FunctionButtons(Composite composite)
    {
        Composite composite1 = new Composite(composite, SWT.NONE);
//        GridLayout gridLayout = new GridLayout();
//        gridLayout.numColumns = 1;
//        composite1.setLayout(gridLayout);
        composite1.setLayout(new FillLayout());
        preView = new Button(composite1, SWT.PUSH);
        preView.setText("预览");
        execute = new Button(composite1, SWT.PUSH);
        execute.setText("执行");
    }
}

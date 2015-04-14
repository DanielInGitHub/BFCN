package daniel.view;

import daniel.view.upside.FunctionButtons;
import daniel.view.upside.FunctionTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TopPart
{
    private FunctionTab functionTab;
    private FunctionButtons functionButtons;
    private SashForm sashForm;

    public TopPart(Composite composite)
    {
        sashForm = new SashForm(composite, SWT.HORIZONTAL | SWT.SMOOTH);
        functionTab = new FunctionTab(sashForm);
        functionButtons = new FunctionButtons(sashForm);
        sashForm.setWeights(new int[]{80, 20});
    }

    public Button getPreViewButton()
    {
        return functionButtons.getPreView();
    }

    public Button getExecuteButton()
    {
        return functionButtons.getExecute();
    }

    public FunctionTab getFunctionTab()
    {
        return functionTab;
    }
}

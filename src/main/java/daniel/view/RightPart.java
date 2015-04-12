package daniel.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class RightPart
{
    private TopPart topPart;
    private CenterPart centerPart;
    private SashForm sashForm;

    public RightPart(Composite composite)
    {
        sashForm = new SashForm(composite, SWT.VERTICAL);
        topPart = new TopPart(sashForm);
        centerPart = new CenterPart(sashForm);
        sashForm.setWeights(new int[]{20, 80});
    }
}

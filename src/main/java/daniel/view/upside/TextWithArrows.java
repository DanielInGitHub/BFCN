package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TextWithArrows
{
    private Text text;
    private Button up, down;
    private Composite textComposite;
    private Composite arrowComposite;

    public TextWithArrows(Composite composite)
    {

        textComposite = new Composite(composite, SWT.NONE);
        textComposite.setLayout(new GridLayout(2, false));

        text = new Text(textComposite, SWT.SINGLE | SWT.BORDER);

        arrowComposite = new Composite(textComposite, SWT.NONE);
        arrowComposite.setLayout(new GridLayout(1, true));

        up = new Button(arrowComposite, SWT.ARROW | SWT.UP);
        down = new Button(arrowComposite, SWT.ARROW | SWT.DOWN);

        onlyNumberAccess();
        buttonAction();
    }

    private void onlyNumberAccess()
    {
        //设置输入框只能输入数字
        text.addVerifyListener(new VerifyListener()
        {
            public void verifyText(VerifyEvent e)
            {
                // 只能输入数值,通过异常来判断是否为数字
                try {
                    new Integer(e.text);
                    e.doit = true;
                } catch (NumberFormatException e1) {
                    e.doit = false;
                }

//                e.doit = "0123456789".indexOf(e.text) >= 0;
            }
        });
    }

    private void buttonAction()
    {
        up.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseUp(MouseEvent e)
            {
                String s = text.getText();
                int num = 0;
                if (s != null && !s.equals(""))
                    try {
                        num = Integer.valueOf(s);
                        num++;
                        text.setText(String.valueOf(num));
                    } catch (NumberFormatException e1) {

                    }
                else
                    text.setText(String.valueOf(num));
//                text.setText(s);
            }
        });

        down.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseUp(MouseEvent e)
            {
                String s = text.getText();
                int num = 0;
                if (s != null && !s.equals(""))
                    try {
                        num = Integer.valueOf(s);
                        if (num > 0)
                            num--;
                        text.setText(String.valueOf(num));
                    } catch (NumberFormatException e1) {

                    }
                else
                    text.setText(String.valueOf(num));
            }
        });
    }
}

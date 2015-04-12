package daniel.view;

import daniel.view.bottomside.StatusBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by daniel chiu on 2015/4/8.
 */
public class MainWindow
{
    private static Display display = new Display();
    private static Shell shell = new Shell(display);
    private static StatusBar statusBar;
    private static SashForm sashForm;
    private static LeftPart leftPart;
    private static RightPart rightPart;
//    private static ToolBar toolBar= new ToolBar(shell);


    public MainWindow()
    {
        shell.setText("批量文件名修改器");
        //设置布局数据
        setLayoutData();
        //添加控件
        setWidgets();

        openWindow();
    }

    public static void main(String[] args)
    {
        new MainWindow();
    }

    /**
     * 打开窗口并进行事件分发控制
     */
    private static void openWindow()
    {
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    /**
     * 设置此软件包含的控件
     */
    private static void setWidgets()
    {
        /**
         * 父控件为shell,必须先实例化状态栏，因为其他控件在实例化过程中会调用状态栏显示信息
         */
        statusBar = new StatusBar(shell);

        /**
         * 父控件为sashForm,左右占比3:7
         */
        leftPart = new LeftPart(sashForm);
        rightPart = new RightPart(sashForm);
        sashForm.setWeights(new int[]{30, 70});

        TopPart topPart = new TopPart(leftPart, rightPart);
    }

    /**
     * 设置控件的布局信息
     */
    private static void setLayoutData()
    {
        /**
         * 总布局管理器为FormLayout，这个管理器管理着状态栏，上面的SashForm（分割窗）
         */
        FormLayout formLayout = new FormLayout();
        shell.setLayout(formLayout);

        /**
         * 上面的布局管理器为SashForm，将SashForm的大小设置为填充剩下的shell区域
         * 将左边的目录选择器和右边的功能区和展示区按 3:7 隔开{ @link MainWindow.setWidgets()}
         */
        sashForm = new SashForm(shell, SWT.HORIZONTAL | SWT.BORDER);

        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.bottom = new FormAttachment(100, -25);
        sashForm.setLayoutData(formData);

    }
}

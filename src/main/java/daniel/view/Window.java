package daniel.view;

import daniel.view.bottomside.StatusBar;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 本程序的入口，基本的整个程序框架都是在这里指定的
 * Created by daniel chiu on 2015/4/8.
 */
public class Window
{
    private static Display display = new Display();
    private static Shell shell = new Shell(display);
    private static StatusBar statusBar;
    private static MainWindow mainWindow;


    public Window()
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
        new Window();
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
        mainWindow = new MainWindow(shell);

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

    }
}

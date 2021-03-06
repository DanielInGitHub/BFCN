package daniel.view.bottomside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * 状态栏的布局。本来还想做的更像Windows本地应用的状态栏，但是实际发现其实根本没需要
 * Created by daniel chiu on 2015/4/9.
 */
public class StatusBar
{
    private Composite statusbar;
    private static Label statusbarLabel;

    public StatusBar(Composite parent)
    {
        statusbar = new Composite(parent, SWT.BORDER);

        // 设置工具栏在Shell中的形状为水平抢占充满，并高位20像素
        FormData formData = new FormData();
        formData.height = 20;
        formData.bottom = new FormAttachment(100, 0);
        statusbar.setLayoutData(formData);

        // 设置为用行列式布局管理状态栏里的组件
        RowLayout layout = new RowLayout();
        layout.marginLeft = layout.marginTop = 0; // 无边距
        statusbar.setLayout(layout);
        // 创建一个用于显示文字的标签
        statusbarLabel = new Label(statusbar, SWT.LEFT_TO_RIGHT);
        statusbarLabel.setLayoutData(new RowData(500, -1));
        statusbarLabel.setText("就绪");
    }

    public static void setStatusMessage(String message)
    {
        statusbarLabel.setText(message);
    }
}

package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/9.
 */
public class FunctionTab
{
    private TabFolder tabFolder;
    private TabItem delete;
    private TabItem number;
    private TabItem add;
    private TabItem replace;
    private TabItem extensionName;
    private TabItem aboutSoftware;

    public FunctionTab(Composite composite)
    {
        tabFolder = new TabFolder(composite, SWT.TOP);
        tabFolder.setLayout(new FillLayout());
        setTabItemData();
    }

    private void setTabItemData()
    {
        setDeleteTabData();
        setNumberTabData();
        setAddTabData();
        setReplaceTabData();
        setExtensionTabData();
        setAboutTabData();
    }

    private void setAboutTabData()
    {
        aboutSoftware = new TabItem(tabFolder, SWT.NONE);
        aboutSoftware.setText("关于");
        Composite composite = new Composite(tabFolder, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙
        composite.setLayout(rowLayout);
        Label label = new Label(composite, SWT.NONE);
        label.setText("作者：Daniel Chiu");
    }

    private void setExtensionTabData()
    {
        extensionName = new TabItem(tabFolder, SWT.NONE);
        extensionName.setText("更改拓展名");
        Composite composite = new Composite(tabFolder, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙
        composite.setLayout(rowLayout);
        Label label = new Label(composite, SWT.NONE);
        label.setText("更改为：");
        Text text = new Text(composite, SWT.BORDER | SWT.SINGLE);
        Label label2 = new Label(composite, SWT.NONE);
        label2.setText("注意：不需加点");
        Button button = new Button(composite, SWT.RADIO);
        Label label4 = new Label(composite, SWT.NONE);
        label4.setText("全部大写");
        Button button2 = new Button(composite, SWT.RADIO);
        Label label5 = new Label(composite, SWT.NONE);
        label5.setText("全部小写");
        extensionName.setControl(composite);
    }

    private void setReplaceTabData()
    {
        replace = new TabItem(tabFolder, SWT.NONE);
        replace.setText("替换字符");
        Composite composite = new Composite(tabFolder, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙
        composite.setLayout(rowLayout);
        Label label = new Label(composite, SWT.NONE);
        label.setText("替换文件名中的：");
        Text text = new Text(composite, SWT.BORDER | SWT.SINGLE);
        Label label2 = new Label(composite, SWT.NONE);
        label2.setText("为：");
        Text text2 = new Text(composite, SWT.BORDER | SWT.SINGLE);
        replace.setControl(composite);
    }

    private void setAddTabData()
    {
        add = new TabItem(tabFolder, SWT.NONE);
        add.setText("添加字符");
        Composite composite = new Composite(tabFolder, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙
        composite.setLayout(rowLayout);
        Label label = new Label(composite, SWT.NONE);
        label.setText("在文件名前添加");
        Text text = new Text(composite, SWT.BORDER | SWT.SINGLE);
        Label label2 = new Label(composite, SWT.NONE);
        label2.setText("在文件名后添加");
        Text text2 = new Text(composite, SWT.BORDER | SWT.SINGLE);
        Label label3 = new Label(composite, SWT.NONE);
        label3.setText("在文件名中的第");
        Text text3 = new Text(composite, SWT.BORDER | SWT.SINGLE);
        Label label4 = new Label(composite, SWT.NONE);
        label4.setText("个字符后添加：");
        Text text4 = new Text(composite, SWT.BORDER | SWT.SINGLE);
        add.setControl(composite);
    }

    private void setNumberTabData()
    {
        number = new TabItem(tabFolder, SWT.NONE);
        number.setText("添加序号");
        Composite composite = new Composite(tabFolder, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙
        composite.setLayout(rowLayout);
        Label label = new Label(composite, SWT.NONE);
        label.setText("格式:");
        Combo combo = new Combo(composite, SWT.DROP_DOWN);
        String[] format = new String[]{"#.<SELF>", "#_<SELF>", "#<SELF>", "<SELF>#", "<SELF>_#", "<SELF>[#]", "Pic_#", "File_#", "图片#", "文件_#", "自定义格式#"};
        combo.setItems(format);
        Label label2 = new Label(composite, SWT.NONE);
        label2.setText("# 号表示序号，<SELF>表示原文件名");
        Label label3 = new Label(composite, SWT.NONE);
        label3.setText("起始序号:");
        Text text = new Text(composite, SWT.BORDER | SWT.SINGLE);
        Button button = new Button(composite, SWT.RADIO);
        Label label4 = new Label(composite, SWT.NONE);
        label4.setText("自动补零");
        Button button2 = new Button(composite, SWT.RADIO);
        Label label5 = new Label(composite, SWT.NONE);
        label5.setText("自定义补零");
        Text text2 = new Text(composite, SWT.BORDER | SWT.SINGLE);
        number.setControl(composite);
    }

    private void setDeleteTabData()
    {
        delete = new TabItem(tabFolder, SWT.NONE);
        delete.setText("删除字符");

        Composite composite = new Composite(tabFolder, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL;// 设置水平填充
        rowLayout.marginLeft = 5;// 左补白
        rowLayout.marginTop = 5;// 上补白
        rowLayout.marginRight = 5;// 右补白
        rowLayout.marginBottom = 5;// 下补白
        rowLayout.spacing = 5;// 控件的间隙
        composite.setLayout(rowLayout);

        Label label = new Label(composite, SWT.CENTER);
        label.setText("删除文件中的：");
        Text text = new Text(composite, SWT.SINGLE | SWT.BORDER);
        Label label2 = new Label(composite, SWT.CENTER);
        label2.setText("从文件名中的第");
        Text text2 = new Text(composite, SWT.SINGLE | SWT.BORDER);
        Label label3 = new Label(composite, SWT.CENTER);
        label3.setText("个字符开始，共删除");
        Text text3 = new Text(composite, SWT.SINGLE | SWT.BORDER);
        Label label4 = new Label(composite, SWT.CENTER);
        label4.setText("个字符");
        Button button = new Button(composite, SWT.CHECK);
        Label label5 = new Label(composite, SWT.CENTER);
        label5.setText("是否倒着数");

        delete.setControl(composite);
    }
}

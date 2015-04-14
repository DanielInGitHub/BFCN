package daniel.view.upside;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by daniel chiu on 2015/4/9.
 */
public class FunctionTab
{
    private TabFolder tabFolder;
    private TabDelete delete;
    private TabAddNumber addNumber;
    private TabAdd add;
    private TabReplace replace;
    private TabExtension extension;
    private TabAbout about;

    public FunctionTab(Composite composite)
    {
        tabFolder = new TabFolder(composite, SWT.TOP);
        tabFolder.setLayout(new FillLayout());
        setTabItemData();
    }

    private void setTabItemData()
    {
        delete = new TabDelete(tabFolder, "删除字符");
        addNumber = new TabAddNumber(tabFolder, "添加序号");
        add = new TabAdd(tabFolder, "添加字符");
        replace = new TabReplace(tabFolder, "替换字符");
        extension = new TabExtension(tabFolder, "更改拓展名");
        about = new TabAbout(tabFolder, "关于");
    }

    public TabFolder getTabFolder()
    {
        return tabFolder;
    }

    public SuperTab getTab(String tabName)
    {
        if (tabName != null && !tabName.equals("")) {
            if (tabName.equals("删除字符"))
                return delete;
            if (tabName.equals("添加序号")) return addNumber;
            if (tabName.equals("添加字符")) return add;
            if (tabName.equals("替换字符")) return replace;
            if (tabName.equals("更改拓展名")) return extension;
        }
        return null;
    }
//    /**
//     * 此处应用多态获取其子类所有的输入框
//     *
//     * @param tabName
//     * @return
//     */
//    public String[] getTexts(String tabName)
//    {
//        if (tabName != null && !tabName.equals(""))
//            if (tabName.equals("删除字符"))
//                return delete.getTexts();
//        if (tabName.equals("添加序号")) return addNumber.getTexts();
//        if (tabName.equals("添加字符")) return add.getTexts();
//        if (tabName.equals("替换字符")) return replace.getTexts();
//        if (tabName.equals("更改拓展名")) return extension.getTexts();
//        else return null;
//    }

    public TabDelete getDelete()
    {
        return delete;
    }

    public TabAddNumber getAddNumber()
    {
        return addNumber;
    }

    public TabAdd getAdd()
    {
        return add;
    }

    public TabReplace getReplace()
    {
        return replace;
    }

    public TabExtension getExtension()
    {
        return extension;
    }

    public TabAbout getAbout()
    {
        return about;
    }
}

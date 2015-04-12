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

}

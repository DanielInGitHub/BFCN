package daniel.view.upside;

import daniel.view.center.ColumnData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.List;

import java.io.File;
import java.util.*;

/**
 * Created by daniel chiu on 2015/4/12.
 */
public class TabAbout extends SuperTab
{

    private Label label1;

    private Text text1;

    private Button button;


    public TabAbout(TabFolder tabFolder, String tabName)
    {
        super(tabFolder, tabName);
        setFirstLine();
        setSecondLine();
    }

    @Override
    protected void setFirstLine()
    {
        label1 = new Label(firstLine, SWT.RIGHT);
        label1.setText("作者：Daniel Chiu  ");

        Link link = new Link(firstLine, SWT.NONE);
        link.setText("<a href=\"https://github.com/DanielInGitHub/BFCN\">开源地址</a>");
        link.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Program.launch("https://github.com/DanielInGitHub/BFCN");
            }
        });

        Link link2 = new Link(firstLine, SWT.NONE);
        link2.setText("<a href=\"http://weibo.com/u/2214818382\">微博反馈</a>");
        link2.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Program.launch("http://weibo.com/u/2214818382");
            }
        });

        Link link3 = new Link(firstLine, SWT.NONE);
        link3.setText("<a href=\"http://blog.csdn.net/u012443091/article/details/45065751\">使用帮助</a>");
        link3.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Program.launch("http://blog.csdn.net/u012443091/article/details/45065751");
            }
        });
    }

    @Override
    protected void setSecondLine()
    {

//        ScrolledComposite scrolledComposite = new ScrolledComposite(secondLine, SWT.H_SCROLL | SWT.V_SCROLL);
//        scrolledComposite.setLayout(new FillLayout());
//        Composite composite = new Composite(scrolledComposite, SWT.NONE);
//        scrolledComposite.setContent(composite);
        text1 = new Text(secondLine, SWT.MULTI);
        text1.setText("本页面的软件遵照GPL协议开放源代码，您可以自由传播和修改，在遵照下面的约束条件的前提下：\n" +
                " \n" +
                "一. 只要你在批量文件名修改器开源软件的每一副本上明显和恰当地出版版权声明，保持此许可证的声明和没有担保的声明完整无损，并和程序一起给每个其他的程序接受者一份许可证的副本，你就可以用任何媒体复制和发布你收到的原始的程序的源代码。你也可以为转让副本的实际行动收取一定费用，但必须事先得到的同意。\n" +
                " \n" +
                "二.你可以修改批量文件名修改器开源软件的一个或几个副本或程序的任何部分，以此形成基于程序的作品。只要你同时满足下面的所有条件，你就可以按前面第一款的要求复制和发布这一经过修改的程序或作品。\n" +
                " \n" +
                "1. 你必须在修改的文件中附有明确的说明：你修改了这一文件及具体的修改日期。\n" +
                " \n" +
                "2. 你必须使你发布或出版的作品（它包含程序的全部或一部分，或包含由程序的全部或部分衍生的作品）允许第三方作为整体按许可证条款免费使用。\n" +
                " \n" +
                "3. 如果修改的程序在运行时以交互方式读取命令，你必须使它在开始进入常规的交互使用方式时打印或显示声明：包括适当的版权声明和没有担保的声明（或者你提供担保的声明）；用户可以按此许可证条款重新发布程序的说明；并告诉用户如何看到这一许可证的副本。（例外的情况：如果原始程序以交互方式工作，它并不打印这样的声明，你的基于程序的作品也就不用打印声明。\n" +
                " \n" +
                "三.只要你遵循一、二条款规定，您就可以自由使用并传播本源代码，但必须原封不动地保留原作者信息");
    }

    @Override
    public ColumnData[] execute(java.util.List<File> fileList)
    {
        java.util.List<String> list = new ArrayList<String>();
        ColumnData columnData = new ColumnData("空列", list);
        return new ColumnData[]{columnData};
    }

    public Text getText1()
    {
        return text1;
    }

    public void setText1(Text text1)
    {
        this.text1 = text1;
    }

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }
}

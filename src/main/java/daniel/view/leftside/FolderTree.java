package daniel.view.leftside;

import daniel.util.DiskDetect;
import daniel.util.IconDetect;
import daniel.exception.FolderUnreachableException;
import daniel.view.bottomside.StatusBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.TreeAdapter;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The reason why I didn't use the local file icon on the treeItem
 * is the transform between local and Java is not perfect
 * for there is a black frame around the image.
 * <p>
 * and I have optimized the speed of this tree.
 * With lower memory hold and higher speed
 * <p>
 * Attention
 * It's no need to add a ScrollComposite on tree.
 * Because the tree extends form Scrollable,it can scroll itself
 * <p>
 * 这里没有使用从本地系统获取文件的图片做树节点的图标是因为经过转换之后的图标四周有黑框
 * 也就是并不是完美的获得的图标
 * <p>
 * 第二次优化：以前是在点击展开时扫描此文件夹的子子文件夹，所以遇到深层多目录的文件夹（如Windows文件夹）时很容易卡死，
 * 现在，只在点击时扫描文件夹，所以速度更快，占用内存也更少.
 * 还有一点就是不需要给树添加滚动面板，因为树的继承树中本来就继承自Scrollable，所以当空间不够时，它自动会生成滚动条，
 * 反而添加滚动面板后会有很多不兼容的情况发生
 * <p>
 * Created by daniel chiu on 2015/4/8.
 */
public class FolderTree
{

    private Tree tree;

    /*存储树的根节点的File对象数组*/
    private List<File> files = new ArrayList<File>();
    private TreeItem[] treeItems;


    public FolderTree(Composite parent, int style, List<File> files)
    {
        tree = new Tree(parent, style);
        //文件夹树，如果没有传递文件夹的信息，那么树就显示
        if (files == null)
            return;

        this.files = files;
        initTree();
    }

    /**
     * 设置tree节点的信息
     */
    public void initTree()
    {
        for (int i = 0; i < files.size(); i++)
            initRootItem(i, files.get(i).toString());

        addTreeListener();
    }

    /**
     * 给树添加监控器
     */
    private void addTreeListener()
    {
        tree.addTreeListener(new TreeAdapter()
        {
            @Override
            public void treeExpanded(TreeEvent e)
            {
                // 首先获得触发事件的TreeItem
                TreeItem father = (TreeItem) e.item;
//                if (father.getParentItem() != null)
//                    father.setImage(ImageFactory.loadImage(father.getDisplay(), "folder_open.ico"));
                father.removeAll();
                addChildTreeItem(father);
                tree.redraw();
            }
        });
    }

    /**
     * 公开给树添加点击事件的接口
     *
     * @param selectionAdapter
     */
    public void addTreeSelectionListener(SelectionAdapter selectionAdapter)
    {
        tree.addSelectionListener(selectionAdapter);
    }

    /**
     * 初始化树的根节点信息
     *
     * @param i
     * @param filePath
     */
    private void initRootItem(int i, String filePath)
    {
        treeItems = new TreeItem[files.size()];
        treeItems[i] = new TreeItem(tree, SWT.NONE);

        treeItems[i].setText(filePath);
        //将file路径（String）和TreeItem对象绑定，便于之后获取，节省内存
        treeItems[i].setData(filePath);
        treeItems[i].setImage(IconDetect.getSWTImageFromSwing(treeItems[i].getDisplay(), new File(filePath)));

        /*给所有不管有没有子节点的节点都添加一个子节点*/
        addFadeTreeItem(treeItems[i]);

        //设置系统盘符的图标
//        String[] strings = filePath.split(":");
//        if (!strings[0].equals(DiskDetect.getSystemDisk()))
//            treeItems[i].setImage(ImageFactory.loadImage(treeItems[i].getDisplay(), "disk.ico"));
//        else treeItems[i].setImage(ImageFactory.loadImage(treeItems[i].getDisplay(), "system_disk.ico"));
    }


    /**
     * 给father这个节点添加可以添加的所有节点
     *
     * @param father
     */
    private void addChildTreeItem(TreeItem father)
    {
        String filePath = (String) father.getData();
        List<File> list = null;
        try {
            list = DiskDetect.getChildFolders(filePath);
        } catch (FolderUnreachableException e) {
            StatusBar.setStatusMessage("访问文件夹\"" + e.getMessage() + "\"的权限不够");
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++)
                newTreeItem(father, list.get(i).toString());

            if (list.size() == 0)
                StatusBar.setStatusMessage(filePath + "没有子文件夹");
        }

    }

    /**
     * 给father这个特定节点添加设置一个子节点
     *
     * @param father
     * @param filePath
     */
    private void newTreeItem(TreeItem father, String filePath)
    {
        File tmpFile = new File(filePath);
        TreeItem treeItem = new TreeItem(father, SWT.NONE);
        treeItem.setText(tmpFile.getName());
        treeItem.setData(filePath);
        treeItem.setImage(IconDetect.getSWTImageFromSwing(father.getDisplay(), tmpFile));
//        treeItem.setImage(ImageFactory.loadImage(father.getDisplay(), "folder.ico"));

        addFadeTreeItem(treeItem);
    }

    /**
     * 不管此节点有没有子节点都添加一个假的子节点，如果没有子节点，那么就不显示子节点
     *
     * @param treeItem
     */
    private void addFadeTreeItem(TreeItem treeItem)
    {
        TreeItem fadeTreeItem = new TreeItem(treeItem, SWT.NONE);
    }

}

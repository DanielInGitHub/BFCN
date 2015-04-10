package daniel.view.leftside;

import daniel.controller.DiskDetect;
import daniel.exception.FolderUnreachableException;
import daniel.view.bottomside.StatusBar;
import daniel.view.util.ImageFactory;
import org.eclipse.swt.SWT;
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
 * 这里没有使用从本地系统获取文件的图片做树节点的图标是因为经过转换之后的图标四周有黑框
 * 也就是并不是完美的获得的图标
 * Created by daniel chiu on 2015/4/8.
 */
@Deprecated
public class FolderTree_OldVersion
{

    private Tree tree;

    /*存储树的根节点的File对象数组*/
    private List<File> files = new ArrayList<File>();
    private TreeItem[] treeItems;


    public FolderTree_OldVersion(Composite parent, int style, List<File> files)
    {
        tree = new Tree(parent, style);
        this.files = files;
        initTree();
    }

    /**
     * 设置tree节点的信息
     */
    public void initTree()
    {
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);

            initRootItem(i, file.toString());
            initSecondLayerItem(treeItems[i], file.toString());
        }

        addTreeEventListener();
    }

    /**
     * 给树添加监控器
     */
    private void addTreeEventListener()
    {
        tree.addTreeListener(new TreeAdapter()
        {
            @Override
            public void treeCollapsed(TreeEvent e)
            {
                //当树的节点收缩时设置为不打开的文件夹图标
                TreeItem father = (TreeItem) e.item;
                if (father.getParentItem() != null)
                    father.setImage(ImageFactory.loadImage(father.getDisplay(), "folder.ico"));

            }

            @Override
            public void treeExpanded(TreeEvent e)
            {
                // 首先获得触发事件的TreeItem
                TreeItem father = (TreeItem) e.item;
                if (father.getParentItem() != null)
                    father.setImage(ImageFactory.loadImage(father.getDisplay(), "folder_open.ico"));
                addChildTreeItem(father);
                tree.redraw();
            }
        });
    }

    /**
     * 初始化树的次根节点信息
     *
     * @param treeItem
     * @param filePath
     */
    private void initSecondLayerItem(TreeItem treeItem, String filePath)
    {
        List<File> list = null;
        try {
            list = DiskDetect.getChildFolders(filePath);
        } catch (FolderUnreachableException e) {
            //将这里的报错文件夹(file)显示在工具栏中
            StatusBar.setStatusMessage("访问文件夹\"" + e.getMessage() + "\"的权限不够");
//            StatusBar.statusbarLabel.setText("访问文件夹\"" + e.getMessage() + "\"的权限不够");
//            StatusBar.statusbarLabel.redraw();
        }
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                newTreeItem(treeItem, list.get(i));
            }
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

        //设置系统盘符的图标
        String[] strings = filePath.split(":");
        if (!strings[0].equals(DiskDetect.getSystemDisk()))
            treeItems[i].setImage(ImageFactory.loadImage(treeItems[i].getDisplay(), "disk.ico"));
        else treeItems[i].setImage(ImageFactory.loadImage(treeItems[i].getDisplay(), "system_disk.ico"));
    }


    /**
     * 给father这个节点添加可以添加的所有节点
     *
     * @param father
     */
    private void addChildTreeItem(TreeItem father)
    {
        TreeItem[] childs = father.getItems();
        for (TreeItem child : childs) {
            //防止重复添加元素到某个节点中
            if (child.getItems().length != 0)
                return;
            String filePath = (String) child.getData();
            List<File> list = null;
            try {
                list = DiskDetect.getChildFolders(filePath);
            } catch (FolderUnreachableException e) {
                StatusBar.setStatusMessage("访问文件夹\"" + e.getMessage() + "\"的权限不够");
//                StatusBar.statusbarLabel.setText("访问文件夹\"" + e.getMessage() + "\"的权限不够");
//                StatusBar.statusbarLabel.redraw();
            }
            if (list != null)
                for (int i = 0; i < list.size(); i++) {
                    newTreeItem(child, list.get(i));
                }
        }
    }

    /**
     * 给father这个特定节点添加设置一个子节点
     *
     * @param father
     * @param file
     */
    private void newTreeItem(TreeItem father, File file)
    {
        TreeItem treeItem = new TreeItem(father, SWT.NONE);
        treeItem.setText(file.getName());
        treeItem.setData(file.toString());

        treeItem.setImage(ImageFactory.loadImage(father.getDisplay(), "folder.ico"));
    }

}

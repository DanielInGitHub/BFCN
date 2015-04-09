package daniel.view.leftside;

import daniel.controller.DiskDetect;
import daniel.exception.FolderUnreachableException;
import daniel.view.bottomside.StatusBar;
import daniel.view.util.ImageFactory;
import daniel.view.util.Test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.TreeAdapter;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/8.
 */
public class FolderSelector
{
    private Tree tree;
    private Tree tree2;

    //该树控件的外面添加可滚动的面板控件
    private ScrolledComposite scrolledComposite;
    private ScrolledComposite scrolledComposite2;

    public FolderSelector(Composite composite)
    {
        composite.setLayout(new FillLayout());

        //该树控件的外面添加可滚动的面板控件
        scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        scrolledComposite.setLayout(new FillLayout());
        tree = new Tree(scrolledComposite, SWT.SINGLE);
        scrolledComposite.setContent(tree);

//        Composite child1 = new Composite(scrolledComposite, SWT.NONE);
//        child1.setLayout(new FillLayout());
//        scrolledComposite.setContent(child1);
//        GridData data = new GridData(GridData.FILL_BOTH);
//        Text text = new Text(child1, SWT.MULTI);
//        text.setLayoutData(data);
//        text.setText("test1");

        scrolledComposite2 = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite2.setLayout(new FillLayout());
        tree2 = new Tree(scrolledComposite2, SWT.SINGLE);
        scrolledComposite2.setContent(tree2);

//        Composite child2 = new Composite(scrolledComposite2, SWT.NONE);
//        child2.setLayout(new FillLayout());
//        scrolledComposite2.setContent(child2);
//        Text text2 = new Text(child1, SWT.MULTI);
//        GridData data2 = new GridData(GridData.FILL_BOTH);
//        child2.setLayoutData(data2);
//        text2.setText("test2");
//        new Text(child2, SWT.MULTI).setText("test2");
        ((SashForm) composite).setWeights(new int[]{30, 70});

        setTreeInfo();
        setTreeInfo2();

        tree.setSize(500, 500);
        tree2.setSize(500, 500);
    }

    /**
     * 设置tree节点的信息
     */
    private void setTreeInfo()
    {
        //获得所有可操作的磁盘
        List<File> files = DiskDetect.getEffectiveDisks();
        for (int i = 0; i < files.size(); i++) {
            TreeItem root = new TreeItem(tree, SWT.NONE);

            File file = files.get(i);
            root.setText(file.toString());
            //将file对象和TreeItem对象绑定，便于之后获取
            root.setData(file);
            String[] strings = file.getAbsolutePath().split(":");
            //设置系统盘符的图标
            if (!strings[0].equals(DiskDetect.getSystemDisk()))
                root.setImage(ImageFactory.loadImage(root.getDisplay(), "disk.ico"));
            else root.setImage(ImageFactory.loadImage(root.getDisplay(), "c.ico"));

            try {
                List<File> list = DiskDetect.getChildFolders(file);
                for (int j = 0; j < list.size(); j++) {
                    newTreeItem(root, list.get(j));
                }
            } catch (FolderUnreachableException e) {
                //之后可以将这里的报错文件夹显示在工具栏中
                String message = e.getMessage();
                StatusBar.statusbarLabel.setText("文件夹\"" + message + "\"无法访问");
                StatusBar.statusbarLabel.redraw();
//                e.printStackTrace();
            }
        }

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

    private void setTreeInfo2()
    {
        //获得所有可操作的磁盘
        List<File> files = DiskDetect.getEffectiveDisks();
        for (int i = 0; i < files.size(); i++) {
            TreeItem root = new TreeItem(tree2, SWT.NONE);

            File file = files.get(i);
            root.setText(file.toString());
            //将file对象和TreeItem对象绑定，便于之后获取
            root.setData(file);
            String[] strings = file.getAbsolutePath().split(":");
            //设置系统盘符的图标
            if (!strings[0].equals(DiskDetect.getSystemDisk()))
                root.setImage(ImageFactory.loadImage(root.getDisplay(), "disk.ico"));
            else root.setImage(ImageFactory.loadImage(root.getDisplay(), "c.ico"));

            try {
                List<File> list = DiskDetect.getChildFolders(file);
                for (int j = 0; j < list.size(); j++) {
                    newTreeItem(root, list.get(j));
                }
            } catch (FolderUnreachableException e) {
                //之后可以将这里的报错文件夹显示在工具栏中
                String message = e.getMessage();
                StatusBar.statusbarLabel.setText("文件夹\"" + message + "\"无法访问");
                StatusBar.statusbarLabel.redraw();
            }
        }

        tree2.addTreeListener(new TreeAdapter()
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
                tree2.redraw();
            }
        });
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
//            if (child.getItems() != null)
//                return;
            File file = (File) child.getData();
            try {
                List<File> list = DiskDetect.getChildFolders(file);
                for (int i = 0; i < list.size(); i++) {
                    newTreeItem(child, list.get(i));
                }
            } catch (FolderUnreachableException e) {
                String message = e.getMessage();
                StatusBar.statusbarLabel.setText("文件夹\"" + message + "\"无法访问");
                StatusBar.statusbarLabel.redraw();
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
        treeItem.setData(file);
        treeItem.setImage(ImageFactory.loadImage(father.getDisplay(), "folder.ico"));
    }
}

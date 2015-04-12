package daniel.view;

import daniel.view.center.FileTable;
import daniel.view.leftside.FolderTree;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/10.
 */
public class TopPart
{
    private LeftPart leftPart;
    private RightPart rightPart;

    public TopPart(LeftPart leftPart, RightPart rightPart)
    {
        this.leftPart = leftPart;
        this.rightPart = rightPart;

        FolderTree folderTree = leftPart.getFolderTree();
        final FileTable fileTable = rightPart.getFileTable();

        folderTree.addTreeSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                Tree tree = (Tree) e.widget;
                TreeItem[] treeItems = tree.getSelection();
                List<File> folders = new ArrayList<File>();
                for (TreeItem item : treeItems) {
                    String tmp = (String) item.getData();
                    folders.add(new File(tmp));
                }
                fileTable.setFolders(folders);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e)
            {
                super.widgetDefaultSelected(e);
            }
        });
    }
}

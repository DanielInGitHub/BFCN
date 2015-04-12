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
@Deprecated
public class TopPart_1
{
    private LeftPart leftPart;
    private RightPart_1 rightPart1;

    public TopPart_1(LeftPart leftPart, RightPart_1 rightPart1)
    {
        this.leftPart = leftPart;
        this.rightPart1 = rightPart1;

        FolderTree folderTree = leftPart.getFolderTree();
        final FileTable fileTable = rightPart1.getFileTable();

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
                fileTable.refreshFiles(folders);
            }

        });
    }
}

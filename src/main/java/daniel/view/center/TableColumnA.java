package daniel.view.center;

import java.io.File;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public abstract class TableColumnA
{
    private String tableHeader;

    public TableColumnA(String tableHeader)
    {
        this.tableHeader = tableHeader;
    }

    public abstract String getColumn(File file);
}

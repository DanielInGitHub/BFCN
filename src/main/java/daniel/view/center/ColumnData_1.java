package daniel.view.center;

import org.eclipse.swt.graphics.Image;

import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class ColumnData_1
{
    private String columnName;
    private List<String> list;
    private List<Image> images;

    public ColumnData_1()
    {
    }

    public ColumnData_1(String columnName, List<String> list)
    {
        this.columnName = columnName;
        this.list = list;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public List<String> getList()
    {
        return list;
    }

    public void setList(List<String> list)
    {
        this.list = list;
    }
}

package daniel.view.center;

import org.eclipse.swt.graphics.Image;

import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class ColumnData
{
    //列名
    private String columnName;

    //一列的数据
    private List<String> list;

    //默认不用定义此项，但是如果此项被赋值，则表示折现索引代表的行不用变换
    private List<Integer> indexes;

    public ColumnData()
    {
    }

    public ColumnData(String columnName, List<String> list)
    {
        this.columnName = columnName;
        this.list = list;
    }

    public ColumnData(String columnName, List<String> list, List<Integer> indexes)
    {
        this.columnName = columnName;
        this.list = list;
        this.indexes = indexes;
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

    public List<Integer> getIndexes()
    {
        return indexes;
    }

    public void setIndexes(List<Integer> indexes)
    {
        this.indexes = indexes;
    }
}

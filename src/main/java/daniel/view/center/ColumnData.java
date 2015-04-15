package daniel.view.center;

import java.util.List;

/**
 * 这是FileTabLe的一列
 * 里面存储了该列的表头信息，和该列需要显示的数据
 * 这个类的存在主要是为了解耦和。因为不想将列的生成逻辑也放在FileTable内部所以只有独立出来
 * Created by daniel chiu on 2015/4/13.
 */
public class ColumnData
{
    //列名
    private String columnName;

    //一列的数据
    private List<String> list;

    public ColumnData()
    {
    }

    public ColumnData(String columnName, List<String> list)
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

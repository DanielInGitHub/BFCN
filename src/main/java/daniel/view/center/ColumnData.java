package daniel.view.center;

import org.eclipse.swt.graphics.Image;

import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class ColumnData
{
    private String columnName;
    private List<String> list;
    private List<Image> images;

    public ColumnData()
    {
    }

    public ColumnData(String columnName, List<String> list/*, List<Image> images*/)
    {
        this.columnName = columnName;
        this.list = list;
//        this.images = images;
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
//        if (list==null)
//            return
        return list;
    }

    public void setList(List<String> list)
    {
        this.list = list;
    }

    public List<Image> getImages()
    {
        return images;
    }

    public void setImages(List<Image> images)
    {
        this.images = images;
    }
}

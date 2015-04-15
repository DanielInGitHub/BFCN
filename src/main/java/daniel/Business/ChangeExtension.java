package daniel.Business;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类主要是对原有的文件后缀进行更改
 * Created by daniel chiu on 2015/4/13.
 */
public class ChangeExtension
{
    /*新的后缀*/
    private String newExtension;
    /*后缀是否大写*/
    private boolean capital;
    /*因为这个是直接修改后缀，所以不需要文件，直接传递文件的个数就可以*/
    private int length;

    public ChangeExtension(String newExtension, boolean capital, int length)
    {
        this.newExtension = newExtension;
        this.capital = capital;
        this.length = length;
    }

    public List<String> rule()
    {
        List<String> list = new ArrayList<String>();
        if (capital)
            for (int i = 0; i < length; i++)
                list.add(newExtension.toUpperCase());
        else
            for (int i = 0; i < length; i++)
                list.add(newExtension.toLowerCase());
        return list;
    }
}

package daniel.Business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class ChangeExtension
{
    private String newExtension;
    private boolean capital;
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

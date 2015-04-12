package daniel.view.center;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel chiu on 2015/4/11.
 */
@Deprecated
public abstract class ATableInfo
{
    private String[] headers;

    protected Map<String, GeneratorI> generators = new HashMap<String, GeneratorI>();

//    public ATableInfo(String[] headers)
//    {
//        this.headers = headers;
//    }

    public String getColumnData(String tableHeader, File file)
    {
        return generators.get(tableHeader).generateColumn(file);
    }

    public abstract GeneratorI[] generate(String[] header);

//    public abstract GeneratorI generate(String header);

    public void setGenerators()
    {
        for (int i = 0; i < headers.length; i++) {
            //写了一个回调方法
            GeneratorI[] gens = generate(headers);
            generators.put(headers[i], gens[i]);
        }
        for (int i = 0; i < headers.length; i++) {
            //写了一个回调方法
            GeneratorI[] gens = generate(headers);
            generators.put(headers[i], gens[i]);
        }
    }

    public String[] tableHeaders()
    {
        return headers;
    }

    protected void setHeaders(String[] headers)
    {
        this.headers = headers;
    }
}

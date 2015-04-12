package daniel.view.center;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel chiu on 2015/4/11.
 */
public abstract class RuleI
{
    private Map<String, String> rules/* = new HashMap<String, String>()*/;

//    private String[] header;
////    private File file;
//
//    public RuleI(String[] header)
//    {
//        this.header = header;
//    }

    /**
     *
     * @return
     */
    protected abstract Map<String, String> rule(String[] headers);

//    public void setRules()
//    {
////        rules.put()
//    }
}

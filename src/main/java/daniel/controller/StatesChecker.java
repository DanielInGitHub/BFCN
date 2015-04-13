package daniel.controller;

import java.util.*;

/**
 * Created by daniel chiu on 2015/4/13.
 */
public class StatesChecker
{
    public static List<String> checkFileName(List<String> names)
    {
        List<String> list = new ArrayList<String>();
        Set<String> strings = new HashSet<String>();
        for (String name : names) {
            if (!strings.contains(name)) {
                strings.add(name);
                list.add("可以更改");
            } else list.add("文件同名");
        }
        return list;
    }
}

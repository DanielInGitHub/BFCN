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
        List<String> strings = new ArrayList<String>();
        for (String name : names) {
            if (!strings.contains(name)) {
                strings.add(name);
                //防止经过新产生的文件名的前头部分存在空格，如果存在直接去除
                if (name.startsWith(" "))
                    for (int i = 0; i < name.length(); i++) {
                        int index = name.indexOf(" ");
                        if (index == -1)
                            break;
                        name = name.substring(index + 1, name.length());
                    }
                list.add("可以更改");
            } else {
                int index = strings.indexOf(name);
                list.set(index, "文件同名");
                list.add("文件同名");
                strings.add(name);
            }
        }
        return list;
    }
}

package daniel.controller;

import java.util.*;

/**
 * 本类是对新生成的文件名进行检查。
 * 原文件名因为存在在系统里，所以是不会出现问题，
 * 但是经过本软件的一些对文件名的更改规则，很有可能产生的文件名是相同的。
 * 本类就是检查是否存在相同吧的文件名，同时检查新生成的文件名开头是否包含空格
 * Created by daniel chiu on 2015/4/13.
 */
public class StatesChecker
{
    public static List<String> checkFileName(List<String> names)
    {
        List<String> list = new ArrayList<String>();
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (!strings.contains(name)) {
                strings.add(name);
                //防止经过新产生的文件名的前头部分存在空格，如果存在直接去除
                if (name.startsWith(" "))
                    for (int j = 0; j < name.length(); j++) {
                        int index = name.indexOf(" ");
                        if (index == -1)
                            break;
                        name = name.substring(index + 1, name.length());
                        list.set(i, name);
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

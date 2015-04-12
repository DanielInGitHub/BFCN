package daniel.view.center;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;

import java.io.File;
import java.util.Map;

/**
 * Created by daniel chiu on 2015/4/11.
 */
@Deprecated
public class FileTableInfo extends ATableInfo
{
    private String[] tmpHeaders = {"原文件名", "新文件名", "后缀", "状态"};

    private FileTableInfo()
    {
        setHeaders(tmpHeaders);
    }

    public static FileTableInfo getInstance()
    {
        return new FileTableInfo();
    }

    @Override
    public GeneratorI[] generate(String[] header)
    {
        GeneratorI[] tmpGenerators = new GeneratorI[tmpHeaders.length];

        return new GeneratorI[0];
    }


    //    @Override
    public void setGenerators()
    {
        generators.put("原文件名", new GeneratorI()
        {
            public String generateColumn(File file)
            {
                try {
                    return DiskDetect.getFilePureName(file);
                } catch (NeedFolderException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });

        generators.put("新文件名", new GeneratorI()
        {
            public String generateColumn(File file)
            {
                return "";
            }
        });

        generators.put("后缀", new GeneratorI()
        {
            public String generateColumn(File file)
            {
                try {
                    return DiskDetect.getFileExtensionName(file);
                } catch (NeedFolderException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });

        generators.put("状态", new GeneratorI()
        {
            public String generateColumn(File file)
            {
                return "";
            }
        });
    }
}

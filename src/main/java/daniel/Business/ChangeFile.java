package daniel.Business;

import daniel.controller.DiskDetect;
import daniel.exception.NeedFolderException;
import daniel.view.bottomside.StatusBar;
import daniel.view.center.ColumnData;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 本类主要是对原有的文件进行更改
 * 1）更改原有的文件名
 * 2）更改后缀
 * Created by daniel chiu on 2015/4/14.
 */
public class ChangeFile
{
    /*需要更改的文件*/
    List<File> files;
    /*更改之后的文件字符串*/
    List<String> stringlist;
    /*是否是对文件名进行修改，默认为true*/
    boolean fileName = true;

    public ChangeFile(List<File> files, List<String> stringlist, boolean fileName)
    {
        this.files = files;
        this.stringlist = stringlist;
        this.fileName = fileName;
    }

    /*将更改之后的信息已FileTable一列信息的方式传回FileTable*/
    public ColumnData execute()
    {
        Date start = new Date();
        List<String> list = new ArrayList<String>();
        File newFile = null;
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            //文件的路径可以通过获得父文件夹老获取路径
            String path = file.getParent();
            String newNameOrExtension = stringlist.get(i);
            //如果更改的是文件名
            if (fileName)
                newFile = changeFileName(newNameOrExtension, file, path);
            else
                newFile = changeFileExtension(newNameOrExtension, file, path);

            try {
                file.renameTo(newFile);
            } catch (Exception e) {
                list.add("失败");
            }
            list.add("成功");
        }

        ColumnData columnData = new ColumnData();
        columnData.setColumnName("状态");
        columnData.setList(list);

        Date end = new Date();
        long time = end.getTime() - start.getTime();
        StatusBar.setStatusMessage("耗时" + time + "");

        return columnData;
    }

    /**
     * 改变文件的后缀
     *
     * @param newExtension
     * @param file
     * @param path
     * @return
     */
    private File changeFileExtension(String newExtension, File file, String path)
    {
        String fileName = null;
        try {
            fileName = DiskDetect.getFilePureName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }

        return new File(path + "/" + fileName + "." + newExtension);
    }

    /**
     * 更改文件的名字
     *
     * @param newName
     * @param file
     * @param path
     * @return
     */
    private File changeFileName(String newName, File file, String path)
    {
        String fileExtension = null;
        try {
            fileExtension = DiskDetect.getFileExtensionName(file);
        } catch (NeedFolderException e) {
            e.printStackTrace();
        }
        return new File(path + "/" + newName + "." + fileExtension);
    }
}

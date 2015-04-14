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
 * Created by daniel chiu on 2015/4/14.
 */
public class ChangeFile
{
    List<File> files;
    List<String> stringlist;
    boolean fileName = true;

    public ChangeFile(List<File> files, List<String> stringlist, boolean fileName)
    {
        this.files = files;
        this.stringlist = stringlist;
        this.fileName = fileName;
    }

    public ColumnData execute()
    {
        Date start = new Date();
        List<String> list = new ArrayList<String>();
//        List<String> newFiles = new ArrayList<String>();
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
//                newFiles.add(file.toString());
            }
            list.add("成功");
//            newFiles.add(newFile.toString());
        }

        ColumnData columnData = new ColumnData();
        columnData.setColumnName("状态");
        columnData.setList(list);

//        ColumnData columnData2 = new ColumnData("新文件", newFiles);
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        StatusBar.setStatusMessage("耗时" + time + "");

//        return new ColumnData[]{columnData, columnData2};
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

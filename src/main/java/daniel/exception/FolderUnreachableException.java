package daniel.exception;

/**
 * 自定义异常：表示没有该文件夹的访问权限，比如某些系统文件夹
 * Created by daniel chiu on 2015/4/8.
 */
public class FolderUnreachableException extends Exception
{

    public FolderUnreachableException(String message)
    {
        super(message);
    }
}

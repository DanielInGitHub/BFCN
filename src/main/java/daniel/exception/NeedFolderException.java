package daniel.exception;

/**
 * 自定义异常：表示本来需要一个文件夹，但是传递进来的是一个文件
 * Created by daniel chiu on 2015/4/10.
 */
public class NeedFolderException extends Exception
{
    public NeedFolderException(String message)
    {
        super("\"" + message + "\"不是文件夹");
    }
}

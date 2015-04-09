/**
 * Created by daniel chiu on 2015/4/9.
 */
public class Test
{
    public static void main(String[] args)
    {
//        System.out.println(System.getProperty("user.home"));
        String string = System.getProperty("user.home");
        String[] strings = string.split(":");
        System.out.println(strings[0]);
    }
}

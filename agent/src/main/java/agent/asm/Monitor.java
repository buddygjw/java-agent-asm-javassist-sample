package agent.asm;

/**
 * @Package agent.asm
 * @Description: TODO
 * @Author guojianwei@jd.com
 * @Date 2016/12/29
 * @Time 20:35
 * @Version V1.0
 */
public class Monitor {

    static long start = 0;

    public static void start(){
        start = System.currentTimeMillis();
    }
    public static void end(){
        long end = System.currentTimeMillis();
        System.out.println("execute method use time :" + (end - start));
    }
}

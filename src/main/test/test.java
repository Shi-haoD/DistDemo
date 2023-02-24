import lsh.disk.utils.HadoopUtils;
import org.junit.Test;

public class test {
    @Test
    public void test1() throws Exception {
        String fname = "/12506/8d4bb619ebc4b7454d51da96c3fc1e178b821502.jpg";
        String basePath = "D:/ideaNew/Disk/target/Disk-1.0-SNAPSHOT/download3/";
        String uname = "/user2";
        boolean flag = HadoopUtils.download(fname, basePath, uname);
        System.out.println(flag);
    }

    @Test
    public void test2() throws Exception {
        String uname = "/user2/lll";
        boolean flag = HadoopUtils.mkdir(uname);
        System.out.println(flag);
    }

}

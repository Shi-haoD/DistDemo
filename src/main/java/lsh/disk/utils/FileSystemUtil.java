
package lsh.disk.utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

public class FileSystemUtil {
    public static final String Connction = "hdfs://192.168.1.176:9000";

    private static FileSystem fs;

    private static Configuration cof;

    static{
        cof = new Configuration();
        cof.set(FileSystem.FS_DEFAULT_NAME_KEY,Connction);
        //System.setProperty("hadoop.home.dir", "/usr/local/hadoop/hadoop-2.7.7/");
    }

    public static FileSystem getFileSystem()throws Exception{
        fs = FileSystem.get(cof);

    return fs;
    }

    public static void Close(FileSystem fs)throws Exception{
        if(fs!=null){
            fs.close();
        }
    }
}

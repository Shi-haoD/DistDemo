package lsh.disk.utils;

import lsh.disk.pojo.FileInfo;
import org.apache.hadoop.fs.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * uname全局使用----其作用是用户创建的时候给用户再集群分配的一个空间
 * uname作为一个路径被使用--很重要的变量,需要配合前端传输过来的用户名才能工作
 */
public class HadoopUtils {

    FileSystem fs = null;
    //上传,可以上传多个,多个上传再controller实现了
    public static void upload(String srcPath, String toPath, String username) throws Exception {
        FileSystem fs = FileSystemUtil.getFileSystem();
        //实例化本地的路径
        Path src = new Path(srcPath);
        //实例化上传路径
            Path to = new Path(username + "/" + toPath);
        //方法---从本地复制到hadoop
        fs.copyFromLocalFile(src, to);
        fs.close();

    }

    //获取名字,
    public static List<FileInfo> getFileListByName(String uname) throws Exception {
        //声明对象
        FileInfo infos = null;
        //声明数组
        List<FileInfo> list = new ArrayList<FileInfo>();
        //实例化连接对象
        FileSystem fs = FileSystemUtil.getFileSystem();
        //实例化hadoop的路径 根据用户名拼接的路径
        Path src = new Path(uname);
        //这个方法是调取文件的信息,状态
        FileStatus[] filelist = fs.listStatus(src);
        //遍历获得的文件状态
        for (int i = 0; i < filelist.length; i++) {
            //必须new对象 每次传入
            infos = new FileInfo();
            System.out.println("_________________第" + i + "个文件"
                    + "____________________");
            //获得遍历的每个文件/文件夹的状态
            FileStatus fileStatus = filelist[i];
            if (fileStatus.isDirectory()) {
                System.out.println("这个是文件夹");
            }
            if (fileStatus.isFile()) {
                System.out.println("这个是文件");
            }
            System.out.println("Name:" + fileStatus.getPath().getName());
            //使用方法取出数据
            //取出名字
            String fanme = fileStatus.getPath().getName();
            //取出文件的长度
            Long leng = fileStatus.getLen();
            //返回的是一个长整型,需要转成字符串
            String lengs = StringUtils.getSizeStr(leng);
            //获得到文件最后的操作时间----后续有用
            Long modiftime = fileStatus.getAccessTime();
            //获得是长整型的毫秒数,需要格式化成一个我们想要的时间
            Date dat = new Date(modiftime);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dat);
            //格式画
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //转化成string
            String sb = format.format(gc.getTime());
            //时间
            String owner = fileStatus.getOwner();
            String group = fileStatus.getGroup();
            ////
            System.out.println("size:" + fileStatus.getLen());
            System.out.println("_________________第" + i + "个文件"
                    + "____________________");
            //放入对象
            infos.setFname(fanme);
            infos.setSize(lengs);
            infos.setDt(sb);
            infos.setOwner(owner);
            infos.setGroup(group);
            //放入集合
            list.add(infos);
        }
        return list;
    }
    public static List<FileInfo> selectAll(String uname)throws Exception{
        System.out.println("进入查询全部");
        //声明对象
        FileInfo infos = null;
        //声明数组
        List<FileInfo> list = new ArrayList<FileInfo>();
        //实例化连接对象
        FileSystem fs = FileSystemUtil.getFileSystem();
        //实例化hadoop的路径 根据用户名拼接的路径
        Path src = new Path(uname+"/");
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(src, true);
        while (iterator.hasNext()){
            LocatedFileStatus file = iterator.next();
            infos = new FileInfo();
            //取出名字
            String fanme = file.getPath().getName();
            //取出文件的长度
            Long leng = file.getLen();
            //返回的是一个长整型,需要转成字符串
            String lengs = StringUtils.getSizeStr(leng);
            //获得到文件最后的操作时间----后续有用
            Long modiftime = file.getAccessTime();
            //获得是长整型的毫秒数,需要格式化成一个我们想要的时间
            Date dat = new Date(modiftime);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dat);
            //格式画
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //转化成string
            String sb = format.format(gc.getTime());
            infos.setFname(fanme);
            infos.setSize(lengs);
            infos.setDt(sb);
            list.add(infos);
            System.out.println(sb);
            System.out.println(lengs);
        }
        return list;
    }
    //获取大小--暂时未完成
    public static Long getSize(String uname) throws Exception {
        long totalSize = 0l;
        List<FileInfo> list = new ArrayList<FileInfo>();
        FileSystem fs = FileSystemUtil.getFileSystem();
        Path src = new Path(uname);
        FileStatus status[] = fs.globStatus(src);
        if (status == null || status.length == 0) {
            throw new FileNotFoundException("Cannot access " + src +
                    ": No such file or directory.");
        }
        for (int i = 0; i < status.length; i++) {
            totalSize = fs.getContentSummary(status[i].getPath()).getLength();
            String pathStr = status[i].getPath().toString();
            System.out.println(("".equals(pathStr) ? "." : pathStr) + "\t" + totalSize);
        }
        return totalSize;

    }

    //下载
    public static boolean download(String fname, String basePath, String uname)  {
        //获取文件的集群中的路径
        Path src = new Path(uname+"/"+ fname);
        //获取要下载的本地路径
        Path to = new Path("/"+basePath);
        //这里输出一下下载路径 一面报错
        System.out.println(to);
        ///实例化连接对象
        try {
            FileSystem fs = FileSystemUtil.getFileSystem();
            //这里是下载到本地delSrc是是否删除原文件  和 usR
            fs.copyToLocalFile(false, src, to, true);
            fs.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }



    }

    //更改名字
    public static void rename(String oldname, String newname) throws Exception {
        //实例化的连接对象
        FileSystem fs = FileSystemUtil.getFileSystem();
        //旧名字
        Path oldsrc = new Path(oldname);
        //新名字
        Path newsrc = new Path(newname);

        //调用hdfs的改名方法
        boolean bl = fs.rename(oldsrc, newsrc);
        if (bl) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }

    //删除文件
    public static void deleteFile(String uname) throws Exception {
        //实例化连接对象
        FileSystem fs = FileSystemUtil.getFileSystem();
        //用户名
        Path src = new Path(uname);
        System.out.println(uname + "sasadddddddd");
        //调用hdfs的删除方法
        boolean flag = fs.delete(src, true);
        if (flag == true) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        fs.close();
    }

    //创建文件
    public static boolean mkdir(String uname) throws Exception {
        //实例化连接对象
        FileSystem fs = FileSystemUtil.getFileSystem();
        //实例化用户名路径
        Path src = new Path(uname);
        //创建文件夹方法
        boolean flag = fs.mkdirs(src);
        return flag;
    }
    //创建全部文件
    public static boolean mkdirAll(String userName)throws Exception{
        FileSystem fs = FileSystemUtil.getFileSystem();
        //实例化用户名路径
        boolean flag = false;
        Path src = new Path(userName);
        boolean flg = fs.mkdirs(src);
        boolean flag1 = fs.mkdirs(new Path(userName+"/tupian"));
        boolean flag2 = fs.mkdirs(new Path(userName+"/wendang"));
        boolean flag3 = fs.mkdirs(new Path(userName+"/shipin"));
        boolean flag4 = fs.mkdirs(new Path(userName+"/zhongzi"));
        boolean flag5 = fs.mkdirs(new Path(userName+"/yinyue"));
        boolean flag6 = fs.mkdirs(new Path(userName+"/qita"));
        if(flag1!=false&&flag2!=false&&flag3!=false&&flag4!=false&&flag5!=false&&flag6!=false){
            flag = true;
        }
        return flag;

    }

    //关闭集群
    public static void closeFS() throws Exception {
        FileSystem.closeAll();
    }
}

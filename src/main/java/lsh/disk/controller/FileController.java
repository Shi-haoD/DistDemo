package lsh.disk.controller;
import lsh.disk.pojo.FileInfo;
import lsh.disk.utils.HadoopUtils;
import lsh.disk.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import com.alibaba.fastjson.JSON;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    //上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String add(HttpServletRequest request, Model model, @RequestParam("uploads") MultipartFile[] uploads) {
        //这里需要一个uploads的参数
        /*
            这么写就行<input type="file" name="uploads" multiple="true"><span style="color:red;font-size:14px">按住Ctrl可批量上传</span>
         */
        String uname = request.getSession().getAttribute("uname").toString();
        //String uname ="/user2/12506/";//这里是用户名,用户的空间
        String destPath = request.getSession().getServletContext().getRealPath("/upload/" + uname);

        File dest = new File(destPath);
        if (!dest.exists()) {
            //如果不是文件,就创建一个文件夹
            dest.mkdirs();
        }
        try {
            for (MultipartFile f : uploads) {
                //可以上传多个文件
                File targetFile = new File(destPath, f.getOriginalFilename());
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx:" + f.getOriginalFilename());
                f.transferTo(targetFile);//上传到tomcat.//可有可无
                //调用api
                HadoopUtils.upload(destPath + "/" + f.getOriginalFilename(), f.getOriginalFilename(), uname);
            }
            request.setAttribute("msg", "上传ok");
            //返回来的<h2>${msg}</h2>这么就可以接受u一下
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "上传error");
        }
        //这个是直接把信息返回过来了
        return "upload_ret";
    }
    //文件查询
    @RequestMapping("/queryFiles")
//    @ResponseBody
    public String queryFiles(Model model, HttpSession session) {
        //给我一个用户名就可以了
        String uname = session.getAttribute("uname").toString();
        //String uname ="/user2/12506";
        try {
            List<FileInfo> infos = HadoopUtils.getFileListByName(uname);
            long filesSize = HadoopUtils.getSize(uname);//这里是
            String sizeStr = StringUtils.getSizeStr(filesSize);
            model.addAttribute("infos", infos);
            //前面""里面的是建  外面的<>{infos(对象).addAttribute里面存的}</>
            //那些信息都在pojo里面,是我用集合放进去的  里面能用的有fanme(文件名),lengs(文件大小),dt(文件修改时间),
            model.addAttribute("filesSize", sizeStr);//这个以后再说
            model.addAttribute("maxSize", 1024 * 1024 * 1024);//这个是文件总大小
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return "/listfile";
//        return JSON.toJSONString(model);
        return "listfile";
    }
    @RequestMapping("/queryFilesAll")
    @ResponseBody
    public String queryFilesAll(Model model, HttpSession session) {
        String uname = session.getAttribute("uname").toString();
        try {
            List<FileInfo> infos = HadoopUtils.selectAll(uname);
            long filesSize = HadoopUtils.getSize(uname);//这里是
            String sizeStr = StringUtils.getSizeStr(filesSize);
            model.addAttribute("infos", infos);
            //前面""里面的是建  外面的<>{infos(对象).addAttribute里面存的}</>
            //那些信息都在pojo里面,是我用集合放进去的  里面能用的有fanme(文件名),lengs(文件大小),dt(文件修改时间),
            model.addAttribute("filesSize", sizeStr);//这个以后再说
            model.addAttribute("maxSize", 1024 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ll";
    }
    //文件下载
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String fname, Model model) throws Exception {
        /**
         * 这里老师用的是控件,比较麻烦,最好模仿一下
         * 这里我需要一个文件名 , 或者多个文件名
         */
        String basePath = request.getSession().getServletContext().getRealPath("download5");
        //这是我下载的本地路径,不用管
        String uname = request.getSession().getAttribute("uname").toString();
        //String uname ="/user/12506";
        boolean flag = HadoopUtils.download(fname, basePath, uname); //从集群下载内容
        File file = new File(basePath + File.separator + fname);
        HttpHeaders headers = new HttpHeaders();//头信息
        if (flag) {
            //这边我也不太明白  , 调用了个请求 ,这个是先把内容下载到tomcat,然后调用一个游览器的下载工具
            String newfname = new String(fname.getBytes("utf-8"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", newfname);
            //MediaType:互联网媒介类型  ContentType:具体请求中的媒体类型信息。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }
        return null;
    }

    @RequestMapping("/rename")
    public String rename(String oldname, String newname, HttpSession session) {
        //给我两个名字,一个旧名字一个新名字
       // String uname = "/user2/12506";
        String uname = session.getAttribute("uname").toString();
        try {
            HadoopUtils.rename(uname + "/" + oldname, uname + "/" + newname);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/file/queryFiles";
    }

    @RequestMapping("/delete")
    public String delete(String fname, HttpSession session) throws Exception {
        //给我一个文件名旧ok了

        String uname = session.getAttribute("uname").toString();
        //String uname ="/user2/12506";
        HadoopUtils.deleteFile(uname + "/" + fname);
        return "redirect:/file/queryFiles";
    }

    @RequestMapping(value = "/batchDelete")
    public String batchDelete(String[] fnames, HttpSession session) throws Exception{
        //删除多个 , 把多个文件名传过来,然后再遍历出来 ,前台传过来的必须是个数组
        System.out.println(fnames.length);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&执行删除&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        //String uname ="/user2/12506";
       String uname = session.getAttribute("uname").toString();
        for (String fname : fnames) {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&：" + fname);
            HadoopUtils.deleteFile(uname + "/" + fname);
        }
        return "redirect:/file/queryFiles";
    }
    /*
    * 这里获取前台发送的用户输入的文件名
    * */
    @RequestMapping(value = "/mkdir")
    public void mkdir(String newfname,HttpSession session){
        //这个目前还没使用,不过应该能用  穿个名字就行
        // String uname = session.getAttribute("uname").toString();
        //这里要获取用户输入的名字
        String uname = session.getAttribute("uname").toString();
        try {
            boolean flag = HadoopUtils.mkdir(uname+"/"+newfname);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

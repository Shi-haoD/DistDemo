package lsh.disk.utils;

public class StringUtils {
    /**
     * 用户名合规校验
     * 用户名规范  字母A~Z a~z 0~9
     * @param str
     * @return
     */
    public static boolean checkStr(String str){
        char ch = ' ';
        for(int i = 0;i<str.length();i++){
            ch = str.charAt(i);
            if(ch>='A'&&ch<='Z'){
                continue;
            } else if(ch>='a'&&ch<='z'){
                continue;
            } else if(ch>='0'&&ch<='9'){
                continue;
            }
            return false;
        }
        return true;
    }

    public static String getSizeStr(long size){
        String sizestr = new String();
        if(size/1024/1024/1024 >= 1){
            sizestr = size/1024/1024/1024 + "GB";
        } else if(size/1024/1024 >= 1){
            sizestr = size/1024/1024 + "MB";
        } else if(size/1024 >= 1){
            sizestr = size/1024 + "KB";
        } else {
            sizestr = size + "B";
        }
        return sizestr;
    }
}

package Day4_JavaIO;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class FileandDirectory {
    /**
     * File类既能代表一个特定文件的名称，又能代表一个目录下的一组文件的名称
     * 如果它指的是一个文件集，就可以对此集合调用list()方法
     * FilePath对这个类来说是个更好的名字
     */


    // 判断文件名是否符合正则表达式"regex"
    class DirFilter implements FilenameFilter{
        private Pattern pattern;
        public DirFilter(String regex){ // regex即为交给list()判断的正则规范
            pattern = Pattern.compile(regex);
        }
        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }


    @Test
    public void dirList(){
        File path = new File("./src/Day2_3_Stream");

        // 查看一个目录列表
        String[] list = path.list();
        if(list!=null) {
            Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
            for(String item:list) System.out.println(item);
        }

        // 找出.java文件
        String[] javaList = path.list(new DirFilter(".*.java.*")); // .*{$}.*代表查找包含{$}的内容
        if(javaList!=null){
            for(String javaItem:javaList) System.out.println(javaItem);
        }
    }


}
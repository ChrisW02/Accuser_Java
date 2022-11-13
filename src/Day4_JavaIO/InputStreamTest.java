package Day4_JavaIO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamTest {
    /**
     * InputStream用来表示从不同数据源产生的输入
     * 这些数据源包括：（每一种数据源都有相应的InputStream子类！）
     * 字节数组；String对象；文件；FileInputStream等
     * 管道（一端输入一端输出）；PipedInputStream
     * 其他流组成的序列；SequenceInputStream
     * 其他数据源，如Internet连接等
     */

    @Test
    public void ISTest() throws IOException {
        String[] strings = {"Chris","Bob","Lily","Paul"};
        byte[] bytes = {2,5,1,6,3,2};

        /**
         * abstract int read() 读取一个字节，返回该字节，碰到结尾时返回-1
         * int read(byte[] b) 读入一个字节数组，返回实际读入的字节数，最多读入b.length个字节
         */

        ByteArrayInputStream bAIS = new ByteArrayInputStream(bytes);
        // 读取首个元素
        int first = bAIS.read();
        System.out.println(first);
        // 读取了首个元素后，流里只剩下5个元素！
        int len = bAIS.read(bytes);
        System.out.println(len);
        // 已经没有了，所以返回-1
        int last = bAIS.read();
        System.out.println(last);

        byte[] bytes2 = {2,5,1,6,3,2};
        ByteArrayInputStream bAIS2 = new ByteArrayInputStream(bytes2);
        // long skip(long n)跳过n个字节，返回实际跳过的字节数，如果碰到结尾，则停留在n
        long skipBytes = bAIS2.skip(10);
        System.out.println(skipBytes);
    }

    // 读取一个文件的所有字符，注意(i=is.read())!=-1的特殊用法
    @Test
    public void inputStreamReadTest() throws IOException {
        InputStream is = null;
        int i;
        char c;
        try{
            is = new FileInputStream("./src/Day4_JavaIO/test.txt");
            System.out.println("Characters printed:");
            while((i = is.read())!=-1){
                c = (char)i;
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null) is.close();
        }
    }

    // 实际上，采用Java7新推出的try(resource =...)会更简单
    @Test
    public void tryResourceTest() throws IOException{
        try(InputStream is = new FileInputStream("./src/Day4_JavaIO/test.txt")){
            int i;
            while((i=is.read())!=-1){
                System.out.println((char)i);
            } // 编译器自动写入finally并调用close
        }
    }

    // 使用缓冲区一次性提取多个字节
    @Test
    public void bufferedReadFileTest() throws IOException {
        try(InputStream is = new FileInputStream("./src/Day4_JavaIO/test.txt")){
            byte[] buffer = new byte[3];
            int i;
            while((i=is.read(buffer))!=-1){ // is.read(byte[] b,int off,int len)指定偏移量和最大填充数
                System.out.println("Have read "+i+" bytes.");
            }
        }
    }

    // 利用InputStream的抽象性质，构造一个StringBuilder字符串拼接器
    public String readAsString(InputStream input)throws IOException{
        int n;
        StringBuilder sb= new StringBuilder();
        while((n=input.read())!=-1){sb.append((char)n);}
        return sb.toString();
    }
    @Test
    public void readStringTest() throws IOException {
        String s;
        try(InputStream is = new FileInputStream("./src/Day4_JavaIO/test.txt")){
            s = readAsString(is);
        }
        System.out.println(s);
    }
}

package Day4_JavaIO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.Collectors;

public class IOStreamExersion {

    // 缓冲输入文件：
    // BufferReader+FileReader+Collectors.joining来拼接每一行
    public static String read(String filename) {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            return in.lines() // 按照行读取
                    .collect(Collectors.joining("  ")); // 将每个元素的内容以delimiter分开
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void bufferedInputFileTest() {
        System.out.println(
                read("./src/Day4_JavaIO/linetext.txt"));
    }

    // 格式化内存输入：
    // DataInputStream是面向字节的，而不是面向字符的！
    // 因此必须使用InputStream类而不是Reader类！

    @Test
    public void dataInputStreamTest() {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("./src/Day4_JavaIO/IOStreamExersion.java")))) {
            while (in.available() != 0) System.out.write(in.readByte());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

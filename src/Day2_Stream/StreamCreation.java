package Day2_Stream;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamCreation {



    public static <T> void show(String title, Stream<T> stream) throws IOException {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).toList();
        System.out.println(title+": ");
        for(int i = 0;i<firstElements.size();++i){
            if(i>0) System.out.println(", ");
            if(i<SIZE) System.out.println(firstElements.get(i));
            else System.out.println("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./src/Day2_Stream/A.txt");
        var contents = Files.readString(path);

        List<String> words = List.of(contents.split("\\PL+"));
        long count = 0;
        for (String w:words){
            if(w.length()>7) count++;
        }
        System.out.println(count);

        count = words.stream().filter(w->w.length()>7).count();
        System.out.println(count);

        count = words.parallelStream().filter(w->w.length()>7).count();
        System.out.println(count);

        // Arraylist <-> Array

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(2);
        // 将ArrayList转化为数组
        int[] arr = integers.stream().mapToInt(Integer::intValue).toArray();
        // 数组转化为List
        List<Integer> list = IntStream.of(arr).boxed().toList();


        }

    @Test
    public void streamTest() throws IOException {

        Path path = Paths.get("./src/Day2_Stream/A.txt");
        var contents = new String(Files.readAllBytes(path),StandardCharsets.UTF_8);

        String[] strings = new String[]{"Hello","Girls","Boys"};
        // 按数组创建流
        Stream<String> stringStream = Stream.of(strings);
        show("stringStream",stringStream);
        Stream<String> emptyStream = Stream.empty();

        // 创建常量的流
        Stream<String> echos = Stream.generate(()-> "Echo");
        show("echos",echos);
        // 随机数的流
        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);
        // 使用迭代器创建流
        Stream<BigInteger> bigintegers = Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));
        show("bigintegers",bigintegers);

        // Pattern.splitAsStream创建流
        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay",wordsAnotherWay);
        // Files.lines创建流，元素是文件中的每一行
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
            show("lines",lines);
        }

        // Iterable创建流
        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(),false);
        show("rootDirectoris",rootDirectories);

        // Iterator+Spliterators创建流
        Iterator<Path> iterator = Paths.get("./src/Day2_Stream/A.txt").iterator();
        Stream<Path> pathComponents = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator,Spliterator.ORDERED),false);
        show("pathComponents",pathComponents);
    }

    @Test
    public void MapTest() throws IOException {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("Girls");
        words.add("Boys");
        Stream<String> lowerCaseMap = words.stream().map(String::toLowerCase);
        show("lowerCaseMap",lowerCaseMap);
    }

    // 将字符串转化为字符串流
    public static Stream<String> codePoints(String s){
        var res = new ArrayList<String>();
        int i = 0;
        while(i<s.length()){
            int j = s.offsetByCodePoints(i,1);
            res.add(s.substring(i,j));
            i = j;
        }
        return res.stream();
    }

    @Test
    public void flatMapTest() throws IOException{
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("Girls");
        words.add("Boys");
        Stream<String> flatResult = words.stream().flatMap(w -> codePoints(w));
        show("flatResult",flatResult);
    }
}

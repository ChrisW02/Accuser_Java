package Day2_3_Stream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExersion extends StreamCreation{

    public static void main(String[] args) throws IOException {

        // stream.limit(n)截断前n个元素
        Stream<Double> randoms = Stream.generate(Math::random).limit(4);
        show("randoms",randoms);

        // stream.skip(n)跳过前n个元素
        var contents = Files.readString(Paths.get("./src/Day2_3_Stream/A.txt"));
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(2);
        show("words",words);

        // stream.distinct()按照原顺序去重产生
        List<String> duplicate = new ArrayList<>();
        duplicate.add("Chris");
        duplicate.add("Paul");
        duplicate.add("Chris");
        Stream<String> distinct = duplicate.stream().distinct();
        show("distinct",distinct);

        // stream.sorted()将原有流排序
        Integer[] ints = {2,1,5,3,2,4};
        Stream<Integer> sortedInts = Stream.of(ints).sorted();
        show("sortedInts",sortedInts);

        // stream.peek()在每次访问一个元素时，都调用一个函数
        Stream<Integer> peekInts = Stream.of(ints)
                .peek(e-> System.out.println("Fetching: "+e));
        show("peekInts",peekInts);


    }

    @Test
    public void whileStream() throws IOException{
        // takeWhile() 和 dropWhile() 高效选择/抛弃流中的元素，特别是在排序数组中！
        Integer[] whileInts = {
                -1,0,1,3,4,6,8,10
        };
        Stream<Integer> takeIntsStream = Stream.of(whileInts).takeWhile(i -> i<5);
        // 遇到第一个不符合条件时停止！
        show("takeIntsStream",takeIntsStream);

        Stream<Integer> dropIntsStream = Stream.of(whileInts).dropWhile(i -> i<5);
        // 遇到第一个不符合条件时停止，并返回剩余的元素！
        show("dropIntsStream",dropIntsStream);
    }

    @Test
    public void MapTest() throws IOException {
        // map():对每个元素应用操作
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
        // flatmap(): 将每个元素都转化成另一个流，再把所有的流连起来成为一个流
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("Girls");
        words.add("Boys");
        Stream<String> flatResult = words.stream().flatMap(StreamExersion::codePoints); // StreamCreation::codePoints
        show("flatResult",flatResult);

        // 利用map+flatMap+distinct找出不重复的字符！
        Stream<String> distinctFlatStream = words.stream()
                .map(e->e.split(""))
                .flatMap(Arrays::stream)
                .distinct();
        show("distinctFlatStream",distinctFlatStream);

    }

    @Test
    public void terminateStream() throws IOException {

        // count, max, min 都是约简方式
        var contents = Files.readString(Paths.get("./src/Day2_3_Stream/A.txt"));
        List<String> words = List.of(contents.split("\\PL+"));
        long count = words.stream().filter(w->w.length()>7).count();
        System.out.println(count);

        // 使用 max + Optional<String> 约简
        Stream<String> wordsStream = words.stream();
        Optional<String> max = wordsStream.max(String::compareToIgnoreCase);
        System.out.println(max);

        // 使用 min + Optional<Integer> 约简
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(5);
        ints.add(6);
        ints.add(3);
        Optional<Integer> maxInteger = ints.stream().min(Integer::compareTo);
        System.out.println(maxInteger);

        // 使用findFirst找到第一个满足条件的值
        Stream<String> wordsStream2 = words.stream(); // 注意：需要新建一个流，不能使用已经约简的流！
        Optional<String> startWith = wordsStream2.filter(e -> e.startsWith("e")).findFirst();
        // startsWith区分大小写，找不到会return Optional.empty
        System.out.println(startWith);

        // findAny找到任意一个匹配的值，相对于findFirst，在并行处理时额外有效
        Stream<String> wordsStream3 = words.stream();
        Optional<String> startAny = wordsStream3.filter(e -> e.startsWith("e")).findAny();
        System.out.println(startAny);
    }

    @Test
    public void cubeStream() throws IOException{
        // 计算每个数字的立方
        ArrayList<Integer> roots = new ArrayList<>();
        roots.add(1);roots.add(2);roots.add(3);roots.add(4);roots.add(5);
        List<Integer> cubes = roots.stream().map(e->e*e*e).toList();
        cubes.forEach(System.out::println);
    }

    @Test
    public void permutationStream() throws IOException{
        // 给定两个数字列表，返回所有排列后的数对
        List<Integer> first = Arrays.asList(1,2,3);
        List<Integer> second = Arrays.asList(4,5);
        List<int[]> pairs = first.stream()
                .flatMap(i -> second.stream().map(j -> (new int[]{i, j}))).toList();
        for(int[] pair:pairs){
            System.out.println(pair[0]+" "+pair[1]);
        }
    }
}
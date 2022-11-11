package Day2_3_Stream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExersion extends StreamCreation{

    public static void main(String[] args) throws IOException {

        // stream.limit(n)限制流在n个元素后结束
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
}

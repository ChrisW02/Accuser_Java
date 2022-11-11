package Day2_Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCreation {

    public static <T> void show(String title, Stream<T> stream){
        final int SIZE = 10;

        List<T> firstElements = stream.limit(SIZE+1).collect(Collectors.toList());
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
//      show("words",words);
        long count = 0;
        for (String w:words){
            if(w.length()>7) count++;
        }
        System.out.println(count);

        count = words.stream().filter(w->w.length()>7).count();
        System.out.println(count);

        count = words.parallelStream().filter(w->w.length()>7).count();
        System.out.println(count);
    }
}

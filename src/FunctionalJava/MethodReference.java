package FunctionalJava;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodReference {

    public static void main(String[] args){
        List<String> names = new ArrayList<>();

        names.add("Chris");
        names.add("Accuser");
        names.add("Bob");
        names.add("Linda");
        names.add("Mike");

        names.forEach(System.out::println);

    }

    public class Person{
        String name;
        LocalDate birthday;
        Person(String name,LocalDate bd){
            this.name = name;
            this.birthday = bd;
        }
        public static int compareAge(Person p1,Person p2){
            return p1.birthday.compareTo(p2.birthday);
        }

        @Override
        public String toString() {
            return name;
        }
    }

//    public class AgeCompare implements Comparator<Person>{
//        public int compare(Person p1, Person p2){
//            return p1.birthday.compareTo(p2.birthday);
//        }
//    }
    @Test
    public void testMethodReference(){
        Person p1 = new Person("Chris",LocalDate.of(2002,9,3));
        Person p2 = new Person("Mike",LocalDate.of(2001,10,26));
        Person p3 = new Person("Paul",LocalDate.of(2003,1,28));
        Person p4 = new Person("Bob",LocalDate.of(2000,5,14));

        Person[] people = {p1,p2,p3,p4};
        Arrays.sort(people, Person::compareAge);
        ArrayList<Person> peopleList = new ArrayList<>(List.of(people));
        peopleList.forEach(System.out::println);
    }
}
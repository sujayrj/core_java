package java8.streams;


import java8.streams.entity.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void main(String[] args) {
        //******************************************************************************************************
        //===============================
        //1. Different ways to create Stream
        //===============================

        //a. Stream.empty() : creates an empty Stream of type T
        //Method signature :  public static<T> Stream<T> empty()
        Stream<Student> emptyStream = Stream.empty();

        //b. Stream.of() : creates a Stream of single element of type T
        //Method signature : public static<T> Stream<T> of(T t)
        Stream<Integer> streamOfInts = Stream.of(5, 2, 8, 1, 3, 0);

        //c. Create Stream from Collections:
        //Every Collection type has a method stream() which returns the stream of respective collection type.
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("one");
        listOfStrings.add("two");
        listOfStrings.add("three");
        listOfStrings.add("four");
        listOfStrings.add("five");
        System.out.println("\n\n*** Create Stream from Collections ");
        listOfStrings.forEach(str -> System.out.print(str + "\t"));
        //******************************************************************************************************

        //===============================
        //2. Selecting Operations
        //===============================

        //a. filter() : Returns a stream of elements which satisfy the given predicate.
        //Method signature : Stream<T> filter(Predicate<T> predicate)
        //Intermediate Operation
        List<String> names1 = new ArrayList<>();
        names1.add("sujay");
        names1.add("ram");
        names1.add("jeppu");
        names1.add("mangalore");
        names1.add("karnataka");
        //Selecting names containing more than 5 characters
        System.out.println("\n\n*** filter() : Selecting names containing more than 5 characters ***");
        names1.stream().filter(name -> name.length() > 5).forEach(str -> System.out.print(str + "\t"));

        //b. distinct() : Selects only unique elements
        //Method Signature : Stream<T> distinct()
        //Intermediate operation
        List<String> names2 = new ArrayList<>();
        names2.add("David");
        names2.add("Johnson");
        names2.add("Samontika");
        names2.add("Brijesh");
        names2.add("John");
        names2.add("David");
        names2.add("Brijesh");
        System.out.println("\n\n*** distinct() : selects only unique elements ***");
        names2.stream().distinct().forEach(name -> System.out.print(name + "\t"));

        //c. limit() : Returns a stream containing first n elements.
        //Method Signature : Stream<T> limit(long maxSize)
        //Intermediate operation
        List<String> names3 = new ArrayList<>();
        names3.add("David");
        names3.add("Johnson");
        names3.add("Samontika");
        names3.add("Brijesh");
        names3.add("John");
        names3.add("David");
        names3.add("Brijesh");
        System.out.println("\n\n*** limit() : selects first n elements ***");
        names3.stream().limit(3).forEach(name -> System.out.print(name + "\t"));

        //d. skip() : Returns a stream after skipping first n elements.
        //Method signature : Stream<T> skip(long n)
        //Intermediate operation
        List<String> names4 = new ArrayList<>();
        names4.add("David");
        names4.add("Johnson");
        names4.add("Samontika");
        names4.add("Brijesh");
        names4.add("John");
        names4.add("David");
        names4.add("Brijesh");
        System.out.println("\n\n*** skip() : skips first n elements ***");
        names4.stream().skip(3).forEach(name -> System.out.print(name + "\t"));


        //===============================
        //3. Mapping Operations
        //===============================
        //a. map() : Returns a stream consisting of results after applying given function to elements of the stream.
        //Method signature : Stream<R> map(Function<T, R> mapper);
        //Intermediate Operation
        List<String> names5 = new ArrayList<>();
        names5.add("David");
        names5.add("Johnson");
        names5.add("Samontika");
        names5.add("Brijesh");
        names5.add("John");
        //Returns length of each name
        System.out.println("\n\n*** map() : maps name to length ***");
        names5.stream().map(name -> name.length()).forEach(len -> System.out.print(len + "\t"));


        //===============================
        //4. Sorting Operations
        //===============================
        //a. sort() :  Returns a stream consisting of elements sorted according to natural order.
        //Method signature : Stream<T> sorted()
        //Intermediate Operation
        List<String> names6 = new ArrayList<>();
        names6.add("David");
        names6.add("Johnson");
        names6.add("Brijesh");
        names6.add("John");
        //Sorting the names according to natural order
        System.out.println("\n\n*** sorted() : sorting names in natural order");
        names6.stream().sorted().forEach(name -> System.out.print(name + "\t"));

        //b. sort(Comparator) : Sorting according to supplied comparator
        //Method signature : Stream<T> sorted(Comparator<T> comparator)
        //Intermediate Operation
        //Returns a stream consisting of elements sorted according to supplied Comparator.
        List<String> names7 = new ArrayList<>();
        names7.add("David");
        names7.add("Johnson");
        names7.add("Michael");
        names7.add("John");
        //Sorting the list according the length of the names
        System.out.println("\n\n\n*** sorted() : sorting names according to length of names ***");
        //names7.stream().sorted((name1, name2) -> name1.length()-name2.length()).forEach(name -> System.out.print(name+"\t"));
        names7.stream().sorted(Comparator.comparing(String::length)).forEach(name -> System.out.print(name + "\t"));


        //===============================
        //4. Reducing Operations
        //===============================
        //Reducing operations are the operations which combine all the elements of a stream repeatedly to produce a single value.
        // For example, counting number of elements, calculating average of elements, finding maximum or minimum of elements etc.

        //a. reduce() : This method performs reduction operation on elements of a stream using initial value and binary operation.
        //Produces a single value
        //Method signature : T reduce(T identity, BinaryOperator<T> accumulator);
        //Terminal operation
        //Overloaded method 1 : takes an initial value
        int sum = Arrays.stream(new int[]{7, 5, 9, 8, 1, 2, 4}).reduce(0, (a, b) -> a + b);
        //Overloaded method 2 : does not take initial value. But returns an Optional object
        OptionalInt optionalSum = Arrays.stream(new int[]{6, 8, 0, 3, 5, 1, 2}).reduce((a, b) -> a + b);
        System.out.println("\n\n\n*** reduce() : reduce to single value - sum in this case *** ");
        System.out.println("sum : " + sum + " Optionalsum : " + optionalSum.getAsInt());

        //b. min() : Finding the minimum
        //Signature : Optional<T> min(Comparator<T> comparator)
        //Terminal Operation
        //It returns minimum element in a stream wrapped in an Optional object.
        OptionalInt min = Arrays.stream(new int[]{6, 3, 1, 8, 2, 3, 9}).min();
        System.out.println("\n\n\n*** min() : returns the minimum element in array *** ");
        System.out.println("OptionalInt : " + min.getAsInt());

        //c. max() : Finding the maximum -->  It returns maximum element in a stream wrapped in an Optional object.
        //Signature : Optional<T> max(Comparator<T> comparator)
        //Terminal Operation
        OptionalInt max = Arrays.stream(new int[]{7, 9, 1, 3, 2, 5, 6}).max();
        System.out.println("\n\n\n*** max() : returns the minimum element in array *** ");
        System.out.println("OptionalInt : " + max.getAsInt());

        //d. count() : Counting the elements
        //Method signature : long count()
        //Terminal operation
        //Returns the number of elements in a Stream
        List<String> newNames = new ArrayList<>();
        newNames.add("David");
        newNames.add("Johnson");
        newNames.add("Samontika");
        newNames.add("Brijesh");
        newNames.add("John");
        //counting the names with length>5
        long count = newNames.stream().filter(name -> name.length() > 5).count();
        System.out.println("\n\n\n*** count() : Returns the number of elements in a Stream ***");
        System.out.println("count : " + count);

        //e. collect() : Returns mutable container
        //Method signature : R collect(Collector<T> collector)
        //Terminal Operation
        //collect() method is a special case of reduction operation called mutable reduction operation because it returns mutable result container such as List or Set.
        List<String> oldNames = new ArrayList<>();
        oldNames.add("David");
        oldNames.add("Johnson");
        oldNames.add("Samontika");
        oldNames.add("Brijesh");
        oldNames.add("John");
        System.out.println("\n\n\n *** Storing the first 3 names in mutable container ***");
        List<String> firstThreeNames = oldNames.stream().limit(3).collect(Collectors.toList());
        System.out.println("First three names : " + firstThreeNames);


        //==================================
        //5. Finding and Matching Operations
        //==================================

        //a. anyMatch() : Any one element matches
        //Method signature : boolean anyMatch(Predicate<T> predicate)
        //Short-circuiting Terminal operation
        //Returns true if any one element of a stream matches with given predicate. This method may not evaluate all the elements of a stream.
        // Even if the first element matches with given predicate, it ends the operation.
        List<String> names8 = new ArrayList<>();
        names8.add("Ashok");
        names8.add("Amar");
        names8.add("Akshay");
        names8.add("Anmol");
        names8.add("Karavali");
        System.out.println("\n\n\n *** anyMatch : Matches any element with str length of 5 and terminates ***");
        if (names8.stream().anyMatch(name -> name.length() >= 5)) {
            System.out.println("Yes!, there exists name with 5 letters");
        }

        //b. allMatch() : All element matches
        //Method signature : Terminal operation
        // This method returns true if all the elements of a stream matches with given predicate. Otherwise returns false.
        List<String> names9 = new ArrayList<>();
        names9.add("Ashok");
        names9.add("Amar");
        names9.add("Akshay");
        names9.add("Anmol");
        names9.add("Karavali");
        System.out.println("\n\n\n *** allMatch : Only if all the elements in the List match length=5 ***");
        if (names9.stream().allMatch(name -> name.length() > 5)) {
            System.out.println("All are big names");
        }

        //c. noneMatch() : No element matches
        //Method Signature : boolean noneMatch(Predicate<T> predicate)
        //Terminal Operation
        //Returns true only if all the elements of a stream doesn’t match with given predicate.
        List<String> names10 = new ArrayList<>();
        names10.add("Ashok");
        names10.add("Amar");
        names10.add("Akshay");
        names10.add("Anmol");
        names10.add("Karavali");
        System.out.println("\n\n\n *** noneMatch : Returns true only if all the elements do not match the condition ***");
        if (names10.stream().noneMatch(name -> name.length() == 2)) {
            System.out.println("There is no 2 letter word in the List");
        }


        //d. findFirst() : Find the first element
        //Method Signature : Optional<T> findFirst()
        //Short-circuiting Terminal Operation
        //Returns first element of a stream wrapped in an Optional object.
        Optional<String> first = Stream.of("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh").findFirst();
        System.out.println("\n\n\n *** findFirst : Returns first element of a stream wrapped in an Optional object. ***");
        System.out.println(first.get());


        //e. findAny() : Finding any element
        //Method Signature : Optional<T> findAny()
        //Short-circuiting Terminal Operation
        //Randomly returns any one element in a stream. The result of this operation is unpredictable. It may select any element in a stream.
        //Multiple invocations on the same source may not return same result.
        Optional<String> anyElement = Stream.of("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh").findAny();
        System.out.println("\n\n\n *** findAny : Returns any element of a stream wrapped in an Optional object. ***");
        System.out.println(first.get());

    }
}


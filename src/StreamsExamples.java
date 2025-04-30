import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class demonstrates Java 8 Streams with examples of increasing complexity.
 * Each example includes both the traditional approach and the Stream approach.
 */
public class StreamsExamples {

    public static void main(String[] args) throws IOException {
        // Run all examples
        basicExample();
        filteringExample();
        mappingExample();
        flatMapExample();
        reduceExample();
        collectExample();
        groupingExample();
        parallelExample();
        fileProcessingExample();
        
        // Complex real-world scenarios
        System.out.println("\n===== COMPLEX SCENARIO: STUDENT ANALYSIS =====");
        studentAnalysisExample();
    }

    /**
     * EXAMPLE 1: Basic Stream Creation and Operations
     */
    public static void basicExample() {
        System.out.println("\n===== EXAMPLE 1: BASIC OPERATIONS =====");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Traditional approach: Sum all numbers
        System.out.println("Traditional approach:");
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println("Sum: " + sum);

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        int streamSum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum: " + streamSum);

        // Different ways to create streams
        System.out.println("\nDifferent ways to create streams:");
        
        // From a collection
        Stream<Integer> streamFromCollection = numbers.stream();
        System.out.println("From collection: " + streamFromCollection.count());
        
        // From array
        String[] array = {"a", "b", "c"};
        Stream<String> streamFromArray = Arrays.stream(array);
        System.out.println("From array: " + streamFromArray.count());
        
        // Stream.of
        Stream<String> streamOf = Stream.of("x", "y", "z");
        System.out.println("From Stream.of: " + streamOf.count());
        
        // Generate infinite stream (limited)
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);
        System.out.println("Random numbers: " + 
            randomStream.map(d -> String.format("%.4f", d)).collect(Collectors.joining(", ")));
        
        // Iterate
        Stream<Integer> iteratedStream = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("First 5 even numbers: " + 
            iteratedStream.map(String::valueOf).collect(Collectors.joining(", ")));
    }

    /**
     * EXAMPLE 2: Filtering Elements
     */
    public static void filteringExample() {
        System.out.println("\n===== EXAMPLE 2: FILTERING =====");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Traditional approach: Filter even numbers
        System.out.println("Traditional approach:");
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            }
        }
        System.out.print("Even numbers: ");
        for (Integer num : evenNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        List<Integer> streamEvenNumbers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + streamEvenNumbers);

        // Additional filtering operations
        System.out.println("\nMore filtering examples:");
        
        // Filter with multiple conditions
        List<Integer> filteredNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .filter(n -> n > 5)
                .collect(Collectors.toList());
        System.out.println("Even numbers > 5: " + filteredNumbers);
        
        // First element that satisfies condition
        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println("First even number: " + firstEven.orElse(-1));
        
        // Check if any/all elements match a condition
        boolean anyGreaterThan9 = numbers.stream().anyMatch(n -> n > 9);
        boolean allLessThan11 = numbers.stream().allMatch(n -> n < 11);
        System.out.println("Any > 9: " + anyGreaterThan9 + ", All < 11: " + allLessThan11);
    }

    /**
     * EXAMPLE 3: Mapping Elements
     */
    public static void mappingExample() {
        System.out.println("\n===== EXAMPLE 3: MAPPING =====");
        List<String> words = Arrays.asList("Java", "Streams", "Are", "Powerful");

        // Traditional approach: Convert to uppercase and get lengths
        System.out.println("Traditional approach:");
        List<String> upperCaseWords = new ArrayList<>();
        List<Integer> wordLengths = new ArrayList<>();
        for (String word : words) {
            upperCaseWords.add(word.toUpperCase());
            wordLengths.add(word.length());
        }
        System.out.println("Uppercase words: " + upperCaseWords);
        System.out.println("Word lengths: " + wordLengths);

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        List<String> streamUpperCase = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        List<Integer> streamLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Uppercase words: " + streamUpperCase);
        System.out.println("Word lengths: " + streamLengths);

        // Additional mapping operations
        System.out.println("\nMore mapping examples:");
        
        // Chain multiple map operations
        List<String> processedWords = words.stream()
                .map(String::toUpperCase)
                .map(word -> word + "!")
                .collect(Collectors.toList());
        System.out.println("Processed words: " + processedWords);
        
        // Map to objects
        List<WordInfo> wordInfos = words.stream()
                .map(word -> new WordInfo(word, word.length()))
                .collect(Collectors.toList());
        System.out.println("Word info objects: " + wordInfos);
    }

    /**
     * EXAMPLE 4: FlatMap for Working with Nested Collections
     */
    public static void flatMapExample() {
        System.out.println("\n===== EXAMPLE 4: FLATMAP =====");
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        // Traditional approach: Flatten nested lists
        System.out.println("Traditional approach:");
        List<Integer> flattenedList = new ArrayList<>();
        for (List<Integer> list : nestedLists) {
            for (Integer num : list) {
                flattenedList.add(num);
            }
        }
        System.out.println("Flattened list: " + flattenedList);

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        List<Integer> streamFlattened = nestedLists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + streamFlattened);

        // Another flatMap example with Strings
        System.out.println("\nMore flatMap examples:");
        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are awesome",
                "FlatMap is powerful"
        );
        
        // Split sentences into words and flatten
        List<String> allWords = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println("All words: " + allWords);
        
        // Count unique characters in all sentences
        long uniqueCharCount = sentences.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .distinct()
                .count();
        System.out.println("Unique character count: " + uniqueCharCount);
    }

    /**
     * EXAMPLE 5: Reduction Operations
     */
    public static void reduceExample() {
        System.out.println("\n===== EXAMPLE 5: REDUCE =====");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Traditional approach: Find product of all numbers
        System.out.println("Traditional approach:");
        int product = 1;
        for (Integer num : numbers) {
            product *= num;
        }
        System.out.println("Product: " + product);

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        int streamProduct = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + streamProduct);

        // More complex reduction example
        List<String> words = Arrays.asList("Java", "Streams", "API");
        
        // Traditional approach
        System.out.println("\nTraditional concatenation approach:");
        String joinedWords = "";
        for (String word : words) {
            if (!joinedWords.isEmpty()) {
                joinedWords += "-";
            }
            joinedWords += word;
        }
        System.out.println("Joined: " + joinedWords);
        
        // Stream approach
        System.out.println("\nStream concatenation approach:");
        String streamJoined = words.stream()
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "-" + b);
        System.out.println("Joined: " + streamJoined);
        
        // Alternative with joining collector
        String joinedWithCollector = words.stream()
                .collect(Collectors.joining("-"));
        System.out.println("Joined with collector: " + joinedWithCollector);
    }

    /**
     * EXAMPLE 6: Collecting Results
     */
    public static void collectExample() {
        System.out.println("\n===== EXAMPLE 6: COLLECT =====");
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "pear", "kiwi");

        // Traditional approach: Collect to different collections
        System.out.println("Traditional approach:");
        Set<String> fruitSet = new HashSet<>(fruits);
        System.out.println("Fruit set: " + fruitSet);
        
        Map<String, Integer> fruitLengthMap = new HashMap<>();
        for (String fruit : fruits) {
            fruitLengthMap.put(fruit, fruit.length());
        }
        System.out.println("Fruit length map: " + fruitLengthMap);

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        Set<String> streamFruitSet = fruits.stream()
                .collect(Collectors.toSet());
        System.out.println("Fruit set: " + streamFruitSet);
        
        Map<String, Integer> streamFruitLengthMap = fruits.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("Fruit length map: " + streamFruitLengthMap);

        // Advanced collecting operations
        System.out.println("\nAdvanced collecting operations:");
        
        // Join to string with custom formatting
        String joinedFruits = fruits.stream()
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined fruits: " + joinedFruits);
        
        // Collect statistics about lengths
        IntSummaryStatistics stats = fruits.stream()
                .mapToInt(String::length)
                .summaryStatistics();
        System.out.println("Length statistics: " + stats);
    }

    /**
     * EXAMPLE 7: Grouping and Partitioning
     */
    public static void groupingExample() {
        System.out.println("\n===== EXAMPLE 7: GROUPING & PARTITIONING =====");
        List<Person> people = Arrays.asList(
                new Person("John", 25),
                new Person("Alice", 22),
                new Person("Bob", 35),
                new Person("Carol", 19),
                new Person("David", 35),
                new Person("Eve", 22)
        );

        // Traditional approach: Group by age
        System.out.println("Traditional approach:");
        Map<Integer, List<Person>> peopleByAge = new HashMap<>();
        for (Person person : people) {
            if (!peopleByAge.containsKey(person.getAge())) {
                peopleByAge.put(person.getAge(), new ArrayList<>());
            }
            peopleByAge.get(person.getAge()).add(person);
        }
        System.out.println("People grouped by age: " + peopleByAge);

        // Java 8 Streams approach
        System.out.println("\nStreams approach:");
        Map<Integer, List<Person>> streamPeopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("People grouped by age: " + streamPeopleByAge);

        // Advanced grouping and partitioning
        System.out.println("\nAdvanced grouping operations:");
        
        // Partition by age > 25
        Map<Boolean, List<Person>> partitionedByAge = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 25));
        System.out.println("Partitioned by age > 25: ");
        System.out.println("  Over 25: " + partitionedByAge.get(true));
        System.out.println("  25 or younger: " + partitionedByAge.get(false));
        
        // Group by age and count people in each group
        Map<Integer, Long> peopleCountByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println("Count of people by age: " + peopleCountByAge);
        
        // Group by age and collect names
        Map<Integer, List<String>> namesByAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println("Names by age: " + namesByAge);
    }

    /**
     * EXAMPLE 8: Parallel Streams
     */
    public static void parallelExample() {
        System.out.println("\n===== EXAMPLE 8: PARALLEL STREAMS =====");
        
        // Generate a large list of numbers
        List<Integer> largeList = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        // Time sequential sum
        long startSeq = System.currentTimeMillis();
        long sequentialSum = largeList.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long endSeq = System.currentTimeMillis();
        
        // Time parallel sum
        long startPar = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long endPar = System.currentTimeMillis();
        
        System.out.println("Sequential sum: " + sequentialSum + " in " + (endSeq - startSeq) + "ms");
        System.out.println("Parallel sum: " + parallelSum + " in " + (endPar - startPar) + "ms");
        
        // Another parallel stream example with filtering and mapping
        System.out.println("\nMore parallel examples:");
        
        // Generate even numbers from 1 to 10 million, then filter and sum
        startSeq = System.currentTimeMillis();
        long seqResult = IntStream.rangeClosed(1, 10_000_000)
                .filter(n -> n % 2 == 0)
                .mapToLong(n -> (long) n * n)
                .sum();
        endSeq = System.currentTimeMillis();
        
        startPar = System.currentTimeMillis();
        long parResult = IntStream.rangeClosed(1, 10_000_000)
                .parallel()
                .filter(n -> n % 2 == 0)
                .mapToLong(n -> (long) n * n)
                .sum();
        endPar = System.currentTimeMillis();
        
        System.out.println("Sequential complex operation: " + seqResult + " in " + (endSeq - startSeq) + "ms");
        System.out.println("Parallel complex operation: " + parResult + " in " + (endPar - startPar) + "ms");
        
        // Note on parallel streams
        System.out.println("\nNote: Parallel streams are not always faster!");
        System.out.println("They work best for computationally intensive operations");
        System.out.println("and for operations that don't require ordering.");
    }

    /**
     * EXAMPLE 9: File Processing with Streams
     */
    public static void fileProcessingExample() {
        System.out.println("\n===== EXAMPLE 9: FILE PROCESSING =====");
        
        try {
            // First create a sample text file
            List<String> lines = Arrays.asList(
                    "Java 8 Streams are powerful",
                    "They make data processing easier",
                    "This is a sample file",
                    "to demonstrate file processing with streams",
                    "Streams can process large files efficiently"
            );
            Files.write(Paths.get("sample.txt"), lines);
            
            System.out.println("Traditional approach:");
            // Traditional way to count words in file
            List<String> fileLines = Files.readAllLines(Paths.get("sample.txt"));
            int wordCount = 0;
            for (String line : fileLines) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            System.out.println("Word count: " + wordCount);
            
            System.out.println("\nStreams approach:");
            // Stream way to count words
            long streamWordCount = Files.lines(Paths.get("sample.txt"))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .count();
            System.out.println("Word count: " + streamWordCount);
            
            // More file processing examples
            System.out.println("\nMore file processing examples:");
            
            // Find lines containing "Streams"
            List<String> streamLines = Files.lines(Paths.get("sample.txt"))
                    .filter(line -> line.contains("Streams"))
                    .collect(Collectors.toList());
            System.out.println("Lines containing 'Streams': " + streamLines);
            
            // Count word occurrences
            Map<String, Long> wordFrequency = Files.lines(Paths.get("sample.txt"))
                    .flatMap(line -> Arrays.stream(line.toLowerCase().split("\\s+")))
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));
            System.out.println("Word frequency: " + wordFrequency);
            
            // Cleanup
            Files.delete(Paths.get("sample.txt"));
            
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
    
    /**
     * EXAMPLE 10: Complex Real-World Scenario - Student Analysis
     */
    public static void studentAnalysisExample() {
        // Create a list of students with courses and grades
        List<Student> students = Arrays.asList(
                new Student("John", "CS", Arrays.asList(
                        new Course("Math", 85),
                        new Course("Programming", 92),
                        new Course("Databases", 78)
                )),
                new Student("Alice", "CS", Arrays.asList(
                        new Course("Math", 90),
                        new Course("Programming", 95),
                        new Course("Databases", 88)
                )),
                new Student("Bob", "Engineering", Arrays.asList(
                        new Course("Math", 75),
                        new Course("Physics", 80),
                        new Course("Mechanics", 85)
                )),
                new Student("Carol", "CS", Arrays.asList(
                        new Course("Math", 92),
                        new Course("Programming", 89),
                        new Course("Databases", 91)
                )),
                new Student("David", "Engineering", Arrays.asList(
                        new Course("Math", 78),
                        new Course("Physics", 85),
                        new Course("Mechanics", 80)
                ))
        );
        
        System.out.println("Traditional approach for complex analysis:");
        
        // 1. Find average grade per department
        Map<String, Double> deptAvgGrades = new HashMap<>();
        Map<String, Integer> deptCounts = new HashMap<>();
        
        for (Student student : students) {
            String dept = student.getDepartment();
            double studentAvg = 0.0;
            int courseCount = 0;
            
            for (Course course : student.getCourses()) {
                studentAvg += course.getGrade();
                courseCount++;
            }
            
            if (courseCount > 0) {
                studentAvg /= courseCount;
            }
            
            if (!deptAvgGrades.containsKey(dept)) {
                deptAvgGrades.put(dept, 0.0);
                deptCounts.put(dept, 0);
            }
            
            deptAvgGrades.put(dept, deptAvgGrades.get(dept) + studentAvg);
            deptCounts.put(dept, deptCounts.get(dept) + 1);
        }
        
        // Calculate final averages
        for (String dept : deptAvgGrades.keySet()) {
            if (deptCounts.get(dept) > 0) {
                deptAvgGrades.put(dept, deptAvgGrades.get(dept) / deptCounts.get(dept));
            }
        }
        
        System.out.println("Department average grades: " + deptAvgGrades);
        
        // 2. Find top student in each department
        Map<String, Student> topStudentsByDept = new HashMap<>();
        Map<String, Double> highestAvgByDept = new HashMap<>();
        
        for (Student student : students) {
            String dept = student.getDepartment();
            double studentAvg = 0.0;
            
            for (Course course : student.getCourses()) {
                studentAvg += course.getGrade();
            }
            
            studentAvg /= student.getCourses().size();
            
            if (!highestAvgByDept.containsKey(dept) || studentAvg > highestAvgByDept.get(dept)) {
                highestAvgByDept.put(dept, studentAvg);
                topStudentsByDept.put(dept, student);
            }
        }
        
        System.out.println("Top students by department: ");
        for (Map.Entry<String, Student> entry : topStudentsByDept.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue().getName());
        }
        
        // ===== STREAMS APPROACH =====
        System.out.println("\nStreams approach for complex analysis:");
        
        // 1. Find average grade per department
        Map<String, Double> streamDeptAvgGrades = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getDepartment,
                        Collectors.averagingDouble(student -> student.getCourses().stream()
                                .mapToInt(Course::getGrade)
                                .average()
                                .orElse(0.0))
                ));
        
        System.out.println("Department average grades: " + streamDeptAvgGrades);
        
        // 2. Find top student in each department
        Map<String, Optional<Student>> streamTopStudentsByDept = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(student -> 
                            student.getCourses().stream()
                                .mapToInt(Course::getGrade)
                                .average()
                                .orElse(0.0)))
                ));
        
        System.out.println("Top students by department: ");
        streamTopStudentsByDept.forEach((dept, studentOpt) -> 
            System.out.println("  " + dept + ": " + studentOpt.map(Student::getName).orElse("None"))
        );
        
        // 3. More complex analysis with streams
        System.out.println("\nMore complex stream analyses:");
        
        // Course with highest average grade across all students
        Map<String, Double> courseAverages = students.stream()
                .flatMap(student -> student.getCourses().stream())
                .collect(Collectors.groupingBy(
                        Course::getName,
                        Collectors.averagingDouble(Course::getGrade)
                ));
        
        String topCourse = courseAverages.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");
        
        System.out.println("Course with highest average grade: " + topCourse + 
                " (Avg: " + courseAverages.getOrDefault(topCourse, 0.0) + ")");
        
        // Grade distribution analysis
        Map<String, Map<String, Long>> gradeDistribution = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getDepartment,
                        Collectors.flatMapping(
                                student -> student.getCourses().stream(),
                                Collectors.groupingBy(
                                        course -> {
                                            int grade = course.getGrade();
                                            if (grade >= 90) return "A";
                                            if (grade >= 80) return "B";
                                            if (grade >= 70) return "C";
                                            if (grade >= 60) return "D";
                                            return "F";
                                        },
                                        Collectors.counting()
                                )
                        )
                ));
        
        System.out.println("Grade distribution by department: " + gradeDistribution);
    }

    /**
     * Helper class for mapping example
     */
    static class WordInfo {
        private final String word;
        private final int length;
        
        public WordInfo(String word, int length) {
            this.word = word;
            this.length = length;
        }
        
        @Override
        public String toString() {
            return word + "(" + length + ")";
        }
    }
    
    /**
     * Helper class for grouping example
     */
    static class Person {
        private final String name;
        private final int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
    
    /**
     * Helper classes for complex example
     */
    static class Student {
        private final String name;
        private final String department;
        private final List<Course> courses;
        
        public Student(String name, String department, List<Course> courses) {
            this.name = name;
            this.department = department;
            this.courses = courses;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDepartment() {
            return department;
        }
        
        public List<Course> getCourses() {
            return courses;
        }
        
        @Override
        public String toString() {
            return name + " (" + department + ")";
        }
    }
    
    static class Course {
        private final String name;
        private final int grade;
        
        public Course(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }
        
        public String getName() {
            return name;
        }
        
        public int getGrade() {
            return grade;
        }
        
        @Override
        public String toString() {
            return name + ": " + grade;
        }
    }
}
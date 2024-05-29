package memorizingtool;//Chapter 4

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.NoSuchFileException;
import java.util.*;

/**
 * Ah, the class NumberMemorize! Well, if we're following the same line of thinking, it is all about helping us remember numbers.
 * Because, let's be honest, numbers can be quite slippery and elusive sometimes.
 * <p>
 * But that's not all! It has some additional features tailored specifically for numbers like:
 * "increment" to increase the stored value by a certain amount,
 * "decrement" to decrease it, and maybe even "multiply" and "divide" to perform basic arithmetic operations.
 * <p>
 * With NumberMemorize at our disposal, we won't have to worry about forgetting or losing track of important numerical values.
 * It's like having a virtual assistant dedicated solely to keeping our numbers safe and accessible.
 */
public class NumberMemorize {

    //old version
    //static ArrayList<Integer> list = new ArrayList<>();
    //static List<Object> args = new ArrayList<>();
    //static Map<String, Class<?>[]> commands;

    //new version ##SNA
    ArrayList<Integer> list = new ArrayList<>();
    List<Object> args = new ArrayList<>();
    Map<String, Class<?>[]> commands;

    boolean finished = false;

    //that the mysterious key belonged to. She spent days poring over books in the village library, searching...
    public NumberMemorize() {
        list.clear();
        commands = new HashMap<>();
        commands.put("/help", new Class<?>[]{});
        commands.put("/menu", new Class<?>[]{});
        commands.put("/add", new Class<?>[]{int.class});
        commands.put("/remove", new Class<?>[]{int.class});
        commands.put("/replace", new Class<?>[]{int.class, int.class});
        commands.put("/replaceAll", new Class<?>[]{int.class, int.class});
        commands.put("/index", new Class<?>[]{int.class});
        commands.put("/sort", new Class<?>[]{String.class});
        commands.put("/frequency", new Class<?>[]{});
        commands.put("/print", new Class<?>[]{int.class});
        commands.put("/printAll", new Class<?>[]{String.class});
        commands.put("/getRandom", new Class<?>[]{});
        commands.put("/count", new Class<?>[]{int.class});
        commands.put("/size", new Class<?>[]{});
        commands.put("/equals", new Class<?>[]{int.class, int.class});
        commands.put("/readFile", new Class<?>[]{String.class});
        commands.put("/writeFile", new Class<?>[]{String.class});
        commands.put("/clear", new Class<?>[]{});
        commands.put("/compare", new Class<?>[]{int.class, int.class});
        commands.put("/mirror", new Class<?>[]{});
        commands.put("/unique", new Class<?>[]{});
        commands.put("/sum", new Class<?>[]{int.class, int.class});
        commands.put("/subtract", new Class<?>[]{int.class, int.class});
        commands.put("/multiply", new Class<?>[]{int.class, int.class});
        commands.put("/divide", new Class<?>[]{int.class, int.class});
        commands.put("/pow", new Class<?>[]{int.class, int.class});
        commands.put("/factorial", new Class<?>[]{int.class});
        commands.put("/sumAll", new Class<?>[]{});
        commands.put("/average", new Class<?>[]{});
        //new util command //SNA
        commands.put("/generate", new Class<?>[]{int.class});
    }

    void Run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        while (!finished) {
            args.clear();
            System.out.println("Perform action:");
            String[] data = scanner.nextLine().split(" ");

            //check if the command exist \\SNA

            if (commands.containsKey(data[0])) {
                //case of existing command

                for (int i = 1; i < data.length; i++) {
                    if (commands.get(data[0])[i - 1].equals(int.class)) {
                        if (data[i].matches("-?\\d*")) {
                            args.add(Integer.parseInt(data[i]));
                        } else {
                            System.out.println("Some arguments can't be parsed!");
                        }
                    }
                    else {
                        args.add(data[i]);
                    }
                }

                try {
                    this.getClass().getDeclaredMethod(data[0].substring(1), commands.get(data[0])).invoke(this, args.toArray());
                } catch (IllegalArgumentException e) {
                    System.out.println("Incorrect amount of arguments provided to a command");
                }
            } else {
                //case of incorrect command \\SNA
                System.out.println("No such command");
            }
        }
    }

    //for any mention of a hidden treasure or a forgotten secret that might hold the key to her discovery.
    void help() {
        System.out.println(
                "===================================================================================================================\n" +
                        "Usage: COMMAND [<TYPE> PARAMETERS]\n" +
                        "===================================================================================================================\n" +
                        "General commands:\n" +
                        "===================================================================================================================\n" +
                        "/help - Display this help message\n" +
                        "/menu - Return to the menu\n" +
                        "\n" +
                        "/add [<T> ELEMENT] - Add the specified element to the list\n" +
                        "/remove [<int> INDEX] - Remove the element at the specified index from the list\n" +
                        "/replace [<int> INDEX] [<T> ELEMENT] - Replace the element at specified index with the new one\n" +
                        "/replaceAll [<T> OLD] [<T> NEW] - Replace all occurrences of specified element with the new " +
                        "one\n" +
                        "\n" +
                        "/index [<T> ELEMENT] - Get the index of the first specified element in the list\n" +
                        "/sort [ascending/descending] - Sort the list in ascending or descending order\n" +
                        "/frequency - The frequency count of each element in the list\n" +
                        "/print [<int> INDEX] - Print the element at the specified index in the list\n" +
                        "/printAll [asList/lineByLine/oneLine] - Print all elements in the list in specified format\n" +
                        "/getRandom - Get a random element from the list\n" +
                        "/count [<T> ELEMENT] - Count the number of occurrences of the specified element in the list\n" +
                        "/size - Get the number of elements in the list\n" +
                        "/equals [<int> INDEX1] [<int> INDEX2] - Check if two elements are equal\n" +
                        "/clear - Remove all elements from the list\n" +
                        "/compare [<int> INDEX1] [<int> INDEX2] Compare elements at the specified indices in the list\n" +
                        "/mirror - Mirror elements' positions in list\n" +
                        "/unique - Unique elements in the list\n" +
                        "/readFile [<string> FILENAME] - Import data from the specified file and add it to the list\n" +
                        "/writeFile [<string> FILENAME] - Export the list data to the specified file");
        System.out.println(
                "===================================================================================================================\n" +
                        "Number-specific commands:\n" +
                        "===================================================================================================================\n" +
                        "/sum [<int> INDEX1] [<int> INDEX2] - Calculate the sum of the two specified elements\n" +
                        "/subtract [<int> INDEX1] [<int> INDEX2] - Calculate the difference between the two specified " +
                        "elements\n" +
                        "/multiply [<int> INDEX1] [<int> INDEX2] - Calculate the product of the two specified elements\n" +
                        "/divide [<int> INDEX1] [<int> INDEX2] - Calculate the division of the two specified elements\n" +
                        "/pow [<int> INDEX1] [<int> INDEX2] - Calculate the power of the specified element to the " +
                        "specified exponent element\n" +
                        "/factorial [<int> INDEX] - Calculate the factorial of the specified element\n" +
                        "/sumAll - Calculate the sum of all elements\n" +
                        "/average - Calculate the average of all elements\n" +
                        "===================================================================================================================");
    }

    //One evening, while deep in her research, Lily stumbled upon an ancient map hidden...
    void menu() {
        this.finished = true;
    }

    void add(int element) {
        list.add(element);
        System.out.println("Element " + element + " added");
    }

    void remove(int index) {
        if (index < list.size()) {
            list.remove(index);
            System.out.println("Element on " + index + " position removed");
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void replace(int index, int element) {
        if (index < list.size()) {
            list.set(index, element);
            System.out.println("Element on " + index + " position replaced with " + element);
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void replaceAll(int from, int to) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(from)) {
                list.set(i, to);
            }
        }
        System.out.println("Each " + from + " element replaced with " + to);
    }

    //within the pages of an old book. The map depicted a hidden cave at the summit of the tallest hill, rumored...
    void index(int value) {
        int index = list.indexOf(value);
        if (index != -1) {
            System.out.println("First occurrence of " + value + " is on " + index + " position");
        } else {
            System.out.println("There is no such element");
        }
    }

    void sort(String way) {

        if (way.matches("ascending|descending")) {
            //first sort ascending \\SNA
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    if (list.get(i) > list.get(j)) {
                        int temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }
            }

            //if sorting must be descending execute mirror() method \\SNA
            if (way.equals("descending")) {
                mirror();
            }

            System.out.printf("Memory sorted %s\n", way);
        } else {
            System.out.println("Incorrect argument, possible arguments: ascending, descending");
        }
    }

    void frequency() {
        if (list.isEmpty()) {
            System.out.println("There are no elements in a list");
        } else {
            Map<Integer, Long> counts = new HashMap<>();
            for (int i : list) {
                if (counts.get(i) == null) {
                    counts.put(i, 1L);
                } else {
                    counts.put(i, counts.get(i) + 1);
                }
            }

            System.out.println("Frequency:");
            for (Map.Entry<Integer, Long> entry : counts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    void print(int index) {
        if (index < list.size()) {
            System.out.println("Element on " + index + " position is " + list.get(index));
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void getRandom() {
        if (!list.isEmpty()) {
            Random random = new Random();
            int listSize = list.size();
            System.out.println("Random element: " + list.get(random.nextInt(listSize)));
        } else {
            System.out.println("There are no elements memorized");
        }
    }

    //to hold the key to unlocking unimaginable wonders. The key in Lily's...

    void printAll(String type) {
        switch (type) {
            case "asList":
                System.out.println("List of elements:\n" +
                        Arrays.toString(list.toArray()));
                break;
            case "lineByLine":
                System.out.println("List of elements:\n");
                for (int i : list) {
                    System.out.println(i);
                }
                break;
            case "oneLine":
                System.out.println("List of elements:");
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.print(list.get(i) + " ");
                }
                if (list.size() > 0)
                    System.out.print(list.get(list.size() - 1));
                System.out.println();
                break;
            default:
                System.out.println("Incorrect argument, possible arguments: asList, lineByLine, oneLine");
        }
    }

    void count(int value) {
        int amount = 0;
        for (int i : list) {
            if (i == value) {
                amount++;
            }
        }
        System.out.println("Amount of " + value + ": " + amount);
    }

    void size() {
        System.out.println("Amount of elements: " + list.size());
    }

    void equals(int i, int j) {
        if (i < list.size() && j < list.size()) {
            boolean res = list.get(i).equals(list.get(j));
            System.out.printf("%d and %d elements are%s equal: %s\n",
                    i, j, res ? "" : " not", list.get(i) + (res ? " = " : " != ") + list.get(j));
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void readFile(String path) throws IOException {
        FileReaderInteger readerThread = new FileReaderInteger();

        ArrayList<Integer> list2 = readerThread.read(path);
        list.addAll(list2);

        //original version \\SNA
        //System.out.println("Data imported: " + (list.size()));

        //corrected version \\SNA
        System.out.println("Data imported: " + (list2.size()));
    }

    void writeFile(String path) throws IOException {
        FileWriterInteger writer = new FileWriterInteger();
        writer.write(path, list);
        System.out.println("Data exported: " + list.size());
    }

    void clear() {
        list.clear();
        System.out.println("Data cleared");
    }

    //possession seemed to match the one shown on the map.
    void compare(int i, int j) {
        if (i < list.size() && j < list.size()) {
            if (list.get(i) > list.get(j)) {
                System.out.println("Result: " + list.get(i) + " > " + list.get(j));
            } else if (list.get(i) < list.get(j)) {
                System.out.println("Result: " + list.get(i) + " < " + list.get(j));
            } else {
                System.out.println("Result: " + list.get(i) + " = " + list.get(j));
            }
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    //With the map as her guide, Lily set out on an arduous journey up the treacherous hill, navigating through...
    void mirror() {

        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            list2.add(list.get(i));
        }

        list = list2;
        System.out.println("Data reversed");

    }

    void unique() {
        Map<Integer, Long> counts = new HashMap<>();
        for (int i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : counts.entrySet()) {
            list2.add(entry.getKey());
        }
        System.out.println("Unique values: " + Arrays.toString(list2.toArray()));
    }

    //dense forests and rocky terrain. After days of perseverance, she finally reached the summit and stood before...
    void sum(int i, int j) {
        if (i < list.size() && j < list.size()) {
            int a = list.get(i), b = list.get(j);

            //old version
            //int res = a + b;

            //fixed version \\SNA
            BigInteger summandA = BigInteger.valueOf(a);
            BigInteger summandB = BigInteger.valueOf(b);
            BigInteger res = summandA.add(summandB);
            System.out.printf("Calculation performed: %d + %d = %d\n", a, b, res);
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void subtract(int i, int j) {
        if (i < list.size() && j < list.size()) {
            int a = list.get(i), b = list.get(j);

            //old version
            //int res = a - b;

            //fixed version \\SNA
            BigInteger summandA = BigInteger.valueOf(a);
            BigInteger summandB = BigInteger.valueOf(b);
            BigInteger res = summandA.subtract(summandB);

            System.out.printf("Calculation performed: %d - %d = %d\n", a, b, res);
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void multiply(int i, int j) {
        if (i < list.size() && j < list.size()) {
            int a = list.get(i), b = list.get(j);

            //old version
            //int res = a * b;

            //fixed version \\SNA
            BigInteger summandA = BigInteger.valueOf(a);
            BigInteger summandB = BigInteger.valueOf(b);
            BigInteger res = summandA.multiply(summandB);
            System.out.printf("Calculation performed: %d * %d = %d\n", a, b, res);
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    void divide(int i, int j) {
        if (i < list.size() && j < list.size()) {
            int a = list.get(i), b = list.get(j);

            //old version
            //int res =  a / b;

            if (b != 0) {
                //fixed version \\SNA
                BigDecimal dividend = new BigDecimal(a);
                BigDecimal divisor = new BigDecimal(b);
                BigDecimal res = dividend.divide(divisor, 6, RoundingMode.CEILING);

                System.out.printf("Calculation performed: %d / %d = %f\n", a, b, res);
            } else {
                System.out.println("Division by zero");
            }
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    /*old version
    void pow(int i, int j) {
        if (i < list.size() && j < list.size()) {
            int a = list.get(i), b = list.get(j);
            double res = Math.pow(a, b);
            System.out.printf("Calculation performed: %d ^ %d = %f\n", a, b, res);
        } else {
            System.out.println("Index out of bounds!");
        }
    }
    */

    //fixed version ##SNA
    void pow(int i, int j) {
        if (i < list.size() && j < list.size()) {
            int a = list.get(i), b = list.get(j);

            //old version
            //long res = (long) Math.pow(a, b);

            //fixed version \\SNA
            if (b >= 0) {
                BigInteger base = BigInteger.valueOf(a);
                BigInteger res = base.pow(b);
                System.out.printf("Calculation performed: %d ^ %d = %d\n", a, b, res);

            } else {
                BigDecimal base = new BigDecimal(a);
                BigDecimal resAbs = base.pow(Math.abs(b));
                BigDecimal res = BigDecimal.ONE.divide(resAbs, 6, RoundingMode.CEILING);
                System.out.printf("Calculation performed: %d ^ %d = %.6f\n", a, b, res);
            }
        } else {
            System.out.println("Index out of bounds!");
        }
    }



    void factorial(int index) {
        if (index < list.size()) {
            if (list.get(index) < 0) {
                System.out.println("undefined");
            } else {
                long res = 1;

                //next string is fixed \\SNA
                int i = 1;
                do {
                    res = res * (i++);
                } while (i <= list.get(index));
                System.out.printf("Calculation performed: %d! = %d\n", list.get(index), res);
            }
        } else {
            System.out.println("Index out of bounds!");
        }
    }

    //the entrance of the hidden cave. With a deep breath, she inserted the silver key into the lock, and with...
    void sumAll() {
        /* old vervios
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        */

        //new fixed version \\SNA
        BigInteger sum = BigInteger.ZERO;
        BigInteger summand;
        for (int i : list) {
            summand = BigInteger.valueOf(i);
            sum = sum.add(summand);
        }

        System.out.println("Sum of all elements: " + sum);
    }

    void average() {

        /* old version

        int sum = 0;
        for (int i : list) {
            sum += i;
        }

        */

        //new fixed version \\SNA
        BigInteger sum = BigInteger.ZERO;
        BigInteger summand;
        for (int i : list) {
            summand = BigInteger.valueOf(i);
            sum = sum.add(summand);
        }

        BigDecimal sumBD = new BigDecimal(sum);
        BigDecimal divisor = new BigDecimal(list.size());
        BigDecimal result = sumBD.divide(divisor, 6, RoundingMode.CEILING);

        //next string is fixed \\SNA
        System.out.println("Average of all elements: " + result);
    }

    //method generates random elements (in number quantity) and add to the list
    void generate(int number) {
        Random r = new Random();
        for (int i = 0; i < number; i++) {
            list.add(r.nextInt());
        }
        System.out.println("Added " + number + " elements. New list size: " + list.size());
    }
}

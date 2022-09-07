//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;

import java.util.*;

import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };


        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        Map<String, Long> countUnicData = Arrays.stream(RAW_DATA)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        for (Map.Entry<String,Long> entry : countUnicData.entrySet()) {
            System.out.println("Key: "+entry.getKey());
            System.out.println("Value: "+entry.getValue());
        }

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        System.out.println();
        System.out.println("Task 2");
        System.out.println("=========================================");
         int [] array = {3,4,2,7};
         boolean finish = false;
         for (int i = 0; i < array.length && !finish; i++) {
            for (int j = 0; j < array.length; j++) {
                if (j != i && (array[i] + array[j]) == 10) {
                    System.out.println("Печатаем первую пару цифр сумма которых равна 10");
                    System.out.println("["+array[i] +", " +array[j]+"]");
                    finish = true;
                    break;
                }

            }
        }

        System.out.println();
        System.out.println("Task 3");
        System.out.println("=========================================");

        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false


    }
    public static boolean fuzzySearch (String input, String output) {
       List<Integer> check = new ArrayList<>();

             for (int i=0; i <=input.length()-1; i++) {
               for (int j = 0; j <= output.length()-1; j++) {
                if ((input.substring(i, i + 1).equals(output.substring(j, j + 1))) && (checkNumber (check, j))  ) {
                      check.add(j);
                     break;
                }
            }

        }
             if (check.size() !=input.length() ) return false;
        return true;
    }
      static boolean checkNumber (List <Integer> list, int n) {
        if (list.size()==0) return true;
            if (list.get(list.size()-1) < n) return true;
          return false;

       }

    }



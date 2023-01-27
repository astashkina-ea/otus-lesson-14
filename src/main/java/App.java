import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        //вар 1 создание стрима на сонове какого то списка
        List<Integer> numbers = Arrays.asList(11, 2, 45, 2 ,7);
        List<String> words = Arrays.asList("кот", "да", "привет", "человек", "да", "нет", "крот");
        Stream<String> streamStr = words.stream(); // создали стрим
        Stream<Integer> streamInt = numbers.stream(); // создали стрим

        //вар 2 создания стрима создаем сразу из каких то значений
        Stream<Integer> str = Stream.of(1, 32, 45, 77);

        //вар 3 создание стрима на основе какого массива
        String[] arr = {"a", "bb"};
        Stream<String> streamStrArr = Arrays.stream(arr);

//        Методы работы со стрижами
//        1. Конвейерные — возвращают другой stream, то есть работают как builder
//        2. Терминальные — возвращают другой объект, такой как коллекция, примитивы, объекты, Optional и т.д.

        //кол-во чисел больше 6
        Stream<Integer> streamInt1 = numbers.stream();
        Long c = streamInt1.filter(i ->  i > 6).count(); //filter возвращает стрим и смотрит каждое значение из списка streamInt и сравнивает с 6
        // (входное значение ->  что мы хотим с ним сделать) - лямда выражение
        // и затем count считает их кол-во
        System.out.println(c);

        //кол-во чисел больше 10 с пропуском 1ых двух
        Stream<Integer> streamInt2 = numbers.stream();
        Long c1 = streamInt2.skip(2).filter(i -> i > 10).count(); //skip - пропускает n эл-тов
        System.out.println(c1);


        //кол-во слов с кол-вом символов больше 3
        Stream<String> streamStr1 = words.stream();
        Long c2 = streamStr1.filter( s ->  s.length() > 3).count();//кол-во слов с кол-во символом больше 3
        System.out.println(c2);

        //лист из уникальных стринговых элементов
        Stream<Integer> streamInt3= numbers.stream();
        List<Integer> list = streamInt3.distinct().collect(Collectors.toList()); //distinct - возвращает стрим из уникальных значений без дубликатов
        //collect - представляет результат в виде коллекции других типов данных
        System.out.println(list);

        //лист из уникальных числовых элементов
        Stream<String> streamStr2 = words.stream();
        List<String> list1 = streamStr2.distinct().collect(Collectors.toList());
        System.out.println(list1);

        //лист из измененных элементов стрима
        Stream<String> streamStr3 = words.stream();
        List<String> list2 = streamStr3.map(s -> s + ";").collect(Collectors.toList()); //map - делает что то с каждым элементом стрима
        System.out.println(list2);

        //выведем все строки с кол-вом символов больше 3 и добавим к нему ;
        Stream<String> streamStr4 = words.stream();
        List<String> list3 = streamStr4.filter(s -> s.length() > 3).
                map(s -> s + ";").
                collect(Collectors.toList()); //map - делает что то с каждым элементом стрима
        System.out.println(list3);

        //выведем все элеметы стрима с изменением (каждый по отдельности) и выведем сам лист
        Stream<String> streamStr5 = words.stream();
        List<String> list4 = streamStr5.map(s -> s.toUpperCase(Locale.ROOT)) //для кажой строки делаем буквыу заглавное
                .peek(s -> System.out.println(s+"!!!")) //peek - возвращает тот же стрим, но применяет к каждому эл-ту стрима какую-либо функцию
                .collect(Collectors.toList());
        System.out.println(list4);

        //листр отсортированных элементов стрима
        Stream<Integer> streamInt4= numbers.stream();
        List<Integer> list5 = streamInt4.sorted().collect(Collectors.toList()); //sorted -  сортирует эл-ты стрима
        System.out.println(list5);

        //вернуть первый самый маленький элемент больше 6
        Stream<Integer> streamInt5= numbers.stream();
        Optional<Integer> result = streamInt5.sorted().filter(i -> i>6).findFirst(); //findFirst - возвращает первый элемент стрима (возвращает Optional)
        if (result.isPresent()) { //isPresent() -  проверяет если что-то внутри result
            System.out.println(result.get());
        } else {
            System.out.println("пусто");
        }

        //вернуть любой элемент стрима начинающися с К
        String result1 = streamStr.filter(s -> s.startsWith("к")).findAny().get(); //  findAny - возвращает любой подходящий элемент из стрима,возврщает Optional
        System.out.println(result1);

        //возвращает лист элементов которые начинаются на К и длина больше 3
        Stream<String> streamStr6 = words.stream();
        List<String> result2 = streamStr6.filter(s -> s.startsWith("к") &s.length()>3)
                        .collect(Collectors.toList());
                System.out.println(result2);

        //есть ли строка у которой кол-во символов больше 3
        Stream<String> streamStr7 = words.stream();
        boolean res = streamStr7.anyMatch(s -> s.length() > 3); //anyMatch - возвращает true если условие выполняется хотя бы для одного элемента
        System.out.println(res);

        //есть ли строка у которой кол-во символов не больше 10
        Stream<String> streamStr8 = words.stream();
        boolean res1 = streamStr8.noneMatch(s -> s.length() > 10); //anyMatch - возвращает true если условие НЕ выполняется ни для одного элемента
        System.out.println(res1);

        //проверяем все ли элементы стрима заканчиваются на !
        Stream<String> streamStr9 = words.stream();
        boolean res2 = streamStr9.allMatch(s -> s.endsWith("!")); //allMatch - возвращает true если условие выполняется для всех элементов
        System.out.println(res2);

        //max элемент стрима
        Stream<Integer> streamInt6 = numbers.stream();
        Integer res3 = streamInt6.max(Integer::compareTo).get(); //:: ссылка на метод compareTo класса Integer
        System.out.println(res3);

        //вывести все элементы стрима
        Stream<String> streamStr10 = words.stream();
        streamStr10.parallel().forEach(s -> System.out.println(s)); //применяется к каждому элементу стрима

        //вывести все элементы массива через стрим
        int[] arr1 = {1, 44, 55};
        Arrays.stream(arr).forEach(i -> System.out.println(i));

        //сложить все элементы стрима
        int[] arr2 = {1, 44, 55};
        int res4 = Arrays.stream(arr2).reduce((m, n) -> m + n).getAsInt();
        System.out.println(res4);



    }
}

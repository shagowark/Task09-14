package LogicModule;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Logic {
    public static List<Integer> createNewList(List<Integer> list){
        List<Integer> newList = new ArrayList<>();
        int prevElem = list.get(0);
        newList.add(prevElem);

        for (int elem : list.subList(1, list.size())){
            if (elem != prevElem){
                newList.add(elem);
            }
            prevElem = elem;
        }

        return newList;
    }

    public static void saveOutputIntoFile(String fileName, List<Integer> result) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(Arrays.toString(new List[]{result}));
        }
    }


    public static void checkIfArrayIsNull(int[] arr) throws Exception{
        if (arr == null){
            throw new Exception("Такой input-файл не существует");
        }
    }


    public static void checkIfArrayIsEmpty(int[] arr) throws Exception {
        if (arr.length == 0) {
            throw new Exception("Пустой массив");
        }
    }

}

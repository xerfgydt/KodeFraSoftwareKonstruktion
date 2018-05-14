
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static javax.print.attribute.standard.MediaSizeName.C;

public class Alice {
    static String path = System.getProperty("user.dir")+"/";



    public static void main(String[] args)
    {
        try{
            System.out.println(path);
            File file = new File(path + "Alice.txt");
            if(file.isFile()){
                Scanner scanner = new Scanner(file);
                // iterere over filen én gang
                Map<Character, Integer> map = new TreeMap<>();
                for (int i = 97; i < 123 ; i++) {
                    map.put((char)i,0); // initialisér map med alfabet
                }
                String line = "";
                double counter = 0.0;
                while(scanner.hasNextLine()){

                    line = scanner.nextLine().toLowerCase();
                    for (Character c : line.toCharArray()) {
                        if(Character.isLetter(c)) {
                            map.put(c, map.get(c) + 1);
                            counter++;
                        }
                    }
                }

                System.out.println("Count: " + counter);
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    double d = (entry.getValue() / counter ) * 100.0;
                    System.out.println("Letter " + entry.getKey() + " Occurances " + entry.getValue() +
                            " Procentvis occurrances: " + d);
                }


            }else {
                System.out.println("file not found");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        Map<String,Integer> words = new HashMap<String,Integer>();
        countEachWords(path + "Alice.txt",words);
        System.out.println("unsorted list of words " + words);

        Map<String, Integer> sortedMap = sortByValue(words);
        System.out.println("sorted list " + sortedMap);



        //ArrayList sortedWords = new ArrayList<>();
        //words.entrySet().toArray();
    }

    private static Map<String,Integer> sortByValue(Map<String,Integer> words) {
        // 1. Convert Map to List of Map
        List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String, Integer>>(words.entrySet()); // Beskriv denne linje, hvad den gør og hvorfor

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Kan skifte o1 og o2 rundt for at skifte rækkefølge

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;

    }

    public static void countEachWords(String fileName, Map<String,Integer> words){
        try {
            Scanner file = new Scanner (new File(fileName));
            while(file.hasNext()){
                String word = file.next();
                Integer count = words.get(word);
                if(count != null){
                    count++;
                    words.put(word,count);
                }
                else{
                    count  = 1;
                    words.put(word,count);
                }
                //Arrays.sort(listen, new Comparator<Map.Entry<String,Integer>>() {
                //})
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}

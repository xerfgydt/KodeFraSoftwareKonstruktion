import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



import java.io.File;
import java.net.URL;

public class URLReader {
    public static void main(String[] args) throws Exception {

        //File file = new File("AliceURL.txt");
        //file.createNewFile();

        URL oracle = new URL("https://www.gutenberg.org/files/11/11-h/11-h.htm");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        String text = "";



        while ((inputLine = in.readLine()) != null) {
            text = text + inputLine + "\n";
        }
            //System.out.println(text);

        in.close();

        Map<Character, Integer> map = new TreeMap<>();
        Scanner scanner = new Scanner(text);
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


    }





}
/*
    Map<String,Integer> words = new HashMap<String,Integer>();
    countEachWords(path + "Alice.txt",words);
        System.out.println(words);

    Map<String, Integer> sortedMap = sortByValue(words);
        System.out.println(sortedMap);



    //ArrayList sortedWords = new ArrayList<>();
    //words.entrySet().toArray();


    private static Map<String,Integer> sortByValue(Map<String, Integer> words) {
        // 1. Convert Map to List of Map
        List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String, Integer>>(words.entrySet());

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
    }
            }

        }

        }
*/
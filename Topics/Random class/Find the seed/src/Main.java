import java.util.*;

public class Main {
    public static void main(String[] args) {
        //fill the map
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Map<Integer, List<Integer>> numbers = new HashMap<>();
        for(int i = a; i <= b; i++){
            List<Integer> seq = new ArrayList<>();
            Random rand = new Random(i);
            for(int j = 0; j < n; j++){
                seq.add(rand.nextInt(0,k));
            }
            numbers.put(i, seq);
        }
/*
        //print the Map
        for(Map.Entry<Integer, List<Integer>> entry: numbers.entrySet()){
            System.out.print(entry.getKey() + ":");
            for(Integer num: entry.getValue()){
                System.out.print(" " + num);
            }
            System.out.println();
        }

 */

        //fill the new map with maximum values
        Map<Integer, Integer> maximums = new HashMap<>();
        for(Map.Entry<Integer, List<Integer>> entry: numbers.entrySet()){
            int max = entry.getValue().get(0);
            for(Integer num: entry.getValue()){
                if(max < num){
                    max = num;
                }
            }
            maximums.put(entry.getKey(), max);
        }

        //search for minimum values from the maximus Map
        int min = Integer.MAX_VALUE;
        int minSeed = a;
        for(Map.Entry<Integer, Integer> entry: maximums.entrySet()){
            if(min > entry.getValue()){
                min = entry.getValue();
                minSeed = entry.getKey();
            }
        }

        System.out.println(minSeed);
        System.out.println(min);
    }
}
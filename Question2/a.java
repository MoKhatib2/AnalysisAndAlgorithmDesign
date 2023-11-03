package Question2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class a {

    public static ArrayList<ArrayList<Integer>> getPairs(int[] array, int number){
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> found = new ArrayList<Integer>();
        int[] sortedArray = mergeSort(array);
        int searchValue;
        int foundIndex;
        for(int i = 0; i<sortedArray.length; i++){
            searchValue = number - sortedArray[i];
            foundIndex = binarySearch(sortedArray, 0, sortedArray.length-1, searchValue);
            if(foundIndex!=-1 && foundIndex!=i && !found.contains(searchValue)){
                ArrayList<Integer> pair = new ArrayList<Integer>();
                pair.add(sortedArray[i]);
                pair.add(searchValue);
                pairs.add(pair);
                found.add(sortedArray[i]);
                found.add(searchValue);
            }
        }
        return pairs;
    }
    
    public static int[] mergeSort(int[] n){
        if(n.length==0 || n.length==1)
            return n;
        
        int[] left = Arrays.copyOfRange(n, 0, n.length/2);
        int[] right = Arrays.copyOfRange(n, n.length/2, n.length);
       
        int[] sortedLeft = mergeSort(left);
        int[] sortedRight = mergeSort(right);
        int[] result = new int[n.length];
        int c = 0;
        int i = 0;
        int j = 0;
        while(i<sortedLeft.length && j<sortedRight.length){
            if(sortedLeft[i] <= sortedRight[j]){
                result[c] = sortedLeft[i];
                i++;
            }else{
                result[c] = sortedRight[j];
                j++;
            }
            c++;
        }
        while(i<sortedLeft.length){
            result[c] = sortedLeft[i];
            i++;
            c++;
        }
        while(j<sortedRight.length){
            result[c] = sortedRight[j];
            j++;
            c++;
        }
        return result;
    }

    public static int binarySearch(int[] n, int begIndex, int endIndex, int searchNum){
        if(begIndex>endIndex){
            return -1;
        }
        int mid = (begIndex + endIndex)/2;
        if(searchNum == n[mid])
            return mid;
        
        if(searchNum < n[mid])
            return binarySearch(n, begIndex, mid-1, searchNum);
        
        return binarySearch(n, mid+1, endIndex, searchNum);    
    }

    public static void saveToCSV(String filename, double[] data) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 1; i<data.length; i++) {
                writer.append((i)*100000 + ",");
                writer.append("" + data[i-1]);
                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // double[] algorithmTimes = new double[10];
        // int factor = 10;
        // for (int i = 0; i < 6; i++) {
        //     int[] array = new int[factor];
        //     for(int j = 0; j < factor; j++){
        //         int num = (int)(Math.random()*10000) + 1;
        //         int sign = (int)(Math.random()*2);
        //         if(sign==1)
        //             num = num*-1;
        //         array[j] = num;
        //     }  

        //     long startTime = System.nanoTime();
        //     getPairs(array, 5000);
        //     long endTime = System.nanoTime();
        //     algorithmTimes[i] = (endTime - startTime) / 1e9;  // Convert to seconds

        //     factor*=10;
        // }

        // saveToCSV("Question2.csv", algorithmTimes);

        System.out.println(getPairs(new int[]{1,5,7,2,6,2,7,3,33,10,66}, 4));
    }
} 


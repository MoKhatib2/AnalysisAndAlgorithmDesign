package Question2;

import java.util.Arrays;

public class a {
    
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
                result[c] = sortedRight[i];
                j++;
            }
            c++;
        }
        while(i<sortedLeft.length){
            result[c] = sortedLeft[i];
            i++;
            c++;
        }
        while(j<sortedLeft.length){
            result[c] = sortedLeft[j];
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
        
} 


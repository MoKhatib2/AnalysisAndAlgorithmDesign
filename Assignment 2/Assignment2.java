public class Assignment2 {
    static double[][] scoringMatrix;
    static String[][] tracingMatrix;

    public static double getSingleScore(double[][] matrix, char[] header, char c1, char c2){
        int x = 0;
        int y = 0;
        for(int i = 0; i<header.length; i++){
            if(header[i]==c1){
                x = i;
            }
            if(header[i]==c2){
                y = i;
            }
        }

        return matrix[x][y];
    }

    public static void getScoringMatrix(String seq1, String seq2, double[][] matrix, char[] header){
        scoringMatrix = new double[seq2.length()+1][seq1.length()+1];
        tracingMatrix = new String[seq2.length()+1][seq1.length()+1];
        
        for(int i = 0; i < scoringMatrix[0].length; i++){
            char c1 ;
            char c2 = '-';
            if(i==0){
                c1 = '-';
                scoringMatrix[0][i] = getSingleScore(matrix, header, c1, c2);
                tracingMatrix[0][i] = "start";
            }else{
                c1 = seq1.charAt(i-1);
                scoringMatrix[0][i] = scoringMatrix[0][i-1] + getSingleScore(matrix, header, c1, c2);
                tracingMatrix[0][i] = "left";
            }
        }
        for(int i = 0; i < scoringMatrix.length; i++){
            char c1 = '-';
            char c2;
            if(i==0){
                c2 = '-';
                scoringMatrix[i][0] = getSingleScore(matrix, header, c1, c2);
                tracingMatrix[i][0] = "start";
            }else{
                c2 = seq2.charAt(i-1);
                scoringMatrix[i][0] = scoringMatrix[i-1][0] + getSingleScore(matrix, header, c1, c2);
                tracingMatrix[i][0] = "up";
            }
        }
        for(int i = 1; i<scoringMatrix.length; i++){
            for(int j = 1; j<scoringMatrix[i].length; j++){
                char c1;
                char c2;
                double scoreUp;
                double scoreLeft;
                double scoreDiagonal;

                c1 = '-';
                c2 = seq2.charAt(i-1);
                scoreUp = scoringMatrix[i-1][j] + getSingleScore(matrix, header, c1, c2);

                c1 = seq1.charAt(j-1);
                c2 = '-';
                scoreLeft = scoringMatrix[i][j-1] + getSingleScore(matrix, header, c1, c2);

                c1 = seq1.charAt(j-1);
                c2 = seq2.charAt(i-1);
                scoreDiagonal = scoringMatrix[i-1][j-1] + getSingleScore(matrix, header, c1, c2);

                if(scoreUp>=scoreLeft){
                    if(scoreUp>=scoreDiagonal){
                        scoringMatrix[i][j] = scoreUp;
                        tracingMatrix[i][j] = "up";
                    }else{
                        scoringMatrix[i][j] = scoreDiagonal;
                        tracingMatrix[i][j] = "diagonal";
                    }  
                }else{
                    if(scoreLeft>=scoreDiagonal){
                        scoringMatrix[i][j] = scoreLeft;
                        tracingMatrix[i][j] = "left";
                    }else{
                        scoringMatrix[i][j] = scoreDiagonal;
                        tracingMatrix[i][j] = "diagonal";
                    }
                }
            }   
        } 
    } 
    
    public static String[] trace(String seq1, String seq2){
        String s1 = "";
        String s2 = "";

        int i = tracingMatrix.length - 1;
        int j = tracingMatrix[i].length - 1;
        while(i>=0){
            while(j>=0){
                if(tracingMatrix[i][j].equals("start")){
                    i = -1;
                    j = -1;
                    break;
                }
                if(tracingMatrix[i][j].equals("up")){
                    s2 = seq2.charAt(i-1) + s2;
                    s1 = '-' + s1;
                    i = i - 1;
                }
                if(tracingMatrix[i][j].equals("left")){
                    s1 = seq1.charAt(j-1) + s1;
                    s2 = '-' + s2;
                    j = j - 1;
                }
                if(tracingMatrix[i][j].equals("diagonal")){
                    s1 = seq1.charAt(j-1) + s1;
                    s2 = seq2.charAt(i-1) + s2;
                    i = i - 1;
                    j = j - 1;
                }
            }
        }

        String[] result = new String[]{s1, s2};
        return result;
    }

    public static String[] SAP(String seq1, String seq2, double[][] matrix, char[] header){
        getScoringMatrix(seq1, seq2, matrix, header);
        return trace(seq1, seq2);
    }

    public static void main(String[] args){
        double[][] matrix = new double[][]{{1, -0.8, -0.2, -2.3, -0.6},
                                           {-0.8, 1, -1.1, -0.7, -1.5},
                                           {-0.2,-1.1 ,1, -0.5, -0.9},
                                           {-2.3, -0.7, -0.5, 1, -1},
                                           {-0.6, -1.5, -0.9, -1, 0}};                                   

        char[] header = new char[]{'A', 'G', 'T', 'C', '-'};
        
        String[] test = SAP("TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC", "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC", matrix, header);
        // getScoringMatrix("ATGCC", "TACGCA", matrix, header);
        
        // for(int i = 0; i<scoringMatrix.length; i++){
        //     for(int j = 0; j<scoringMatrix[i].length; j++){
        //         System.out.print(scoringMatrix[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // for(int i = 0; i<tracingMatrix.length; i++){
        //     for(int j = 0; j<tracingMatrix[i].length; j++){
        //         System.out.print(tracingMatrix[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        // String t1 = "--T--CC-C-AGT--TATGT-CAGGGGACACG-A-GCATGCAGA-GAC"; 
        // String t2 = "AATTGCCGCC-GTCGT-T-TTCAG----CA-GTTATG-T-CAGAT--C";
        
        double total = 0;
        for(int i = 0; i<test[0].length(); i++){
            total += getSingleScore(matrix, header, test[0].charAt(i), test[1].charAt(i));
        }

        System.out.println("total: " + total);
        System.out.println("s1: " + test[0]);
        System.out.println("s2: " + test[1]);    

    }
}


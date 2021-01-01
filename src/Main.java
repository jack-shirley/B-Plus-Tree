import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Main {



    public static void main(String[] args) {
        //Read in records and store in a temporary array list

        ArrayList<Integer> tempInput = new ArrayList<Integer>();

        try {
            File infile = new File("C:\\Users\\blitz\\Desktop\\csce-608\\project2data\\output_data\\records.txt");
            Scanner fileScanner = new Scanner(infile);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                int temp = Integer.parseInt(data);
                tempInput.add(temp);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //add spacing
        for(int z = 0; z < 5; z++){
            System.out.println();
        }


        //create dense tree, order 13
        int order = 13;  //VARIABLE CHANGE
        Tree denseTree = new Tree(tempInput, "dense", order);
        denseTree.printTree(denseTree, "Dense");

        //create sparse tree, order 13
        Tree sparseTree = new Tree(tempInput, "sparse", order);
        sparseTree.printTree(sparseTree, "Sparse");


        //create dense tree, order 24
        order = 24;
        Tree denseTree2 = new Tree(tempInput, "dense", order);
        denseTree2.printTree(denseTree2, "Dense");

        //create sparse tree, order 24
        Tree sparseTree2 = new Tree(tempInput, "sparse", order);
        sparseTree2.printTree(sparseTree2, "Sparse");





        //randomly generated insertions on dense trees

        for(int a = 0; a < 2; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Inserting Key: " + random);
            denseTree.insertKey(random, true);
        }

        for(int a = 0; a < 2; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Inserting Key: " + random);
            denseTree2.insertKey(random, true);
        }



        //randomly generated deletions on sparse trees

        for(int a = 0; a < 2; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Deleting Key: " + random);
            sparseTree.deleteKey(random);
        }

        for(int a = 0; a < 2; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Deleting Key: " + random);
            sparseTree2.deleteKey(random);
        }

        System.out.println();
        System.out.println();
        System.out.println("Random operations");
        System.out.println();
        System.out.println();

        //randomly generated insertions/deletions
        //dense tree 1
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Inserting Key: " + random);
            denseTree.insertKey(random, true);
            System.out.println();
            System.out.println();
            int random2 = 100000 + (int)(Math.random() * 200000);
            System.out.println("Deleting Key: " + random2);
            denseTree.deleteKey(random2);
            System.out.println();
            System.out.println();
        }

        //sparse tree 1
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Inserting Key: " + random);
            sparseTree.insertKey(random, true);
            System.out.println();
            System.out.println();
            int random2 = 100000 + (int)(Math.random() * 200000);
            System.out.println("Deleting Key: " + random2);
            sparseTree.deleteKey(random2);
            System.out.println();
            System.out.println();
        }

        //dense tree 2
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Inserting Key: " + random);
            denseTree2.insertKey(random, true);
            System.out.println();
            System.out.println();
            int random2 = 100000 + (int)(Math.random() * 200000);
            System.out.println("Deleting Key: " + random2);
            denseTree2.deleteKey(random2);
            System.out.println();
            System.out.println();
        }

        //sparse tree 2
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            System.out.println("Inserting Key: " + random);
            sparseTree2.insertKey(random, true);
            System.out.println();
            System.out.println();
            int random2 = 100000 + (int)(Math.random() * 200000);
            System.out.println("Deleting Key: " + random2);
            sparseTree2.deleteKey(random2);
            System.out.println();
            System.out.println();
        }


        System.out.println("Search operations");
        System.out.println();
        System.out.println();

        //randomly generated search operations
        //dense tree 1
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            denseTree.searchNodes(random, "Dense");
        }

        //sparse tree 1
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            sparseTree.searchNodes(random, "Sparse");
        }

        //dense tree 2
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            denseTree2.searchNodes(random, "Dense");
        }

        //sparse tree 2
        for(int a = 0; a < 5; a++){
            int random = 100000 + (int)(Math.random() * 200000);
            sparseTree2.searchNodes(random, "Sparse");
        }

    }
}

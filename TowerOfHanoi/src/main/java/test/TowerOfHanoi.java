package test;

 
 import java.util.*;
 
 public class TowerOfHanoi
 {

     public static Stack<Integer>[] T = new Stack[4]; 
     
     public static int totalDisk = 3;
 
     public static void main(String[] args)
     {
    	 //source
         T[1] = new Stack<Integer>();
         //auxiliary
         T[2] = new Stack<Integer>();
         //destination
         T[3] = new Stack<Integer>();
         towerOfHanoi(totalDisk);
     }

     public static void towerOfHanoi(int totalDisk)
     {
         for (int disk= totalDisk; disk > 0; disk--) {
        	 T[1].push(disk); 
         }
         //Display initial position
         viewDisks();
         //start shifting
         shiftRecursively(totalDisk, 1, 2, 3);         
     }

     public static void shiftRecursively(int diskCount, int source, int auxiliary, int destination)
     {
         if (diskCount != 0) {
        	 shiftRecursively(diskCount-1, source, destination, auxiliary);     
             int d = T[source].pop();                                             
             T[destination].push(d);
             viewDisks();                   
             shiftRecursively(diskCount-1, auxiliary, source, destination);     
         }         
     }

     public static void viewDisks()
     {
         for(int i=totalDisk-1;i>=0;i--)
         {
             String disk1, disk2, disk3;
             disk1 = T[1].size() != 0 && T[1].size() > i ? T[1].get(i).toString() : " ";
             disk2 = T[2].size() != 0 && T[2].size() > i ? T[2].get(i).toString() : " ";
             disk3 = T[3].size() != 0 && T[3].size() > i ? T[3].get(i).toString() : " ";
             System.out.println(disk1+"  "+disk2+"  "+disk3);
         }
         System.out.println("_  _  _"); 
     }
 }

package kmeans;

import java.io.IOException;
import java.util.Scanner;



public class Kmeans {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		int cont;
		System.out.println("Welcome, This program is implementation of K-Means Algorithm for IRIS Dataset");
		Scanner sc=new Scanner(System.in);
		
		do {
		
			/*
			 * Reading number of cluster (kvalue) max Iteration and Distance metrics from user. 
			 * Input file should be  name Iris.txt and present in same folder. 
			 */
		int kvalue,maxIteration;
		System.out.println("Enter number of cluster");
		kvalue=sc.nextInt();
		System.out.println("Enter the number of Iteration");
		maxIteration=sc.nextInt();
		System.out.println("Select Distance Metrics\n1. Euclidean Distance \n2. Manhattan");
		int DistanceMetric=sc.nextInt();
		
		IrisInput km=new IrisInput();
		//reading dataset
		km.loadIrisData("Iris.txt");
		
		//creating initialCluster
		km.initialCluster(kvalue,DistanceMetric);
	
		
		for(int i=0;i<maxIteration;i++)
		{
		//below method is re assigning data point to new cluster. 
		km.newClusterSelection(DistanceMetric);
					
		}
			
		System.out.println("********************Cluster Details and Data point********************************************");
			km.display();
		System.out.println("*****************************************************************");
			System.out.print("Above result based on \n1.No of Cluster="+kvalue+"\n2.Number of Iteration="+maxIteration+"\n3.Distance used=");
			if(DistanceMetric==1)
				System.out.println("Euclidean");
			else
				System.out.println("Manhattan");
			
			km.wCSS();

			
			
			System.out.println("If you want to contniue ,press 1\n");
		 cont=sc.nextInt();
		
		
		
	}while(cont==1);
		
		
		System.out.println("\n*****Programmed by Sunil Kumar Rai******");
		sc.close();
	}

}

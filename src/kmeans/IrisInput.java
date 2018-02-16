package kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class IrisInput {

	//Creating array list for Iris data set.
	ArrayList<Iris> IrisAL = new ArrayList<Iris>();

	//creating array list to store cluster details.
	ArrayList<ClusterData> ClusterAL = new ArrayList<ClusterData>();
	
	
	int numberOfCluster = 1;
	

	/*
	 * reading data 
	 */
	void loadIrisData(String filename) throws NumberFormatException, IOException {

		File file = new File(filename);

		try {
			BufferedReader readFile = new BufferedReader(new FileReader(file));
			String line;
			int rownumber = 0;
			while ((line = readFile.readLine()) != null) {

				String[] split = line.split(",");
				double[] feature = new double[split.length - 1];
				for (int i = 0; i < split.length - 1; i++)
					feature[i] = Double.parseDouble(split[i]);

				String label = split[split.length - 1];
				Iris obje = new Iris(rownumber, feature[0], feature[1], feature[2], feature[3], label);
				IrisAL.add(obje);
				rownumber++;
			}

			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//method to display element in cluster
	void display() {
		
		int i = 0;
		Iterator<ClusterData> it = ClusterAL.iterator();
		while (it.hasNext()) {
			System.out.println("ClusterID=" + (ClusterAL.get(i).clusterid) + " " +"Number of Element in cluster is" + ClusterAL.get(i).numberOfElement);
			int size = ClusterAL.get(i).index.length;
			int a[] = new int[size];
			a = ClusterAL.get(i).index;
			for (int m = 0; m < size; m++) {
				System.out.println("index="+a[m]);
				//int index = a[m];

				//System.out.println(IrisAL.get(index).rowNo + " " + IrisAL.get(index).petalLength + " "
					//	+ IrisAL.get(index).petalWidth + " " + IrisAL.get(index).sepalLenght + " "
						//+ IrisAL.get(index).sepalWidth + " " + IrisAL.get(index).Irisclass);

			}
			System.out.println();
			i++;
			it.next();
		}

	}

	
	//method to calculate initial cluster
	void initialCluster(int numberOfCluster, int distanceMetric) {

		for (int k = 0; k < numberOfCluster; k++) {
			int clusterid = k;
			//System.out.println("Cluster=" + clusterid);

			//assigning random number to cluster
			int numberOfElementInIntCluster = ThreadLocalRandom.current().nextInt(1, 10);
			//System.out.println(numberOfElementInIntCluster);

			int ii = 0;

			int[] iindex = new int[numberOfElementInIntCluster];

			while (ii < numberOfElementInIntCluster) {

				//assigning random number to row and select the row from data
				int rownumber = ThreadLocalRandom.current().nextInt(1, IrisAL.size());
				iindex[ii] = rownumber;
				ii++;

			}
			ClusterData ob = new ClusterData(clusterid, numberOfElementInIntCluster, iindex);
			ClusterAL.add(ob);
		}
		System.out.println("Initial Centroid  Distance");
	//calculating centorid for initial cluster.
	centorid(distanceMetric);
		
		
	}

	// 2d array to store clusterid and distance and centorid 
	//getting data from each cluster and calculating distance with each elements in data 
	
	
	public double[][] ClusterID_Dist;
	
	void centorid(int distanceMetric ) {
		int i = 0;
		double clusFeat[] = new double[4];
		double trainFeat[] = new double[4];
        ClusterID_Dist=new double[ClusterAL.size()][IrisAL.size()];
        
		Iterator<ClusterData> it =this.ClusterAL.iterator();

		while (it.hasNext()) {
			double petallensum = 0, petalwidsum = 0, sepallensum = 0, sepalwidsum = 0;

			int totalelement =this.ClusterAL.get(i).numberOfElement;

			// System.out.println("ClusterID="+ClusterAL.get(i).clusterid+"
			// "+"Element="+ClusterAL.get(i).numberOfElement);
			int size =this.ClusterAL.get(i).index.length;
			int a[] = new int[size];
			a = this.ClusterAL.get(i).index;
			
						for (int m = 0; m < size; m++)
						{
							// System.out.println(a[m]);
							petallensum += IrisAL.get(a[m]).petalLength;
							petalwidsum += IrisAL.get(a[m]).petalWidth;
							sepallensum += IrisAL.get(a[m]).sepalLenght;
							sepalwidsum += IrisAL.get(a[m]).sepalWidth;

						}

			//System.out.printf("petallenmean=" + petallensum / totalelement + " petalwidmean="
			//		+ petalwidsum / totalelement + " sepallenmean=" + sepallensum / totalelement + " sepalwidmean="
				//	+ sepalwidsum / totalelement+"\n");


				
				clusFeat[0] = petallensum / totalelement;
				clusFeat[1] = petalwidsum / totalelement;
				clusFeat[2] = sepallensum / totalelement;
				clusFeat[3] = sepalwidsum / totalelement;
				Distance obj = new Distance();
				for (int m = 0; m < IrisAL.size() ; m++)
					{
					
					trainFeat[0] = IrisAL.get(m).petalLength;
					trainFeat[1] = IrisAL.get(m).petalWidth;
					trainFeat[2] = IrisAL.get(m).sepalLenght;
					trainFeat[3] = IrisAL.get(m).sepalWidth;
					if(distanceMetric==1)
					{
						 ClusterID_Dist[i][m] = obj.getEuclideanDistance(clusFeat, trainFeat);
				            
					}
					
					else {
						ClusterID_Dist[i][m] = obj.getManhattanDistance(clusFeat, trainFeat);
			             
					}
	             
	             	}
		
					i++;
					it.next();
			}
		
}

	/*********************************************WCSS**********************************************************************************/
	
	void wCSS() {
		/*
		 * 
		 * this is same as calculating as centorid but 
		 * distance is being calculated only from element present in cluster and cluster mean distance.
		 * 
		 */
		int i = 0;
		double clusFeat[] = new double[4];
		double trainFeat[] = new double[4];
        ClusterID_Dist=new double[ClusterAL.size()][IrisAL.size()];
        double sse=0;	

		Iterator<ClusterData> it =this.ClusterAL.iterator();

		while (it.hasNext()) {
			double petallensum = 0, petalwidsum = 0, sepallensum = 0, sepalwidsum = 0;

			int totalelement =this.ClusterAL.get(i).numberOfElement;
			int size =this.ClusterAL.get(i).index.length;
			int a[] = new int[size];
			a = this.ClusterAL.get(i).index;
			
						for (int m = 0; m < size; m++)
						{
							// System.out.println(a[m]);
							petallensum += IrisAL.get(a[m]).petalLength;
							petalwidsum += IrisAL.get(a[m]).petalWidth;
							sepallensum += IrisAL.get(a[m]).sepalLenght;
							sepalwidsum += IrisAL.get(a[m]).sepalWidth;
						}
				
						clusFeat[0] = petallensum / totalelement;
						clusFeat[1] = petalwidsum / totalelement;
						clusFeat[2] = sepallensum / totalelement;
						clusFeat[3] = sepalwidsum / totalelement;
						Distance obj = new Distance();
				//double sse=0;
					for (int m = 0; m < size ; m++)
					{
					
					trainFeat[0] = IrisAL.get(a[m]).petalLength;
					trainFeat[1] = IrisAL.get(a[m]).petalWidth;
					trainFeat[2] = IrisAL.get(a[m]).sepalLenght;
					trainFeat[3] = IrisAL.get(a[m]).sepalWidth;
						 
	             // System.out.println(ClusterID_Dist[i][m]);
	              sse+=obj.getSSE(clusFeat, trainFeat);
					}
					//System.out.println("WCSS of Cluster"+i+"="+sse);
			i++;
			
			it.next();
			}
		System.out.println("WCSS="+sse);
}
/**********************************************************Wcc end*********************/
	
 
	void newClusterSelection(int DistanceMetric)
	{
		int numberCluster=ClusterAL.size();
		int totalElementinData=IrisAL.size();
		int ind=0;
		int newClusterID_Dist[][]=new int[numberCluster][totalElementinData];
		//******************************************
		ClusterAL.clear();
		//ClusterAL.removeAll(ClusterAL);
		ClusterAL = new ArrayList<ClusterData>();
		
		for(int element=0;element<totalElementinData;element++)
		{
			
			double minValue=Integer.MAX_VALUE;
			for(int nc=0; nc<numberCluster;nc++)
			{
				if(minValue>ClusterID_Dist[nc][element])
				{
					minValue=ClusterID_Dist[nc][element];
					ind=nc+1;
					
				}
			}
			newClusterID_Dist[ind-1][element]=ind;
			
		}
	//print assign cluster to each element
		/*for(int nc=0; nc<numberCluster;nc++)
		 
		{
			for(int element=0;element<totalElementinData;element++)
			{
				System.out.print(newClusterID_Dist[nc][element]+" ");
			}
			System.out.println();
		}*/
	
	//********************************************************
		
		int elementIndex[];
		for(int nc=0; nc<numberCluster;nc++)
		{
			int sz=0;
			for(int element=0;element<totalElementinData;element++)
			{ 
				//System.out.println((newClusterID_Dist[nc][element]));
				if((newClusterID_Dist[nc][element])==(nc+1))
					{sz++;}
			}
			
		elementIndex=new int[sz];
		int in=0;
			for(int element=0;element<totalElementinData;element++)
			{
				if(newClusterID_Dist[nc][element]==nc+1)
				{
					elementIndex[in]=element;
					in++;	
				}
					}
			ClusterData obj = new ClusterData(nc, sz, elementIndex);
			this.ClusterAL.add(obj);
			
			
		
		}	
		centorid(DistanceMetric);
		
	}


}
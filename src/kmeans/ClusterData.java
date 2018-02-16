package kmeans;

import java.util.ArrayList;

/*
 * This is data structure to stored cluster details like id, number of element in cluster and what
 * are elements present in cluster.
 * 
 */
public class ClusterData {
	
	int clusterid;
	int numberOfElement;
	int index[];
	
	public ClusterData(int clusterid, int numberOfElement, int index[]) {
		super();
		this.clusterid = clusterid;
		this.numberOfElement = numberOfElement;
		this.index=index;
	}
	 
	

}

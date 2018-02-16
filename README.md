# KMeans Clustering in Java Implementation

K-Means Algorithm
K-Means is unsupervised learning algorithm and used in clustering.

1. We are reading K-value (number of cluster ), MaxIteration and Distance metric to be used from users.
2. Load training data set.
3. Generate Random number to put number of elements in each cluster.
   Generate random number to select element from input file in the cluster.
   Calculate centroid using Euclidean or Manhattan distance.
4.Select one cluster at a time and calculate distance for each element in input file and store the distance.
5. Repeat step 4 for all clusters.
6.Compare distances of each element in input with all cluster centroids and assign element to cluster which has lowest distance.
7. Create new cluster and re-assign the elements.
8. Calculate centroids for new clusters.
9.Repeat the steps 4 to 8 till max iterations.
10. Clusters after max iterations will be final clusters.


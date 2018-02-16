package kmeans;
/*
 * 
 * 
 * this is the data structure for Iris data set and used as DS of array list.
 * 
 * 
 */
public class Iris {
	
	int rowNo;
	double petalLength;
	double petalWidth;
	double sepalLenght;
	double sepalWidth;
	String Irisclass;
	
	public Iris(int rowNo, double petalLength, double petalWidth, double sepalLenght, double sepalWidth,
			String irisclass) {
		
		this.rowNo = rowNo;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.sepalLenght = sepalLenght;
		this.sepalWidth = sepalWidth;
		Irisclass = irisclass;
	}
	
	
	public int getRowNo() {
		return rowNo;
	}
	public double getPetalLength() {
		return petalLength;
	}
	public double getPetalWidth() {
		return petalWidth;
	}
	public double getSepalLenght() {
		return sepalLenght;
	}
	public double getSepalWidth() {
		return sepalWidth;
	}
	public String getIrisclass() {
		return Irisclass;
	}

}

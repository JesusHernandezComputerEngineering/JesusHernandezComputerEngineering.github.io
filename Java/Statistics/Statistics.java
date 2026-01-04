import java.util.Arrays;

 

public class Statistics

{

//instance variables

private double [] data; 

private double [] sdata;

 

//create a static variable count

public static int count=0;

 

//constructor Statistics

public Statistics (double [ ] d )

{

            //increment static variable count

            count=count + 1;

 

            //create the array data, sdata

            data = new double  [d.length];

             sdata = new double [d.length];

 

            //copy the data from array d into array data and sdata

            System.arraycopy (d, 0, data, 0, d.length);

            System.arraycopy (d, 0, sdata, 0, d.length);

 

            //sort the array sdata

            Arrays.sort (sdata ); //To access Arrays, import java.util.Arrays: 

}

 

//instance methods

public double findMin ( )

{

            double min=sdata[0];

            return min;

}

 

public double findMax ( )

{

            double max=sdata[sdata.length-1];

            return max;

}

 

public double findMean ( )

{

            double sum, mean;

            sum=0;

            for (int i=0; i<sdata.length; i++)

{

                        sum=sum+sdata [i];

}

mean=sum/sdata.length;

return mean;

}

 

//put below the code of method findMedian given in a later section below

public double findMedian ( )

{
	 double median;

     if(sdata.length % 2 == 0)
     	median = (sdata[(sdata.length - 1) / 2] + sdata[sdata.length / 2]) / 2;
     else 
     	median = sdata[sdata.length / 2];
   
     return median;

} 

 

//method returns a copy of the array data containing the original data.

public double [ ] getOrigData ( )

{

            //create a new array d.

            double [ ] d = new double [ data.length ];

 

            //copy the array data into array d

            System.arraycopy ( data, 0, d, 0, data.length );

 

            //return the reference of the array d

            return d;

}

 

//method returns a copy of the instance variable array sdata containing the sorted data.

//write this method much like getOrigData above

public double [ ] getSortedData ( )

{

            //create a new array d.

           double [] d = new double [sdata.length];

 

            //copy the array sdata into array d

           System.arraycopy(sdata, 0,d, 0, sdata.length);
           
 

            //return the reference of the array d

           

            //delete the line below. it is put there temporarily

            return sdata;

}

 

//static methods

public static double computeMin (double [ ] data)

{

            //Create a Statistics object. Pass it the array data during creation. 

            Statistics st = new Statistics ( data );

 

            //Ask Statistics object to find min of the array passed during creation.

            double min = st.findMin ( );

 

            //return min to the caller

            return min;

}

 

public static double computeMax (double [ ] data)

{

            //Create a Statistics object. Pass it array data during creation. 

            Statistics st = new Statistics ( data );

 

            //Ask Statistics object to find max of the array passed during creation.

            double max = st.findMax ( );

 

            //return max to the caller

            return max;

}

 

 

 

 

 

}

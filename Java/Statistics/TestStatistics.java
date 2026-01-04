import java.text.DecimalFormat;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

 

public class TestStatistics

{

     public static void main (String [] args)

    {

          //Create a String in for inputting data values.

         String in="";

 

         //Create a String delim for specifying the delimiter (separator).

         String delim = ",";

 

         //Create an int count to get data count from user.

         int count=0;

 

         //Create a String token to get token value from the StringTokenizer.

         String token = "";

 

         //Get data input in String in

         in = JOptionPane.showInputDialog

                ("Enter data values separated by commas");

 

         //Create StringTokenizer and supply it with String in and String delim

         StringTokenizer st = new StringTokenizer (in, delim);

 

         //Get token count from StringTokenizer

         count = st.countTokens ( );

 

         //Create an array data of size count.

         double[] data = new double [count];

 

         //Set up a for loop to go arount count times.

         //In each pass through the loop, get the next token from StringTokenizer.

         //Convert token into a double and store it in corresponding array element.

         for (int i=0; i<count; i++)

        {

            token = st.nextToken ( );

            //trim the token

            //trim removes all leading and trailing spaces.

            token = token.trim ( );

            data [i] = Double.parseDouble(token);

        }

 

          //create an object of class Statistics and pass it the array data

          Statistics stat=new Statistics(data);

 

          //find min, max, mean. median by calling stat object's methods

          double min = stat.findMin();

          double max = stat.findMax();

          double mean = stat.findMean();
          
          double median = stat.findMedian();

 

 

 

          //get original and sorted data be call stat object's appropriate methods

 

          double[] origData = stat.getOrigData();

    
          double[] sdata = stat.getSortedData();
 

         

 

         //Input the number of decimal places

          in = JOptionPane.showInputDialog("Enter number of decimal places for output");

         int placesCount = Integer.parseInt (in);

 

         //Build pattern string

         String pattern = "0.";

         for (int i=0; i<placesCount; i++)

            pattern = pattern + "0";

 

         //Create a DecimalFormat object. Pass it the pattern string.

         DecimalFormat df = new DecimalFormat ( );

         //use applyPattern

 

          //build output by accumulating output in variable out

       
      
         
          String out = "";

          out = "Original Data: \n";

          for (int i=0; i<origData.length; i++)
          {

                out = out + df.format(origData [i]) + " ";

          }

          out = out + "\n";
          
          out = out + "\n";

          out = out + "Results Using Static Methods:\n";

          out = out + "Sorted Data: \n";

          for (int i=0; i<sdata.length; i++)
          {

                out = out + df.format(sdata [i]) + " ";

          }
          
          out = out + "\n";
          
          out = out + "\n";

          df.applyPattern (pattern);
          
          double min2 = Statistics.computeMin(data);

          double max2 = Statistics.computeMax(data);
          
         

          out = out + "Min Value: " + df.format (min2) + "\n";

          out = out + "Max Value: " + df.format (max2) + "\n"; 
          
          out = out + "Mean:" + df.format(mean) + "\n";

          out = out + "Median:" + df.format(median) + "\n";

          out = out + "\n";


          out = out + "The Total Number of Statistics objects created during execution:\n";
          
          int instanceCount=Statistics.count;

          out = out + instanceCount+"\n";

 

         //display output

         JOptionPane.showMessageDialog ( null, out);

    }

}


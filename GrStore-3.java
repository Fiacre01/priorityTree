import java.util.Random;
/**
 * GrStore uses the PriorityQueue class to run a customer service at the store
 * 
 * @author (Fiacre Indagiye) 
 */

public class GrStore
{
   public static void main (String[] args)
   {
       //creating a new priority line
      PriorityQueue line = new PriorityQueue();
      Random ranLuck = new Random();
      int total = 0;
      int luck, lineSize = 0;
      int maxLength = 0;
     
      //for the 60 minutes
      for (int i = 0; i < 60; i++)
      {
          //giving the new customer a 25 percent chance
          
         luck = ranLuck.nextInt(4);
         if (luck == 3)
         {
             //adding a new priority customer to the line
            line.add(new PriorityCustomer());
            
            System.out.println("New customer added, Queue length is now: " + line.getSize());
            //updating line length
            if (maxLength < line.getSize())
            {
               maxLength = line.getSize();
            } 
         }
         
         PriorityCustomer p = line.peek();
         //remove customer in line 
         if (p != null)
         {
            if (p.getServiceTime() == 1)
            {
               line.remove();
               
               System.out.println("Customer serviced and removed from the queue. Queue length is now: " + line.getSize());
               
               total++;
            }
            
            else
            {
               p.decServiceTime();
            }
         }
         
         lineSize++;
         System.out.println(lineSize+ "   minutes --------------------------------------------\n"); 
      }
      
      System.out.println("Total number of customers serviced: " + total);
      System.out.println("Max line lenght during simulation: " + maxLength); 
      
   }

}
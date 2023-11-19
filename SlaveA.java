/**
 * Both Slave A and SlaveB do the two types of jobs and sleep according to diff
 * amount of seconds specified in project requirements
 *
 * I just thought we should probably have an arraylist that acts as a queue for all the jobs waiting to be
 * processed, and then when we do the job, we remove it from the list. ex. [1,2,1,1,2]
 */
public class SlaveA
{
   private int jobNum;

    /**
     *
     * @param j corresponds to jobNum which is either 1 or 2
     */
   public SlaveA(int j)
   {
       jobNum = j;

   }

   public void doJob()
   {

       if (jobNum == 1)
       {
           int sleep = 2;
           while (sleep > 0)
           {
               sleep--;
           }
           System.out.println("job 1 done.");
       }
       // job 2 will sleep for  sec on slaveA
       if (jobNum == 2)
       {
           int sleep = 10;
           while (sleep > 0)
           {
               sleep--;
           }
           System.out.println("job 2 done.");
       }


   }


}

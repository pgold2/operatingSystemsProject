/**
 * Both Slave A and SlaveB do the two types of jobs and sleep according to diff
 * amount of seconds specified in project requirements
 */
public class SlaveB
{
        private char jobNum;
    /**
     *
     * @param j corresponds to jobNum which is either 1 or 2
     */
    public SlaveB(char j)
    {
        jobNum = j;
    }
    public void doJob()
    {

        // job 1 will sleep for 10 sec on slave B
        if (jobNum == 'A')
        {
            int sleep = 10;
            while (sleep > 0)
            {
                sleep--;
            }
            System.out.println("job A done.");
        }

        // job 2 will sleep for 2 sec on slave B
        if (jobNum == 'B')
        {
            int sleep = 2;
            while (sleep > 0)
            {
                sleep--;
            }
            System.out.println("job B done.");
        }
    }
}

package semesterProject;
public class SlaveWaitTime
{

        private int value;

        public SlaveWaitTime(int initial)
        {
            value = initial;
        }

        public int getCurrValue()
        {
            return value;
        }

        public void setCurrValue(int v)
        {
            value = v;
        }
        public String toString()
        {
            return Integer.toString(value);
        }

        public void removeCounter(int decrementNum){
             if (decrementNum == 2){
                 value =-2;
             } else {
                 value =- 10;
             }
        }


}

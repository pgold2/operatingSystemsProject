public class WeightConverter
{
    private double originalWeight;
    private int choice;

    public WeightConverter(double oW, int c)
    {
        originalWeight = oW;
        choice = c;
    }

    private double chooseConversion()
    {
        double finalWeight = 0;
        if (choice == 1)
        {
            finalWeight = convertLbstoKilo();
        }
        else
        {
            finalWeight = convertKilostoLbs();
        }
        return finalWeight;
    }

    private double convertLbstoKilo()
    {
       double finalWeight = originalWeight *.454;
       System.out.println(originalWeight + " LB is " + finalWeight + "KL.");
       return finalWeight;
    }

    private double convertKilostoLbs()
    {
        double finalWeight = originalWeight * 2.205;
        System.out.println(originalWeight + " KL is " + finalWeight + "KL.");
        return finalWeight;
    }

}

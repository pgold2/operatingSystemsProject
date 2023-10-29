public class WeightConverter
{
    private double originalWeight;

    public WeightConverter(double oW)
    {
        originalWeight = oW;
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

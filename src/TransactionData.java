public class TransactionData
{


    private String action;
    private int counter;
    private int pickCounter;



    TransactionData(String action, int counter, int pickCounter)
    {
        this.action = action;
        this.counter = counter;
        this.pickCounter = pickCounter;
    }



    String getAction()
    {
        return action;
    }

    int getCounter()
    {
        return this.counter;
    }

    int getPickCounter()
    {
        return pickCounter;
    }



}

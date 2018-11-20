public class QueueItem {
    private int index;
    private boolean forkOrKnife;
    //0 = fork ; 1 = knife

    public QueueItem(int index, boolean forkOrKnife)
    {
        this.index = index;
        this.forkOrKnife = forkOrKnife;
    }

    public boolean isForkOrKnife() {
        return forkOrKnife;
    }
}

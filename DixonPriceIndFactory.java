public class DixonPriceIndFactory implements IndFactory {
    private int n;

    public DixonPriceIndFactory(int n) {
        this.n = n;
    }

    @Override
    public Ind getInstance() {
        return new DixonPriceInd(n);
    }
}

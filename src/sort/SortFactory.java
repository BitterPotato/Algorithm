package sort;

public class SortFactory {

    public OrdinarySort createSort(SortType sortType) {
        switch (sortType) {
        case InsertSort:
            return new InsertSort();
        case QuickSort:
            return new QuickSort();
        case ChooseSort:
            return new ChooseSort();
        case BubbleSort:
            return new BubbleSort();
        case NonRecurQuickSort:
            return new NonRecurQuickSort();
        case AnotherQuickSort:
            return new AnotherQuickSort();
        default:
            return new InsertSort();
        }
    }
}

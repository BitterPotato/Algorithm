package struc;


public class BinaryHeapFactory {

    private int default_capa = 256;
    public BinaryHeap getMaxBinaryHeap() {
        return getMaxBinaryHeapWithCap(default_capa);
    }

    public BinaryHeap getMinBinaryHeap() {
        return getMinBinaryHeapWithCap(default_capa);
    }

    public BinaryHeap getMinBinaryHeap(swapOrderRepsonder callback) {
        return new BinaryHeap(false, default_capa, callback);
    }

    public BinaryHeap getMaxBinaryHeapWithCap(int capacity) {
        if(capacity <= 0) {
            throw new InvalidCapException();
        }

        return new BinaryHeap(true, capacity);
    }

    public BinaryHeap getMinBinaryHeapWithCap(int capacity) {
        if(capacity <= 0) {
            throw new InvalidCapException();
        }

        return new BinaryHeap(false, capacity);
    }

    class InvalidCapException extends RuntimeException {

    }
}

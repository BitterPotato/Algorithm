package sort;
/**
 * ��ͨ����ӿ�
 * @author Yang Weijie
 *
 */
public interface OrdinarySort {
    public <T extends Comparable<? super T>> void execute(T[] sequ);
}

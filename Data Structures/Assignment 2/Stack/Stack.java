package Stack;

public interface Stack<T> {
    boolean push(T element);
    T pop();
    T peek();
    boolean isEmpty();
    boolean isFull();
}
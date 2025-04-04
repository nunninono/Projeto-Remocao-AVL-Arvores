package AVLGenerica;

public class AVLNode <T extends Comparable<T>> {
    private AVLNode <T> right;
    private AVLNode <T> left;
    private T info;
    private int fatBal;

    public AVLNode(T info) {
        this.info = info;
        this.fatBal = 0;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public int getFatBal() {
        return fatBal;
    }

    public void setFatBal(int fatBal) {
        this.fatBal = fatBal;
    } 
}




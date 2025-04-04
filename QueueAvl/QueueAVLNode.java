package QueueAvl;

import AVLGenerica.*;;

public class QueueAVLNode<T extends Comparable<T>>  {
    private AVLNode<T> info;  // O tipo do nó será AVLNode<T>
    private QueueAVLNode<T> prox;

    // Construtor
    public QueueAVLNode(AVLNode<T> info) {
        this.info = info;
        this.prox = null;
    }

    // Métodos de acesso
    public AVLNode<T> getInfo() {
        return info;
    }

    public void setInfo(AVLNode<T> info) {
        this.info = info;
    }

    public QueueAVLNode<T> getProx() {
        return prox;
    }

    public void setProx(QueueAVLNode<T> prox) {
        this.prox = prox;
    }
}

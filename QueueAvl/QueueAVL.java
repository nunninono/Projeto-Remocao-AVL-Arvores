package QueueAvl;

import AVLGenerica.*;

public class QueueAVL<T extends Comparable<T>>  {
    private QueueAVLNode<T> inicio;
    private QueueAVLNode<T> fim;

    // Construtor
    public QueueAVL() {
        this.inicio = null;
        this.fim = null;
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return inicio == null;
    }

    // Enfileira um nó da árvore AVL
    public void enQueue(AVLNode<T> node) {
        QueueAVLNode<T> newNode = new QueueAVLNode<>(node);  // Cria um novo nó da fila
        if (isEmpty()) {
            inicio = fim = newNode;  // Se a fila estiver vazia, o novo nó é o início e o fim
        } 
        else {
            fim.setProx(newNode);  // Caso contrário, adiciona o nó ao fim da fila
            fim = newNode;  // Atualiza o fim da fila
        }
    }

    // Desenfileira um nó da árvore AVL
    public AVLNode<T> deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Fila vazia! Não é possível desenfileirar.");
        }
        AVLNode<T> node = inicio.getInfo();  // Pega o nó que está no início da fila
        inicio = inicio.getProx();  // Atualiza o início da fila
        if (inicio == null) {
            fim = null;  // Se a fila ficar vazia, o fim também deve ser null
        }
        return node;
    }

    // Olha o próximo nó da fila sem removê-lo
    public AVLNode<T> peek() {
        if (isEmpty()) {
            System.out.println("Fila vazia!");
            return null;
        }
        return inicio.getInfo();  // Retorna o nó que está no início da fila
    }
}

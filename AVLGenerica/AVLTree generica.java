package AVLGenerica;

// Importando o pacote QueueGenerica
import QueueAvl.*;

class AVLTreeGenerica <T extends Comparable<T>> {
    private AVLNode<T> root; // declaração da raiz (root)
    private boolean status; // Variavel "responde" a pergunta "A arvore precisa ser balanceada?"
    // status == false -> arvore balanceada
    // status == true -> arvore desbalanceada

    // setter de status
    public void setStatus (boolean status) {
        this.status = status;
    }

    // Verifica se a arvore esta vazia
    public boolean isEmpty() {
        return root == null;
    }

    // chamada de Inserção
    public void insert (T value) {
        // Se vazia, cria o primeiro nó que será apontado pela raiz (root)
        if (this.isEmpty() == true) {
            this.root = new AVLNode <T> (value);
        }
        // Se possuir nós, chama a função de inserção insertNode()
        else {
            this.root = insertNode(this.root, value);
            // False garante que a arvore vai estar com status (balanceada) ao fim da inserção
            this.status = false; 
        }
    }

    // Função de inserir que respeita os fatores de balanceamento
    private AVLNode <T> insertNode (AVLNode <T> node, T value) {
        // Se node == null, a posição de inserção foi encontrada
        if (node == null) {
            node = new AVLNode <T> (value); // Cria o novo nó
            // Status == True para o codigo verificar se é preciso corrigir o balanceamento da AVL
            this.status = true; 
        }

        // Enquanto o nó nao for null, o código cai nesse else if()
        // Objetivo aqui é de descer na árvore usando o compareTo dos valores Info dos nós
        else if (node.getInfo().compareTo(value) > 0) { // Se node.Info > valor passado
            // Vai para esquerda
            node.setLeft(insertNode(node.getLeft(), value)); // Desce na arvore para esquerda

            // Este If é acionado após o nó ser inserido e status passar a ser True
            if (this.status == true) {
                // fatBal +1 subárvore direita maior
                // fatBal 0 subárvores iguais em altura
                // fatBal -1 subárvore esquerda maior
                switch (node.getFatBal()) { // Switch recebe o fatBal do nó atual como parametro
                    case 1:  
                        node.setFatBal(0); // fatBal era +1 e se torna 0 apos inserir na esquerda
                        this.status = false; // muda status para False (balanceado)
                        break; // sai do switch 
                    case 0:
                        node.setFatBal(-1); // fatBal era 0 e se torna -1 apos inserir na esquerda
                        break; // sai do switch
                    case -1:
                        node = this.rotateRight(node); // fatBal era -1 e se torna -2 apos inserir na esquerda
                        // Dado o fatBal -2, a rotação para direita se faz necessária
                        break;
                }
            }
        }
        // Segunda opção caso o nó nao seja null
        // Objetivo aqui é de descer na árvore usando o compareTo dos valores Info dos nós
        // Entra no ELSE caso o node.Info == valor passado || node.Info < valor passado
        else {  
            // vai para direita
            node.setRight(insertNode(node.getRight(), value)); // Desce na arvore da Direita

            // Este If é acionado após o nó ser inserido e status passar a ser True
            if (this.status == true) {
                // fatBal +1 subárvore direita maior
                // fatBal 0 subárvores iguais em altura
                // fatBal -1 subárvore esquerda maior
                switch (node.getFatBal()) { // Switch recebe o fatBal do nó atual como parametro
                    case -1:
                        node.setFatBal(0); // fatBal era -1 e se torna 0 apos inserir na direita
                        this.status = false; // Muda status para False (balanceado)
                        break; // Sai do switch
                    case 0:
                        node.setFatBal(1); // fatBal era 0 e se torna +1 apos inserir na direita
                        break; // Sai do switch
                    case 1:
                        node = rotateLeft(node); // fatBal era +1 e se torna +2 apos inserir na direita
                        // Dado o fatBal +2, a rotação para esquerda se faz necessária
                        break; // Sai do switch
                }
            }
        }
        return node;
        // Retorna o nó que serviu de pai na inserção
        // Se houver rotação, o nó retornado é o nó que assumiu papel de A 
    }

    // Função de rotação para direita
    // Objetivo é corrigir o balanceamento quanto um nó possuir fatBal == -2
    private AVLNode <T> rotateRight(AVLNode <T> a) {
        // Declara as variaveis B e C
        AVLNode <T> b, c;
        b = a.getLeft(); // B se torna a esquerda de A

        // R.S.D (Rotação Simples para Direita)
        // Entra no IF caso B possua uma subArvore mais alta para esquerda
        if (b.getFatBal() == -1) {
            a.setLeft(b.getRight()); // Subarvore a direita de B se torna filho esquerdo de A
            b.setRight(a); // B passa a apontar para o filho a direita de A
            a.setFatBal(0); // fatBal de A se torna 0
            a = b; // B toma o lugar de A como raiz primaria da rotação
            // B que é a nova raiz, passa a ser referenciada por A
        }

        // R.D.D (Rotação Dupla para Direita)
        // Em caso de B possuir uma subArvore maior para esquerda, entra no ELSE
        else {
            c = b.getRight(); // C se torna o filho a direita de B
            b.setRight(c.getLeft()); // Subarvore a esquerda de C (caso haja) se torna filho direito de B
            c.setLeft(b); // B se torna filho a esquerda de C
            a.setLeft(c.getRight()); // Subarvore a direita de C (caso haja) se torna filho esquerdo de A
            c.setRight(a); // A se torna filho direito de C

            // Caso C possua fatBal de -1, A recebe fatBal +1
            // Caso não, fatBal de A recebe 0;
            if (c.getFatBal() == -1) {
                a.setFatBal(1);
            }
            else {
                a.setFatBal(0);
            }

            // Caso C possua fatBal de 1, B recebe fatBal -1
            // Caso não, fatBal de B recebe 0
            if (c.getFatBal() == 1) {
                b.setFatBal(-1);
            }
            else {
                b.setFatBal(0);
            }
            // C se torna a nova raiz no lugar do antigo A
            // C que é a nova raiz, passa a ser referenciada por A
            a = c;
        }
        // Atualiza o fatBal de A (antigo C ou B) para zero
        a.setFatBal(0); // C ou B, pois depende do tipo de rotação
        this.status = false; // Altera status para False (arvore balanceada)
        // Retorna a nova raiz da subarvore que seria o C ou B, embora seja referenciada como A
        return a; 
    }

    // Função de rotação para esquerda
    // Objetivo é corrigir o balanceamento quanto um nó possuir fatBal == +2
    private AVLNode <T> rotateLeft(AVLNode <T> a) {
        // Declara as variaveis B e C
        AVLNode <T> b, c;
        b = a.getRight(); // B se torna o filho a direita de A

        // R.S.E (Rotação Simples para Direita)
        // Entra no IF caso B possua uma subArvore mais alta para direita
        if (b.getFatBal() == 1) {
            a.setRight(b.getLeft()); // A subarvore a esquerda de B se torna filha direita de A
            b.setLeft(a); // A se torna filho esquerdo de B (B se torna raiz da subarvore)
            a.setFatBal(0); // Ajusta o fatBal de A para 0 (balanceado)
            a = b;  // B que é a nova raiz, passa a ser referenciada por A
        }

        // R.D.E (Rotação Dupla para Esquerda)
        // É necessaria quando a subarvore(C) a esquerda de B é mais alta que a direita
        else {
            c = b.getLeft(); // C se torna filho esquerdo de B
            b.setLeft(c.getRight()); // Subarvore a direita de C passa a ser filho esquerdo de B
            c.setRight(b); // B passa a ser filho direito de C
            a.setRight(c.getLeft()); // Subarvore a esquerda de C passa a ser filho direito de A
            c.setLeft(a); // A passa a ser filho esquerdo de C

            // Caso C possua fatBal == 1, A recebe fatBal -1
            // Caso não, fatBal de A recebe 0
            if (c.getFatBal() == 1) {
                a.setFatBal(-1);
            }
            else {
                a.setFatBal(0);
            }

            // Caso C possua fatBal == -1, A recebe fatBal 1
            // Caso não, fatBal de A recebe 0
            if (c.getFatBal() == -1) {
                b.setFatBal(1);
            }
            else {
                b.setFatBal(0);
            }
            // C se torna a nova raiz no lugar do antigo A
            // C que é a nova raiz, passa a ser referenciada por A
            a = c;
        }
        // Atualiza o fatBal de A (antigo C ou B) para zero
        a.setFatBal(0); // C ou B, pois depende de tipo de rotação
        this.status = false; // Atualiza status para False (arvore balanceada)
        // Retorna a nova raiz da subarvore que seria o C ou B, embora seja referenciada como A
        return a; 
    }

    // Chamada do PercorrerEmOrdem()
    public void emOrdem () {
        // Se a arvore estiver vazia, informa isso atravez do print
        // Se não, chama a função percorrerEmOrdem()
        if (this.isEmpty() == true) {
            System.out.println("Arvore vazia!!");
        }
        else {
            this.percorrerEmOrdem (this.root);
        }
    }
    // Função de percorrer em ordem
    private void percorrerEmOrdem (AVLNode <T> node) {
        if (node != null) {
            // Chama ela mesma até chegar no menor nó da arvore
            // O processo é como uma pilha
            percorrerEmOrdem(node.getLeft());
            System.out.print(node.getInfo() + "(ftb: " + node.getFatBal() + ") "); // imprime valor
            percorrerEmOrdem(node.getRight());
        }
    }

    public void passeioPorNivel() {
        // Declaração de variáveis
        QueueAVL<T> fila;   
        AVLNode<T> aux;

        if (this.isEmpty() == false) { // Verifica se a arvore esta vazia
            fila = new QueueAVL<T>(); // Cria uma filaAVL de tipo T
            fila.enQueue(this.root);  // Add raiz como primeiro elemento da fila
            int numNosRestantes = 1;

            // Laço que vai rodar até que a fila esteja vazia
            // Vai passar por todos os nós
            while (fila.isEmpty() == false && numNosRestantes >= 0) {
                // Realiza deQueue e guarda o nó em aux
                aux = fila.deQueue(); 

                // Entra no IF caso o aux possua valores (nao seja null)
                if (aux != null) {
                    // Exibe a info presente no nó aux
                    System.out.print(aux.getInfo() + "(ftb: " + aux.getFatBal() + ") "); 
                    numNosRestantes--;

                    // Caso aux possua um filho a esquerda, adiciona o mesmo na fila
                    // Caso não, adiciona null na fila
                    if (aux.getLeft() != null) {
                        fila.enQueue(aux.getLeft()); 
                        numNosRestantes++;
                    }
                    else {
                        fila.enQueue(null);  // null indica a ausência do filho esquerdo
                    }
    
                    // Caso aux possua um filho a direita, adiciona o mesmo na fila
                    // Caso não, adiciona null na fila
                    if (aux.getRight() != null) {
                        fila.enQueue(aux.getRight());
                        numNosRestantes++;
                    }
                    else {
                        fila.enQueue(null);  // null indica a ausência do filho direito
                    }
                }
                // Aux nao possuindo valores (aux == null), printa apenas o "null"
                else {
                    System.out.print("null ");
                }
            }
            // Quebra de linha
            System.out.println();  
        }
        // Retorna essa mensagem caso a arvore esteja vazia 
        else {
            System.out.println("Arvore vazia!!");
        }
    }

    // Chamada da função de remoção
    public void remove(T value) {
        // Se a arvore estiver vazia, imprime informando isso e nao faz nada
        // Se nao estver vazia, chama a função removerNode()
        if (this.isEmpty()) {
            System.out.println("Árvore vazia! Não é possível remover.");
            return;
        }
        // Chama a função recursiva para remover o nó
        this.root = removeNode(this.root, value);
    }

    // Função recursiva para remoção de um nó
    private AVLNode<T> removeNode(AVLNode<T> node, T value) {
        // Condição de parada caso nao encontre o nó
        if (node == null) {
            return node; // Se o nó for nulo, apenas retorna
        }

        // Desce na árvore até encontrar o nó a ser removido
        // Se valor for menor que o nó perguntado, desde pra esquerda
        if (value.compareTo(node.getInfo()) < 0) {
            node.setLeft(removeNode(node.getLeft(), value)); // Vai para a subárvore esquerda
        } 

        // Se valor for maior que o nó perguntado, desde pra direita
        else if (value.compareTo(node.getInfo()) > 0) {
            node.setRight(removeNode(node.getRight(), value)); // Vai para a subárvore direita
        } 

        // Encontrou o nó a ser removido
        else if (value.compareTo(node.getInfo()) == 0) {    
            // Nó com um único filho ou nenhum
            // Se não tem filho à esquerda, retorna o filho à direita
            if (node.getLeft() == null) {
                return node.getRight(); 
            } 
            // Se não tem filho à direita, retorna o filho à esquerda
            else if (node.getRight() == null) {
                return node.getLeft(); 
            }

            // Nó com dois filhos
            // Encontrar o maior valor na subárvore esquerda
            AVLNode<T> maiorLeft = node.getLeft(); // Novo nó aponta para esquerda de node
            // Uma vez na subArvore da esquerda, basta ir buscar o maior nó indo so na direita
            while (maiorLeft.getRight() != null) {
                maiorLeft = maiorLeft.getRight();
            }
            // Substitui node pelo maiorLeft (maiorLeft passa a ser a raiz)
            node.setInfo(maiorLeft.getInfo()); 
            // Remove o maiorLeft original
            node.setLeft(removeNode(node.getLeft(), maiorLeft.getInfo())); 
        }

        // balancear a árvore se necessário após a remoção
        return balancear(node);
    }

    // Função para balancear a arvore após a remoção
    private AVLNode<T> balancear(AVLNode<T> node) {
        // Obtem o fatBal do node passado por parametro
        int balanceFactor = obterFatBal(node);

        // Verifica qual situação o nó se encontra
        // 1. Rotação simples à direita
        // fatBal do nó = +2 e do filho a esquerda tambem pendendo pra esquerda
        if (balanceFactor > 1 && obterFatBal(node.getLeft()) >= 0) {
            return rotateRight(node);
        }

        // 2. Rotação simples à esquerda
        // fatBal do nó = -2 e do filho a direita tambem pendendo pra direita
        if (balanceFactor < -1 && obterFatBal(node.getRight()) <= 0) {
            return rotateLeft(node);
        }

        // 3. Rotação dupla à direita (B pra esquerda e C pra direita)
        // fatBal do nó = +2 e do filho a esquerda pendendo pra esquerda
        if (balanceFactor > 1 && obterFatBal(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft())); // Primeira rotação à esquerda
            return rotateRight(node); // Segunda rotação à direita
        }

        // 4. Rotação dupla à esquerda (B pra direita e C pra esquerda)
        // fatBal do nó = -2 e do filho a direita pendendo pra esquerda
        if (balanceFactor < -1 && obterFatBal(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight())); // Primeira rotação à direita
            return rotateLeft(node); // Segunda rotação à esquerda
        }

        // Retorna o nó após o balancear ou se ele ja estiver balandeado
        return node; 
    }

    // Função para obter o fator de balanceamento de um nó
    private int obterFatBal(AVLNode<T> node) {
        // Se node == null, retorna pois nao tem o que balancear
        if (node == null) {
            return 0;
        }
    
        // Obtém altura da subárvore esquerda
        int alturaEsquerda = -1;  // -1 pois nó nulo tem altura -1
        // Se houver filho na esquerda, chama altura()
        if (node.getLeft() != null) {
            alturaEsquerda = altura(node.getLeft());
        }
    
        // Obtém altura da subárvore direita
        int alturaDireita = -1;  // -1 pois nó nulo tem altura -1
        // Se houver filho na direita, chama altura()
        if (node.getRight() != null) {
            alturaDireita = altura(node.getRight());
        }
        
        // Retorna o fatBal (diferença das alturas)
        return alturaEsquerda - alturaDireita;
    }

    // Função para calcular a altura de um nó
    private int altura(AVLNode<T> node) {
        // nó nulo tem altura -1
        if (node == null) {
            return -1;
        }
        
        // Calcula ambas as alturas de forma recursiva
        int alturaLeft = altura(node.getLeft());
        int alturaRight = altura(node.getRight());
        
        // Calcula a altura real do nó pedido
        // Calculo da altura ltura é 1 + altura da maior subArvore
        if (alturaLeft > alturaRight) {
            return alturaLeft + 1;
        } 
        else {
            return alturaRight + 1;
        }
    }

}

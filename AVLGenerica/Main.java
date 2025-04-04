package AVLGenerica;

import java.util.Scanner; // import do Scanenr java

public class Main {
    public static void main(String[] args) { // Dinifição da main
        Scanner input = new Scanner(System.in); 
        AVLTreeGenerica<Integer> AVLTree = new AVLTreeGenerica<>();
        int opcao;
        int valor;

        do {
            // Exibição do menu
            System.out.println("\n===================================");
            System.out.println("1. Inserir valor na arvore.");
            System.out.println("2. Exibir árvore em ordem.");
            System.out.println("3. Exibir árvore por nivel.");
            System.out.println("4. Remover valor da arvore.");
            System.out.println("5. Fechar programa.");
            System.out.println("===================================");
            System.out.print("Escolha uma opcao: ");
            
            opcao = input.nextInt(); // Recebe a opção inputada

            switch (opcao) {
                case 1: // Inserir valor na arvore
                    System.out.print("Digite o valor a ser inserido: ");
                    valor = input.nextInt();
                    AVLTree.insert(valor);
                    System.out.println("Valor " + valor + " inserido na árvore.");
                    break;

                case 2: // Exibir árvore em ordem
                    System.out.println("\nExibindo arvore em ordem:");
                    AVLTree.emOrdem();
                    break;

                case 3: // Exibir árvore por nivel
                    System.out.println("\nExibindo arvore por nivel:");
                    AVLTree.passeioPorNivel();
                    break;

                case 4: // Remover valor da arvore
                    System.out.print("Insira o valor a ser removido: ");
                    valor = input.nextInt();
                    AVLTree.remove(valor);
                    System.out.println("Valor " + valor + " removido da árvore.");
                    break;

                case 5: // Fechar programa.
                    System.out.println("Saindo do programa...");
                    return;

                default:
                    System.out.println("Opção invalida! Tente outra.");
                    break;
            }
        } while (opcao != 5); // mantem o loop ate que a 5 opção ser escolhida

        input.close(); // fechar o scanner
    }
}

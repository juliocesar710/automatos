package AFN;

import java.nio.channels.InterruptibleChannel;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AFNe{
    static int[][][] transicao = {
            { 
                { 1 }, {}, {}           //q0
            },
            { 
                {}, { 2 }, { 2 }        // q1
            },
            { 
                {}, {}, {}              // q2
            } 
        };
    static int[][] transicaoVazia = { {}, {0}, {0} };
    static int estadoInicial = 0;
    static int[] aceitacao = { 0 };

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String cadeia;

        System.out.print("Informe uma cadeia: ");
        cadeia = scan.nextLine();

        int[] estados = eclose(new int[]{ estadoInicial });
        int posicao = 0;
        while (posicao < cadeia.length()) {
            imprimeCI(cadeia, estados, posicao);
            int[] novosEstados = new int[]{};
            int elemento = Integer.parseInt(cadeia.substring(posicao, posicao+1));
            for (int i : estados) {
                int[] destinoPosicao = transicao[i][elemento];
                novosEstados = uniao(novosEstados, destinoPosicao);
                novosEstados = eclose(novosEstados);
            }
            estados = novosEstados;
            if (estados.length == 0) break;
            posicao++;

        }
        imprimeCI(cadeia, estados, posicao);
        
        if (aceita(estados)) {
            System.out.println("Aceita!!!");
        } else {
            System.out.println("Não Aceita!!!");
        }

    }

    private static int[] eclose(int[] estados) {
        int[] eclose = estados;
        for (int i : eclose) {
            int[] vetAux = transicaoVazia[i];
            int[] vetAux2 = eclose(vetAux);
            eclose = uniao(eclose, vetAux);
            eclose = uniao(eclose, vetAux2);
        }
        return eclose;
    }

    private static int[] uniao(int[] vet, int[] vet2) {
        Set<Integer> u = new TreeSet<>();
        for (int i : vet) 
            u.add(i);
        for (int i : vet2)
            u.add(i);
        int[] arr = new int[u.size()];
        int pos = 0;
        for (Integer i : u) {
            arr[pos++] = i;
        }
        return arr;
    }

    public static boolean aceita(int[] estados) {
        if (estados == null)
            return false;
        for (int i : estados) {
            for (int j : aceitacao) {
                if (i == j)
                    return true;
            }
        }
        return false;
    }

    public static void imprimeCI(String cadeia, int[] estado, int posicao) {
        System.out.print(cadeia.substring(0, posicao)+"[");
        for (int i = 0; i < estado.length; i++) {
            System.out.print("q" + estado[i]);
            if (i < estado.length-1)
            {
                System.out.print(",");
            }
        }
        System.out.println("]" + cadeia.substring(posicao));
    }
}

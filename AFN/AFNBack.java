/*Q = {q0,q1,q2}
 * estado inicial = q0
 * estado Final = q2
 * Alfabeto = {0,1}
 * 
 *         0 | 1
 * ->q0 {q1,q0} {q0}
 *   q1   {}    {q2} 
 *  *q2   {}    {}
 * 
 * 100 rejeita
 * 101 aceita
 */

package AFN;

import java.util.Scanner;

public class AFNBack {
    
    static int[][][] transicao = {
        {
            { 0, 1}, {0}
        },
        {
            {}, { 2 }
        },
        {
            {}, {}
        }
    };
    static int estadoInicial = 0;
    static int[] aceitacao = {2}; 
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        String cadeia;

        System.out.print("Informe uma cadeia: ");
        cadeia = scan.nextLine();


        int[] estados = {estadoInicial};
        int[] estadosFinais = testa(cadeia, estados, estadoInicial);
        if(aceita(estadosFinais)){
            System.out.println("Aceita!!!");
        }else{
        System.out.println("Não Aceita!!!");}
        
    }

    public static int[] testa(String cadeia, int[] estados, int posicao) {
        if(posicao==cadeia.length()){
            imprimeCI(cadeia, estados[0], posicao);
            if(aceita(estados)){
                return estados;
            }
            else {
                System.out.println("<< volte >> Fim da cadeia!!");
                return null;
            }
        }
        int simboloInt = Integer.parseInt(cadeia.substring(posicao, posicao+1));
        for(int i:estados){
            imprimeCI(cadeia, i, posicao);
            int[] novosEstados = transicao[i][simboloInt];

            if(novosEstados.length==0){
                System.out.println("<< volte >> Sem opções!"); 
                return null;
            } 

            int[] transicoes = testa(cadeia, novosEstados,posicao+1);
            if(transicoes != null) return transicoes;
        }
        return null;
    }

    public static boolean aceita(int[] estados){
        if(estados==null) return false;
        for(int i:estados){
            for(int j:aceitacao){
                if(i==j) return true;
            }
        }
        return false;
    }

    public static void imprimeCI(String cadeia, int estado, int posicao){
        System.out.print(cadeia.substring(0, posicao));
        System.out.print("[q"+estado+"]");
        System.out.println(cadeia.substring(posicao));
    }
}

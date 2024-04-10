/*Q = {q0,q1}
 * estado inicial = q0
 * estado Final = q1
 * Alfabeto = {0,1}
 * 
 *         0 | 1
 * ->q0  {q0} {q1}
 *   q1  {q1} {q0} 
 * 
 * 101 rejeita
 * 100 aceita
 */



import java.util.Scanner;

public class AFDGenerico {

    static int[][] transicao = {
        { 0, 1},
        { 1, 0}
    };
    static int estadoInicial = 0;
    static int[] aceitacao = {1};
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            String cadeia;
            int posicao=0,estado=estadoInicial;
            char simbolo;

            System.out.print("Digite uma cadeia do alfabeto:");
            cadeia = scan.nextLine();


            while(posicao<cadeia.length()){

                imprimeCI(cadeia, estado, posicao);
                simbolo = cadeia.charAt(posicao);
                int simboloInt = Integer.parseInt(simbolo+"");
                estado = transicao[estado][simboloInt];
                posicao++;

            }
            imprimeCI(cadeia, estado, posicao);

            boolean aceita = false;
            for(int i:aceitacao){
                if(estado==i) aceita=true;
            }
            if(aceita){
                System.out.println("Aceita");
            }
            else {
                System.out.println("Não Aceita");   
            }

           
        }
// CI = Configuracao Instantanea

        public static void imprimeCI(String cadeia, int estado, int posicao){
                System.out.print(cadeia.substring(0, posicao));
                System.out.print("[q"+estado+"]");
                System.out.println(cadeia.substring(posicao));
        }
        
    }
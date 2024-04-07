 package automatos.AFD;

import java.util.Scanner;

public class AFD {
        
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            String cadeia;
            int posicao=0,estado=0;
            char simbolo;

            System.out.print("Digite uma cadeia do alfabeto:");
            cadeia = scan.nextLine();


            while(posicao<cadeia.length()){

                imprimeCI(cadeia, estado, posicao);
                simbolo = cadeia.charAt(posicao);
                if(estado == 0 && simbolo=='0'){
                    estado = 0;
                }
                else if(estado == 0 && simbolo == '1'){
                    estado = 1;
                }
                else if(estado == 1 && simbolo == '0'){
                    estado = 1;
                }
                else if(estado == 1 && simbolo == '1'){
                    estado = 0; 
                }
                posicao++;
            }
            imprimeCI(cadeia, estado, posicao);

            if(estado == 1){
                System.out.println("Aceita");
            }
            else if(estado == 0){
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
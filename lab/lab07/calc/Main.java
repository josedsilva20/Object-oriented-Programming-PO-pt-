package calc;

public class Main {

  public static void main(String []args) {
    Numero n1 = new Numero(1);
    Numero n2 = new Numero(2);
    Numero n3 = new Numero(3);

    OperacaoMais op1 = new OperacaoMais(n1, n2);
    OperacaoMais op2 = new OperacaoMais(n3, op1.calcula());

    System.out.println("Resultado final: " + op2.calcula().obtemValor());
  }
}
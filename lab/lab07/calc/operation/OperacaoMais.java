package calc.operation;

import calc.core.Numero;

public class OperacaoMais {

  private Numero _arg1;
  private Numero _arg2;


  public OperacaoMais(Numero n1, Numero n2) {
    _arg1 = n1;
    _arg2 = n2;
  }

  public Numero calcula() {
    return new Numero(_arg1.obtemValor() + _arg2.obtemValor());
  }
}

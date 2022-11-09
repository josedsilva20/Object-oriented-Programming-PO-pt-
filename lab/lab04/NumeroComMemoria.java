public class NumeroComMemoria extends Numero {
	private int _anterior;


	public NumeroComMemoria(int valor){
		super(valor);
		_anterior = valor;
	}

	public void alteraValor(int valor){
		_anterior = getNumero();
		super.alteraValor(valor);
	}

	public int obtemValorAnterior (){
		return _anterior;
	}

	public void desfaz(){
		alteraValor(_anterior);
	}
}
public class Numero{
	private int _numero;

	public Numero(){
	}

	public Numero(int numero){
		_numero = numero;
	}

	public int getNumero(){
		return _numero;
	}

	public String toString(){
		return "" + _numero;
	}

	public void alteraValor(int valor){
		_numero = valor;
	}

	public boolean equals(Numero numero){
		return _numero == numero._numero;
	}

	public Numero greater(Numero numero){
		if (this.getNumero() > numero._numero)
			return this;
		return numero;
	}
}
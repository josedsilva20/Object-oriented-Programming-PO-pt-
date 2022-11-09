public class Caneta{

	private int _capacidadeTinta;
	private String _cor;
	private Marca _marca;
	private int _quantidadeTinta;



	public Caneta(Marca marca, int capacidade, String cor){
		_capacidadeTinta = capacidade;
		_quantidadeTinta = capacidade;
		_marca = marca;
		_cor = cor;
	}

	public String obterCor(){
		return _cor;
	}

	public int obterQuantidadeDisponivel(){
		return _quantidadeTinta;
	}

	public void escrever(String frase){
		_quantidadeTinta -= frase.length();
		if (_quantidadeTinta < 0)
			_quantidadeTinta = 0;
	}

	public int recarregar(int quantidade){
		int resto = quantidade + _quantidadeTinta - _capacidadeTinta;

		if (resto < 0)
			return 0;

		return resto;
	}

	

}
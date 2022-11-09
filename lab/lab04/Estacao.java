public class Estacao{
	private int _capacidade;
	private int[] _bombas;
	private int _quantidade;
	private Estacao[] _estacoesAmigas;
	private int _nrAmigas;

	public Estacao(int capacidade){
		_capacidade = capacidade;
		_quantidade = capacidade;
		_bombas = new int[10];
		_estacoesAmigas = new Estacao[10];
	}

	public int abastecer(int quantidade, int id_bomba){
		int abastecido = _quantidade;
		if (_quantidade >= quantidade){
			_quantidade -= quantidade;
			abastecido -= _quantidade;
		}

		else{
			abastecido = quantidade - _quantidade;
			_quantidade = 0;
		}

		_bombas[id_bomba] = abastecido;
		
		return abastecido;
	}

	public int getCombustivelDisponivel(){
		return _quantidade;
	}

	public int getAbastecimentoBomba(int id_bomba){
		return _bombas[id_bomba];
	}

	public void addAmiga(Estacao amiga){
		_estacoesAmigas[_nrAmigas] = amiga;
		_nrAmigas++;
	}

	public void showAmigas(){
		for (int i = 0; i < _nrAmigas; i++)
			System.out.println(_estacoesAmigas[i].getCombustivelDisponivel());
	}

	public void addCombustivel(int quantidade){
		int resto = _capacidade - _quantidade - quantidade;

		if (resto <= 0)
			_quantidade += resto;

		_quantidade += quantidade;
	}

	public static void main(String[] args) {
		Estacao a = new Estacao(10);
		Estacao b = new Estacao(3);

		a.addAmiga(b);
		a.showAmigas();
	}
}
public class Carro {
	private String _marca;
	private int _vMaxima;
	private Pneu[] _pneus;
	private int _quilometragem;


	public Carro (Pneu[] pneus){
		Pneu[] _pneus = new Pneu[4];
		for (int i = 0; i < _pneus.length; i++)
			_pneus[i] = pneus[i];
	}

	public int obterQuilometragem (){
		return _quilometragem;
	}

	public String obterMarca(){
		return _marca;
	}

	public boolean pneusVazios(){
		for (int i = 0; i < _pneus.length; i++)
			if (_pneus[i].estaVazio())		/* Precisa ser implementado em Pneu.java  */
				return true;
		return false;
	}

	public boolean montarPneus(){
		for (int i = 1; i < _pneus.length; i++){
			if (_pneus[i] != _pneus[i-1])
				return false;
		}
		return true;
	}

	public void alterarQuilometragem(int quilometragem){
		_quilometragem = quilometragem;
	}
}
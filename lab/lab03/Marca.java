public class Marca {
	private String _nome;
	private int _ano;
	private Caneta[] _canetas;

	public Marca(String nome, int ano, Caneta canetas){
		_nome = nome; 
		_ano = ano;
		_canetas = new Caneta[1000];
	}

	public void fabricarCaneta (int quantidade, String cor){
		Caneta c = new Caneta(this, quantidade, cor);
	}
}
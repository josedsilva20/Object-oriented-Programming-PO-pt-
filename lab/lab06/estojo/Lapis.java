public class Lapis{
	private String _marca;
	private String _cor;

	public Lapis(String marca, String cor){
		_marca = marca;
		_cor = cor;
	}

	public boolean equals (Lapis lapis){
		return (_marca == marca && _cor == cor);
	}

	
}
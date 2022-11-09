public class Funcionario{
	private String _nome;
	private int _salario;

	public Funcionario(String nome, int salario){
		_salario = salario;
		_nome = nome;
	}

	public int getSalary(){
		return _salario;
	}

	public String getName(){
		return _nome;
	}

	public boolean equals(Funcionario f){
		return _nome == f.getName();
	}
}
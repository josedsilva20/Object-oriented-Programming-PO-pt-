import java.util.*;

public class Empresa{
	private Set<Funcionario> _funcionarios;

	public Empresa(){
		_funcionarios = new TreeSet<Funcionario>();
	}

	public boolean addFuncionario(Funcionario funcionario){
			return _funcionarios.add(funcionario);
	}

	public void listaFuncionarios(){
		for (Funcionario f: _funcionarios)
			System.out.println(f.getName() + f.getSalary());
	}

	public List<Funcionario> obterSalarioMaior(int valor){

		List<Funcionario> aux = new ArrayList<>();
		for (Funcionario f: _funcionarios)
			if (f.getSalary() >= valor)
				aux.add(f);

		if (aux.size() == 0)
			return null;

		return aux;
	}

	public void removerComInicioIgualA(String nome){
		Iterator<Funcionario> iter = _funcionarios.iterator();
		while(iter.hasNext()){
			Funcionario f = iter.next();
			if (f.getName().startsWith(nome))
				iter.remove();
		}	
	}

	public List<Funcionario> getOrdenadosNomeSalario(){
		List<Funcionario> aux = new ArrayList<>(_funcionarios);
		aux.sort(new FuncionariosSalarioNomeComparator());
		return aux;
	}
}
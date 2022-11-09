import java.util.*;

/*	ESTA CLASSE PRECISA SER PACKAGE PRIVATE PORQUE JAVA APENAS PERMITE QUE UMA SEJA PUBLIC */

class FuncionariosSalarioNomeComparator implements Comparator<Funcionario>{
	public int compare(Funcionario f1, Funcionario f2) {
	if (f1.getSalary() != f2.getSalary()) 
		return f1.getSalary() - f2.getSalary();
	else 
		return f1.getName().compareTo(f2.getName()); 
	}
}
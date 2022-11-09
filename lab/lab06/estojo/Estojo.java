public class Estojo{
	private List<Lapis> _lapis;

	public Estojo(int capacidade){
		_lapis = new ArrayList<>(capacidade);
	}

	public boolean addLapis(Lapis lapis){
		return _lapis.add(lapis);
	}

	public void obterIguais(Lapis lapis){
		Iterator<Lapis> iter = _lapis.iterator();
		while (iter.hasNext()){
			iter = iter.next();
			if (iter.equals(lapis))
				iter.remove();
		}
	}

}
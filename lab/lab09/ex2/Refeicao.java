public abstract class Refeicao{
    private List<Ingrediente> _ingredientes = new ArrayList<Ingrediente>();
    private String _nome;
    private float _precoBase;

    public Refeicao(String nome, List<Ingrediente> ingredientes, float precoBase) {
        _nome = nome;
        _ingredientes.addAll(ingredientes);
        if (ingredientes.isEmpty())
            throw new InvalidArgumentException("Lista de ingredientes está vazia");
        _precoBase = precoBase;
    }

    public float calculaPreco(){
        float result = _precoBase;
        for (Ingrediente i : _ingredientes){
            p += i.getPreco();
        }
        return result;
    }
}

//solucao alternativa. vai haver metodo abstrato definido em classe refeicao getPrecoBase

public abstract class Refeicao{
    private List<Ingrediente> _ingredientes = new ArrayList<Ingrediente>();
    private String _nome;

    public Refeicao(String nome, List<Ingrediente> ingredientes) {
        _nome = nome;
        _ingredientes.addAll(ingredientes);
        if (ingredientes.isEmpty())
            throw new InvalidArgumentException("Lista de ingredientes está vazia");
    }

    abstract protected float getPrecoBase();

    public float calculaPreco(){
        float result = _precoBase;
        getPrecoBase();
        for (Ingrediente i : _ingredientes){
            p += i.getPreco();
        }
        return result;
    }
}
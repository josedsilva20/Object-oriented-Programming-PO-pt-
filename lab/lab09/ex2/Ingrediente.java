public class Ingrediente{
    public String _name;
    public float _preco;

    public Ingrediente(String name, float preco){
        _name = name;
        _preco = preco;
    }

    public String getName(){
        return _name;
    }

    public float getPreco(){
        return _preco;
    }
}
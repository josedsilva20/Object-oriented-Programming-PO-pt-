public class RefeicaoQuente extends Refeicao{
    public RefeicaoQuente(String nome, List<Ingredientes> i){
        super(nome, i, 0.75);
    }

    protected float getPrecoBase(){
        return 0.75;
    }
}

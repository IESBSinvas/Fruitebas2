package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

/**
 * Criado por Sinvas em 24/08/2015.
 */
public class Placar {

    private int golsA;
    private int golsB;
    private TimesPlacar timeUltimoGol;

    public Placar() {
        this.golsA = 0;
        this.golsB = 0;
        timeUltimoGol = TimesPlacar.TIME_INDEFINIDO;
    }

    public int getGolsA() {
        return golsA;
    }

    public int getGolsB() {
        return golsB;
    }

    public void adicionaGol(TimesPlacar time)
    {
        switch (time){
            case TIME_A:
                this.golsA = this.golsA + 1;
                timeUltimoGol = time;
                break;
            case TIME_B:
                this.golsB = this.golsB + 1;
                timeUltimoGol = time;
                break;
        }
    }

    public void desfazerJogada()
    {
        switch (timeUltimoGol){
            case TIME_A:
                this.golsA = this.golsA - 1;
                break;
            case TIME_B:
                this.golsB = this.golsB - 1;
                break;
        }
        timeUltimoGol = TimesPlacar.TIME_INDEFINIDO;
    }


    public void zeraPlacar(){
        this.golsA = 0;
        this.golsB = 0;
    }

    public enum TimesPlacar{
        TIME_A,
        TIME_B,
        TIME_INDEFINIDO
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        Placar retorno = (Placar)super.clone();
        retorno.golsA = this.golsA;
        retorno.golsB = this.golsB;
        retorno.timeUltimoGol = this.timeUltimoGol;

        return retorno;
    }

}


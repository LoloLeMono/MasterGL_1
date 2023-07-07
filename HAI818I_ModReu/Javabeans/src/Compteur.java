public class Compteur extends Model{
    protected int valeur = 0;

    protected void changerValeur(int i){
        valeur = valeur + i;
        this.changed("valeur");
    }
    public int getValeur(){return valeur;}
    public void incrementer() { this.changerValeur(1); }
    public void decrementer() { this.changerValeur(-1); }
}

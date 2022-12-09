/**
 * la class Noeud correspond a un argument
 * @author julien et Victor
 *
 */
public class Noeud {
    /**
    * Le nom du noeud
    */
    private String arg;
      
    /**
    * Creer un noeud en fonction de son nom
    *@param arg nom du noeud
    */
    public Noeud(String arg){
        this.arg = arg;
    }
    /**
     * retourne un noeud
     * @return un string
     */
    public String getNoeud() {
        return arg;
    }
    /**
     * modifier un noeud
     * @param arg
     */
    public void setNoeud(String arg) {
        this.arg = arg;
    }

}

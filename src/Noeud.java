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
    public String getNoeud() {
        return arg;
    }
    public void setNoeud(String arg) {
        this.arg = arg;
    }
}

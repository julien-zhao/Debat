import java.io.FileNotFoundException;

public class fichierException extends Exception{

	private static final long serialVersionUID = 1L;

	public fichierException() {
		super("Le fichier n'existe pas");
	}
}

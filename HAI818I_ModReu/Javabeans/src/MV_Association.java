import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

public abstract class MV_Association {
    // utilise un dictionnaire pour stocker les couples mod`ele−vue
    static Dictionary MVDictionary = new Hashtable();

    // permet d’associer un modèle à une vue
    public static void add(Model m, View v){
        MVDictionary.put(m, v);
    }

    // rend la collection de vues associées à un modèle
    public static Collection<View> getViews(Model m)
    {
        MVDictionary.get(m)
    }
}
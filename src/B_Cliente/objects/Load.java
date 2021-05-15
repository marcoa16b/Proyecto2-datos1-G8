package B_Cliente.objects;

// Clase encrgada de cargar los recursos

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Clase encargada de cargar archivos.
 */
public class Load {

    /**
     * Metodo para cargar imagenes.
     */
    public static BufferedImage imageLoad(String path){
        try {
            return ImageIO.read(Load.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // si ocurre un error al cargar la imagen retorn null
    }
}

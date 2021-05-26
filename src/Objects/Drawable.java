package Objects;

import java.awt.*;

/**
 * Interfase implementada en los objetos para que sean mostrados en pantalla
 */
public interface Drawable {
	public void draw(Graphics2D g);
	public void update(double delta);
}

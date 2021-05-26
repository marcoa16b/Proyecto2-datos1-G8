package display;

import java.awt.*;

/**
 *Clase SuperStateMachine: define statemachine.
 */
public abstract class SuperStateMachine {
	private StateMachine stateMachine;

	/**
	 *Metodo SuperStateMachine: Set StateMachine
	 * @param stateMachine
	 */
	public SuperStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	/**
	 *Metodo getStateMachine: getea StateMachine.
	 * @return this.stateMachine
	 */
	public StateMachine getStateMachine() {
		return this.stateMachine;
	}

	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
	public abstract void init(Canvas canvas);
}

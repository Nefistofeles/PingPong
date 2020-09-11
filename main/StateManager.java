package main;

public abstract class StateManager{

	private static StateManager currentState ;
	private static State state ;
	
	public StateManager() {
		currentState = null ;
		state = State.MenuState ;
	}
	public abstract void init();
	public abstract void cleanUp();
	public abstract void update();
	
	public static StateManager getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(StateManager currentState) {
		if(StateManager.currentState != null) {
			StateManager.currentState.cleanUp();
		}
		StateManager.currentState = currentState ;
		StateManager.currentState.init(); 
	}
	public static State getState() {
		return state;
	}
	public static void setState(State state) {
		StateManager.state = state;
	}
	
	


	
	
}

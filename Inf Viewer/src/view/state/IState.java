package view.state;

import view.View;

public interface IState {
	public void doAction(View view);

	@Override
	public String toString();
}

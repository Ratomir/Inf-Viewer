package observer;

public interface ISubject {
	void attachObserver(IObserver observer);

	void removeObserver(IObserver observer);

	void notifyAllObjects(IObserver observer);
}

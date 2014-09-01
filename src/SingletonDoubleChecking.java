package common;
public class SingletonDoubleChecking{
	private volatile SingletonDoubleChecking instance;
	private SingletonDoubleChecking(){}

	public SingletonDoubleChecking getInstance(){
		if(instance == null){
			synchronized(SingletonDoubleChecking.class){
				if(instance == null)
					instance = new SingletonDoubleChecking();
			}
		}
		return instance;
	}
}

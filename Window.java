package kyumin;

public interface Window {
	public boolean ITS_OPEN = true;
	public boolean ITS_CLOSE = false;

	public void open();
	public void close();
	public void check_state();
}

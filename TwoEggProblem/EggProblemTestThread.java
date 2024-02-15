public class EggProblemTestThread extends Thread {

	static {
               //System.loadLibrary("eggjni"); <-- Worked in Mainline, but would not load here??
		String fullPath = System.getProperty("user.dir")  + "/eggjni.so";
		System.load(fullPath);
	}
	private int m_numEggs = 0;
	private int m_height = 0;
	private int m_result = 0;
	
    public native int JNI_compute(int nb_items, int height);	

    public void run() {
    	m_result = this.JNI_compute(m_numEggs, m_height);
    }
    
    
    public EggProblemTestThread (int height, int numEggs) {
       	m_numEggs = numEggs;
    	m_height = height;
    }

    public int getHeight() {
    	return m_height;
    }
    
    public int getNumEggs() {
    	return m_numEggs;
    }
    
    public int getResult() {
    	return m_result;
    }
}


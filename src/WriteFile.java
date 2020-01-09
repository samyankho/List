import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteFile<E> {

	public String path;
	public PrintWriter writer;
	
	public WriteFile(String path) throws FileNotFoundException{
		this.path = path;
		this.writer = new PrintWriter(path);
	}
	
	public void writeFile(E e) {
		this.writer.print(e);
	}
	
	public void flush() {
		this.writer.flush();
	}
	
	public void closeFile() {
		this.writer.close();
	}
	
}

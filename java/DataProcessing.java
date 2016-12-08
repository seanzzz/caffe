import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Processes our data
 * 
 * @author Connie, Tony & Sean
 *
 */
public class DataProcessing {

	/**
	 * pre-process the annotation to fit the input requirement of Caffe
	 */
	public static void preProcess() {
		try {
			BufferedReader b1 = new BufferedReader(new FileReader(
					"/Users/Sean/Desktop/annotations/trainval.txt"));
			FileWriter r = new FileWriter(
					"/Users/Sean/Desktop/annotations/trainval_caffe.txt");

			String l = "";
			while ((l = b1.readLine()) != null) {
				String[] s = l.split(" ");
				r.write("/home/usr/sean/Desktop/images/" + s[0] + ".jpg "
						+ s[1] + "\n");
			}

			r.close();
			b1.close();
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * post process the result so that we can visualize our data
	 * 
	 * @param filename
	 */
	public static void postProcess(String filename) {
		try {
			BufferedReader b1 = new BufferedReader(new FileReader(
					"/Users/Sean/Desktop/" + filename + ".txt"));
			FileWriter r = new FileWriter("/Users/Sean/Desktop/" + filename
					+ ".txt");
			FileWriter r2 = new FileWriter("/Users/Sean/Desktop/" + filename
					+ ".csv");
			String l, prevLine = "";
			while ((l = b1.readLine()) != null) {
				if (l.contains(", loss = ")) {
					r.write(prevLine + "\n");
					r.write(l + "\n");
					r2.write(l.substring(l.indexOf(", loss = ")
							+ ", loss = ".length())
							+ ",");
					r.write(b1.readLine() + "\n");
				}
			}
			r.close();
			b1.close();
			r2.close();
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		// preProcess();
		postProcess("train_val-6-result");
	}

}

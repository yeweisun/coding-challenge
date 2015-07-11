import java.io.*;
import java.util.*;

public class words_tweeted {

	public static void main(String[] args) {
		int init = 1, longestwordsize = 0;
		String word, ifilename, ofilename;
		long startMili = System.currentTimeMillis();// Runing time in millisec
		long endMili;

		ifilename = "../tweet_input/tweets.txt";
		ofilename = "../tweet_output/ft1.txt";

		if (args.length > 1) {
			ifilename = args[0];
			ofilename = args[1];
		} else if (args.length > 0) {
			ifilename = args[0];
		}

		HashMap<String, tweettype> CharHash = new HashMap<String, tweettype>();

		File file = new File(ifilename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// get a line each time until file end
			while ((tempString = reader.readLine()) != null) {
				// display line number
				//System.out.println("line " + line + ": " + tempString);
				//line++;

				String delimiter = " ";
				StringTokenizer tokenizer = new StringTokenizer(tempString,delimiter);
				while (tokenizer.hasMoreTokens()) {
					word = tokenizer.nextToken();
					tweettype t = (tweettype) CharHash.get(word);
					if (t != null) {
						t.count++;
					} else {
						tweettype t1 = new tweettype();
					//	t1.name = word;
						t1.count = init;
						CharHash.put(word, t1);

						if (word.length() > longestwordsize) {
							longestwordsize = word.length();
						}
					}
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		endMili = System.currentTimeMillis();
		System.out.println("counting ended, spend: " + (endMili - startMili) + " milliseconds");

		try {
			// write in the output file
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					ofilename)));

			Set<String> wordset = CharHash.keySet();
			TreeSet<String> sortedset = new TreeSet<String>(wordset);

			endMili = System.currentTimeMillis();
			System.out.println("sorting ended,spend: " + (endMili - startMili) + " milliseconds");

			for (String w : sortedset) {
				tweettype t = (tweettype) CharHash.get(w);
				pw.println(w +"\t"+ t.count);
			}
			//pw.println(wordset.size());

			pw.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}

		endMili = System.currentTimeMillis();
		System.out.println("program ended,spend: " + (endMili - startMili) + " milliseconds");
	}
}

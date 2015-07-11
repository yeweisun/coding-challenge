import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;

import java.util.Vector;

public class median_unique {

	public static void main(String[] args) {
		long startMili = System.currentTimeMillis();// Runing time in millisec
		long endMili;
		String word, ifilename, ofilename;
		ifilename = "../tweet_input/tweets.txt";
		ofilename = "../tweet_output/ft2.txt";

		if (args.length > 1) {
			ifilename = args[0];
			ofilename = args[1];
		} else if (args.length > 0) {
			ifilename = args[0];
		}

		Vector<Double> medians = new Vector<Double>();

		File file = new File(ifilename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			Vector<String> tweet = new Vector<String>();

			boolean find;
			int newWordcount = 0, tweetcount = 0;
			double median = 0;

			// get a line each time until file end
			while ((tempString = reader.readLine()) != null) {
				String delimiter = " ";
				StringTokenizer tokenizer = new StringTokenizer(tempString,
						delimiter);
				
				while (tokenizer.hasMoreTokens()) {
					find = false;
					word = tokenizer.nextToken();

					for (String s : tweet) {
						if (s.equalsIgnoreCase(word)) {
							find = true;
							break;
						}
					}
					if (!find) {
						tweet.addElement(word);
						newWordcount++;
					}
				}
				median = (median * tweetcount + newWordcount)
						/ (tweetcount + 1);
				medians.add(new Double(median));
				newWordcount = 0;
				tweetcount++;
				tweet.clear();
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
		System.out.println("counts ended,spend: " + (endMili - startMili) + " milliseconds");

		try {
			// write in the output file
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					ofilename)));

			for (Double m : medians) {
				pw.println(new java.text.DecimalFormat("0.00").format(m.doubleValue()));
			}

			pw.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		endMili = System.currentTimeMillis();
		System.out.println("program ended,spend: " + (endMili - startMili) + " milliseconds");
	}

}

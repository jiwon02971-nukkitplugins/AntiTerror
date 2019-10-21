package iKguana.antiterror;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.nukkit.Server;

public class LogAPI {
	public static File logFolder;

	public LogAPI() {
		logFolder = new File(Server.getInstance().getDataPath(), "customLogs");
		logFolder.mkdirs();
	}

	public static void saveLog(String fname, String data) {
		(new File(logFolder, fname)).mkdirs();

		String yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());
		String kkMM = new SimpleDateFormat("kk:MM", Locale.KOREA).format(new Date());

		File file = new File(logFolder, fname + File.separator + yyyyMMdd + ".txt");
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)), true);
			writer.println(kkMM + " " + data);
			writer.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}

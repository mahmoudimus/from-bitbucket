package tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.cocuyo.util.FileExtensionFilter;

public class TestOpenResource {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String _appPath = "D:\\home\\arian\\eclipse\\cocuyospace\\bin";
		File _appFolder = new File(_appPath);
		System.out.println(_appPath + ":");

		for (File _file : _appFolder.listFiles(new FileExtensionFilter("jar"))) {
			System.out.println(_file);
			JarFile _jar = new JarFile(_file);
			JarEntry _entry = _jar.getJarEntry("install");

			if (_entry != null) {
				InputStream _input = _jar.getInputStream(_entry);

				InputStreamReader _strmReader = new InputStreamReader(_input);
				BufferedReader _buffReader = new BufferedReader(_strmReader);

				String _line;

				while ((_line = _buffReader.readLine()) != null) {
					System.out.println("$" + _line + "$");
				}

			}
		}
	}
}

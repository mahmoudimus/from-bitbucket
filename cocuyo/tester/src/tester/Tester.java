package tester;

import org.cocuyo.CocuyoException;
import org.cocuyo.ant.CocuyoAntConfigurator;
import org.cocuyo.ant.CocuyoAntInstaller;
import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.extension.ExtensionManager;
import org.cocuyo.startup.Startup;

public class Tester {

	/**
	 * @param args
	 * @throws CocuyoException 
	 */
	public static void main(String[] args) throws CocuyoException {
		CocuyoAntInstaller _installer = new CocuyoAntInstaller();
		_installer.registerExtensionPoints();
		_installer.installExtensions();
		Startup.main("ant");
		/*Startup 
				.main("gen Main -in dsls -show-text -ext c:\\home\\arian\\eclipse\\cocuyospace\\cocuyo\\ext");*/
		/*Startup
				.main("gold Pepe -in c:\\home\\arian\\net\\TigerCompiler\\TigerCompiler\\Pepe.gr -ext c:\\home\\arian\\eclipse\\cocuyospace\\cocuyo\\ext");*/

		//DomainModel _model = new DomainModel(null, null);
		//_model.
	}

}

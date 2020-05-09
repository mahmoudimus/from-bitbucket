package org.cocuyo.help.shell;

import static org.cocuyo.util.Printing.print;
import static org.cocuyo.util.Printing.println;
import static org.cocuyo.util.Text.NEW_LINE;

import java.util.Hashtable;

import org.cocuyo.extension.ExtensionPoint;

@ExtensionPoint
public class HelpShell implements IHelpShell {
	private String fTitle;
	private Hashtable<String, HelpItem> fItems;

	public HelpShell(String aTitle) {
		fTitle = aTitle;
		fItems = new Hashtable<String, HelpItem>();
	}

	public void addHelpItem(HelpItem aItem) {
		fItems.put(aItem.getName(), aItem);
	}

	public void addHelpItem(String aName, String aDescription) {
		addHelpItem(new HelpItem(aName, aDescription));
	}

	@Override
	public void show() {
		print("*** ");
		print(fTitle);
		println(" ***");
		println();

		for (HelpItem _item : fItems.values()) {
			println(_item.getName() + ":" + NEW_LINE + "\t"
					+ _item.getDescription());
		}
	}

	@Override
	public boolean showHelpItem(String aItemName) {
		if (fItems.containsKey(aItemName)) {
			println(fTitle);
			println();
			HelpItem _item = fItems.get(aItemName);
			println(aItemName + "\t:\t" + _item.getDescription());
			return true;
		}

		return false;
	}

}

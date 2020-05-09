package org.cocuyo.help.shell;

import static org.cocuyo.util.Printing.println;

import java.util.ArrayList;

import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.env.ShellArguments;
import org.cocuyo.extension.ExtensionManager;
import org.cocuyo.extension.IStartExtension;

public class HelpShellManager implements IStartExtension
{

	ArrayList<IHelpShell> fHelps;

	@Override
	public void start()
	{
		try
		{
			ShellArguments _args = CocuyoEnvironment.getShellArguments();

			if (!_args.getFreeValues().isEmpty()
					&& _args.getFirstFreeValue().equals("help"))
			{

				loadHelps();

				if (_args.getFreeValues().size() == 2)
				{

					boolean _existHelp = false;

					String _helpItemName = _args.getFreeValues().get(1);

					for (IHelpShell _help : fHelps)
					{
						_existHelp = _help.showHelpItem(_helpItemName)
								|| _existHelp;
					}

					if (!_existHelp)
					{
						println("Don't exist help for item '" + _helpItemName
								+ "'");
					}
				} else
				{
					for (IHelpShell _help : fHelps)
					{
						_help.show();
						println();
					}
				}

				println();
				println();

			} else if (_args.isEmpty())
			{
				printHelpFormat();
			}
		} catch (Exception _ex)
		{
			throw new RuntimeException(_ex);
		}
	}

	private void loadHelps()
	{
		fHelps = new ArrayList<IHelpShell>();

		for (IHelpShell _help : ExtensionManager
				.findAllExtensionFor(IHelpShell.class))
		{
			fHelps.add(_help);
		}
	}

	public void printHelpFormat()
	{
		println("For help pass argument:");
		println();
		println("help");
		println();
		println();
	}
}

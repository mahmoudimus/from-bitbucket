/**
 * 
 */
package org.cocuyo.help.shell;

public class HelpItem
{
    private String fName;
    private String fDescription;

    public HelpItem(String aName, String aDescription)
    {
	fName = aName;
	fDescription = aDescription;
    }

    public String getName()
    {
	return fName;
    }

    public void setName(String aItemName)
    {
	fName = aItemName;
    }

    public String getDescription()
    {
	return fDescription;
    }

    public void setDescription(String aItemHelpDescription)
    {
	fDescription = aItemHelpDescription;
    }
}
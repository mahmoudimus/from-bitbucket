package org.cocuyo.dsl.grammar.ast;

//open-imports//close-imports
public class RuleLeftSide
//open-inheritance//close-inheritance
{
	private Id id;
	//open-fields//close-fields
	
	public RuleLeftSide(Id id)
	{
		this.id = id;
	}
	//open-members

    public String getName()
    {
	return id.getText();
    }

    //close-members
}
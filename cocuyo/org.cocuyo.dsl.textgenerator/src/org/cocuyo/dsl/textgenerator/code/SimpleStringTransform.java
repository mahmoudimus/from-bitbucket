/**
 * 
 */
package org.cocuyo.dsl.textgenerator.code;

/**
 * @author Arian Fornaris Fernandez
 * 
 */
public class SimpleStringTransform implements IStringTransformation
{
    public enum Transform
    {
	UPPER_CASE, LOWER_CASE
    }

    public static SimpleStringTransform upperCase = new SimpleStringTransform(
	    Transform.UPPER_CASE);
    public static SimpleStringTransform lowerCase = new SimpleStringTransform(
	    Transform.LOWER_CASE);

    private final Transform fTransform;

    private SimpleStringTransform(Transform aTransform)
    {
	fTransform = aTransform;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cocuyo.generator.code.IStringTransformation#transform(java.lang.String)
     */
    @Override
    public String transform(String aString)
    {
	String _result = aString;

	switch (fTransform)
	{
	case LOWER_CASE:
	    _result = _result.toLowerCase();
	    break;
	case UPPER_CASE:
	    _result = _result.toUpperCase();
	    break;
	}

	return _result;
    }
}

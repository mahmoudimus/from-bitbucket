package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.code.ICode;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.code.NewlineCode;
import org.cocuyo.dsl.textgenerator.code.StringCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class StringLiteralCode extends Code
//open-inheritance//close-inheritance
{
	private StringToken string_literal;
	//open-fields

	private ICode fCode;

	//close-fields

	public StringLiteralCode(StringToken string_literal) {
		this.string_literal = string_literal;
	}

	//open-members

	@Override
	public Object execute(ExecutionEnvironment aEnv) {
		if (fCode == null) {

			if (string_literal.isLineString()) {
				String _str = string_literal.getText();
				//_str = _str.substring(0, _str.length() - 1);
				//Eso ya se calcula en StringToken
				fCode = new StringCode(_str);

				ListCode _list = new ListCode();
				_list.add(fCode);
				_list.add(NewlineCode.NEWLINE);
				fCode = _list;
			} else
				fCode = new StringCode(string_literal.getText());
		}

		return fCode;
	}
	//close-members
}
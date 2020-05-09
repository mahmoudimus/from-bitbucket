package org.cocuyo.dsl.entity.type;

import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.dsl.entity.DomainModelElement;

public class EntityFieldType extends DomainModelElement {

	public static EntityFieldType loadFromClasspath(String aClassName,
			DomainModel aModel) throws ClassNotFoundException {
		EntityFieldType _fieldType = null;
		Class _class = EntityFieldType.class.forName(aClassName);
		try {
			_fieldType = (EntityFieldType) _class.newInstance();
			_fieldType.setModel(aModel);
		} catch (Exception _e) {
			throw new RuntimeException("Expecting class '"
					+ EntityFieldType.class.getName() + "', founded '"
					+ _class.getName() + "'", _e);
		}
		return _fieldType;
	}

	public EntityFieldType(String aName, DomainModel aModel) {
		super(aName, aModel);
	}

	protected EntityFieldType(String aName) {
		super(aName, null);
	}

}

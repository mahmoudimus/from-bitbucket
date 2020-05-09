package org.cocuyo.dsl.protocol;

public interface IObjectPackageMember extends INamed {

	String getFullName();

	ObjectPackage getContainer();

	ObjectPackage getRoot();

}

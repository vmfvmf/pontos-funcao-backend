package com.vmf.cpf.interfaces;

public interface ICustomCallback<T> {
	public T execute(T objectOfTType) throws Exception;
}

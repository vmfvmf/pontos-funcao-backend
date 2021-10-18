package com.vmf.cpf.interfaces;

public interface IValidator<T> {
	public void validate(T entityOfTType) throws Exception;
}

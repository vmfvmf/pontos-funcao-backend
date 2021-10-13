package com.vmf.interfaces;

public interface IValidator<T> {
	public void validate(T entityOfTType) throws Exception;
}

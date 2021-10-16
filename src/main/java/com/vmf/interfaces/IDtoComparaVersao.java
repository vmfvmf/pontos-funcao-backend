package com.vmf.interfaces;

public interface IDtoComparaVersao<E, O> extends IHaveGetObjetos<O>, ISetAlteradoDadoContagem, IGetId {
	public void checkComparacao(E entidadeAnterior);
}

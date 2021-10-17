package com.vmf.interfaces;

import com.vmf.enums.ContagemDadoSituacaoEnum;

public interface IDtoContagemDadoAlterado extends IGetId {
	void setAlteradoDadoContagem(ContagemDadoSituacaoEnum situacao);
}

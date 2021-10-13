package com.vmf.enums;

public enum TipoContagemItemEnum {
	TRANSACAO("TRANSACAO"),
	ARQUIVO_REFERENCIADO("ARQUIVO_REFERENCIADO");
	
	private final String label;

    private TipoContagemItemEnum(String label) {
        this.label = label;
    }
    
    public String getLabel() {
    	return label;
    }
}

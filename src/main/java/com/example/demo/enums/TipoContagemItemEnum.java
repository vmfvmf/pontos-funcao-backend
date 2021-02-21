package com.example.demo.enums;

public enum TipoContagemItemEnum {
	TRANSACAO("TRANSACAO"),
	ARQUIVO_REFERENCIADO("ARQUIVO_REFERENCIADO");
	
	public final String label;

    private TipoContagemItemEnum(String label) {
        this.label = label;
    }
}

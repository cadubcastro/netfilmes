package br.com.cadubcastro.netfilmes.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}

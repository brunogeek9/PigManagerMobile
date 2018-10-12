package br.ufrn.eaj.tads.pigmanager.retrofit;

import br.ufrn.eaj.tads.pigmanager.servicos.ServicoUsuario;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    Retrofit retrofit;

    //Subistituir esse endereço para o IP da máquina ou a url do serviço
    //IP da máguina: Quando o serviço estiver rodando localmente
    //URL: Quando o serviço estiver hospedado na WEB
    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.91:8084/PigManager/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ServicoUsuario getUsuarioService(){
        return this.retrofit.create(ServicoUsuario.class);
    };
}

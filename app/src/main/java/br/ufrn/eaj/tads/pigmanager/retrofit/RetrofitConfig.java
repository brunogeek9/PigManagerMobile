package br.ufrn.eaj.tads.pigmanager.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.ufrn.eaj.tads.pigmanager.servicos.ServicoMatriz;
import br.ufrn.eaj.tads.pigmanager.servicos.ServicoUsuario;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    Retrofit retrofit;

    /* Anotar alguns ips
    * Contêiner Laura: 10.77.34.122
    * */
    // Subistituir esse endereço para o IP da máquina ou a url do serviço
    // IP da máguina: Quando o serviço estiver rodando localmente
    // URL: Quando o serviço estiver hospedado na WEB
    public RetrofitConfig() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Gson gsonConverterFactory = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.17:8080/PigManager/")
                .addConverterFactory(GsonConverterFactory.create(gsonConverterFactory))
                .client(client)
                .build();
    }

    public ServicoUsuario getUsuarioService(){
        return this.retrofit.create(ServicoUsuario.class);
    };

    public ServicoMatriz getMatrizService() {
        return  this.retrofit.create(ServicoMatriz.class);
    }
}

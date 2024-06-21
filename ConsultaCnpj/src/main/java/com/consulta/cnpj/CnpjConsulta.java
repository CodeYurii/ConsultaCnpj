package com.consulta.cnpj;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * Classe responsável por conectar por URL na API e retorna o resultado como um StringBuilder
 */


public class CnpjConsulta {
    private static final String API_URL = "https://gateway.apiserpro.serpro.gov.br/consulta-cnpj-df-trial/v2/basica/";
    private static final String BEARER_TOKEN = "Coloque seu Bearer aqui";

    public String consultar(String cnpj) throws IOException, InterruptedException {
        String url = API_URL + cnpj;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();
        System.out.println("JSON Response: " + jsonResponse);

        // Verifica se a resposta é um objeto JSON
        JsonElement jsonElement = JsonParser.parseString(jsonResponse);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Extraindo os dados do JSON
            String ni = getValueAsString(jsonObject, "ni");
            String tipoEstabelecimento = getValueAsString(jsonObject, "tipoEstabelecimento");
            String nomeEmpresarial = getValueAsString(jsonObject, "nomeEmpresarial");
            String nomeFantasia = getValueAsString(jsonObject, "nomeFantasia");
            String situacaoCadastral = getSituacaoCadastral(jsonObject);
            String dataAbertura = getValueAsString(jsonObject, "dataAbertura");
            String naturezaJuridica = getValueAsString(jsonObject.getAsJsonObject("naturezaJuridica"), "descricao");
            String cnaePrincipal = getValueAsString(jsonObject.getAsJsonObject("cnaePrincipal"), "descricao");
            String tipoLogradouro = getValueAsString(jsonObject.getAsJsonObject("endereco"), "tipoLogradouro");
            String logradouro = getValueAsString(jsonObject.getAsJsonObject("endereco"), "logradouro");
            String numero = getValueAsString(jsonObject.getAsJsonObject("endereco"), "numero");
            String complemento = getValueAsString(jsonObject.getAsJsonObject("endereco"), "complemento");
            String cep = getValueAsString(jsonObject.getAsJsonObject("endereco"), "cep");
            String bairro = getValueAsString(jsonObject.getAsJsonObject("endereco"), "bairro");
            String municipio = getValueAsString(jsonObject.getAsJsonObject("endereco").getAsJsonObject("municipio"), "descricao");
            String uf = getValueAsString(jsonObject.getAsJsonObject("endereco"), "uf");
            String pais = getValueAsString(jsonObject.getAsJsonObject("endereco").getAsJsonObject("pais"), "descricao");

            StringBuilder sb = new StringBuilder();
            sb.append("CNPJ: ").append(cnpj).append("\n\n");
            sb.append("Nome Empresarial: ").append(nomeEmpresarial).append("\n");
            sb.append("Nome Fantasia: ").append(nomeFantasia).append("\n");
            sb.append("Tipo de Estabelecimento: ").append(tipoEstabelecimento).append("\n");
            sb.append("Data de Abertura: ").append(dataAbertura).append("\n");
            sb.append("Situação Cadastral: ").append(situacaoCadastral).append("\n");
            sb.append("Natureza Jurídica: ").append(naturezaJuridica).append("\n");
            sb.append("CNAE Principal: ").append(cnaePrincipal).append("\n\n");
            sb.append("Endereço:\n");
            sb.append("Tipo Logradouro: ").append(tipoLogradouro).append("\n");
            sb.append("Logradouro: ").append(logradouro).append("\n");
            sb.append("Número: ").append(numero).append("\n");
            sb.append("Complemento: ").append(complemento).append("\n");
            sb.append("CEP: ").append(cep).append("\n");
            sb.append("Bairro: ").append(bairro).append("\n");
            sb.append("Município: ").append(municipio).append("\n");
            sb.append("UF: ").append(uf).append("\n");
            sb.append("País: ").append(pais).append("\n\n");

            return sb.toString();
        } else {
            return "Resposta inválida da API.";
        }
    }

    // Método para obter valores de um JsonObject como String
    private String getValueAsString(JsonObject jsonObject, String key) {
        return jsonObject.has(key) ? jsonObject.get(key).getAsString() : "N/A";
    }

    // Método para obter a situação cadastral
    private String getSituacaoCadastral(JsonObject jsonObject) {
        JsonObject situacaoCadastral = jsonObject.getAsJsonObject("situacaoCadastral");
        if (situacaoCadastral != null) {
            String codigo = getValueAsString(situacaoCadastral, "codigo");
            String data = getValueAsString(situacaoCadastral, "data");
            return "Código: " + codigo + " - Data: " + data;
        } else {
            return "N/A";
        }
    }
}


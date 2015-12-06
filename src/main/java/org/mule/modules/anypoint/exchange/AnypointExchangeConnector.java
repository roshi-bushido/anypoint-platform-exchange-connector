package org.mule.modules.anypoint.exchange;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.anypoint.exchange.config.ConnectorConfig;
import org.mule.modules.anypoint.exchange.config.DefaultResponseHandler;

@Connector(name="anypoint-exchange", friendlyName="Anypoint Exchange")
public class AnypointExchangeConnector {
    private static String API_PLATFROM_BASE_URI = "https://anypoint.mulesoft.com/exchange/api";
	
    @Config
    ConnectorConfig config;
    
    protected String getUriFor(String resource) {
    	return API_PLATFROM_BASE_URI + resource;
    }

    /**
     * Gets all the apis from the api platform
     *
     * {@sample.xml ../../../doc/anypoint-platform-connector.xml.sample anypoint-exchange:list-objects}
     *
     * @return this list of apis
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Processor
    public JSONArray listObjects(
    		@Optional @Default(value="10") Integer pageSize, 
    		@Optional @Default(value="0") Integer pageNumber, 
    		@Optional @Default(value="") String searchQuery) throws ClientProtocolException, IOException {
        
    	CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> map = new ArrayList<NameValuePair>();
        map.add(new BasicNameValuePair("include", "public"));
        map.add(new BasicNameValuePair("perPage", pageSize.toString()));
        map.add(new BasicNameValuePair("page", pageNumber.toString()));
        map.add(new BasicNameValuePair("searchTerms", searchQuery));

        String queryString = URLEncodedUtils.format(map, Charset.defaultCharset());
        HttpGet getAPIsRequest = new HttpGet(this.getUriFor(String.format("/%s/objects?%s", this.config.getOrganizationId(), queryString)));
        getAPIsRequest.addHeader("studio", "Bearer " + this.config.getSessionAccessToken());
        getAPIsRequest.addHeader("Content-Type", "application/json");
        
        String sessionResponseBody = httpClient.execute(getAPIsRequest, new DefaultResponseHandler());
        return new JSONArray(sessionResponseBody);
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}
package bsu.comp152;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class UnivModel {
    private String location;

    public UnivModel(String loc){
        location = loc;
    }

    public ArrayList<APIUniv> getData(){
        var dataGrabber = HttpClient.newHttpClient();
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(location)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            System.out.println("Error connecting to network or site");
        }
        catch (InterruptedException e){
            System.out.println("Connection to site broken");
        }
        if (response == null ){
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        ArrayList<APIUniv> univData = jsonInterpreter.fromJson(usefulData, new TypeToken<ArrayList<APIUniv>>(){}.getType());
        return univData;
    }

    class APIUniv{
        ArrayList<String> domains;
        String country;
        ArrayList<String> web_pages;
        String name;
        @SerializedName("state-province")
        String stateProvince;
        String alpha_two_code;
    }
}

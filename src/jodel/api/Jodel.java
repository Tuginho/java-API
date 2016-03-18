package jodel.api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import jodel.Crawler;

import org.json.JSONException;
import org.json.JSONObject;


public class Jodel {
	private String accessToken;
	
	public Jodel(String token){
		accessToken = token;
	}
	
	public void putLocation(JodelLocation loc) throws Exception{
		JSONObject jsonLoc = loc.toJSONObj();
		JSONObject outer = new JSONObject();
		outer.put("location", jsonLoc);
		
		String content = outer.toString();
		String charset = StandardCharsets.UTF_8.name(); 
		System.out.println(content);
		
		URL url = new URL("http://api.go-tellm.com/api/v2/users/place");
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setDoOutput(true); 
		urlConnection.setRequestMethod("PUT");
		urlConnection.setRequestProperty("User-Agent", "Jodel/65000 Dalvik/2.1.0 (Linux; U; Android 6.0.1; Find7 Build/MMB29M)");
		urlConnection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
		urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
		
		try (OutputStream output = urlConnection.getOutputStream()) {
		    output.write(content.getBytes(charset));
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream(), StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		reader.close();
		Crawler.log(sb.toString());
		
		
		if (urlConnection.getResponseCode() != 204){
			//fail
			Crawler.log("location put failed");
		}
	}
	
	public JSONObject getPosts(JodelLocation loc) throws Exception{
		//GET /api/v2/posts/location/combo?lat=47.6750029&lng=9.1720287
		Formatter formatter = new Formatter(Locale.US);
		URL url = new URL(formatter.format("https://api.go-tellm.com/api/v2/posts/location/combo?lat=%f&lng=%f", loc.lat, loc.lng).toString());
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestProperty("User-Agent", "Jodel/4.3.7 Dalvik/2.1.0 (Linux; U; Android 6.0.1; Find7 Build/MMB29M)");
		urlConnection.setRequestProperty("Authorization", "Bearer "+ accessToken);
		urlConnection.setRequestMethod("GET");
		
		//x headers
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		urlConnection.setRequestProperty("X-Timestamp", sdf.format(new Date()));
		urlConnection.setRequestProperty("X-Client-Type", "android_4.3.7");
		urlConnection.setRequestProperty("X-Api-Version", "0.1");
		urlConnection.setRequestProperty("X-Authorization", "HMAC CDCAC434FE4363C3ADFB696ECDBECC9BCB7B421E");
		
		//get content
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream(), StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		reader.close();
		String result = sb.toString();
		
		return new JSONObject(result);
	}
	
	public JSONObject getDistinctID() throws Exception{
		URL url = new URL("https://api.go-tellm.com/api/v2/distinctID");
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setRequestProperty("User-Agent", "Jodel/65000 Dalvik/2.1.0 (Linux; U; Android 6.0.1; Find7 Build/MMB29M)");
		urlConnection.setRequestProperty("Authorization", "Bearer "+ accessToken);
		urlConnection.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream(), StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		reader.close();
		String result = sb.toString();
		Crawler.log(result);
		
		return new JSONObject(result);
	}
	
	public void putToken() throws Exception{
		JSONObject outer = new JSONObject();
		outer.put("client_id", JodelAuth.clientid);
		outer.put("push_token", "APA91bFwFCBHXPdCCGoPQGSBj9wkdelRAtQgkRPmao5Shyi10s_BoaajReKG4Km-UHc8jW8spELpKr-jgbdxHY25k_taFf1VM3d7qPlcphavZ5PjPUBknozcLFq8NDKX4xDayJZh5pVD");
		
		String content = outer.toString();
		String charset = StandardCharsets.UTF_8.name(); 
		System.out.println(content);
		
		URL url = new URL("https://api.go-tellm.com/api/v2/users/pushToken/");
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setDoOutput(true); 
		urlConnection.setRequestMethod("PUT");
		urlConnection.setRequestProperty("User-Agent", "Jodel/65000 Dalvik/2.1.0 (Linux; U; Android 6.0.1; Find7 Build/MMB29M)");
		urlConnection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
		urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
		
		try (OutputStream output = urlConnection.getOutputStream()) {
		    output.write(content.getBytes(charset));
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream(), StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		reader.close();
		Crawler.log(sb.toString());
		
		
		if (urlConnection.getResponseCode() != 204){
			//fail
			Crawler.log("location put failed");
		}
	}
}

package util;

import java.io.IOException;  
import org.apache.commons.httpclient.*;  
import org.apache.commons.httpclient.methods.*;  

public class HttpHelper {
	public String SendHttpRequest(String type, String url, String xml){
		String ResponseBody = "";
		type = type.toUpperCase();
		if (type.equalsIgnoreCase("GET")){
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("Content-type", "application/xml");
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(get);
				ResponseBody = get.getResponseBodyAsString();
				get.releaseConnection();
				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		else if (type.equalsIgnoreCase("POST")){
			PostMethod post = new PostMethod(url);
			post.setRequestBody(xml);
			post.setRequestHeader("Content-type", "application/xml");
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(post);
				ResponseBody = post.getResponseBodyAsString();
				post.releaseConnection();
				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		else if (type.equalsIgnoreCase("PUT")){
			PutMethod put = new PutMethod(url);
			put.setRequestBody(xml);
			put.setRequestHeader("Content-type", "application/xml");
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(put);
				ResponseBody = put.getResponseBodyAsString();
				put.releaseConnection();
				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		else{
		}
		return ResponseBody;
	}
}

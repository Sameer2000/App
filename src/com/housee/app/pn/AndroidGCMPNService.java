package com.housee.app.pn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidGCMPNService {

	//REFER
	//http://stackoverflow.com/questions/17805876/android-gcm-sending-link-testing
	
    static private String testdeviceId = "APA91bECwIADB5Qn6JABB6dZVqcSBMSfOGT1DCkFKN6jpHtD7Lkh4uirm9BNIBdrCgBpc-yU_s0ic0Yxr-kwYpwVb-rDZMw_8MEHASRtONCy4e_04H4WSwH4pQVIjxhbzvLZt5cBEHut";
    final static private String apiId = "AIzaSyCcImFqm3ln2490lLsGiwFM2cwETToJDeo";//"YOUR_API_ID";
    final static private String sendUrl = "https://android.googleapis.com/gcm/send";

    public static void main(String[] args) {
		AndroidGCMPNService.testPush(null, null);
	}
    
    public static void testPush(String[] deviceIdList,String strDeviceId) {
        URL url;
        HttpsURLConnection urlConnection;
        OutputStream os = null;
        InputStream is = null;;
        try {
            url = new URL(sendUrl);
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("User-Agent", "Android Push tester");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "key="+apiId);
            JSONObject message = new JSONObject();
            JSONArray regIds = new JSONArray();
            JSONObject data = new JSONObject();
            
//            for(int i=0;i<deviceIdList.length;i++){
//            	regIds.put(deviceId);
//            }

            regIds.put(testdeviceId);
            message.put("registration_ids", regIds);
            //message.put("collapse_key", value)
            data.put("payload", "Welcome Houseee!");
            message.put("data", data);
            urlConnection.setDoOutput(true);
            os = urlConnection.getOutputStream();
            os.write(message.toString().getBytes());
            os.flush();
            int status = urlConnection.getResponseCode();
            is = urlConnection.getInputStream();
            byte[] response = new byte[4096];
            is.read(response);
            String responseText = String.valueOf(response); 
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            os = null;
            is = null;
        }
    }

    public static void sendPush(List<String> deviceIdList, String messageString) {
        URL url;
        HttpsURLConnection urlConnection;
        OutputStream os = null;
        InputStream is = null;;
        try {
            url = new URL(sendUrl);
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("User-Agent", "Android Push tester");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "key="+apiId);
            JSONObject message = new JSONObject();
            JSONArray regIds = new JSONArray();
            JSONObject data = new JSONObject();
            
            for(int i=0;i<deviceIdList.size();i++){
            	regIds.put(deviceIdList.get(i));
            }

            message.put("registration_ids", regIds);
            //message.put("collapse_key", value)
            data.put("payload", messageString);

            
            
            
            message.put("data", data);
            urlConnection.setDoOutput(true);
            os = urlConnection.getOutputStream();
            os.write(message.toString().getBytes());
            os.flush();
            int status = urlConnection.getResponseCode();
            is = urlConnection.getInputStream();
            byte[] response = new byte[4096];
            is.read(response);
            String responseText = String.valueOf(response); 
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            os = null;
            is = null;
        }
    }

    
}

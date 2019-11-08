package ma.ormvasm.factei;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Utilisateur;

import static android.provider.Settings.System.DATE_FORMAT;

public class ApiConnector {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_FORMAT = "HH:mm:ss";

    public  JSONArray getData(Class myClass, String urlString,ArrayList<?> response){
        JSONArray jsonArray = null;
        HttpURLConnection urlConnection =null;

        try{
            URL url = new URL(urlString);
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept-Encoding", "gzip");
                InputStream is = new GZIPInputStream(urlConnection.getInputStream());
                GsonBuilder gb = new GsonBuilder()
                        .registerTypeHierarchyAdapter(byte[].class,new ByteArrayToBase64TypeAdapter())
                        .serializeNulls()
                        .disableHtmlEscaping()
                        .setDateFormat("yyyy-MM-dd");
                gb.registerTypeAdapter(Time.class, new TimeDeserializer());
                Gson gson = gb.create();

                //Type listType = new TypeToken<ArrayList<Utilisateur>>(){}.getType();

                Type listType = TypeToken.getParameterized(ArrayList.class, myClass).getType();
                Type TT;
                //final ArrayList<Utilisateur>
                response = gson.fromJson(
                        new InputStreamReader(is, Charset.forName("UTF-8")),
                        listType);


                String jsonString = new Gson().toJson(response);


                try {
                    jsonArray = new JSONArray(jsonString);
                    int i =145;
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }


            }

            catch (IOException e) {
                e.printStackTrace();

            }

            finally {
                urlConnection.disconnect();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.decode(json.getAsString(), Base64.NO_WRAP);
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
        }
    }



    class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
            try {
                return new SimpleDateFormat(DATE_FORMAT, Locale.FRANCE).parse(jsonElement.getAsString());
            } catch (ParseException e) {
            }

            throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                    + "\". Supported formats: " + DATE_FORMAT);
        }
    }

    class TimeDeserializer implements JsonDeserializer<Time> {

        @Override
        public Time deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
            try {

                String s = jsonElement.getAsString();
                SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.US);
                sdf.parse(s);
                long ms = sdf.parse(s).getTime();
                Time t = new Time(ms);
                return t;
            } catch (ParseException e) {
            }
            throw new JsonParseException("Unparseable time: \"" + jsonElement.getAsString()
                    + "\". Supported formats: " + TIME_FORMAT);
        }
    }

    public static boolean isServerAlive(String urlString) {
        int t = 0;

        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlString);

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(100000);
                urlConnection.connect();

                int b = urlConnection.getResponseCode();

                if ( b != 200) return false;
                return true;
            } catch (IOException e) {
                return false; // Either timeout or unreachable or failed DNS lookup.
            }
        } catch (Exception e) {
            //Log.e(e.getMessage(),"rrr");
            return false; // Either timeout or unreachable or failed DNS lookup.

        }


    }


    public  JSONArray setData(JSONArray jsonObjArr, String urlString,Context ctx){
        //JSONArray jsonArray = null;
        HttpURLConnection urlConnection =null;

        try{
            URL url = new URL(urlString);
            try {
                urlConnection = (HttpURLConnection) url.openConnection();

                //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setInstanceFollowRedirects(false);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                urlConnection.setRequestProperty("Accept","application/json");


                DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());


                os.writeBytes(jsonObjArr.toString());


                os.flush();
                os.close();


                Log.i("hh-STATUS", String.valueOf(urlConnection.getResponseCode()));
                Log.i("hh-MSG" , urlConnection.getResponseMessage());
                Log.i("hh-CONTENT" ,urlConnection.getContent().toString());

                urlConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();

            }

            finally {
                urlConnection.disconnect();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return jsonObjArr;

    }

    public String makePOSTRequest(String url, List<NameValuePair> nameValuePairs) {
        String response = "";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }




}

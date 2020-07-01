import org.jibble.pircbot.PircBot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPI extends PircBot
{
    public static String processAPI(String z)
    {
        String n = "";
        String url = "http://api.openweathermap.org/data/2.5/weather?";
        String key = "appid=5e058159f432e98a8c8680011096d7da";

        if(containZip(z))
        {
            n = url + "zip="+ z +"&"+ key;
        }
        else
        {
            n = url + "q=" + z + "&" + key;
        }
        return getTemp(n);
    }
    public static boolean containZip(String za) {
        int number = 0;

        if (za.length() == 5)
        {

            for (int x = 0; x < za.length(); x++)
            {
                if (Character.isDigit(za.charAt(x)))
                {
                    number++;
                }
                if (number == 5) return true;

            }
        }
        return false;
    }
    public static String getTemp(String url)
    {
        try
        {
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // create GET request
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            JSONObject obj_JSONObject = new JSONObject(response.toString());
            return obj_JSONObject.getJSONArray("weather").getJSONObject(0).getString("description");

        }catch(Exception e) {e.printStackTrace();}
        return null;
    }
}

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieAPI
{
    public static String url = "http://www.omdbapi.com/?";
    public static String key = "a4c0fa84";
    public static String a = "";

    public static String processMovieAPI(String t, String y)
    {
        if(containYear(y))
        {
            a = url + "t=" + t + "&y=" + y + "&apikey=" + key;
            return getMovie(a);
        }
        return null;
    }
    public static String processMovieAPI(String t)
    {
        a = url + "t=" + t + "&apikey=" + key;
        return getMovie(a);
    }
    public static String getMovie(String a) {
        try {

            URL obj = new URL(a);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // create GET request
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject obj_JSONObject = new JSONObject(response.toString());
            String rated = obj_JSONObject.getString("Rated");
            String runtime = obj_JSONObject.getString("Runtime");
            String genre = obj_JSONObject.getString("Genre");
            String plot = obj_JSONObject.getString("Plot");
            String awards = obj_JSONObject.getString("Awards");
            JSONObject ratings = obj_JSONObject.getJSONArray("Ratings").getJSONObject(1);
            String ratings2 = ratings.toString();
            return "Rated:" + rated + ", Runtime:" + runtime + ", Genre:" + genre + ", Plot:" + plot + ", Awards:" + awards + ", Ratings:"+ratings2;

        } catch (Exception e) {e.printStackTrace();}

        return null;
    }
    public static boolean containYear(String za) {
        int number = 0;

        if (za.length() == 4)
        {
            for (int x = 0; x < za.length(); x++)
            {
                if (Character.isDigit(za.charAt(x)))
                {
                    number++;
                }
                if (number == 4) return true;
            }
        }
        return false;
    }
}

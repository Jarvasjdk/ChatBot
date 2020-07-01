import org.jibble.pircbot.*;

public class MyBot extends PircBot {

    private boolean valid, valid2, title = false;
    public static String store = "";

    public MyBot()
    {
        // name given when joining server
        this.setName("jack1243");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message)
    {
        if (message.equalsIgnoreCase("time"))
        {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + " The time is now" + time);
        }
        if(message.equalsIgnoreCase("hello"))
        {
            sendMessage(channel, sender + " Hey how are you doing today?");
        }
        if(valid)
        {
            sendMessage("#jake1900", "The weather is " + WeatherAPI.processAPI(message));
            valid = false;
        }
        if(message.contains("weather")||message.contains("Weather"))
        {
            valid = true;
            sendMessage(channel,sender + " Enter your zip-code or city");

        }
        // if no year given return information
        if(message.contains("q"))
        {
            sendMessage("#jake1900",MovieAPI.processMovieAPI(store));
        }
        if(title)
        {
            store = message;
            sendMessage("#jake1900","Enter a year, if unknown enter 'q'");
            title = false;
        }
        if(containYear(message))
        {
            if(valid2) // tests to make sure client doesn't accidentally enter year
            sendMessage("#jake1900", MovieAPI.processMovieAPI(store,message));
        }
        if(message.contains("movie"))
        {
            title = true;
            valid2 = true;
            sendMessage("#jake1900","Enter title of movie");
        }
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

    public static void getBot()
    {
        try
        {
            MyBot bot = new MyBot();

            bot.setVerbose(true);

            // Connect to the IRC server.
            bot.connect("irc.freenode.net");

            // Join the #pircbot channel.
            bot.joinChannel("#jake1900");

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

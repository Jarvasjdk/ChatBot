import java.io.*;
import java.net.*;

        public class Main
        {
            public static void main(String[] args)
            {
                // the server to connect to our details
                String server = "irc.freenode.net";
                String jack1243 = "jack1243";
                String login = "user";
                // the channel which the bot will join
                String channel = "#jake1900";
                // connect directly to the irc server
                Socket socket = null;
                try
                {
                    socket = new Socket(server,  6667);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // log onto the server
                    writer.write("JACK1243 " + jack1243 + "\r\n");
                    writer.write("USER " + login + "8 * : JAVA IRC Hacks Bot \r\n");
                    writer.flush();
                    // read lines onto the server until it tells us we have connection
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        if (line.indexOf("004") >= 0)
                        {
                            // at this point we connected to the server
                            break;
                        }
                        else if (line.indexOf("433") >= 0) {
                            System.out.println("Nickname is already in use.");
                            return;
                        }
                    }
                    // join the channel
                    writer.write("JOIN " + channel + "\r\n");
                    writer.flush();
                    // keep writing lines from the server
                    while ((line = reader.readLine()) != null)
                    {
                        if (line.toLowerCase().startsWith("PING "))
                        {
                            // We must respond to pings to avoid being disconnected
                            writer.write("PONG " + line.substring(5) + "\r\n");
                            writer.write("PRIVMSG " + channel + ":I got pinged \r\n");
                            writer.flush();
                        }
                    }
                }catch (IOException e) {e.printStackTrace();}
                // connect bot to irc server
                MyBot.getBot();
            }
        }


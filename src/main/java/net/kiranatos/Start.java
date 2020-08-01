package net.kiranatos;

import net.kiranatos.crawlers.CrawlerOfYouTubePage;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Start {
    public static void main(String[] args) throws IOException {
        //String str = CrawlerOfYouTubePage.getVideoName("https://www.youtube.com/watch?v=SKy71bCcOyg&list=PLpt61bADOMwW2aA9I1hWHyR8OixSuCXzc&index=5");
        //CrawlerOfYouTubePage.getVideosFromPlaylist("https://www.youtube.com/playlist?list=PLoij6udfBncioun9-sBwpkpTit1SIhWko");
        //CrawlerOfYouTubePage.getVideosFromPlaylist("https://www.youtube.com/playlist?list=PLmqFxxywkatSaOvqOXuTOJQ8uQSQnfcQk");

//        String[] strArr = CrawlerOfYouTubePage.getPlaylistsFromChannel(
//                "https://www.youtube.com/c/EnglishClass101/playlists");
//
//        for (int i = 0; i < strArr.length; i++) {
//            System.out.println(i + " " + strArr[i]);
//        }

//        String str = CrawlerOfYouTubePage.getVideoName(
//                "https://www.youtube.com/playlist?list=PL5bLw9Uguvv0hbDdFVrxv5UeIXUm6BoJh");
//        System.out.println(str);
       // System.out.println(CrawlerOfYouTubePage.getVideoNode("https://www.youtube.com/watch?v=5YeHvIMVkaA"));

        System.out.println(CrawlerOfYouTubePage.getChannelWithPlaylists(
                "https://www.youtube.com/c/EnglishClass101/playlists"));


// https://www.youtube.com/watch?v=6AQk2RelHD0

        

        
    }
}


/*
System.out.println(str);
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("リジナルソング】ひか");
        System.out.println("System.getProperty(\"file.encoding\")=" + System.getProperty("file.encoding"));
        System.out.println("StandardCharsets.UTF_8.name(): " + StandardCharsets.UTF_8.name());
        System.out.println("Charset.defaultCharSet()=" + Charset.defaultCharset());

        String symbPique = "リジナルソング】ひか";
        String symbCoeur = "їєїЇЄ₴Ёёы";
        String symbCarreau = "\u2666";
        String symbTrefle = "\u2663";

        System.out.println("System.getProperty(\"file.encoding\")=" + System.getProperty("file.encoding"));
        System.out.println("StandardCharsets.UTF_8.name(): " + StandardCharsets.UTF_8.name());
        System.out.println("Charset.defaultCharSet()=" + Charset.defaultCharset());
        System.out.println("Default PrintStream: " + symbCarreau + "--" + symbCoeur + "--" + symbPique + "--" + symbTrefle);

        PrintStream outStream = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
        outStream.println("UTF-8 PrintStream: " + symbCarreau + "--" + symbCoeur + "--" + symbPique + "--" + symbTrefle);

*/
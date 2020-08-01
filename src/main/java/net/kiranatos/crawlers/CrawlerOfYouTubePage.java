package net.kiranatos.crawlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.kiranatos.exceptions.MoreThanOneTitleException;
import net.kiranatos.nodes.Node;
import net.kiranatos.nodes.NodeChannel;
import net.kiranatos.nodes.NodePlayList;
import net.kiranatos.nodes.NodeVideo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Take one https://www.youtube.com/watch?v=JZTVchX8qJs&list=PLpt61bADOMwW2aA9I1hWHyR8OixSuCXzc&index=3
 * @author Kiranatos
 */
public class CrawlerOfYouTubePage {
    
    private static String userAgent = "Chrome/4.0.249.0 Safari/532.5";
    private static int timeout = 6000;
    private static final String VIDEO_PATTERN = "(?<=(\"videoId\":\"))[a-zA-Z0-9-_]+(?=[\"])";
    private static final String PLAYLIST_PATTERN = "(?<=(playlist\\?list=))[a-zA-Z0-9-_]+(?=[\"])";
    private static final Pattern PATTERN_VIDEO = Pattern.compile(VIDEO_PATTERN);
    private static final Pattern PATTERN_PLAYLIST = Pattern.compile(PLAYLIST_PATTERN);
    private static final String YOUTUBE_LINK_TO_VIDEO = "https://www.youtube.com/watch?v=";
    private static final String YOUTUBE_LINK_TO_PLAYLIST = "https://www.youtube.com/playlist?list=";

    /**
     * Получить название видео с ю-туба по ссылке
     *
     * @param youtubeURL - link to page with one video. (Example: https://www.youtube.com/watch?v=JZTVchX8qJs)
     * @return name in s title
     * @throws IOException
     */
    public static Node getVideoNode(String youtubeURL) {
        Document docInternetPage = getDocument(youtubeURL);
        NodeVideo nv = new NodeVideo(getTitle(docInternetPage), youtubeURL);
        return nv;
    }
    
    /**
     * Получить массив ссылок на ю-туб видосики из ссылки плейлиста
     *
     * @param youtubeListURL link to youtube playlist (Example https://www.youtube.com/playlist?list=PL5bLw9Uguvv3pJd1FZjbEJbv8qcrIvjgQ)
     * @return array of links to videos from {@code youtubeListURL}
     * @throws IOException
     */
    public static Node getVideosFromPlaylist(String youtubeListURL) {
        Document docInternetPage = getDocument(youtubeListURL);
        NodePlayList np = new NodePlayList(getTitle(docInternetPage), youtubeListURL);
        String[] videostStringArr = getManyLinksFromOneLink(docInternetPage, PATTERN_VIDEO,"script", YOUTUBE_LINK_TO_VIDEO);

        Node[] videosNodeArr = new Node[videostStringArr.length];
        for (int i = 0; i < videostStringArr.length; i++) {
            Node video = getVideoNode(videostStringArr[i]);
            videosNodeArr[i] = video;
        }

        np.setVideos(videosNodeArr);
        np.setMeToMyVideos();
        return np;
    }

    /**
     * Получить массив ссылок на ю-туб плейлисты из ссылки на канал
     *
     * @param youtubeChannelURL link to youtube channel (Example: https://www.youtube.com/c/EnglishClass101/playlists)
     * @return array of links to videos from {@code youtubeChannelURL}
     * @throws IOException
     */
    public static Node getChannelWithPlaylists(String youtubeChannelURL) {
        Document docInternetPage = getDocument(youtubeChannelURL);
        NodeChannel channel = new NodeChannel(getTitle(docInternetPage), youtubeChannelURL);
        String[] playListStringArr = getManyLinksFromOneLink(docInternetPage, PATTERN_PLAYLIST,"script", YOUTUBE_LINK_TO_PLAYLIST);

        Node[] playListsNodeArr = new Node[playListStringArr.length];
        for (int i = 0; i < playListStringArr.length; i++) {
            Node playList = getVideosFromPlaylist(playListStringArr[i]);
            playListsNodeArr[i] = playList;
        }

        channel.setPlaylists(playListsNodeArr);
        channel.setMeToMyPlaylists();

        return channel;
    }

    /* #################### #################### PRIVATE METHODS #################### #################### */

    /**
     *
     * @param docInternetPage document of youtube page
     * @param pattY Pattern
     * @param cssQuery tag of page
     * @param firstPartOfLinks first part of result links (Example: https://www.youtube.com/watch?v=)
     * @return array of links to videos from {@code linkYouTube}
     * @throws IOException
     */
    private static String[] getManyLinksFromOneLink(Document docInternetPage, Pattern pattY, String cssQuery, String firstPartOfLinks) {
        List<String> videoCodes = new ArrayList<>();
        Elements links = docInternetPage.select(cssQuery);

        for (Element link: links) {
            Matcher mName = pattY.matcher(link.toString());
            while (mName.find()) {
                String t = mName.group();
                if (!videoCodes.contains(t))
                    videoCodes.add(t);
            }
        }

        String[] videoLinks = new String[videoCodes.size()];
        for (int i = 0; i < videoCodes.size(); i++) {
            videoLinks[i] = firstPartOfLinks + videoCodes.get(i);
        }

        return videoLinks;
    }

    private static String getTitle(Document document) {
        Elements links = document.getElementsByTag("title");

        if (links.size() == 1){
            return links.get(0).text();
        } else {
            System.out.println("YouTube video with url: " + document.baseUri() + " have more then one title or no one at all... ");
            return null;
        }
    }

    /**
     * Чтоб не ганять часто интернет-соединение.
     *
     * @param youtubeURL link to youtube page
     * @return org.jsoup.nodes.Document
     */
    private static Document getDocument(String youtubeURL)  {
        System.out.println("\t...getting info from: " + youtubeURL);
        try {
            return Jsoup.connect(youtubeURL)
                    .userAgent(userAgent)
                    .timeout(timeout)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

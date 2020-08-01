package net.kiranatos.nodes;

public class NodeVideo extends Node {

    Node playList;
    Node youtubeChannel;

    public NodeVideo(String name, String link) {
        super(name, link);
    }

    @Override
    public String toString() {
        return "video: " + name + " : " + link;
    }

    public void setYoutubeChannel(Node youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public void setPlayList(Node playList) {
        this.playList = playList;
    }

    public Node getYoutubeChannel() {
        return youtubeChannel;
    }

    public Node getPlayList() {
        return playList;
    }
}

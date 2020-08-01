package net.kiranatos.nodes;

public class NodePlayList extends Node {

    Node[] videos;
    Node youtubeChannel;

    public NodePlayList(String name, String link) {
        super(name, link);
    }

    public void setYoutubeChannel(Node youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public void setVideos(Node[] videos) {
        this.videos = videos;
    }

    public Node getYoutubeChannel() {
        return youtubeChannel;
    }

    public Node[] getVideos() {
        return videos;
    }

    public void setMeToMyVideos(){
        if ((videos != null) && (videos.length > 0)) {
            for (Node node: videos ) {
                if (node instanceof NodeVideo) {
                    NodeVideo nv = (NodeVideo) node;
                    nv.setPlayList(this);
                    nv.setYoutubeChannel(youtubeChannel);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("playlist: ");
        sb.append(name).append(' ').append(':').append(' ').append(link).append(System.lineSeparator()).append('\t');
        for (Node v : videos){
            sb.append(v).append(System.lineSeparator()).append('\t');
        }

        return sb.toString();
    }
}

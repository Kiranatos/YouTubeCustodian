package net.kiranatos.nodes;

public class NodeChannel extends Node {

    Node[] playlists = null;

    public NodeChannel(String name, String link) {
        super(name, link);
    }

    public void setPlaylists(Node[] playlists) {
        this.playlists = playlists;
    }

    public Node[] getPlaylists() {
        return playlists;
    }

    public void setMeToMyPlaylists(){
        if ((playlists != null) && (playlists.length > 0)) {
            for (Node node: playlists ) {
                if (node instanceof NodePlayList) {
                    NodePlayList pl = (NodePlayList) node;
                    pl.setYoutubeChannel(this);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nchannel: ");
        sb.append(name).append(' ').append(':').append(' ').append(link).append(System.lineSeparator()).append('\t');
        for (Node p : playlists){
            sb.append(p).append(System.lineSeparator()).append('\t');
        }

        return sb.toString();
    }
}

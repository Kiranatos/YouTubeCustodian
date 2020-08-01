package net.kiranatos.nodes;

import java.io.Serializable;
import java.util.*;

public abstract class Node implements Serializable {
    Set<String> tags = new HashSet<String>();
    String name;
    String link;
    String github;
    List<String> notes = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    List<String> connectedSites = new ArrayList<>();
    Date date = null;
    List<Language> languages = new ArrayList<>();


    public Node(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public abstract String toString();

    public void addErrorMessage(String message){
        errors.add(message);
    }
}

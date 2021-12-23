package ormdatabase.model;

import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public class ShimStack {
    List<Pair<Integer, Pair<Float, Float>>> compression;
    List<Pair<Integer, Pair<Float, Float>>> rebound;
    Date lastChange;
    String comment;
    Boolean favorites;
}
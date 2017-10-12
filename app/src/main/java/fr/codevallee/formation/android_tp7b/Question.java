package fr.codevallee.formation.android_tp7b;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by tgoudouneix on 11/10/2017.
 */

public class Question implements Serializable {
    public String label;
    public String A;
    public String B;
    public String C;
    public String D;
    public String theme;
    public String goodAnswer;

    @Override
    public String toString() {
        return "Question{" +
                "label='" + label + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", D='" + D + '\'' +
                ", theme='" + theme + '\'' +
                ", goodAnswer='" + goodAnswer + '\'' +
                '}';
    }
}

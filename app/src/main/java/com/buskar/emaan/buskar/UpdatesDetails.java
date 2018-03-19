package com.buskar.emaan.buskar;

/**
 * Created by Emaan on 11/30/2017.
 */

public class UpdatesDetails {
    private String post_body;
    private String post_username;
    private Double post_score;
    private String hashtag_Loc;
    private String hashtag_Prob;

    public String getPostBody() {
        return post_body;
    }
    public void setPostBody(String postBody) {
        this.post_body = postBody;
    }

    public String getPostUsername() {
        return post_username;
    }
    public void setPostUsername(String postUsername) {
        this.post_username = postUsername;
    }

    public String getPostHashtagLoc() {return hashtag_Loc;
    }
    public void setPostHashtagLoc(String postHashtagLoc) {
        this.hashtag_Loc = postHashtagLoc;
    }

    public String getPostHashtagProb() {
        return hashtag_Prob;
    }
    public void setPostHashtagProb(String postHashtagProb) {
        this.hashtag_Prob = postHashtagProb;
    }
    public Double getPostScore() {
        return post_score;
    }
    public void setPostScore(Double postScore) {
        this.post_score = postScore;
    }


}

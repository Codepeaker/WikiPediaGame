package com.codepeaker.wikipediagame.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ApiResponse {

    /**
     * batchcomplete :
     * continue : {"grncontinue":"0.549024084535|0.549024423496|57878183|0","continue":"grncontinue||"}
     * query : {"pages":{"21490054":{"pageid":21490054,"ns":0,"title":"Athletics at the 1928 Summer Olympics \u2013 Men's 5000 metres","extract":"The men's 5000 metres event was part of the track and field athletics programme at the 1928 Summer Olympics. The competition was held on Tuesday, July 31, 1928, and on Friday, August 3, 1928. Thirty-eight long-distance runners from 19 nations competed.\n\n\n== Medalists ==\n\n\n== Records ==\nThese were the standing world and Olympic records (in minutes) prior to the 1928 Summer Olympics.\n\n\n== Results ==\n\n\n=== Semifinals ===\nAll semi-finals were held on Tuesday, July 31, 1928, and started at 5:10 p.m.\nThe best four finishers of every heat qualified for the final.\nSemifinal 1\n\nSemifinal 2\n\nSemifinal 3\n\n\n=== Final ===\nThe final was held on Friday, August 3, 1928, and started at 2:30 p.m.\nThe same three runners finished on the podium than four years earlier in the 5000 metre event at the 1924 Games. But this time Ritola won the gold medal and Nurmi silver, Wide won the bronze medal again.\n\n\n== References ==\n\n\n== External links ==\nOfficial Olympic Report\nWudarski, Pawel (1999)."}}}
     */

    private String batchcomplete;
    @SerializedName("continue")
    private ContinueBean continueX;
    private QueryBean query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public ContinueBean getContinueX() {
        return continueX;
    }

    public void setContinueX(ContinueBean continueX) {
        this.continueX = continueX;
    }

    public QueryBean getQuery() {
        return query;
    }

    public void setQuery(QueryBean query) {
        this.query = query;
    }

    public static class ContinueBean {
        /**
         * grncontinue : 0.549024084535|0.549024423496|57878183|0
         * continue : grncontinue||
         */

        private String grncontinue;
        @SerializedName("continue")
        private String continueX;

        public String getGrncontinue() {
            return grncontinue;
        }

        public void setGrncontinue(String grncontinue) {
            this.grncontinue = grncontinue;
        }

        public String getContinueX() {
            return continueX;
        }

        public void setContinueX(String continueX) {
            this.continueX = continueX;
        }
    }

    public static class QueryBean {
        /**
         * pages : {"21490054":{"pageid":21490054,"ns":0,"title":"Athletics at the 1928 Summer Olympics \u2013 Men's 5000 metres","extract":"The men's 5000 metres event was part of the track and field athletics programme at the 1928 Summer Olympics. The competition was held on Tuesday, July 31, 1928, and on Friday, August 3, 1928. Thirty-eight long-distance runners from 19 nations competed.\n\n\n== Medalists ==\n\n\n== Records ==\nThese were the standing world and Olympic records (in minutes) prior to the 1928 Summer Olympics.\n\n\n== Results ==\n\n\n=== Semifinals ===\nAll semi-finals were held on Tuesday, July 31, 1928, and started at 5:10 p.m.\nThe best four finishers of every heat qualified for the final.\nSemifinal 1\n\nSemifinal 2\n\nSemifinal 3\n\n\n=== Final ===\nThe final was held on Friday, August 3, 1928, and started at 2:30 p.m.\nThe same three runners finished on the podium than four years earlier in the 5000 metre event at the 1924 Games. But this time Ritola won the gold medal and Nurmi silver, Wide won the bronze medal again.\n\n\n== References ==\n\n\n== External links ==\nOfficial Olympic Report\nWudarski, Pawel (1999)."}}
         */
        @SerializedName("pages")
        @Expose
        private Map<String, Page> pageMap;

        public Map<String, Page> getPageMap() {
            return pageMap;
        }

        public void setPageMap(Map<String, Page> pageMap) {
            this.pageMap = pageMap;
        }
    }
}

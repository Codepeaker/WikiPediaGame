package com.codepeaker.wikipediagame.model;

public class Page {


    /**
     * pageid : 18639803
     * ns : 0
     * title : Service Creek, Oregon
     * extract : Service Creek is an unincorporated community in Wheeler County, in the U.S. state of Oregon. Service Creek lies on Oregon Route 19 near its intersection with Oregon Route 207.  It is also near the mouth of a stream, Service Creek, formerly Sarvis Creek, that empties into the John Day River.A Sarvicecreek post office was established here in 1918. May Tilley was the first postmaster. Later in the year, the name was changed to Servicecreek and changed again to Service Creek in 1929. The post office closed in 1956.From 1908 through the mid-1940s, Service Creek had a one-room schoolhouse that doubled as a community center and church. The building was demolished in the late 1940s or early 1950s to make room for highway improvements.
     */

    private int pageid;
    private String title;
    private String extract;

    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }
}


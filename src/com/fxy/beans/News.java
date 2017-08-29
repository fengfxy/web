package com.fxy.beans;

public class News {
    private Integer id;

    private String title;

    private String author;

    private String photoA;

    private String photoB;

    private String photoC;

    private String st;

    private NewsType newsType;

    private String startInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPhotoA() {
        return photoA;
    }

    public void setPhotoA(String photoA) {
        this.photoA = photoA == null ? null : photoA.trim();
    }

    public String getPhotoB() {
        return photoB;
    }

    public void setPhotoB(String photoB) {
        this.photoB = photoB == null ? null : photoB.trim();
    }

    public String getPhotoC() {
        return photoC;
    }

    public void setPhotoC(String photoC) {
        this.photoC = photoC == null ? null : photoC.trim();
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st == null ? null : st.trim();
    }

   

    public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public String getStartInfo() {
        return startInfo;
    }

    public void setStartInfo(String startInfo) {
        this.startInfo = startInfo == null ? null : startInfo.trim();
    }
}
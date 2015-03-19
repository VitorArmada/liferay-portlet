package pt.impresa.liferay.content.service.model;

import com.liferay.portal.kernel.util.StringPool;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnore;

public class ImpresaContent implements Serializable {
    
    private static final long serialVersionUID = 1337L;
    
	private String title;
    private String body;
    private String lead;
    private String articleUrl;
    private String publishedDate;
    private String sectionUrl;
	private String sectionUniqueName;
    private String sectionName;
    private String contentType;
    private String articleType;
    private String author;
    private List<String> keywords;
	
	//teaserrel or picturerels 0,1,2 in order 
    private String imageSrc;
    private String imageAlt;
	private Map<String,String> videoUrls;
	//picturerels 0,1,2 in order
	private List<ImpresaContent> mediaAbove;
	private List<ImpresaContent> mediaWrapped;
	private List<ImpresaContent> mediaBelow;
    
    public ImpresaContent() { }
    
    @JsonIgnore
    public String getArticlePageUrl() {
        StringBuilder articlePageUrl = new StringBuilder();
		if(getSectionUrl() != null && !getSectionUrl().substring(0, 1).equalsIgnoreCase(StringPool.SLASH)){
			articlePageUrl.append(StringPool.SLASH);
		} 
        articlePageUrl.append(getSectionUrl());
        articlePageUrl.append(StringPool.SLASH);
        articlePageUrl.append(getArticleUrl());
        return articlePageUrl.toString();
    }
    
    @JsonIgnore
    public String getSectionPageUrl() {
        return getSectionUrl();
    }
	
    /* Raw Properties */
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getLead() {
        return lead;
    }
    
    public void setLead(String lead) {
        this.lead = lead;
    }
    
    public String getArticleUrl() {
        return articleUrl;
    }
    
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
    
    public String getImageSrc() {
        return imageSrc;
    }
    
    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
    
    public String getImageAlt() {
        return imageAlt;
    }
    
    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
		
    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Map<String,String> getVideoUrls() {
        if(videoUrls == null) {
            videoUrls = new HashMap<String, String>();
        }
        return videoUrls;
    }

    public void setVideoUrls(Map<String,String> videoUrls) {
        this.videoUrls = videoUrls;
    }

	public String getSectionUniqueName() {
		return sectionUniqueName;
	}

	public void setSectionUniqueName(String sectionUniqueName) {
		this.sectionUniqueName = sectionUniqueName;
	}

    public String getSectionUrl() {
        return sectionUrl;
    }

    public void setSectionUrl(String sectionUrl) {
        this.sectionUrl = sectionUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    
    public List<ImpresaContent> getMediaAbove() {
		return mediaAbove;
	}
    
    public List<ImpresaContent> getMediaBelow() {
		return mediaBelow;
	}
    
    public List<ImpresaContent> getMediaWrapped() {
		return mediaWrapped;
	}
    
    public void setMediaAbove(List<ImpresaContent> mediaAbove) {
		this.mediaAbove = mediaAbove;
	}
    
    public void setMediaBelow(List<ImpresaContent> mediaBelow) {
		this.mediaBelow = mediaBelow;
	}
    
    public void setMediaWrapped(List<ImpresaContent> mediaWrapped) {
		this.mediaWrapped = mediaWrapped;
	}
    
}

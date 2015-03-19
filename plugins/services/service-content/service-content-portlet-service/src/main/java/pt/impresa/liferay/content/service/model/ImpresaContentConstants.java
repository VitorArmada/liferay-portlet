package pt.impresa.liferay.content.service.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public interface ImpresaContentConstants {
    
    public class FIELD {
        
		public static final String STATUS = "status";
		public static final String VIDEO_SAPO_HD = "video.sapo_hd";
		public static final String VIDEO_NET = "video.net";
		public static final String NAME = "name";
		public static final String UID = "uid";
		public static final String LEAD = "leadtext";
		public static final String BODY = "body";
		public static final String TITLE = "title";
		public static final String ALTERNATES = "ALTERNATES";
		public static final String PUBLISHED_DATE = "published_date";
		public static final String ARTICLE_URL = "articleurl";
		public static final String CONTENT_TYPE = "contenttype";
		public static final String ARTICLE_TYPE = "articletype";
		public static final String URL = "url";
		public static final String VIDEOURLS = "VIDEOURLS";
		public static final String PROFILE = "PROFILE";
		public static final String VIDEO_URL_PREFIX = "VIDEO.";
		public static final String HOMESECTION = "homeSection";
		public static final String AUTHOR = "AUTHOR";
		
		public static class DEFAULT {
			
			public static final List<String> DETAILS = Arrays.asList
				( ImpresaContentConstants.FIELD.UID
				, ImpresaContentConstants.FIELD.URL
				, ImpresaContentConstants.FIELD.CONTENT_TYPE
				, ImpresaContentConstants.FIELD.TITLE
                , ImpresaContentConstants.FIELD.LEAD
				, ImpresaContentConstants.FIELD.BODY
				, ImpresaContentConstants.FIELD.PUBLISHED_DATE
				, ImpresaContentConstants.FIELD.VIDEO_NET
				, ImpresaContentConstants.FIELD.VIDEO_SAPO_HD
				, ImpresaContentConstants.FIELD.STATUS
				, ImpresaContentConstants.FIELD.AUTHOR
				, ImpresaContentConstants.FIELD.LEAD );
			
			public static final List<String> LIST = Arrays.asList
				( ImpresaContentConstants.FIELD.UID
				, ImpresaContentConstants.FIELD.URL
				, ImpresaContentConstants.FIELD.CONTENT_TYPE
				, ImpresaContentConstants.FIELD.TITLE
                , ImpresaContentConstants.FIELD.LEAD
                , ImpresaContentConstants.FIELD.ARTICLE_TYPE
				, ImpresaContentConstants.FIELD.PUBLISHED_DATE
				, ImpresaContentConstants.FIELD.AUTHOR );
			
		}
        
    }
    
    public class SECTION {
    
        public static final String NAME = "NAME";
        public static final String UNIQUENAME = "UNIQUENAME";
        public static final String SECTIONURL = "SECTIONURL";
        
    }
    
    public class RELATION {
        
        public static final String TEASERREL = "teaserrel";
        public static final String PICTUREREL = "picturerel";
        public static final String PICTUREREL_1 = "picturerel_1";
        public static final String PICTUREREL_2 = "picturerel_2";
		public static final String STORYREL = "storyrel";
		public static final String HOMESECTION = "HOMESECTION";
		
		public static final Set<String> MEDIA_RELATIONS = Collections.synchronizedSet(new HashSet<String>(Arrays.asList(PICTUREREL,PICTUREREL_1,PICTUREREL_2)));
		
		public static final List<String> STORY_RELATIONS = Collections.synchronizedList(new LinkedList<String>(Arrays.asList(STORYREL,PICTUREREL,PICTUREREL_1,PICTUREREL_2)));

    }
    
    public class TYPE {
        
        public static final String NEWS = "news";
        public static final String PICTURE = "picture";
        
		public static class DEFAULT {
	
			public static final List<String> GENERAL_TYPE = Arrays.asList(ImpresaContentConstants.TYPE.NEWS);
			public static final List<String> RELATED_TYPE = Arrays.asList(ImpresaContentConstants.TYPE.NEWS);
			public static final List<String> DETAIL_TYPE = Arrays.asList(ImpresaContentConstants.TYPE.NEWS);
			
		}
        
    }
	
}

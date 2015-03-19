package pt.impresa.liferay.config;

public interface ImpresaConfigurationConstants {
	
	public static class CONFIG {
	
		public static final String ACTION = "action";
		public static final String ADD_FIELD_GROUP = "addFieldGroup";
		public static final String ADD_FIELD = "addField";
		
	}
	
	public static class ARTICLE {
		
		public static final String TITLE = "articleTitle";
		public static final String IMAGE_URL = "articleImageUrl";
		public static final String URL = "articleUrl";
		public static final String LEAD = "articleLead";
		public static final String KEYWORDS = "articleKeywords";

	}
	
	public static class WIDGET {

		public static final String DISABLED = "disabled";
		public static final String CHECKBOX_ON = "on";
		public static final String WIDGET_CONFIGURATION = "widgetConfiguration";
		public static final String CONFIG_MODULE = "configModule";
		public static final String CONFIG_JSON = "configJSON";
		public static final String CONFIG = "config";

		public static class LIST {

			public static final String GENERAL = "general";

			public static class GENERAL {

				public static final String MODULE_NAME = "general";
				public static final String DISPLAY_ARTICLE_PAGE = "Display on Article Page";
				public static final String DISPLAY_SECTION_PAGE = "Display on Section Page";
				public static final String DISPLAY_ALWAYS = "Display Always";
				public static final String DISPLAY = "display";
				public static final String APPEND_TO_NEW_TARGET = "appendToNewTarget";
				public static final String NEW_TARGET_ID = "newTargetId";
				public static final String NEW_TARGET_ANIM_DURATION = "newTargetAnimDuration";
				public static final String NEW_TARGET_ANIM_DELAY = "newTargetAnimDelay";

				public class DEFAULT {
					public static final int NEW_TARGET_ANIM_DURATION = 0;
					public static final int NEW_TARGET_ANIM_DELAY = 0;
				}
			}
			
			public static final String CONTENT = "content";

			public static class CONTENT {

				public static final String IMPRESA_CONTENT = "impresaContent";
				public static final String IMPRESA_CONTENT_ID = "impresaContentId";
				public static final String MODULE_NAME = "content";
				public static final String PUBLICATION = "publication";
				public static final String PUBLICATION_NAME = "publicationName";
				public static final String SECTION = "section";
				public static final String ARTICLE = "article";
				public static final String MAX_ARTICLES = "maxArticles";
				public static final String SOURCE_TYPE = "sourceType";
				public static final String CONTENT_TYPE = "contentType";
				public static final String GROUP_ID = "groupId";
				public static final String INCLUDE_SUBSECTIONS = "includeSubsections";
				public static final String HIDE_ON_CONTENT = "hideOnContent";
				public static final String CONTENT_RELATED = "Related Articles";
				public static final String CONTENT_SECTION = "Section";
				public static final String CONTENT_DETAIL = "Article Detail";
				public static final String CONTENT_SOURCE = "contentSource";

				public class DEFAULT {

					public static final int MAX_ARTICLES = 10;
					public static final int CURRENT_PAGE = 0;
					public static final boolean INCLUDE_SUBSECTIONS = false;
					public static final boolean HIDE_ON_CONTENT = false;
				}
			}

			public class FIELDS {

				public static final String FIELD_TEMPLATES = "fieldTemplates";
				public static final String CSS_CLASS = "fieldCssClass";
				public static final String MODULE_NAME = "fields";
				public static final String GROUP = "group";
				public static final String TYPE = "type";
				public static final String NGROUPS = "ngroups";
				public static final String NFIELDS = "nfields";
				public static final String IMAGE_VERSION = "imageVersion";
				public static final String DATE_FORMAT = "dateFormat";

				public class TEMPLATES {

					public static final String TITLE = "title";
					public static final String LEAD = "lead";
					public static final String BODY = "body";
					public static final String DATE = "date";
					public static final String PUBLISHED_DATE = "published_date";
					public static final String IMAGE = "image";
					public static final String MEDIA_ABOVE = "mediaAbove";
					public static final String MEDIA_WRAPPED = "mediaWrapped";
					public static final String MEDIA_BELOW = "mediaBelow";
					public static final String READMORE = "readmore";
					public static final String SHARE = "share";
					public static final String SECTION_NAME = "sectionName";
					public static final String CONTENT_TYPE = "contentType";
					public static final String ARTICLE_TYPE = "articleType";
					public static final String VIDEO_PLAYER = "videoPlayer";
					public static final String AUTHOR = "author";
					public static final String KEYWORDS = "keywords";
				}

				public class DEFAULT {

					public static final String IMAGE_VERSION = "empty";
				}
			}

			public class PAGINATION {

				public static final String MODULE_NAME = "pagination";
				public static final String CURRENT_PAGE = "currentPage";
				public static final String SHOW_BOTTOM_PAGINATION = "showBottomPagination";
				public static final String CSS_CLASS = "paginationCssClass";
				public static final String SHOW_FIRST_PAGE_LINK = "showFirstPageLink";
				public static final String FIRST_PAGE_LABEL = "firstPageLabel";
				public static final String SHOW_LAST_PAGE_LINK = "showLastPageLink";
				public static final String LAST_PAGE_LABEL = "lastPageLabel";
				public static final String SHOW_NEXT_PAGE_LINK = "showNextPageLink";
				public static final String NEXT_PAGE_LABEL = "nextPageLabel";
				public static final String SHOW_PREVIOUS_PAGE_LINK = "showPreviousPageLink";
				public static final String PREVIOUS_PAGE_LABEL = "previousPageLabel";
				public static final String MAX_ITEMS_PER_PAGE = "maxItemsPerPage";
				public static final String MAX_PAGE_LINKS = "maxPageLink";
				public static final String SHOW_PAGINATION_SUMMARY = "showPaginationSummary";
				public static final String PAGINATION_SUMMARY_LABEL = "paginationSummaryLabel";
				public static final String SHOW_FOLLOW_LINK = "showFollowLink";
				public static final String FOLLOW_LINK = "followLink";
				public static final String FOLLOW_LINK_LABEL = "followLinkLabel";

				public class DEFAULT {

					public static final int MAX_PAGE_LINKS = 0;
					public static final String CURRENT_PAGE_WRAPPER = "X";
					public static final String TOTAL_PAGES_WRAPPER = "Y";
					public static final String CSS_CLASS = "defaultPagination";
					public static final String FOLLOW_LINK_LABEL = "+";
				}
			}

			public class EVENT {

				public static final String DISPLAY_CONTENT = "displayContent";
			}

			public class SCRIPTS {

				public static final String MODULE_NAME = "scripts";
				// CAROUSEL
				public static final String USE_CAROUSEL = "useCarousel";
				public static final String CAROUSEL_ITEM_SELECTOR = "carouselItemSelector";
				public static final String CAROUSEL_PARAMETERS = "carouselParameters";
				// ELLIPSIS
				public static final String USE_ELLIPSIS = "useEllipsis";
				public static final String ELLIPSIS_ITEM_SELECTOR = "ellipsisItemSelector";
				public static final String ELLIPSIS_CHARACTER = "ellipsisCharacter";
				// CUSTOMSCROLLBAR
				public static final String USE_CUSTOMSCROLLBAR = "useCustomScrollbar";
				public static final String CUSTOMSCROLLBAR_ITEM_SELECTOR = "customScrollbarItemSelector";
				public static final String CUSTOMSCROLLBAR_PARAMETERS = "customScrollbarParameters";
				// TOGGLEVIEW
				public static final String USE_TOGGLEVIEW = "useToggleView";
				public static final String TOGGLEVIEW_DEFAULTVIEW = "toggleViewDefaultView";
				public static final String TOGGLEVIEW_ALTERNATIVEVIEW = "toggleViewAlternativeView";
				// MASONRY
				public static final String USE_MASONRY = "useMasonry";
				public static final String MASONRY_ITEM_SELECTOR = "masonryItemSelector";
				public static final String MASONRY_PARAMETERS = "masonryParameters";
				// INFINITE SCROLL
				public static final String USE_INFINITESCROLL = "useInfiniteScroll";
				public static final String INFINITESCROLL_ITEM_SELECTOR = "infiniteScrollItemSelector";
				public static final String INFINITESCROLL_ITEMS_TO_APPEND = "infiniteScrollItemsToAppend";
				public static final String INFINITESCROLL_MORE_RESULTS_LABEL = "infiniteMoreResultsLabel";
				public static final String INFINITESCROLL_PARAMETERS = "infiniteScrollParameters";

				public class DEFAULT {

					// CAROUSEL
					public static final String CAROUSEL_ITEM_SELECTOR = "article";
					public static final String CAROUSEL_PARAMETERS = "";
					// ELLIPSIS
					public static final String ELLIPSIS_ITEM_SELECTOR = "textDetails";
					// CUSTOMSCROLLBAR
					public static final String CUSTOMSCROLLBAR_ITEM_SELECTOR = "listContainer";
					public static final String CUSTOMSCROLLBAR_PARAMETERS = "";
					// TOGGLEVIEW
					public static final String TOGGLEVIEW_DEFAULTVIEW = "";
					public static final String TOGGLEVIEW_ALTERNATIVEVIEW = "";
					// MASONRY
					public static final String MASONRY_ITEM_SELECTOR = "article";
					public static final String MASONRY_PARAMETERS = "";
					// INFINITE SCROLL
					public static final String INFINITESCROLL_ITEM_SELECTOR = ".listContainer";
					public static final String INFINITESCROLL_ITEMS_TO_APPEND = ".listContainer .article";
				}
			}

			public class SOCIAL {

				public static final String MODULE_NAME = "socialNetworks";
				public static final String PUBLISH_META_TAGS = "publishMetaTags";
				public static final String USE_FACEBOOK_LIKE = "useFacebookLike";
				public static final String FACEBOOK_LIKE_CONFIG = "facebookLikeConfig";
				public static final String USE_TWITTER_TWEET = "useTwitterTweet";
				public static final String TWITTER_TWEET_CONFIG = "twitterTweetConfig";
				public static final String USE_GOOGLEPLUS_PLUSONE = "useGooglePlusPlusone";
				public static final String GOOGLEPLUS_PLUSONE_CONFIG = "googlePlusPlusoneConfig";
				public static final String USE_GOOGLEPLUS_SHARE = "useGooglePlusShare";
				public static final String GOOGLEPLUS_SHARE_CONFIG = "googlePlusShareConfig";

				public class DEFAULT {

					public static final boolean PUBLISH_META_TAGS = false;
					public static final boolean USE_FACEBOOK_LIKE = false;
					public static final boolean USE_TWITTER_TWEET = false;
					public static final boolean USE_GOOGLEPLUS_PLUSONE = false;
					public static final boolean USE_GOOGLEPLUS_SHARE = false;
					public static final String FACEBOOK_LIKE_CONFIG = "data-send='false' data-layout='button_count' data-width='90' data-show-faces='false'";
					public static final String TWITTER_TWEET_CONFIG = "data-count='horizontal'";
					public static final String GOOGLEPLUS_PLUSONE_CONFIG = "data-size='medium'";
					public static final String GOOGLEPLUS_SHARE_CONFIG = "data-annotation='bubble'";
				}
			}
		}

		public class DETAIL {

			public static final String ARTICLEURI = "articleuri";
		}
	}
}

package pt.impresa.liferay.content.service.impl;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import pt.impresa.api.content.EContent;
import pt.impresa.api.content.EPagination;
import pt.impresa.api.content.EResponse;
import pt.impresa.api.content.ESectionContent;
import pt.impresa.api.content.ESectionValue;
import pt.impresa.api.content.ESubContent;
import pt.impresa.api.content.EValue;
import pt.impresa.api.content.SectionFieldName;
import pt.impresa.liferay.content.service.model.ImpresaContent;
import pt.impresa.liferay.content.service.model.ImpresaContentConstants;
import pt.impresa.liferay.content.service.model.ImpresaContentConstants.FIELD;
import pt.impresa.liferay.content.service.model.ImpresaContentConstants.TYPE;
import pt.impresa.liferay.content.service.model.ImpresaPagination;


public class ContentAPIResultFetcher {

	// Fetch section Data into a map
	public static Map<SectionFieldName, String> getSectionData(ESectionContent section) {
		EnumMap<SectionFieldName, String> sectionData = new EnumMap<SectionFieldName, String>(SectionFieldName.class);
		if (section != null && section.getValues() != null && !section.getValues().isEmpty()) {
			for (ESectionValue sectionValue : section.getValues()) {
				sectionData.put(sectionValue.getFieldName(), sectionValue.getValue());
			}
		}
		return sectionData;
	}
	
	
	public static List<EContent> getRelatedArticles(EResponse response, int maxArticles) {
		List<EContent> result = new LinkedList<EContent>();
		if (response != null && response.getQuery() != null && response.getQuery().getOutcome() != null && !response.getQuery().getOutcome().isEmpty()) {
			EContent content = response.getQuery().getOutcome().get(0);
			if (content != null) {
				if (content.getRelated() != null) {
					for (ESubContent relatedContent : content.getRelated()) {
						if (relatedContent.getRelationName().equalsIgnoreCase(ImpresaContentConstants.RELATION.STORYREL)) {
							result.addAll(relatedContent.getChild());
							if (result.size() == maxArticles) {
								break;
							}
						}
					}
				}
			}
		}
		return result;
	}

	public static List<ImpresaContent> packageImpresaContents(List<EContent> contents) {
		List<ImpresaContent> impresaContentList = new LinkedList<ImpresaContent>();
		for (EContent content : contents) {
			impresaContentList.add(ContentAPIResultFetcher.packageImpresaContent(content));
		}
		return impresaContentList;
	}

	public static ImpresaContent packageImpresaContent(EContent content) {

		ImpresaContent impresaContent = new ImpresaContent();

		// /Fetch homesection data
		Map<SectionFieldName, String> sectionData = getSectionData(content.getHomeSection());
		impresaContent.setSectionUniqueName(sectionData.get(SectionFieldName.UNIQUENAME));
		impresaContent.setSectionName(sectionData.get(SectionFieldName.NAME));
		impresaContent.setSectionUrl(sectionData.get(SectionFieldName.SECTIONURL));

		// Fetch keywords data
		impresaContent.setKeywords(content.getTags());

		// Fetch other values
		if (content.getValues() != null) {
			for (EValue value : content.getValues()) {
				if (value.getFieldName().equalsIgnoreCase(FIELD.TITLE)) {
					impresaContent.setTitle(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.LEAD)) {
					impresaContent.setLead(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.BODY)) {
					impresaContent.setBody(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.PUBLISHED_DATE)) {
					impresaContent.setPublishedDate(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.ARTICLE_URL)) {
					impresaContent.setArticleUrl(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.CONTENT_TYPE)) {
					impresaContent.setContentType(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.ARTICLE_TYPE)) {
					impresaContent.setArticleType(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.URL)) {
					impresaContent.setArticleUrl(value.getValue());
				} else if (value.getFieldName().equalsIgnoreCase(FIELD.AUTHOR)) {
					impresaContent.setAuthor(value.getValue());
				} else if (value.getFieldName().toLowerCase().startsWith(FIELD.VIDEO_URL_PREFIX.toLowerCase())) {
					impresaContent.getVideoUrls().put(value.getFieldName().toLowerCase(), value.getValue());
				}
			}

			if (content.getRelated() != null) {
				for (ESubContent relation : content.getRelated()) {
					if (relation.getChild() != null) {
						String relationName = relation.getRelationName();
						for (EContent relatedContent : relation.getChild()) {
							if (ImpresaContentConstants.RELATION.MEDIA_RELATIONS.contains(relationName.toLowerCase())) {
								// loads article's media
								ImpresaContent multimedia = packageImpresaContent(relatedContent);
								if (multimedia != null) {
									List<ImpresaContent> items = null;
									if (ImpresaContentConstants.RELATION.PICTUREREL.equals(relationName.toLowerCase())) {
										items = impresaContent.getMediaAbove();
									} else if (ImpresaContentConstants.RELATION.PICTUREREL_1.equals(relationName.toLowerCase())) {
										items = impresaContent.getMediaWrapped();
									} else if (ImpresaContentConstants.RELATION.PICTUREREL_2.equals(relationName.toLowerCase())) {
										items = impresaContent.getMediaBelow();
									}
									if (items == null) {
										items = new LinkedList<ImpresaContent>();
										if (ImpresaContentConstants.RELATION.PICTUREREL.equals(relationName.toLowerCase())) {
											impresaContent.setMediaAbove(items);
										} else if (ImpresaContentConstants.RELATION.PICTUREREL_1.equals(relationName.toLowerCase())) {
											impresaContent.setMediaWrapped(items);
										} else if (ImpresaContentConstants.RELATION.PICTUREREL_2.equals(relationName.toLowerCase())) {
											impresaContent.setMediaBelow(items);
										}
									}
									items.add(multimedia);
								}
							} else if (ImpresaContentConstants.RELATION.TEASERREL.equalsIgnoreCase(relationName)) {
								String contentType = getContentField(relatedContent, FIELD.CONTENT_TYPE);
								if (TYPE.PICTURE.equalsIgnoreCase(contentType)) {
									// loads article's teaser image
									impresaContent.setImageSrc(getContentField(relatedContent, FIELD.URL));
									break;
								}
							} else {
								break;
							}
						}
					}
				}
			}
			if (impresaContent.getImageSrc() == null) {
				fallbackTeaser(impresaContent);
			}
		}
		return impresaContent;
	}

	private static void fallbackTeaser(ImpresaContent content) {
		if (TYPE.PICTURE.equalsIgnoreCase(content.getContentType())) {
			content.setImageSrc(content.getArticleUrl());
		} else {
			List<ImpresaContent> sortedMedia = new LinkedList<ImpresaContent>();
			if (content.getMediaAbove() != null) {
				sortedMedia.addAll(content.getMediaAbove());
			}
			if (content.getMediaWrapped() != null) {
				sortedMedia.addAll(content.getMediaWrapped());
			}
			if (content.getMediaBelow() != null) {
				sortedMedia.addAll(content.getMediaBelow());
			}
			for (ImpresaContent media : sortedMedia) {
				if (TYPE.PICTURE.equalsIgnoreCase(media.getContentType())) {
					content.setImageSrc(media.getArticleUrl());
					break;
				}
			}
		}
	}

	private static String getContentField(EContent content, String fieldName) {
		String result = null;
		if (content != null) {
			for (EValue value : content.getValues()) {
				if (fieldName.equalsIgnoreCase(value.getFieldName())) {
					result = value.getValue();
					break;
				}
			}
		}
		return result;
	}

	public static ImpresaPagination packageImpresaPagination(EPagination pagination) {
		ImpresaPagination impresaPagination = new ImpresaPagination();
		if (pagination != null) {
			Integer pageIndex = pagination.getPageIndex();
			impresaPagination.setCurrentPage(pageIndex != null ? pageIndex : 0);
			Integer pageCount = pagination.getPageCount();
			impresaPagination.setTotalPages(pageCount != null ? pageCount : 0);
		}
		return impresaPagination;
	}

}

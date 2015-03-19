<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty article.contentType}">
    <c:choose>
        <c:when test="${not empty article.articlePageUrl}">
            <a class="contentType ${article.contentType}" href="${article.articlePageUrl}"></a>
        </c:when>
        <c:otherwise>
            <a class="contentType ${article.contentType}" ></a>
        </c:otherwise>
    </c:choose>
</c:if>
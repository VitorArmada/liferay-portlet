<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty article.articleType}">
    <c:choose>
        <c:when test="${not empty article.articlePageUrl}">
            <a class="articleType ${article.articleType}" href="${article.articlePageUrl}"></a>
        </c:when>
        <c:otherwise>
            <a class="articleType ${article.articleType}" ></a>
        </c:otherwise>
    </c:choose>
</c:if>
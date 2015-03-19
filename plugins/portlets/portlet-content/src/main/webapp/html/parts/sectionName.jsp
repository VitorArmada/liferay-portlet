<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty article.sectionName}">
    <div class="sectionNameContainer">
        <span><a href="${article.sectionPageUrl}">${article.sectionName}</a></span>
    </div>
</c:if>
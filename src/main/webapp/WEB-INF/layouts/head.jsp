<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/js/jquery-2.1.0.min.js" var="jQery"/>
<script src='${jQery}'></script>
<c:url value="/js/bootstrap.min.js" var="bootjs"/>
<script src="${bootjs}"></script>


<!-- BootStrap CSS -->
<c:url value="/css/bootstrap.min.css" var="boot1"/>
<c:url value="/css/bootstrap-theme.min.css" var="boot2"/>
<link rel="stylesheet" href="${boot1}">
<link rel="stylesheet" href="${boot2}">
<!-- My CSS -->
<c:url value="/css/default.css" var="css"/>
<link rel="stylesheet" href="${css}">

<!-- TimeLine -->
<c:url value="http://www.google.com/jsapi" var="googleJsapi"/>
<script src="${googleJsapi}"></script>
<c:url value="/js/timeline.js" var="timeLineJs"/>
<script src="${timeLineJs}"></script>
<c:url value="/js/timeLineController.js" var="timeLineController"/>
<script src="${timeLineController}"></script>
<c:url value="/css/timeline.css" var="timeLineCss"/>
<link rel="stylesheet" href="${timeLineCss}">

<title>${title eq null ? "SERVER" : "title" }</title>

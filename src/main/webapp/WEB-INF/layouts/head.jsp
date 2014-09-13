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

<title>${title eq null ? "SERVER" : "title" }</title>

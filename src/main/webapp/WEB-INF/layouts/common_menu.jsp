<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/index.html" var="menuitem1"/>
<c:url value="/alarms.html" var="menuitem2"/>

<div id="menu">
	<ul>
		<li class="current_page_item"><a href="${menuitem1 }" accesskey="1" title="">Homepage</a></li>
		<li><a href="${menuitem2}" accesskey="2" title="">Allarmi</a></li>
	</ul>
</div>

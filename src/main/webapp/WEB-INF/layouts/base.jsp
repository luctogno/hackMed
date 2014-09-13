<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<tiles:insertAttribute name="head" />
</head>

<body>

	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="messaggi" />

	<tiles:insertAttribute name="body" />

	<tiles:insertAttribute name="footer" />

</body>
</html>

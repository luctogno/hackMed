<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1>
				<a href="#">SERVER</a>
			</h1>
		</div>
		<tiles:insertAttribute name="menu" />
	</div>
</div>
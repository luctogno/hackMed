<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	var messaggioSuccesso;
	var messaggioInfo;
	var messaggioWarning;
	var nessunMessaggio = false;
	var messaggioDanger;
</script>

<c:choose>
	<c:when test="${messaggioSuccesso != null}">
		<script type="text/javascript">
			messaggioSuccesso = "${messaggioSuccesso}";
		</script>
	</c:when>
	<c:when test="${messaggioInfo != null}">
		<script type="text/javascript">
			messaggioInfo = "${messaggioInfo}";
		</script>
	</c:when>
	<c:when test="${messaggioWarning != null}">
		<script type="text/javascript">
			messaggioWarning = "${messaggioWarning}";
		</script>
	</c:when>
	<c:when test="${messaggioDanger != null }">
		<script type="text/javascript">
			messaggioDanger = "${messaggioDanger}";
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			nessunMessaggio = true;
		</script>
	</c:otherwise>
</c:choose>
<div class="container">
	<div class="alert alert-success" id="divMessaggioSuccesso">
		${messaggioSuccesso}</div>
	<div class="alert alert-info" id="divMessaggioInfo">
		${messaggioInfo}</div>
	<div class="alert alert-warning" id="divMessaggioWarning">
		${messaggioWarning}</div>
	<div class="alert alert-danger" id="divMessaggioDanger">
		${messaggioDanger}</div>
</div>
<script type="text/javascript">
	var delay = 800;

	$(document).ready(function() {
		aggiornaMessaggi(false, null);
	});

	function gestisciMessaggi(mex, div, reload, newLocation) {
		if (mex != null) {
			div.html(mex);
			div.slideDown(300).delay(delay).slideUp(300, function() {
				if (reload) {
					location.reload();
				}
				if (newLocation != null && newLocation != "") {
					location.href=newLocation;
				}
			});
		} else {
			div.hide();
		}
	}

	function aggiornaMessaggi(reload, newLocation) {
		gestisciMessaggi(messaggioSuccesso, $("#divMessaggioSuccesso"), reload,
				newLocation);
		gestisciMessaggi(messaggioInfo, $("#divMessaggioInfo"), reload,
				newLocation);
		gestisciMessaggi(messaggioWarning, $("#divMessaggioWarning"), reload,
				newLocation);
		gestisciMessaggi(messaggioDanger, $("#divMessaggioDanger"), reload,
				newLocation);
	}
</script>
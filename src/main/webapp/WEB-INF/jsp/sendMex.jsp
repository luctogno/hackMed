<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:url value="/js/json.js" var="jsonJs"/>
<script src="${jsonJs}"></script>

<c:url value="../messaggi" var="url"></c:url>

<form:form id="formID" method="POST" modelAttribute="mex"
			action="${url}">

			<form:hidden path="id" />
			<form:hidden path="command" />
			<form:hidden path="data" />
			<div class="f_sx ottantaPerCento">
				
				<div class="input-group">
				  <span class="input-group-addon">TO</span>
				  <form:input  id="idDestinatario" path="destinatario" type="text" class="form-control" placeholder="destinatario" />
				</div>
				<div class="input-group">
				  <span class="input-group-addon">FROM</span>
				  <form:input id="idAutore" path="author" type="text" class="form-control" placeholder="mittente" />
				</div>
				<div><!-- --><br /><br /></div>
				<div>
				<form:label path="content">MESSAGE</form:label>
				<form:textarea placeholder="message" rows="5" cols="50" class="form-control" id="txtAreaContent"
						path="content"></form:textarea>
				</div>
			</div>
			<div class="f_dx submit">
				<input type="submit" class="button" value="Send Message" />
			</div>

		</form:form>

<div style="clear: both;"><!--  --></div>

<script>
$('#formID').submit(function(event) {
	event.preventDefault();
	$.ajax({
		url : "${url}",
		type : 'POST',
		data : JSON.stringify(serializeForm($(this))),
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			messaggioSuccesso = "Messaggio inviato con successo";
			aggiornaMessaggi(false,"./");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			messaggioDanger = "Errore nell'invio del messaggio";
			aggiornaMessaggi(false,null);
		}
	});
	return false;
});
</script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="wrapper">
  <div id="featured-wrapper">
    <div id="featured" class="container">
      
      <!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="active homeTabs"><a href="#home" role="tab" data-toggle="tab">Home</a></li>
		<li class="homeTabs"><a href="#timeLine" role="tab" data-toggle="tab">Eventi</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
				<div class="tab-pane active" id="home">
					<div class="column1 column">
						<img id="imageSmile" src="img/ok.jpeg" style="width:100%; height: 100%; padding: auto;" onClick="changeImage();"/>
					</div>
					<div class="column2 columnBig">
						<ul class="list-group">
						<c:forEach var="listVar" items="${alarms}"> //add the model attribute of list in items
						<c:if test="${listVar.severity eq 'LOW'}">
 							<li class="list-group-item list-group-item-info"><c:out value="${listVar.title}"/> - <c:out value="${listVar.description}"/></li>
						</c:if>
						<c:if test="${listVar.severity eq 'MEDIUM'}">
 							<li class="list-group-item list-group-item-warning"><c:out value="${listVar.title}"/> - <c:out value="${listVar.description}"/></li>
						</c:if>
						<c:if test="${listVar.severity eq 'HIGH'}">
 							<li class="list-group-item list-group-item-danger"><c:out value="${listVar.title}"/> - <c:out value="${listVar.description}"/></li>
						</c:if> 
					</c:forEach>
						</ul>
					</div>
				</div> <!-- home -->
				
				<div class="tab-pane" id="timeLine">
					<div id="mytimeline"><!--  --></div>
				</div> <!-- timeLine -->
	</div> <!-- tab-content -->
      
      
    </div>  <!-- Container -->
  </div>
</div>

<script>
	function changeImage(){
		$("#imageSmile").attr("src","img/sad.png");
	}
</script>


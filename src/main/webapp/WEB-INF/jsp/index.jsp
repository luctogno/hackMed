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
						<img id="imageSmile" src="img/ok.png" style="width:100%; height: 100%; padding: auto;" onClick="changeImage();"/>
					</div>
					<div class="column2 columnBig">
						<ul class="list-group">
						<c:forEach var="listVar" items="${alarms}"> //add the model attribute of list in items
						<c:if test="${listVar.severity eq 'LOW'}">
 							<div class="panel panel-info">
 							<div class="panel-heading">
							    <h3 class="panel-title"><c:out value="${listVar.title}"/></h3>
							  </div>
							  <div class="panel-body">
							    <c:out value="${listVar.description}"/>
							  </div>
							 </div>
						</c:if>
						<c:if test="${listVar.severity eq 'MEDIUM'}">
						<div class="panel panel-warning">
 							<div class="panel-heading">
							    <h3 class="panel-title"><c:out value="${listVar.title}"/></h3>
							  </div>
							  <div class="panel-body">
							    <c:out value="${listVar.description}"/>
							  </div>
							 </div>
						</c:if>
						<c:if test="${listVar.severity eq 'HIGH'}">
 							<div class="panel panel-danger">
 							<div class="panel-heading">
							    <h3 class="panel-title"><c:out value="${listVar.title}"/></h3>
							  </div>
							  <div class="panel-body">
							    <c:out value="${listVar.description}"/>
							  </div>
							 </div>
						</c:if> 
					</c:forEach>
						</ul>
					</div>
				</div> <!-- home -->
				
				<div class="tab-pane" id="timeLine">
					<div id="mytimeline"><!--  --></div>
				</div> <!-- timeLine -->
	</div> <!-- tab-content -->
     
    <script type="text/javascript">
    var timeline;
	
	google.load("visualization", "1");
	
	// Set callback to run when API is loaded
	google.setOnLoadCallback(drawVisualization);
		
		// Called when the Visualization API is loaded.
		function drawVisualization() {
		// Create and populate a data table.
		var data = [];
		<c:forEach var="alert" items="${storico}">
			data.push({
				'start' : new Date(),
				'content' : '${alert.title}',
				'id' : '${alert.id}',
				'description' : '${alert.description}'
				// Optional: a field 'end'
				// Optional: a field 'group'
				// Optional: a field 'className'
				// Optional: a field 'editable'
			});
		</c:forEach>
		
		// specify options
		var options = {
		  "width":  "100%",
		  "height": "400px",
		  "style": "box" // optional
		};
		
		// Instantiate our timeline object.
		timeline = new links.Timeline(document.getElementById('mytimeline'));
		
		// Draw our timeline with the created data and options
		timeline.draw(data, options);
		
		//La timeline non disegna nulla se inizialmente è in un div nascosto.
		//Per evitarlo forzo il redraw della timeline al cambio di tab.
		 $('a[data-toggle="tab"]').on('shown.bs.tab',function(e) {
			timeline.redraw();
		});
		
		//Triggero il redraw al resize della finestra.
	    //(Viene chiamata al cambio di orientamento di un tablet).
		$(window).resize(function() {
			timeline.redraw();
		});
	}
		
		function changeImage(){
			$("#imageSmile").attr("src","img/sad.png");
		}
    </script>
      
    </div>  <!-- Container -->
  </div>
</div>


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
						<span class="icon icon-cogs"></span>
						<div class="title">
							<h2>Maecenas lectus sapien</h2>
						</div>
						<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
							Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
					</div>
					<div class="column2 column">
						<span class="icon icon-legal"></span>
						<div class="title">
							<h2>Praesent scelerisque</h2>
						</div>
						<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
							Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
					</div>
					<div class="column3 column">
						<span class="icon icon-unlock"></span>
						<div class="title">
							<h2>Fusce ultrices fringilla</h2>
						</div>
						<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
							Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
					</div>
					<div class="column4 column">
						<span class="icon icon-wrench"></span>
						<div class="title">
							<h2>Etiam posuere augue</h2>
						</div>
						<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
							Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
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
		<c:forEach var="alert" items="${alertList}">
			data.push({
				'start' : new Date(${intervento.getRealCreated().getYear() + 1900}, ${intervento.getRealCreated().getMonth()}, ${intervento.getRealCreated().getDate()}),
				'content' : '${intervento.getName()}',
				'id' : '${intervento.getId()}',
				'description' : '${intervento.getDescription()}'
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
    </script>
      
    </div>  <!-- Container -->
  </div>
</div>


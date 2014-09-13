var timeline;

google.load("visualization", "1");

// Set callback to run when API is loaded
google.setOnLoadCallback(drawVisualization);
	
	// Called when the Visualization API is loaded.
	function drawVisualization() {
	// Create and populate a data table.
	var data = new google.visualization.DataTable();
	data.addColumn('datetime', 'start');
	data.addColumn('datetime', 'end');
	data.addColumn('string', 'content');
	
	data.addRows([
	  [new Date(2010,7,23), , 'Conversation<br>' +
		'<img src="img/comments-icon.png" style="width:32px; height:32px;">'],
	  [new Date(2010,7,23,23,0,0), , 'Mail from boss<br>' +
		'<img src="img/mail-icon.png" style="width:32px; height:32px;">'],
	  [new Date(2010,7,24,16,0,0), , 'Report'],
	  [new Date(2010,7,26), new Date(2010,8,2), 'Traject A'],
	  [new Date(2010,7,28), , 'Memo<br>' +
		'<img src="img/notes-edit-icon.png" style="width:48px; height:48px;">'],
	  [new Date(2010,7,29), , 'Phone call<br>' +
		'<img src="img/Hardware-Mobile-Phone-icon.png" style="width:32px; height:32px;">'],
	  [new Date(2010,7,31), new Date(2010,8,3), 'Traject B'],
	  [new Date(2010,8,4,12,0,0), , 'Report<br>' +
		'<img src="img/attachment-icon.png" style="width:32px; height:32px;">']
	]);
	
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


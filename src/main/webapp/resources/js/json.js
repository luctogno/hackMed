function serializeForm(form) {
		var jsonData = {};
		var formData = form.serializeArray();
		$.each(formData, function() {
			if (jsonData[this.name]) {
				if (!jsonData[this.name].push) {
					jsonData[this.name] = [ jsonData[this.name] ];
				}
				jsonData[this.name].push(this.value || null);
			} else {
				jsonData[this.name] = this.value || null;
			}
			

		});
		console.log(jsonData);
		return jsonData;
	}
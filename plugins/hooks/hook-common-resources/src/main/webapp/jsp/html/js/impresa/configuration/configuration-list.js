IWListConfiguration = (function(){
    var instance = {
		
		getTemplateAjax : function (path, callback) {
			var source;
			var template;
 
			$.ajax({
				url: path,
				success: function(data) {
					source = data;
					if (callback) callback(data);
				}
			});
		},
		
		addGroup : function() {
			
			console.log("init2");
			instance.getTemplateAjax(instance.templates.fieldGroup, function(data){
				var newElement = $(data);
				newElement.find('#addFieldButton').click( newElement.find('#fieldsContainer'), function(event){
					instance.addField(event.data);
				});
				$('#configFields').find('#groupsContainer').append(newElement);
			});
        },
                
        addField : function(parentElement) {
            var newElement = $(instance.templates.titleField);
            $(parentElement).append(newElement);
        },
                
        init : function(templates) {
			
			instance.templates = templates;
			
            $('#configFields').find('#addGroupButton').click(function() {
				console.log("init");
                instance.addGroup();
            });
        }
    }; 
    
    return instance;
    
})();


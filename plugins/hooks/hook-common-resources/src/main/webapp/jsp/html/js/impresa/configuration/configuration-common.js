//TODO: Think better about this
var IWConfiguration = (function(){
    
    var instance = {
        init : function() {
            $( "#configTabs" ).tabs();
        }
    }; 
    
    return instance;
    
})();

head.ready(function() {
    IWConfiguration.init();
});



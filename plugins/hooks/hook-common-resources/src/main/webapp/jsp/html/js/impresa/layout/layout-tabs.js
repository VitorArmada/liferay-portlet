var LayoutTabs = (function(){
    
    var instance = {
	
		configureTabs : function () {
			
			var tabContainers = $('div.tabsContainer');
			
			if(tabContainers !== undefined) {
				
				$(tabContainers).each(function(index, tabContainerElement) {
					
					//Hack: Change tab ids to their original condition
					var tabDivs = $(tabContainerElement).find("div[class*='tabIndex-']");
					if(tabDivs !== undefined) {
						$(tabDivs).each(function(index,tabDivElement){
							$(tabDivElement).attr('id','tabs-' + (index + 1));
						});
					}

					var tabLinks  = $(tabContainerElement).find('li > a.tablink');
					if(tabLinks !== undefined) {
						$(tabLinks).each(function(tabLinkIndex, tabLinkElement){
							var tabId = $(tabLinkElement).attr('href');
							var tab = $(tabLinkElement).closest('div.tabsContainer').find(tabId);
							if(tab !== undefined) {
								var portletTitle = $(tab).find('input.portlet-title');
								if(portletTitle !== undefined && portletTitle.val() !== "") {
									$(tabLinkElement).text($(portletTitle).val());
								}
							}
						});
					} 
					
					$(tabContainerElement).tabs({
						activate : function( event, ui ) {
							//Liferay.fire(PortalCommon.Events.PAGE_VIEW);
						}
					});
					
				});
				
			}
			
		}
		
	};
	
	return instance;
})();
var PortletCommon = (function() {
	var instance = {
		
		Content : {
			goToPage : function(pageUrl, portletId, replacer) {
				$.ajax({
					url: pageUrl,
					success: function(data) {
						replacer(portletId, data);
						Liferay.fire(PortalCommon.Events.PAGE_VIEW);
					}
				});
			}
		},
		
		VisualEffects: {
			
			reloadToggle : function() {
				$toggleButtons = $(".toggleViewButtons div");
				$portletElement = $toggleButtons.closest("div[id*='p_p_id_']");
				$parentElement = $portletElement;
				$portletContainers = $parentElement;
				$lookupTabs = $parentElement.parents(".tabsLayout");
				if($lookupTabs.length > 0) {
					$parentElement = $lookupTabs;
					$portletContainers = $parentElement.find("div[id*='p_p_id_']");
				}
				
				if ($.cookie("cookieCurrentView") == "alternativeView") {
					$portletContainers.addClass('alternativeView').removeClass('defaultView');
					$toggleButtons.filter(".toggleToDefaultView").removeClass('activeView');
					$toggleButtons.filter(".toggleToAlternativeView").addClass('activeView');
				} else {
					$portletContainers.addClass('defaultView').removeClass('alternativeView');
					$toggleButtons.filter(".toggleToAlternativeView").removeClass('activeView');
					$toggleButtons.filter(".toggleToDefaultView").addClass('activeView');
				}
			} ,
			
			toggleView: function(buttonElement) {
				
				if ($.cookie("cookieCurrentView") === "defaultView" || $.cookie("cookieCurrentView") === undefined ) {
					$.cookie("cookieCurrentView", "alternativeView");
				} else {
					$.cookie("cookieCurrentView", "defaultView");
				}
				instance.VisualEffects.reloadToggle();
				Liferay.fire(PortalCommon.Events.TOGGLE_VIEW,  { portlets: $portletElement } );
				
			},
			hideDisabledPortlets: function() {
				var hiddenElements = $('.portlet-hidden');
				if (hiddenElements !== undefined) {
					$(hiddenElements).each(function(index, element) {
						$(element).closest("div[id*='p_p_id_']").hide();
					});
				}
			},
			fadeInFadeOutReplace: function(elementId, data) {
				var element = $('div[id*="p_p_id_' + elementId + '"]')
								.find('.portlet-body')
								.last();
				if (element !== undefined) {
					$(element).fadeOut('fast', function() {
						$(element).html(data);
						$(element).fadeIn('fast');
					});
				}
			}
		}
	};

	return instance;

})();



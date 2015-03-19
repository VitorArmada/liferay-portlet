var PortalCommon = (function() {
	var instance = {	
		Events : {
			TOGGLE_VIEW : "toggleView",
			INFINITE_SCROLL : "infiniteScroll",
			PAGE_VIEW : "pageView",
			SWITCH_TABS : "switchTabs"
		},
		
		Ads : {
			stickyMrec : function() {
				//reload iframe
				$('.stickyMrec iframe').attr('src', function (i, val) { return val; });
				//move mrec
				$(".stickyMrec").css({
					display: "none",
					position: "fixed",
					top: "10px"
				});
				setTimeout(function () {
					$(".stickyMrec").fadeIn(500);
				}, 750);
				//back to default position
				$(window).scroll(function () {
					var top = $(window).scrollTop();
					if (top < 750) {
						$(".stickyMrec").css({
							position: "static",
							top: "auto"
						});
					}
				});
						
			},
			
			initAds : function() {
				Liferay.on(instance.Events.INFINITE_SCROLL, instance.Ads.stickyMrec);
			}
				
		}
		
	};
	return instance;
}());
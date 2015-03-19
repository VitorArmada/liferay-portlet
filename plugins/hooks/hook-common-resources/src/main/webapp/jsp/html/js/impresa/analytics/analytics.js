var WebAnalytics  = (function() {
	var instance = {

		init : function(params) {
			if(params.platforms !== undefined) {
				instance.platforms = {};
				for(var key in params.platforms) {
					if(params.platforms.hasOwnProperty(key)) {
						var platform;
						switch(key) {
							case "NETSCOPE_2" :
								instance.platforms.NETSCOPE_2 = platform = instance.netscope2;
								break;
							case "NETSCOPE_3" :
								instance.platforms.NETSCOPE_3 = platform = instance.netscope3;
								break;
							case "SAPO_ANALYTICS" :
								instance.platforms.SAPO_ANALYTICS = platform = instance.sapoWebAnalytics;
								break;
							case "GOOGLE_ANALYTICS" :
								instance.platforms.GOOGLE_ANALYTICS = platform = instance.googleAnalytics;
								break;
						}
						
						//initialize parameters
						for(var initParameter in params.platforms[key]) {
							if(params.platforms[key].hasOwnProperty(initParameter)) {
								platform[initParameter] = params.platforms[key][initParameter];
							}
						}
						
					}
				}
			}
			
			for(var key in instance.platforms) {
				if(instance.platforms.hasOwnProperty(key)) {
					if(typeof(instance.platforms[key].init) === 'function') {
						instance.platforms[key].init();
					}
				}
			}
		},

		
		trackPageView : function() {
			for(var key in instance.platforms) {
				if(instance.platforms.hasOwnProperty(key)) {
					if(typeof(instance.platforms[key].trackPageView) === 'function') {
						instance.platforms[key].trackPageView();
					}
				}
			}
		},
				
		trackAjaxEvent : function() {
			for(var key in instance.platforms) {
				if(instance.platforms.hasOwnProperty(key)) {
					if(typeof(instance.platforms[key].trackAjaxEvent) === 'function') {
						instance.platforms[key].trackAjaxEvent();
					}
				}
			}
		},
		
		netscope2 : {
			init : function () {
				window.WEBO_ID_GROUPE = instance.netscopeCompanyId;
				this.w_counter = new wreport_counter
					( this.WRP_SECTION
					, this.WRP_SUBSECTION
					, this.WRP_ID
					, null
					, this.WRP_CHANNEL
					, this.WRP_SECTION_GRP
					, this.WRP_SUBSECTION_GRP); 
				this.w_counter.add_content(this.WRP_CONTENT);
			},
			trackPageView : function() {
				if (typeof this.w_counter !== undefined) {
					this.w_counter.count();
				}
			},
			trackAjaxEvent : function() {
				if (typeof this.w_counter !== undefined) {
					this.w_counter.count();
				}
			}
		},
		
		netscope3 : {
			init : function () {
			},
			trackPageView : function() {
				window.pp_gemius_identifier = this.identifier;
				window.pp_gemius_extraparameters = new Array('gA=' + this.value);
				window.pp_gemius_event = function() {var x = window.gemius_sevents = window.gemius_sevents || []; x[x.length]=arguments;};
				( function(d,t) { var ex; try { var gt=d.createElement(t),s=d.getElementsByTagName(t)[0],l='http'+((location.protocol=='https:')?'s://secure':'://data'); gt.async='true'; gt.src=l+'.netscope.marktest.pt/netscope-gemius.js'; s.parentNode.appendChild(gt);} catch (ex){}}(document,'script'));
			},
			trackAjaxEvent : function() {
				if(typeof pp_gemius_event !== undefined) {
					pp_gemius_event(this.identifier,'gA=' + this.value);
				}
			}
		},
				
		sapoWebAnalytics : {
			init : function () {
			},
			trackPageView : function() {
				if (typeof SAPO !== 'undefined' && typeof SAPO.WebAnalytics !== 'undefined') {
					SAPO.WebAnalytics.track(JSON.constructor({
						swakt: this.swakt,
						swasection: this.swasection,
						swasubsection: this.swasubsection,
						swasectiongrp: this.swasectiongrp,
						swasubsectiongrp: this.swasubsectiongrp,
						swacontent: this.swacontent,
						swachannel: this.swachannel
					}));
				}
			},
			trackAjaxEvent : function() {
				if (typeof SAPO !== 'undefined' && typeof SAPO.WebAnalytics !== 'undefined') {
					SAPO.WebAnalytics.track(JSON.constructor({
						swakt: this.swakt,
						swasection: this.swasection,
						swasubsection: this.swasubsection,
						swasectiongrp: this.swasectiongrp,
						swasubsectiongrp: this.swasubsectiongrp,
						swacontent: this.swacontent,
						swachannel: this.swachannel
					}));
				}
			}
		},
		
		googleAnalytics : {
			init : function () {
				window._gaq = _gaq || [];
				_gaq.push(['_setAccount', this.account]);
				_gaq.push(['_setDomainName', this.domainName]);
				_gaq.push(['_setAllowLinker', true]);
				_gaq.push(['_setCustomVar', 1, 'Section', this.section, 3]);
				_gaq.push(['_setCustomVar', 2, 'Sub-Section', this.subSection, 3]);
			},
			trackPageView : function() {
				if (typeof _gaq !== undefined) {
					_gaq.push(['_trackPageview']);
				}
			},
			trackAjaxEvent : function() {
				if (typeof _gaq !== undefined) {
					_gaq.push(['_trackEvent', 'ajax', 'click']);
				}
			}
		}
	};
	return instance;
}());

			
			
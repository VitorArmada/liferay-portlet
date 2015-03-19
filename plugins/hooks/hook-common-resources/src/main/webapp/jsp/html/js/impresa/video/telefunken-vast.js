telefunken.giveUp = function(sneiders) {
	if(sneiders && $(sneiders).contents() && $(sneiders).contents()[0]){
		var str = $(sneiders).contents()[0].data;
		if(str.indexOf("[CDATA[") >= 0) {
			str = str.replace("[CDATA[","");
			str = str.replace("]]","");
		}
		return str;	
	}
	return "";
};

/**
 * Triggers the process of fetching a VAST response from the ad server, parsing that resonse
 * and preparing the player to play the ads
 */
telefunken.getVAST = function(playerWrapper, playerParams) {
	
	if (!$.support.cors && window.XDomainRequest) {
		
		// Use Microsoft XDR
		var xdr = new XDomainRequest();
		xdr.open("get", playerParams.vastUrl);
		xdr.onload = function () {
			var doc = new ActiveXObject('Microsoft.XMLDOM');
			doc.async = false;
			try {
				doc.loadXML(xdr.responseText);
			} catch(e) {
				doc = undefined;
			}
			if (!doc || !doc.documentElement || doc.getElementsByTagName('parsererror').length) {
				status.code = 500;
				status.message = 'parseerror';
				throw 'Invalid XML: ' + xdr.responseText;
			}
			telefunken.parseVAST(playerWrapper, playerParams)(doc,null);
		};
		xdr.onprogress = function(){ };
		xdr.ontimeout = function(){ };
		xdr.onerror = function () { };
		setTimeout(function(){
			xdr.send();
		}, 0);
		
	} else {
		$.ajax({
			url : playerParams.vastUrl,
			dataType : "xml",
			success : telefunken.parseVAST(playerWrapper, playerParams)
		});
	}
};

/**
 * Search for an ad with a given id on the VAST Definitions object created by the parseVAST callback.
 */
telefunken.getVASTAdById = function(vastDefs, id) {
	var ad;
	if(vastDefs) {
		if(vastDefs.ads) {
			for(var vastAd in vastDefs.ads) {
				if(vastAd.id === id) {
					ad = vastAd;
					break;
				}
			}	
		}
	}
	return ad;
};

/**
 * Receives a Wrapper or InLine element, finds Impression tags inside and 
 * pushes them into the VAST Definitions Ad object
 */
telefunken.findAndParseImpressions = function(vastDefsAd, domElement) {
	$impressions = $(domElement).find('Impression');
	if($impressions.length > 0) {
		$impressions.each(function(index) {
			//$url = $(this).text();
			$url = telefunken.giveUp(this);
			if($url !== undefined) {
				$id = $(this).attr("id");
				vastDefsAd.impressions.push({ id : $id, url : $url });
			}
		});
	}
};

/**
 * Receives a Wrapper or InLine element, finds Title tags inside and 
 * pushes them into the VAST Definitions Ad object
 */
telefunken.findAndParseTitle = function(vastDefsAd, domElement) {
	$title = $(domElement).find('AdTitle');
	if($title !== undefined) {
		vastDefsAd.title = telefunken.giveUp($title);//$title.text();
	}
};

/**
 * Receives a Wrapper or InLine element, finds Description tags inside and 
 * pushes them into the VAST Definitions Ad object
 */
telefunken.findAndParseDescription = function(vastDefsAd, domElement) {
	$description = $(domElement).find('Description');
	if($description !== undefined) {
		vastDefsAd.description = telefunken.giveUp($description);//$description.text();
	}
};

/**
 * Receives a Wrapper or InLine element, finds Error tags inside and 
 * pushes them into the VAST Definitions Ad object
 */
telefunken.findAndParseErrors = function(vastDefsAd, domElement) {
	$errors = $(domElement).find('Error');
	if($errors !== undefined) {
		$errors.each(function(index) {
			$url = telefunken.giveUp(this);
			//$url = $(this).text();
			if($url !== undefined) {
				vastDefsAd.errors.push($url);
			}
		});
	}
};

/**
 * Receives a Wrapper element, finds the remote Ad Server URL and retrieves it
 */
telefunken.findReferencedAdServerVASTUrl = function(domElement) {
	var vastUrl;
	$vastAdTagURI = $(domElement).find('VASTAdTagURI');
	if($vastAdTagURI !== undefined) {
		vastUrl = telefunken.giveUp($vastAdTagURI);//$vastAdTagURI.text();
	}
	return vastUrl;
};

/**
 * Search for a creative with a given id on the VAST Ad Definitions object 
 * created by the findAndParseCreatives callback.
 */
telefunken.getCreativeById = function(vastDefsAd, creativeId) {
	var findCreative;
	if(vastDefsAd) {
		if(vastDefsAd.creatives){
			for(var creative in vastDefsAd.creatives) {
				if(creative.id === creativeId) {
					findCreative = creative;
					break;
				}
			}
		}
	}
	return findCreative;
};

/**
 * Receives an array of tracking events and returns the event with eventName
 */
telefunken.getTrackingEventByName = function(trackingEvents, eventName) {
	var findTrackingEvent;
	if(trackingEvents){
		for(var trackingEvent in trackingEvents) {
			if(trackingEvent.eventName === eventName) {
				findTrackingEvent = trackingEvent;
				break;
			}
		}
	}
	return findTrackingEvent;
};

/**
 * Search for trackingEvents within a Linear Ad element
 * created by the findAndParseCreatives callback.
 */
telefunken.findAndParseTrackingEvents = function(vastDefsCreative, domElement) {
	$trackingEvents = $(domElement).find("TrackingEvents");
	if($trackingEvents !== undefined && $trackingEvents.children() !== undefined) {
		$trackingEvents.children().each(function(index) {
			//$url = $(this).text();
			$url = telefunken.giveUp(this);
			$eventName = $(this).attr("event");
			if($url !== undefined && $eventName !== undefined) {
				var trackingEvent = telefunken.getTrackingEventByName(vastDefsCreative.linear.trackingEvents, $eventName);
				if(trackingEvent === undefined) {
					trackingEvent = {
						eventName : $eventName,
						urls : []
					}
					vastDefsCreative.linear.trackingEvents.push(trackingEvent);
				}
				trackingEvent.urls.push($url);
			}
		});
	}
};

/**
 * Search for mediaFiles within a Linear Ad element created by the findAndParseCreatives callback.
 */
telefunken.findAndParseMediaFiles = function(vastDefsCreative, domElement) {
	$mediaFiles = $(domElement).find("MediaFiles");
	if($mediaFiles !== undefined && $mediaFiles.children() !== undefined) {
		$mediaFiles.children().each(function(index){
			//Sacar um give up
			
				$url = telefunken.giveUp(this);
				//$url = $(this).text();
				if($url !== undefined) {
					vastDefsCreative.linear.mediaFiles.push({
						url : $url,
						mimeType : $(this).attr("type"),
						delivery : $(this).attr("delivery"),
						bitrate : $(this).attr("bitrate"),
						width : $(this).attr("width"),
						height : $(this).attr("height")
					});
				}
			
		});
	}
};

/**
 * Search for mediaFiles within a Linear Ad element created by the findAndParseCreatives callback.
 */
telefunken.findAndParseVideoClicks = function(vastDefsCreative, domElement) {
	$videoClicks = $(domElement).find("VideoClicks");
	if($videoClicks !== undefined && $videoClicks.children() !== undefined) {
		$clickThrough = $videoClicks.find("ClickThrough");
		if($clickThrough !== undefined) {
			vastDefsCreative.linear.videoClicks.clickThrough = $url = telefunken.giveUp($clickThrough);//$clickThrough.text();
		}
		$clickTracking = $videoClicks.find("ClickTracking");
		if($clickTracking !== undefined) {
			$clickTracking.each(function(index) {
				vastDefsCreative.linear.videoClicks.clickTracking.push(telefunken.giveUp(this)/*$(this).text()*/);
			});
		}
	}
};

/**
 * Receives a Wrapper or InLine element, finds Creative tags inside and 
 * pushes them into the VAST Definitions Ad object
 */
telefunken.findAndParseCreatives = function(vastDefsAd, domElement) {
	$creatives = $(domElement).find("Creatives");
	if($creatives !== undefined && $creatives.children() !== undefined) {
		$creatives.children().each(function(index) {
			$creativeId = $(this).attr("id");
			if($creativeId !== undefined) {
				var creative = telefunken.getCreativeById(vastDefsAd, $creativeId);
				if(creative === undefined) {
					creative = {
						linear : {
							id: $creativeId,
							trackingEvents : [],
							mediaFiles : [],
							videoClicks : {
								clickTracking : []
							}	
						}
					};
					vastDefsAd.creatives.push(creative);
				}
				//Look for linear ad (there can only be one)
				$linear = $(this).find("Linear");
				if($linear !== undefined) {
					$linearDuration = $linear.find("Duration");
					if($linearDuration !== undefined) {
						creative.linear.duration = telefunken.giveUp($linearDuration);//$linearDuration.text();
					}
					telefunken.findAndParseTrackingEvents(creative, $linear);
					telefunken.findAndParseVideoClicks(creative, $linear);
					telefunken.findAndParseMediaFiles(creative, $linear);
				}
				//Non Linear ad support disabled
			}
		});
	}
};
			
							
/**
 * Parse a vast Ad response
 */
telefunken.parseVAST = function(playerWrapper, playerParams) {
	return function(data, textStatus) {
		if(data !== undefined) {
			var shouldSchedule = false;
			//Create empty VAST definitions if they don't exist
			playerParams.VAST = playerParams.VAST || { adPod: [],  ads: [], adBuffet: [] }; 
			
			//Parse XML response
			$vastXml = $(data);
			
			if($vastXml.length > 0) {
				//Find ad elements in the response
				$ads = $vastXml.find('Ad');
				if($ads.length > 0) {
					$ads.each(function(index) {
						
						adId = $(this).attr('id');
						if(adId) {
						
							//Initialize a new ad or get an existing one
							var ad = telefunken.getVASTAdById(playerParams.VAST, adId);
							if(!ad) {
								ad = { 
									id: adId,
									creatives: [], 
									impressions: [],
									errors: [] 
								};
								
								var sequence = $(this).attr('sequence');
								if (sequence) {
									playerParams.VAST.adPod[parseInt(sequence)] = ad;
								} else {
									playerParams.VAST.adBuffet.push(ad);
								}
								playerParams.VAST.ads.push(ad);
							}
							
							//Look for wrapper elements (Wrapper is a reference to another adserver)
							$wrapper = $(this).find('Wrapper');
							if($wrapper.length > 0) {
								telefunken.findAndParseImpressions(ad, $wrapper);
								telefunken.findAndParseTitle(ad, $wrapper);
								telefunken.findAndParseDescription(ad, $wrapper);
								telefunken.findAndParseErrors(ad, $wrapper);
								telefunken.findAndParseCreatives(ad, $wrapper);
								//Find referenced Ad Server URL
								var referencedAdServerVASTUrl = telefunken.findReferencedAdServerVASTUrl($wrapper);
								if(referencedAdServerVASTUrl) {
									$.get(referencedAdServerVASTUrl, telefunken.parseVAST(playerWrapper, playerParams));
								}
							} else {
								//Look for inline elements
								$inline = $(this).find('InLine');
								if($inline.length > 0) {
									shouldSchedule = true;	
									telefunken.findAndParseImpressions(ad, $inline);
									telefunken.findAndParseTitle(ad, $inline);
									telefunken.findAndParseDescription(ad, $inline);
									telefunken.findAndParseErrors(ad, $inline);
									telefunken.findAndParseCreatives(ad, $inline);
								}
							}
						}
						
					});
				}
			}
			
			if(playerParams.autoplay && shouldSchedule) {
				//Do my magic in here
				telefunken.log(playerParams, playerParams.VAST);
				telefunken.scheduleAds(playerWrapper, playerParams);
				playerWrapper.play();
			}
				
		} else {
			//Play the video independently from what has happened	
			if(playerParams.autoplay) {
				playerWrapper.play();
			}
		}
	};
};

/**
 * Bind all the VAST Event Handlers
 */
telefunken.scheduleAds = function(playerWrapper, playerParams) {
	// Support for Ads is kinda Hardcoded in here =/ If there is and adPod, play it. 
	// Otherwise, search for the first video (Linear Ad) in the AdBuffet
	if(playerParams.VAST.adPod.length > 0) {
		var filteredArray = playerParams.VAST.adPod.filter(function(){return true});
		if(filteredArray && filteredArray.length > 0) {
			var adToPlay = filteredArray[0];
			var remainingAds = filteredArray.slice(1);
			telefunken.scheduleAd(playerWrapper, playerParams, adToPlay, remainingAds);
		}
	} else if(playerParams.VAST.adBuffet.length > 0) {
		//this can be a real good fuckup.
		telefunken.scheduleAd(playerWrapper, playerParams, playerParams.VAST.adBuffet[0]);
	}
};

/**
 * Schedules an ad for playing and handles the queue, if there is more than one ad
 */
telefunken.scheduleAd = function(playerWrapper, playerParams, adToPlay, remainingAds) {
	if(adToPlay.creatives !== undefined) {
		//Hardcoded support for 1 creative.
		$(adToPlay.creatives).each(function(index, creative) {
			if(creative !== undefined) {
				if(creative.linear !== undefined) {
					//set the video
					if(creative.linear.mediaFiles !== undefined) {
						var mediaFile = creative.linear.mediaFiles[0];
						if(mediaFile !== undefined) {
							playerWrapper.setVideo(mediaFile.url);
							
							//bind tracking events
							if(creative.linear.trackingEvents !== undefined) {
								$(creative.linear.trackingEvents).each(function(index,element){
									telefunken.bindVASTEventHandler(playerWrapper, playerParams, element);
								});
							}
							
							
							var bindMouseOverClickThrough = function(playerWrapper, playerParams, creative) {
								return function() {
									if(creative.linear.videoClicks !== undefined) {
										var clickThrough = creative.linear.videoClicks.clickThrough;
										var clickTracking = creative.linear.videoClicks.clickTracking;
										telefunken.bindVASTEventHandler(playerWrapper, playerParams, { 
											eventName : "mouseover", 
											clickThrough: clickThrough, 
											clickTracking: clickTracking 
										});
										playerWrapper.unbind("play", arguments.callee);
									}
								};
							};
						
							//bind tracking impressions
							if(adToPlay.impressions !== undefined) {
								$(adToPlay.impressions).each(function(index, element) {
									playerWrapper.bind("play"
										, telefunken.VASTHandlers.trackVASTImpression(playerWrapper, playerParams, "play", element, true) );	
								});
							}
							
							playerWrapper.bind("play", bindMouseOverClickThrough(playerWrapper, playerParams, creative));
		
							//when preroll ends play the next video or ad
							playerWrapper.bind("ended", telefunken.VASTHandlers.prerollEnded(playerWrapper, playerParams, "ended", "loadedmetadata", remainingAds));
							
						}
					}
				}
			}
		});
	}
};
	
/**
 * Binds a callback to a player event, in the context of a VAST tracking Event, while
 * keeping track of the association, to unbind in the end of the preroll
 */
telefunken.bindVASTEventHandler = function(playerWrapper, playerParams, vastEvent) {
	var callback, playerEvent, unbind;
	
	switch(vastEvent.eventName) {
		case "creativeView" :
		case "start" :
			playerEvent = "play";
			unbind = true;
			callback = telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent, unbind);		
			break;
		case "complete" :
			playerEvent = "ended";
			unbind = true;
			callback = telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent, unbind);
			break;
		case "firstQuartile" :
			playerEvent = "timeupdate";
			unbind = true;
			callback = telefunken.VASTHandlers.trackVASTEventTimed(playerWrapper, playerParams, playerEvent, vastEvent, 25, unbind);
			break; 
		case "midpoint" :
			playerEvent = "timeupdate";
			unbind = true;
			callback = telefunken.VASTHandlers.trackVASTEventTimed(playerWrapper, playerParams, playerEvent, vastEvent, 50, unbind);
			break; 
		case "thirdQuartile" : 
			playerEvent = "timeupdate";
			unbind = true;
			callback = telefunken.VASTHandlers.trackVASTEventTimed(playerWrapper, playerParams, playerEvent, vastEvent, 75, unbind);
			break;
		case "pause" : 
			playerEvent = "pause";
			unbind = false;
			callback = telefunken.VASTHandlers.trackVASTPauseEvent(playerWrapper, playerParams, playerEvent, vastEvent);
			break;
		case "mute" : 
			playerEvent = "volumechange";
			unbind = false;
			callback = telefunken.VASTHandlers.trackVASTMuteEvent(playerWrapper, playerParams, playerEvent, vastEvent);
			break;
		case "unmute" : 
			playerEvent = "volumechange";
			unbind = false;
			callback = telefunken.VASTHandlers.trackVASTUnmuteEvent(playerWrapper, playerParams, playerEvent, vastEvent);
			break;
		case "fullscreen" : 
		case "expand" : 
			playerEvent = "fullscreenchange";
			unbind = false;
			callback = telefunken.VASTHandlers.trackVASTEnterFullscreenEvent(playerWrapper, playerParams, playerEvent, vastEvent);
			break;
		case "exitFullscreen" : 
		case "collapse" : 
			playerEvent = "fullscreenchange";
			unbind = false;
			callback = telefunken.VASTHandlers.trackVASTExitFullscreenEvent(playerWrapper, playerParams, playerEvent, vastEvent);
			break;
		case "mouseover" : 
			playerEvent = "mouseover";
			unbind = false;
			callback = telefunken.VASTHandlers.trackVASTMouseOver(playerWrapper, playerParams, vastEvent);
			break;
		default : 
			break;	
	}
	//bind and keep track of the bound event for future unbinds
	if(playerEvent && callback) {
		playerWrapper.bind(playerEvent, callback);
		if(!unbind) {
			playerParams.registeredEvents = playerParams.registeredEvents || [];
			playerParams.registeredEvents.push( { playerEvent : playerEvent, callback : callback } );	
		}
	}
};

/**
 * Clear all the vast event handlers
 */
telefunken.unbindVASTEventHandlers = function(playerWrapper, playerParams) {
	if(playerParams && playerParams.registeredEvents) {
		$(playerParams.registeredEvents).each(function(index, element){
			playerWrapper.unbind(element.playerEvent, element.callback);
		});
	}
};

/**
 * Handler functions for VAST events
 */
telefunken.VASTHandlers = {
	
	trackVASTEventTimed : function(playerWrapper, playerParams, playerTickEvent, vastEvent, timePercent, unbind) {
		return function() {
			var videoDuration = playerWrapper.duration();
			var currentTime = playerWrapper.currentTime();
			var currentPercent = (currentTime * 100) / videoDuration;
			if(currentPercent >= timePercent) {
				telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerTickEvent, vastEvent)();
				if(unbind) {
					playerWrapper.unbind(playerTickEvent, arguments.callee);	
				}
			}
		};
	},
	
	trackVASTEvent : function(playerWrapper, playerParams, playerEvent, vastEvent, unbind) {
		return function() {
			telefunken.log(playerParams, "will track event " + vastEvent.eventName);
			$.get(vastEvent.url, function(data, textStatus) {
				telefunken.log(playerParams, "successfully tracked event " + vastEvent.eventName);
			});
			if(unbind) {
				playerWrapper.unbind(playerEvent, arguments.callee);
			}
		};
	},
	
	trackVASTPauseEvent : function(playerWrapper, playerParams, playerEvent, vastEvent) {
		return function() {
			var videoDuration = Math.round(playerWrapper.duration());
			var currentTime = Math.round(playerWrapper.currentTime());
			if(currentTime < videoDuration) {
				telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent)();
			}
		};
	},
	
	trackVASTMuteEvent : function(playerWrapper, playerParams, playerEvent, vastEvent) {
		return function() {
			var videoVolume = playerWrapper.volume();
			if(videoVolume === 0 && ! playerParams.VAST.isMuted) {
				telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent)();	
				playerParams.VAST.isMuted = true;
			}
		};
	},
	
	trackVASTUnmuteEvent : function(playerWrapper, playerParams, playerEvent, vastEvent) {
		return function() {
			var videoVolume = playerWrapper.volume();
			if(videoVolume !== 0 && playerParams.VAST.isMuted) {
				telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent)();	
				playerParams.VAST.isMuted = false;
			}
		};
	},
	
	trackVASTEnterFullscreenEvent : function(playerWrapper, playerParams, playerEvent, vastEvent) {
		return function() {
			var isFullScreen = playerWrapper.isFullScreen();
			if(isFullScreen) {
				telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent)();	
			}
		};
	},
	
	trackVASTExitFullscreenEvent : function(playerWrapper, playerParams, playerEvent, vastEvent) {
		return function() {
			var isFullScreen = playerWrapper.isFullScreen();
			if(!isFullScreen) {
				telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, playerEvent, vastEvent)();	
			}
		};
	},
	
	trackVASTImpression : function(playerWrapper, playerParams, playerEventName, impression, unbind) {
		return function() {
			telefunken.log(playerParams, "will track impression " + impression.id);
			$.get(impression.url, function(data, textStatus) {
				telefunken.log(playerParams, "successfully tracked impression " + impression.id);
			});
			if(unbind) {
				playerWrapper.unbind(playerEventName, arguments.callee);
			}
		};
	},
	
	prerollEnded : function(playerWrapper, playerParams, playerEndEventName, playerLoadedMetadataEventName, remainingAds) {
		return function() {
			
			telefunken.log(playerParams, "preroll ended. will start the next Video");
			
			//Clear all the event handlers
			telefunken.unbindVASTEventHandlers(playerWrapper, playerParams);
			playerWrapper.pause();
			
			if(remainingAds && remainingAds.length > 0) {
				var adToPlay = remainingAds[0];
				var remainingRemainingAds = remainingAds.slice(1);
				telefunken.scheduleAd(playerWrapper, playerParams, adToPlay, remainingRemainingAds);
			} else {
				//not sending mime type could this be a problem ?
				playerWrapper.setVideo(playerParams.videoUrl);
			}
			
			playerWrapper.bind(playerLoadedMetadataEventName, function() {
				telefunken.log(playerParams, "video is ready. will start playing");
				playerWrapper.play();
				playerWrapper.unbind(playerLoadedMetadataEventName, arguments.callee);
			});
			playerWrapper.load();
			playerWrapper.unbind(playerEndEventName, arguments.callee);
		};
	},
	
	trackVASTMouseOver : function(playerWrapper,playerParams,vastEvent) {
		return function() {
			var sneiders = '<div class="clickthrough"><span>Saiba mais</span></div>';
			$sneiders = $(sneiders);
			
			var removeClickThrough = function(videoElement) {
				return function() {
					$findSneiders = $(videoElement).find('.clickthrough');
					if($findSneiders && $findSneiders.length > 0){
						$findSneiders.remove();
					}
				};
			};
			
			var clickThrough = function(playerWrapper, playerParams, vastClickEvent) {
				return function() {
					window.open(vastClickEvent.clickThrough,"_blank");
					telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, null, vastEvent)();
					for (var i = 0; i < vastClickEvent.clickTracking.length; i++) {
						telefunken.VASTHandlers.trackVASTEvent(playerWrapper, playerParams, null, { 
							url : vastClickEvent.clickTracking[i]
						})();
					}
				};
			};
			var element = document.getElementById(playerWrapper.getId());
			$sneiders = $sneiders.mouseout(removeClickThrough(element));
			$sneiders = $sneiders.click(clickThrough(playerWrapper, playerParams, vastEvent));
			
			$(element).prepend($sneiders);
		};
	}
	
};

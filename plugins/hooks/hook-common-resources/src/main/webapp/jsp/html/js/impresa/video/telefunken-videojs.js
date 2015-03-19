/**
 * Wrapper declaration
 */
var TelefunkenVideoJS = function() {
	this.settings = {
		/**
		 * This object defines the available events in Telefunken, and maps them to the corresponding 
		 * events in VideoJS. (They're equal) In the future, telefunken should have its own eventing system
		 */
		eventMapping : {
			loadstart : "loadstart",
			loadedmetadata : "loadedmetadata",
			loadeddata : "loadeddata",
			loadedalldata : "loadedalldata",
			play : "play",
			pause : "pause",
			timeupdate : "timeupdate",
			ended : "ended",
			durationchange : "durationchange",
			progress : "progress",
			resize : "resize",
			volumechange : "volumechange",
			error : "error",
			fullscreenchange : "fullscreenchange"
		}
	};
};

TelefunkenVideoJS.prototype.init = function(element, playerParams, readyCallback) {
	//Store initialization parameters
	this.playerParams = playerParams;
	
	//Create video tag required by videojs player only
	this.videoElementId = element.id + "_video";
	var videoTag = document.createElement("video");
	videoTag.setAttribute("id",this.videoElementId);
	
	//Set cssClass (for now, vjs-default-skin is hardcoded)
	videoTag.setAttribute("class", "video-js vjs-default-skin");
	
	//Set base video dimensions
	if( playerParams.width && playerParams.height ) {
		videoTag.setAttribute("width", playerParams.width);
		videoTag.setAttribute("height", playerParams.height);
	}
	
	element.appendChild(videoTag);
	
	//configure data-setup variable
	var dataSetup = {};
	//The order of technologies
	if(playerParams.techOrder) {
		dataSetup["techOrder"] = playerParams.techOrder;
	}
	//autoplay
	if(playerParams.autoplay && !playerParams.vastUrl) {
		dataSetup["autoplay"] = playerParams.autoplay;
	}
	//Send video metadata to the browser
	if(playerParams.preload) {
		dataSetup["preload"] = initParams.preload;
	}
	//Enable or disable video controls
	if(playerParams.controls) {
		dataSetup["controls"] = playerParams.controls;
	}
	
	this.vjsPlayer = videojs(this.videoElementId, dataSetup, function(){
  		this.src( playerParams.videoUrl );
        readyCallback();
  	});

    videojs.options.flash.swf = playerParams.swfPath;
};

TelefunkenVideoJS.prototype.getParameters = function() {
	return this.playerParams;
};

TelefunkenVideoJS.prototype.setVideo = function(videoUrl) {
	this.vjsPlayer.src( videoUrl );
};

TelefunkenVideoJS.prototype.pause = function() {
	this.vjsPlayer.pause();
};

TelefunkenVideoJS.prototype.play = function() {
    this.vjsPlayer.play();
};

TelefunkenVideoJS.prototype.load = function() {
	this.vjsPlayer.load();
};

TelefunkenVideoJS.prototype.bind = function(eventName, callback) {
	var evtMapping = this.settings.eventMapping[eventName];
	if(evtMapping) {
		this.vjsPlayer.on(evtMapping, callback);
	} else {
        $('#' + this.videoElementId).on(eventName, callback);
    }
};

TelefunkenVideoJS.prototype.unbind = function(eventName, callback) {
	var evtMapping = this.settings.eventMapping[eventName];
	if(evtMapping) {
		this.vjsPlayer.off(evtMapping, callback);	
	} else {
        $('#' + this.videoElementId).off(eventName, callback);
    }
};

TelefunkenVideoJS.prototype.currentTime = function() {
	return this.vjsPlayer.currentTime();
};

TelefunkenVideoJS.prototype.duration = function() {
	return this.vjsPlayer.duration();
};

TelefunkenVideoJS.prototype.volume = function() {
	return this.vjsPlayer.volume();
};

TelefunkenVideoJS.prototype.isFullScreen = function() {
	return this.vjsPlayer.width() === $(window).width();
};

TelefunkenVideoJS.prototype.getId = function() {
	return this.vjsPlayer.id();
};

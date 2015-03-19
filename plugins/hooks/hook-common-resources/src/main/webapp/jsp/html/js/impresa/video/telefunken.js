/**
 * @param {Object} id 				The DOM element where to put the video
 * @param {Object} playerWrapper	An implementation of the wrapper api, using the chosen backend (VideoJS, JPlayer, etc...)
 * @param {Object} params			The player initialization parameters
 */
var telefunken = function(id, playerWrapper, initParams) {
	
	//validate jQuery dependency (Avoid loosing time with dumb errors)
	if($ === undefined) {
		telefunken.error("i need jquery to work");
	}
	
	//id is the only required parameter in this function, make sure it is right
	if (typeof id !== "string") {
		telefunken.error("elemend id must be a string");
	}
	
	//Initialize the players array if it is null;
	telefunken.players = telefunken.players || [];
	
	var player;
	
	//check if player was already created
	if(telefunken.players[id]) {
		player = telefunken.players[id];
	} else {
		if (!playerWrapper) {
			telefunken.error("you should provide a playerWrapper");
		};
		telefunken.initPlayer(id, playerWrapper, initParams);
		player = telefunken.players[id] = playerWrapper;		
	}
	
	return player;
};

/**
 * Video Player default parameters
 */
telefunken.playerDefaultParams = {
	debug : false,
	autoplay : false,
	controls : true,
	width : "695", //ratio 16:9
	height : "349" //ratio 16:9
};

/**
 * Instantiates a new player
 * @param {Object} id 			The DOM element id
 * @param {Object} params		The player initialization parameters
 */
telefunken.initPlayer = function(id, playerWrapper, initParams) {
	
	//Search for the DOM element
	var element = document.getElementById(id);
	if(element === undefined) {
		telefunken.error("could not find element with id: " + id);
	}
	
	//Create the player's params object, from the default and the init params
	var playerParams = telefunken.mergeOverrideCopy(telefunken.playerDefaultParams, initParams);

	telefunken.log(playerParams, 'created player with id: ' + id);

    var readyCallback = function(playerWrapper, playerParams) {
        return function() {
            //If a vast url is provided, setup the process
            if(playerParams.vastUrl) {
                telefunken.getVAST(playerWrapper, playerParams);
            }
        }
    }

	//Initialize the player using the standard parameters (even if there is a vastUrl)
	playerWrapper.init(element, playerParams, readyCallback(playerWrapper, playerParams));

    telefunken.log(playerParams, 'initialized player with id' + id);
};

/**
 * Clones an array or a javascript object
 * @param {Object} obj		The object to clone
 */
telefunken.deepCopy = function (obj) {
	var out, i;
	if(obj && typeof obj === 'object') {
		out = (Object.prototype.toString.call(obj) === '[object Array]') ? [] : {}
        for ( i in obj ) {
        	//Recursive deep copy to clone child objects
            out[i] = telefunken.deepCopy( obj[i] );
        }
        return out;
    }
    return obj;
};

/**
 * Clones object A and merges objectB's properties into it overriding objectA's properties 
 * with the ones in objectB that have the same id
 * @param {Object} objA		The object to be overriden
 * @param {Object} objB		The object to override
 */
telefunken.mergeOverrideCopy = function(objA, objB) {
	var mergeCopy, i;
	if(objA && objB && typeof objA === typeof objB) {
		mergeCopy = telefunken.deepCopy(objA);
		for(i in objB) {
			mergeCopy[i] = objB[i];
		}
	}
	return mergeCopy;
};

/**
 * Logs to console if debugging is active and if console exists
 */
telefunken.log = function(initParams, msg) {
	if(initParams && initParams.debug) {
		if(console && typeof console.log == "function") {
			console.log(msg);
		}
	}
};

/**
 * Throws a javascript exception with an error
 * @param {Object} msg		The error message
 */
telefunken.error = function(msg) {
	throw "Exception: " + msg + "!";
};


/* Array filter for ie 8 */
if (!Array.prototype.filter)
{
  Array.prototype.filter = function(fun /*, thisp */)
  {
    "use strict";

    if (this === void 0 || this === null)
      throw new TypeError();

    var t = Object(this);
    var len = t.length >>> 0;
    if (typeof fun !== "function")
      throw new TypeError();

    var res = [];
    var thisp = arguments[1];
    for (var i = 0; i < len; i++)
    {
      if (i in t)
      {
        var val = t[i]; // in case fun mutates this
        if (fun.call(thisp, val, i, t))
          res.push(val);
      }
    }

    return res;
  };
}

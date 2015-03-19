/*
	--------------------------------
	Infinite Scroll Behavior
	AutoToManual / Facebook-style
	--------------------------------
	*/

$.extend($.infinitescroll.prototype, {
	_setup_facebook: function infscr_setup_facebook() {
		var instance = this;
		var opts = this.options;
		jQuery(opts.nextSelector).hide();
		this._binding('bind');
		this._numScrolls = 0; // Register a scroll counter
		this.options.loading.finished = function () {
			var $box = jQuery(this);
			opts.loading.msg.fadeOut('normal');
			instance._numScrolls++;
			if (instance._numScrolls > opts.maxScrollsBeforeManual - 1) {
				jQuery(window).unbind(".infscr");
				jQuery(opts.nextSelector).unbind().show().bind("click", function (e) {
					e.preventDefault();
					$box.infinitescroll('retrieve');
				});
				// remove the paginator when we are done.
				jQuery(document).ajaxError(function (e, xhr, opt) {
					if (xhr.status == 404)
						jQuery(opts.nextSelector).remove();
				});
			}
		};
		return false;
	}
});
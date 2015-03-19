/* Impresa Site jQuery */

$( document ).ready(function() {

/* *** group by years *** */
	
	$('.groupByYears .listContainer .publishedDate span').each(function( index ) {
		var str = $(this).text();
		var arr = str.split('_');
		$(this).text(arr[1]);
	});
	$('.groupByYears .listContainer').before('<h2 class="year-title">2013</h2>');
	
/* *** */
	
/* *** fixed menu *** */
	    
    var nav = $('#fixed-navigation');
           
    $(window).scroll(function () {    	
    	if ($(this).scrollTop() > 132) {
    		nav.addClass("f-nav");
    		nav.show();
        } else {
        	nav.hide();
            nav.removeClass("f-nav");            
        }
    });
    $("#fixed-navigation ul.fixed-menu-list li:first-child a").css("border-top","1px dotted #c3c3c3");
    $("#fixed-navigation ul.fixed-menu-list li.selected").prev().children("a").css("border-bottom","1px dotted #dd1e26");
    $("#fixed-navigation ul.fixed-menu-list li.selected:first-child a").css("border-top","1px dotted #dd1e26");
	    
/* *** */
		
/* *** Article page *** */

    /* lms
     * Hide Nested Portlet in section page.
     * All articles starts with a date, ex: 2013-07-05-artigo 
     * */
    $("html:not([id^='20']) .articleNestedPortlet").hide();

    $(".articleListPortlet .readMoreContainer a").text("ler mais");

    /* hide column 2 when has no related stories (articles) */
    if ($('.articleNestedPortlet .portlet-column-last .listContainer').children().length == 0) {
    	$('.articleNestedPortlet .portlet-column-last').hide();
    	$('.articleNestedPortlet .portlet-column-first .imageContainer').addClass('fullpageImage');
    }
/* *** */
    
});
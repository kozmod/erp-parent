$(document).ready(function($) {
	
	////////////////////////////////////
	// GENERAL
	////////////////////////////////////
	
	// Preloader
	
	$(window).load(function() {
		
		// After load
		
		setTimeout(function() {
			
			$('#preloader').fadeOut(300);
			
		}, 600);

	});	
	
	// Get scrollbar width
	
	function getScrollBarWidth () {
		var inner = document.createElement('p');
		inner.style.width = '100%';
		inner.style.height = '200px';

		var outer = document.createElement('div');
		outer.style.position = 'absolute';
		outer.style.top = '0px';
		outer.style.left = '0px';
		outer.style.visibility = 'hidden';
		outer.style.width = '200px';
		outer.style.height = '150px';
		outer.style.overflow = 'hidden';
		outer.appendChild (inner);

		document.body.appendChild (outer);
		var w1 = inner.offsetWidth;
		outer.style.overflow = 'scroll';
		var w2 = inner.offsetWidth;
		if (w1 == w2) w2 = outer.clientWidth;

		document.body.removeChild (outer);

		return (w1 - w2);
	};
	
	// Make item responsive
	
	function makeResponsive() {
		$('.size_is').each(function() {
			var viewportWidth = $(window).actual('width');
			
			if (viewportWidth < 600) { $(this).addClass('size_xxs'); } 
			else { $(this).removeClass('size_xxs'); }
			
			if (viewportWidth >= 600 && viewportWidth < 768) { $(this).addClass('size_xs'); } 
			else { $(this).removeClass('size_xs'); }
			
			if (viewportWidth >= 768 && viewportWidth < 992) { $(this).addClass('size_s'); } 
			else { $(this).removeClass('size_s'); }
			
			if (viewportWidth >= 992 && viewportWidth < 1200) { $(this).addClass('size_m'); } 
			else { $(this).removeClass('size_m'); }
			
			if (viewportWidth >= 1200) { $(this).addClass('size_l'); } 
			else { $(this).removeClass('size_l'); }
		});
	}
	
	$(window).on('load resize scroll', function() {
		makeResponsive();
	});
	
	// Content shadow
	
	function contentShadow() {
		var heightContent = $('#content').actual('height');
		var heightWrapper = $('#content .wrapper').outerHeight();
		
		// Show and hide top shadow
		
		$('#content').scroll(function() {
			if ($('#content').scrollTop() >= 20) {
				$('#content .shadow.top').show();
			} else {
				$('#content .shadow.top').hide();
			}
		});
		
		// Show and hide bottom shadow
		
		if (heightContent < heightWrapper) {
			$('#content .shadow.bot').show();
		} else {
			$('#content .shadow.bot').hide();
		}
		
		$('#content').scroll(function() {
			if ((heightWrapper - heightContent - $('#content').scrollTop()) == 0) {
				$('#content .shadow.bot').hide();
			} else {
				$('#content .shadow.bot').show();
			}
		});
	}
	
	$(window).on('load resize scroll', function() {
		contentShadow();
	});
	
	// Sidebar toggle
	
	$('.toggle_sidebar').click(function() {
		$('#sidebar').toggleClass('collapsed');
		$('#content').toggleClass('expanded');
	});
	
	// Dialog window	
	
    $('.toggle_dialog').click(function() {
		var dialogWindow = $(this).data('dialog');
			
		$('#container').toggleClass('blur_3');
		$('#content').toggleClass('locked');
		$('#dialog_wrapper').fadeToggle(300);
		
		setTimeout(function() {
			$('#dialog_wrapper .dialog_window.dialog_' + dialogWindow).toggleClass('hide_me');
		}, 150);
		
		setTimeout(function() {
			$('#dialog_wrapper .dialog_window.dialog_' + dialogWindow).toggleClass('open');
		}, 300);
    });
	
	// Toggle content block
	
    $('.toggle_cb').click(function() {
		$(this).toggleClass('active');
		
		var toggleContainer = $(this).data('toggle');
		$('.' + toggleContainer).slideToggle(300);
    });
	
	// Quick options

	$('#topbar .nav .quick_options #theme_zoom').on('change', function() {
		var zoom = $(this).val();
		$('html').css('zoom', zoom);
		$('#topbar .nav .quick_options .current_zoom').text(Math.round(zoom * 100) + '%');
		$('#topbar .nav .quick_options button.bttn').prop('disabled', false);
	});
	
	$('#topbar .nav .quick_options .colors a').each(function() {
		$(this).click(function() {
			$(this).addClass('active');
			$('#topbar .nav .quick_options .no_color').removeClass('active');
			$('#topbar .nav .quick_options .colors a').not(this).removeClass('active');
			
			var theme = $(this).data('color');
			$('#user_theme_color').attr('href', 'styles/theme-color-' + theme + '.css');
			$('#topbar .nav .quick_options button.bttn').prop('disabled', false);
		});
	});
	
	$('#topbar .nav .quick_options .no_color').click(function() {
		$(this).addClass('active');
		$('#topbar .nav .quick_options .colors a').removeClass('active');
		
		$('#user_theme_color').attr('href', 'styles/theme-color-0.css');
		$('#topbar .nav .quick_options button.bttn').prop('disabled', false);
	});
	
	$('#topbar .nav .quick_options .patterns a').each(function() {
		$(this).click(function() {
			$(this).addClass('active');
			$('#topbar .nav .quick_options .no_pattern').removeClass('active');
			$('#topbar .nav .quick_options .patterns a').not(this).removeClass('active');
			
			var theme = $(this).data('pattern');
			$('#user_theme_pattern').attr('href', 'styles/theme-pattern-' + theme + '.css');
			$('#topbar .nav .quick_options button.bttn').prop('disabled', false);
		});
	});
	
	$('#topbar .nav .quick_options .no_pattern').click(function() {
		$(this).addClass('active');
		$('#topbar .nav .quick_options .patterns a').removeClass('active');
		
		$('#user_theme_pattern').attr('href', 'styles/theme-pattern-0.css');
		$('#topbar .nav .quick_options button.bttn').prop('disabled', false);
	});
	
	// Sidebar menu
	
	$('#container #sidebar .user_menu').slinky({
		title:true,
		theme:'slinky_sidemenu',
	});
	
	// Tooltips general
	
	$('.ttp_bt').tooltipster({side:'bottom'});
	$('.ttp_tp').tooltipster({side:'top'});
	$('.ttp_lt').tooltipster({side:'left'});
	$('.ttp_rt').tooltipster({side:'right'});
	
	// Quick options popover
	
	//$('.quick_options_show').webuiPopover({
		//animation:'pop',
		//placement:'bottom-left',
		//type:'html',
		//trigger:'click',
		// content:$('#' + $(this).data('popover')),
		//content:$('.quick_options_popover'),
	//});
	
});
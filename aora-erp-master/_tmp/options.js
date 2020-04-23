function loadOptions () {
	
	////////////////////////////////////
	// GENERAL
	////////////////////////////////////
	
	// Preloader
	
	$(window).load(function() {
		
		// After load
		
		setTimeout(function() {
			
			$('#preloader').fadeOut(300);
			
		}, 900);

	});	
	
	// Log-in screen
	
	$('.height_equal').matchHeight();
	
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
	
	// Toggle accordion
	
	$('.accordion .toggle_acc').click(function(e) {
		e.preventDefault();
	  
		var $this = $(this);
	  
		if ($this.next().hasClass('show')) {
			$this.next().removeClass('show');
			$this.next().slideUp(350);
		} else {
			$this.parent().parent().find('.inner_acc').removeClass('show');
			$this.parent().parent().find('.inner_acc').slideUp(300);
			$this.next().toggleClass('show');
			$this.next().slideToggle(300);
		}
		
		$this.toggleClass('active');
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

	var height = '';
	
    w.parent.postMessage({
      sentinel: 'amp',
      type: 'embed-size-360',
      height: size,
      width: size
    }, '.container');
	
	// Select boxes
	
	$('select').niceSelect();
	
	// Field with filler
	
	$('label.fld.filler').each(function() {
		var filler = $(this).find('em');
		var input = $(this).find('.inp');
		
		if (input.val().length === 0) {
			filler.removeClass('focus');
		} else {
			filler.addClass('focus');
		}
		
		input.focus(function() {
			filler.addClass('focus');
		}).blur(function() {
			if (input.val().length === 0) {
				filler.removeClass('focus');
			}
		});
		
	});
	
	// Tooltips general
	
	$('.ttp_bt').tooltipster({side:'bottom'});
	$('.ttp_tp').tooltipster({side:'top'});
	$('.ttp_lt').tooltipster({side:'left'});
	$('.ttp_rt').tooltipster({side:'right'});
	
	// Equal heights
	
	$('.height_equal').matchHeight();
	
	// Dialog actions width
	
	function dialogActionsWidth() {
		$('.dialog_actions .inner .actions').each(function() {
			var itemsCount = $(this).find('li').length;
			
			if (itemsCount == 1) {
				$(this).find('li').width('100%');
			}
			
			if (itemsCount == 2 || itemsCount == 4) {
				$(this).find('li').addClass('w_1_2').width('50%');
			}
			
			if (itemsCount == 3 || itemsCount == 6) {
				$(this).find('li').addClass('w_1_3').width((100 / 3) + '%');
			}
			
			if (itemsCount == 5) {
				$(this).find('li:not(:last-child)').addClass('w_1_2').width('50%');
				$(this).find('li:last-child').width('100%');
			}
		});
	}
	
	$(window).on('load', function() {
		dialogActionsWidth();
	});
	
	// Auto popover
	
	$('.show_popover').each(function() {
		var popoverToggler = $(this);
		
		popoverToggler.webuiPopover({
			container:'#content .wrapper',
			animation:'fade',
			dismissible: true,
			type:'html',
			trigger:'click',
			placement:popoverToggler.data('popover-position'),
			content:$('#' + popoverToggler.data('popover')),
			onShow: function() {
				$('.webui-popover-content a').click(function() {
					popoverToggler.webuiPopover('hide');
				});
			}
		});
	});
	
	// Toggle content block
	
    $('.toggle_cb').click(function() {
		$(this).toggleClass('active');
		
		var toggleContainer = $(this).data('toggle');
		$('.' + toggleContainer).slideToggle(300);
    });
	
}
	
$(document).ready(function($) {
	
	// Sidebar toggle
	
	$('.toggle_sidebar').click(function() {
		$('#sidebar').toggleClass('collapsed');
		$('#content').toggleClass('expanded');
	});
	
	// Toggle dialog window
	
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
	
	// Sidebar menu
	
	$('#container #sidebar .user_menu').slinky({
		title:true,
		theme:'slinky_sidemenu',
	});
	
	// Log-off timer
	
	$('.test_timer').startTimer({
		onComplete: function(element) {
			window.location.replace('login.php');
		},
	});
	
	loadOptions();
	
	// Ajax load #content
	
	$('.load_content').each(function() {
		$(this).click(function() {
			var loadFrom = $(this).data('load-from');
			var loadTo = $(this).data('load-to');
			
			$.ajax({
				url:loadFrom,
				success: function(response) {
					$('#' + loadTo).html($(response));
					
					//loadOptions();
				}
			});
		});
	});
	
});
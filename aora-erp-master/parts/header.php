<?php
	// Test array
	
	$settings = array(
		'app_screen_name' => 'Dashboard',
		
		'user_theme_color' => '1',
		'user_theme_pattern' => '0',
		'user_theme_zoom' => '1.0',
	);
?>
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
	<meta name="viewport" content="initial-scale=1" />
	
    <title>%APPNAME%</title>
	
    <script type="text/javascript" src="scripts/jquery.js"></script>
    <script type="text/javascript" src="scripts/actual.js"></script>
    <script type="text/javascript" src="scripts/popover.js"></script>
    <script type="text/javascript" src="scripts/tooltipster.js"></script>
    <script type="text/javascript" src="scripts/niceselect.js"></script>
    <script type="text/javascript" src="scripts/sidemenu.js"></script>
    <script type="text/javascript" src="scripts/equals.js"></script>
    <script type="text/javascript" src="scripts/timer.js"></script>
    <script type="text/javascript" src="scripts/smoothscroll.js"></script>
    <script type="text/javascript" src="scripts/options.js"></script>
	<script type="text/javascript" src="scripts/jquery.sortable.js"></script>
	<script type="text/javascript" src="scripts/jquery.sortable.min.js"></script>	
	
    <link rel="stylesheet" href="styles/general.css" />
    <link rel="stylesheet" href="styles/theme-color-<?php echo $settings['user_theme_color']; ?>.css" id="user_theme_color" />
    <link rel="stylesheet" href="styles/theme-pattern-<?php echo $settings['user_theme_pattern']; ?>.css" id="user_theme_pattern" />
</head>
<body>

<div id="preloader" class="thm_grd full_size">
    <div class="spinner rds_full"></div>
</div>
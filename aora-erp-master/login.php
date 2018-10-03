<?php include ('parts/header.php'); ?>

<!-- Container start -->
<div id="container" class="fullscreen animate_300">
<!-- Container start -->

<div class="form_login">
	<div class="inner">
		<div class="logo">LOGO</div>
		
		<form class="form clr_b4" action="dashboard.php" method="post" autocomplete="off">
			<label class="fld">
				<div class="sign"><i class="fas fa-user"></i></div>
				<input type="text" name="login" class="clr_b4" placeholder="Логин" autocomplete="off" />
			</label>
			
			<label class="fld">
				<div class="sign"><i class="fas fa-key"></i></div>
				<input type="password" name="password" class="clr_b4" placeholder="Пароль" autocomplete="new-password" />
			</label>
			
			<div class="sep"></div>
			
			<label class="switcher left">
				<input type="checkbox" class="www" />
				
				<strong>Запомнить</strong>
			</label>
			
			<button type="submit" name="submit" class="bttn bttn_submit bttn_40 right rds_20"><i class="fas fa-sign-in-alt"></i> Войти</button>
			
			<div class="clear"></div>
		</form>
		
		<div class="meta">META</div>
	</div>
</div>

<!-- Background start -->
<div id="background" class="thm_grd full_size"></div>
<!-- Background end -->

<!-- Container end -->
</div>
<!-- Container end -->

</body> 
</html>
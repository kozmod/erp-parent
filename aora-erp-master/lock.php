<?php include ('parts/header.php'); ?>

<!-- Container start -->
<div id="container" class="fullscreen animate_300">
<!-- Container start -->

<div class="form_login">
	<div class="inner">
		<div class="logo">LOGO</div>
		
		<form class="form clr_b4" action="dashboard.php" method="post" autocomplete="off">
			<div class="message">
				<div class="sign"><i class="fas fa-lock-alt"></i></div>
				
				Доступ заблокирован.<br />Для продолжения работы введите ваш пароль.
			</div>
			
			<label class="fld">
				<div class="sign"><i class="fas fa-key"></i></div>
				<input type="password" name="password" class="clr_b4" placeholder="Пароль" autocomplete="new-password" />
			</label>
			
			<div class="sep"></div>
			
			<button type="submit" name="submit" class="bttn bttn_submit unlock bttn_40 rds_20"><i class="fas fa-lock-open-alt"></i> Разблокировать</button>
			
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
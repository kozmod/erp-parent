<div id="topbar" class="animate_150 clr_b2">
	<a href="javascript:;" class="toggle_sidebar button left"><i class="fas fa-bars"></i></a>
	
	<a href="dashboard.php" class="toggle_sidebar button size_is hide_xs hide_s hide_m hide_l left"><i class="fas fa-home"></i></a>
    
    <a href="dashboard.php" class="logo size_is hide_xxs left">LOGO HERE</a>
    
    <form class="search left size_is hide_xs hide_xxs">
		<label class="fld">
			<div class="sign"><i class="fas fa-search"></i></div>
			<input type="text" name="password" class="clr_b4" placeholder="Поиск по всем данным" autocomplete="off" />
		</label>
    </form>
    
	<ul class="nav right">
		<li class="size_is hide_l hide_m hide_s">
			<a href="javascript:;" class="button toggle_dialog" data-dialog="launchpad"><i class="fas fa-search"></i></a>
		</li>
		
		<li>
			<a href="javascript:;" class="button toggle_dialog" data-dialog="launchpad"><i class="fas fa-rocket"></i></a>
		</li>
		
		<li>
			<a href="javascript:;" class="button toggle_cb" data-toggle="quick_options"><i class="fas fa-paint-brush"></i></a>
			
			<form class="quick_options hide_me">
				<div class="inner">
					<strong>
						Масштаб
						<span class="current_zoom meta right">100%</span>
					</strong>
					
					<input type="range" min="0.8" max="1.4" step="0.1" value="1" class="inp_range" id="theme_zoom" />
					
					<hr />
					
					<strong>
						Фон 
						<!--<a href="javascript:;" class="no_color meta right"><i class="fas fa-check"></i> Нет</a>-->
					</strong>
				
					<div class="style colors clear_after">
						<a href="javascript:;" class="clr_1 left rds_5 active" data-color="1"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="clr_2 left rds_5" data-color="2"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="clr_3 left rds_5" data-color="3"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="clr_4 left rds_5" data-color="4"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="clr_5 left rds_5" data-color="5"><i class="fas fa-check"></i></a>
					</div>
					
					<hr />
					
					<strong>
						Паттерн
						<a href="javascript:;" class="no_pattern meta right active"><i class="fas fa-check"></i> Нет</a>
					</strong>
				
					<div class="style patterns clear_after">
						<a href="javascript:;" class="pttrn_1 left rds_5" data-pattern="1"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="pttrn_2 left rds_5" data-pattern="2"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="pttrn_3 left rds_5" data-pattern="3"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="pttrn_4 left rds_5" data-pattern="4"><i class="fas fa-check"></i></a>
						<a href="javascript:;" class="pttrn_5 left rds_5" data-pattern="5"><i class="fas fa-check"></i></a>
					</div>
				</div>
				
				<div class="actions clear_after">
					<a href="settings.php" class="bttn bttn_40 bttn_simple left">Настройки</a>
					<button type="submit" name="submit" class="bttn bttn_submit bttn_40 right" disabled="true">Сохранить</button>
				</div>
			</form>
		</li>
		
		<li>
			<a href="javascript:;" class="button toggle_dialog" data-dialog="logout"><i class="fas fa-sign-out"></i></a>
		</li>
	</ul>
    
    <div class="clear"></div>
</div>
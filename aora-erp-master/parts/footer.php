<!-- Dialog start -->

<script>	
	$(function() {
			$('#sortable1, #sortable2').sortable();
			
		});
	</script>
	

  
	<style>
	.otst{
	margin:5px;	
	}
	
	.text_hru{
	font-size:26px;
	line-height: 40px;
	
	
	}
	.sortable li {
	float:left;
	
	cursor: move;
	display:inline-block;	
    line-height: 80px;
    width: 80px;
    height: 80px;
    text-align: center;
    list-style: none;
    border: 1px solid #CCC;
    background: #F6F6F6 url(http://icons.iconarchive.com/icons/google/noto-emoji-animals-nature/128/22235-pig-face-icon.png) no-repeat center center;
	background-size: cover;
    color: #1C94C4;
    	
}
li.sortable-placeholder {
	background: rgba(0, 0, 0, 0.8);
	
	-moz-border-radius:50%;
	-webkit-border-radius:50%;
	border-radius:50%;
	
	-moz-filter: blur(3px); 
	-webkit-filter: blur(3px);
	filter: blur(3px);
	filter:progid:DXImageTransform.Microsoft.Blur(PixelRadius='3');
}
	</style>

	
<div id="dialog_wrapper" class="hide_me full_size">
	<div class="svinoblo">
		<div class="container">
	

				<?php
			//print_r($_GET['items']);
			
			if (isset($_GET['items'])) {
				$order = $_GET['items'];
			} else {
				$order = range(1, 15);
			}
		?>
		
	<form method="get">
		<div class="text_hru">Моё поросячье избранное 
		<button type="submit" name="submit" class="bttn bttn_submit bttn_40">ХРЮ</button>
		</div>
		
		<br /><br /><br /><br />
		
		<ul id="sortable2" class="sortable grid">
			
			
			<?php foreach ($order as $item) { ?>
			
				<div class="col_xxs_1 col_xs_1 col_s_2 col_m_1 col_l_1 otst"><li>Item <?php echo $item; ?> <input type="hidden" value="<?php echo $item; ?>" name="items[]"/></li></div>
				
			<?php } ?>
			
		</ul>
	</form>
		
		</div>
	</div>
</div>	
	<div class="dialog_window dialog_logout animate_300 hide_me">
		<div class="container">
			<div class="row">
				<div class="col_xxs_10 col_xs_8 col_s_6 col_m_6 col_l_4 push_l_4 push_m_3 push_s_3 push_xs_2 push_xxs_1">
					<div class="dialog_actions">
					<div class="inner">
						<div class="title">Подтверждение выхода</div>
						
						<div class="text">
							Вы действительно хотите выйти из системы? Все несохраненные изменения будут утеряны.
						</div>
						
						<ul class="actions clear_after">
							<li><a href="javascript:;" class="toggle_dialog dialog_close bttn bttn_50 bttn_simple" data-dialog="logout">Отмена</a></li>
							<li><a href="login.php" class="bttn bttn_50 bttn_simple" data-dialog="logout">Выйти</a></li>
						</ul>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    
	<div class="dialog_window dialog_adduser animate_300 hide_me">
		<div class="container">
			<div class="row">
				<div class="col_xxs_10 col_xs_8 col_s_6 col_m_6 col_l_4 push_l_4 push_m_3 push_s_3 push_xs_2 push_xxs_1">
					<div class="dialog_actions">
					<div class="inner">
						<div class="title">Новый пользователь</div>
						
						<div class="text">
							Вы действительно хотите выйти из системы? Все несохраненные изменения будут утеряны.
						</div>
						
						<ul class="actions clear_after">
							<li><a href="javascript:;" class="toggle_dialog dialog_close bttn bttn_50 bttn_simple" data-dialog="adduser">Отмена</a></li>
							<li><a href="javascript:;" class="toggle_dialog dialog_close bttn bttn_50 bttn_simple" data-dialog="adduser">Добавить</a></li>
						</ul>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    
    <div class="dialog_background full_size clr_b2"></div>
</div>
<!-- Dialog end -->

</body> 
</html>
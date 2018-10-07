<!-- Dialog start -->
<div id="dialog_wrapper" class="hide_me clr_b2 full_size">
	<div class="dialog_window dialog_launchpad animate_300 hide_me">
		<div class="container">
			<div class="row">
				<?php
					$icons = array(
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
						array('javascript:;', 'Title', 'pig.png'),
					);
					
					foreach ($icons as $icon) {
				?>
				
					<div class="col_xxs_4 col_xs_4 col_s_3 col_m_2 col_l_2 sort">
						<a href="<?php echo $icon[0]; ?>" class="size_is toggle_dialog" data-dialog="launchpad">
							<img src="_tmp/<?php echo $icon[2]; ?>" class="animate_150 decolorize" />
							<strong><?php echo $icon[1]; ?></strong>
						</a>
					</div>
				
				<?php } ?>
			</div>
		</div>
	</div>
    
	<div class="dialog_window dialog_test_simple animate_300 hide_me">
		<div class="container">
			<div class="row">
				<div class="col_xxs_12 col_xs_12 col_s_8 col_m_6 col_l_6 push_l_3 push_m_3 push_s_2">
					<div class="dialog_simple">
						
						<a href="javascript:;" class="toggle_dialog dialog_close" data-dialog="test_simple">&times;</a>
					</div>
				</div>
			</div>
		</div>
	</div>
    
    <div class="dialog_background full_size thm_grd"></div>
</div>
<!-- Dialog end -->

</body> 
</html>
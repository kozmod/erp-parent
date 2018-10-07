<?php include ('parts/header.php'); ?>

<!-- Container start -->
<div id="container" class="animate_300">
<!-- Container start -->

<?php include ('parts/topbar.php'); ?>

<div id="content" class="animate_300">
    <div class="wrapper">
		<div id="module">
			<div class="module_header size_is">
				<div class="container">
					<div class="row">
						<div class="col_xxs_12 col_xs_12 col_s_12 col_m_8 col_l_8">
							<div class="module_title">Управление пользователями</div>
						</div>
						
						<div class="col_xxs_12 col_xs_12 col_s_12 col_m_4 col_l_4">
							<div class="module_actions">
								<label class="switcher ttp_bt" title="Добавить в лаунчпад">
									<input type="checkbox" class="www" />
									
									<div class="signs">
										<i class="fas fa-rocket"></i>
									</div>
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container">
				<div class="row">
					<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12">
						<ul class="module_menu">
							<li class="sep_right">
								<a href="javascript:;" class="clr_b2 ttp_tp">
									<i class="fas fa-user-plus"></i> Добавить
								</a>
							</li>
							
							<li>
								<span class="clr_b2">С отмеченными:</span>
							</li>
							
							<li>
								<a href="javascript:;" class="clr_b2 ttp_tp" title="Заблокировать">
									<i class="fas fa-minus-circle"></i>
								</a>
							</li>
							
							<li>
								<a href="javascript:;" class="clr_b2 ttp_tp" title="Удалить">
									<i class="fas fa-trash-alt"></i>
								</a>
							</li>
							
							<li class="select clr_b2">
								<select>
									<option>Назначить роль</option>
									<option>Администратор</option>
									<option>Бухгалтер</option>
									<option>Менеджер</option>
									<option>Прораб</option>
								</select>
							</li>
							
							<li>
								<a href="javascript:;" class="clr_b2" title="Удалить">
									Сохранить
								</a>
							</li>
							
							<div class="clear"></div>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="module_content module_users">
				<div class="container">
					<div class="row">
						<?php
							$user_names = array(
								'Lindy Kin',
								'Todd Diehl',
								'Danuta Aldrete',
								'Terrell Lubinsky',
								'Mignon Dempsey',
								'Jamaal Huskins',
								'Consuela Tippett',
								'Clemencia Kurth',
							);
							
							$user_roles = array(
								'Администратор',
								'Бухгалтер',
								'Продажник',
								'Менеджер',
								'Кастомный',
							);
							
							foreach (range(1, 10) as $user) {
								
							$user_id = rand(1, 1000);
							$user_name = $user_names[array_rand($user_names)];
							$user_email = str_replace(' ', '', strtolower($user_name)).'@gmail.com';
							$user_phone = '+'.rand(1, 9).' ('.rand(111, 999).') '.rand(11111111, 99999999);
							$user_role = $user_roles[array_rand($user_roles)];
						?>
						
						<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12 clr_b2 user animate_150">
							<div class="row">
								<div class="col_xxs_10 col_xs_10 col_s_10 col_m_10 col_l_10">
									<div class="row">
										<div class="col_xxs_12 col_xs_12 col_s_12 col_m_7 col_l_7">
											<div class="details name size_is height_equal">
												<div>
													<strong><?php echo $user_name; ?></strong>
													<span>ID <?php echo $user_id; ?> &bull; <?php echo $user_role; ?></span>
													
													<label class="radiocheck">
														<input type="checkbox" class="www" />
													</label>
												</div>
											</div>
										</div>
								
										<div class="col_xxs_12 col_xs_12 col_s_12 col_m_5 col_l_5">
											<div class="details meta size_is height_equal">
												<div>
													<span><?php echo $user_email; ?></span>
													<span><?php echo $user_phone; ?></span>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col_xxs_2 col_xs_2 col_s_2 col_m_2 col_l_2">
									<div class="actions size_is height_equal">
										<a href="javascript:;" class="show_popover rds_full" data-popover="user_actions_<?php echo $user_id; ?>" data-popover-position="left">
											<i class="fas fa-ellipsis-v"></i>
										</a>
									</div>
									
									<div class="hide_me">
										<div id="user_actions_<?php echo $user_id; ?>">
											<ul class="popover_menu">
												<li><a href="javascript:;" class="toggle_cb" data-toggle="user_edit_<?php echo $user_id; ?>"><i class="fas fa-user-edit"></i> Изменить</a></li>
												<li><a href="javascript:;" class="toggle_cb" data-toggle="user_roles_<?php echo $user_id; ?>"><i class="fas fa-key"></i> Права доступа</a></li>
												<li><a href="javascript:;"><i class="fas fa-ban"></i> Заблокировать</a></li>
												<li><a href="javascript:;"><i class="fas fa-trash-alt"></i> Удалить</a></li>
											</ul>
										</div>
										
										<div class="actions_popover">1111</div>
									</div>
								</div>
							</div>
							
							<div class="user_edit_<?php echo $user_id; ?> edit hide_me">
								<div class="row">
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_7 col_l_7">
										Изменение пользователя <?php echo $user_name; ?>
									</div>
									
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_5 col_l_5">
										
									</div>
								</div>
							</div>
							
							<div class="user_roles_<?php echo $user_id; ?> edit hide_me">
								<div class="row">
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_7 col_l_7">
										<div class="edit_title"></div>
                                        
                                        <div class="item">
                                            
                                        </div>
									</div>
									
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_5 col_l_5">
										
									</div>
								</div>
							</div>
						</div>
						
						<?php } ?>
					</div>
				</div>
			</div>
		</div>
    </div>
    
    <div class="shadow top animate_150"></div>
    <div class="shadow bot animate_150"></div>
</div>

<?php include ('parts/sidebar.php'); ?>

<!-- Background start -->
<div id="background" class="thm_grd full_size">
	<div class="pattern full_size"></div>
</div>
<!-- Background end -->

<!-- Container end -->
</div>
<!-- Container end -->

<?php include ('parts/footer.php'); ?>
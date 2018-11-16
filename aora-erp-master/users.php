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
								<a href="javascript:;" class="toggle_dialog ttp_tp" data-dialog="adduser">
									<i class="fas fa-user-plus"></i> Добавить
								</a>
							</li>
						</ul>
                        
                        <ul class="module_menu">
                            <form method="get">
                                <li>
                                    <div class="name">С отмеченными:</div>
                                </li>
                                
                                <li>
                                    <select name="someselect">
                                        <option disabled>Выберите действие</option>
                                        <option data-display="&lt;i class=&quot;fas fa-minus-circle&quot;&gt;&lt;/i&gt; Заблокировать">Заблокировать</option>
                                        <option data-display="&lt;i class=&quot;fas fa-trash-alt&quot;&gt;&lt;/i&gt;
     Удалить">Удалить</option>
                                        <option disabled>Назначить роль</option>
                                        <option>Бухгалтер</option>
                                        <option>Менеджер</option>
                                        <option>Прораб</option>
                                        <option>Очень длинная роль</option>
                                    </select>
                                </li>
                                
                                <li>
                                    <button type="submit" class="button">Применить</button>
                                </li>
                                
                                <li>
                                    <a href="#testanchor" class="testscroll">SCROLL</a>
                                </li>
                            </form>
						</ul>
					</div>
				</div>
			</div>
			
            <a name="testanchor"></a>
            <div id="testanchor"></div>
            
			<div class="module_content module_users">
				<div class="container">
					<div class="row no_gutter">
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
							
							foreach (range(1, 20) as $user) {
								
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
												<li><a href="#user_edit_<?php echo $user_id; ?>" class="toggle_cb" data-toggle="user_edit_<?php echo $user_id; ?>"><i class="fas fa-user-edit"></i> Изменить</a></li>
												<li><a href="#user_roles_<?php echo $user_id; ?>" class="toggle_cb" data-toggle="user_roles_<?php echo $user_id; ?>"><i class="fas fa-key"></i> Права доступа</a></li>
												<li><a href="javascript:;"><i class="fas fa-ban"></i> Заблокировать</a></li>
												<li><a href="javascript:;"><i class="fas fa-trash-alt"></i> Удалить</a></li>
											</ul>
										</div>
										
										<div class="actions_popover">1111</div>
									</div>
								</div>
							</div>
							
							<div class="user_edit_<?php echo $user_id; ?> edit hide_me">
								<div class="row no_gutter">
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12">
										<a name="user_edit_<?php echo $user_id; ?>"></a>
                                        
                                        <div class="edit_user">
                                            Изменение пользователя
                                            <span><?php echo $user_name; ?></span>
                                            
                                            <a href="javascript:;" class="toggle_cb" data-toggle="user_edit_<?php echo $user_id; ?>"><i class="far fa-times-circle"></i></a>
                                        </div>
                                     </div>
                                    
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_6 col_l_6 push_m_3 push_l_3">
                                        <div class="edit_basic">
                                            <label class="fld filler">
                                                <em>ФИО</em>
                                                
                                                <div class="sign"><i class="fas fa-user"></i></div>
                                                <input type="text" class="inp" name="somename" />
                                            </label>
                                            
                                            <label class="fld filler">
                                                <em>Должность</em>
                                                
                                                <div class="sign"><i class="fas fa-user"></i></div>
                                                <input type="text" class="inp" name="somename" />
                                            </label>
                                            
                                            <label class="fld filler">
                                                <em>Email</em>
                                                
                                                <div class="sign"><i class="fas fa-envelope"></i></div>
                                                <input type="text" class="inp" name="somename" />
                                            </label>
                                            
                                            <label class="fld filler">
                                                <em>Телефон</em>
                                                
                                                <div class="sign"><i class="fas fa-phone"></i></div>
                                                <input type="text" class="inp" name="somename" />
                                            </label>
                                            
                                            <label class="fld filler">
                                                <em>Пароль</em>
                                                
                                                <div class="sign"><i class="fas fa-key"></i></div>
                                                <input type="password" class="inp" name="somename" />
                                            </label>
                                            
                                            <div class="clear_after">
                                                <button type="submit" name="submit" class="bttn bttn_submit bttn_40 right rds_5">Сохранить</button>
                                                
                                                <a href="javascript:;" class="bttn bttn_40 right rds_5 toggle_cb" data-toggle="user_edit_<?php echo $user_id; ?>">Закрыть</a>
                                            </div>
                                        </div>
									</div>
								</div>
							</div>
							
							<div class="user_roles_<?php echo $user_id; ?> edit hide_me">
								<div class="row no_gutter">
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12">
										<div class="edit_user">
                                            <a name="user_roles_<?php echo $user_id; ?>"></a>
                                        
                                            Управление правами пользователя
                                            <span><?php echo $user_name; ?></span>
                                            
                                            <a href="javascript:;" class="toggle_cb" data-toggle="user_roles_<?php echo $user_id; ?>"><i class="far fa-times-circle"></i></a>
                                        </div>
                                     </div>
                                    
									<div class="col_xxs_12 col_xs_12 col_s_12 col_m_6 col_l_6 push_m_3 push_l_3">
                                        <form class="accordion">
                                            <?php
                                                $modules = array(
                                                    array('Модуль', 'Контрагенты'),
                                                    array('Модуль', 'Финанализ'),
                                                    array('Модуль', 'Строительство'),
                                                    array('Модуль', 'Пользователи'),
                                                    array('Система', 'Доступ к настройкам'),
                                                );
                                                
                                                foreach ($modules as $module) {
                                            ?>
                                                
                                                <div class="edit_title toggle_acc">
                                                    <div class="icon rds_5">
                                                        <i class="fas fa-ambulance"></i>
                                                    </div>
                                                    
                                                    <span><?php echo $module[0]; ?></span>
                                                    <strong><?php echo $module[1]; ?></strong>
                                                    
                                                    <div class="toggler open"><i class="fal fa-plus-circle"></i></div>
                                                    <div class="toggler close"><i class="fal fa-minus-circle"></i></div>
                                                </div>
                                                
                                                <div class="inner_acc">
                                                    <?php
                                                        $access = array(
                                                            'Доступ к модулю',
                                                            'Добавлять данные',
                                                            'Изменять данные',
                                                            'Удалять данные',
                                                        );
                                                        
                                                        foreach ($access as $rule) {
                                                    ?>
                                                    
                                                        <div class="edit_item">
                                                            <strong><?php echo $rule; ?></strong>
                                                            
                                                            <label class="switcher">
                                                                <input type="checkbox" class="www" />
                                                            </label>
                                                        </div>
                                                    
                                                    <?php } ?>
                                                </div>
                                                
                                            <?php } ?>
                                            
                                            <div class="clear_after">
                                                <button type="submit" name="submit" class="bttn bttn_submit bttn_40 right rds_5">Сохранить</button>
                                                
                                                <a href="javascript:;" class="bttn bttn_40 right rds_5 toggle_cb" data-toggle="user_roles_<?php echo $user_id; ?>">Закрыть</a>
                                            </div>
                                        </form>
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
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
							<div class="module_title">Гарантийка <i class="fal fa-arrow-right"></i> Контрагенты</div>
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
									<i class="fas fa-user-plus"></i> Добавить контрагента
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
                                        <option data-display="&lt;i class=&quot;fas fa-archive&quot;&gt;&lt;/i&gt; Архивировать">Архивировать</option>
                                        <option data-display="&lt;i class=&quot;fas fa-trash-alt&quot;&gt;&lt;/i&gt; Удалить">Удалить</option>
                                    </select>
                                </li>
                                
                                <li>
                                    <button type="submit" class="button">Применить</button>
                                </li>
                            </form>
						</ul>
					</div>
				</div>
			</div>
            
			<?php 
				$companies = array(
					'ООО "Министерство Магии"',
					'Отряд Дамблдора',
					'Друмстранг' => array(
						'ООО "Турнир трех волшебников"',
						'ИП Грин-де-Вальд',
						'ИП Пожиратель Смерти',
						'ИП Виктор Крам',
					),
					'Oceanic Airlines',
					'Хогвартс',
				);
			?>
			
			<div class="module_content module_guarantee">
				<div class="container">
					<div class="row">
						<?php 
							foreach ($companies as $company) { 
							
							$id = rand(00001, 99999);
						?>
							
							<?php if (is_array($company)) { ?>
							
								<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12">
									<div class="item">
										<div class="title clr_b2">
											<label class="radiocheck">
												<input type="checkbox" class="www" />
												
												<strong><?php echo $company[0]; ?></strong>
											</label>
											
											<div class="more animate_150">
												<a href="javascript:;" class="toggle_cb" data-toggle="sub_<?php echo $id; ?>">
													И еще <?php echo count($company); ?> компании
												</a>
												
												<a href="g_page_2.php">Договоры</a>
												
												<a href="g_page_3.php">КС</a>
											</div>
											
											<a href="javascript:;" class="bookmark rds_full">
												<i class="far fa-bookmark"></i>
											</a>
											
											<a href="javascript:;" class="actions show_popover rds_full" data-popover="actions_<?php echo $id; ?>" data-popover-position="left">
												<i class="fas fa-ellipsis-v"></i>
											</a>
										
											<div class="hide_me">
												<div id="actions_<?php echo $id; ?>">
													<ul class="popover_menu">
														<li><a href="javascript:;"><i class="fas fa-paw"></i> <?php echo $company[0]; ?></a></li>
														<li><a href="javascript:;"><i class="fas fa-paw"></i> Любые</a></li>
														<li><a href="javascript:;"><i class="fas fa-paw"></i> Действия</a></li>
														<li><a href="javascript:;"><i class="fas fa-paw"></i> В этом меню</a></li>
													</ul>
												</div>
											</div>
										</div>
										
										<div class="sub sub_<?php echo $id; ?> hide_me">
											<?php foreach ($company as $parent => $child) { $id = rand(00001, 99999); ?>
												
												<div class="title clr_b2">
													<label class="radiocheck">
														<input type="checkbox" class="www" />
														
														<strong><?php echo $child; ?></strong>
													</label>
											
													<div class="more animate_150">
														<a href="g_page_2.php">Договоры</a>
														
														<a href="g_page_3.php">КС</a>
													</div>
											
													<a href="javascript:;" class="bookmark rds_full">
														<i class="far fa-bookmark"></i>
													</a>
											
													<a href="javascript:;" class="actions show_popover rds_full" data-popover="actions_<?php echo $id; ?>" data-popover-position="left">
														<i class="fas fa-ellipsis-v"></i>
													</a>
												
													<div class="hide_me">
														<div id="actions_<?php echo $id; ?>">
															<ul class="popover_menu">
																<li><a href="javascript:;"><i class="fas fa-paw"></i> <?php echo $child; ?></a></li>
																<li><a href="javascript:;"><i class="fas fa-paw"></i> Любые</a></li>
																<li><a href="javascript:;"><i class="fas fa-paw"></i> Действия</a></li>
																<li><a href="javascript:;"><i class="fas fa-paw"></i> В этом меню</a></li>
															</ul>
														</div>
													</div>
												</div>
												
											<?php } ?>
										</div>
									</div>
								</div>
							
							<?php } else { ?>
							
								<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12">
									<div class="item clr_b2">
										<div class="title">
											<label class="radiocheck">
												<input type="checkbox" class="www" />
												
												<strong><?php echo $company; ?></strong>
											</label>
											
											<div class="more animate_150">
												<a href="g_page_2.php">Договоры</a>
												
												<a href="g_page_3.php">КС</a>
											</div>
											
											<a href="javascript:;" class="bookmark rds_full">
												<i class="far fa-bookmark"></i>
											</a>
											
											<a href="javascript:;" class="actions show_popover rds_full" data-popover="actions_<?php echo $id; ?>" data-popover-position="left">
												<i class="fas fa-ellipsis-v"></i>
											</a>
										
											<div class="hide_me">
												<div id="actions_<?php echo $id; ?>">
													<ul class="popover_menu">
														<li><a href="javascript:;"><i class="fas fa-paw"></i> <?php echo $company; ?></a></li>
														<li><a href="javascript:;"><i class="fas fa-paw"></i> Любые</a></li>
														<li><a href="javascript:;"><i class="fas fa-paw"></i> Действия</a></li>
														<li><a href="javascript:;"><i class="fas fa-paw"></i> В этом меню</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							
							<?php } ?>
						
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
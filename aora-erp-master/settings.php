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
							<div class="module_title">Настройки</div>
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
            
			<div class="module_content module_settings">
				<div class="container">
					<div class="row">
						<div class="col_xxs_12 col_xs_12 col_s_12 col_m_12 col_l_12">
							<div class="accordion">
								<div class="clr_b2">
									<a class="toggle_acc" href="javascript:void(0);">
										<i class="fal fa-plus-circle"></i> Персональные данные
									</a>
									
									<div class="inner_acc">
										<div class="row">
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_4 col_l_4">
												<label class="fld filler">
													<em>Фамилия</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
											
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_4 col_l_4">
												<label class="fld filler">
													<em>Имя</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
											
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_4 col_l_4">
												<label class="fld filler">
													<em>Отчество</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
										</div>
									</div>
								</div>

								<div class="clr_b2">
									<a class="toggle_acc" href="javascript:void(0);">
										<i class="fal fa-plus-circle"></i> Контактная информация
									</a>
									
									<div class="inner_acc">
										<div class="row">
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_6 col_l_6">
												<label class="fld filler">
													<em>Почта</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
											
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_6 col_l_6">
												<label class="fld filler">
													<em>Телефон</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
										</div>
									</div>
								</div>

								<div class="clr_b2">
									<a class="toggle_acc" href="javascript:void(0);">
										<i class="fal fa-plus-circle"></i> Пароль
									</a>
									
									<div class="inner_acc">
										<div class="row">
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_6 col_l_6">
												<label class="fld filler">
													<em>Новый пароль</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
											
											<div class="col_xxs_12 col_xs_12 col_s_12 col_m_6 col_l_6">
												<label class="fld filler">
													<em>Повтор пароля</em>
													
													<div class="sign"><i class="fas fa-user"></i></div>
													<input type="text" class="inp" name="somename" value="" />
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
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
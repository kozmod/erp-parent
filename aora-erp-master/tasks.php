

<!-- TASKS -->
	
<?php
	$tasks = array(
		'375' => array(
			'id' => '375',
			'title' => 'Название задачи 1',
			'deadline' => '1545987600', // Thu, 20 Dec 2018 15:20:00 GMT
			
			'subtasks' => array(
				array(
					'title' => 'Подзадача 1',
					'timing' => '7200',
					'days' => '2',
					'exclude' => false,
					'parallel' => true,
				),
				
				array(
					'title' => 'Подзадача 2',
					'timing' => '14400',
					'days' => '5',
					'exclude' => false,
					'parallel' => true,
				),
				
				array(
					'title' => 'Подзадача 3',
					'timing' => '10800',
					'days' => '3',
					'exclude' => false,
					'parallel' => true,
				),
				
				array(
					'title' => 'Подзадача 4',
					'timing' => '5400',
					'days' => '7',
					'exclude' => false,
					'parallel' => true,
				),
			),
		),
		
		'678' => array(
			'id' => '678',
			'title' => 'Название задачи 2',
			'deadline' => '1545987600', // Sun, 10 Feb 2019 16:29:00 GMT
			
			'subtasks' => array(
				array(
					'title' => 'Подзадача 2-3',
					'timing' => '14400',
					'days' => '3',
					'exclude' => false,
					'parallel' => true,
				),
			),
		),
	);
	
	echo '<pre>';
	print_r($tasks);
	echo '</pre>';
	
	///////////////////////////////////////
	// TEMP VARS
	///////////////////////////////////////
	
	date_default_timezone_set('UTC');
	
	$working_days = array('1', '2', '3', '4', '5');
	$working_day_start = '10:00';
	$working_day_end = '18:00';
	$working_hours = strtotime(date('d.m.Y '.$working_day_end), 0) - strtotime(date('d.m.Y '.$working_day_start), 0);
	$busy_hours = array();
	$busy_hours_2 = array();
	
	//////////////////////////////////////////
	// Start tasks loop
	//////////////////////////////////////////
	
	foreach ($tasks as $task_id => $task) {
	
		// Set deadline day
		
		$deadline_day = $task['deadline']; // 10.02.2019 16:00
		$deadline_day = (int)$deadline_day; // 10.02.2019 16:00
		
		// Try to find last working deadline day
		
		echo '<hr>';
		echo 'Deadline date '.date('d.m.Y N H:i', $deadline_day);
		echo '<hr>';
		
		if (strtotime(date('d.m.Y '.$working_day_start, $deadline_day)) >= $deadline_day) {
			
			$deadline_day = strtotime(date('d.m.Y '.$working_day_end, $deadline_day - 86400));
			
		}
		
		if (strtotime(date('d.m.Y '.$working_day_end, $deadline_day)) < $deadline_day) {
			
			$deadline_day = strtotime(date('d.m.Y '.$working_day_end, $deadline_day));
			
		}
		
		if (!in_array(date('N', $deadline_day), $working_days)) {
			
			$deadline_day = $deadline_day - 86400;
			
			while (!in_array(date('N', $deadline_day), $working_days)) {
			
				$deadline_day = $deadline_day - 86400; 
			
			}
			
			$deadline_day = strtotime(date('d.m.Y '.$working_day_end, $deadline_day));
			
		} 
		
		echo 'Deadline date checked (twice) '.date('d.m.Y N H:i', $deadline_day);
		echo '<hr>';
		
		// Loop trough $busy_hours and create $busy_hours_2
		
		foreach ($busy_hours as $busy_day => $busy_total) {
			
			$busy_hours_2[$busy_day] = $busy_total['busy'];
			
		}
		
		// Define some vars
		
		$subtasks = $task['subtasks'];
		$loop_day = $deadline_day;
		
		// Exclude subtasks
		
		foreach ($subtasks as $i => $subtask) {
			
			if ($subtask['exclude'] === true) { unset($task[$i]); }
		
		}
		
		// Order  tasks
		
		sort($subtasks, SORT_NUMERIC);
		$total_subtasks = count($subtasks);
		array_reverse($subtasks);
		
		// Loop through tasks
		
		foreach ($subtasks as $i => $subtask) {
			
			echo $i.' '.$subtask['title'];
			echo '<br>';
			echo 'days = '.$subtask['days'];
			echo '<br>';
			echo 'seconds = '.$subtask['timing'];
			echo '<br>';
			echo '<br>';
			
			// Define $loop_day
			
			if ($i > 0) {
				
				foreach (range(0, ($i - 1)) as $item) {
					
					$earliest_date = min(array_keys($busy_hours));
					
				}
				
				//$loop_day = $earliest_date + (($subtask['days'] - 1) * 86400);
				$loop_day = $deadline_day;
				
			} else {
			
				$loop_day = $deadline_day;
			
			}
			
			// Check working days
			
			while (!in_array(date('N', $loop_day), $working_days)) {
			
				$loop_day = $loop_day - 86400;
			
			}
			
			$x = $subtask['days'];
			
			// While for days
			
			while ($x > 0) {
				
				$wh = ($loop_day === $deadline_day ? $deadline_day - strtotime(date('d.m.Y '.$working_day_start, $deadline_day)) : $working_hours);
				
				if (isset($busy_hours[strtotime(date('d.m.Y', $loop_day))]['busy'])) {
				
					$ha = $wh - $busy_hours[strtotime(date('d.m.Y', $loop_day))]['busy'];
				
				} else {
					
					$busy_hours[strtotime(date('d.m.Y', $loop_day))]['busy'] = 0;
					$ha = $wh;
					
				}
				
				$i_hours = (int)$subtask['timing'];
				
				var_dump($ha);

				if ($ha >= $i_hours) {
					
					$i_2 = $i; $i_2++;
					
					// Try to fit subtasks
					
					if ($i_2 < $total_subtasks) {
						
						foreach (range($i_2, ($total_subtasks - 1)) as $item) {
							
							if ($ha - $i_hours - $subtasks[$item]['timing'] >= 0) {
								
								$i_hours = $i_hours + $subtasks[$item]['timing'];
							
								echo '<br>';
								var_dump($i_hours);
								echo '<hr>';
								
							}
							
						}
						
					}
					
					// Save total and free time to $loop_day 

					$busy_hours[strtotime(date('d.m.Y', $loop_day))]['busy'] = $busy_hours[strtotime(date('d.m.Y', $loop_day))]['busy'] + $subtask['timing'];
					$busy_hours[strtotime(date('d.m.Y', $loop_day))]['free'] = $wh - $busy_hours[strtotime(date('d.m.Y', $loop_day))]['busy'];
					
					// Calculate hours
					
					$i_hours = $i_hours - $subtask['timing'];
					
					if (!isset($busy_hours_2[strtotime(date('d.m.Y', $loop_day))])) {
					
						$busy_hours_2[strtotime(date('d.m.Y', $loop_day))] = 0;
					
					} 
					
					$tmp2 = strtotime(date('d.m.Y '.$working_day_start, $loop_day)) + $busy_hours_2[strtotime(date('d.m.Y', $loop_day))] + $i_hours;
					$tmp3 = $tmp2 + $subtask['timing'];
					
					// Save tasks to array
					
					$busy_hours[strtotime(date('d.m.Y', $loop_day))]['tasks'][] = array(
						'task_id' => $task_id,
						'subtask_id' => ($total_subtasks - 1) - $i,
						'st' => $tmp2,
						'et' => $tmp3
					);
					
					echo '<br>';
					var_dump(date('d.m.Y N H:i', $tmp2));
					echo ' ';
					echo $tmp2;
					echo '<br>';
					var_dump(date('d.m.Y N H:i', $tmp3));
					echo ' ';
					echo $tmp3;
					echo '<br>';
					
					// Select previous day
					
					$x--;
					$loop_day = $loop_day - 86400;
					
				} else {
					
					$loop_day = $loop_day - 86400;
					
				}
			
				// Check working days
				
				while (!in_array(date('N', $loop_day), $working_days)) {
				
					$loop_day = $loop_day - 86400;
				
				}
					
			}
			
			echo '<hr>';
			
		}
	
	}
	
	//////////////////////////////////////////
	// End tasks loop
	//////////////////////////////////////////
	
	echo 'total days = '.count($busy_hours);
	echo '<hr>';
	
	echo '<pre>';
	print_r($busy_hours);
	echo '</pre>';
	
	echo '<hr>';
	
	echo '<pre>';
	print_r($busy_hours_2);
	echo '</pre>';
	
	echo '<hr>';
?>
	
<?php foreach ($busy_hours as $day => $meta) { ?>
		
	<div style="margin-bottom:20px; padding:20px; border:1px solid #666;">
		
		<div>
			дата: <?php echo date('d.m.Y N', $day); ?>
			
			<br>
			
			занятого времени: <?php echo $meta['busy'] / 3600; ?>
			
			<br>
			
			свободного времени: <?php echo $meta['free'] / 3600; ?>
		</div>
		
		<hr>
		
		<?php
			$subtasks = $meta['tasks'];
			foreach ($subtasks as $task) {
		?>
			
			<div style="margin-top:20px;">
				задача <?php echo $tasks[$task['task_id']]['title']; ?>
				
				<br>
				
				подзадача <?php echo $tasks[$task['task_id']]["subtasks"][$task['subtask_id']]['title']; ?>
				
				<br>
				
				начать: <?php echo date('d.m.Y H:i', $task['st']); ?>
				
				<br>
				
				кончить: <?php echo date('d.m.Y H:i', $task['et']); ?>
			</div>
			
		<?php } ?>
		
	</div>	
		
<?php } ?>
	
<!-- TASKS -->
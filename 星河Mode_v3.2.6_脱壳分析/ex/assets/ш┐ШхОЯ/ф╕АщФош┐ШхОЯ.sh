echo 正在还原
#性能
settings delete global accelerated_enabled_for_all >/dev/null 2>&1 &
settings delete global game_accelerator_state >/dev/null 2>&1 &
settings delete global touch_performance_boost >/dev/null 2>&1 &
settings delete global game_touch_sampling_rate >/dev/null 2>&1 &
settings delete global accelerated_for_all >/dev/null 2>&1 &
settings delete global hwui.disable.vsync >/dev/null 2>&1 &
settings delete global game_acceleration_enabled >/dev/null 2>&1 &
settings delete global game_mode_high_fps >/dev/null 2>&1 &
settings delete system block_untrusted_touches >/dev/null 2>&1 &
settings delete system pemMonsterState >/dev/null 2>&1 &
settings delete system pem_little_window_high_temp_protect >/dev/null 2>&1 &
settings delete system electroic_mode_enabled >/dev/null 2>&1 &
settings delete system pointer_speed >/dev/null 2>&1 &
wait
echo 20%
settings delete system gamewatch_game_target_fps >/dev/null 2>&1 &
settings delete secure double_tap_speed >/dev/null 2>&1 &
settings delete secure long_press_timeout >/dev/null 2>&1 &
settings delete secure TapInterval >/dev/null 2>&1 &
settings delete secure vivo_career_mode_order_assistance_toggle_state >/dev/null 2>&1 &
settings delete secure vivo_career_mode_toggle_state >/dev/null 2>&1 &
settings delete secure is_game_mode >/dev/null 2>&1 &
settings delete global game_mode >/dev/null 2>&1 &
settings delete system game_scene_more_fps >/dev/null 2>&1 &
settings delete secure vivo_reduce_dbi_frequency >/dev/null 2>&1 &
settings delete secure system_property_power_mode_type >/dev/null 2>&1 &
settings delete system vts_game_para_adjust >/dev/null 2>&1 &
settings delete system sys.vivo.cont_start >/dev/null 2>&1 &
wait
echo 40%
settings delete system benchmark_mode >/dev/null 2>&1 &
settings delete global game_mode_network_boost >/dev/null 2>&1 &
settings delete global game_framerate_high >/dev/null 2>&1 &
settings delete system surface_flinger.touch_property_rate >/dev/null 2>&1 &
settings delete system touch_chip_enhancement >/dev/null 2>&1 &
settings delete global sf.disable_triple_buffer >/dev/null 2>&1 &
settings delete system lcm_highbrightness_float >/dev/null 2>&1 &
settings delete system pem_ddc_open_game_list >/dev/null 2>&1 &
settings delete global debug.sf.enable_gl_backpressure >/dev/null 2>&1 &
settings delete global block_untrusted_touches >/dev/null 2>&1 &
settings delete system bench_mark_mode >/dev/null 2>&1 &
settings delete system game_plus_mode_key >/dev/null 2>&1 &
settings delete secure multi_press_timeout >/dev/null 2>&1 &
settings delete global debug.sf.latch_unsignaled >/dev/null 2>&1 &
settings delete secure vivo_career_mode_manual_state >/dev/null 2>&1 &
wait
#均衡
echo 60%
settings delete global zram_enabled >/dev/null 2>&1 &
settings delete global app_standby_enabled >/dev/null 2>&1 &
settings delete global adaptive_battery_management_enabled >/dev/null 2>&1 &
settings delete system app_auto_restriction_enabled >/dev/null 2>&1 &
settings delete global ppm_perf_enabled >/dev/null 2>&1 &
settings delete global hwui_disable_vsync >/dev/null 2>&1 &
settings delete system storage_benchmark >/dev/null 2>&1 &
settings delete system adaptive_learning_enabled >/dev/null 2>&1 &
settings delete system app_usage_tracking_enabled >/dev/null 2>&1 &
settings delete global smart_power_control >/dev/null 2>&1 &
settings delete global accelerated_enabled_for_all >/dev/null 2>&1 &
settings delete global app_memory_compression >/dev/null 2>&1 &
settings delete global debug.sf.enable_gl_backpressure >/dev/null 2>&1 &
settings delete global cl_cmdrate >/dev/null 2>&1 &
settings delete global cl_updaterate >/dev/null 2>&1 &
settings delete system gamewatch_game_target_fps >/dev/null 2>&1 &
settings delete secure long_press_timeout >/dev/null 2>&1 &
settings delete secure multi_press_timeout >/dev/null 2>&1 &
settings delete global game_accelerator_state >/dev/null 2>&1 &
settings delete system is_network_enhancement_enable >/dev/null 2>&1 &
settings delete system debug.sf.hw >/dev/null 2>&1 &
wait
#续航
echo 70%
settings delete system vivo_background_management >/dev/null 2>&1 &
settings delete secure vivo_app_hibernation >/dev/null 2>&1 &
settings delete system vivo_smart_standby >/dev/null 2>&1 &
settings delete global vivo_deep_sleep >/dev/null 2>&1 &
settings delete system vivo_deep_sleep_mode >/dev/null 2>&1 &
settings delete global wifi_scan_always_enabled >/dev/null 2>&1 &
settings delete global ble_scan_always_enabled >/dev/null 2>&1 &
settings delete global wifi_scan_throttle_enabled >/dev/null 2>&1 &
settings delete global wifi_power_save >/dev/null 2>&1 &
settings delete global adaptive_battery_management_enabled >/dev/null 2>&1 &
settings delete global auto_sync >/dev/null 2>&1 &
settings delete global protect_battery >/dev/null 2>&1 &
wait
echo 90%
sleep 0.5
echo 还原完成
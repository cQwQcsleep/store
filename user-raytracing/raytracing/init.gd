extends Node
##
## RayTracing mod controller.
## Adds a low-res SubViewport with a fullscreen fragment path tracer as an
## overlay. Defaults to a Cornell-box test scene; F8 switches to live-probed
## game geometry (baked once, so CPU cost stays low on a 2-core machine).

const DEFAULT_FOV := 50.0
const MIN_SCALE := 0.125
const MAX_SCALE := 1.0
const ACCUM_FRAMES := 12
const ACCUM_ALPHA := 0.5

var _dir := ""
var _layer: CanvasLayer
var _container: SubViewportContainer
var _viewport: SubViewport
var _rect: ColorRect
var _mat: ShaderMaterial

var _triangles: Array = []
var _nodes: Array = []
var _order: Array = []
var _tri_count := 0

var _enabled := true
var _use_game_scene := false
var _render_scale := 0.25
var _frame := 0
var _accum_ready := 0

# Camera (default test-scene placement)
var _cam_pos := Vector3(1.0, 1.0, 5.0)
var _cam_target := Vector3(1.0, 1.0, 0.0)


func _ready() -> void:
	_dir = get_script().resource_path.get_base_dir()
	# Overlay first so the material exists; then _rebuild_scene_data pushes the
	# packed textures into it.
	_setup_overlay()
	_rebuild_scene_data()
	set_process(true)


func _log(msg: String) -> void:
	var l := get_node_or_null("/root/ModLoaderLog")
	if l != null:
		l.info("[RayTracing] " + msg, "RayTracing")
	else:
		print("[RayTracing] " + msg)


func _build_triangles() -> Array:
	if _use_game_scene:
		var probe := load(_dir + "/scene_probe.gd")
		var root := get_tree().current_scene
		if root == null:
			root = get_tree().root
		var cam: Camera3D = probe.find_camera(root)
		if cam != null:
			_cam_pos = cam.global_transform.origin
			_cam_target = _cam_pos - cam.global_transform.basis.z
		var tris: Array = probe.collect_triangles(root, 4096)
		if tris.size() >= 3:
			_log("probed %d game triangles" % tris.size())
			return tris
		_log("game probe empty, falling back to test scene")
	var test := load(_dir + "/test_scene.gd")
	return test.build()


func _rebuild_scene_data() -> void:
	_triangles = _build_triangles()
	var bvh := load(_dir + "/bvh.gd")
	var result: Dictionary = bvh.build(_triangles, 4)
	_nodes = result.nodes
	_order = result.order
	var pack := load(_dir + "/pack.gd")
	var packed: Dictionary = pack.pack_scene(_triangles, _nodes, _order)
	_tri_count = packed.tri_count
	if _mat != null:
		_mat.set_shader_parameter("tri_data", packed.tri_texture)
		_mat.set_shader_parameter("bvh_nodes", packed.bvh_texture)
		_mat.set_shader_parameter("tri_order", packed.order_texture)
		_mat.set_shader_parameter("tri_count", _tri_count)
		_mat.set_shader_parameter("bvh_root", 0)
	_accum_ready = 0
	_log("scene ready: %d triangles, %d bvh nodes" % [_triangles.size(), _nodes.size()])


func _setup_overlay() -> void:
	_layer = CanvasLayer.new()
	_layer.layer = 100
	add_child(_layer)

	_container = SubViewportContainer.new()
	_container.set_anchors_preset(Control.PRESET_FULL_RECT)
	_container.stretch = true
	_container.mouse_filter = Control.MOUSE_FILTER_IGNORE
	# Nearest-neighbour upscale: keep the pixelated look matching the game's
	# low-poly / low-res art instead of a soft bilinear blur.
	_container.texture_filter = CanvasItem.TEXTURE_FILTER_NEAREST
	_layer.add_child(_container)

	_viewport = SubViewport.new()
	_viewport.transparent_bg = true
	_viewport.render_target_clear_mode = SubViewport.RENDER_TARGET_CLEAR_MODE_NEVER
	_container.add_child(_viewport)

	_rect = ColorRect.new()
	_rect.set_anchors_preset(Control.PRESET_FULL_RECT)
	_rect.color = Color(0, 0, 0, 0)
	_rect.mouse_filter = Control.MOUSE_FILTER_IGNORE
	_viewport.add_child(_rect)

	_mat = ShaderMaterial.new()
	_mat.shader = load(_dir + "/trace.gdshader")
	_rect.material = _mat

	_update_viewport_size()
	_update_camera_uniforms()
	get_viewport().size_changed.connect(_update_viewport_size)


func _update_viewport_size() -> void:
	if _viewport == null:
		return
	var size: Vector2 = get_viewport().get_visible_rect().size
	var vs := Vector2i(maxi(64, int(size.x * _render_scale)), maxi(36, int(size.y * _render_scale)))
	_viewport.size = vs
	_accum_ready = 0


func _update_camera_uniforms() -> void:
	if _mat == null:
		return
	var fwd := (_cam_target - _cam_pos).normalized()
	var right := fwd.cross(Vector3.UP).normalized()
	if right.length_squared() < 1e-6:
		right = Vector3.RIGHT
	var up := right.cross(fwd).normalized()
	_mat.set_shader_parameter("cam_pos", _cam_pos)
	_mat.set_shader_parameter("cam_forward", fwd)
	_mat.set_shader_parameter("cam_right", right)
	_mat.set_shader_parameter("cam_up", up)
	_mat.set_shader_parameter("cam_fov", DEFAULT_FOV)


func _process(_delta: float) -> void:
	if _mat == null:
		return
	_frame += 1
	if _accum_ready < ACCUM_FRAMES:
		_accum_ready += 1
		_mat.set_shader_parameter("acc_alpha", 0.0)
	else:
		_mat.set_shader_parameter("acc_alpha", ACCUM_ALPHA)
	if _use_game_scene:
		var cam := _find_camera()
		if cam != null:
			_cam_pos = cam.global_transform.origin
			_cam_target = _cam_pos - cam.global_transform.basis.z
			_update_camera_uniforms()


func _find_camera() -> Camera3D:
	var root := get_tree().current_scene
	if root == null:
		root = get_tree().root
	var probe := load(_dir + "/scene_probe.gd")
	return probe.find_camera(root)


func _unhandled_input(event: InputEvent) -> void:
	if not (event is InputEventKey) or not event.pressed or event.echo:
		return
	match event.physical_keycode:
		KEY_F8:
			_use_game_scene = not _use_game_scene
			_rebuild_scene_data()
			_update_camera_uniforms()
			_log("scene mode: %s" % ("game probe" if _use_game_scene else "test scene"))
		KEY_F9:
			_enabled = not _enabled
			_layer.visible = _enabled
			_log("ray tracing %s" % ("enabled" if _enabled else "disabled"))
		KEY_F10:
			_render_scale = clampf(_render_scale * 0.75, MIN_SCALE, MAX_SCALE)
			_update_viewport_size()
			_log("render scale: %.2f" % _render_scale)
		KEY_F11:
			_render_scale = clampf(_render_scale * 1.333, MIN_SCALE, MAX_SCALE)
			_update_viewport_size()
			_log("render scale: %.2f" % _render_scale)

extends Node
##
## Ray Tracing mod - entry point.
## Loads the real controller from raytracing/init.gd.


func _init() -> void:
	ModLoaderLog.info("Initializing RayTracing mod", "RayTracing")
	var init_path := get_script().resource_path.get_base_dir() + "/raytracing/init.gd"
	var mod := load(init_path).new()
	add_child(mod)

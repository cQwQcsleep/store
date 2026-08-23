extends RefCounted
##
## SceneProbe - extracts a triangle soup (with materials) from the live game
## scene at runtime. Falls back gracefully (empty array) if nothing usable is
## found. Triangles are transformed into world space.
##
## Triangle format: { "v0", "v1", "v2": Vector3, "albedo": Color,
##                    "emissive": float, "spec": Color, "rough": float,
##                    "ior": float, "trans": float }


static func find_camera(root: Node) -> Camera3D:
	var cams := root.find_children("*", "Camera3D", true, false)
	for c in cams:
		var cam := c as Camera3D
		if cam != null and cam.is_current():
			return cam
	if not cams.is_empty():
		return cams[0] as Camera3D
	return null


static func collect_triangles(root: Node, max_tris := 4096) -> Array:
	var tris: Array = []
	var meshes := root.find_children("*", "MeshInstance3D", true, false)
	for mi in meshes:
		var m := mi as MeshInstance3D
		var mesh := m.mesh
		if mesh == null:
			continue
		var xform := m.global_transform
		for s in mesh.get_surface_count():
			if tris.size() >= max_tris:
				break
			var arr := mesh.surface_get_arrays(s)
			if arr.is_empty():
				continue
			var verts: PackedVector3Array = arr[Mesh.ARRAY_VERTEX]
			var idx: PackedInt32Array = arr[Mesh.ARRAY_INDEX]
			var mat: Material = m.get_surface_override_material(s)
			if mat == null:
				mat = mesh.surface_get_material(s)
			var albedo := Color(0.8, 0.8, 0.8)
			var emissive := 0.0
			var spec := Color(0.04, 0.04, 0.04)
			var rough := 1.0
			var ior := 1.0
			var trans := 0.0
			if mat is BaseMaterial3D:
				var bm := mat as BaseMaterial3D
				albedo = bm.albedo_color
				if bm.emission_enabled:
					emissive = maxf(bm.emission_energy_multiplier, 1.0)
				var metallic := clampf(bm.metallic, 0.0, 1.0)
				spec = albedo.lerp(Color(0.04, 0.04, 0.04), 1.0 - metallic)
				rough = clampf(bm.roughness, 0.0, 1.0)
				if bm.transparency != BaseMaterial3D.TRANSPARENCY_DISABLED:
					trans = 0.9
					ior = 1.5
			if idx.is_empty():
				for v in range(0, verts.size() - 2, 3):
					if tris.size() >= max_tris:
						break
					_push(tris, xform * verts[v], xform * verts[v + 1], xform * verts[v + 2], albedo, emissive, spec, rough, ior, trans)
			else:
				for v in range(0, idx.size() - 2, 3):
					if tris.size() >= max_tris:
						break
					_push(tris, xform * verts[idx[v]], xform * verts[idx[v + 1]], xform * verts[idx[v + 2]], albedo, emissive, spec, rough, ior, trans)
		if tris.size() >= max_tris:
			break
	return tris


static func _push(tris: Array, a: Vector3, b: Vector3, c: Vector3, albedo: Color, emissive: float, spec: Color, rough: float, ior: float, trans: float) -> void:
	if a.is_equal_approx(b) or b.is_equal_approx(c) or a.is_equal_approx(c):
		return
	tris.append({"v0": a, "v1": b, "v2": c, "albedo": albedo, "emissive": emissive, "spec": spec, "rough": rough, "ior": ior, "trans": trans})

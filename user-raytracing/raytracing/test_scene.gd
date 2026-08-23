extends RefCounted
##
## TestScene - a Cornell-box triangle soup with an emissive light panel.
## Room spans x/y/z in [0, 2]; the front (z = 0) is open so a camera placed on
## the +Z axis can look in. Includes one metal box, one glossy dielectric and
## one glass box to showcase reflections / refractions.
##
## Triangle format: { "v0", "v1", "v2": Vector3, "albedo": Color,
##                    "emissive": float, "spec": Color, "rough": float,
##                    "ior": float, "trans": float }


static func build() -> Array:
	var tris: Array = []

	# Floor (y = 0)
	_quad(tris, Vector3(0, 0, 0), Vector3(2, 0, 0), Vector3(2, 0, 2), Vector3(0, 0, 2), Color(0.75, 0.75, 0.75))
	# Ceiling (y = 2)
	_quad(tris, Vector3(0, 2, 0), Vector3(0, 2, 2), Vector3(2, 2, 2), Vector3(2, 2, 0), Color(0.75, 0.75, 0.75))
	# Back wall (z = 2)
	_quad(tris, Vector3(0, 0, 2), Vector3(2, 0, 2), Vector3(2, 2, 2), Vector3(0, 2, 2), Color(0.75, 0.75, 0.75))
	# Left wall (x = 0, red)
	_quad(tris, Vector3(0, 0, 2), Vector3(0, 0, 0), Vector3(0, 2, 0), Vector3(0, 2, 2), Color(0.65, 0.05, 0.05))
	# Right wall (x = 2, green)
	_quad(tris, Vector3(2, 0, 0), Vector3(2, 0, 2), Vector3(2, 2, 2), Vector3(2, 2, 0), Color(0.12, 0.45, 0.10))
	# Emissive light panel on the ceiling (non-reflective)
	_quad(tris, Vector3(0.75, 1.99, 0.75), Vector3(1.25, 1.99, 0.75), Vector3(1.25, 1.99, 1.25), Vector3(0.75, 1.99, 1.25), Color(1, 1, 1), 8.0, Color(0, 0, 0), 1.0)
	# Metal box
	_box(tris, Vector3(0.3, 0, 0.3), Vector3(0.8, 0.8, 0.8), Color(0.95, 0.95, 0.95), Color(0.95, 0.95, 0.95), 0.15)
	# Glossy dielectric box
	_box(tris, Vector3(1.2, 0, 1.1), Vector3(1.7, 0.6, 1.6), Color(0.6, 0.6, 0.9), Color(0.04, 0.04, 0.04), 0.4)
	# Glass box
	_box(tris, Vector3(0.75, 0, 0.9), Vector3(1.15, 0.4, 1.3), Color(0.9, 0.95, 1.0), Color(1, 1, 1), 0.02, 1.5, 0.95)
	return tris


static func _quad(tris: Array, a: Vector3, b: Vector3, c: Vector3, d: Vector3, albedo: Color, emissive: float = 0.0, spec: Color = Color(0.04, 0.04, 0.04), rough: float = 0.9, ior: float = 1.0, trans: float = 0.0) -> void:
	tris.append({"v0": a, "v1": b, "v2": c, "albedo": albedo, "emissive": emissive, "spec": spec, "rough": rough, "ior": ior, "trans": trans})
	tris.append({"v0": a, "v1": c, "v2": d, "albedo": albedo, "emissive": emissive, "spec": spec, "rough": rough, "ior": ior, "trans": trans})


static func _box(tris: Array, p0: Vector3, p1: Vector3, albedo: Color, spec: Color = Color(0.04, 0.04, 0.04), rough: float = 0.9, ior: float = 1.0, trans: float = 0.0) -> void:
	var x0 := p0.x
	var y0 := p0.y
	var z0 := p0.z
	var x1 := p1.x
	var y1 := p1.y
	var z1 := p1.z
	_quad(tris, Vector3(x0, y0, z0), Vector3(x1, y0, z0), Vector3(x1, y0, z1), Vector3(x0, y0, z1), albedo, 0.0, spec, rough, ior, trans)
	_quad(tris, Vector3(x0, y1, z0), Vector3(x0, y1, z1), Vector3(x1, y1, z1), Vector3(x1, y1, z0), albedo, 0.0, spec, rough, ior, trans)
	_quad(tris, Vector3(x0, y0, z1), Vector3(x1, y0, z1), Vector3(x1, y1, z1), Vector3(x0, y1, z1), albedo, 0.0, spec, rough, ior, trans)
	_quad(tris, Vector3(x0, y0, z0), Vector3(x0, y1, z0), Vector3(x1, y1, z0), Vector3(x1, y0, z0), albedo, 0.0, spec, rough, ior, trans)
	_quad(tris, Vector3(x0, y0, z0), Vector3(x0, y0, z1), Vector3(x0, y1, z1), Vector3(x0, y1, z0), albedo, 0.0, spec, rough, ior, trans)
	_quad(tris, Vector3(x1, y0, z0), Vector3(x1, y0, z1), Vector3(x1, y1, z1), Vector3(x1, y1, z0), albedo, 0.0, spec, rough, ior, trans)

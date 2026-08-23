extends RefCounted
##
## RayPack - packs triangle / BVH data into RGBAF float textures that the
## fragment raytracer samples.
##
## Texture layouts (one float4 texel per data element, row-major, width TEX_W):
##   tri_data   : per triangle, 6 texels: v0, v1, v2, (albedo.rgb, emissive),
##                (specular.rgb, roughness), (ior, transparency, 0, 0)
##   bvh_nodes  : per node, 3 texels: bmin, bmax, (left, right, tri_start, tri_count)
##   tri_order  : per triangle, 1 texel: r = original triangle index

const TEX_W := 512


static func _put(img: Image, i: int, c: Color) -> void:
	var col := i % TEX_W
	var row := i / TEX_W
	img.set_pixel(col, row, c)


static func pack_scene(triangles: Array, nodes: Array, order: Array) -> Dictionary:
	var n_tris := triangles.size()
	var tri_px := n_tris * 6
	var bvh_px := nodes.size() * 3
	var order_px := n_tris

	var tri_img := Image.create(TEX_W, maxi(1, ceili(float(tri_px) / TEX_W)), false, Image.FORMAT_RGBAF)
	var bvh_img := Image.create(TEX_W, maxi(1, ceili(float(bvh_px) / TEX_W)), false, Image.FORMAT_RGBAF)
	var ord_img := Image.create(TEX_W, maxi(1, ceili(float(order_px) / TEX_W)), false, Image.FORMAT_RGBAF)

	for t in n_tris:
		var tri: Dictionary = triangles[t]
		_put(tri_img, t * 6 + 0, Color(tri.v0.x, tri.v0.y, tri.v0.z, 0.0))
		_put(tri_img, t * 6 + 1, Color(tri.v1.x, tri.v1.y, tri.v1.z, 0.0))
		_put(tri_img, t * 6 + 2, Color(tri.v2.x, tri.v2.y, tri.v2.z, 0.0))
		_put(tri_img, t * 6 + 3, Color(tri.albedo.r, tri.albedo.g, tri.albedo.b, tri.emissive))
		_put(tri_img, t * 6 + 4, Color(tri.spec.r, tri.spec.g, tri.spec.b, tri.rough))
		_put(tri_img, t * 6 + 5, Color(tri.ior, tri.trans, 0.0, 0.0))

	for ni in nodes.size():
		var node = nodes[ni]
		_put(bvh_img, ni * 3 + 0, Color(node.bmin.x, node.bmin.y, node.bmin.z, 0.0))
		_put(bvh_img, ni * 3 + 1, Color(node.bmax.x, node.bmax.y, node.bmax.z, 0.0))
		_put(bvh_img, ni * 3 + 2, Color(float(node.left), float(node.right), float(node.tri_start), float(node.tri_count)))

	for oi in order.size():
		_put(ord_img, oi, Color(float(order[oi]), 0.0, 0.0, 0.0))

	return {
		"tri_texture": ImageTexture.create_from_image(tri_img),
		"bvh_texture": ImageTexture.create_from_image(bvh_img),
		"order_texture": ImageTexture.create_from_image(ord_img),
		"tri_count": n_tris,
	}

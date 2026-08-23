extends RefCounted
##
## BvhBuilder - builds a binary BVH (median split on the largest centroid axis)
## over an array of triangles.
##
## Triangle format: { "v0": Vector3, "v1": Vector3, "v2": Vector3,
##                     "albedo": Color, "emissive": float }
##
## Returns { "nodes": Array[BvhNode], "order": Array[int] }
##   - nodes:     flattened BVH, nodes[0] is the root.
##   - order:     reordered triangle indices. Leaves reference a contiguous
##                slice [tri_start, tri_start + tri_count) of `order`.

class BvhNode:
	const INF := 1e30
	var bmin := Vector3(INF, INF, INF)
	var bmax := Vector3(-INF, -INF, -INF)
	# Child node indices. Both < 0 marks a leaf.
	var left := -1
	var right := -1
	# Leaf data: range into `order`.
	var tri_start := 0
	var tri_count := 0


static func build(triangles: Array, leaf_size := 4) -> Dictionary:
	var n := triangles.size()
	if n == 0:
		return {"nodes": [], "order": []}

	var tri_bounds: Array = []
	var centroids: Array = []
	for t in triangles:
		var bmin: Vector3 = t.v0
		bmin = bmin.min(t.v1).min(t.v2)
		var bmax: Vector3 = t.v0
		bmax = bmax.max(t.v1).max(t.v2)
		tri_bounds.append([bmin, bmax])
		centroids.append((bmin + bmax) * 0.5)

	var order: Array = []
	for i in n:
		order.append(i)

	var nodes: Array = []
	_build_rec(triangles, tri_bounds, centroids, order, 0, n, nodes, leaf_size)
	return {"nodes": nodes, "order": order}


static func _build_rec(
	triangles: Array,
	tri_bounds: Array,
	centroids: Array,
	order: Array,
	start: int,
	count: int,
	nodes: Array,
	leaf_size: int
) -> int:
	var node := BvhNode.new()
	var node_idx := nodes.size()
	nodes.append(node)

	# Union of triangle bounds.
	for i in range(start, start + count):
		var oi: int = order[i]
		node.bmin = node.bmin.min(tri_bounds[oi][0])
		node.bmax = node.bmax.max(tri_bounds[oi][1])

	if count <= leaf_size:
		node.tri_start = start
		node.tri_count = count
		return node_idx

	# Centroid bounds.
	var cmin: Vector3 = centroids[order[start]]
	var cmax: Vector3 = centroids[order[start]]
	for i in range(start + 1, start + count):
		var c: Vector3 = centroids[order[i]]
		cmin = cmin.min(c)
		cmax = cmax.max(c)
	var ext := cmax - cmin

	var axis := 0
	if ext.y > ext.x and ext.y > ext.z:
		axis = 1
	elif ext.z > ext.x and ext.z > ext.y:
		axis = 2

	# Degenerate bounds: fall back to a leaf.
	if ext[axis] < 1.0e-6:
		node.tri_start = start
		node.tri_count = count
		return node_idx

	# Median split along the largest axis.
	var sub := order.slice(start, start + count)
	sub.sort_custom(func(a: int, b: int) -> bool:
		return centroids[a][axis] < centroids[b][axis]
	)
	for i in range(count):
		order[start + i] = sub[i]

	var mid := count / 2
	node.left = _build_rec(triangles, tri_bounds, centroids, order, start, mid, nodes, leaf_size)
	node.right = _build_rec(triangles, tri_bounds, centroids, order, start + mid, count - mid, nodes, leaf_size)
	return node_idx

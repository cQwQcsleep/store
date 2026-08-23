shader_type canvas_item;

// Fragment-shader software path tracer for the GL Compatibility renderer.
// Samples triangle / BVH data from RGBAF textures and renders it fullscreen.
// Features (ported from RenderLab-style techniques):
//   - Stack-based BVH traversal + Moller-Trumbore intersection
//   - Diffuse, glossy (rough) reflection, glass refraction (Schlick + TIR)
//   - Direct sun with a shadow ray (reduces direct-light noise)
//   - Reinhard tonemapping + gamma (accumulation happens in display space)
//
// tri_data texel layout (per triangle):
//   t*6+0/1/2 : v0/v1/v2
//   t*6+3     : (albedo.rgb, emissive)
//   t*6+4     : (specular.rgb, roughness)
//   t*6+5     : (ior, transparency, 0, 0)

uniform sampler2D tri_data : filter_nearest, repeat_disable;
uniform sampler2D bvh_nodes : filter_nearest, repeat_disable;
uniform sampler2D tri_order : filter_nearest, repeat_disable;
uniform int tri_count = 0;
uniform int bvh_root = 0;

// ---- Camera ----
uniform vec3 cam_pos = vec3(1.0, 1.0, 5.0);
uniform vec3 cam_right = vec3(1.0, 0.0, 0.0);
uniform vec3 cam_up = vec3(0.0, 1.0, 0.0);
uniform vec3 cam_forward = vec3(0.0, 0.0, -1.0);
uniform float cam_fov = 50.0;

// ---- Lighting ----
// sun_dir points from the scene TOWARD the sun (used by both the direct-light
// term and the shadow ray, so they stay consistent).
uniform vec3 sun_dir = vec3(-0.35, 1.0, -0.4);
uniform vec3 sun_color = vec3(1.0, 0.95, 0.85);
uniform float sun_intensity = 1.2;
uniform vec3 sky_color = vec3(0.05, 0.08, 0.12);

// ---- Controls ----
uniform int max_bounces = 3;
uniform float acc_alpha = 0.0;   // temporal accumulation strength (0 = off)
uniform float overlay_alpha = 1.0;

const float PI = 3.14159265359;
const int STACK_SIZE = 64;
const int MAX_LEAF_TRIS = 8;
const int MAX_BOUNCES_LIMIT = 5;


vec4 fetch_tex(sampler2D tex, int idx) {
	ivec2 dims = textureSize(tex, 0);
	int col = idx % dims.x;
	int row = idx / dims.x;
	return texture(tex, (vec2(float(col), float(row)) + 0.5) / vec2(dims));
}

vec2 rng2(vec2 seed) {
	vec2 p = fract(seed * vec2(123.34, 456.21));
	p += dot(p, p + 45.32);
	return fract(p * p * (p.xy + p.yx));
}

struct Ray {
	vec3 o;
	vec3 d;
};

struct Hit {
	float t;
	int tri;
	vec3 n;
};

bool intersect_box(Ray r, vec3 bmin, vec3 bmax, inout float t0, inout float t1) {
	vec3 inv = 1.0 / r.d;
	vec3 tmn = (bmin - r.o) * inv;
	vec3 tmx = (bmax - r.o) * inv;
	vec3 a = min(tmn, tmx);
	vec3 b = max(tmn, tmx);
	t0 = max(max(a.x, a.y), a.z);
	t1 = min(min(b.x, b.y), b.z);
	return t1 >= max(t0, 0.0);
}

bool intersect_tri(Ray r, int t, inout float th, inout vec3 n) {
	vec3 v0 = fetch_tex(tri_data, t * 6 + 0).xyz;
	vec3 v1 = fetch_tex(tri_data, t * 6 + 1).xyz;
	vec3 v2 = fetch_tex(tri_data, t * 6 + 2).xyz;
	vec3 e1 = v1 - v0;
	vec3 e2 = v2 - v0;
	vec3 pv = cross(r.d, e2);
	float det = dot(e1, pv);
	if (abs(det) < 1e-9) return false;
	float inv_det = 1.0 / det;
	vec3 tv = r.o - v0;
	float u = dot(tv, pv) * inv_det;
	if (u < 0.0 || u > 1.0) return false;
	vec3 qv = cross(tv, e1);
	float v = dot(r.d, qv) * inv_det;
	if (v < 0.0 || (u + v) > 1.0) return false;
	float tt = dot(e2, qv) * inv_det;
	if (tt < 1e-5) return false;
	th = tt;
	n = normalize(cross(e1, e2));
	return true;
}

bool trace(Ray r, inout Hit hit) {
	float tbest = 1e30;
	int stack[STACK_SIZE];
	int sp = 0;
	stack[sp++] = bvh_root;
	bool found = false;
	while (sp > 0) {
		int ni = stack[--sp];
		vec3 bmin = fetch_tex(bvh_nodes, ni * 3 + 0).xyz;
		vec3 bmax = fetch_tex(bvh_nodes, ni * 3 + 1).xyz;
		vec4 child = fetch_tex(bvh_nodes, ni * 3 + 2);
		float t0;
		float t1;
		if (!intersect_box(r, bmin, bmax, t0, t1)) continue;
		if (t0 > tbest) continue;
		int ts = int(child.z);
		int tc = int(child.w);
		if (tc > 0) {
			for (int k = 0; k < MAX_LEAF_TRIS; k++) {
				if (k >= tc) break;
				int oi = int(fetch_tex(tri_order, ts + k).r);
				float tt;
				vec3 n;
				if (intersect_tri(r, oi, tt, n)) {
					if (tt < tbest) {
						tbest = tt;
						hit.t = tt;
						hit.tri = oi;
						hit.n = n;
						found = true;
					}
				}
			}
		} else {
			stack[sp++] = int(child.x);
			stack[sp++] = int(child.y);
		}
	}
	return found;
}

vec3 scatter_dir(vec3 n, vec2 rnd) {
	vec3 up = abs(n.y) < 0.999 ? vec3(0.0, 1.0, 0.0) : vec3(1.0, 0.0, 0.0);
	vec3 t = normalize(cross(up, n));
	vec3 b = cross(n, t);
	float u = rnd.x * 2.0 * PI;
	float s = sqrt(rnd.y);
	vec3 d = vec3(cos(u) * s, sin(u) * s, sqrt(max(1.0 - rnd.y, 0.0)));
	return normalize(t * d.x + b * d.y + n * d.z);
}

vec3 scatter_glossy(vec3 d, vec3 n, float rough, vec2 rnd) {
	vec3 r = reflect(d, n);
	if (rough < 0.02) return r;
	vec3 up = abs(r.y) < 0.999 ? vec3(0.0, 1.0, 0.0) : vec3(1.0, 0.0, 0.0);
	vec3 t = normalize(cross(up, r));
	vec3 b = cross(r, t);
	float u = rnd.x * 2.0 * PI;
	float s = sqrt(rnd.y);
	vec3 dd = vec3(cos(u) * s, sin(u) * s, sqrt(max(1.0 - rnd.y, 0.0)));
	return normalize(mix(r, normalize(t * dd.x + b * dd.y + r * dd.z), clamp(rough, 0.0, 1.0)));
}

// Directional-sun shadow ray: returns true if the point is occluded.
bool occluded(vec3 p, vec3 n) {
	Ray s;
	s.o = p + n * 1e-3;
	s.d = normalize(sun_dir);
	Hit h;
	return trace(s, h);
}

vec3 shade(Ray r, vec2 seed, inout float alpha) {
	vec3 col = vec3(0.0);
	vec3 thr = vec3(1.0);
	for (int b = 0; b < MAX_BOUNCES_LIMIT; b++) {
		if (b >= max_bounces) break;
		Hit hit;
		if (!trace(r, hit)) {
			if (b == 0) alpha = 0.0;
			col += thr * sky_color;
			break;
		}
		vec4 m0 = fetch_tex(tri_data, hit.tri * 6 + 3);
		vec3 albedo = m0.rgb;
		float emit = m0.a;
		vec4 m1 = fetch_tex(tri_data, hit.tri * 6 + 4);
		vec3 spec = m1.rgb;
		float rough = clamp(m1.a, 0.0, 1.0);
		vec4 m2 = fetch_tex(tri_data, hit.tri * 6 + 5);
		float ior = m2.r;
		float trans = m2.g;

		vec3 n = dot(hit.n, r.d) < 0.0 ? hit.n : -hit.n;
		vec3 p = r.o + r.d * hit.t;

		col += thr * emit;

		// Direct sun with shadow ray.
		float ndotl = max(dot(n, normalize(sun_dir)), 0.0);
		if (ndotl > 0.0 && !occluded(p, n)) {
			col += thr * albedo * sun_color * sun_intensity * ndotl;
		}

		if (b >= max_bounces - 1) break;

		vec2 rnd = rng2(seed + vec2(float(b) * 3.7, float(b) * 1.3));

		if (trans > 0.01) {
			// Glass: Schlick fresnel + refract/reflect, total internal reflection.
			float eta = ior <= 1.0 ? 1.5 : ior;
			float facing = dot(hit.n, r.d);
			float n1 = facing < 0.0 ? 1.0 : eta;
			float n2 = facing < 0.0 ? eta : 1.0;
			vec3 N = facing < 0.0 ? hit.n : -hit.n;
			float cosi = abs(dot(r.d, N));
			float f0 = 0.04;
			float fr = f0 + (1.0 - f0) * pow(1.0 - cosi, 5.0);
			if (rnd.x < fr) {
				thr *= spec;
				r.d = reflect(r.d, N);
			} else {
				thr *= (vec3(1.0) - fr * spec);
				vec3 rd = refract(r.d, N, n1 / n2);
				if (dot(rd, rd) < 1e-6) {
					r.d = reflect(r.d, N);
				} else {
					r.d = normalize(rd);
				}
			}
			r.o = p + r.d * 1e-3;
		} else {
			float sp = max(max(spec.r, spec.g), spec.b);
			vec2 srnd = rng2(seed + vec2(float(b) * 5.1, float(b) * 2.3));
			if (rnd.x < sp) {
				thr *= spec;
				r.d = scatter_glossy(r.d, n, rough, srnd);
				r.o = p + n * 1e-3;
			} else {
				thr *= albedo;
				r.d = scatter_dir(n, srnd);
				r.o = p + n * 1e-3;
			}
		}
	}
	return col;
}

vec3 tone_map(vec3 c) {
	c = c / (vec3(1.0) + c);         // Reinhard
	return pow(c, vec3(1.0 / 2.2));  // gamma
}

void fragment() {
	if (tri_count <= 0) {
		COLOR = vec4(0.0);
		return;
	}
	vec2 seed = rng2(SCREEN_UV * 791.0 + fract(TIME) * 1000.0);
	vec2 jit = rng2(seed + vec2(7.3, 3.1)) - 0.5;
	vec2 uv = SCREEN_UV + jit * SCREEN_PIXEL_SIZE;

	float aspect = SCREEN_PIXEL_SIZE.y / max(SCREEN_PIXEL_SIZE.x, 1e-6);
	float tan_h = tan(radians(cam_fov) * 0.5);
	vec2 ndc = vec2((uv.x * 2.0 - 1.0) * aspect, 1.0 - uv.y * 2.0) * tan_h;

	Ray r;
	r.o = cam_pos;
	r.d = normalize(cam_right * ndc.x + cam_up * ndc.y + cam_forward);

	float alpha = 1.0;
	vec3 col = tone_map(shade(r, seed, alpha));

	// Temporal accumulation in display space (SCREEN_TEXTURE = previous frame).
	if (acc_alpha > 0.0) {
		vec4 prev = texture(SCREEN_TEXTURE, SCREEN_UV);
		col = mix(col, prev.rgb, acc_alpha);
		alpha = mix(alpha, prev.a, acc_alpha);
	}
	COLOR = vec4(col, alpha * overlay_alpha);
}

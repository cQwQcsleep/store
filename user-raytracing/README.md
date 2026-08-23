# user-raytracing

面向 nophenia 游戏（Godot 4，GL Compatibility 渲染器）的**片元着色器软件光追 mod**。
在全屏片元着色器里实现 BVH 遍历 + 路径追踪，通过数据纹理把三角形 / BVH 数据送进 GPU。
针对 GTX950 级显卡与 2 核 CPU 做了降分辨率与低频 BVH 重建适配。

## 特性

- 全屏片元路径追踪（GL Compatibility，无需计算着色器）
- 中位数切分 BVH（静态场景一次构建，CPU 开销低）
- 材质：漫反射 / 金属光泽反射 / 玻璃（折射 + IOR + 透明度）
- 太阳直射光 + 阴影射线，Reinhard 色调映射 + Gamma 校正
- 时间累积降噪（12 帧）
- F8 切换：默认 Cornell Box 测试场景 / 实时探测游戏场景几何

## 目录

```
user-raytracing/
├── manifest.json       # ModLoader 元数据（namespace: user, name: raytracing）
├── mod_main.gd         # mod 入口
├── raytracing/
│   ├── init.gd         # 主控：覆盖层、相机、场景数据、输入
│   ├── trace.gdshader  # 片元光追着色器（BVH 遍历 + 求交 + 材质 + 色调映射）
│   ├── bvh.gd          # BVH 构建器
│   ├── pack.gd         # 三角形/BVH 打包为 RGBAF 纹理
│   ├── scene_probe.gd  # 运行时探测游戏场景几何
│   └── test_scene.gd   # Cornell Box 测试场景
└── user-raytracing.zip # 打包好的 mod，可直接放入游戏 mods/ 目录
```

## 安装

1. 将 `user-raytracing.zip` 复制到 nophenia 游戏根目录的 `mods/` 文件夹（不存在则新建）
2. 启动游戏，ModLoader（7.0.1）启动时自动解压到 `mods-unpacked/user-raytracing/` 并加载
3. 进入游戏后按 **F8** 在测试场景与游戏场景探测之间切换

日志出现 `[RayTracing] scene ready: N triangles, M bvh nodes` 即加载成功。

## 要求

- nophenia 游戏本体（内置 Godot ModLoader 7.0.1）
- 兼容游戏版本：0.0.1 – 1.0.2
- 显卡无需 RT 核心（软件光追），建议分辨率下调至 0.25（默认）

## 版本

- v0.1.0 — 首个发布版本

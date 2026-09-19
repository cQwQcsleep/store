#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
SRQ_8PRO_BLE_F80 固件(.bin) 解析 / 对比 / 打补丁工具
=================================================
识别: Telink TLSR82xx 系列 BLE SoC 固件(Thumb 指令), 产品 "SRQ_8PRO_BLE_F80"
用法:
  python3 srq_fw.py info   <file.bin>
  python3 srq_fw.py map    <file.bin>
  python3 srq_fw.py strings <file.bin>
  python3 srq_fw.py params <file.bin>          # 设备参数区(0x78563412 magic)
  python3 srq_fw.py table  <file.bin>          # 特征/功能记录表
  python3 srq_fw.py diff   <a.bin> <b.bin>
  python3 srq_fw.py patch  <file.bin> --off 0x.. --hex "AA BB" [--out out.bin]
  python3 srq_fw.py patch  <file.bin> --off 0x.. --str "xxx"   [--out out.bin]
"""
import sys, os, re, struct, math
from collections import Counter

BASE_FLASH = 0x00840000          # 固件代码在 Flash 中的映射基址(由头部地址表推断)
DEV_MAGIC  = b'\x12\x34\x56\x78' # 设备参数区 magic

def entropy(blk):
    c = Counter(blk); n = len(blk)
    return -sum((k/n)*math.log2(k/n) for k in c.values()) if n else 0

def read(path):
    with open(path, 'rb') as f:
        return f.read()

def find_ascii(d, minlen=4):
    return [(m.start(), m.group().decode('ascii')) for m in re.finditer(rb'[\x20-\x7e]{%d,}' % minlen, d)]

def find_utf8(d, minlen=2):
    return [(m.start(), m.group().decode('utf-8', 'ignore')) for m in re.finditer(rb'[\xe0-\xef][\x80-\xbf]{2}', d)]

# ---------------------------------------------------------------- info
def cmd_info(path):
    d = read(path)
    print(f'文件: {path}  大小: {len(d)} (0x{len(d):x})')
    if len(d) < 0x200:
        print('(!) 文件过小, 不是有效固件'); return
    magic = d[8:12]
    print(f'magic@8     : {magic!r} ({"匹配" if magic==b"KNLT" else "?"})')
    img_size = struct.unpack_from('<I', d, 0x18)[0]
    print(f'镜像大小@0x18: 0x{img_size:x}  ({img_size} B)  {"= 文件大小 [确认]" if img_size==len(d) else "<- 与实际不符!"}')
    w = lambda o: struct.unpack_from('<I', d, o)[0]
    print(f'0x00        : 0x{w(0):08x}   0x04: 0x{w(4):08x}   0x0C: 0x{w(0xC):08x}   0x10: 0x{w(0x10):08x}')
    print(f'代码起点@0x20: {d[0x20:0x28].hex(" ")} (Thumb)')
    print(f'表头@0x164 : 元素数 0x{w(0x168):x}/0x{w(0x16C):x}')
    for o in (0x170, 0x174, 0x178, 0x17C, 0x180, 0x184, 0x188, 0x18C, 0x190, 0x194, 0x198, 0x19C, 0x1A0, 0x1A4, 0x1A8, 0x1AC, 0x1B0):
        print(f'  表项 @{o:04x}: 0x{w(o):08x}')
    # 关键字符串
    for key in (b'SRQ_8PRO_BLE_F80', b'8.', b'V6.1.5', b'TELINK', b'Telink', b'pcode'):
        i = d.find(key)
        if i >= 0:
            s = d[i:i+60].split(b'\0')[0]
            print(f'字符串 @0x{i:06x}: {s!r}')
    # MAC / 密钥区
    for o in (0xC844, 0xC844+0x100):
        if o + 16 < len(d):
            print(f'@0x{o:06x}: {d[o:o+16].hex(" ")}')
    print(f'尾部4字节@0x{len(d)-4:06x}: {d[-4:].hex(" ")}  (=0x{struct.unpack_from("<I", d, len(d)-4)[0]:08x}, 算法未识别)')

# ---------------------------------------------------------------- map
def cmd_map(path):
    d = read(path)
    print(f'{path}  len={len(d)}')
    ent = [(off, entropy(d[off:off+128])) for off in range(0, len(d), 128)]
    regions = []
    for off, e in ent:
        kind = 'H' if e > 6.5 else ('M' if e > 4.5 else 'L')
        if regions and regions[-1][2] == kind:
            regions[-1][1] = off + 128
        else:
            regions.append([off, off+128, kind])
    for s, e, k in regions:
        print(f'  0x{s:06x}-0x{e:06x} ({e-s:6d}B) 熵={ent[s//128][1]:.2f}  {"高熵(疑似加密/随机)" if k=="H" else "低熵(表/填充)" if k=="L" else "代码/数据"}')
    # 特殊标记
    for mark, name in ((b'\x12\x34\x56\x78', '设备参数区'), (b'Sdk', 'SDK水印'), (b'8.4.9', '版本8.4.9'), (b'8.2.9', '版本8.2.9')):
        i = d.find(mark)
        if i >= 0:
            print(f'  * {name} @ 0x{i:06x}')

# ---------------------------------------------------------------- strings
def cmd_strings(path, ctx=24):
    d = read(path)
    ss = []
    for off, s in find_ascii(d, 5):
        ctxb = d[max(0, off-6):off]
        ss.append((off, s, ctxb.hex(' ')))
    print(f'{len(ss)} 条 ASCII 字符串:')
    for off, s, ctx in ss:
        print(f'  0x{off:06x} [{ctx}] {s!r}')

# ---------------------------------------------------------------- params
def cmd_params(path):
    d = read(path)
    i = d.find(DEV_MAGIC)
    if i < 0:
        print('未找到设备参数区 magic'); return
    end = min(i + 0x180, len(d))
    print(f'设备参数区 @ 0x{i:06x} - 0x{end:06x}:')
    j = i
    while j < end:
        row = d[j:j+16]
        asc = ''.join(chr(c) if 0x20 <= c < 0x7f else '.' for c in row)
        print(f'  {j:06x}: {row.hex(" "):47} {asc}')
        j += 16

# ---------------------------------------------------------------- table
def cmd_table(path):
    d = read(path)
    hits = []
    for i in range(0, len(d)-16, 2):
        if d[i] == 0 and d[i+1] == 0 and d[i+2] in (1, 3) and d[i+3] in (2, 0x10):
            ln, a, b = struct.unpack_from('<III', d, i+4)
            if ln <= 0x200 and (b >> 4) < 0x1000000 and a < len(d):
                if (b >= BASE_FLASH and b < BASE_FLASH + 0x10000) or b < len(d):
                    hits.append((i, d[i+2], d[i+3], ln, a, b))
    print(f'记录表候选 {len(hits)} 条: (类型=01/03, 子型=02/10, len, dataPtr, codePtr)')
    for off, t1, t2, ln, a, b in hits:
        mark = ''
        if a and a < len(d):
            s = d[a:a+32]
            if all(0x20 <= c < 0x7f or c == 0 for c in s):
                mark = s.split(b'\0')[0].decode('ascii', 'ignore')
        ref = b - BASE_FLASH if b >= BASE_FLASH else b
        print(f'  @0x{off:04x} type={t1}.{t2} len={ln:3d} data=0x{a:06x} code=0x{ref:06x} {mark}')

# ---------------------------------------------------------------- diff
def cmd_diff(a_path, b_path):
    a = read(a_path); b = read(b_path)
    print(f'A: {a_path} ({len(a)} B)\nB: {b_path} ({len(b)} B)')
    n = min(len(a), len(b))
    diffs = [i for i in range(n) if a[i] != b[i]]
    print(f'前 {n} 字节中共 {len(diffs)} 处不同 (A 多出 {max(0, len(a)-len(b))} B, B 多出 {max(0, len(b)-len(a))} B)')
    # 差异聚集段
    runs, s, p = [], None, None
    for i in diffs:
        if s is None: s = p = i
        elif i - p <= 32:
            p = i
        else:
            runs.append((s, p)); s = p = i
    if s is not None: runs.append((s, p))
    print(f'差异分为 {len(runs)} 段:')
    for s, e in runs[:80]:
        a1 = a[s:e+1]
        b1 = b[s:e+1]
        print(f'  0x{s:06x}-0x{e+1:06x} ({e+1-s} B)')
        for o in range(s, e+1, 16):
            ra = a[o:o+16].hex(' '); rb = b[o:o+16].hex(' ')
            print(f'    A[{o:06x}]: {ra}')
            print(f'    B[{o:06x}]: {rb}')
    # 共同区间的定位锚点(字符串)
    print('共享非差异区间的字符串锚点:')
    shared_ranges = []  # 简化为直接列字符串
    for m, name in ((b'KNLT', 'magic'), (b'8.4.9', '版本8.4.9'), (b'8.2.9', '版本8.2.9'), (b'SRQ_8PRO_BLE_F80', '产品名'), (b'V6.1.5', 'SDK版本')):
        ia, ib = a.find(m), b.find(m)
        if ia >= 0 or ib >= 0:
            print(f'  {name:12s}: A=0x{ia:06x} B=0x{ib:06x}')

# ---------------------------------------------------------------- patch
def cmd_patch(path, off, hexs=None, strs=None, out=None):
    d = bytearray(read(path))
    if hexs is not None:
        patch_bytes = bytes.fromhex(hexs)
    elif strs is not None:
        patch_bytes = strs.encode('utf-8')
    else:
        print('必须提供 --hex 或 --str'); return 1
    if not (0 <= off < len(d) and off + len(patch_bytes) <= len(d)):
        print(f'越界: off=0x{off:x} len={len(patch_bytes)}'); return 1
    old = bytes(d[off:off+len(patch_bytes)])
    d[off:off+len(patch_bytes)] = patch_bytes
    out = out or (os.path.splitext(path)[0] + '_patched.bin')
    with open(out, 'wb') as f:
        f.write(d)
    print(f'已打补丁: 0x{off:06x}  len={len(patch_bytes)}')
    print(f'  原: {old.hex(" ")}')
    print(f'  新: {patch_bytes.hex(" ")}')
    print(f'  输出: {out}')
    print('注意: 打补丁不会自动更新 0x18 镜像大小字段; 尾部 4 字节签名算法未识别(非常见CRC/累加),')
    print('      刷写前请确认设备是否校验该签名(建议先在真机/模拟器验证, 或对比官方工具生成的固件)。')
    return 0

if __name__ == '__main__':
    argv = sys.argv[1:]
    if not argv:
        print(__doc__); sys.exit(0)
    sub = argv[0]
    try:
        if sub == 'info':    cmd_info(argv[1])
        elif sub == 'map':   cmd_map(argv[1])
        elif sub == 'strings': cmd_strings(argv[1])
        elif sub == 'params': cmd_params(argv[1])
        elif sub == 'table': cmd_table(argv[1])
        elif sub == 'diff':  cmd_diff(argv[1], argv[2])
        elif sub == 'patch':
            off = None; hx = None; st = None; out = None
            i = 2
            while i < len(argv):
                if argv[i] == '--off': off = int(argv[i+1], 0); i += 2
                elif argv[i] == '--hex': hx = argv[i+1]; i += 2
                elif argv[i] == '--str': st = argv[i+1]; i += 2
                elif argv[i] == '--out': out = argv[i+1]; i += 2
                else: i += 1
            sys.exit(cmd_patch(argv[1], off, hx, st, out))
        else:
            print(f'未知子命令: {sub}'); print(__doc__)
    except IndexError:
        print(__doc__)
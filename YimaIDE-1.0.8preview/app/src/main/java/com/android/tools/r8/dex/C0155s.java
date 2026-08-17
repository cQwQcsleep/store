package com.android.tools.r8.dex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/* JADX INFO: renamed from: com.android.tools.r8.dex.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0155s {
    public final ByteBuffer a;

    public C0155s(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
    }

    public static C0155s b(byte[] bArr) {
        return new C0155s(ByteBuffer.wrap(bArr));
    }

    public final byte[] a() {
        return c().array();
    }

    public void c(int i) {
        this.a.position(i);
    }

    public int d() {
        return c().capacity();
    }

    public final byte e() {
        return c().get();
    }

    public int f() {
        return c().getInt();
    }

    public short g() {
        return c().getShort();
    }

    public final int h() {
        return this.a.getShort() & 65535;
    }

    public final String i() {
        byte[] bArr = new byte[this.a.getInt()];
        a(bArr);
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public final String j() {
        byte[] bArr = new byte[this.a.getShort() & 65535];
        a(bArr);
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public final boolean k() {
        return c().hasArray();
    }

    public final boolean l() {
        return c().hasRemaining();
    }

    public final int m() {
        return c().position();
    }

    public final int n() {
        return c().remaining();
    }

    public final void o() {
        this.a.rewind();
    }

    public ByteBuffer c() {
        return this.a;
    }

    public void a(ByteOrder byteOrder) {
        c().order(byteOrder);
    }

    public byte a(int i) {
        return c().get(i);
    }

    public final int b() {
        return c().arrayOffset();
    }

    public void a(byte[] bArr) {
        c().get(bArr);
    }

    public int b(int i) {
        return c().getInt(i);
    }
}

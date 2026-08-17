package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1742iQ extends E {
    public long d;
    public long e;
    public final ByteBuffer a = ByteBuffer.allocate(23).order(ByteOrder.LITTLE_ENDIAN);
    public final int b = 16;
    public final int c = 16;
    public int f = 0;

    public C1742iQ(int i) {
        long j = i;
        this.d = j;
        this.e = j;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final C0886Us a() {
        ByteBuffer byteBuffer;
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        this.a.flip();
        while (true) {
            int iRemaining = this.a.remaining();
            int i = this.c;
            byteBuffer = this.a;
            if (iRemaining < i) {
                break;
            }
            a(byteBuffer);
        }
        byteBuffer.compact();
        this.a.flip();
        if (this.a.remaining() > 0) {
            ByteBuffer byteBuffer2 = this.a;
            this.f = byteBuffer2.remaining() + this.f;
            long j8 = 0;
            switch (byteBuffer2.remaining()) {
                case 1:
                    j = 0;
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer3 = this.a;
                    byteBuffer3.position(byteBuffer3.limit());
                    break;
                case 2:
                    j2 = 0;
                    j = j2 ^ (((long) (byteBuffer2.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer4 = this.a;
                    byteBuffer4.position(byteBuffer4.limit());
                    break;
                case XmlPullParser.END_TAG /* 3 */:
                    j3 = 0;
                    j2 = j3 ^ (((long) (byteBuffer2.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer2.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer5 = this.a;
                    byteBuffer5.position(byteBuffer5.limit());
                    break;
                case 4:
                    j4 = 0;
                    j3 = (((long) (byteBuffer2.get(3) & 255)) << 24) ^ j4;
                    j2 = j3 ^ (((long) (byteBuffer2.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer2.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer6 = this.a;
                    byteBuffer6.position(byteBuffer6.limit());
                    break;
                case XmlPullParser.CDSECT /* 5 */:
                    j5 = 0;
                    j4 = j5 ^ (((long) (byteBuffer2.get(4) & 255)) << 32);
                    j3 = (((long) (byteBuffer2.get(3) & 255)) << 24) ^ j4;
                    j2 = j3 ^ (((long) (byteBuffer2.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer2.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer7 = this.a;
                    byteBuffer7.position(byteBuffer7.limit());
                    break;
                case XmlPullParser.ENTITY_REF /* 6 */:
                    j6 = 0;
                    j5 = (((long) (byteBuffer2.get(5) & 255)) << 40) ^ j6;
                    j4 = j5 ^ (((long) (byteBuffer2.get(4) & 255)) << 32);
                    j3 = (((long) (byteBuffer2.get(3) & 255)) << 24) ^ j4;
                    j2 = j3 ^ (((long) (byteBuffer2.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer2.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer8 = this.a;
                    byteBuffer8.position(byteBuffer8.limit());
                    break;
                case 7:
                    j6 = ((long) (byteBuffer2.get(6) & 255)) << 48;
                    j5 = (((long) (byteBuffer2.get(5) & 255)) << 40) ^ j6;
                    j4 = j5 ^ (((long) (byteBuffer2.get(4) & 255)) << 32);
                    j3 = (((long) (byteBuffer2.get(3) & 255)) << 24) ^ j4;
                    j2 = j3 ^ (((long) (byteBuffer2.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer2.get(1) & 255)) << 8);
                    j7 = j ^ ((long) (byteBuffer2.get(0) & 255));
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer9 = this.a;
                    byteBuffer9.position(byteBuffer9.limit());
                    break;
                case 8:
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer10 = this.a;
                    byteBuffer10.position(byteBuffer10.limit());
                    break;
                case 9:
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer11 = this.a;
                    byteBuffer11.position(byteBuffer11.limit());
                    break;
                case XmlPullParser.DOCDECL /* 10 */:
                    j8 ^= ((long) (byteBuffer2.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer12 = this.a;
                    byteBuffer12.position(byteBuffer12.limit());
                    break;
                case AndroidSdkVersion.HONEYCOMB /* 11 */:
                    j8 ^= ((long) (byteBuffer2.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer2.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer13 = this.a;
                    byteBuffer13.position(byteBuffer13.limit());
                    break;
                case 12:
                    j8 ^= ((long) (byteBuffer2.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer2.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer2.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer14 = this.a;
                    byteBuffer14.position(byteBuffer14.limit());
                    break;
                case 13:
                    j8 ^= ((long) (byteBuffer2.get(12) & 255)) << 32;
                    j8 ^= ((long) (byteBuffer2.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer2.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer2.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer15 = this.a;
                    byteBuffer15.position(byteBuffer15.limit());
                    break;
                case 14:
                    j8 ^= ((long) (byteBuffer2.get(13) & 255)) << 40;
                    j8 ^= ((long) (byteBuffer2.get(12) & 255)) << 32;
                    j8 ^= ((long) (byteBuffer2.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer2.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer2.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer16 = this.a;
                    byteBuffer16.position(byteBuffer16.limit());
                    break;
                case 15:
                    j8 = ((long) (byteBuffer2.get(14) & 255)) << 48;
                    j8 ^= ((long) (byteBuffer2.get(13) & 255)) << 40;
                    j8 ^= ((long) (byteBuffer2.get(12) & 255)) << 32;
                    j8 ^= ((long) (byteBuffer2.get(11) & 255)) << 24;
                    j8 ^= ((long) (byteBuffer2.get(10) & 255)) << 16;
                    j8 ^= ((long) (byteBuffer2.get(9) & 255)) << 8;
                    j8 ^= (long) (byteBuffer2.get(8) & 255);
                    j7 = byteBuffer2.getLong();
                    this.d = (Long.rotateLeft(j7 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
                    this.e ^= Long.rotateLeft(j8 * 5545529020109919103L, 33) * (-8663945395140668459L);
                    ByteBuffer byteBuffer17 = this.a;
                    byteBuffer17.position(byteBuffer17.limit());
                    break;
                default:
                    x01.a("Should never get here.");
                    return null;
            }
        }
        long j9 = this.d;
        long j10 = this.f;
        long j11 = j9 ^ j10;
        long j12 = j10 ^ this.e;
        long j13 = j11 + j12;
        long j14 = j12 + j13;
        long j15 = (j13 ^ (j13 >>> 33)) * (-49064778989728563L);
        long j16 = (j15 ^ (j15 >>> 33)) * (-4265267296055464877L);
        long j17 = (j14 ^ (j14 >>> 33)) * (-49064778989728563L);
        long j18 = (j17 ^ (j17 >>> 33)) * (-4265267296055464877L);
        long j19 = j18 ^ (j18 >>> 33);
        long j20 = (j16 ^ (j16 >>> 33)) + j19;
        this.d = j20;
        this.e = j19 + j20;
        byte[] bArrArray = ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.d).putLong(this.e).array();
        char[] cArr = AbstractC0912Vs.b;
        return new C0886Us(bArrArray);
    }

    public final void a(ByteBuffer byteBuffer) {
        long j = byteBuffer.getLong();
        long j2 = byteBuffer.getLong();
        long jRotateLeft = (Long.rotateLeft(j * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.d;
        this.d = jRotateLeft;
        long jRotateLeft2 = Long.rotateLeft(jRotateLeft, 27);
        long j3 = this.e;
        this.d = ((jRotateLeft2 + j3) * 5) + 1390208809;
        long jRotateLeft3 = (Long.rotateLeft(j2 * 5545529020109919103L, 33) * (-8663945395140668459L)) ^ j3;
        this.e = jRotateLeft3;
        this.e = ((Long.rotateLeft(jRotateLeft3, 31) + this.d) * 5) + 944331445;
        this.f += 16;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(byte b) {
        ByteBuffer byteBuffer;
        this.a.put(b);
        if (this.a.remaining() < 8) {
            this.a.flip();
            while (true) {
                int iRemaining = this.a.remaining();
                int i = this.c;
                byteBuffer = this.a;
                if (iRemaining < i) {
                    break;
                }
                a(byteBuffer);
            }
            byteBuffer.compact();
        }
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(int i) {
        ByteBuffer byteBuffer;
        this.a.putInt(i);
        if (this.a.remaining() < 8) {
            this.a.flip();
            while (true) {
                int iRemaining = this.a.remaining();
                int i2 = this.c;
                byteBuffer = this.a;
                if (iRemaining < i2) {
                    break;
                }
                a(byteBuffer);
            }
            byteBuffer.compact();
        }
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(long j) {
        ByteBuffer byteBuffer;
        this.a.putLong(j);
        if (this.a.remaining() < 8) {
            this.a.flip();
            while (true) {
                int iRemaining = this.a.remaining();
                int i = this.c;
                byteBuffer = this.a;
                if (iRemaining < i) {
                    break;
                }
                a(byteBuffer);
            }
            byteBuffer.compact();
        }
        return this;
    }

    @Override // com.android.tools.r8.internal.E
    public final InterfaceC0990Ys a(int i, byte[] bArr) {
        ByteBuffer byteBuffer;
        ByteBuffer byteBuffer2;
        ByteBuffer byteBuffer3;
        int i2 = 0;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr, 0, i).order(ByteOrder.LITTLE_ENDIAN);
        if (byteBufferOrder.remaining() <= this.a.remaining()) {
            this.a.put(byteBufferOrder);
            if (this.a.remaining() < 8) {
                this.a.flip();
                while (true) {
                    int iRemaining = this.a.remaining();
                    int i3 = this.c;
                    byteBuffer3 = this.a;
                    if (iRemaining < i3) {
                        break;
                    }
                    a(byteBuffer3);
                }
                byteBuffer3.compact();
            }
            return this;
        }
        int iPosition = this.b - this.a.position();
        while (true) {
            byteBuffer = this.a;
            if (i2 >= iPosition) {
                break;
            }
            byteBuffer.put(byteBufferOrder.get());
            i2++;
        }
        byteBuffer.flip();
        while (true) {
            int iRemaining2 = this.a.remaining();
            int i4 = this.c;
            byteBuffer2 = this.a;
            if (iRemaining2 < i4) {
                break;
            }
            a(byteBuffer2);
        }
        byteBuffer2.compact();
        while (byteBufferOrder.remaining() >= this.c) {
            a(byteBufferOrder);
        }
        this.a.put(byteBufferOrder);
        return this;
    }
}

package j$.util.stream;

import com.shadow.okhttp3.internal.http2.Http2;
import java.util.function.IntFunction;

/* renamed from: j$.util.stream.v2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0202v2 extends AbstractC0096a0 {
    final /* synthetic */ long m;
    final /* synthetic */ long n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0202v2(AbstractC0101b0 abstractC0101b0, int i, long j, long j2) {
        super(abstractC0101b0, i, 0);
        this.m = j;
        this.n = j2;
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U P(AbstractC0100b abstractC0100b, j$.util.U u) {
        long j;
        long j2;
        long jG = abstractC0100b.G(u);
        long j3 = this.n;
        if (jG > 0 && u.hasCharacteristics(Http2.INITIAL_MAX_FRAME_SIZE)) {
            j$.util.K k = (j$.util.K) abstractC0100b.X(u);
            long j4 = this.m;
            return new C0213x3(k, j4, A0.B(j4, j3));
        }
        if (EnumC0129g3.ORDERED.n(abstractC0100b.K())) {
            return ((M0) new B2(this, abstractC0100b, u, new C0118e2(1), this.m, this.n).invoke()).spliterator();
        }
        j$.util.K k2 = (j$.util.K) abstractC0100b.X(u);
        long j5 = this.m;
        if (j5 <= jG) {
            long jMin = jG - j5;
            if (j3 >= 0) {
                jMin = Math.min(j3, jMin);
            }
            j = jMin;
            j2 = 0;
        } else {
            j = j3;
            j2 = j5;
        }
        return new D3(k2, j2, j);
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        long jMin;
        long j;
        long jG = abstractC0100b.G(u);
        if (jG > 0 && u.hasCharacteristics(Http2.INITIAL_MAX_FRAME_SIZE)) {
            return A0.G(abstractC0100b, A0.C(abstractC0100b.J(), u, this.m, this.n), true);
        }
        if (!EnumC0129g3.ORDERED.n(abstractC0100b.K())) {
            j$.util.K k = (j$.util.K) abstractC0100b.X(u);
            long j2 = this.m;
            long j3 = this.n;
            if (j2 <= jG) {
                jMin = j3 >= 0 ? Math.min(j3, jG - j2) : jG - j2;
                j = 0;
            } else {
                jMin = j3;
                j = j2;
            }
            return A0.G(this, new D3(k, j, jMin), true);
        }
        return (M0) new B2(this, abstractC0100b, u, intFunction, this.m, this.n).invoke();
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        return new C0197u2(this, interfaceC0182r2);
    }
}

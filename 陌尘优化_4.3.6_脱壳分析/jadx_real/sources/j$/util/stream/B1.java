package j$.util.stream;

import java.util.concurrent.CountedCompleter;

/* loaded from: /workspace/unpacked/classes3.dex */
class B1 extends CountedCompleter {
    protected final M0 a;
    protected final int b;
    public final /* synthetic */ int c;
    private final Object d;

    public B1(M0 m0, Object obj, int i) {
        this.c = i;
        this.a = m0;
        this.b = 0;
        this.d = obj;
    }

    B1(B1 b1, M0 m0, int i, byte b) {
        super(b1);
        this.a = m0;
        this.b = i;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        B1 b1A = this;
        while (b1A.a.r() != 0) {
            b1A.setPendingCount(b1A.a.r() - 1);
            int i = 0;
            int iCount = 0;
            while (i < b1A.a.r() - 1) {
                B1 b1A2 = b1A.a(i, b1A.b + iCount);
                iCount = (int) (iCount + b1A2.a.count());
                b1A2.fork();
                i++;
            }
            b1A = b1A.a(i, b1A.b + iCount);
        }
        switch (b1A.c) {
            case 0:
                ((L0) b1A.a).d(b1A.d, b1A.b);
                break;
            default:
                b1A.a.j((Object[]) b1A.d, b1A.b);
                break;
        }
        b1A.propagateCompletion();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public B1(B1 b1, M0 m0, int i) {
        this(b1, m0, i, (byte) 0);
        this.c = 1;
        this.d = (Object[]) b1.d;
    }

    final B1 a(int i, int i2) {
        switch (this.c) {
            case 0:
                return new B1(this, ((L0) this.a).b(i), i2);
            default:
                return new B1(this, this.a.b(i), i2);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public B1(B1 b1, L0 l0, int i) {
        this(b1, l0, i, (byte) 0);
        this.c = 0;
        this.d = b1.d;
    }
}

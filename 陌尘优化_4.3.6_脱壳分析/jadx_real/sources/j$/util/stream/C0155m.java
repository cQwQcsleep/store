package j$.util.stream;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* renamed from: j$.util.stream.m, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0155m extends AbstractC0163n2 {
    public final /* synthetic */ int b;
    Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0155m(AbstractC0100b abstractC0100b, InterfaceC0182r2 interfaceC0182r2, int i) {
        super(interfaceC0182r2);
        this.b = i;
        this.c = abstractC0100b;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0155m(InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.b = 0;
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public void l() {
        switch (this.b) {
            case 0:
                this.c = null;
                this.a.l();
                break;
            default:
                super.l();
                break;
        }
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public void m(long j) {
        switch (this.b) {
            case 0:
                this.c = new HashSet();
                this.a.m(-1L);
                break;
            case 1:
            default:
                super.m(j);
                break;
            case 2:
                this.a.m(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.b) {
            case 0:
                if (!((HashSet) this.c).contains(obj)) {
                    ((HashSet) this.c).add(obj);
                    this.a.accept((InterfaceC0182r2) obj);
                    break;
                }
                break;
            case 1:
                ((Consumer) ((C0189t) this.c).n).accept(obj);
                this.a.accept((InterfaceC0182r2) obj);
                break;
            case 2:
                if (((Predicate) ((C0189t) this.c).n).test(obj)) {
                    this.a.accept((InterfaceC0182r2) obj);
                    break;
                }
                break;
            case 3:
                this.a.accept((InterfaceC0182r2) ((Function) ((C0189t) this.c).n).apply(obj));
                break;
            case 4:
                this.a.accept(((ToIntFunction) ((W) this.c).n).applyAsInt(obj));
                break;
            case 5:
                this.a.accept(((ToLongFunction) ((C0141j0) this.c).n).applyAsLong(obj));
                break;
            default:
                this.a.accept(((ToDoubleFunction) ((C0214y) this.c).n).applyAsDouble(obj));
                break;
        }
    }
}

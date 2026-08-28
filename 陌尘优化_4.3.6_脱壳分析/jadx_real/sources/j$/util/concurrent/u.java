package j$.util.concurrent;

/* loaded from: /workspace/unpacked/classes3.dex */
final class u extends ThreadLocal {
    @Override // java.lang.ThreadLocal
    protected final Object initialValue() {
        return new z(0);
    }
}

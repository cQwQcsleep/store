package j$.util.stream;

/* loaded from: /workspace/unpacked/classes3.dex */
final class L3 implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ Runnable b;

    L3(Runnable runnable, Runnable runnable2) {
        this.a = runnable;
        this.b = runnable2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable = this.b;
        try {
            this.a.run();
            runnable.run();
        } catch (Throwable th) {
            try {
                runnable.run();
            } catch (Throwable th2) {
                try {
                    th.addSuppressed(th2);
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}

package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LB extends AbstractC2323p90 {
    public int c;
    public final /* synthetic */ InterfaceC2635sr d;
    public final /* synthetic */ Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LB(Va0 va0, InterfaceC2635sr interfaceC2635sr, Va0 va1) {
        super(va0);
        this.d = interfaceC2635sr;
        this.e = va1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2323p90
    public final Object c(Object obj) throws Throwable {
        int i = this.c;
        int iB = 1;
        if (i != 0) {
            if (i != 1) {
                k2d.a("This coroutine had already completed");
                return null;
            }
            this.c = 2;
            AbstractC2579s90.a(obj);
            return obj;
        }
        this.c = 1;
        AbstractC2579s90.a(obj);
        KB.a((Object) this.d, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
        InterfaceC2635sr interfaceC2635sr = this.d;
        if (interfaceC2635sr != null) {
            if (interfaceC2635sr instanceof InterfaceC0418Cr) {
                iB = ((InterfaceC0418Cr) interfaceC2635sr).b();
            } else if (interfaceC2635sr instanceof InterfaceC1270cr) {
                iB = 0;
            } else if (!(interfaceC2635sr instanceof InterfaceC1439er)) {
                iB = 2;
            }
            if (iB != 2) {
                throw ((ClassCastException) KB.a((RuntimeException) new ClassCastException(interfaceC2635sr.getClass().getName().concat(" cannot be cast to kotlin.jvm.functions.Function2")), AbstractC2795uj0.class.getName()));
            }
        }
        return interfaceC2635sr.a(this.e, this);
    }
}

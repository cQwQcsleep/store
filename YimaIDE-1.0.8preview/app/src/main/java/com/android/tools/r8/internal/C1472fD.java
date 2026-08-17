package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1472fD implements Ai0 {
    public final C0692Ng b;

    public C1472fD(C0692Ng c0692Ng) {
        this.b = c0692Ng;
    }

    public static AbstractC3220zi0 a(C0692Ng c0692Ng, C0471Es c0471Es, Fj0 fj0, InterfaceC1386eD interfaceC1386eD) {
        AbstractC3220zi0 abstractC3220zi0A;
        Object objA = c0692Ng.a(new Fj0(interfaceC1386eD.value())).a();
        boolean zNullSafe = interfaceC1386eD.nullSafe();
        if (objA instanceof AbstractC3220zi0) {
            abstractC3220zi0A = (AbstractC3220zi0) objA;
        } else {
            if (!(objA instanceof Ai0)) {
                krd.a("Invalid attempt to bind an instance of ", objA.getClass().getName(), " as a @JsonAdapter for ", AbstractC1278d.c(fj0.b), ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                return null;
            }
            abstractC3220zi0A = ((Ai0) objA).a(c0471Es, fj0);
        }
        return (abstractC3220zi0A == null || !zNullSafe) ? abstractC3220zi0A : new C3134yi0(abstractC3220zi0A);
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        InterfaceC1386eD interfaceC1386eD = (InterfaceC1386eD) fj0.a.getAnnotation(InterfaceC1386eD.class);
        if (interfaceC1386eD == null) {
            return null;
        }
        return a(this.b, c0471Es, fj0, interfaceC1386eD);
    }
}

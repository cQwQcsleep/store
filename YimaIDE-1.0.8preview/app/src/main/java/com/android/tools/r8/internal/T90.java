package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2883vk0;
import com.android.tools.r8.internal.T90;
import com.android.tools.r8.naming.mappinginformation.e;
import com.android.tools.r8.retrace.RetraceUnknownJsonMappingInformationResult;
import com.android.tools.r8.retrace.RetraceUnknownMappingInformationElement;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T90 implements RetraceUnknownJsonMappingInformationResult {
    public final List a;

    public T90(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
    }

    public static T90 a(List list) {
        final C0473Eu c0473EuG = AbstractC0551Hu.g();
        list.forEach(new Consumer() { // from class: s0e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                T90.a(c0473EuG, (e) obj);
            }
        });
        return new T90(c0473EuG.a());
    }

    @Override // com.android.tools.r8.retrace.RetraceUnknownJsonMappingInformationResult
    public final Stream stream() {
        return this.a.stream().map(new Function() { // from class: t0e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C2883vk0) obj);
            }
        });
    }

    public final /* synthetic */ RetraceUnknownMappingInformationElement a(C2883vk0 c2883vk0) {
        return new U90(this, c2883vk0);
    }

    public static void a(C0473Eu c0473Eu, com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        if (eVar instanceof C2883vk0) {
            c0473Eu.a(eVar.k());
        }
    }
}

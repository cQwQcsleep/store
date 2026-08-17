package org.jetbrains.kotlin.incremental;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class IncrementalJvmCache$ConstantsMap$dumpValue$1 extends FunctionReferenceImpl implements Function1<Long, String> {
    public static final IncrementalJvmCache$ConstantsMap$dumpValue$1 INSTANCE = new IncrementalJvmCache$ConstantsMap$dumpValue$1();

    public IncrementalJvmCache$ConstantsMap$dumpValue$1() {
        super(1, Long.TYPE, "toString", "toString()Ljava/lang/String;", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).longValue());
    }

    public final String invoke(long j) {
        return String.valueOf(j);
    }
}

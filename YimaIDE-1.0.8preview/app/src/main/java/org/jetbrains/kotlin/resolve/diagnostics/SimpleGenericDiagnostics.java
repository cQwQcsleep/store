package org.jetbrains.kotlin.resolve.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.diagnostics.GenericDiagnostics;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\n\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00018\u00008\u00000\bH\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00018\u00008\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/diagnostics/SimpleGenericDiagnostics;", "T", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/GenericDiagnostics;", "diagnostics", "", "<init>", "(Ljava/util/Collection;)V", "Ljava/util/ArrayList;", "kotlin.jvm.PlatformType", "all", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public class SimpleGenericDiagnostics<T extends UnboundDiagnostic> implements GenericDiagnostics<T> {
    private final ArrayList<T> diagnostics;

    public SimpleGenericDiagnostics(Collection<? extends T> collection) {
        collection.getClass();
        this.diagnostics = new ArrayList<>(collection);
    }

    @Override // org.jetbrains.kotlin.resolve.diagnostics.Diagnostics
    public ArrayList<T> all() {
        return this.diagnostics;
    }
}

package org.jetbrains.kotlin.resolve.lazy;

import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtImportDirective;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/lazy/ImportForceResolver;", "", "forceResolveNonDefaultImports", "", "forceResolveImport", "importDirective", "Lorg/jetbrains/kotlin/psi/KtImportDirective;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface ImportForceResolver {
    void forceResolveImport(KtImportDirective importDirective);

    void forceResolveNonDefaultImports();
}

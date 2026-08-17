package org.jetbrains.kotlin.fir.resolve;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.resolve.calls.FirReceiversKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"ambiguityDiagnosticFor", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeSimpleDiagnostic;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "labelName", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitValueStorageKt {
    public static final ConeSimpleDiagnostic ambiguityDiagnosticFor(Set<? extends ImplicitReceiverValue<?>> set, String str) {
        set.getClass();
        Set<? extends ImplicitReceiverValue<?>> set2 = set;
        int i = 0;
        if (!(set2 instanceof Collection) || !set2.isEmpty()) {
            Iterator<T> it = set2.iterator();
            while (it.hasNext()) {
                if ((FirReceiversKt.getReferencedMemberSymbol((ImplicitReceiverValue) it.next()) instanceof FirAnonymousFunctionSymbol) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (i >= set.size() - 1) {
            return new ConeSimpleDiagnostic("Clashing this@" + str, DiagnosticKind.LabelNameClash);
        }
        return new ConeSimpleDiagnostic("Ambiguous this@" + str, DiagnosticKind.AmbiguousLabel);
    }
}

package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u000b\"!\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\"\u001b\u0010\n\u001a\u00020\u0001*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\n\u0010\r¨\u0006\u000e"}, d2 = {"allSuccessful", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "getAllSuccessful$annotations", "(Ljava/util/Collection;)V", "getAllSuccessful", "(Ljava/util/Collection;)Z", "anyUnsuccessful", "getAnyUnsuccessful", "isSuccess", "isSuccess$annotations", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;)V", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;)Z", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolutionDiagnosticKt {
    public static final boolean getAllSuccessful(Collection<? extends ResolutionDiagnostic> collection) {
        collection.getClass();
        Collection<? extends ResolutionDiagnostic> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            if (!CandidateApplicabilityKt.isSuccess(((ResolutionDiagnostic) it.next()).getApplicability())) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ void getAllSuccessful$annotations(Collection collection) {
    }

    public static final boolean getAnyUnsuccessful(Collection<? extends ResolutionDiagnostic> collection) {
        collection.getClass();
        return !getAllSuccessful(collection);
    }

    public static final boolean isSuccess(ResolutionDiagnostic resolutionDiagnostic) {
        resolutionDiagnostic.getClass();
        return CandidateApplicabilityKt.isSuccess(resolutionDiagnostic.getApplicability());
    }

    public static /* synthetic */ void isSuccess$annotations(ResolutionDiagnostic resolutionDiagnostic) {
    }
}

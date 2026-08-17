package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeIntegerOperatorConflictResolver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "<init>", "()V", "chooseMaximallySpecificCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeIntegerOperatorConflictResolver extends ConeCallConflictResolver {
    public static final ConeIntegerOperatorConflictResolver INSTANCE = new ConeIntegerOperatorConflictResolver();

    private ConeIntegerOperatorConflictResolver() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver
    public Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates) {
        Object next;
        candidates.getClass();
        if (candidates.size() > 1) {
            Iterator<T> it = candidates.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!FirIntegerConstantOperatorScopeKt.isWrappedIntegerOperator(((Candidate) next).getSymbol()));
            Candidate candidate = (Candidate) next;
            if (candidate != null) {
                return SetsKt.setOf(candidate);
            }
        }
        return candidates;
    }
}

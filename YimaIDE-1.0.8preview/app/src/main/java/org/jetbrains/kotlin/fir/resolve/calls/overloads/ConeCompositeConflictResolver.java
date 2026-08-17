package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016R\u0018\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCompositeConflictResolver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "conflictResolvers", Argument.Delimiters.none, "<init>", "([Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;)V", "[Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "chooseMaximallySpecificCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeCompositeConflictResolver extends ConeCallConflictResolver {
    private final ConeCallConflictResolver[] conflictResolvers;

    public ConeCompositeConflictResolver(ConeCallConflictResolver... coneCallConflictResolverArr) {
        coneCallConflictResolverArr.getClass();
        this.conflictResolvers = coneCallConflictResolverArr;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver
    public Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates) {
        candidates.getClass();
        if (candidates.size() <= 1) {
            return candidates;
        }
        int i = 0;
        while (candidates.size() > 1) {
            ConeCallConflictResolver[] coneCallConflictResolverArr = this.conflictResolvers;
            if (i >= coneCallConflictResolverArr.length) {
                break;
            }
            candidates = coneCallConflictResolverArr[i].chooseMaximallySpecificCandidates(candidates);
            i++;
        }
        return candidates;
    }
}

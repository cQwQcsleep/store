package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ReplOverloadCallConflictResolver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "<init>", "()V", "chooseMaximallySpecificCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReplOverloadCallConflictResolver extends ConeCallConflictResolver {
    public static final ReplOverloadCallConflictResolver INSTANCE = new ReplOverloadCallConflictResolver();

    private ReplOverloadCallConflictResolver() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver
    public Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates) {
        FirReplSnippetSymbol originalReplSnippetSymbol;
        candidates.getClass();
        Set<Candidate> set = candidates;
        Iterator<T> it = set.iterator();
        do {
            if (!it.hasNext()) {
                originalReplSnippetSymbol = null;
                break;
            }
            originalReplSnippetSymbol = DeclarationAttributesKt.getOriginalReplSnippetSymbol(((Candidate) it.next()).getSymbol().getFir());
        } while (originalReplSnippetSymbol == null);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : set) {
            FirReplSnippetSymbol originalReplSnippetSymbol2 = DeclarationAttributesKt.getOriginalReplSnippetSymbol(((Candidate) obj).getSymbol().getFir());
            if (originalReplSnippetSymbol2 == null || originalReplSnippetSymbol2 == originalReplSnippetSymbol) {
                linkedHashSet.add(obj);
            }
        }
        return linkedHashSet;
    }
}

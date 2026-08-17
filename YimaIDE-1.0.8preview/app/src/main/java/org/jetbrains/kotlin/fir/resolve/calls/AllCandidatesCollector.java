package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001e\u0010\b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/AllCandidatesCollector;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;)V", "allCandidatesMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "consumeCandidate", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "group", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "candidate", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "shouldStopAtTheGroup", Argument.Delimiters.none, "allCandidates", Argument.Delimiters.none, "getAllCandidates", "()Ljava/util/Collection;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AllCandidatesCollector extends CandidateCollector {
    private final Map<FirBasedSymbol<?>, Candidate> allCandidatesMap;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllCandidatesCollector(BodyResolveComponents bodyResolveComponents, ResolutionStageRunner resolutionStageRunner) {
        super(bodyResolveComponents, resolutionStageRunner);
        bodyResolveComponents.getClass();
        resolutionStageRunner.getClass();
        this.allCandidatesMap = new LinkedHashMap();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector
    public CandidateApplicability consumeCandidate(TowerGroup group, Candidate candidate, ResolutionContext context) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirBasedSymbol<?> symbol;
        TypeAliasConstructorInfo typeAliasConstructorInfo;
        FirConstructor firConstructor;
        group.getClass();
        candidate.getClass();
        context.getClass();
        FirDeclaration fir = candidate.getSymbol().getFir();
        FirConstructor firConstructor2 = fir instanceof FirConstructor ? (FirConstructor) fir : null;
        if (firConstructor2 == null || (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructor2)) == null || (firConstructor = (FirConstructor) typeAliasConstructorInfo.getOriginalConstructor()) == null || (symbol = firConstructor.getSymbol()) == null) {
            symbol = candidate.getSymbol();
        }
        Map<FirBasedSymbol<?>, Candidate> map = this.allCandidatesMap;
        if (map.get(symbol) == null) {
            map.put(symbol, candidate);
        }
        return super.consumeCandidate(group, candidate, context);
    }

    public final Collection<Candidate> getAllCandidates() {
        return this.allCandidatesMap.values();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector
    public boolean shouldStopAtTheGroup(TowerGroup group) {
        group.getClass();
        return false;
    }
}

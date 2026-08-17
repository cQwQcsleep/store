package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirTypeCandidateCollector;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapperKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002#$B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ(\u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ2\u0010\u001f\u001a\u00020\f*\b\u0012\u0002\b\u0003\u0018\u00010\u001a2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010 \u001a\u00020!J\u0016\u0010\"\u001a\u00020\u00182\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "resolveDeprecations", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;Z)V", "candidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "value", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "applicability", "getApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "processCandidate", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "isVisible", "getResult", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult;", "filterOutAmbiguousTypealiases", "TypeResolutionResult", "TypeCandidate", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeCandidateCollector {
    private CandidateApplicability applicability;
    private final Set<TypeCandidate> candidates;
    private final List<FirDeclaration> containingDeclarations;
    private final boolean resolveDeprecations;
    private final FirSession session;
    private final SupertypeSupplier supertypeSupplier;
    private final FirFile useSiteFile;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B9\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0082\u0004J\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004J\r\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0086\u0002J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0086\u0002R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCandidate;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "applicability", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "component1", "component2", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TypeCandidate extends AbstractCandidate {
        private final CandidateApplicability applicability;
        private final ConeDiagnostic diagnostic;
        private final FirResolvedSymbolOrigin resolvedSymbolOrigin;
        private final ConeSubstitutor substitutor;
        private final FirBasedSymbol<?> symbol;

        public TypeCandidate(FirBasedSymbol<?> firBasedSymbol, ConeSubstitutor coneSubstitutor, ConeDiagnostic coneDiagnostic, CandidateApplicability candidateApplicability, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
            firBasedSymbol.getClass();
            candidateApplicability.getClass();
            this.symbol = firBasedSymbol;
            this.substitutor = coneSubstitutor;
            this.diagnostic = coneDiagnostic;
            this.applicability = candidateApplicability;
            this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
        }

        public final FirBasedSymbol<?> component1() {
            return getSymbol();
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeSubstitutor getSubstitutor() {
            return this.substitutor;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TypeCandidate) && Intrinsics.areEqual(getSymbol(), ((TypeCandidate) other).getSymbol());
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate
        public CandidateApplicability getApplicability() {
            return this.applicability;
        }

        public final ConeDiagnostic getDiagnostic() {
            return this.diagnostic;
        }

        public final FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
            return this.resolvedSymbolOrigin;
        }

        public final ConeSubstitutor getSubstitutor() {
            return this.substitutor;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate
        public FirBasedSymbol<?> getSymbol() {
            return this.symbol;
        }

        public int hashCode() {
            return getSymbol().hashCode();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirTypeCandidateCollector(FirSession firSession, FirFile firFile, List<? extends FirDeclaration> list, SupertypeSupplier supertypeSupplier, boolean z) {
        firSession.getClass();
        list.getClass();
        supertypeSupplier.getClass();
        this.session = firSession;
        this.useSiteFile = firFile;
        this.containingDeclarations = list;
        this.supertypeSupplier = supertypeSupplier;
        this.resolveDeprecations = z;
        this.candidates = new LinkedHashSet();
    }

    public static boolean a(Set set, TypeCandidate typeCandidate) {
        ClassId classId;
        typeCandidate.getClass();
        FirBasedSymbol<?> symbol = typeCandidate.getSymbol();
        FirClassLikeSymbol firClassLikeSymbol = symbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) symbol : null;
        return (firClassLikeSymbol == null || (classId = firClassLikeSymbol.getClassId()) == null || !set.contains(classId)) ? false : true;
    }

    private final void filterOutAmbiguousTypealiases(Set<TypeCandidate> candidates) {
        ClassId classId;
        if (candidates.size() <= 1) {
            return;
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        Map<ClassId, ClassId> classTypealiasesThatDontCauseAmbiguity = FirPlatformClassMapperKt.getPlatformClassMapper(this.session).getClassTypealiasesThatDontCauseAmbiguity();
        Iterator<TypeCandidate> it = candidates.iterator();
        while (it.hasNext()) {
            FirBasedSymbol<?> symbol = it.next().getSymbol();
            if ((symbol instanceof FirClassLikeSymbol) && (classId = classTypealiasesThatDontCauseAmbiguity.get(((FirClassLikeSymbol) symbol).getClassId())) != null) {
                linkedHashSet.add(classId);
            }
        }
        if (linkedHashSet.isEmpty()) {
            return;
        }
        CollectionsKt.removeAll(candidates, new Function1() { // from class: jf5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirTypeCandidateCollector.a(linkedHashSet, (FirTypeCandidateCollector.TypeCandidate) obj));
            }
        });
    }

    private final boolean isVisible(FirBasedSymbol<?> firBasedSymbol, FirFile firFile, List<? extends FirDeclaration> list, SupertypeSupplier supertypeSupplier) {
        FirDeclaration fir = firBasedSymbol != null ? firBasedSymbol.getFir() : null;
        if (firFile == null || !(fir instanceof FirMemberDeclaration)) {
            return true;
        }
        return FirVisibilityChecker.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(this.session), (FirMemberDeclaration) fir, this.session, firFile, list, null, false, null, false, supertypeSupplier, 192, null);
    }

    public static /* synthetic */ void processCandidate$default(FirTypeCandidateCollector firTypeCandidateCollector, FirBasedSymbol firBasedSymbol, ConeSubstitutor coneSubstitutor, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            coneSubstitutor = null;
        }
        firTypeCandidateCollector.processCandidate(firBasedSymbol, coneSubstitutor, firResolvedSymbolOrigin);
    }

    public final CandidateApplicability getApplicability() {
        return this.applicability;
    }

    public final TypeResolutionResult getResult() {
        filterOutAmbiguousTypealiases(this.candidates);
        int size = this.candidates.size();
        if (size == 1) {
            return new TypeResolutionResult.Resolved((TypeCandidate) CollectionsKt.single(this.candidates));
        }
        return size > 1 ? new TypeResolutionResult.Ambiguity(CollectionsKt.toList(this.candidates)) : TypeResolutionResult.Unresolved.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0030 A[PHI: r0
      0x0030: PHI (r0v1 org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability) = 
      (r0v0 org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability)
      (r0v11 org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability)
     binds: [B:3:0x0010, B:9:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:23:0x0057  */
    public final void processCandidate(FirBasedSymbol<?> symbol, ConeSubstitutor substitutor, FirResolvedSymbolOrigin resolvedSymbolOrigin) {
        ConeVisibilityError coneVisibilityError;
        ConeVisibilityError coneVisibilityError2;
        symbol.getClass();
        CandidateApplicability candidateApplicability = CandidateApplicability.RESOLVED;
        if (isVisible(symbol, this.useSiteFile, this.containingDeclarations, this.supertypeSupplier)) {
            coneVisibilityError = null;
        } else {
            boolean z = CollectionsKt.getOrNull(this.containingDeclarations, 1) instanceof FirCodeFragment;
            candidateApplicability = (CandidateApplicability) ComparisonsKt.minOf(z ? CandidateApplicability.RESOLVED_LOW_PRIORITY : CandidateApplicability.K2_VISIBILITY_ERROR, candidateApplicability);
            if (z) {
                coneVisibilityError = null;
            } else {
                coneVisibilityError = new ConeVisibilityError(symbol);
            }
        }
        if (this.resolveDeprecations && DeprecationUtilsKt.isDeprecationLevelHidden(symbol, this.session)) {
            candidateApplicability = (CandidateApplicability) ComparisonsKt.minOf(CandidateApplicability.HIDDEN, candidateApplicability);
            coneVisibilityError2 = null;
        } else {
            coneVisibilityError2 = coneVisibilityError;
        }
        CandidateApplicability candidateApplicability2 = candidateApplicability;
        CandidateApplicability candidateApplicability3 = this.applicability;
        if (candidateApplicability3 != null) {
            candidateApplicability3.getClass();
            if (candidateApplicability2.compareTo(candidateApplicability3) > 0) {
                this.applicability = candidateApplicability2;
                this.candidates.clear();
            }
        } else {
            this.applicability = candidateApplicability2;
            this.candidates.clear();
        }
        if (candidateApplicability2 == this.applicability) {
            this.candidates.add(new TypeCandidate(symbol, substitutor, coneVisibilityError2, candidateApplicability2, resolvedSymbolOrigin));
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult;", Argument.Delimiters.none, "<init>", "()V", "resolvedCandidateOrNull", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "Ambiguity", "Unresolved", "Resolved", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult$Ambiguity;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult$Resolved;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult$Unresolved;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class TypeResolutionResult {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult$Ambiguity;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult;", "typeCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "<init>", "(Ljava/util/List;)V", "getTypeCandidates", "()Ljava/util/List;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Ambiguity extends TypeResolutionResult {
            private final List<TypeCandidate> typeCandidates;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Ambiguity(List<TypeCandidate> list) {
                super(null);
                list.getClass();
                this.typeCandidates = list;
            }

            public final List<TypeCandidate> getTypeCandidates() {
                return this.typeCandidates;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult$Resolved;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult;", "typeCandidate", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;)V", "getTypeCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Resolved extends TypeResolutionResult {
            private final TypeCandidate typeCandidate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Resolved(TypeCandidate typeCandidate) {
                super(null);
                typeCandidate.getClass();
                this.typeCandidate = typeCandidate;
            }

            public final TypeCandidate getTypeCandidate() {
                return this.typeCandidate;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult$Unresolved;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Unresolved extends TypeResolutionResult {
            public static final Unresolved INSTANCE = new Unresolved();

            private Unresolved() {
                super(null);
            }
        }

        public /* synthetic */ TypeResolutionResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TypeCandidate resolvedCandidateOrNull() {
            Resolved resolved = this instanceof Resolved ? (Resolved) this : null;
            if (resolved != null) {
                return resolved.getTypeCandidate();
            }
            return null;
        }

        private TypeResolutionResult() {
        }
    }

    public /* synthetic */ FirTypeCandidateCollector(FirSession firSession, FirFile firFile, List list, SupertypeSupplier supertypeSupplier, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firFile, list, (i & 8) != 0 ? SupertypeSupplier.Default.INSTANCE : supertypeSupplier, (i & 16) != 0 ? true : z);
    }
}

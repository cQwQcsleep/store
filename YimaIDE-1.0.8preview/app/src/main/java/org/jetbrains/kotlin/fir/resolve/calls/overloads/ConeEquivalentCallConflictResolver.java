package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeEquivalentCallConflictResolver;
import org.jetbrains.kotlin.fir.scopes.impl.FirStandardOverrideChecker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeEquivalentCallConflictResolver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "chooseMaximallySpecificCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "filterOutEquivalentCalls", Argument.Delimiters.none, "mappedArgumentsOrderRepresentation", Argument.Delimiters.none, "getMappedArgumentsOrderRepresentation", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)[I", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeEquivalentCallConflictResolver extends ConeCallConflictResolver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirSession session;

    public ConeEquivalentCallConflictResolver(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    public static boolean b(ConeEquivalentCallConflictResolver coneEquivalentCallConflictResolver, Candidate candidate, Candidate candidate2) {
        return Arrays.equals(coneEquivalentCallConflictResolver.getMappedArgumentsOrderRepresentation(candidate), coneEquivalentCallConflictResolver.getMappedArgumentsOrderRepresentation(candidate2));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final Set<Candidate> filterOutEquivalentCalls(Collection<Candidate> candidates) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<Candidate> listSortedWith = CollectionsKt.sortedWith(candidates, new Comparator() { // from class: org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeEquivalentCallConflictResolver$filterOutEquivalentCalls$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                FirSession.Kind kind = ((Candidate) t).getSymbol().getFir().getModuleData().getSession().getKind();
                FirSession.Kind kind2 = FirSession.Kind.Source;
                return ComparisonsKt.compareValues(Boolean.valueOf(kind != kind2), Boolean.valueOf(((Candidate) t2).getSymbol().getFir().getModuleData().getSession().getKind() != kind2));
            }
        });
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (final Candidate candidate : listSortedWith) {
            FirDeclaration fir = candidate.getSymbol().getFir();
            if (fir instanceof FirCallableDeclaration) {
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
                if (ClassMembersKt.containingClassLookupTag(firCallableDeclaration.getSymbol()) == null) {
                    Iterator it = linkedHashSet.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            final Candidate candidate2 = (Candidate) it.next();
                            FirDeclaration fir2 = candidate2.getSymbol().getFir();
                            if (fir2 instanceof FirCallableDeclaration) {
                                FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) fir2;
                                if (ClassMembersKt.containingClassLookupTag(firCallableDeclaration2.getSymbol()) == null && INSTANCE.areEquivalentTopLevelCallables(firCallableDeclaration, firCallableDeclaration2, this.session, new Function0() { // from class: tp2
                                    public final Object invoke() {
                                        return Boolean.valueOf(ConeEquivalentCallConflictResolver.b(this.b, candidate, candidate2));
                                    }
                                })) {
                                    if (!((FirMemberDeclaration) fir2).getStatus().isExpect() || ((FirMemberDeclaration) fir).getStatus().isExpect()) {
                                        break;
                                    }
                                    it.remove();
                                }
                            }
                        }
                    }
                }
            }
            linkedHashSet.add(candidate);
        }
        return linkedHashSet;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final int[] getMappedArgumentsOrderRepresentation(Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclaration fir = candidate.getSymbol().getFir();
        FirFunction firFunction = fir instanceof FirFunction ? (FirFunction) fir : null;
        if (firFunction == null) {
            return null;
        }
        List listPlus = CollectionsKt.plus(firFunction.getValueParameters(), firFunction.getContextParameters());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        int i = 0;
        int i2 = 0;
        for (Object obj : listPlus) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to((FirValueParameter) obj, Integer.valueOf(i2)));
            i2 = i3;
        }
        Map map = MapsKt.toMap(arrayList);
        if (!candidate.getArgumentMappingInitialized()) {
            return null;
        }
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping = candidate.getArgumentMapping();
        int size = argumentMapping.size() + 1;
        int[] iArr = new int[size];
        for (int i4 = 0; i4 < size; i4++) {
            iArr[i4] = firFunction.getValueParameters().size();
        }
        for (Object obj2 : argumentMapping.values()) {
            i++;
            obj2.getClass();
            Integer num = (Integer) map.get((FirValueParameter) obj2);
            if (num == null) {
                k2d.a("Unmapped argument in arguments mapping");
                return null;
            }
            iArr[i] = num.intValue();
        }
        return iArr;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver
    public Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates) {
        candidates.getClass();
        return filterOutEquivalentCalls(candidates);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeEquivalentCallConflictResolver$Companion;", Argument.Delimiters.none, "<init>", "()V", "areEquivalentTopLevelCallables", Argument.Delimiters.none, "first", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "second", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "argumentMappingIsEqual", "Lkotlin/Function0;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean areEquivalentTopLevelCallables(FirCallableDeclaration first, FirCallableDeclaration second, FirSession session, Function0<Boolean> argumentMappingIsEqual) {
            first.getClass();
            second.getClass();
            session.getClass();
            if (!Intrinsics.areEqual(first.getSymbol().getCallableId(), second.getSymbol().getCallableId())) {
                return false;
            }
            if ((Intrinsics.areEqual(first.getModuleData(), second.getModuleData()) && !first.getModuleData().getAreRedeclarationsEquivalent()) || (first instanceof FirVariable) != (second instanceof FirVariable)) {
                return false;
            }
            if (argumentMappingIsEqual != null && !((Boolean) argumentMappingIsEqual.invoke()).booleanValue()) {
                return false;
            }
            FirStandardOverrideChecker firStandardOverrideChecker = new FirStandardOverrideChecker(session);
            if ((first instanceof FirProperty) && (second instanceof FirProperty)) {
                return firStandardOverrideChecker.isOverriddenProperty(first, (FirProperty) second, true) && firStandardOverrideChecker.isOverriddenProperty(second, (FirProperty) first, true);
            }
            if ((first instanceof FirNamedFunction) && (second instanceof FirNamedFunction)) {
                FirNamedFunction firNamedFunction = (FirNamedFunction) first;
                FirNamedFunction firNamedFunction2 = (FirNamedFunction) second;
                if (firStandardOverrideChecker.isOverriddenFunction(firNamedFunction, firNamedFunction2, true) && firStandardOverrideChecker.isOverriddenFunction(firNamedFunction2, firNamedFunction, true)) {
                    return true;
                }
            }
            return false;
        }

        private Companion() {
        }
    }
}

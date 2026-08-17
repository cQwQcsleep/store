package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.impl.AbstractFirOverrideScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractOverrideCheckerKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JJ\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\t\"\f\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J<\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0013\"\f\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\f2\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u00132\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0019*\b\u0012\u0004\u0012\u0002H\u00190\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J#\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u0006\u0012\u0002\b\u00030\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0002¢\u0006\u0002\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirOverrideService;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "extractBothWaysOverridable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "overrider", "members", Argument.Delimiters.none, "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "selectMostSpecificMembers", Argument.Delimiters.none, "overridables", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "removeFlagged", Argument.Delimiters.none, "E", "flags", Argument.Delimiters.none, "compareTo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideService$MemberWithBaseScopeAndReturnType;", "other", "(Lorg/jetbrains/kotlin/fir/scopes/FirOverrideService$MemberWithBaseScopeAndReturnType;Lorg/jetbrains/kotlin/fir/scopes/FirOverrideService$MemberWithBaseScopeAndReturnType;)Ljava/lang/Integer;", "MemberWithBaseScopeAndReturnType", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOverrideService implements FirSessionComponent {
    private final FirSession session;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u000e\b\u0000\u0010\u0001 \u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirOverrideService$MemberWithBaseScopeAndReturnType;", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", Argument.Delimiters.none, "memberWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;)V", "getMemberWithBaseScope", "()Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "returnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getReturnType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MemberWithBaseScopeAndReturnType<D extends FirCallableSymbol<?>> {
        private final MemberWithBaseScope<D> memberWithBaseScope;
        private final ConeKotlinType returnType;

        /* JADX WARN: Multi-variable type inference failed */
        public MemberWithBaseScopeAndReturnType(MemberWithBaseScope<? extends D> memberWithBaseScope, ReturnTypeCalculator returnTypeCalculator) {
            memberWithBaseScope.getClass();
            returnTypeCalculator.getClass();
            this.memberWithBaseScope = memberWithBaseScope;
            FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnTypeOrNull = returnTypeCalculator.tryCalculateReturnTypeOrNull((FirCallableDeclaration) memberWithBaseScope.getMember().getFir());
            this.returnType = firResolvedTypeRefTryCalculateReturnTypeOrNull != null ? firResolvedTypeRefTryCalculateReturnTypeOrNull.getConeType() : null;
        }

        public final MemberWithBaseScope<D> getMemberWithBaseScope() {
            return this.memberWithBaseScope;
        }

        public final ConeKotlinType getReturnType() {
            return this.returnType;
        }
    }

    public FirOverrideService(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Integer compareTo(MemberWithBaseScopeAndReturnType<?> memberWithBaseScopeAndReturnType, MemberWithBaseScopeAndReturnType<?> memberWithBaseScopeAndReturnType2) {
        ConeKotlinType returnType;
        ConeKotlinType coneKotlinTypeSubstituteOrSelf;
        ConeKotlinType returnType2;
        int iIntValue;
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) memberWithBaseScopeAndReturnType.getMemberWithBaseScope().getMember().getFir();
        FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) memberWithBaseScopeAndReturnType2.getMemberWithBaseScope().getMember().getFir();
        Integer numCompare = Visibilities.INSTANCE.compare(firCallableDeclaration.getStatus().getVisibility(), firCallableDeclaration2.getStatus().getVisibility());
        boolean z = false;
        int iIntValue2 = numCompare != null ? numCompare.intValue() : 0;
        ConeSubstitutor coneSubstitutorBuildSubstitutorForOverridesCheck = FirAbstractOverrideCheckerKt.buildSubstitutorForOverridesCheck(firCallableDeclaration, firCallableDeclaration2, this.session);
        if (coneSubstitutorBuildSubstitutorForOverridesCheck == null || (returnType = memberWithBaseScopeAndReturnType.getReturnType()) == null || (coneKotlinTypeSubstituteOrSelf = coneSubstitutorBuildSubstitutorForOverridesCheck.substituteOrSelf(returnType)) == null || (returnType2 = memberWithBaseScopeAndReturnType2.getReturnType()) == null) {
            return null;
        }
        TypeCheckerState typeCheckerStateNewTypeCheckerState = TypeComponentsKt.getTypeContext(this.session).newTypeCheckerState(false, false, false);
        AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
        boolean zIsSubtypeOf$default = AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, typeCheckerStateNewTypeCheckerState, coneKotlinTypeSubstituteOrSelf, returnType2, false, 8, (Object) null);
        boolean zIsSubtypeOf$default2 = AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, typeCheckerStateNewTypeCheckerState, returnType2, coneKotlinTypeSubstituteOrSelf, false, 8, (Object) null);
        if (zIsSubtypeOf$default && zIsSubtypeOf$default2) {
            Integer numCompareTo$merge = compareTo$merge(!(coneKotlinTypeSubstituteOrSelf instanceof ConeFlexibleType), !(returnType2 instanceof ConeFlexibleType), iIntValue2);
            if (numCompareTo$merge == null) {
                return null;
            }
            iIntValue = numCompareTo$merge.intValue();
        } else {
            if (!zIsSubtypeOf$default || iIntValue2 < 0) {
                if (zIsSubtypeOf$default2 && iIntValue2 <= 0) {
                    iIntValue = -1;
                }
                return null;
            }
            iIntValue = 1;
        }
        if (firCallableDeclaration instanceof FirNamedFunction) {
            if (firCallableDeclaration2 instanceof FirNamedFunction) {
                return Integer.valueOf(iIntValue);
            }
            dt1.a("b is ", firCallableDeclaration2.getClass());
            return null;
        }
        if (!(firCallableDeclaration instanceof FirVariable)) {
            z01.a("Unexpected callable: ", firCallableDeclaration.getClass());
            return null;
        }
        if (!(firCallableDeclaration2 instanceof FirVariable)) {
            dt1.a("b is ", firCallableDeclaration2.getClass());
            return null;
        }
        FirVariable firVariable = (FirVariable) firCallableDeclaration;
        if (firVariable.getIsVar() && !zIsSubtypeOf$default) {
            return null;
        }
        FirVariable firVariable2 = (FirVariable) firCallableDeclaration2;
        if (firVariable2.getIsVar() && !zIsSubtypeOf$default2) {
            return null;
        }
        boolean z2 = (firCallableDeclaration instanceof FirProperty) && DeclarationAttributesKt.getHasExplicitBackingField((FirProperty) firCallableDeclaration);
        if ((firCallableDeclaration2 instanceof FirProperty) && DeclarationAttributesKt.getHasExplicitBackingField((FirProperty) firCallableDeclaration2)) {
            z = true;
        }
        Integer numCompareTo$merge2 = compareTo$merge(z2, z, iIntValue);
        if (numCompareTo$merge2 != null) {
            return compareTo$merge(firVariable.getIsVar(), firVariable2.getIsVar(), numCompareTo$merge2.intValue());
        }
        return null;
    }

    private static final Integer compareTo$merge(boolean z, boolean z2, int i) {
        if (z == z2) {
            return Integer.valueOf(i);
        }
        if (!z || i < 0) {
            return (!z2 || i > 0) ? null : -1;
        }
        return 1;
    }

    private final <E> void removeFlagged(List<E> list, boolean[] zArr) {
        int length = zArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!zArr[i2]) {
                list.set(i, list.get(i2));
                i++;
            }
        }
        while (list.size() > i) {
            CollectionsKt.removeLast(list);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends FirCallableSymbol<?>> List<MemberWithBaseScope<D>> extractBothWaysOverridable(MemberWithBaseScope<? extends D> overrider, Collection<MemberWithBaseScope<D>> members, FirOverrideChecker overrideChecker) {
        overrider.getClass();
        members.getClass();
        overrideChecker.getClass();
        ArrayList arrayList = new ArrayList();
        arrayList.add(overrider);
        Iterator<MemberWithBaseScope<D>> it = members.iterator();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) overrider.getMember().getFir();
        while (it.hasNext()) {
            MemberWithBaseScope<D> next = it.next();
            if (Intrinsics.areEqual(next, overrider)) {
                it.remove();
            } else if (AbstractFirOverrideScopeKt.similarFunctionsOrBothProperties(overrideChecker, firCallableDeclaration, (FirCallableDeclaration) next.getMember().getFir())) {
                arrayList.add(next);
                it.remove();
            }
        }
        return arrayList;
    }

    public final FirSession getSession() {
        return this.session;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0054 A[PHI: r7
      0x0054: PHI (r7v2 boolean) = (r7v1 boolean), (r7v4 boolean) binds: [B:13:0x0047, B:17:0x0050] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends FirCallableSymbol<?>> List<MemberWithBaseScope<D>> selectMostSpecificMembers(List<? extends MemberWithBaseScope<? extends D>> overridables, ReturnTypeCalculator returnTypeCalculator) {
        boolean z;
        overridables.getClass();
        returnTypeCalculator.getClass();
        if (overridables.isEmpty()) {
            w01.a("Should have at least one overridable symbol");
            return null;
        }
        if (overridables.size() == 1) {
            return overridables;
        }
        ArrayList arrayList = new ArrayList(2);
        Iterator it = overridables.iterator();
        while (it.hasNext()) {
            MemberWithBaseScopeAndReturnType<?> memberWithBaseScopeAndReturnType = new MemberWithBaseScopeAndReturnType<>((MemberWithBaseScope) it.next(), returnTypeCalculator);
            int size = arrayList.size();
            boolean[] zArr = new boolean[size];
            boolean z2 = false;
            for (int i = 0; i < size; i++) {
                Integer numCompareTo = compareTo((MemberWithBaseScopeAndReturnType) arrayList.get(i), memberWithBaseScopeAndReturnType);
                if (numCompareTo == null) {
                    z = false;
                } else {
                    int iIntValue = numCompareTo.intValue();
                    if (iIntValue >= 0) {
                        z2 = true;
                    }
                    if (iIntValue < 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                zArr[i] = z;
            }
            removeFlagged(arrayList, zArr);
            if (!z2) {
                arrayList.add(memberWithBaseScopeAndReturnType);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((MemberWithBaseScopeAndReturnType) it2.next()).getMemberWithBaseScope());
        }
        return arrayList2;
    }
}

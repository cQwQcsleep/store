package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CollectTypeVariableUsagesInfo;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeParameterBasedTypeVariable;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeDeclaredUpperBoundConstraintPosition;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableTypeConstructor;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintKind;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J.\u0010\u0014\u001a\u00020\u000e*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u000eH\u0002J(\u0010\u001d\u001a\u00020\u000e*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00102\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0002JF\u0010!\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\"0\u0012*\u00020\u00152\u0006\u0010\u001e\u001a\u00020#2\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\"0\u0012H\u0002J8\u0010%\u001a\u00020\u000e*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00192\u001a\u0010'\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\"0\u0012H\u0002J\u0016\u0010(\u001a\u0004\u0018\u00010#*\u00020\u00152\u0006\u0010)\u001a\u00020\u0019H\u0002J\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00190\u0012*\u00020\u00152\u0006\u0010\u001e\u001a\u00020#H\u0002J\f\u0010+\u001a\u00020\u0005*\u00020,H\u0002¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CollectTypeVariableUsagesInfo;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isContainedInInvariantOrContravariantPositionsAmongTypeParameters", Argument.Delimiters.none, "checkingTypeVariable", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeParameterBasedTypeVariable;", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "isContainedInInvariantOrContravariantPositions", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "variableTypeConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "baseType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "wasOutVariance", "isContainedInInvariantOrContravariantPositionsWithDependencies", "variable", "candidateSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getDependentTypeParameters", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "dependentTypeParametersSeen", "isContainedInInvariantOrContravariantPositionsAmongUpperBound", "checkingType", "dependentTypeParameters", "getTypeParameterByVariable", "typeConstructor", "getDependingOnTypeParameter", "recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CollectTypeVariableUsagesInfo extends ResolutionStage {
    public static final CollectTypeVariableUsagesInfo INSTANCE = new CollectTypeVariableUsagesInfo();

    private CollectTypeVariableUsagesInfo() {
    }

    public static Iterable b(final NewConstraintSystemImpl newConstraintSystemImpl, final TypeConstructorMarker typeConstructorMarker, Map.Entry entry) {
        Pair pair;
        Object next;
        KotlinTypeMarker type;
        entry.getClass();
        TypeConstructorMarker typeConstructorMarker2 = (TypeConstructorMarker) entry.getKey();
        VariableWithConstraints variableWithConstraints = (VariableWithConstraints) entry.getValue();
        if (!(typeConstructorMarker2 instanceof ConeTypeVariableTypeConstructor)) {
            w01.a("Failed requirement.");
            return null;
        }
        List constraints = variableWithConstraints.getConstraints();
        ArrayList arrayList = new ArrayList();
        for (Object obj : constraints) {
            Constraint constraint = (Constraint) obj;
            if ((constraint.getPosition().getFrom() instanceof ConeDeclaredUpperBoundConstraintPosition) && constraint.getKind() == ConstraintKind.UPPER) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(newConstraintSystemImpl.typeConstructor(((Constraint) it.next()).getType()), typeConstructorMarker)) {
                pair = TuplesKt.to(typeConstructorMarker2, null);
            } else {
                Iterator it2 = arrayList.iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (!newConstraintSystemImpl.contains(((Constraint) next).getType(), new Function1() { // from class: m52
                    public final Object invoke(Object obj2) {
                        return Boolean.valueOf(CollectTypeVariableUsagesInfo.getDependentTypeParameters$lambda$0$1$0$0(newConstraintSystemImpl, typeConstructorMarker, (KotlinTypeMarker) obj2));
                    }
                }));
                Constraint constraint2 = (Constraint) next;
                ConeKotlinType coneKotlinType = (constraint2 == null || (type = constraint2.getType()) == null) ? null : (ConeKotlinType) type;
                pair = coneKotlinType != null ? TuplesKt.to(typeConstructorMarker2, coneKotlinType) : null;
            }
            if (pair != null) {
                arrayList2.add(pair);
            }
        }
        return arrayList2;
    }

    public static boolean d(List list, TypeConstructorMarker typeConstructorMarker, Pair pair) {
        pair.getClass();
        return (list.contains(pair) || Intrinsics.areEqual(pair.getFirst(), typeConstructorMarker)) ? false : true;
    }

    private final List<Pair<ConeTypeVariableTypeConstructor, ConeKotlinType>> getDependentTypeParameters(final NewConstraintSystemImpl newConstraintSystemImpl, final TypeConstructorMarker typeConstructorMarker, final List<? extends Pair<? extends TypeConstructorMarker, ? extends ConeKotlinType>> list) {
        List list2 = SequencesKt.toList(SequencesKt.filter(SequencesKt.flatMapIterable(MapsKt.asSequence(newConstraintSystemImpl.getBuilder().currentStorage().getNotFixedTypeVariables()), new Function1() { // from class: o52
            public final Object invoke(Object obj) {
                return CollectTypeVariableUsagesInfo.b(newConstraintSystemImpl, typeConstructorMarker, (Map.Entry) obj);
            }
        }), new Function1() { // from class: p52
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CollectTypeVariableUsagesInfo.d(list, typeConstructorMarker, (Pair) obj));
            }
        }));
        List list3 = list2;
        SmartList smartList = new SmartList();
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor = (ConeTypeVariableTypeConstructor) ((Pair) it.next()).component1();
            CollectionsKt.addAll(smartList, !Intrinsics.areEqual(coneTypeVariableTypeConstructor, typeConstructorMarker) ? INSTANCE.getDependentTypeParameters(newConstraintSystemImpl, coneTypeVariableTypeConstructor, CollectionsKt.plus(list3, list)) : CollectionsKt.emptyList());
        }
        return CollectionsKt.plus(list3, smartList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List getDependentTypeParameters$default(CollectTypeVariableUsagesInfo collectTypeVariableUsagesInfo, NewConstraintSystemImpl newConstraintSystemImpl, TypeConstructorMarker typeConstructorMarker, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        return collectTypeVariableUsagesInfo.getDependentTypeParameters(newConstraintSystemImpl, typeConstructorMarker, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getDependentTypeParameters$lambda$0$1$0$0(NewConstraintSystemImpl newConstraintSystemImpl, TypeConstructorMarker typeConstructorMarker, KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return Intrinsics.areEqual(newConstraintSystemImpl.typeConstructor(kotlinTypeMarker), typeConstructorMarker);
    }

    private final List<ConeTypeVariableTypeConstructor> getDependingOnTypeParameter(NewConstraintSystemImpl newConstraintSystemImpl, TypeConstructorMarker typeConstructorMarker) {
        List<Constraint> constraints;
        VariableWithConstraints variableWithConstraints = (VariableWithConstraints) newConstraintSystemImpl.getBuilder().currentStorage().getNotFixedTypeVariables().get(typeConstructorMarker);
        if (variableWithConstraints == null || (constraints = variableWithConstraints.getConstraints()) == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Constraint constraint : constraints) {
            ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor = null;
            if ((constraint.getPosition().getFrom() instanceof ConeDeclaredUpperBoundConstraintPosition) && constraint.getKind() == ConstraintKind.UPPER) {
                TypeConstructorMarker typeConstructorMarkerTypeConstructor = newConstraintSystemImpl.typeConstructor(constraint.getType());
                if (typeConstructorMarkerTypeConstructor instanceof ConeTypeVariableTypeConstructor) {
                    coneTypeVariableTypeConstructor = (ConeTypeVariableTypeConstructor) typeConstructorMarkerTypeConstructor;
                }
            }
            if (coneTypeVariableTypeConstructor != null) {
                arrayList.add(coneTypeVariableTypeConstructor);
            }
        }
        return arrayList;
    }

    private final TypeConstructorMarker getTypeParameterByVariable(NewConstraintSystemImpl newConstraintSystemImpl, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor) {
        FirTypeParameterSymbol typeParameterSymbol;
        Object obj = newConstraintSystemImpl.getBuilder().currentStorage().getAllTypeVariables().get(coneTypeVariableTypeConstructor);
        ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable = obj instanceof ConeTypeParameterBasedTypeVariable ? (ConeTypeParameterBasedTypeVariable) obj : null;
        if (coneTypeParameterBasedTypeVariable == null || (typeParameterSymbol = coneTypeParameterBasedTypeVariable.getTypeParameterSymbol()) == null) {
            return null;
        }
        return typeParameterSymbol.getLookupTag();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isContainedInInvariantOrContravariantPositions(NewConstraintSystemImpl newConstraintSystemImpl, FirSession firSession, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, ConeKotlinType coneKotlinType, boolean z) {
        TypeConstructorMarker typeParameterByVariable;
        FirClassLikeSymbol<?> symbol;
        FirClassLikeDeclaration firClassLikeDeclaration;
        ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor2 = coneTypeVariableTypeConstructor;
        if ((coneKotlinType instanceof ConeClassLikeType) && (typeParameterByVariable = getTypeParameterByVariable(newConstraintSystemImpl, coneTypeVariableTypeConstructor2)) != null && (symbol = ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) coneKotlinType).getLookupTag(), firSession)) != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) != null) {
            List<FirTypeParameterRef> typeParameters = firClassLikeDeclaration.getTypeParameters();
            if (typeParameters.size() < coneKotlinType.getTypeArguments().length) {
                return false;
            }
            ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
            int length = typeArguments.length;
            int i = 0;
            while (i < length) {
                ConeTypeProjection coneTypeProjection = typeArguments[i];
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                if (type != null && !ConeTypeUtilsKt.isMarkedNullable(type)) {
                    boolean z2 = z && (((FirTypeParameter) typeParameters.get(i).getSymbol().getFir()).getVariance() == Variance.OUT_VARIANCE || coneTypeProjection.getKind() == ProjectionKind.OUT);
                    TypeConstructorMarker typeConstructorMarkerTypeConstructor = newConstraintSystemImpl.typeConstructor(type);
                    if (((Intrinsics.areEqual(typeConstructorMarkerTypeConstructor, typeParameterByVariable) || Intrinsics.areEqual(typeConstructorMarkerTypeConstructor, coneTypeVariableTypeConstructor2)) && !z2) || isContainedInInvariantOrContravariantPositions(newConstraintSystemImpl, firSession, coneTypeVariableTypeConstructor2, type, z2)) {
                        return true;
                    }
                }
                i++;
                coneTypeVariableTypeConstructor2 = coneTypeVariableTypeConstructor;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean isContainedInInvariantOrContravariantPositions$default(CollectTypeVariableUsagesInfo collectTypeVariableUsagesInfo, NewConstraintSystemImpl newConstraintSystemImpl, FirSession firSession, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return collectTypeVariableUsagesInfo.isContainedInInvariantOrContravariantPositions(newConstraintSystemImpl, firSession, coneTypeVariableTypeConstructor, coneKotlinType, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isContainedInInvariantOrContravariantPositionsAmongTypeParameters(ConeTypeParameterBasedTypeVariable checkingTypeVariable, List<? extends FirTypeParameterRef> typeParameters) {
        List<? extends FirTypeParameterRef> list = typeParameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (FirTypeParameterRef firTypeParameterRef : list) {
            if (((FirTypeParameter) firTypeParameterRef.getSymbol().getFir()).getVariance() != Variance.OUT_VARIANCE && Intrinsics.areEqual(firTypeParameterRef.getSymbol(), checkingTypeVariable.getTypeParameterSymbol())) {
                return true;
            }
        }
        return false;
    }

    private final boolean isContainedInInvariantOrContravariantPositionsAmongUpperBound(NewConstraintSystemImpl newConstraintSystemImpl, FirSession firSession, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, List<? extends Pair<ConeTypeVariableTypeConstructor, ? extends ConeKotlinType>> list) {
        List<? extends Pair<ConeTypeVariableTypeConstructor, ? extends ConeKotlinType>> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor2 = coneTypeVariableTypeConstructor;
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor3 = (ConeTypeVariableTypeConstructor) pair.component1();
            ConeKotlinType coneKotlinType = (ConeKotlinType) pair.component2();
            if (coneKotlinType == null) {
                return true;
            }
            NewConstraintSystemImpl newConstraintSystemImpl2 = newConstraintSystemImpl;
            FirSession firSession2 = firSession;
            if (isContainedInInvariantOrContravariantPositions$default(INSTANCE, newConstraintSystemImpl2, firSession2, coneTypeVariableTypeConstructor2, coneKotlinType, false, 8, null)) {
                return true;
            }
            coneTypeVariableTypeConstructor2 = coneTypeVariableTypeConstructor3;
            newConstraintSystemImpl = newConstraintSystemImpl2;
            firSession = firSession2;
        }
        return false;
    }

    private final boolean isContainedInInvariantOrContravariantPositionsWithDependencies(final NewConstraintSystemImpl newConstraintSystemImpl, FirSession firSession, ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable, FirCallableSymbol<?> firCallableSymbol) {
        FirResolvedTypeRef returnTypeRef = ((FirCallableDeclaration) firCallableSymbol.getFir()).getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeKotlinType coneKotlinType = coneType == null ? null : coneType;
        if (coneKotlinType == null) {
            return false;
        }
        ConeTypeVariableTypeConstructor typeConstructor = coneTypeParameterBasedTypeVariable.getTypeConstructor();
        FirSession firSession2 = firSession;
        if (isContainedInInvariantOrContravariantPositions$default(this, newConstraintSystemImpl, firSession2, typeConstructor, coneKotlinType, false, 8, null)) {
            return true;
        }
        List<ConeTypeVariableTypeConstructor> dependingOnTypeParameter = getDependingOnTypeParameter(newConstraintSystemImpl, typeConstructor);
        if (!(dependingOnTypeParameter instanceof Collection) || !dependingOnTypeParameter.isEmpty()) {
            Iterator<T> it = dependingOnTypeParameter.iterator();
            while (it.hasNext()) {
                boolean zIsContainedInInvariantOrContravariantPositions$default = isContainedInInvariantOrContravariantPositions$default(INSTANCE, newConstraintSystemImpl, firSession2, (ConeTypeVariableTypeConstructor) it.next(), coneKotlinType, false, 8, null);
                ConeKotlinType coneKotlinType2 = coneKotlinType;
                FirSession firSession3 = firSession2;
                if (zIsContainedInInvariantOrContravariantPositions$default) {
                    return true;
                }
                firSession2 = firSession3;
                coneKotlinType = coneKotlinType2;
            }
        }
        ConeKotlinType coneKotlinType3 = coneKotlinType;
        FirSession firSession4 = firSession2;
        List<? extends Pair<ConeTypeVariableTypeConstructor, ? extends ConeKotlinType>> dependentTypeParameters$default = getDependentTypeParameters$default(this, newConstraintSystemImpl, typeConstructor, null, 2, null);
        List<? extends Pair<ConeTypeVariableTypeConstructor, ? extends ConeKotlinType>> list = dependentTypeParameters$default;
        boolean z = list instanceof Collection;
        if (!z || !list.isEmpty()) {
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                FirSession firSession5 = firSession4;
                ConeKotlinType coneKotlinType4 = coneKotlinType3;
                if (isContainedInInvariantOrContravariantPositions$default(INSTANCE, newConstraintSystemImpl, firSession5, (ConeTypeVariableTypeConstructor) ((Pair) it2.next()).getFirst(), coneKotlinType4, false, 8, null)) {
                    return true;
                }
                coneKotlinType3 = coneKotlinType4;
                firSession4 = firSession5;
            }
        }
        FirSession firSession6 = firSession4;
        ConeKotlinType coneKotlinType5 = coneKotlinType3;
        if (!isContainedInInvariantOrContravariantPositionsAmongUpperBound(newConstraintSystemImpl, firSession6, typeConstructor, dependentTypeParameters$default)) {
            return false;
        }
        if (z && list.isEmpty()) {
            return false;
        }
        Iterator<T> it3 = list.iterator();
        while (it3.hasNext()) {
            final ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor = (ConeTypeVariableTypeConstructor) ((Pair) it3.next()).component1();
            if (newConstraintSystemImpl.contains(coneKotlinType5, new Function1() { // from class: n52
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CollectTypeVariableUsagesInfo.isContainedInInvariantOrContravariantPositionsWithDependencies$lambda$2$0(newConstraintSystemImpl, coneTypeVariableTypeConstructor, (KotlinTypeMarker) obj));
                }
            })) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isContainedInInvariantOrContravariantPositionsWithDependencies$lambda$2$0(NewConstraintSystemImpl newConstraintSystemImpl, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return Intrinsics.areEqual(TypeSystemContextHelpersKt.typeConstructor(kotlinTypeMarker, newConstraintSystemImpl), INSTANCE.getTypeParameterByVariable(newConstraintSystemImpl, coneTypeVariableTypeConstructor)) && !newConstraintSystemImpl.isMarkedNullable(kotlinTypeMarker);
    }

    private final void recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter(ConeTypeVariable coneTypeVariable) {
        coneTypeVariable.getTypeConstructor().recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        if (symbol instanceof FirConstructorSymbol) {
            List<FirTypeParameterRef> typeParameters = ((FirConstructor) ((FirConstructorSymbol) symbol).getFir()).getTypeParameters();
            for (ConeTypeVariable coneTypeVariable : candidate.getFreshVariables()) {
                if ((coneTypeVariable instanceof ConeTypeParameterBasedTypeVariable) && isContainedInInvariantOrContravariantPositionsAmongTypeParameters((ConeTypeParameterBasedTypeVariable) coneTypeVariable, typeParameters)) {
                    recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter(coneTypeVariable);
                }
            }
        } else if (symbol instanceof FirCallableSymbol) {
            FirSession session = resolutionContext.getSession();
            for (ConeTypeVariable coneTypeVariable2 : candidate.getFreshVariables()) {
                if ((coneTypeVariable2 instanceof ConeTypeParameterBasedTypeVariable) && isContainedInInvariantOrContravariantPositionsWithDependencies(candidate.getSystem(), session, (ConeTypeParameterBasedTypeVariable) coneTypeVariable2, (FirCallableSymbol) symbol)) {
                    recordInfoAboutTypeVariableUsagesAsInvariantOrContravariantParameter(coneTypeVariable2);
                }
            }
        }
        return Unit.INSTANCE;
    }
}

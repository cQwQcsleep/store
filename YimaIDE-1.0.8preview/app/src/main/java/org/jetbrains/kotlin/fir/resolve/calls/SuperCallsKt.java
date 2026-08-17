package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.SuperCallsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a(\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a(\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0015H\u0002\u001aC\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u000e2\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00130\u001cH\u0082\b\u001a\"\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0013*\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0015H\u0002\u001a\"\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0013*\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0015H\u0002\u001a\u001c\u0010\"\u001a\u00020\u000e*\u00020\u00032\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u001dH\u0002\"*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"findTypesForSuperCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "superTypeRefs", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "containingCall", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "ARITY_OF_METHODS_OF_ANY", "Ljava/util/HashMap;", Argument.Delimiters.none, Argument.Delimiters.none, "Lkotlin/collections/HashMap;", "isCallingMethodOfAny", Argument.Delimiters.none, "callExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "resolveSupertypesForMethodOfAny", "supertypes", Argument.Delimiters.none, "calleeName", "Lorg/jetbrains/kotlin/name/Name;", "resolveSupertypesByCalleeName", "resolveSupertypesByPropertyName", "propertyName", "resolveSupertypesByMembers", "allowNonConcreteInterfaceMembers", "getMembers", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getFunctionMembers", ModuleXmlParser.TYPE, ModuleXmlParser.NAME, "getPropertyMembers", "isConcreteMember", "supertype", "member", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SuperCallsKt {
    private static final HashMap<String, Integer> ARITY_OF_METHODS_OF_ANY = MapsKt.hashMapOf(new Pair[]{TuplesKt.to("hashCode", 0), TuplesKt.to("equals", 1), TuplesKt.to("toString", 0)});

    public static final List<ConeKotlinType> findTypesForSuperCandidates(BodyResolveComponents bodyResolveComponents, List<? extends FirTypeRef> list, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        Name name;
        bodyResolveComponents.getClass();
        list.getClass();
        firQualifiedAccessExpression.getClass();
        List<? extends FirTypeRef> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) it.next();
            firResolvedTypeRef.getClass();
            arrayList.add(firResolvedTypeRef.getConeType());
        }
        boolean z = firQualifiedAccessExpression instanceof FirFunctionCall;
        boolean z2 = z && isCallingMethodOfAny((FirFunctionCall) firQualifiedAccessExpression);
        if (arrayList.size() <= 1 && !z2) {
            return arrayList;
        }
        if (z) {
            Name name2 = ((FirFunctionCall) firQualifiedAccessExpression).getCalleeReference().getName();
            return z2 ? resolveSupertypesForMethodOfAny(bodyResolveComponents, arrayList, name2) : resolveSupertypesByCalleeName(bodyResolveComponents, arrayList, name2);
        }
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        FirNamedReference firNamedReference = calleeReference instanceof FirNamedReference ? (FirNamedReference) calleeReference : null;
        return (firNamedReference == null || (name = firNamedReference.getName()) == null) ? CollectionsKt.emptyList() : resolveSupertypesByPropertyName(bodyResolveComponents, arrayList, name);
    }

    private static final Collection<FirCallableDeclaration> getFunctionMembers(BodyResolveComponents bodyResolveComponents, ConeKotlinType coneKotlinType, Name name) {
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(bodyResolveComponents, coneKotlinType, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        if (firTypeScopeScope != null) {
            firTypeScopeScope.processFunctionsByName(name, new Function1() { // from class: std
                public final Object invoke(Object obj) {
                    return SuperCallsKt.getFunctionMembers$lambda$0$0(listCreateListBuilder, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getFunctionMembers$lambda$0$0(List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        list.add(firNamedFunctionSymbol.getFir());
        return Unit.INSTANCE;
    }

    private static final Collection<FirCallableDeclaration> getPropertyMembers(BodyResolveComponents bodyResolveComponents, ConeKotlinType coneKotlinType, Name name) {
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(bodyResolveComponents, coneKotlinType, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        if (firTypeScopeScope != null) {
            firTypeScopeScope.processPropertiesByName(name, new Function1() { // from class: rtd
                public final Object invoke(Object obj) {
                    return SuperCallsKt.getPropertyMembers$lambda$0$0(listCreateListBuilder, (FirVariableSymbol) obj);
                }
            });
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getPropertyMembers$lambda$0$0(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        list.add(firVariableSymbol.getFir());
        return Unit.INSTANCE;
    }

    private static final boolean isCallingMethodOfAny(FirFunctionCall firFunctionCall) {
        Integer num = ARITY_OF_METHODS_OF_ANY.get(firFunctionCall.getCalleeReference().getName().asString());
        if (num == null) {
            num = -1;
        }
        return num.intValue() == firFunctionCall.getArgumentList().getArguments().size();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isConcreteMember(BodyResolveComponents bodyResolveComponents, ConeKotlinType coneKotlinType, FirCallableDeclaration firCallableDeclaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (firCallableDeclaration.getStatus().getModality() == Modality.ABSTRACT) {
            return false;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(bodyResolveComponents, coneKotlinType);
        if (regularClassSymbol == null || ((FirRegularClass) regularClassSymbol.getFir()).getClassKind() != ClassKind.INTERFACE) {
            return true;
        }
        FirCallableDeclaration fir = firCallableDeclaration.getSymbol().getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(fir) || (fir.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(fir) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(fir) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            fir = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = fir.getSymbol();
        if (symbol != null) {
            ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(symbol);
            return !Intrinsics.areEqual(coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null ? coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId() : null, StandardClassIds.INSTANCE.getAny());
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration>");
        return false;
    }

    private static final List<ConeKotlinType> resolveSupertypesByCalleeName(BodyResolveComponents bodyResolveComponents, Collection<? extends ConeKotlinType> collection, Name name) {
        SmartList smartList = new SmartList();
        SmartList smartList2 = new SmartList();
        for (ConeKotlinType coneKotlinType : collection) {
            List listPlus = CollectionsKt.plus(getFunctionMembers(bodyResolveComponents, coneKotlinType, name), getPropertyMembers(bodyResolveComponents, coneKotlinType, name));
            if (!listPlus.isEmpty()) {
                List list = listPlus;
                boolean z = list instanceof Collection;
                if (!z || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (isConcreteMember(bodyResolveComponents, coneKotlinType, (FirCallableDeclaration) it.next())) {
                                smartList.add(coneKotlinType);
                            }
                        }
                    }
                }
                if (!z || !list.isEmpty()) {
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        ConeSimpleKotlinType dispatchReceiverType = ((FirCallableDeclaration) it2.next()).getDispatchReceiverType();
                        if (dispatchReceiverType != null && !ConeBuiltinTypeUtilsKt.isAny(dispatchReceiverType)) {
                            smartList2.add(coneKotlinType);
                            break;
                        }
                    }
                }
            }
        }
        CollectionsKt.removeAll(smartList, new SuperCallsKt$resolveSupertypesByMembers$3(smartList2, bodyResolveComponents));
        return !smartList.isEmpty() ? smartList : smartList2;
    }

    private static final List<ConeKotlinType> resolveSupertypesByPropertyName(BodyResolveComponents bodyResolveComponents, Collection<? extends ConeKotlinType> collection, Name name) {
        SmartList smartList = new SmartList();
        SmartList smartList2 = new SmartList();
        for (ConeKotlinType coneKotlinType : collection) {
            Collection<FirCallableDeclaration> propertyMembers = getPropertyMembers(bodyResolveComponents, coneKotlinType, name);
            if (!propertyMembers.isEmpty()) {
                Collection<FirCallableDeclaration> collection2 = propertyMembers;
                boolean z = collection2 instanceof Collection;
                if (!z || !collection2.isEmpty()) {
                    Iterator<T> it = collection2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (isConcreteMember(bodyResolveComponents, coneKotlinType, (FirCallableDeclaration) it.next())) {
                                smartList.add(coneKotlinType);
                            }
                        }
                    }
                }
                if (!z || !collection2.isEmpty()) {
                    Iterator<T> it2 = collection2.iterator();
                    while (it2.hasNext()) {
                        ConeSimpleKotlinType dispatchReceiverType = ((FirCallableDeclaration) it2.next()).getDispatchReceiverType();
                        if (dispatchReceiverType != null && !ConeBuiltinTypeUtilsKt.isAny(dispatchReceiverType)) {
                            smartList2.add(coneKotlinType);
                            break;
                        }
                    }
                }
            }
        }
        CollectionsKt.removeAll(smartList, new SuperCallsKt$resolveSupertypesByMembers$3(smartList2, bodyResolveComponents));
        return !smartList.isEmpty() ? smartList : smartList2;
    }

    private static final List<ConeKotlinType> resolveSupertypesForMethodOfAny(BodyResolveComponents bodyResolveComponents, Collection<? extends ConeKotlinType> collection, Name name) {
        FirRegularClassSymbol regularClassSymbol;
        ClassKind classKind;
        List smartList = new SmartList();
        SmartList smartList2 = new SmartList();
        for (ConeKotlinType coneKotlinType : collection) {
            Collection<FirCallableDeclaration> functionMembers = getFunctionMembers(bodyResolveComponents, coneKotlinType, name);
            if (!functionMembers.isEmpty()) {
                Collection<FirCallableDeclaration> collection2 = functionMembers;
                boolean z = collection2 instanceof Collection;
                if (!z || !collection2.isEmpty()) {
                    Iterator<T> it = collection2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (isConcreteMember(bodyResolveComponents, coneKotlinType, (FirCallableDeclaration) it.next())) {
                                smartList.add(coneKotlinType);
                            }
                        }
                    }
                }
                if (!z || !collection2.isEmpty()) {
                    Iterator<T> it2 = collection2.iterator();
                    while (it2.hasNext()) {
                        ConeSimpleKotlinType dispatchReceiverType = ((FirCallableDeclaration) it2.next()).getDispatchReceiverType();
                        if (dispatchReceiverType != null && !ConeBuiltinTypeUtilsKt.isAny(dispatchReceiverType)) {
                            smartList2.add(coneKotlinType);
                            break;
                        }
                    }
                }
            }
        }
        CollectionsKt.removeAll(smartList, new SuperCallsKt$resolveSupertypesByMembers$3(smartList2, bodyResolveComponents));
        if (smartList.isEmpty()) {
            smartList = new ArrayList();
            for (Object obj : smartList2) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) obj;
                if ((coneKotlinType2 instanceof ConeClassLikeType) && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) bodyResolveComponents, ((ConeClassLikeType) coneKotlinType2).getLookupTag())) != null && (classKind = regularClassSymbol.getClassKind()) != null && classKind == ClassKind.CLASS) {
                    smartList.add(obj);
                }
            }
        }
        if (smartList.isEmpty()) {
            smartList = CollectionsKt.listOf(bodyResolveComponents.getSession().getBuiltinTypes().getAnyType().getConeType());
        }
        return smartList;
    }
}

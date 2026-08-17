package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a3\u0010\n\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\rH\u0082\b\u001a\u0012\u0010\u000e\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u000f\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0011\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0012\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0014\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0015\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0015\u001a\u00020\u0006*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0016\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001aW\u0010\u0019\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2+\b\u0002\u0010\u001e\u001a%\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f\u0018\u00010\r¢\u0006\u0002\b!\u001a\u001c\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0018\u001a\u001c\u0010$\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0002\u001a\u001e\u0010%\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0018H\u0002\u001a\u001c\u0010&\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0018H\u0002\u001a\u001d\u0010'\u001a\u0004\u0018\u00010(*\u00020\u0018R\u00020)j\u0006\u0010*\u001a\u00020)¢\u0006\u0002\u0010+\u001a1\u0010,\u001a\b\u0012\u0002\b\u0003\u0018\u00010-*\u00020\u00022\u0006\u0010#\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u0006R\u00020)j\u0006\u0010*\u001a\u00020)¢\u0006\u0002\u0010/\u001a\u0018\u00100\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u00101\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u00102\u001a\u00020\u0002*\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u00103\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c*\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u00104\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c*\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u00105\u001a\u00020\u0002*\u00020 2\u0006\u00106\u001a\u00020\u0002H\u0002\u001a\u0014\u00107\u001a\u0004\u0018\u00010\u0001*\u0002082\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u00109\u001a\u0004\u0018\u00010:*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006;"}, d2 = {"functionTypeKind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "expandTypeAliases", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/name/ClassId;", "isFunctionTypeWithPredicate", "errorOnNotFunctionType", "predicate", "Lkotlin/Function1;", "isBasicFunctionType", "isBasicSuspendFunctionType", "isBasicFunctionOrKFunctionType", "isNonKFunctionType", "isSuspendOrKSuspendFunctionType", "isReflectFunctionType", "isNonReflectFunctionType", "isSomeFunctionType", "isNotBasicFunctionType", "customFunctionTypeToSimpleFunctionType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "createFunctionTypeWithNewKind", "kind", "additionalAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "updateTypeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lkotlin/ExtensionFunctionType;", "findSubtypeOfBasicFunctionType", "expectedFunctionType", "isFunctionOrKFunctionType", "findSubtypeOfBasicFunctionTypeImpl", "isSubtypeOfFunctionType", "findBaseInvokeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "findContributedInvokeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "shouldCalculateReturnTypesOfFakeOverrides", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;Z)Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "contextParameterTypes", "receiverType", "returnType", "valueParameterTypesWithoutReceivers", "valueParameterTypesIncludingReceiver", "typeOrDefault", "default", "specialFunctionTypeKind", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "valueParameterName", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctionalTypeUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction a(FirNamedFunctionSymbol firNamedFunctionSymbol, Ref.ObjectRef objectRef, SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ConeClassLikeType coneClassLikeType, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        firNamedFunctionSymbol2.getClass();
        if (!Intrinsics.areEqual(firNamedFunctionSymbol2, firNamedFunctionSymbol)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol2.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (!Intrinsics.areEqual((FirNamedFunctionSymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null), firNamedFunctionSymbol)) {
                FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firNamedFunctionSymbol2.getFir();
                while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration2)) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
                    }
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        break;
                    }
                    firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
                }
                FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration2.getSymbol();
                if (symbol == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
                    return null;
                }
                ConeSimpleKotlinType dispatchReceiverType = ((FirNamedFunctionSymbol) symbol).getDispatchReceiverType();
                ConeClassLikeType coneClassLikeType2 = dispatchReceiverType instanceof ConeClassLikeType ? (ConeClassLikeType) dispatchReceiverType : null;
                FunctionTypeKind functionTypeKindFunctionTypeKind$default = coneClassLikeType2 != null ? functionTypeKind$default((ConeRigidType) coneClassLikeType2, sessionAndScopeSessionHolder.getSession(), false, 2, (Object) null) : null;
                FunctionTypeKind functionTypeKindFunctionTypeKind$default2 = functionTypeKind$default((ConeRigidType) coneClassLikeType, sessionAndScopeSessionHolder.getSession(), false, 2, (Object) null);
                if (functionTypeKindFunctionTypeKind$default == null || !FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default) || ((functionTypeKindFunctionTypeKind$default2 != null && FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default2)) || functionTypeKindFunctionTypeKind$default2 == null || functionTypeKindFunctionTypeKind$default2.isReflectType() != functionTypeKindFunctionTypeKind$default.isReflectType())) {
                    return ProcessorAction.NEXT;
                }
                objectRef.element = firNamedFunctionSymbol2;
                return ProcessorAction.STOP;
            }
        }
        objectRef.element = firNamedFunctionSymbol2;
        return ProcessorAction.STOP;
    }

    public static Unit b(Ref.ObjectRef objectRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        objectRef.element = firNamedFunctionSymbol;
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(FirNamedFunctionSymbol firNamedFunctionSymbol, Ref.ObjectRef objectRef, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        firNamedFunctionSymbol2.getClass();
        if (((FirNamedFunction) firNamedFunctionSymbol2.getFir()).getValueParameters().size() != ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters().size()) {
            return Unit.INSTANCE;
        }
        objectRef.element = firNamedFunctionSymbol2;
        return Unit.INSTANCE;
    }

    public static final List<ConeKotlinType> contextParameterTypes(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        if (!isSomeFunctionType(coneKotlinType, firSession)) {
            return CollectionsKt.emptyList();
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        List listTake = ArraysKt.take(coneKotlinTypeFullyExpandedType$default.getTypeArguments(), CompilerConeAttributesKt.getContextParameterNumberForFunctionType(coneKotlinTypeFullyExpandedType$default));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listTake, 10));
        Iterator it = listTake.iterator();
        while (it.hasNext()) {
            arrayList.add(typeOrDefault((ConeTypeProjection) it.next(), firSession.getBuiltinTypes().getNothingType().getConeType()));
        }
        return arrayList;
    }

    public static final ConeClassLikeType createFunctionTypeWithNewKind(ConeKotlinType coneKotlinType, FirSession firSession, FunctionTypeKind functionTypeKind, List<? extends FirAnnotation> list, Function1<? super ConeTypeProjection[], ? extends ConeTypeProjection[]> function1) {
        ConeTypeProjection[] coneTypeProjectionArr;
        coneKotlinType.getClass();
        firSession.getClass();
        functionTypeKind.getClass();
        list.getClass();
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        ClassId classId = new ClassId(functionTypeKind.getPackageFqName(), functionTypeKind.numberedClassName(coneKotlinTypeFullyExpandedType$default.getTypeArguments().length - 1));
        ConeTypeProjection[] typeArguments = coneKotlinTypeFullyExpandedType$default.getTypeArguments();
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(classId);
        if (function1 != null && (coneTypeProjectionArr = (ConeTypeProjection[]) function1.invoke(typeArguments)) != null) {
            typeArguments = coneTypeProjectionArr;
        }
        return TypeConstructionUtilsKt.constructClassType(lookupTag, typeArguments, ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinTypeFullyExpandedType$default), coneKotlinTypeFullyExpandedType$default.getAttributes().add(CopyUtilsKt.computeTypeAttributes$default(list, firSession, null, false, false, 6, null)));
    }

    public static /* synthetic */ ConeClassLikeType createFunctionTypeWithNewKind$default(ConeKotlinType coneKotlinType, FirSession firSession, FunctionTypeKind functionTypeKind, List list, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            function1 = null;
        }
        return createFunctionTypeWithNewKind(coneKotlinType, firSession, functionTypeKind, list, function1);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006c  */
    public static final ConeClassLikeType customFunctionTypeToSimpleFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        List listEmptyList;
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default != null) {
            FunctionTypeKind functionTypeKind = FunctionTypeKind.Function.INSTANCE;
            if (!Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, functionTypeKind)) {
                FunctionTypeKind functionTypeKind2 = FunctionTypeKind.KFunction.INSTANCE;
                if (!Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, functionTypeKind2)) {
                    FunctionTypeKind functionTypeKind3 = functionTypeKindFunctionTypeKind$default.isReflectType() ? functionTypeKind2 : functionTypeKind;
                    ClassId annotationOnInvokeClassId = functionTypeKindFunctionTypeKind$default.getAnnotationOnInvokeClassId();
                    if (annotationOnInvokeClassId == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    } else {
                        ClassId classId = FirAnnotationUtilsKt.hasAnnotation(CustomAnnotationTypeAttributeKt.getCustomAnnotations(coneKotlinType.getAttributes()), annotationOnInvokeClassId, firSession) ? null : annotationOnInvokeClassId;
                        if (classId != null) {
                            FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
                            firAnnotationBuilder.setArgumentMapping(FirEmptyAnnotationArgumentMapping.INSTANCE);
                            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                            firResolvedTypeRefBuilder.setConeType(ScopeUtilsKt.defaultType(classId, CollectionsKt.emptyList()));
                            firAnnotationBuilder.setAnnotationTypeRef(firResolvedTypeRefBuilder.build());
                            listEmptyList = CollectionsKt.listOf(firAnnotationBuilder.mo289build());
                            if (listEmptyList == null) {
                                listEmptyList = CollectionsKt.emptyList();
                            }
                        } else {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                    }
                    return createFunctionTypeWithNewKind$default(coneKotlinType, firSession, functionTypeKind3, listEmptyList, null, 8, null);
                }
            }
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirNamedFunctionSymbol findBaseInvokeSymbol(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ConeClassLikeType coneClassLikeType) {
        FirClass firClass;
        sessionAndScopeSessionHolder.getClass();
        coneClassLikeType.getClass();
        if (!isSomeFunctionType(coneClassLikeType, sessionAndScopeSessionHolder.getSession())) {
            w01.a("Failed requirement.");
            return null;
        }
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(sessionAndScopeSessionHolder, coneClassLikeType.getLookupTag());
        if (classSymbol == null || (firClass = (FirClass) classSymbol.getFir()) == null) {
            return null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FirKotlinScopeProviderKt.unsubstitutedScope(sessionAndScopeSessionHolder, firClass, false, (FirResolvePhase) null).processFunctionsByName(OperatorNameConventions.INVOKE, new Function1() { // from class: ms5
            public final Object invoke(Object obj) {
                return FunctionalTypeUtilsKt.b(objectRef, (FirNamedFunctionSymbol) obj);
            }
        });
        return (FirNamedFunctionSymbol) objectRef.element;
    }

    public static final FirFunctionSymbol<?> findContributedInvokeSymbol(final SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ConeKotlinType coneKotlinType, final ConeClassLikeType coneClassLikeType, boolean z) {
        sessionAndScopeSessionHolder.getClass();
        coneKotlinType.getClass();
        coneClassLikeType.getClass();
        final FirNamedFunctionSymbol firNamedFunctionSymbolFindBaseInvokeSymbol = findBaseInvokeSymbol(sessionAndScopeSessionHolder, coneClassLikeType);
        if (firNamedFunctionSymbolFindBaseInvokeSymbol == null) {
            return null;
        }
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(sessionAndScopeSessionHolder, coneKotlinType, z ? CallableCopyTypeCalculator.CalculateDeferredForceLazyResolution.INSTANCE : CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        if (firTypeScopeScope == null) {
            return null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        firTypeScopeScope.processFunctionsByName(OperatorNameConventions.INVOKE, new Function1() { // from class: ks5
            public final Object invoke(Object obj) {
                return FunctionalTypeUtilsKt.c(firNamedFunctionSymbolFindBaseInvokeSymbol, objectRef, (FirNamedFunctionSymbol) obj);
            }
        });
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Object obj = objectRef.element;
        if (obj != null) {
            FirTypeScopeKt.processOverriddenFunctions(firTypeScopeScope, (FirNamedFunctionSymbol) obj, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: ls5
                public final Object invoke(Object obj2) {
                    return FunctionalTypeUtilsKt.a(firNamedFunctionSymbolFindBaseInvokeSymbol, objectRef2, sessionAndScopeSessionHolder, coneClassLikeType, (FirNamedFunctionSymbol) obj2);
                }
            });
        }
        if (objectRef2.element != null) {
            return (FirFunctionSymbol) objectRef.element;
        }
        return null;
    }

    public static final ConeKotlinType findSubtypeOfBasicFunctionType(ConeKotlinType coneKotlinType, FirSession firSession, ConeClassLikeType coneClassLikeType) {
        coneKotlinType.getClass();
        firSession.getClass();
        coneClassLikeType.getClass();
        if (isFunctionOrKFunctionType(coneClassLikeType, firSession, true)) {
            return findSubtypeOfBasicFunctionTypeImpl(coneKotlinType, firSession, coneClassLikeType);
        }
        w01.a("Failed requirement.");
        return null;
    }

    private static final ConeKotlinType findSubtypeOfBasicFunctionTypeImpl(ConeKotlinType coneKotlinType, FirSession firSession, ConeClassLikeType coneClassLikeType) {
        Object obj = null;
        if (coneKotlinType instanceof ConeClassLikeType) {
            if (!isNotBasicFunctionType(coneKotlinType, firSession) && isSubtypeOfFunctionType(coneKotlinType, firSession, coneClassLikeType)) {
                return (ConeClassLikeType) coneKotlinType;
            }
            return null;
        }
        boolean z = true;
        if (coneKotlinType instanceof ConeIntersectionType) {
            ConeIntersectionType coneIntersectionType = (ConeIntersectionType) coneKotlinType;
            Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
            if (!(intersectedTypes instanceof Collection) || !intersectedTypes.isEmpty()) {
                Iterator<T> it = intersectedTypes.iterator();
                do {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                } while (!isNotBasicFunctionType((ConeKotlinType) it.next(), firSession));
            } else {
                z = false;
                break;
            }
            if (z) {
                return null;
            }
            for (Object obj2 : coneIntersectionType.getIntersectedTypes()) {
                if (findSubtypeOfBasicFunctionTypeImpl((ConeKotlinType) obj2, firSession, coneClassLikeType) != null) {
                    obj = obj2;
                    break;
                }
            }
            return (ConeKotlinType) obj;
        }
        if (!(coneKotlinType instanceof ConeTypeParameterType)) {
            return null;
        }
        List<FirResolvedTypeRef> resolvedBounds = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getResolvedBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
        Iterator<T> it2 = resolvedBounds.iterator();
        while (it2.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it2.next()).getConeType());
        }
        if (arrayList.isEmpty()) {
            z = false;
            break;
        }
        Iterator it3 = arrayList.iterator();
        do {
            if (!it3.hasNext()) {
                z = false;
                break;
            }
        } while (!isNotBasicFunctionType((ConeKotlinType) it3.next(), firSession));
        if (z) {
            return null;
        }
        for (Object obj3 : arrayList) {
            if (findSubtypeOfBasicFunctionTypeImpl((ConeKotlinType) obj3, firSession, coneClassLikeType) != null) {
                obj = obj3;
                break;
            }
        }
        return (ConeKotlinType) obj;
    }

    public static final FunctionTypeKind functionTypeKind(ConeRigidType coneRigidType, FirSession firSession, boolean z) {
        coneRigidType.getClass();
        firSession.getClass();
        if (!(coneRigidType instanceof ConeClassLikeType)) {
            return null;
        }
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default = (ConeClassLikeType) coneRigidType;
        if (z) {
            coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeTypeFullyExpandedType$default, firSession, (Function1) null, 2, (Object) null);
        }
        return functionTypeKind(coneClassLikeTypeFullyExpandedType$default.getLookupTag(), firSession);
    }

    public static /* synthetic */ FunctionTypeKind functionTypeKind$default(ConeKotlinType coneKotlinType, FirSession firSession, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return functionTypeKind(coneKotlinType, firSession, z);
    }

    public static final boolean isBasicFunctionOrKFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default);
    }

    public static final boolean isBasicFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.Function.INSTANCE);
    }

    public static final boolean isBasicSuspendFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.SuspendFunction.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean isFunctionOrKFunctionType(ConeKotlinType coneKotlinType, FirSession firSession, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default != null) {
            return FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default);
        }
        if (!z) {
            return false;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(coneKotlinType.getClass() + " is not a function type", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, coneKotlinType);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final boolean isNonKFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return !Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.KFunction.INSTANCE);
    }

    public static final boolean isNonReflectFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return !functionTypeKindFunctionTypeKind$default.isReflectType();
    }

    public static final boolean isNotBasicFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return !FunctionTypeKindKt.isBasicFunctionOrKFunction(functionTypeKindFunctionTypeKind$default);
    }

    public static final boolean isReflectFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return functionTypeKindFunctionTypeKind$default.isReflectType();
    }

    public static final boolean isSomeFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null) != null;
    }

    private static final boolean isSubtypeOfFunctionType(ConeKotlinType coneKotlinType, FirSession firSession, ConeClassLikeType coneClassLikeType) {
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(firSession), coneKotlinType, ConeTypeUtilsKt.replaceArgumentsWithStarProjections(coneClassLikeType), false, 8, (Object) null);
    }

    public static final boolean isSuspendOrKSuspendFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null) {
            return false;
        }
        return Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.SuspendFunction.INSTANCE) || Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.KSuspendFunction.INSTANCE);
    }

    public static final ConeKotlinType receiverType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        if (!isSomeFunctionType(coneKotlinType, firSession) || !TypeUtilsKt.isExtensionFunctionType(coneKotlinType, firSession)) {
            return null;
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        return typeOrDefault(coneKotlinTypeFullyExpandedType$default.getTypeArguments()[CompilerConeAttributesKt.getContextParameterNumberForFunctionType(coneKotlinTypeFullyExpandedType$default)], firSession.getBuiltinTypes().getNothingType().getConeType());
    }

    public static final ConeKotlinType returnType(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        coneClassLikeType.getClass();
        firSession.getClass();
        return typeOrDefault((ConeTypeProjection) ArraysKt.last(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null).getTypeArguments()), firSession.getBuiltinTypes().getNullableAnyType().getConeType());
    }

    public static final FunctionTypeKind specialFunctionTypeKind(FirFunction firFunction, FirSession firSession) {
        firFunction.getClass();
        firSession.getClass();
        FirFunctionSymbol<FirFunction> symbol = firFunction.getSymbol();
        FirNamedFunctionSymbol firNamedFunctionSymbol = symbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) symbol : null;
        if (firNamedFunctionSymbol != null) {
            return FirFunctionTypeKindServiceKt.getFunctionTypeService(firSession).extractSingleSpecialKindForFunction(firNamedFunctionSymbol);
        }
        return null;
    }

    private static final ConeKotlinType typeOrDefault(ConeTypeProjection coneTypeProjection, ConeKotlinType coneKotlinType) {
        if (coneTypeProjection instanceof ConeKotlinTypeProjection) {
            return ((ConeKotlinTypeProjection) coneTypeProjection).getType();
        }
        if (coneTypeProjection instanceof ConeStarProjection) {
            return coneKotlinType;
        }
        bu8.a();
        return null;
    }

    public static final Name valueParameterName(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ParameterNameTypeAttribute parameterNameAttribute = ParameterNameTypeAttributeKt.getParameterNameAttribute(coneKotlinType.getAttributes());
        if (parameterNameAttribute == null) {
            return null;
        }
        if (parameterNameAttribute.getName() != null) {
            return parameterNameAttribute.getName();
        }
        String stringArgument = FirAnnotationUtilsKt.getStringArgument((FirAnnotation) CollectionsKt.first(parameterNameAttribute.getAnnotations()), StandardNames.NAME);
        if (stringArgument == null) {
            return null;
        }
        return Name.identifier(stringArgument);
    }

    public static final List<ConeKotlinType> valueParameterTypesIncludingReceiver(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        coneClassLikeType.getClass();
        firSession.getClass();
        List listDropLast = ArraysKt.dropLast(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null).getTypeArguments(), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            arrayList.add(typeOrDefault((ConeTypeProjection) it.next(), firSession.getBuiltinTypes().getNothingType().getConeType()));
        }
        return arrayList;
    }

    public static final List<ConeKotlinType> valueParameterTypesWithoutReceivers(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        coneClassLikeType.getClass();
        firSession.getClass();
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null);
        List listDropLast = CollectionsKt.dropLast(ArraysKt.drop(coneClassLikeTypeFullyExpandedType$default.getTypeArguments(), CompilerConeAttributesKt.getContextParameterNumberForFunctionType(coneClassLikeTypeFullyExpandedType$default) + (CompilerConeAttributesKt.isExtensionFunctionType(coneClassLikeTypeFullyExpandedType$default) ? 1 : 0)), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            arrayList.add(typeOrDefault((ConeTypeProjection) it.next(), firSession.getBuiltinTypes().getNothingType().getConeType()));
        }
        return arrayList;
    }

    public static /* synthetic */ FunctionTypeKind functionTypeKind$default(ConeRigidType coneRigidType, FirSession firSession, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return functionTypeKind(coneRigidType, firSession, z);
    }

    public static final boolean isSomeFunctionType(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        return functionTypeKind(coneClassLikeLookupTag, firSession) != null;
    }

    public static final FunctionTypeKind functionTypeKind(ConeKotlinType coneKotlinType, FirSession firSession, boolean z) {
        coneKotlinType.getClass();
        firSession.getClass();
        return functionTypeKind(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType), firSession, z);
    }

    public static final FunctionTypeKind functionTypeKind(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        return functionTypeKind(coneClassLikeLookupTag.getClassId(), firSession);
    }

    public static final FunctionTypeKind functionTypeKind(ClassId classId, FirSession firSession) {
        classId.getClass();
        firSession.getClass();
        FirFunctionTypeKindService functionTypeService = FirFunctionTypeKindServiceKt.getFunctionTypeService(firSession);
        FqName packageFqName = classId.getPackageFqName();
        String strAsString = classId.getShortClassName().asString();
        strAsString.getClass();
        return functionTypeService.getKindByClassNamePrefix(packageFqName, strAsString);
    }
}

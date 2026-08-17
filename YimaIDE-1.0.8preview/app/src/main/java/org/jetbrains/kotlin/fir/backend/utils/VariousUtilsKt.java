package org.jetbrains.kotlin.fir.backend.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.InlineClassRepresentation;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrMetadataSourceOwner;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrStarProjection;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0005R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\b\u0012\u0004\u0012\u00020\n0\u0001\u001a)\u0010\u000b\u001a\u00020\f*\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0000R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u000f\u001a \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0000\u001a\u001d\u0010\u001b\u001a\u00020\u001c*\u00020\u001dH\u0000R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u001e\u001a9\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010 2\u0006\u0010!\u001a\u00020\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H 0&H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010'\u001a\u0012\u0010(\u001a\u00020\f*\u00020\f2\u0006\u0010)\u001a\u00020*\u001a\u0016\u00103\u001a\u000200*\u0006\u0012\u0002\b\u0003042\u0006\u00105\u001a\u000206\u001a\n\u00107\u001a\u000200*\u00020\u001d\u001aJ\u00108\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H 09\"\b\b\u0000\u0010:*\u00020\t\"\u0004\b\u0001\u0010 2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H 092\u0010\u0010<\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003040\bH\u0000\"\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0015\u0010+\u001a\u00020,*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u0019\u0010/\u001a\u000200*\u0006\u0012\u0002\b\u0003018F¢\u0006\u0006\u001a\u0004\b/\u00102\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006="}, d2 = {"getIrSymbolsForSealedSubclasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/util/List;", "extractFirDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "irTypeForPotentiallyComponentCall", "Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "predefinedType", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Lorg/jetbrains/kotlin/ir/types/IrType;)Lorg/jetbrains/kotlin/ir/types/IrType;", "varargElementType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getVarargElementType", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "implicitCast", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "original", "castType", "typeOperator", "Lorg/jetbrains/kotlin/ir/expressions/IrTypeOperator;", "buildSubstitutorByCalledCallable", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "convertCatching", "R", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getArrayElementType", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "defaultTypeWithoutArguments", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "getDefaultTypeWithoutArguments", "(Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;)Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "isInlineClassProperty", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "shouldHaveReceiver", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isConstructorCallOnTypealiasWithInnerRhs", "filterOutSymbolsFromCache", Argument.Delimiters.none, "T", "cache", "filterOutSymbols", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VariousUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeSubstitutor buildSubstitutorByCalledCallable(Fir2IrComponents fir2IrComponents, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        List<FirTypeParameterRef> typeParameters;
        fir2IrComponents.getClass();
        firQualifiedAccessExpression.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        FirCallableDeclaration firCallableDeclaration = resolvedCallableSymbol$default != null ? (FirCallableDeclaration) resolvedCallableSymbol$default.getFir() : null;
        if (firCallableDeclaration instanceof FirFunction) {
            typeParameters = ((FirFunction) firCallableDeclaration).getTypeParameters();
        } else {
            if (!(firCallableDeclaration instanceof FirProperty)) {
                return ConeSubstitutor.Empty.INSTANCE;
            }
            typeParameters = ((FirProperty) firCallableDeclaration).getTypeParameters();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
            int i2 = i + 1;
            Object orNull = CollectionsKt.getOrNull(firQualifiedAccessExpression.getTypeArguments(), i);
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = orNull instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) orNull : null;
            if (firTypeProjectionWithVariance != null) {
                linkedHashMap.put(firTypeParameterRef.getSymbol(), FirTypeUtilsKt.getConeType(firTypeProjectionWithVariance.getTypeRef()));
            }
            i = i2;
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, fir2IrComponents.getSession(), false, 4, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <R> R convertCatching(FirElement firElement, Fir2IrConversionScope fir2IrConversionScope, Function0<? extends R> function0) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFile irFileContainingFileIfAny;
        firElement.getClass();
        function0.getClass();
        try {
            return (R) function0.invoke();
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + firElement.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firElement);
            if (fir2IrConversionScope != null && (irFileContainingFileIfAny = fir2IrConversionScope.containingFileIfAny()) != null) {
                exceptionAttachmentBuilder.withEntry("file", IrDeclarationsKt.getPath(irFileContainingFileIfAny));
            }
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ Object convertCatching$default(FirElement firElement, Fir2IrConversionScope fir2IrConversionScope, Function0 function0, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFile irFileContainingFileIfAny;
        if ((i & 2) != 0) {
            fir2IrConversionScope = null;
        }
        firElement.getClass();
        function0.getClass();
        try {
            return function0.invoke();
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + firElement.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firElement);
            if (fir2IrConversionScope != null && (irFileContainingFileIfAny = fir2IrConversionScope.containingFileIfAny()) != null) {
                exceptionAttachmentBuilder.withEntry("file", IrDeclarationsKt.getPath(irFileContainingFileIfAny));
            }
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    public static final Set<FirDeclaration> extractFirDeclarations(List<? extends IrDeclaration> list) {
        list.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            IrMetadataSourceOwner irMetadataSourceOwner = (IrDeclaration) it.next();
            irMetadataSourceOwner.getClass();
            MetadataSource metadata = irMetadataSourceOwner.getMetadata();
            metadata.getClass();
            linkedHashSet.add(((FirMetadataSource) metadata).getFir());
        }
        return linkedHashSet;
    }

    public static final <T extends FirDeclaration, R> Map<T, R> filterOutSymbolsFromCache(Map<T, ? extends R> map, Set<? extends FirBasedSymbol<?>> set) {
        map.getClass();
        set.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<T, ? extends R> entry : map.entrySet()) {
            if (!set.contains(entry.getKey().getSymbol())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    public static final IrType getArrayElementType(IrType irType, Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        irType.getClass();
        fir2IrBuiltinSymbolsContainer.getClass();
        if (IrTypeUtilsKt.isBoxedArray(irType)) {
            IrTypeProjection irTypeProjection = (IrTypeArgument) CollectionsKt.singleOrNull(((IrSimpleType) irType).getArguments());
            if (irTypeProjection instanceof IrTypeProjection) {
                return irTypeProjection.getType();
            }
            if (irTypeProjection instanceof IrStarProjection) {
                return fir2IrBuiltinSymbolsContainer.getAnyNType();
            }
            if (irTypeProjection == null) {
                k2d.a("Unexpected array argument type: null");
                return null;
            }
            bu8.a();
            return null;
        }
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
        classOrNull.getClass();
        IrType irType2 = fir2IrBuiltinSymbolsContainer.getPrimitiveArrayElementTypes().get(classOrNull);
        if (irType2 != null) {
            return irType2;
        }
        IrType irType3 = fir2IrBuiltinSymbolsContainer.getUnsignedArraysElementTypes().get(classOrNull);
        if (irType3 != null) {
            return irType3;
        }
        w04.a("Primitive array expected: ", classOrNull);
        return null;
    }

    public static final IrSimpleType getDefaultTypeWithoutArguments(IrClassSymbol irClassSymbol) {
        irClassSymbol.getClass();
        return IrSimpleTypeImplKt.IrSimpleTypeImpl$default(irClassSymbol, SimpleTypeNullability.DEFINITELY_NOT_NULL, CollectionsKt.emptyList(), CollectionsKt.emptyList(), (KotlinType) null, 16, (Object) null);
    }

    public static final List<IrClassSymbol> getIrSymbolsForSealedSubclasses(Fir2IrComponents fir2IrComponents, FirRegularClass firRegularClass) {
        Fir2IrComponents fir2IrComponents2;
        IrClassifierSymbol irSymbol$default;
        fir2IrComponents.getClass();
        firRegularClass.getClass();
        FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(fir2IrComponents.getSession());
        List<ClassId> sealedClassInheritors = SealedClassInheritorsKt.getSealedClassInheritors(firRegularClass, fir2IrComponents.getSession());
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = sealedClassInheritors.iterator();
        while (it.hasNext()) {
            FirClassLikeSymbol<?> classLikeSymbolByClassId = symbolProvider.getClassLikeSymbolByClassId((ClassId) it.next());
            if (classLikeSymbolByClassId != null) {
                fir2IrComponents2 = fir2IrComponents;
                irSymbol$default = SymbolConversionUtilsKt.toIrSymbol$default(fir2IrComponents2, classLikeSymbolByClassId, null, null, 6, null);
            } else {
                fir2IrComponents2 = fir2IrComponents;
                irSymbol$default = null;
            }
            if (irSymbol$default != null) {
                arrayList.add(irSymbol$default);
            }
            fir2IrComponents = fir2IrComponents2;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (obj instanceof IrClassSymbol) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public static final ConeKotlinType getVarargElementType(FirValueParameter firValueParameter) {
        firValueParameter.getClass();
        if (firValueParameter.getIsVararg()) {
            return FirTypeUtilsKt.arrayElementType$default(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), false, 1, null);
        }
        return null;
    }

    public static final IrExpression implicitCast(IrExpression irExpression, IrType irType, IrTypeOperator irTypeOperator) {
        irExpression.getClass();
        irType.getClass();
        irTypeOperator.getClass();
        if (Intrinsics.areEqual(irExpression.getType(), irType)) {
            return irExpression;
        }
        return irExpression instanceof IrTypeOperatorCall ? implicitCast(((IrTypeOperatorCall) irExpression).getArgument(), irType, irTypeOperator) : BuildersKt.IrTypeOperatorCallImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), irType, irTypeOperator, irType, irExpression);
    }

    public static final IrType irTypeForPotentiallyComponentCall(Fir2IrComponents fir2IrComponents, FirVariable firVariable, IrType irType) {
        ConeKotlinType coneType;
        fir2IrComponents.getClass();
        firVariable.getClass();
        FirExpression initializer = firVariable.getInitializer();
        if (firVariable.getIsVal() && (initializer instanceof FirComponentCall)) {
            coneType = FirTypeUtilsKt.getResolvedType(initializer);
        } else {
            if (irType != null) {
                return irType;
            }
            coneType = FirTypeUtilsKt.getConeType(firVariable.getReturnTypeRef());
        }
        return Fir2IrTypeConverterKt.toIrType$default(fir2IrComponents, coneType, (ConversionTypeOrigin) null, 2, (Object) null);
    }

    public static /* synthetic */ IrType irTypeForPotentiallyComponentCall$default(Fir2IrComponents fir2IrComponents, FirVariable firVariable, IrType irType, int i, Object obj) {
        if ((i & 2) != 0) {
            irType = null;
        }
        return irTypeForPotentiallyComponentCall(fir2IrComponents, firVariable, irType);
    }

    public static final boolean isConstructorCallOnTypealiasWithInnerRhs(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firQualifiedAccessExpression.getClass();
        FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(firQualifiedAccessExpression.getCalleeReference());
        FirConstructorSymbol firConstructorSymbol = symbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) symbol : null;
        return (firConstructorSymbol == null || !Intrinsics.areEqual(firConstructorSymbol.getOrigin(), FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE) || firConstructorSymbol.getReceiverParameterSymbol() == null) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isInlineClassProperty(FirCallableSymbol<?> firCallableSymbol) {
        InlineClassRepresentation<ConeRigidType> inlineClassRepresentation;
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol;
            if (firPropertySymbol.getDispatchReceiverType() != null && firPropertySymbol.getReceiverParameterSymbol() == null && !FirCallableSymbolKt.getHasContextParameters(firCallableSymbol)) {
                FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
                FirRegularClassSymbol firRegularClassSymbol = containingClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol : null;
                if (firRegularClassSymbol == null || (inlineClassRepresentation = FirValueClassRepresentationKt.getInlineClassRepresentation((FirRegularClass) firRegularClassSymbol.getFir())) == null) {
                    return false;
                }
                return Intrinsics.areEqual(inlineClassRepresentation.getUnderlyingPropertyName(), firPropertySymbol.getName());
            }
        }
        return false;
    }

    public static final boolean shouldHaveReceiver(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return !FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol.getFir(), StandardClassIds$Annotations.INSTANCE.getJsNoDispatchReceiver(), firSession);
    }
}

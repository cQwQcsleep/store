package org.jetbrains.kotlin.fir.backend.utils;

import defpackage.f2f;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aG\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\f\u001a!\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u00020\u000fR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0010\u001a5\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0016\u001a5\u0010\u0017\u001a\u0004\u0018\u00010\u0012*\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0016\u001a=\u0010\u0018\u001a\u0004\u0018\u00010\u0012*\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u001bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a>\u0010\u0000\u001a\u0004\u0018\u00010\u0012*\u00020\u00022\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u0016\u0010 \u001a\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002¨\u0006%"}, d2 = {"toIrSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "handleAnnotations", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "extractDeclarationSiteSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/references/FirReference;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "toIrSymbolForCall", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "explicitReceiver", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "toIrSymbolForSetCall", "toIrSymbolForCallableReference", "lhs", "isDelegate", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Z)Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "symbol", "useSite", "Lorg/jetbrains/kotlin/fir/backend/utils/UseSiteKind;", "toLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SymbolConversionUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UseSiteKind.values().length];
            try {
                iArr[UseSiteKind.Reference.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final FirCallableSymbol<?> extractDeclarationSiteSymbol(Fir2IrComponents fir2IrComponents, FirReference firReference) throws KotlinIllegalArgumentExceptionWithAttachments {
        fir2IrComponents.getClass();
        firReference.getClass();
        if (!(firReference instanceof FirResolvedNamedReference)) {
            return null;
        }
        FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) firReference;
        FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
        FirCallableSymbol<FirCallableDeclaration> symbol = resolvedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) resolvedSymbol : null;
        if (symbol == null) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Non-callable symbol got from call reference", (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            exceptionAttachmentBuilder.withEntry("symbol", firResolvedNamedReference.getResolvedSymbol().toString());
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        if (Intrinsics.areEqual(symbol.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
            FirCallableDeclaration fir = symbol.getFir();
            while (Intrinsics.areEqual(fir.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(fir) || (fir.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                fir = originalForSubstitutionOverrideAttr;
            }
            symbol = fir.getSymbol();
        }
        return ScopeUtilsKt.unwrapCallRepresentative$default(fir2IrComponents, symbol, null, 2, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final IrSymbol toIrSymbol(Fir2IrComponents fir2IrComponents, FirCallableSymbol<?> firCallableSymbol, FirExpression firExpression, FirExpression firExpression2, UseSiteKind useSiteKind, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeClassLikeLookupTag lookupTag;
        ConeClassLikeLookupTag coneClassLikeLookupTag;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        FirConstructor firConstructor;
        FirConstructorSymbol symbol;
        FirNamedFunction delegate;
        FirNamedFunctionSymbol symbol2;
        FirNamedFunctionSymbol symbol3;
        ConeClassLikeLookupTag coneClassLikeLookupTag2 = null;
        if (firCallableSymbol.getRawStatus().isStatic()) {
            FirResolvedQualifier firResolvedQualifier = firExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpression : null;
            if (firResolvedQualifier != null) {
                lookupTag = toLookupTag(firResolvedQualifier, fir2IrComponents.getSession());
                coneClassLikeLookupTag = lookupTag;
            } else {
                coneClassLikeLookupTag = null;
            }
        } else {
            if (firExpression != null) {
                ConeKotlinType irSymbol$findReceiverType = toIrSymbol$findReceiverType(firExpression);
                ConeSimpleKotlinType dispatchReceiverType = firCallableSymbol.getDispatchReceiverType();
                if ((irSymbol$findReceiverType instanceof ConeDynamicType) && dispatchReceiverType != null) {
                    irSymbol$findReceiverType = dispatchReceiverType;
                }
                lookupTag = LookupTagUtilsKt.findClassRepresentation(irSymbol$findReceiverType, irSymbol$findReceiverType, fir2IrComponents.getDeclarationStorage().getSession());
            } else {
                if (useSiteKind == UseSiteKind.Reference && ((FirCallableDeclaration) firCallableSymbol.getFir()).getReceiverParameter() == null) {
                    FirResolvedQualifier firResolvedQualifier2 = firExpression2 instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpression2 : null;
                    if (firResolvedQualifier2 != null) {
                        lookupTag = toLookupTag(firResolvedQualifier2, fir2IrComponents.getSession());
                    }
                }
                coneClassLikeLookupTag = null;
            }
            coneClassLikeLookupTag = lookupTag;
        }
        if (firCallableSymbol instanceof FirSimpleSyntheticPropertySymbol) {
            if (z) {
                return Fir2IrDeclarationStorage.getIrPropertySymbol$default(fir2IrComponents.getDeclarationStorage(), (FirPropertySymbol) firCallableSymbol, null, 2, null);
            }
            if (useSiteKind == UseSiteKind.Reference) {
                return fir2IrComponents.getDeclarationStorage().getIrPropertySymbol((FirPropertySymbol) firCallableSymbol, coneClassLikeLookupTag);
            }
            FirSyntheticProperty syntheticProperty = ((FirSimpleSyntheticPropertySymbol) firCallableSymbol).getSyntheticProperty();
            if (useSiteKind == UseSiteKind.GetCall) {
                symbol3 = syntheticProperty.getGetter().getDelegate().getSymbol();
            } else {
                FirSyntheticPropertyAccessor setter = syntheticProperty.getSetter();
                if (setter == null || (delegate = setter.getDelegate()) == null || (symbol2 = delegate.getSymbol()) == null) {
                    f2f.a("Written synthetic property must have a setter: ", UtilsKt.render(syntheticProperty));
                    return null;
                }
                symbol3 = symbol2;
            }
            return toIrSymbol(fir2IrComponents, ScopeUtilsKt.unwrapCallRepresentative$default(fir2IrComponents, symbol3, null, 2, null), firExpression, null, useSiteKind, false);
        }
        if (firCallableSymbol instanceof FirConstructorSymbol) {
            Fir2IrDeclarationStorage declarationStorage = fir2IrComponents.getDeclarationStorage();
            FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firCallableSymbol;
            TypeAliasConstructorInfo typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo((FirFunction) firConstructorSymbol.getFir());
            if (typeAliasConstructorInfo != null && (firConstructor = (FirConstructor) typeAliasConstructorInfo.getOriginalConstructor()) != null && (symbol = firConstructor.getSymbol()) != null) {
                firConstructorSymbol = symbol;
            }
            return Fir2IrDeclarationStorage.getIrConstructorSymbol$default(declarationStorage, firConstructorSymbol, false, 2, null);
        }
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            return Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrComponents.getDeclarationStorage(), (FirFunctionSymbol) firCallableSymbol, coneClassLikeLookupTag, false, 4, null);
        }
        if (firCallableSymbol instanceof FirPropertySymbol) {
            return fir2IrComponents.getDeclarationStorage().getIrPropertySymbol((FirPropertySymbol) firCallableSymbol, coneClassLikeLookupTag);
        }
        if (!(firCallableSymbol instanceof FirFieldSymbol)) {
            if (firCallableSymbol instanceof FirBackingFieldSymbol) {
                return fir2IrComponents.getDeclarationStorage().getIrBackingFieldSymbol((FirBackingFieldSymbol) firCallableSymbol);
            }
            if (firCallableSymbol instanceof FirDelegateFieldSymbol) {
                return fir2IrComponents.getDeclarationStorage().getIrDelegateFieldSymbol((FirDelegateFieldSymbol) firCallableSymbol);
            }
            if (firCallableSymbol instanceof FirVariableSymbol) {
                return fir2IrComponents.getDeclarationStorage().getIrValueSymbol((FirVariableSymbol) firCallableSymbol);
            }
            return null;
        }
        if (WhenMappings.$EnumSwitchMapping$0[useSiteKind.ordinal()] == 1) {
            return fir2IrComponents.getDeclarationStorage().getIrSymbolForField((FirFieldSymbol) firCallableSymbol, coneClassLikeLookupTag);
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol4 = firCallableDeclaration.getSymbol();
        if (symbol4 == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol");
            return null;
        }
        FirFieldSymbol firFieldSymbol = (FirFieldSymbol) symbol4;
        FirDeclarationOrigin origin = firFieldSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firFieldSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration2 = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration2 == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration2)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                coneClassLikeLookupTag2 = coneClassLikeLookupTag;
            }
        }
        IrSymbol irSymbolForField = fir2IrComponents.getDeclarationStorage().getIrSymbolForField(firFieldSymbol, coneClassLikeLookupTag2);
        irSymbolForField.getClass();
        return fir2IrComponents.getDeclarationStorage().findBackingFieldOfProperty((IrPropertySymbol) irSymbolForField);
    }

    public static /* synthetic */ IrClassifierSymbol toIrSymbol$default(Fir2IrComponents fir2IrComponents, FirClassifierSymbol firClassifierSymbol, ConversionTypeOrigin conversionTypeOrigin, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        return toIrSymbol(fir2IrComponents, firClassifierSymbol, conversionTypeOrigin, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeKotlinType toIrSymbol$findReceiverType(FirExpression firExpression) {
        ConeKotlinType coneType;
        if (!(firExpression instanceof FirSmartCastExpression)) {
            return ((firExpression instanceof FirCheckNotNullCall) && ConeBuiltinTypeUtilsKt.isNothing(FirTypeUtilsKt.getResolvedType(firExpression))) ? toIrSymbol$findReceiverType((FirExpression) CollectionsKt.first(((FirCall) firExpression).getArgumentList().getArguments())) : FirTypeUtilsKt.getResolvedType(firExpression);
        }
        FirTypeRef smartcastTypeWithoutNullableNothing = ((FirSmartCastExpression) firExpression).getSmartcastTypeWithoutNullableNothing();
        return (smartcastTypeWithoutNullableNothing == null || (coneType = FirTypeUtilsKt.getConeType(smartcastTypeWithoutNullableNothing)) == null) ? FirTypeUtilsKt.getResolvedType(firExpression) : coneType;
    }

    public static final IrSymbol toIrSymbolForCall(Fir2IrComponents fir2IrComponents, FirCallableSymbol<?> firCallableSymbol, FirExpression firExpression, FirExpression firExpression2) {
        fir2IrComponents.getClass();
        firCallableSymbol.getClass();
        return toIrSymbol(fir2IrComponents, firCallableSymbol, firExpression, firExpression2, UseSiteKind.GetCall, false);
    }

    public static final IrSymbol toIrSymbolForCallableReference(Fir2IrComponents fir2IrComponents, FirCallableSymbol<?> firCallableSymbol, FirExpression firExpression, FirExpression firExpression2, boolean z) {
        fir2IrComponents.getClass();
        firCallableSymbol.getClass();
        return toIrSymbol(fir2IrComponents, firCallableSymbol, firExpression, firExpression2, UseSiteKind.Reference, z);
    }

    public static final IrSymbol toIrSymbolForSetCall(Fir2IrComponents fir2IrComponents, FirCallableSymbol<?> firCallableSymbol, FirExpression firExpression, FirExpression firExpression2) {
        fir2IrComponents.getClass();
        firCallableSymbol.getClass();
        return toIrSymbol(fir2IrComponents, firCallableSymbol, firExpression, firExpression2, UseSiteKind.SetCall, false);
    }

    private static final ConeClassLikeLookupTag toLookupTag(FirResolvedQualifier firResolvedQualifier, FirSession firSession) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol instanceof FirClassSymbol) {
            return ((FirClassSymbol) symbol).getLookupTag();
        }
        if (!(symbol instanceof FirTypeAliasSymbol) || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(symbol, firSession)) == null) {
            return null;
        }
        return firRegularClassSymbolFullyExpandedClass.getLookupTag();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final IrClassifierSymbol toIrSymbol(Fir2IrComponents fir2IrComponents, FirClassifierSymbol<?> firClassifierSymbol, ConversionTypeOrigin conversionTypeOrigin, Function1<? super List<? extends FirAnnotation>, Unit> function1) {
        IrClassifierSymbol irSymbol;
        fir2IrComponents.getClass();
        firClassifierSymbol.getClass();
        conversionTypeOrigin.getClass();
        if (firClassifierSymbol instanceof FirTypeParameterSymbol) {
            return fir2IrComponents.getClassifierStorage().getIrTypeParameterSymbol((FirTypeParameterSymbol) firClassifierSymbol, conversionTypeOrigin);
        }
        if (firClassifierSymbol instanceof FirTypeAliasSymbol) {
            if (function1 != null) {
                function1.invoke(((FirTypeAlias) ((FirTypeAliasSymbol) firClassifierSymbol).getFir()).getExpandedTypeRef().getAnnotations());
            }
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(((FirTypeAlias) ((FirTypeAliasSymbol) firClassifierSymbol).getFir()).getExpandedTypeRef());
            coneType.getClass();
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) fir2IrComponents, coneClassLikeType.getLookupTag());
            return (symbol == null || (irSymbol = toIrSymbol(fir2IrComponents, symbol, conversionTypeOrigin, function1)) == null) ? fir2IrComponents.getClassifierStorage().getIrClassForNotFoundClass(coneClassLikeType.getLookupTag()).getSymbol() : irSymbol;
        }
        if (firClassifierSymbol instanceof FirClassSymbol) {
            return fir2IrComponents.getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) firClassifierSymbol);
        }
        bu8.a();
        return null;
    }
}

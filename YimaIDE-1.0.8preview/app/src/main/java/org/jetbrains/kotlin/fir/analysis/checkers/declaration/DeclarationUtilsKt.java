package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKindKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitUnitTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006\u001a>\u0010\b\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0016\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00010\nH\u0082\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\f\u0010\f\u001a\u00020\u0001*\u00020\rH\u0000\u001a\u0010\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000eH\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000eH\u0002\u001a+\u0010\u0010\u001a\u00020\u0001*\u00020\r2\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0010\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00122\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0013\u001a+\u0010\u0014\u001a\u00020\u0001*\u00020\r2\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0014\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00122\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0013\u001a\u0012\u0010\u001e\u001a\u00020\u0001*\u00020\u001f2\u0006\u0010 \u001a\u00020!\u001a\u0012\u0010\u001e\u001a\u00020\u0001*\u00020\"2\u0006\u0010 \u001a\u00020!\u001a_\u0010%\u001a\u00020&*\u00020'2\u0006\u0010(\u001a\u00020'2K\u0010)\u001aG\u0012\u0013\u0012\u00110+¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110+¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(/\u0012\u0013\u0012\u001100¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020&0*\u001aD\u0010%\u001a\u00020&2\f\u00102\u001a\b\u0012\u0004\u0012\u00020+032\f\u00104\u001a\b\u0012\u0004\u0012\u00020+032\u001e\u0010)\u001a\u001a\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020&0*H\u0002\"\u0018\u0010\u0015\u001a\u00020\u0001*\u00020\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001b\"\u001c\u0010\u001c\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d\"\u0019\u0010#\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00128F¢\u0006\u0006\u001a\u0004\b$\u0010\u001d¨\u00065"}, d2 = {"isInsideExpectClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "isInsideExternalClass", "isInsideSpecificClass", "predicate", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lkotlin/jvm/functions/Function1;)Z", "isEffectivelyFinal", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isFinal", "isEffectivelyExpect", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "isEffectivelyExternal", "canHaveOpenMembers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getCanHaveOpenMembers", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Z", "isLocalDeclaredInBlock", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "isExtensionMember", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "needsMultiFieldValueClassFlattening", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "hasExplicitReturnType", "getHasExplicitReturnType", "checkValueParameterNamesWith", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "otherFunctionSymbol", "reportAction", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "currentParameter", "conflictingParameter", Argument.Delimiters.none, "parameterIndex", "symbols", Argument.Delimiters.none, "otherSymbols", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeclarationUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    private static final void checkValueParameterNamesWith(List<FirValueParameterSymbol> list, List<FirValueParameterSymbol> list2, Function3<? super FirValueParameterSymbol, ? super FirValueParameterSymbol, ? super Integer, Unit> function3) {
        int i = 0;
        for (Pair pair : CollectionsKt.zip(list, list2)) {
            int i2 = i + 1;
            FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) pair.component1();
            FirValueParameterSymbol firValueParameterSymbol2 = (FirValueParameterSymbol) pair.component2();
            if (!FirValueParameterKindKt.isLegacyContextReceiver((FirValueParameter) firValueParameterSymbol.getFir()) && !FirValueParameterKindKt.isLegacyContextReceiver((FirValueParameter) firValueParameterSymbol2.getFir()) && !Intrinsics.areEqual(firValueParameterSymbol.getName(), firValueParameterSymbol2.getName())) {
                function3.invoke(firValueParameterSymbol, firValueParameterSymbol2, Integer.valueOf(i));
            }
            i = i2;
        }
    }

    public static final boolean getCanHaveOpenMembers(FirClass firClass) {
        firClass.getClass();
        return FirHelpersKt.modality(firClass) != Modality.FINAL || firClass.getClassKind() == ClassKind.ENUM_CLASS;
    }

    public static final boolean getHasExplicitReturnType(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        FirResolvedTypeRef resolvedReturnTypeRef = firCallableSymbol.getResolvedReturnTypeRef();
        return resolvedReturnTypeRef.getDelegatedTypeRef() != null || (resolvedReturnTypeRef instanceof FirImplicitUnitTypeRef);
    }

    public static final boolean isEffectivelyExpect(CheckerContext checkerContext, FirMemberDeclaration firMemberDeclaration, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firMemberDeclaration.getClass();
        if (firMemberDeclaration.getStatus().isExpect()) {
            return true;
        }
        return firClassSymbol != null && isInsideExpectClass(checkerContext, firClassSymbol);
    }

    public static final boolean isEffectivelyExternal(CheckerContext checkerContext, FirMemberDeclaration firMemberDeclaration, FirClassSymbol<?> firClassSymbol) {
        FirProperty firProperty;
        FirPropertyAccessor getter;
        FirPropertyAccessor setter;
        checkerContext.getClass();
        firMemberDeclaration.getClass();
        if (firMemberDeclaration.getStatus().isExternal()) {
            return true;
        }
        if (firMemberDeclaration instanceof FirPropertyAccessor) {
            Object objLast = CollectionsKt.last(checkerContext.getContainingDeclarations());
            objLast.getClass();
            return isEffectivelyExternal(checkerContext, (FirPropertySymbol) objLast, firClassSymbol);
        }
        if (!(firMemberDeclaration instanceof FirProperty) || (getter = (firProperty = (FirProperty) firMemberDeclaration).getGetter()) == null || !getter.getStatus().isExternal() || (firProperty.getIsVar() && ((setter = firProperty.getSetter()) == null || !setter.getStatus().isExternal()))) {
            return firClassSymbol != null && isInsideExternalClass(checkerContext, firClassSymbol);
        }
        return true;
    }

    public static final boolean isEffectivelyFinal(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (isFinal(firBasedSymbol)) {
            return true;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
        FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
        if (firClassSymbol == null) {
            return true;
        }
        return firClassSymbol.getClassKind() != ClassKind.ENUM_CLASS && firClassSymbol.getResolvedStatus().getModality() == Modality.FINAL;
    }

    public static final boolean isExtensionMember(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return (firCallableSymbol.getResolvedReceiverTypeRef() == null || firCallableSymbol.getDispatchReceiverType() == null) ? false : true;
    }

    private static final boolean isFinal(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getResolvedStatus().getModality() == Modality.FINAL;
        }
        return !(firBasedSymbol instanceof FirClassLikeSymbol) || ((FirClassLikeSymbol) firBasedSymbol).getResolvedStatus().getModality() == Modality.FINAL;
    }

    public static final boolean isInsideExpectClass(CheckerContext checkerContext, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firClassSymbol.getClass();
        if ((firClassSymbol instanceof FirRegularClassSymbol) && firClassSymbol.getRawStatus().isExpect()) {
            return true;
        }
        List<FirBasedSymbol> listAsReversed = CollectionsKt.asReversed(checkerContext.getContainingDeclarations());
        if ((listAsReversed instanceof Collection) && listAsReversed.isEmpty()) {
            return false;
        }
        for (FirBasedSymbol firBasedSymbol : listAsReversed) {
            if (firBasedSymbol instanceof FirRegularClassSymbol) {
                FirClassSymbol firClassSymbol2 = (FirClassSymbol) firBasedSymbol;
                if ((firClassSymbol2 instanceof FirRegularClassSymbol) && firClassSymbol2.getRawStatus().isExpect()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isInsideExternalClass(CheckerContext checkerContext, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firClassSymbol.getClass();
        if ((firClassSymbol instanceof FirRegularClassSymbol) && firClassSymbol.getRawStatus().isExternal()) {
            return true;
        }
        List<FirBasedSymbol> listAsReversed = CollectionsKt.asReversed(checkerContext.getContainingDeclarations());
        if ((listAsReversed instanceof Collection) && listAsReversed.isEmpty()) {
            return false;
        }
        for (FirBasedSymbol firBasedSymbol : listAsReversed) {
            if (firBasedSymbol instanceof FirRegularClassSymbol) {
                FirClassSymbol firClassSymbol2 = (FirClassSymbol) firBasedSymbol;
                if ((firClassSymbol2 instanceof FirRegularClassSymbol) && firClassSymbol2.getRawStatus().isExternal()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isLocalDeclaredInBlock(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        if (firDeclaration instanceof FirClassLikeDeclaration) {
            return ((FirClassLikeDeclaration) firDeclaration).getIsLocal();
        }
        FirBasedSymbol<FirDeclaration> symbol = firDeclaration.getSymbol();
        if (symbol instanceof FirLocalPropertySymbol) {
            return true;
        }
        if (symbol instanceof FirNamedFunctionSymbol) {
            return Intrinsics.areEqual(((FirNamedFunctionSymbol) symbol).getRawStatus().getVisibility(), Visibilities.Local.INSTANCE);
        }
        if (symbol instanceof FirAnonymousFunctionSymbol) {
            return true;
        }
        if (symbol instanceof FirBackingFieldSymbol) {
            return ((FirBackingFieldSymbol) symbol).getPropertySymbol() instanceof FirLocalPropertySymbol;
        }
        return false;
    }

    public static final boolean needsMultiFieldValueClassFlattening(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        return typeContext.isMultiFieldValueClass(typeContext.typeConstructor(coneKotlinType)) && !ConeTypeUtilsKt.isMarkedNullable(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null));
    }

    public static final boolean isEffectivelyExpect(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firCallableSymbol.getClass();
        return isEffectivelyExpect(checkerContext, (FirMemberDeclaration) firCallableSymbol.getFir(), firClassSymbol);
    }

    public static final boolean needsMultiFieldValueClassFlattening(FirTypeRef firTypeRef, FirSession firSession) {
        firTypeRef.getClass();
        firSession.getClass();
        return needsMultiFieldValueClassFlattening(FirTypeUtilsKt.getConeType(firTypeRef), firSession);
    }

    public static final boolean isEffectivelyFinal(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return isEffectivelyFinal(firMemberDeclaration.getSymbol());
    }

    public static final void checkValueParameterNamesWith(FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2, Function3<? super FirValueParameterSymbol, ? super FirValueParameterSymbol, ? super Integer, Unit> function3) {
        firNamedFunctionSymbol.getClass();
        firNamedFunctionSymbol2.getClass();
        function3.getClass();
        checkValueParameterNamesWith(firNamedFunctionSymbol.getValueParameterSymbols(), firNamedFunctionSymbol2.getValueParameterSymbols(), function3);
        checkValueParameterNamesWith(firNamedFunctionSymbol.getContextParameterSymbols(), firNamedFunctionSymbol2.getContextParameterSymbols(), function3);
    }

    public static final boolean isEffectivelyExternal(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firCallableSymbol.getClass();
        return isEffectivelyExternal(checkerContext, (FirMemberDeclaration) firCallableSymbol.getFir(), firClassSymbol);
    }
}

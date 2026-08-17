package org.jetbrains.kotlin.fir.declarations.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.DirectDeclarationsAccess;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u00100\u001a\u0004\u0018\u00010-*\u0006\u0012\u0002\b\u00030&2\u0006\u00101\u001a\u000202\u001a\n\u00109\u001a\u00020\u001a*\u00020:\u001a\f\u0010;\u001a\u0004\u0018\u00010<*\u00020=\u001a\u0010\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u000b*\u00020\"\u001a\n\u0010@\u001a\u00020\u001a*\u00020A\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b*\u00020\f8F¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u000e\"(\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b*\u00020\f8FX\u0087\u0004r\u0002\b\u0018¢\u0006\f\u0012\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0017\u0010\u000e\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u001b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001c\"\u0016\u0010\u001d\u001a\u00020\u001a*\u00020\u001b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001c\"\u0016\u0010\u001e\u001a\u00020\u001a*\u00020\u001b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001c\"\u0016\u0010\u001f\u001a\u00020\u001a*\u00020\u001b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001c\"\u0015\u0010 \u001a\u00020\u001a*\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b \u0010\u001c\"\u0015\u0010!\u001a\u00020\u001a*\u00020\"8F¢\u0006\u0006\u001a\u0004\b!\u0010#\"\u0015\u0010$\u001a\u00020\u001a*\u00020\"8F¢\u0006\u0006\u001a\u0004\b$\u0010#\"\u0019\u0010%\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030&8F¢\u0006\u0006\u001a\u0004\b%\u0010'\"\u001b\u0010(\u001a\u0004\u0018\u00010)*\u0006\u0012\u0002\b\u00030&8F¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0015\u0010,\u001a\u00020)*\u00020-8F¢\u0006\u0006\u001a\u0004\b.\u0010/\"\u0015\u00103\u001a\u00020\u001a*\u0002048F¢\u0006\u0006\u001a\u0004\b3\u00105\"\u0015\u00106\u001a\u00020\u001a*\u0002078F¢\u0006\u0006\u001a\u0004\b6\u00108¨\u0006B"}, d2 = {"expandedConeType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "getExpandedConeType", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getClassId", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/name/ClassId;", "superConeTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getSuperConeTypes", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Ljava/util/List;", "anonymousInitializers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "getAnonymousInitializers$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "getAnonymousInitializers", "delegateFields", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "getDelegateFields$annotations", "getDelegateFields", "Lorg/jetbrains/kotlin/fir/declarations/DirectDeclarationsAccess;", "isJava", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "isFromLibrary", "isPrecompiled", "isSynthetic", "isLocal", "isExtension", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "isInstanceExtension", "isMemberDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "memberDeclarationNameOrNull", "Lorg/jetbrains/kotlin/name/Name;", "getMemberDeclarationNameOrNull", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/name/Name;", "nameOrSpecialName", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "getNameOrSpecialName", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Lorg/jetbrains/kotlin/name/Name;", "asMemberDeclarationResolvedTo", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "isMethodOfAny", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", "isErrorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;)Z", "isDestructuredParameter", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getDestructuredParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "contextParametersForFunctionOrContainingProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "hasGeneratedDelegateBody", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationUtilKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final FirMemberDeclaration asMemberDeclarationResolvedTo(FirBasedSymbol<?> firBasedSymbol, FirResolvePhase firResolvePhase) throws KotlinIllegalArgumentExceptionWithAttachments {
        firBasedSymbol.getClass();
        firResolvePhase.getClass();
        FirDeclaration fir = firBasedSymbol.getFir();
        FirMemberDeclaration firMemberDeclaration = fir instanceof FirMemberDeclaration ? (FirMemberDeclaration) fir : null;
        if (firMemberDeclaration == null) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, firResolvePhase);
        return firMemberDeclaration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<FirValueParameter> contextParametersForFunctionOrContainingProperty(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return firCallableDeclaration instanceof FirPropertyAccessor ? ((FirProperty) ((FirPropertyAccessor) firCallableDeclaration).getPropertySymbol().getFir()).getContextParameters() : firCallableDeclaration.getContextParameters();
    }

    public static final List<FirAnonymousInitializer> getAnonymousInitializers(FirClass firClass) {
        firClass.getClass();
        List<FirDeclaration> declarations = firClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirAnonymousInitializer) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ void getAnonymousInitializers$annotations(FirClass firClass) {
    }

    public static final ClassId getClassId(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        return firClassLikeDeclaration.getSymbol().getClassId();
    }

    public static final List<FirField> getDelegateFields(FirClass firClass) {
        firClass.getClass();
        List<FirDeclaration> declarations = firClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirField) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((FirField) obj2).getOrigin() instanceof FirDeclarationOrigin.Synthetic) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }

    @DirectDeclarationsAccess
    public static /* synthetic */ void getDelegateFields$annotations(FirClass firClass) {
    }

    public static final FirValueParameterSymbol getDestructuredParameter(FirVariable firVariable) {
        firVariable.getClass();
        FirExpression initializer = firVariable.getInitializer();
        if (!(initializer instanceof FirComponentCall)) {
            return null;
        }
        FirComponentCall firComponentCall = (FirComponentCall) initializer;
        KtSourceElement source = firComponentCall.getSource();
        if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredComponentFunctionCall)) {
            return null;
        }
        FirExpression dispatchReceiver = firComponentCall.getDispatchReceiver();
        if ((dispatchReceiver == null && (dispatchReceiver = firComponentCall.getExtensionReceiver()) == null) || !(dispatchReceiver instanceof FirPropertyAccessExpression)) {
            return null;
        }
        FirNamedReference calleeReference = ((FirPropertyAccessExpression) dispatchReceiver).getCalleeReference();
        FirResolvedNamedReference firResolvedNamedReference = calleeReference instanceof FirResolvedNamedReference ? (FirResolvedNamedReference) calleeReference : null;
        if (firResolvedNamedReference == null) {
            return null;
        }
        FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
        if (resolvedSymbol instanceof FirValueParameterSymbol) {
            return (FirValueParameterSymbol) resolvedSymbol;
        }
        return null;
    }

    public static final ConeClassLikeType getExpandedConeType(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        FirResolvedTypeRef expandedTypeRef = firTypeAlias.getExpandedTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = expandedTypeRef instanceof FirResolvedTypeRef ? expandedTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        return (ConeClassLikeType) (coneType instanceof ConeClassLikeType ? coneType : null);
    }

    public static final Name getMemberDeclarationNameOrNull(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        FirDeclaration fir = firBasedSymbol.getFir();
        FirMemberDeclaration firMemberDeclaration = fir instanceof FirMemberDeclaration ? (FirMemberDeclaration) fir : null;
        if (firMemberDeclaration != null) {
            return getNameOrSpecialName(firMemberDeclaration);
        }
        return null;
    }

    public static final Name getNameOrSpecialName(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        if (firMemberDeclaration instanceof FirCallableDeclaration) {
            return ((FirCallableDeclaration) firMemberDeclaration).getSymbol().getName();
        }
        if (firMemberDeclaration instanceof FirClassLikeDeclaration) {
            return getClassId((FirClassLikeDeclaration) firMemberDeclaration).getShortClassName();
        }
        bu8.a();
        return null;
    }

    public static final List<ConeClassLikeType> getSuperConeTypes(FirClass firClass) {
        firClass.getClass();
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = superTypeRefs.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) it.next();
            FirResolvedTypeRef firResolvedTypeRef2 = firResolvedTypeRef instanceof FirResolvedTypeRef ? firResolvedTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) (coneType instanceof ConeClassLikeType ? coneType : null);
            if (coneClassLikeType != null) {
                arrayList.add(coneClassLikeType);
            }
        }
        return arrayList;
    }

    public static final boolean hasGeneratedDelegateBody(FirPropertyAccessor firPropertyAccessor) {
        List<FirStatement> statements;
        FirStatement firStatement;
        KtSourceElement source;
        firPropertyAccessor.getClass();
        FirBlock body = firPropertyAccessor.getBody();
        return Intrinsics.areEqual((body == null || (statements = body.getStatements()) == null || (firStatement = (FirStatement) CollectionsKt.firstOrNull(statements)) == null || (source = firStatement.getSource()) == null) ? null : source.getKind(), KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE);
    }

    public static final boolean isDestructuredParameter(FirStatement firStatement) {
        firStatement.getClass();
        return (firStatement instanceof FirVariable) && getDestructuredParameter((FirVariable) firStatement) != null;
    }

    public static final boolean isErrorPrimaryConstructor(FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        return firConstructorSymbol.getFir() instanceof FirErrorPrimaryConstructor;
    }

    public static final boolean isExtension(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return firCallableDeclaration.getReceiverParameter() != null;
    }

    public static final boolean isFromLibrary(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Library.INSTANCE) || Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Java.Library.INSTANCE);
    }

    public static final boolean isInstanceExtension(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return isExtension(firCallableDeclaration) && !firCallableDeclaration.getStatus().isStatic();
    }

    public static final boolean isJava(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Java;
    }

    public static final boolean isLocal(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        if (firDeclaration instanceof FirFile) {
            return false;
        }
        if (firDeclaration instanceof FirMemberDeclaration) {
            return ((FirMemberDeclaration) firDeclaration).getIsLocal();
        }
        return true;
    }

    public static final boolean isMemberDeclaration(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        return firBasedSymbol.getFir() instanceof FirMemberDeclaration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isMethodOfAny(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        ConeKotlinType resolvedReturnType;
        firNamedFunctionSymbol.getClass();
        if (FirSymbolStatusUtilsKt.isExtension(firNamedFunctionSymbol) || FirCallableSymbolKt.getHasContextParameters(firNamedFunctionSymbol)) {
            return false;
        }
        Name name = firNamedFunctionSymbol.getName();
        if (Intrinsics.areEqual(name, OperatorNameConventions.EQUALS)) {
            FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.singleOrNull(firNamedFunctionSymbol.getValueParameterSymbols());
            return (firValueParameterSymbol == null || (resolvedReturnType = firValueParameterSymbol.getResolvedReturnType()) == null || !ConeBuiltinTypeUtilsKt.isNullableAny(resolvedReturnType)) ? false : true;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.HASH_CODE) || Intrinsics.areEqual(name, OperatorNameConventions.TO_STRING)) {
            return ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters().isEmpty();
        }
        return false;
    }

    public static final boolean isPrecompiled(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Precompiled.INSTANCE);
    }

    public static final boolean isSynthetic(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic;
    }
}

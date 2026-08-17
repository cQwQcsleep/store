package org.jetbrains.kotlin.fir.java.scopes;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.java.scopes.JavaScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.load.java.BuiltinSpecialProperties;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a/\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\f\b\u0000\u0010\b*\u0006\u0012\u0002\b\u00030\u0002*\u0002H\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\t\u001a \u0010\n\u001a\u0004\u0018\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0018\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u000e\u0010\r\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e\u001a*\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u0012H\u0002\u001aq\u0010\u0013\u001a\u0004\u0018\u0001H\b\"\f\b\u0000\u0010\b*\u0006\u0012\u0002\b\u00030\u0002*\u0002H\b2\u0006\u0010\u0003\u001a\u00020\u00042/\u0010\u0014\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00160\u0012\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u00172\u0014\b\b\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0082\b¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"doesOverrideBuiltinWithDifferentJvmName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "containingScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getOverriddenBuiltinWithDifferentJvmName", "T", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getJvmMethodNameIfSpecial", "Lorg/jetbrains/kotlin/name/Name;", "isFromBuiltinClass", "isBuiltinClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "firstOverriddenFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "predicate", "Lkotlin/Function1;", "firstOverriddenCallable", "processFunction", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaScopeUtilsKt {
    public static boolean a(FirSession firSession, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return BuiltinMethodsWithDifferentJvmName.INSTANCE.isBuiltinFunctionWithDifferentNameInJvm(firNamedFunctionSymbol, firSession);
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope, FirSession firSession) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        firSession.getClass();
        return getOverriddenBuiltinWithDifferentJvmName(firCallableSymbol, firTypeScope, firSession) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirNamedFunctionSymbol firstOverriddenFunction(FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope, final Function1<? super FirNamedFunctionSymbol, Boolean> function1) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FirScopeKt.processOverriddenFunctionsAndSelf(firTypeScope, firNamedFunctionSymbol, new Function1() { // from class: org.jetbrains.kotlin.fir.java.scopes.JavaScopeUtilsKt$firstOverriddenCallable$1
            public final ProcessorAction invoke(FirCallableSymbol firCallableSymbol) {
                firCallableSymbol.getClass();
                if (!((Boolean) function1.invoke(firCallableSymbol)).booleanValue()) {
                    return ProcessorAction.NEXT;
                }
                objectRef.element = firCallableSymbol;
                return ProcessorAction.STOP;
            }
        });
        return (FirNamedFunctionSymbol) ((FirCallableSymbol) objectRef.element);
    }

    public static final Name getJvmMethodNameIfSpecial(FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope, FirSession firSession) {
        String strAsString;
        Name jvmName;
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        firSession.getClass();
        FirCallableSymbol<?> overriddenBuiltinWithDifferentJvmName = getOverriddenBuiltinWithDifferentJvmName(firCallableSymbol, firTypeScope, firSession);
        if (overriddenBuiltinWithDifferentJvmName == null) {
            return null;
        }
        if (overriddenBuiltinWithDifferentJvmName instanceof FirPropertySymbol) {
            strAsString = ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(overriddenBuiltinWithDifferentJvmName, firTypeScope);
        } else {
            strAsString = (!(overriddenBuiltinWithDifferentJvmName instanceof FirNamedFunctionSymbol) || (jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((FirNamedFunctionSymbol) overriddenBuiltinWithDifferentJvmName)) == null) ? null : jvmName.asString();
        }
        if (strAsString == null) {
            return null;
        }
        return Name.identifier(strAsString);
    }

    public static final <T extends FirCallableSymbol<?>> T getOverriddenBuiltinWithDifferentJvmName(T t, FirTypeScope firTypeScope, final FirSession firSession) {
        t.getClass();
        firTypeScope.getClass();
        firSession.getClass();
        if (!SpecialGenericSignatures.Companion.getORIGINAL_SHORT_NAMES().contains(t.getName()) && !BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(t.getName())) {
            return null;
        }
        if (t instanceof FirNamedFunctionSymbol) {
            return firstOverriddenFunction((FirNamedFunctionSymbol) t, firTypeScope, new Function1() { // from class: gl7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(JavaScopeUtilsKt.a(firSession, (FirNamedFunctionSymbol) obj));
                }
            });
        }
        if (t instanceof FirPropertySymbol) {
            return (T) ClassicBuiltinSpecialProperties.INSTANCE.findBuiltinSpecialPropertyFqName(t, firTypeScope);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isBuiltinClass(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        if (((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getOrigin().isBuiltIns()) {
            return true;
        }
        return firClassLikeSymbol.getClassId().getPackageFqName().startsWith(StandardNames.BUILT_INS_PACKAGE_NAME) && JavaToKotlinClassMap.INSTANCE.isMappedKotlinClass(firClassLikeSymbol.getClassId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFromBuiltinClass(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
        FirClassLikeSymbol<?> symbol;
        ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firCallableSymbol);
        return (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull, firSession)) == null || !isBuiltinClass(symbol)) ? false : true;
    }
}

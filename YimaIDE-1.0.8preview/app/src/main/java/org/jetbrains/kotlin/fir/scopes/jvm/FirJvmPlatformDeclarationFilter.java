package org.jetbrains.kotlin.fir.scopes.jvm;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.scopes.FirPlatformDeclarationFilter;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.jvm.FirJvmPlatformDeclarationFilter;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/FirJvmPlatformDeclarationFilter;", Argument.Delimiters.none, "<init>", "()V", "isFunctionAvailable", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "javaClassScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmPlatformDeclarationFilter {
    public static final FirJvmPlatformDeclarationFilter INSTANCE = new FirJvmPlatformDeclarationFilter();

    private FirJvmPlatformDeclarationFilter() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit a(String str, Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (Intrinsics.areEqual(SignatureUtilsKt.computeJvmDescriptor$default((FirFunction) firNamedFunctionSymbol.getFir(), null, false, null, 7, null), str)) {
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    public final boolean isFunctionAvailable(FirNamedFunction function, FirTypeScope javaClassScope, FirSession session) {
        function.getClass();
        javaClassScope.getClass();
        session.getClass();
        if (FirPlatformDeclarationFilter.INSTANCE.isNotPlatformDependent(function, session)) {
            return true;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final String strComputeJvmDescriptor$default = SignatureUtilsKt.computeJvmDescriptor$default(function, null, false, null, 7, null);
        javaClassScope.processFunctionsByName(function.getName(), new Function1() { // from class: x95
            public final Object invoke(Object obj) {
                return FirJvmPlatformDeclarationFilter.a(strComputeJvmDescriptor$default, booleanRef, (FirNamedFunctionSymbol) obj);
            }
        });
        return booleanRef.element;
    }
}

package org.jetbrains.kotlin.fir.extensions;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.extensions.GeneratedDeclarationsUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\b"}, d2 = {"generatedNestedClassifiers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "generatedMembers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GeneratedDeclarationsUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static Unit a(List list, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        if (((FirConstructor) firConstructorSymbol.getFir()).getOrigin().getGenerated()) {
            list.add(firConstructorSymbol.getFir());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (((FirVariable) firVariableSymbol.getFir()).getOrigin().getGenerated()) {
            list.add(firVariableSymbol.getFir());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static Unit c(List list, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if (firClassifierSymbol.getFir().getOrigin().getGenerated()) {
            if (!(firClassifierSymbol instanceof FirClassLikeSymbol)) {
                dt1.a("Plugins can not generate type parameters, but had ", firClassifierSymbol);
                return null;
            }
            list.add(((FirClassLikeSymbol) firClassifierSymbol).getFir());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit d(List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (((FirNamedFunction) firNamedFunctionSymbol.getFir()).getOrigin().getGenerated()) {
            list.add(firNamedFunctionSymbol.getFir());
        }
        return Unit.INSTANCE;
    }

    public static final List<FirCallableDeclaration> generatedMembers(FirRegularClass firRegularClass, FirSession firSession) {
        firRegularClass.getClass();
        firSession.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope = FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firRegularClass, (FirResolvePhase) null);
        final ArrayList arrayList = new ArrayList();
        for (Name name : firContainingNamesAwareScopeDeclaredMemberScope.getCallableNames()) {
            firContainingNamesAwareScopeDeclaredMemberScope.processFunctionsByName(name, new Function1() { // from class: sy5
                public final Object invoke(Object obj) {
                    return GeneratedDeclarationsUtilsKt.d(arrayList, (FirNamedFunctionSymbol) obj);
                }
            });
            firContainingNamesAwareScopeDeclaredMemberScope.processPropertiesByName(name, new Function1() { // from class: ty5
                public final Object invoke(Object obj) {
                    return GeneratedDeclarationsUtilsKt.b(arrayList, (FirVariableSymbol) obj);
                }
            });
        }
        firContainingNamesAwareScopeDeclaredMemberScope.processDeclaredConstructors(new Function1() { // from class: uy5
            public final Object invoke(Object obj) {
                return GeneratedDeclarationsUtilsKt.a(arrayList, (FirConstructorSymbol) obj);
            }
        });
        return arrayList;
    }

    public static final List<FirClassLikeDeclaration> generatedNestedClassifiers(FirRegularClass firRegularClass, FirSession firSession) {
        firRegularClass.getClass();
        firSession.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope = FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firRegularClass, (FirResolvePhase) null);
        final ArrayList arrayList = new ArrayList();
        for (Name name : firContainingNamesAwareScopeDeclaredMemberScope.getClassifierNames()) {
            final Function1 function1 = new Function1() { // from class: vy5
                public final Object invoke(Object obj) {
                    return GeneratedDeclarationsUtilsKt.c(arrayList, (FirClassifierSymbol) obj);
                }
            };
            firContainingNamesAwareScopeDeclaredMemberScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.extensions.GeneratedDeclarationsUtilsKt$generatedNestedClassifiers$$inlined$processClassifiersByName$1
                public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                    firClassifierSymbol.getClass();
                    coneSubstitutor.getClass();
                    function1.invoke(firClassifierSymbol);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                    return Unit.INSTANCE;
                }
            });
        }
        return arrayList;
    }
}

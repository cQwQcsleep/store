package org.jetbrains.kotlin.fir.java.scopes;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassStaticEnhancementScope;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\r0\u0011H\u0016J$\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0011H\u0016J\u001c\u0010\u0015\u001a\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\r0\u0011H\u0016J\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0017b\u0002\b\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassStaticEnhancementScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirDelegatingContainingNamesAwareScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "useSiteStaticScope", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassStaticUseSiteScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassStaticUseSiteScope;)V", "signatureEnhancement", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;", "processPropertiesByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaClassStaticEnhancementScope extends FirDelegatingContainingNamesAwareScope {
    private final FirRegularClassSymbol owner;
    private final FirSignatureEnhancement signatureEnhancement;
    private final JavaClassStaticUseSiteScope useSiteStaticScope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public JavaClassStaticEnhancementScope(FirSession firSession, FirRegularClassSymbol firRegularClassSymbol, JavaClassStaticUseSiteScope javaClassStaticUseSiteScope) {
        super(javaClassStaticUseSiteScope);
        firSession.getClass();
        firRegularClassSymbol.getClass();
        javaClassStaticUseSiteScope.getClass();
        this.owner = firRegularClassSymbol;
        this.useSiteStaticScope = javaClassStaticUseSiteScope;
        this.signatureEnhancement = new FirSignatureEnhancement((FirRegularClass) firRegularClassSymbol.getFir(), firSession, false, new Function1() { // from class: vb7
            public final Object invoke(Object obj) {
                return JavaClassStaticEnhancementScope.e((FirCallableDeclaration) obj);
            }
        }, 4, null);
    }

    public static Unit b(JavaClassStaticEnhancementScope javaClassStaticEnhancementScope, Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        function1.invoke(javaClassStaticEnhancementScope.signatureEnhancement.enhancedConstructor(firConstructorSymbol));
        return Unit.INSTANCE;
    }

    public static Unit c(Function1 function1, JavaClassStaticEnhancementScope javaClassStaticEnhancementScope, Name name, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        function1.invoke(javaClassStaticEnhancementScope.signatureEnhancement.enhancedProperty(firVariableSymbol, name));
        return Unit.INSTANCE;
    }

    public static Unit d(JavaClassStaticEnhancementScope javaClassStaticEnhancementScope, Name name, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        function1.invoke(FirSignatureEnhancement.enhancedFunction$default(javaClassStaticEnhancementScope.signatureEnhancement, firNamedFunctionSymbol, name, null, 4, null));
        return Unit.INSTANCE;
    }

    public static List e(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.useSiteStaticScope.processDeclaredConstructors(new Function1() { // from class: sb7
            public final Object invoke(Object obj) {
                return JavaClassStaticEnhancementScope.b(this.b, processor, (FirConstructorSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(final Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteStaticScope.processFunctionsByName(name, new Function1() { // from class: tb7
            public final Object invoke(Object obj) {
                return JavaClassStaticEnhancementScope.d(this.b, name, processor, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(final Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteStaticScope.processPropertiesByName(name, new Function1() { // from class: ub7
            public final Object invoke(Object obj) {
                return JavaClassStaticEnhancementScope.c(processor, this, name, (FirVariableSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public JavaClassStaticEnhancementScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        JavaClassStaticUseSiteScope javaClassStaticUseSiteScopeWithReplacedSessionOrNull = this.useSiteStaticScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (javaClassStaticUseSiteScopeWithReplacedSessionOrNull != null) {
            return new JavaClassStaticEnhancementScope(newSession, this.owner, javaClassStaticUseSiteScopeWithReplacedSessionOrNull);
        }
        return null;
    }
}

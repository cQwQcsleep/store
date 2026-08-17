package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u0016J\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0017b\u0002\b\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedClassNestedClassifierScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "storage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$ClassifierStorage;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage$ClassifierStorage;)V", "getNestedClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "isEmpty", Argument.Delimiters.none, "getClassifierNames", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirGeneratedClassNestedClassifierScope extends FirNestedClassifierScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirGeneratedMemberDeclarationsStorage.ClassifierStorage storage;

    private FirGeneratedClassNestedClassifierScope(FirSession firSession, FirClass firClass, FirGeneratedMemberDeclarationsStorage.ClassifierStorage classifierStorage) {
        super(firClass, firSession);
        this.storage = classifierStorage;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return this.storage.getAllClassifierNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope
    public FirRegularClassSymbol getNestedClassSymbol(Name name) {
        name.getClass();
        return (FirRegularClassSymbol) this.storage.getClassifiersCache().getValue(name, null);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope
    public boolean isEmpty() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirGeneratedClassNestedClassifierScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return null;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedClassNestedClassifierScope$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedClassNestedClassifierScope;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "regularNestedClassifierScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final FirGeneratedClassNestedClassifierScope create(FirSession useSiteSession, FirClassSymbol<?> classSymbol, FirNestedClassifierScope regularNestedClassifierScope) {
            useSiteSession.getClass();
            classSymbol.getClass();
            FirGeneratedMemberDeclarationsStorage.ClassifierStorage classifierStorage$org_jetbrains_kotlin_providers = FirGeneratedScopesKt.getGeneratedDeclarationsStorage(classSymbol.getModuleData().getSession()).getClassifierStorage$org_jetbrains_kotlin_providers(classSymbol, regularNestedClassifierScope);
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (classifierStorage$org_jetbrains_kotlin_providers == null) {
                return null;
            }
            return new FirGeneratedClassNestedClassifierScope(useSiteSession, (FirClass) classSymbol.getFir(), classifierStorage$org_jetbrains_kotlin_providers, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirGeneratedClassNestedClassifierScope(FirSession firSession, FirClass firClass, FirGeneratedMemberDeclarationsStorage.ClassifierStorage classifierStorage, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firClass, classifierStorage);
    }
}

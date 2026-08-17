package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\nH\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0016J\u001c\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0017b\u0002\b\u001aR+\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScopeImpl;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/FirSession;)V", "classIndex", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getClassIndex", "()Ljava/util/Map;", "classIndex$delegate", "Lkotlin/Lazy;", "getNestedClassSymbol", ModuleXmlParser.NAME, "isEmpty", Argument.Delimiters.none, "getClassifierNames", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNestedClassifierScopeImpl extends FirNestedClassifierScope {

    /* JADX INFO: renamed from: classIndex$delegate, reason: from kotlin metadata */
    private final Lazy classIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirNestedClassifierScopeImpl(final FirClass firClass, FirSession firSession) {
        super(firClass, firSession);
        firClass.getClass();
        firSession.getClass();
        this.classIndex = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: gb5
            public final Object invoke() {
                return FirNestedClassifierScopeImpl.b(firClass);
            }
        });
    }

    public static Map b(FirClass firClass) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
            if (firDeclaration instanceof FirRegularClass) {
                FirRegularClass firRegularClass = (FirRegularClass) firDeclaration;
                linkedHashMap.put(firRegularClass.getName(), firRegularClass.getSymbol());
            } else if (firDeclaration instanceof FirTypeAlias) {
                FirTypeAlias firTypeAlias = (FirTypeAlias) firDeclaration;
                linkedHashMap.put(firTypeAlias.getName(), firTypeAlias.getSymbol());
            }
        }
        return linkedHashMap;
    }

    private final Map<Name, FirClassLikeSymbol<?>> getClassIndex() {
        return (Map) this.classIndex.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return getClassIndex().keySet();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope
    public FirClassLikeSymbol<?> getNestedClassSymbol(Name name) {
        name.getClass();
        return getClassIndex().get(name);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope
    public boolean isEmpty() {
        return getClassIndex().isEmpty();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirNestedClassifierScopeImpl withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirNestedClassifierScopeImpl(getKlass(), newSession);
    }
}

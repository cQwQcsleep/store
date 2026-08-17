package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractSimpleImportingScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\nH\u0014J.\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\n2\u001c\u0010\u0015\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00140\u0016H\u0016J$\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aH\u0016J(\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\n2\u0016\u0010\u0015\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0012\u0004\u0012\u00020\u00140\u001aH\u0016J\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0012\u001a\u00020\nH\u0017b\u0002\b J\u001c\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0005H'b\u0002\b$R$\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractSimpleImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractImportingScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "simpleImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "getSimpleImports", "()Ljava/util/Map;", "isExcluded", Argument.Delimiters.none, "import", ModuleXmlParser.NAME, "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processFunctionsByName", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "findEnumEntryWithoutResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractSimpleImportingScope extends FirAbstractImportingScope {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractSimpleImportingScope(FirSession firSession, ScopeSession scopeSession) {
        super(firSession, scopeSession, true);
        firSession.getClass();
        scopeSession.getClass();
    }

    public static Unit b(Function2 function2, FirClassLikeSymbol firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        function2.invoke(firClassLikeSymbol, ConeSubstitutor.Empty.INSTANCE);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope
    @FirImplementationDetail
    public FirEnumEntrySymbol findEnumEntryWithoutResolution(Name name) {
        name.getClass();
        List<FirResolvedImport> list = getSimpleImports().get(name);
        if (list == null) {
            return null;
        }
        return doFindEnumEntryWithoutResolution(null, list);
    }

    public abstract Map<Name, List<FirResolvedImport>> getSimpleImports();

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope
    public boolean isExcluded(FirResolvedImport firResolvedImport, Name name) {
        firResolvedImport.getClass();
        name.getClass();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, final Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        List<FirResolvedImport> list = getSimpleImports().get(name);
        if (list == null) {
            return;
        }
        processClassifiersFromImportsByName(null, list, new Function1() { // from class: xx4
            public final Object invoke(Object obj) {
                return FirAbstractSimpleImportingScope.b(processor, (FirClassLikeSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        List<FirResolvedImport> list = getSimpleImports().get(name);
        if (list == null) {
            return;
        }
        processFunctionsByName(null, list, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        List<FirResolvedImport> list = getSimpleImports().get(name);
        if (list == null) {
            return;
        }
        processPropertiesByName(null, list, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract FirAbstractSimpleImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);
}

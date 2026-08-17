package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J.\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u001c\u0010\u001c\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001b0\u001dH\u0016J$\u0010 \u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001b0!H\u0016J(\u0010#\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010\u001c\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030$\u0012\u0004\u0012\u00020\u001b0!H\u0016J\u0016\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u0016\u001a\u00020\u0017H\u0017b\u0002\b'J\u001c\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0005H'b\u0002\b+R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractStarImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractImportingScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "lookupInFir", Argument.Delimiters.none, "excludedImportNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;ZLjava/util/Set;)V", "getExcludedImportNames", "()Ljava/util/Set;", "starImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "getStarImports", "()Ljava/util/List;", "isExcluded", "import", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "absentClassifierNames", Argument.Delimiters.none, "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processFunctionsByName", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "findEnumEntryWithoutResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractStarImportingScope extends FirAbstractImportingScope {
    private final Set<Name> absentClassifierNames;
    private final Set<FqName> excludedImportNames;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractStarImportingScope(FirSession firSession, ScopeSession scopeSession, boolean z, Set<FqName> set) {
        super(firSession, scopeSession, z);
        firSession.getClass();
        scopeSession.getClass();
        set.getClass();
        this.excludedImportNames = set;
        this.absentClassifierNames = new LinkedHashSet();
    }

    public static Unit b(Ref.BooleanRef booleanRef, Function2 function2, FirClassLikeSymbol firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        booleanRef.element = true;
        function2.invoke(firClassLikeSymbol, ConeSubstitutor.Empty.INSTANCE);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope
    @FirImplementationDetail
    public FirEnumEntrySymbol findEnumEntryWithoutResolution(Name name) {
        name.getClass();
        return doFindEnumEntryWithoutResolution(name, getStarImports());
    }

    public final Set<FqName> getExcludedImportNames() {
        return this.excludedImportNames;
    }

    public abstract List<FirResolvedImport> getStarImports();

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope
    public boolean isExcluded(FirResolvedImport firResolvedImport, Name name) {
        firResolvedImport.getClass();
        name.getClass();
        if (this.excludedImportNames.isEmpty()) {
            return false;
        }
        Set<FqName> set = this.excludedImportNames;
        FqName importedFqName = firResolvedImport.getImportedFqName();
        importedFqName.getClass();
        return set.contains(importedFqName.child(name));
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, final Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (!name.isSpecial()) {
            String identifier = name.getIdentifier();
            identifier.getClass();
            if (identifier.length() == 0) {
                return;
            }
        }
        if (getStarImports().isEmpty() || this.absentClassifierNames.contains(name)) {
            return;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        processClassifiersFromImportsByName(name, getStarImports(), new Function1() { // from class: yx4
            public final Object invoke(Object obj) {
                return FirAbstractStarImportingScope.b(booleanRef, processor, (FirClassLikeSymbol) obj);
            }
        });
        if (booleanRef.element) {
            return;
        }
        this.absentClassifierNames.add(name);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        processFunctionsByName(name, getStarImports(), processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        processPropertiesByName(name, getStarImports(), processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope, org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract FirAbstractStarImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);
}

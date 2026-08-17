package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH$J.\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00110\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0017H&J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019H\u0016J\u001e\u0010\u001f\u001a\u0004\u0018\u00010\u00002\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H'b\u0002\b#R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getKlass", "()Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getNestedClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "isEmpty", Argument.Delimiters.none, "getCallableNames", Argument.Delimiters.none, "scopeOwnerLookupNames", Argument.Delimiters.none, Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirNestedClassifierScope extends FirContainingNamesAwareScope {
    private final FirClass klass;
    private final List<String> scopeOwnerLookupNames;
    private final FirSession useSiteSession;

    public FirNestedClassifierScope(FirClass firClass, FirSession firSession) {
        firClass.getClass();
        firSession.getClass();
        this.klass = firClass;
        this.useSiteSession = firSession;
        this.scopeOwnerLookupNames = firClass.isLocal() ? CollectionsKt.emptyList() : new SmartList<>(FirDeclarationUtilKt.getClassId(firClass).asFqNameString());
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return SetsKt.emptySet();
    }

    public final FirClass getKlass() {
        return this.klass;
    }

    public abstract FirClassLikeSymbol<?> getNestedClassSymbol(Name name);

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return this.scopeOwnerLookupNames;
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }

    public abstract boolean isEmpty();

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        Object objSubstitutorByMap;
        name.getClass();
        processor.getClass();
        FirClassLikeSymbol<?> nestedClassSymbol = getNestedClassSymbol(name);
        if (nestedClassSymbol == null) {
            return;
        }
        if (this.klass.getTypeParameters().isEmpty()) {
            objSubstitutorByMap = ConeSubstitutor.Empty.INSTANCE;
        } else {
            List<FirTypeParameterRef> typeParameters = this.klass.getTypeParameters();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(typeParameters, 10)), 16));
            for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
                Pair pair = TuplesKt.to(firTypeParameterRef.getSymbol(), FirNestedClassifierScopeKt.toConeType(firTypeParameterRef));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            objSubstitutorByMap = ConeSubstitutorByMapKt.substitutorByMap(linkedHashMap, this.useSiteSession, true);
        }
        processor.invoke(nestedClassSymbol, objSubstitutorByMap);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract FirNestedClassifierScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);
}

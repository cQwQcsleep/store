package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\"\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J.\u0010\u0017\u001a\u00020\u00182\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H'b\u0002\b\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J*\u0010\u001e\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H'b\u0002\b\u001bJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J*\u0010!\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020 0\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H'b\u0002\b\u001bJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0014H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getTopLevelCallableSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelFunctionSymbolsTo", "getTopLevelPropertySymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getTopLevelPropertySymbolsTo", "hasPackage", Argument.Delimiters.none, "fqName", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSymbolProvider implements FirSessionComponent {
    private final FirSession session;

    public FirSymbolProvider(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    public abstract FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId);

    public final FirSession getSession() {
        return this.session;
    }

    public abstract FirSymbolNamesProvider getSymbolNamesProvider();

    public List<FirCallableSymbol<?>> getTopLevelCallableSymbols(FqName packageFqName, Name name) {
        packageFqName.getClass();
        name.getClass();
        List<FirCallableSymbol<?>> listCreateListBuilder = CollectionsKt.createListBuilder();
        getTopLevelCallableSymbolsTo(listCreateListBuilder, packageFqName, name);
        return CollectionsKt.build(listCreateListBuilder);
    }

    @FirSymbolProviderInternals
    public abstract void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name);

    public List<FirNamedFunctionSymbol> getTopLevelFunctionSymbols(FqName packageFqName, Name name) {
        packageFqName.getClass();
        name.getClass();
        List<FirNamedFunctionSymbol> listCreateListBuilder = CollectionsKt.createListBuilder();
        getTopLevelFunctionSymbolsTo(listCreateListBuilder, packageFqName, name);
        return CollectionsKt.build(listCreateListBuilder);
    }

    @FirSymbolProviderInternals
    public abstract void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name);

    public List<FirPropertySymbol> getTopLevelPropertySymbols(FqName packageFqName, Name name) {
        packageFqName.getClass();
        name.getClass();
        List<FirPropertySymbol> listCreateListBuilder = CollectionsKt.createListBuilder();
        getTopLevelPropertySymbolsTo(listCreateListBuilder, packageFqName, name);
        return CollectionsKt.build(listCreateListBuilder);
    }

    @FirSymbolProviderInternals
    public abstract void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name);

    public abstract boolean hasPackage(FqName fqName);
}

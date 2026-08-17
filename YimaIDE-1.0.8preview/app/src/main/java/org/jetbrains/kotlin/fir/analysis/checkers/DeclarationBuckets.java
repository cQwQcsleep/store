package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR'\u0010\u000e\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR#\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/DeclarationBuckets;", Argument.Delimiters.none, "<init>", "()V", "simpleFunctions", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", Argument.Delimiters.none, "getSimpleFunctions", "()Ljava/util/List;", "constructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getConstructors", "classLikes", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getClassLikes", "properties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getProperties", "extensionProperties", "getExtensionProperties", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class DeclarationBuckets {
    private final List<Pair<FirNamedFunctionSymbol, String>> simpleFunctions = new ArrayList();
    private final List<Pair<FirConstructorSymbol, String>> constructors = new ArrayList();
    private final List<Pair<FirClassLikeSymbol<?>, String>> classLikes = new ArrayList();
    private final List<Pair<FirPropertySymbol, String>> properties = new ArrayList();
    private final List<Pair<FirPropertySymbol, String>> extensionProperties = new ArrayList();

    public final List<Pair<FirClassLikeSymbol<?>, String>> getClassLikes() {
        return this.classLikes;
    }

    public final List<Pair<FirConstructorSymbol, String>> getConstructors() {
        return this.constructors;
    }

    public final List<Pair<FirPropertySymbol, String>> getExtensionProperties() {
        return this.extensionProperties;
    }

    public final List<Pair<FirPropertySymbol, String>> getProperties() {
        return this.properties;
    }

    public final List<Pair<FirNamedFunctionSymbol, String>> getSimpleFunctions() {
        return this.simpleFunctions;
    }
}

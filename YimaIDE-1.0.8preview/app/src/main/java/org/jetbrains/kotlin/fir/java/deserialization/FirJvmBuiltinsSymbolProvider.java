package org.jetbrains.kotlin.fir.java.deserialization;

import java.io.InputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirCompositeSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirFallbackBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J.\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0017b\u0002\b\u001eJ*\u0010\u001f\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020 0\u00192\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0017b\u0002\b\u001eJ*\u0010!\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\"0\u00192\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0017b\u0002\b\u001eJ\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/FirJvmBuiltinsSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "fallbackBuiltinSymbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirFallbackBuiltinSymbolProvider;", "findPackagePartData", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/FqName;", "Ljava/io/InputStream;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirFallbackBuiltinSymbolProvider;Lkotlin/jvm/functions/Function1;)V", "classpathBuiltinSymbolProvider", "Lorg/jetbrains/kotlin/fir/java/deserialization/FirJvmClasspathBuiltinSymbolProvider;", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", Argument.Delimiters.none, "fqName", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmBuiltinsSymbolProvider extends FirSymbolProvider {
    private final FirJvmClasspathBuiltinSymbolProvider classpathBuiltinSymbolProvider;
    private final FirFallbackBuiltinSymbolProvider fallbackBuiltinSymbolProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmBuiltinsSymbolProvider(FirSession firSession, FirFallbackBuiltinSymbolProvider firFallbackBuiltinSymbolProvider, Function1<? super FqName, ? extends InputStream> function1) {
        super(firSession);
        firSession.getClass();
        firFallbackBuiltinSymbolProvider.getClass();
        function1.getClass();
        this.fallbackBuiltinSymbolProvider = firFallbackBuiltinSymbolProvider;
        this.classpathBuiltinSymbolProvider = new FirJvmClasspathBuiltinSymbolProvider(firSession, firFallbackBuiltinSymbolProvider.getModuleData(), firFallbackBuiltinSymbolProvider.getKotlinScopeProvider(), function1);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirRegularClassSymbol getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        FirRegularClassSymbol classLikeSymbolByClassId = this.classpathBuiltinSymbolProvider.getClassLikeSymbolByClassId(classId);
        return classLikeSymbolByClassId == null ? this.fallbackBuiltinSymbolProvider.getClassLikeSymbolByClassId(classId) : classLikeSymbolByClassId;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return FirCompositeSymbolNamesProvider.INSTANCE.fromSymbolProviders(CollectionsKt.listOf(new AbstractFirBuiltinSymbolProvider[]{this.classpathBuiltinSymbolProvider, this.fallbackBuiltinSymbolProvider}));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        int size = destination.size();
        this.classpathBuiltinSymbolProvider.getTopLevelCallableSymbolsTo(destination, packageFqName, name);
        if (size == destination.size()) {
            this.fallbackBuiltinSymbolProvider.getTopLevelCallableSymbolsTo(destination, packageFqName, name);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        int size = destination.size();
        this.classpathBuiltinSymbolProvider.getTopLevelFunctionSymbolsTo(destination, packageFqName, name);
        if (size == destination.size()) {
            this.fallbackBuiltinSymbolProvider.getTopLevelFunctionSymbolsTo(destination, packageFqName, name);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        int size = destination.size();
        this.classpathBuiltinSymbolProvider.getTopLevelPropertySymbolsTo(destination, packageFqName, name);
        if (size == destination.size()) {
            this.fallbackBuiltinSymbolProvider.getTopLevelPropertySymbolsTo(destination, packageFqName, name);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return this.classpathBuiltinSymbolProvider.hasPackage(fqName) || this.fallbackBuiltinSymbolProvider.hasPackage(fqName);
    }
}

package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0010\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0014\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000eR\u0014\u0010\u0018\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000e¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirDelegatingCachedSymbolNamesProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirCachedSymbolNamesProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "delegate", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;)V", "computePackageNames", Argument.Delimiters.none, Argument.Delimiters.none, "hasSpecificClassifierPackageNamesComputation", Argument.Delimiters.none, "getHasSpecificClassifierPackageNamesComputation", "()Z", "computePackageNamesWithTopLevelClassifiers", "computeTopLevelClassifierNames", "Lorg/jetbrains/kotlin/name/Name;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasSpecificCallablePackageNamesComputation", "getHasSpecificCallablePackageNamesComputation", "computePackageNamesWithTopLevelCallables", "computeTopLevelCallableNames", "mayHaveSyntheticFunctionTypes", "getMayHaveSyntheticFunctionTypes", "mayHaveSyntheticFunctionType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatingCachedSymbolNamesProvider extends FirCachedSymbolNamesProvider {
    private final FirSymbolNamesProvider delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDelegatingCachedSymbolNamesProvider(FirSession firSession, FirSymbolNamesProvider firSymbolNamesProvider) {
        super(firSession);
        firSession.getClass();
        firSymbolNamesProvider.getClass();
        this.delegate = firSymbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<String> computePackageNames() {
        return this.delegate.getPackageNames();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<String> computePackageNamesWithTopLevelCallables() {
        return this.delegate.getPackageNamesWithTopLevelCallables();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<String> computePackageNamesWithTopLevelClassifiers() {
        return this.delegate.getPackageNamesWithTopLevelClassifiers();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<Name> computeTopLevelCallableNames(FqName packageFqName) {
        packageFqName.getClass();
        return this.delegate.getTopLevelCallableNamesInPackage(packageFqName);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
    public Set<Name> computeTopLevelClassifierNames(FqName packageFqName) {
        packageFqName.getClass();
        return this.delegate.getTopLevelClassifierNamesInPackage(packageFqName);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean getHasSpecificCallablePackageNamesComputation() {
        return this.delegate.getHasSpecificCallablePackageNamesComputation();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean getHasSpecificClassifierPackageNamesComputation() {
        return this.delegate.getHasSpecificClassifierPackageNamesComputation();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean getMayHaveSyntheticFunctionTypes() {
        return this.delegate.getMayHaveSyntheticFunctionTypes();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public boolean mayHaveSyntheticFunctionType(ClassId classId) {
        classId.getClass();
        return this.delegate.mayHaveSyntheticFunctionType(classId);
    }
}

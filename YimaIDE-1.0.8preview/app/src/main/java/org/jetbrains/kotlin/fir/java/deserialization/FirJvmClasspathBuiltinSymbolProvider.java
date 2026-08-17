package org.jetbrains.kotlin.fir.java.deserialization;

import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\u0004\b\f\u0010\rR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00120\u0011X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/FirJvmClasspathBuiltinSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "findPackagePartData", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/FqName;", "Ljava/io/InputStream;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lkotlin/jvm/functions/Function1;)V", "getFindPackagePartData", "()Lkotlin/jvm/functions/Function1;", "builtInsPackageFragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", "getBuiltInsPackageFragments", "()Ljava/util/Map;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmClasspathBuiltinSymbolProvider extends AbstractFirBuiltinSymbolProvider {
    private final Map<FqName, AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment> builtInsPackageFragments;
    private final Function1<FqName, InputStream> findPackagePartData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmClasspathBuiltinSymbolProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, Function1<? super FqName, ? extends InputStream> function1) {
        super(firSession, firModuleData, firKotlinScopeProvider, false);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
        function1.getClass();
        this.findPackagePartData = function1;
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (FqName fqName : StandardClassIds.INSTANCE.getBuiltInsPackages()) {
            InputStream inputStream = (InputStream) this.findPackagePartData.invoke(fqName);
            if (inputStream != null) {
                mapCreateMapBuilder.put(fqName, new AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment(inputStream));
            }
        }
        this.builtInsPackageFragments = MapsKt.build(mapCreateMapBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider
    public Map<FqName, AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment> getBuiltInsPackageFragments() {
        return this.builtInsPackageFragments;
    }

    public final Function1<FqName, InputStream> getFindPackagePartData() {
        return this.findPackagePartData;
    }
}

package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirFallbackBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirFallbackBuiltinSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;)V", "builtInsPackageFragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", "getBuiltInsPackageFragments", "()Ljava/util/Map;", "Companion", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFallbackBuiltinSymbolProvider extends AbstractFirBuiltinSymbolProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<FqName, AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment> builtInsPackageFragments;

    static {
        final ClassLoader classLoader = FirFallbackBuiltinSymbolProvider.class.getClassLoader();
        Function1 function1 = new Function1() { // from class: x75
            public final Object invoke(Object obj) {
                return FirFallbackBuiltinSymbolProvider.builtInsPackageFragments$lambda$0$0(classLoader, (String) obj);
            }
        };
        Set<FqName> builtInsPackages = StandardClassIds.INSTANCE.getBuiltInsPackages();
        ArrayList arrayList = new ArrayList();
        for (FqName fqName : builtInsPackages) {
            InputStream inputStream = (InputStream) function1.invoke(BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(fqName));
            Pair pair = inputStream != null ? TuplesKt.to(fqName, new AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment(inputStream)) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        builtInsPackageFragments = MapsKt.toMap(arrayList);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirFallbackBuiltinSymbolProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider) {
        super(firSession, firModuleData, firKotlinScopeProvider, true);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InputStream builtInsPackageFragments$lambda$0$0(ClassLoader classLoader, String str) {
        InputStream resourceAsStream;
        str.getClass();
        return (classLoader == null || (resourceAsStream = classLoader.getResourceAsStream(str)) == null) ? ClassLoader.getSystemResourceAsStream(str) : resourceAsStream;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider
    public Map<FqName, AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment> getBuiltInsPackageFragments() {
        return builtInsPackageFragments;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirFallbackBuiltinSymbolProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "builtInsPackageFragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", "getBuiltInsPackageFragments", "()Ljava/util/Map;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Map<FqName, AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment> getBuiltInsPackageFragments() {
            return FirFallbackBuiltinSymbolProvider.builtInsPackageFragments;
        }

        private Companion() {
        }
    }
}

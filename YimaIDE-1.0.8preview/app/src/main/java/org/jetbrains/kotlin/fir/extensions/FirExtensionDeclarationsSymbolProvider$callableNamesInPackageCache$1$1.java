package org.jetbrains.kotlin.fir.extensions;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$1 extends FunctionReferenceImpl implements Function1<FirDeclarationGenerationExtension, Set<? extends CallableId>> {
    public static final FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$1 INSTANCE = new FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$1();

    public FirExtensionDeclarationsSymbolProvider$callableNamesInPackageCache$1$1() {
        super(1, FirDeclarationGenerationExtension.class, "getTopLevelCallableIds", "getTopLevelCallableIds()Ljava/util/Set;", 0);
    }

    public final Set<CallableId> invoke(FirDeclarationGenerationExtension firDeclarationGenerationExtension) {
        firDeclarationGenerationExtension.getClass();
        return firDeclarationGenerationExtension.getTopLevelCallableIds();
    }
}

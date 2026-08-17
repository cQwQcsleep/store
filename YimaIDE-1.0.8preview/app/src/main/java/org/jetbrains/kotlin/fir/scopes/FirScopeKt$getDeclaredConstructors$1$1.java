package org.jetbrains.kotlin.fir.scopes;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirScopeKt$getDeclaredConstructors$1$1 extends AdaptedFunctionReference implements Function1<FirConstructorSymbol, Unit> {
    public FirScopeKt$getDeclaredConstructors$1$1(Object obj) {
        super(1, obj, List.class, "add", "add(Ljava/lang/Object;)Z", 8);
    }

    public final void invoke(FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        ((List) ((AdaptedFunctionReference) this).receiver).add(firConstructorSymbol);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirConstructorSymbol) obj);
        return Unit.INSTANCE;
    }
}

package org.jetbrains.kotlin.cli.js;

import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.wasm.ic.WasmICContextMultimodule;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class IcCachesKt$prepareIcCaches$icContext$contextConstructor$2 extends FunctionReferenceImpl implements Function4<Boolean, Boolean, Boolean, Boolean, WasmICContextMultimodule> {
    public static final IcCachesKt$prepareIcCaches$icContext$contextConstructor$2 INSTANCE = new IcCachesKt$prepareIcCaches$icContext$contextConstructor$2();

    public IcCachesKt$prepareIcCaches$icContext$contextConstructor$2() {
        super(4, WasmICContextMultimodule.class, "<init>", "<init>(ZZZZ)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), ((Boolean) obj4).booleanValue());
    }

    public final WasmICContextMultimodule invoke(boolean z, boolean z2, boolean z3, boolean z4) {
        return new WasmICContextMultimodule(z, z2, z3, z4);
    }
}

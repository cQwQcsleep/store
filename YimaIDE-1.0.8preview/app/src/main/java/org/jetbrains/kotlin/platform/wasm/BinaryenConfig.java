package org.jetbrains.kotlin.platform.wasm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/platform/wasm/BinaryenConfig;", "", "<init>", "()V", "binaryenCommonArgs", "", "", "binaryenArgs", "getBinaryenArgs", "()Ljava/util/List;", "binaryenMultimoduleArgs", "getBinaryenMultimoduleArgs", "org.jetbrains.kotlin:wasm.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BinaryenConfig {
    public static final BinaryenConfig INSTANCE = new BinaryenConfig();
    private static final List<String> binaryenArgs;
    private static final List<String> binaryenCommonArgs;
    private static final List<String> binaryenMultimoduleArgs;

    static {
        List<String> listListOf = CollectionsKt.listOf(new String[]{"--enable-gc", "--enable-reference-types", "--enable-exception-handling", "--enable-bulk-memory", "--enable-nontrapping-float-to-int", "--no-inline=kotlin.wasm.internal.throwValue", "--no-inline=kotlin.wasm.internal.getKotlinException", "--no-inline=kotlin.wasm.internal.jsToKotlinStringAdapter", "--inline-functions-with-loops", "--traps-never-happen", "--fast-math"});
        binaryenCommonArgs = listListOf;
        binaryenArgs = CollectionsKt.plus(listListOf, CollectionsKt.listOf(new String[]{"--closed-world", "--type-ssa", "-O3", "-O3", "--gufa", "-O3", "--type-merging", "-O3", "-Oz"}));
        binaryenMultimoduleArgs = CollectionsKt.plus(listListOf, CollectionsKt.listOf(new String[]{"-O3", "-O3", "-O3"}));
    }

    private BinaryenConfig() {
    }

    public final List<String> getBinaryenArgs() {
        return binaryenArgs;
    }

    public final List<String> getBinaryenMultimoduleArgs() {
        return binaryenMultimoduleArgs;
    }
}

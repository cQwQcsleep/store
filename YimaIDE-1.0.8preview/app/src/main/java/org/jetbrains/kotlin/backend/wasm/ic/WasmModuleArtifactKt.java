package org.jetbrains.kotlin.backend.wasm.ic;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0002\b\u0005H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"ifExists", "T", "Ljava/io/File;", "f", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/io/File;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class WasmModuleArtifactKt {
    public static final <T> T ifExists(File file, Function1<? super File, ? extends T> function1) {
        file.getClass();
        function1.getClass();
        if (file.exists()) {
            return (T) function1.invoke(file);
        }
        return null;
    }
}

package org.jetbrains.kotlin.incremental;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class CompilerRunnerUtils$makeJvmIncrementally$files$1 extends FunctionReferenceImpl implements Function1<File, Boolean> {
    public static final CompilerRunnerUtils$makeJvmIncrementally$files$1 INSTANCE = new CompilerRunnerUtils$makeJvmIncrementally$files$1();

    public CompilerRunnerUtils$makeJvmIncrementally$files$1() {
        super(1, File.class, "isFile", "isFile()Z", 0);
    }

    public final Boolean invoke(File file) {
        file.getClass();
        return Boolean.valueOf(file.isFile());
    }
}

package org.jetbrains.kotlin.incremental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class RecoverableCompilationTransaction$cleanupStash$2$1$1 extends FunctionReferenceImpl implements Function1<Path, Unit> {
    public static final RecoverableCompilationTransaction$cleanupStash$2$1$1 INSTANCE = new RecoverableCompilationTransaction$cleanupStash$2$1$1();

    public RecoverableCompilationTransaction$cleanupStash$2$1$1() {
        super(1, Files.class, "delete", "delete(Ljava/nio/file/Path;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
        invoke((Path) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Path path) throws IOException {
        Files.delete(path);
    }
}

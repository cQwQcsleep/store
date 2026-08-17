package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/SchemeParseError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SchemeParseError extends Exception {
    public SchemeParseError() {
        super("Internal scheme parse error");
    }
}

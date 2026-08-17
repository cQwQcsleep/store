package org.jetbrains.kotlin.cli.jvm.modules;

import com.intellij.util.lang.JavaVersion;
import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"isAtLeastJava9", "", "org.jetbrains.kotlin:cli-base"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class JavaVersionUtilsKt {
    public static final boolean isAtLeastJava9() {
        return JavaVersion.current().compareTo(JavaVersion.compose(9)) >= 0;
    }
}

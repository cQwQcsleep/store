package org.jetbrains.kotlin.resolve.jvm;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/JvmConstants;", "", "<init>", "()V", "INVALID_CHARS", "", "", "getINVALID_CHARS", "()Ljava/util/Set;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class JvmConstants {
    public static final JvmConstants INSTANCE = new JvmConstants();
    private static final Set<Character> INVALID_CHARS = SetsKt.setOf(new Character[]{'.', ';', '[', ']', '/', '<', '>', ':', '\\'});

    private JvmConstants() {
    }

    public final Set<Character> getINVALID_CHARS() {
        return INVALID_CHARS;
    }
}

package org.jetbrains.kotlin.resolve.jvm;

import com.intellij.psi.CommonClassNames;
import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003¨\u0006\n"}, d2 = {"JAVA_LANG_RECORD_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "getJAVA_LANG_RECORD_FQ_NAME", "()Lorg/jetbrains/kotlin/name/FqName;", "JAVA_POLYMORPHIC_SIGNATURE_NAME", "getJAVA_POLYMORPHIC_SIGNATURE_NAME", "JAVA_METHOD_HANDLE_FQ_NAME", "getJAVA_METHOD_HANDLE_FQ_NAME", "JAVA_VAR_HANDLE_FQ_NAME", "getJAVA_VAR_HANDLE_FQ_NAME", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JdkClassesKt {
    private static final FqName JAVA_LANG_RECORD_FQ_NAME = new FqName(CommonClassNames.JAVA_LANG_RECORD);
    private static final FqName JAVA_POLYMORPHIC_SIGNATURE_NAME = new FqName(CommonClassNames.JAVA_LANG_INVOKE_MH_POLYMORPHIC);
    private static final FqName JAVA_METHOD_HANDLE_FQ_NAME = new FqName("java.lang.invoke.MethodHandle");
    private static final FqName JAVA_VAR_HANDLE_FQ_NAME = new FqName("java.lang.invoke.VarHandle");

    public static final FqName getJAVA_LANG_RECORD_FQ_NAME() {
        return JAVA_LANG_RECORD_FQ_NAME;
    }

    public static final FqName getJAVA_METHOD_HANDLE_FQ_NAME() {
        return JAVA_METHOD_HANDLE_FQ_NAME;
    }

    public static final FqName getJAVA_POLYMORPHIC_SIGNATURE_NAME() {
        return JAVA_POLYMORPHIC_SIGNATURE_NAME;
    }

    public static final FqName getJAVA_VAR_HANDLE_FQ_NAME() {
        return JAVA_VAR_HANDLE_FQ_NAME;
    }
}

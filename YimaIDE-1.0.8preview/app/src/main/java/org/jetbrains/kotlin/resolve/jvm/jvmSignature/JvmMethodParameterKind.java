package org.jetbrains.kotlin.resolve.jvm.jvmSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public enum JvmMethodParameterKind {
    VALUE,
    THIS,
    OUTER,
    RECEIVER,
    CONTEXT_RECEIVER,
    CAPTURED_LOCAL_VARIABLE,
    ENUM_NAME_OR_ORDINAL,
    SUPER_CALL_PARAM,
    CONSTRUCTOR_MARKER
}

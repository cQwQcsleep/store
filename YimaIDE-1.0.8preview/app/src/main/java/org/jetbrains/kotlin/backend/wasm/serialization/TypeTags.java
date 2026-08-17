package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/TypeTags;", "", "<init>", "()V", "REF", "Lkotlin/UInt;", "I", "REF_NULL", "ANYREF", "EQREF", "EXTERN_REF_TYPE", "EXTERN_REF", "F32", "F64", "FUNC_REF", "I16", "I31_REF", "I32", "I64", "I8", "NULL_EXTERN_REF_TYPE", "REF_NULL_EXTERN_REF_TYPE", "REF_NULL_REF_TYPE", "STRUCT_REF", "UNREACHABLE_TYPE", "V12", "ARRAY_REF", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class TypeTags {
    public static final int ANYREF = 2;
    public static final int ARRAY_REF = 20;
    public static final int EQREF = 3;
    public static final int EXTERN_REF = 5;
    public static final int EXTERN_REF_TYPE = 4;
    public static final int F32 = 6;
    public static final int F64 = 7;
    public static final int FUNC_REF = 8;
    public static final int I16 = 9;
    public static final int I31_REF = 10;
    public static final int I32 = 11;
    public static final int I64 = 12;
    public static final int I8 = 13;
    public static final TypeTags INSTANCE = new TypeTags();
    public static final int NULL_EXTERN_REF_TYPE = 14;
    public static final int REF = 0;
    public static final int REF_NULL = 1;
    public static final int REF_NULL_EXTERN_REF_TYPE = 15;
    public static final int REF_NULL_REF_TYPE = 16;
    public static final int STRUCT_REF = 17;
    public static final int UNREACHABLE_TYPE = 18;
    public static final int V12 = 19;

    private TypeTags() {
    }
}

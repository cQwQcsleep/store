package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u0015\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0002¢\u0006\u0002\n\u0000\"\u0015\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0002¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "Lkotlin/jvm/JvmField;", "KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME_CALLABLE_ID", "Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:deserialization"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class SuspendFunctionTypeUtilKt {
    public static final FqName KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME = new FqName("kotlin.suspend");
    public static final CallableId KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME_CALLABLE_ID;

    static {
        FqName fqName = StandardNames.BUILT_INS_PACKAGE_FQ_NAME;
        Name nameIdentifier = Name.identifier("suspend");
        nameIdentifier.getClass();
        KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME_CALLABLE_ID = new CallableId(fqName, nameIdentifier);
    }
}

package org.jetbrains.kotlin.codegen.coroutines;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"CONTINUATION_VARIABLE_NAME", Argument.Delimiters.none, "SUSPEND_FUNCTION_COMPLETION_PARAMETER_NAME", "SUSPEND_CALL_RESULT_NAME", "ILLEGAL_STATE_ERROR_MESSAGE", "org.jetbrains.kotlin:backend.common.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoroutineConstantsKt {
    public static final String CONTINUATION_VARIABLE_NAME = "$continuation";
    public static final String ILLEGAL_STATE_ERROR_MESSAGE = "call to 'resume' before 'invoke' with coroutine";
    public static final String SUSPEND_CALL_RESULT_NAME = "$result";
    public static final String SUSPEND_FUNCTION_COMPLETION_PARAMETER_NAME = "$completion";
}

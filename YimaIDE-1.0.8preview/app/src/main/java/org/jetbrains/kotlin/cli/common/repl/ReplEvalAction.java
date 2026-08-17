package org.jetbrains.kotlin.cli.common.repl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalAction;", Argument.Delimiters.none, "eval", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "state", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "compileResult", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "scriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "invokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ReplEvalAction {
    static /* synthetic */ ReplEvalResult eval$default(ReplEvalAction replEvalAction, IReplStageState iReplStageState, ReplCompileResult.CompiledClasses compiledClasses, ScriptArgsWithTypes scriptArgsWithTypes, InvokeWrapper invokeWrapper, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: eval");
            return null;
        }
        if ((i & 4) != 0) {
            scriptArgsWithTypes = null;
        }
        if ((i & 8) != 0) {
            invokeWrapper = null;
        }
        return replEvalAction.eval(iReplStageState, compiledClasses, scriptArgsWithTypes, invokeWrapper);
    }

    ReplEvalResult eval(IReplStageState<?> state, ReplCompileResult.CompiledClasses compileResult, ScriptArgsWithTypes scriptArgs, InvokeWrapper invokeWrapper);
}

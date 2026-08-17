package org.jetbrains.kotlin.cli.common.repl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/Evaluable;", Argument.Delimiters.none, "compiledCode", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "getCompiledCode", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "eval", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "scriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "invokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface Evaluable {
    static /* synthetic */ ReplEvalResult eval$default(Evaluable evaluable, ScriptArgsWithTypes scriptArgsWithTypes, InvokeWrapper invokeWrapper, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: eval");
            return null;
        }
        if ((i & 1) != 0) {
            scriptArgsWithTypes = null;
        }
        if ((i & 2) != 0) {
            invokeWrapper = null;
        }
        return evaluable.eval(scriptArgsWithTypes, invokeWrapper);
    }

    ReplEvalResult eval(ScriptArgsWithTypes scriptArgs, InvokeWrapper invokeWrapper);

    ReplCompileResult.CompiledClasses getCompiledCode();
}

package org.jetbrains.kotlin.cli.common.repl;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/GenericReplCompilingEvaluator;", "Lorg/jetbrains/kotlin/cli/common/repl/GenericReplCompilingEvaluatorBase;", "compiler", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;", "baseClasspath", Argument.Delimiters.none, "Ljava/io/File;", "baseClassloader", "Ljava/lang/ClassLoader;", "fallbackScriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "repeatingMode", "Lorg/jetbrains/kotlin/cli/common/repl/ReplRepeatingMode;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;Ljava/lang/Iterable;Ljava/lang/ClassLoader;Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;Lorg/jetbrains/kotlin/cli/common/repl/ReplRepeatingMode;)V", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GenericReplCompilingEvaluator extends GenericReplCompilingEvaluatorBase {
    public /* synthetic */ GenericReplCompilingEvaluator(ReplCompilerWithoutCheck replCompilerWithoutCheck, Iterable iterable, ClassLoader classLoader, ScriptArgsWithTypes scriptArgsWithTypes, ReplRepeatingMode replRepeatingMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(replCompilerWithoutCheck, iterable, (i & 4) != 0 ? Thread.currentThread().getContextClassLoader() : classLoader, (i & 8) != 0 ? null : scriptArgsWithTypes, (i & 16) != 0 ? ReplRepeatingMode.REPEAT_ONLY_MOST_RECENT : replRepeatingMode);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericReplCompilingEvaluator(ReplCompilerWithoutCheck replCompilerWithoutCheck, Iterable<? extends File> iterable, ClassLoader classLoader, ScriptArgsWithTypes scriptArgsWithTypes, ReplRepeatingMode replRepeatingMode) {
        super(replCompilerWithoutCheck, new GenericReplEvaluator(iterable, classLoader, scriptArgsWithTypes, replRepeatingMode), scriptArgsWithTypes);
        replCompilerWithoutCheck.getClass();
        iterable.getClass();
        replRepeatingMode.getClass();
    }
}

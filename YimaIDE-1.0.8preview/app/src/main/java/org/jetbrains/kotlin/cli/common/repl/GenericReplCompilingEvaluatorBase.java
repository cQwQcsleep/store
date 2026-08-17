package org.jetbrains.kotlin.cli.common.repl;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\"B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J0\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J0\u0010\u001a\u001a\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J4\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010 0\u001e2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u00010\u0007H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/GenericReplCompilingEvaluatorBase;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplFullEvaluator;", "compiler", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;", "evaluator", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvaluator;", "fallbackScriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;Lorg/jetbrains/kotlin/cli/common/repl/ReplEvaluator;Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;)V", "getCompiler", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;", "getEvaluator", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplEvaluator;", "createState", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "compileAndEval", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "state", "codeLine", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "scriptArgs", "invokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "eval", "compileResult", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "compileToEvaluable", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult;", "Lorg/jetbrains/kotlin/cli/common/repl/Evaluable;", "defaultScriptArgs", "DelayedEvaluation", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class GenericReplCompilingEvaluatorBase implements ReplFullEvaluator {
    private final ReplCompilerWithoutCheck compiler;
    private final ReplEvaluator evaluator;
    private final ScriptArgsWithTypes fallbackScriptArgs;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/GenericReplCompilingEvaluatorBase$DelayedEvaluation;", "Lorg/jetbrains/kotlin/cli/common/repl/Evaluable;", "state", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "compiledCode", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "evaluator", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvaluator;", "defaultScriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;Lorg/jetbrains/kotlin/cli/common/repl/ReplEvaluator;Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;)V", "getCompiledCode", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "eval", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "scriptArgs", "invokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DelayedEvaluation implements Evaluable {
        private final ReplCompileResult.CompiledClasses compiledCode;
        private final ScriptArgsWithTypes defaultScriptArgs;
        private final ReplEvaluator evaluator;
        private final IReplStageState<?> state;

        public DelayedEvaluation(IReplStageState<?> iReplStageState, ReplCompileResult.CompiledClasses compiledClasses, ReplEvaluator replEvaluator, ScriptArgsWithTypes scriptArgsWithTypes) {
            iReplStageState.getClass();
            compiledClasses.getClass();
            replEvaluator.getClass();
            this.state = iReplStageState;
            this.compiledCode = compiledClasses;
            this.evaluator = replEvaluator;
            this.defaultScriptArgs = scriptArgsWithTypes;
        }

        @Override // org.jetbrains.kotlin.cli.common.repl.Evaluable
        public ReplEvalResult eval(ScriptArgsWithTypes scriptArgs, InvokeWrapper invokeWrapper) {
            ReplEvaluator replEvaluator = this.evaluator;
            IReplStageState<?> iReplStageState = this.state;
            ReplCompileResult.CompiledClasses compiledCode = getCompiledCode();
            if (scriptArgs == null) {
                scriptArgs = this.defaultScriptArgs;
            }
            return replEvaluator.eval(iReplStageState, compiledCode, scriptArgs, invokeWrapper);
        }

        @Override // org.jetbrains.kotlin.cli.common.repl.Evaluable
        public ReplCompileResult.CompiledClasses getCompiledCode() {
            return this.compiledCode;
        }
    }

    public GenericReplCompilingEvaluatorBase(ReplCompilerWithoutCheck replCompilerWithoutCheck, ReplEvaluator replEvaluator, ScriptArgsWithTypes scriptArgsWithTypes) {
        replCompilerWithoutCheck.getClass();
        replEvaluator.getClass();
        this.compiler = replCompilerWithoutCheck;
        this.evaluator = replEvaluator;
        this.fallbackScriptArgs = scriptArgsWithTypes;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.ReplAtomicEvalAction
    public ReplEvalResult compileAndEval(IReplStageState<?> state, ReplCodeLine codeLine, ScriptArgsWithTypes scriptArgs, InvokeWrapper invokeWrapper) {
        ReplEvalResult replEvalResultEval;
        state.getClass();
        codeLine.getClass();
        if (StringsKt.trim(codeLine.getCode()).toString().length() == 0) {
            return new ReplEvalResult.UnitResult();
        }
        ReentrantReadWriteLock lock = state.getLock();
        ReentrantReadWriteLock.ReadLock lock2 = lock.readLock();
        int i = 0;
        int readHoldCount = lock.getWriteHoldCount() == 0 ? lock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock2.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            AggregatedReplStageState aggregatedReplStageState = (AggregatedReplStageState) state.asState(AggregatedReplStageState.class);
            ReplCompileResult replCompileResultCompile = this.compiler.compile(state, codeLine);
            if (replCompileResultCompile instanceof ReplCompileResult.Error) {
                ReentrantReadWriteLock lock3 = aggregatedReplStageState.getLock();
                lock2 = lock3.readLock();
                readHoldCount = lock3.getWriteHoldCount() == 0 ? lock3.getReadHoldCount() : 0;
                for (int i3 = 0; i3 < readHoldCount; i3++) {
                    lock2.unlock();
                }
                writeLock = lock3.writeLock();
                writeLock.lock();
                try {
                    aggregatedReplStageState.getState1().getHistory().size();
                    aggregatedReplStageState.getState2().getHistory().size();
                    GenericReplCompilingEvaluatorKt.adjustHistories(aggregatedReplStageState);
                    Unit unit = Unit.INSTANCE;
                    for (int i4 = 0; i4 < readHoldCount; i4++) {
                        lock2.lock();
                    }
                    writeLock.unlock();
                    replEvalResultEval = new ReplEvalResult.Error.CompileTime(((ReplCompileResult.Error) replCompileResultCompile).getMessage(), ((ReplCompileResult.Error) replCompileResultCompile).getLocation());
                } finally {
                    for (int i5 = 0; i5 < readHoldCount; i5++) {
                        lock2.lock();
                    }
                    writeLock.unlock();
                }
            } else if (replCompileResultCompile instanceof ReplCompileResult.Incomplete) {
                replEvalResultEval = new ReplEvalResult.Incomplete(((ReplCompileResult.Incomplete) replCompileResultCompile).getMessage());
            } else {
                if (!(replCompileResultCompile instanceof ReplCompileResult.CompiledClasses)) {
                    throw new NoWhenBranchMatchedException();
                }
                replEvalResultEval = eval(state, (ReplCompileResult.CompiledClasses) replCompileResultCompile, scriptArgs, invokeWrapper);
                if ((replEvalResultEval instanceof ReplEvalResult.Error) || (replEvalResultEval instanceof ReplEvalResult.HistoryMismatch) || (replEvalResultEval instanceof ReplEvalResult.Incomplete)) {
                    ReentrantReadWriteLock lock4 = aggregatedReplStageState.getLock();
                    ReentrantReadWriteLock.ReadLock lock5 = lock4.readLock();
                    int readHoldCount2 = lock4.getWriteHoldCount() == 0 ? lock4.getReadHoldCount() : 0;
                    for (int i6 = 0; i6 < readHoldCount2; i6++) {
                        lock5.unlock();
                    }
                    ReentrantReadWriteLock.WriteLock writeLock2 = lock4.writeLock();
                    writeLock2.lock();
                    try {
                        if (aggregatedReplStageState.getState1().getHistory().size() > aggregatedReplStageState.getState2().getHistory().size()) {
                            GenericReplCompilingEvaluatorKt.adjustHistories(aggregatedReplStageState);
                            aggregatedReplStageState.getState1().getHistory().size();
                            aggregatedReplStageState.getState2().getHistory().size();
                        }
                        Unit unit2 = Unit.INSTANCE;
                        for (int i7 = 0; i7 < readHoldCount2; i7++) {
                            lock5.lock();
                        }
                        writeLock2.unlock();
                    } finally {
                        for (int i8 = 0; i8 < readHoldCount2; i8++) {
                            lock5.lock();
                        }
                        writeLock2.unlock();
                    }
                } else if (!(replEvalResultEval instanceof ReplEvalResult.ValueResult) && !(replEvalResultEval instanceof ReplEvalResult.UnitResult)) {
                    throw new NoWhenBranchMatchedException();
                }
            }
            return replEvalResultEval;
        } catch (Throwable th) {
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.ReplDelayedEvalAction
    public Pair<ReplCompileResult, Evaluable> compileToEvaluable(IReplStageState<?> state, ReplCodeLine codeLine, ScriptArgsWithTypes defaultScriptArgs) {
        state.getClass();
        codeLine.getClass();
        ReplCompileResult replCompileResultCompile = this.compiler.compile(state, codeLine);
        if (!(replCompileResultCompile instanceof ReplCompileResult.CompiledClasses)) {
            return new Pair<>(replCompileResultCompile, (Object) null);
        }
        ReplCompileResult.CompiledClasses compiledClasses = (ReplCompileResult.CompiledClasses) replCompileResultCompile;
        ReplEvaluator replEvaluator = this.evaluator;
        if (defaultScriptArgs == null) {
            defaultScriptArgs = this.fallbackScriptArgs;
        }
        return new Pair<>(replCompileResultCompile, new DelayedEvaluation(state, compiledClasses, replEvaluator, defaultScriptArgs));
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.CreateReplStageStateAction
    public IReplStageState<?> createState(ReentrantReadWriteLock lock) {
        lock.getClass();
        return new AggregatedReplStageState(this.compiler.createState(lock), this.evaluator.createState(lock), lock);
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.ReplEvalAction
    public ReplEvalResult eval(IReplStageState<?> state, ReplCompileResult.CompiledClasses compileResult, ScriptArgsWithTypes scriptArgs, InvokeWrapper invokeWrapper) {
        state.getClass();
        compileResult.getClass();
        return this.evaluator.eval(state, compileResult, scriptArgs, invokeWrapper);
    }

    public final ReplCompilerWithoutCheck getCompiler() {
        return this.compiler;
    }

    public final ReplEvaluator getEvaluator() {
        return this.evaluator;
    }

    public /* synthetic */ GenericReplCompilingEvaluatorBase(ReplCompilerWithoutCheck replCompilerWithoutCheck, ReplEvaluator replEvaluator, ScriptArgsWithTypes scriptArgsWithTypes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(replCompilerWithoutCheck, replEvaluator, (i & 4) != 0 ? null : scriptArgsWithTypes);
    }
}

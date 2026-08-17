package org.jetbrains.kotlin.cli.common.repl;

import java.io.Reader;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import kotlin.Metadata;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u00011B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0015J\u0016\u0010!\u001a\u0006\u0012\u0002\b\u00030\"2\b\b\u0002\u0010#\u001a\u00020$H$J\u0014\u0010%\u001a\u0006\u0012\u0002\b\u00030\"2\u0006\u0010\u0016\u001a\u00020\u0017H\u0004J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010*\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010-\u001a\u0004\u0018\u00010\u00132\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¤\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00062"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineBase;", "Ljavax/script/AbstractScriptEngine;", "Ljavax/script/ScriptEngine;", "Ljavax/script/Compilable;", "myFactory", "Ljavax/script/ScriptEngineFactory;", "<init>", "(Ljavax/script/ScriptEngineFactory;)V", "getMyFactory", "()Ljavax/script/ScriptEngineFactory;", "replCompiler", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;", "getReplCompiler", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCompilerWithoutCheck;", "replEvaluator", "Lorg/jetbrains/kotlin/cli/common/repl/ReplFullEvaluator;", "getReplEvaluator", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplFullEvaluator;", "eval", Argument.Delimiters.none, "script", Argument.Delimiters.none, "context", "Ljavax/script/ScriptContext;", "Ljava/io/Reader;", "compile", "Ljavax/script/CompiledScript;", "createBindings", "Ljavax/script/Bindings;", "getFactory", "nextCodeLine", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "code", "createState", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "getCurrentState", "getInvokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "overrideScriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "compileAndEval", "compiledScript", "Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineBase$CompiledKotlinScript;", "asJsr223EvalResult", "body", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "CompiledKotlinScript", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KotlinJsr223JvmScriptEngineBase extends AbstractScriptEngine implements Compilable, ScriptEngine {
    private final ScriptEngineFactory myFactory;

    public KotlinJsr223JvmScriptEngineBase(ScriptEngineFactory scriptEngineFactory) {
        scriptEngineFactory.getClass();
        this.myFactory = scriptEngineFactory;
    }

    public static ReplEvalResult a(KotlinJsr223JvmScriptEngineBase kotlinJsr223JvmScriptEngineBase, IReplStageState iReplStageState, CompiledKotlinScript compiledKotlinScript, ScriptContext scriptContext) {
        return kotlinJsr223JvmScriptEngineBase.getReplEvaluator().eval(iReplStageState, compiledKotlinScript.getCompiledData(), kotlinJsr223JvmScriptEngineBase.overrideScriptArgs(scriptContext), kotlinJsr223JvmScriptEngineBase.getInvokeWrapper(scriptContext));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.script.ScriptException */
    private final Object asJsr223EvalResult(Function0<? extends ReplEvalResult> body) throws ScriptException {
        try {
            ReplEvalResult replEvalResult = (ReplEvalResult) body.invoke();
            if (replEvalResult instanceof ReplEvalResult.ValueResult) {
                return ((ReplEvalResult.ValueResult) replEvalResult).getValue();
            }
            if (replEvalResult instanceof ReplEvalResult.UnitResult) {
                return null;
            }
            if (!(replEvalResult instanceof ReplEvalResult.Error)) {
                if (replEvalResult instanceof ReplEvalResult.Incomplete) {
                    throw new ScriptException("Error: incomplete code. " + ((ReplEvalResult.Incomplete) replEvalResult).getMessage());
                }
                if (!(replEvalResult instanceof ReplEvalResult.HistoryMismatch)) {
                    bu8.a();
                    return null;
                }
                throw new ScriptException("Repl history mismatch at line: " + ((ReplEvalResult.HistoryMismatch) replEvalResult).getLineNo());
            }
            if (replEvalResult instanceof ReplEvalResult.Error.Runtime) {
                ReplEvalResult.Error.Runtime runtime = (ReplEvalResult.Error.Runtime) replEvalResult;
                if (runtime.getCause() != null) {
                    Throwable cause = runtime.getCause();
                    Exception runtimeException = cause instanceof Exception ? (Exception) cause : null;
                    if (runtimeException == null) {
                        runtimeException = new RuntimeException(runtime.getCause());
                    }
                    throw new ScriptException(runtimeException);
                }
            }
            if (replEvalResult instanceof ReplEvalResult.Error.CompileTime) {
                ReplEvalResult.Error.CompileTime compileTime = (ReplEvalResult.Error.CompileTime) replEvalResult;
                if (compileTime.getLocation() != null) {
                    throw new ScriptException(compileTime.getMessage(), compileTime.getLocation().getPath(), compileTime.getLocation().getLine(), compileTime.getLocation().getColumn());
                }
            }
            throw new ScriptException(((ReplEvalResult.Error) replEvalResult).getMessage());
        } catch (Exception e) {
            throw new ScriptException(e);
        }
    }

    public static ReplEvalResult b(KotlinJsr223JvmScriptEngineBase kotlinJsr223JvmScriptEngineBase, IReplStageState iReplStageState, ReplCodeLine replCodeLine, ScriptContext scriptContext) {
        return kotlinJsr223JvmScriptEngineBase.getReplEvaluator().compileAndEval(iReplStageState, replCodeLine, kotlinJsr223JvmScriptEngineBase.overrideScriptArgs(scriptContext), kotlinJsr223JvmScriptEngineBase.getInvokeWrapper(scriptContext));
    }

    public static /* synthetic */ IReplStageState createState$default(KotlinJsr223JvmScriptEngineBase kotlinJsr223JvmScriptEngineBase, ReentrantReadWriteLock reentrantReadWriteLock, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createState");
            return null;
        }
        if ((i & 1) != 0) {
            reentrantReadWriteLock = new ReentrantReadWriteLock();
        }
        return kotlinJsr223JvmScriptEngineBase.createState(reentrantReadWriteLock);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.script.ScriptException */
    public CompiledScript compile(String script, ScriptContext context) throws ScriptException {
        script.getClass();
        context.getClass();
        ReplCodeLine replCodeLineNextCodeLine = nextCodeLine(context, script);
        ReplCompileResult replCompileResultCompile = getReplCompiler().compile(getCurrentState(context), replCodeLineNextCodeLine);
        if (replCompileResultCompile instanceof ReplCompileResult.Error) {
            ReplCompileResult.Error error = (ReplCompileResult.Error) replCompileResultCompile;
            throw new ScriptException("Error" + KotlinJsr223JvmScriptEngineBaseKt.locationString(error) + ": " + error.getMessage());
        }
        if (replCompileResultCompile instanceof ReplCompileResult.Incomplete) {
            throw new ScriptException("Error: incomplete code; " + ((ReplCompileResult.Incomplete) replCompileResultCompile).getMessage());
        }
        if (replCompileResultCompile instanceof ReplCompileResult.CompiledClasses) {
            return new CompiledKotlinScript(this, replCodeLineNextCodeLine, (ReplCompileResult.CompiledClasses) replCompileResultCompile);
        }
        bu8.a();
        return null;
    }

    public Object compileAndEval(String script, final ScriptContext context) {
        script.getClass();
        context.getClass();
        final ReplCodeLine replCodeLineNextCodeLine = nextCodeLine(context, script);
        final IReplStageState<?> currentState = getCurrentState(context);
        return asJsr223EvalResult(new Function0() { // from class: mc8
            public final Object invoke() {
                return KotlinJsr223JvmScriptEngineBase.b(this.b, currentState, replCodeLineNextCodeLine, context);
            }
        });
    }

    public Bindings createBindings() {
        Bindings simpleBindings = new SimpleBindings();
        simpleBindings.put(KotlinJsr223JvmScriptEngineBaseKt.KOTLIN_SCRIPT_ENGINE_BINDINGS_KEY, simpleBindings);
        return simpleBindings;
    }

    public abstract IReplStageState<?> createState(ReentrantReadWriteLock lock);

    public Object eval(final CompiledKotlinScript compiledScript, final ScriptContext context) {
        compiledScript.getClass();
        context.getClass();
        final IReplStageState<?> currentState = getCurrentState(context);
        return asJsr223EvalResult(new Function0() { // from class: nc8
            public final Object invoke() {
                return KotlinJsr223JvmScriptEngineBase.a(this.b, currentState, compiledScript, context);
            }
        });
    }

    public final IReplStageState<?> getCurrentState(ScriptContext context) {
        context.getClass();
        Map bindings = context.getBindings(100);
        bindings.getClass();
        Map map = bindings;
        Object objCreateState$default = map.get(KotlinJsr223JvmScriptEngineBaseKt.KOTLIN_SCRIPT_STATE_BINDINGS_KEY);
        if (objCreateState$default == null) {
            context.getBindings(100).put(KotlinJsr223JvmScriptEngineBaseKt.KOTLIN_SCRIPT_ENGINE_BINDINGS_KEY, this);
            objCreateState$default = createState$default(this, null, 1, null);
            map.put(KotlinJsr223JvmScriptEngineBaseKt.KOTLIN_SCRIPT_STATE_BINDINGS_KEY, objCreateState$default);
        }
        objCreateState$default.getClass();
        return (IReplStageState) objCreateState$default;
    }

    /* JADX INFO: renamed from: getFactory, reason: from getter */
    public ScriptEngineFactory getMyFactory() {
        return this.myFactory;
    }

    public InvokeWrapper getInvokeWrapper(ScriptContext context) {
        context.getClass();
        return null;
    }

    public final ScriptEngineFactory getMyFactory() {
        return this.myFactory;
    }

    public abstract ReplCompilerWithoutCheck getReplCompiler();

    public abstract ReplFullEvaluator getReplEvaluator();

    public final ReplCodeLine nextCodeLine(ScriptContext context, String code) {
        context.getClass();
        code.getClass();
        IReplStageState<?> currentState = getCurrentState(context);
        return new ReplCodeLine(currentState.getNextLineNo(), currentState.getCurrentGeneration(), code);
    }

    public ScriptArgsWithTypes overrideScriptArgs(ScriptContext context) {
        context.getClass();
        return null;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\n\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineBase$CompiledKotlinScript;", "Ljavax/script/CompiledScript;", "engine", "Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineBase;", "codeLine", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "compiledData", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineBase;Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;)V", "getEngine", "()Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineBase;", "getCodeLine", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "getCompiledData", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "eval", Argument.Delimiters.none, "context", "Ljavax/script/ScriptContext;", "Ljavax/script/ScriptEngine;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CompiledKotlinScript extends CompiledScript {
        private final ReplCodeLine codeLine;
        private final ReplCompileResult.CompiledClasses compiledData;
        private final KotlinJsr223JvmScriptEngineBase engine;

        public CompiledKotlinScript(KotlinJsr223JvmScriptEngineBase kotlinJsr223JvmScriptEngineBase, ReplCodeLine replCodeLine, ReplCompileResult.CompiledClasses compiledClasses) {
            kotlinJsr223JvmScriptEngineBase.getClass();
            replCodeLine.getClass();
            compiledClasses.getClass();
            this.engine = kotlinJsr223JvmScriptEngineBase;
            this.codeLine = replCodeLine;
            this.compiledData = compiledClasses;
        }

        public Object eval(ScriptContext context) {
            context.getClass();
            return this.engine.eval(this, context);
        }

        public final ReplCodeLine getCodeLine() {
            return this.codeLine;
        }

        public final ReplCompileResult.CompiledClasses getCompiledData() {
            return this.compiledData;
        }

        public ScriptEngine getEngine() {
            return this.engine;
        }

        public final KotlinJsr223JvmScriptEngineBase getEngine() {
            return this.engine;
        }
    }

    public Object eval(Reader script, ScriptContext context) {
        script.getClass();
        context.getClass();
        return compileAndEval(TextStreamsKt.readText(script), context);
    }

    public Object eval(String script, ScriptContext context) {
        script.getClass();
        context.getClass();
        return compileAndEval(script, context);
    }

    public CompiledScript compile(Reader script) {
        script.getClass();
        String text = TextStreamsKt.readText(script);
        ScriptContext context = getContext();
        context.getClass();
        return compile(text, context);
    }

    public CompiledScript compile(String script) {
        script.getClass();
        ScriptContext context = getContext();
        context.getClass();
        return compile(script, context);
    }
}

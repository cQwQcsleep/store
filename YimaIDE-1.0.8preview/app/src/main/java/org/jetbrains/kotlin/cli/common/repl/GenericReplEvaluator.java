package org.jetbrains.kotlin.cli.common.repl;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.repl.GenericReplEvaluator;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J0\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\b2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\t\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluator;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvaluator;", "baseClasspath", Argument.Delimiters.none, "Ljava/io/File;", "baseClassloader", "Ljava/lang/ClassLoader;", "fallbackScriptArgs", "Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "repeatingMode", "Lorg/jetbrains/kotlin/cli/common/repl/ReplRepeatingMode;", "<init>", "(Ljava/lang/Iterable;Ljava/lang/ClassLoader;Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;Lorg/jetbrains/kotlin/cli/common/repl/ReplRepeatingMode;)V", "getBaseClasspath", "()Ljava/lang/Iterable;", "getBaseClassloader", "()Ljava/lang/ClassLoader;", "getFallbackScriptArgs", "()Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "getRepeatingMode", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplRepeatingMode;", "createState", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "eval", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "state", "compileResult", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "scriptArgs", "invokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class GenericReplEvaluator implements ReplEvaluator {
    private final ClassLoader baseClassloader;
    private final Iterable<File> baseClasspath;
    private final ScriptArgsWithTypes fallbackScriptArgs;
    private final ReplRepeatingMode repeatingMode;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReplRepeatingMode.values().length];
            try {
                iArr[ReplRepeatingMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReplRepeatingMode.REPEAT_ONLY_MOST_RECENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReplRepeatingMode.REPEAT_ANY_PREVIOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ GenericReplEvaluator(Iterable iterable, ClassLoader classLoader, ScriptArgsWithTypes scriptArgsWithTypes, ReplRepeatingMode replRepeatingMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iterable, (i & 2) != 0 ? Thread.currentThread().getContextClassLoader() : classLoader, (i & 4) != 0 ? null : scriptArgsWithTypes, (i & 8) != 0 ? ReplRepeatingMode.REPEAT_ONLY_MOST_RECENT : replRepeatingMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object eval$lambda$0$5(Constructor constructor, Object[] objArr) {
        return constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.CreateReplStageStateAction
    public IReplStageState<?> createState(ReentrantReadWriteLock lock) {
        lock.getClass();
        return new GenericReplEvaluatorState(this.baseClasspath, this.baseClassloader, lock);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r5v24, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v5, types: [org.jetbrains.kotlin.cli.common.repl.HistoryActionsForNoRepeat] */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r9v14, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r9v15, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r9v16, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r9v17, types: [org.jetbrains.kotlin.cli.common.repl.EvalClassWithInstanceAndLoader] */
    @Override // org.jetbrains.kotlin.cli.common.repl.ReplEvalAction
    public ReplEvalResult eval(IReplStageState<?> state, ReplCompileResult.CompiledClasses compileResult, ScriptArgsWithTypes scriptArgs, InvokeWrapper invokeWrapper) throws Throwable {
        int i;
        ?? historyActionsForNoRepeat;
        ArrayList arrayList;
        List listEmptyList;
        Object[] objArr;
        ReplEvalResult unitResult;
        Field field;
        Field field2;
        Object[] array;
        Class<?> cls;
        KClass<? extends Object>[] scriptArgsTypes;
        ILineId id;
        Object next;
        state.getClass();
        compileResult.getClass();
        ReentrantReadWriteLock lock = state.getLock();
        ReentrantReadWriteLock.ReadLock lock2 = lock.readLock();
        int readHoldCount = lock.getWriteHoldCount() == 0 ? lock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock2.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            GenericReplEvaluatorState genericReplEvaluatorState = (GenericReplEvaluatorState) state.asState(GenericReplEvaluatorState.class);
            int i3 = WhenMappings.$EnumSwitchMapping$0[this.repeatingMode.ordinal()];
            i = 2;
            if (i3 == 1) {
                historyActionsForNoRepeat = new HistoryActionsForNoRepeat(genericReplEvaluatorState);
            } else if (i3 == 2) {
                ReplHistoryRecord<EvalClassWithInstanceAndLoader> replHistoryRecordPeek = genericReplEvaluatorState.getHistory().peek();
                historyActionsForNoRepeat = (replHistoryRecordPeek == null || !Intrinsics.areEqual(replHistoryRecordPeek.getId(), compileResult.getLineId())) ? new HistoryActionsForNoRepeat(genericReplEvaluatorState) : new HistoryActionsForRepeatRecentOnly(genericReplEvaluatorState);
            } else {
                if (i3 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Iterator<EvalClassWithInstanceAndLoader> it = genericReplEvaluatorState.getHistory().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((ReplHistoryRecord) next).getId(), compileResult.getLineId()));
                ReplHistoryRecord replHistoryRecord = (ReplHistoryRecord) next;
                historyActionsForNoRepeat = replHistoryRecord == null ? new HistoryActionsForNoRepeat(genericReplEvaluatorState) : new HistoryActionsForRepeatAny(genericReplEvaluatorState, replHistoryRecord);
            }
            Pair<ReplHistoryRecord<EvalClassWithInstanceAndLoader>, ILineId> pairFirstMismatch = historyActionsForNoRepeat.firstMismatch(CollectionsKt.asSequence(compileResult.getPreviousLines()));
            if (pairFirstMismatch != null) {
                ReplHistoryRecord replHistoryRecord2 = (ReplHistoryRecord) pairFirstMismatch.getFirst();
                ReplEvalResult.HistoryMismatch historyMismatch = new ReplEvalResult.HistoryMismatch(((replHistoryRecord2 == null || (id = replHistoryRecord2.getId()) == null) && (id = (ILineId) pairFirstMismatch.getSecond()) == null) ? -1 : id.getNo());
                for (int i4 = 0; i4 < readHoldCount; i4++) {
                    lock2.lock();
                }
                writeLock.unlock();
                return historyMismatch;
            }
            try {
                try {
                    Pair<ClassLoader, Class<? extends Object>> pairProcessClasses = historyActionsForNoRepeat.processClasses(compileResult);
                    ClassLoader classLoader = (ClassLoader) pairProcessClasses.component1();
                    Class cls2 = (Class) pairProcessClasses.component2();
                    ScriptArgsWithTypes scriptArgsWithTypes = scriptArgs == null ? this.fallbackScriptArgs : scriptArgs;
                    Object[] scriptArgs2 = scriptArgsWithTypes != null ? scriptArgsWithTypes.getScriptArgs() : null;
                    if (scriptArgsWithTypes == null || (scriptArgsTypes = scriptArgsWithTypes.getScriptArgsTypes()) == null) {
                        arrayList = null;
                    } else {
                        arrayList = new ArrayList(scriptArgsTypes.length);
                        for (KClass<? extends Object> kClass : scriptArgsTypes) {
                            arrayList.add(JvmClassMappingKt.getJavaClass(kClass));
                        }
                    }
                    Class[] clsArr = {Object[].class};
                    if (scriptArgs2 != null) {
                        listEmptyList = new ArrayList(scriptArgs2.length);
                        int length = scriptArgs2.length;
                        int i5 = 0;
                        int i6 = 0;
                        while (i6 < length) {
                            Object obj = scriptArgs2[i6];
                            int i7 = i5 + 1;
                            if (arrayList == null || (cls = (Class) CollectionsKt.getOrNull(arrayList, i5)) == null) {
                                cls = obj != null ? obj.getClass() : Object.class;
                            }
                            listEmptyList.add(cls);
                            i6++;
                            i5 = i7;
                        }
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    Class[] clsArr2 = (Class[]) ArraysKt.plus(clsArr, listEmptyList);
                    SpreadBuilder spreadBuilder = new SpreadBuilder(2);
                    List<EvalClassWithInstanceAndLoader> effectiveHistory = historyActionsForNoRepeat.getEffectiveHistory();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(effectiveHistory, 10));
                    Iterator it2 = effectiveHistory.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((EvalClassWithInstanceAndLoader) it2.next()).getInstance());
                    }
                    if (arrayList2.isEmpty()) {
                        arrayList2 = null;
                    }
                    if (arrayList2 == null || (array = arrayList2.toArray(new Object[0])) == null) {
                        i = 0;
                        objArr = new Object[0];
                    } else {
                        objArr = array;
                        i = 0;
                    }
                    spreadBuilder.add(objArr);
                    if (scriptArgs2 == null) {
                        scriptArgs2 = new Object[i];
                    }
                    spreadBuilder.addSpread(scriptArgs2);
                    final Object[] array2 = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
                    final Constructor constructor = cls2.getConstructor((Class[]) Arrays.copyOf(clsArr2, clsArr2.length));
                    historyActionsForNoRepeat.addPlaceholder(compileResult.getLineId(), new EvalClassWithInstanceAndLoader(JvmClassMappingKt.getKotlinClass(cls2), null, classLoader, invokeWrapper));
                    ?? contextClassLoader = Thread.currentThread().getContextClassLoader();
                    Thread.currentThread().setContextClassLoader(classLoader);
                    KClass kotlinClass = ".<init>";
                    try {
                        try {
                            try {
                                Object objInvoke = invokeWrapper != null ? invokeWrapper.invoke(new Function0() { // from class: zy5
                                    public final Object invoke() {
                                        return GenericReplEvaluator.eval$lambda$0$5(constructor, array2);
                                    }
                                }) : constructor.newInstance(Arrays.copyOf(array2, array2.length));
                                historyActionsForNoRepeat.removePlaceholder(compileResult.getLineId());
                                Thread.currentThread().setContextClassLoader(contextClassLoader);
                                LineId lineId = compileResult.getLineId();
                                kotlinClass = JvmClassMappingKt.getKotlinClass(cls2);
                                contextClassLoader = new EvalClassWithInstanceAndLoader(kotlinClass, objInvoke, classLoader, invokeWrapper);
                                historyActionsForNoRepeat.addFinal(lineId, contextClassLoader);
                                if (compileResult.getHasResult()) {
                                    String strScriptResultFieldName = ReplUtilKt.scriptResultFieldName(compileResult.getLineId().getNo());
                                    Field[] declaredFields = cls2.getDeclaredFields();
                                    declaredFields.getClass();
                                    int length2 = declaredFields.length;
                                    int i8 = i;
                                    while (true) {
                                        if (i8 >= length2) {
                                            field = null;
                                            break;
                                        }
                                        field = declaredFields[i8];
                                        if (Intrinsics.areEqual(field.getName(), strScriptResultFieldName)) {
                                            break;
                                        }
                                        i8++;
                                    }
                                    if (field != null) {
                                        field.setAccessible(true);
                                        field2 = field;
                                    } else {
                                        field2 = null;
                                    }
                                    field2.getClass();
                                    unitResult = new ReplEvalResult.ValueResult(strScriptResultFieldName, field2.get(objInvoke), compileResult.getType(), null, 8, null);
                                } else {
                                    unitResult = new ReplEvalResult.UnitResult();
                                }
                                for (int i9 = i; i9 < readHoldCount; i9++) {
                                    lock2.lock();
                                }
                                writeLock.unlock();
                                return unitResult;
                            } catch (Throwable th) {
                                historyActionsForNoRepeat.removePlaceholder(compileResult.getLineId());
                                Thread.currentThread().setContextClassLoader(contextClassLoader);
                                throw th;
                            }
                        } catch (InvocationTargetException e) {
                            Throwable cause = e.getCause();
                            cause.getClass();
                            String strRenderReplStackTrace = ReplUtilKt.renderReplStackTrace(cause, cls2.getName() + ((String) kotlinClass));
                            Throwable targetException = e.getTargetException();
                            ReplEvalResult.Error.Runtime runtime = new ReplEvalResult.Error.Runtime(strRenderReplStackTrace, targetException instanceof Exception ? (Exception) targetException : null);
                            historyActionsForNoRepeat.removePlaceholder(compileResult.getLineId());
                            Thread.currentThread().setContextClassLoader(contextClassLoader);
                            for (int i10 = i; i10 < readHoldCount; i10++) {
                                lock2.lock();
                            }
                            writeLock.unlock();
                            return runtime;
                        }
                    } catch (Throwable th2) {
                        Throwable cause2 = th2.getCause();
                        cause2.getClass();
                        ReplEvalResult.Error.Runtime runtime2 = new ReplEvalResult.Error.Runtime(ReplUtilKt.renderReplStackTrace(cause2, cls2.getName() + ((String) kotlinClass)), th2 instanceof Exception ? th2 : null);
                        historyActionsForNoRepeat.removePlaceholder(compileResult.getLineId());
                        Thread.currentThread().setContextClassLoader(contextClassLoader);
                        for (int i11 = i; i11 < readHoldCount; i11++) {
                            lock2.lock();
                        }
                        writeLock.unlock();
                        return runtime2;
                    }
                } catch (Exception e2) {
                    String message = e2.getMessage();
                    if (message == null) {
                        message = "unknown";
                    }
                    ReplEvalResult.Error.Runtime runtime3 = new ReplEvalResult.Error.Runtime(message, e2);
                    for (int i12 = 0; i12 < readHoldCount; i12++) {
                        lock2.lock();
                    }
                    writeLock.unlock();
                    return runtime3;
                }
            } catch (Throwable th3) {
                th = th3;
                for (int i13 = i; i13 < readHoldCount; i13++) {
                    lock2.lock();
                }
                writeLock.unlock();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            i = 0;
        }
    }

    public final ClassLoader getBaseClassloader() {
        return this.baseClassloader;
    }

    public final Iterable<File> getBaseClasspath() {
        return this.baseClasspath;
    }

    public final ScriptArgsWithTypes getFallbackScriptArgs() {
        return this.fallbackScriptArgs;
    }

    public final ReplRepeatingMode getRepeatingMode() {
        return this.repeatingMode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GenericReplEvaluator(Iterable<? extends File> iterable, ClassLoader classLoader, ScriptArgsWithTypes scriptArgsWithTypes, ReplRepeatingMode replRepeatingMode) {
        iterable.getClass();
        replRepeatingMode.getClass();
        this.baseClasspath = iterable;
        this.baseClassloader = classLoader;
        this.fallbackScriptArgs = scriptArgsWithTypes;
        this.repeatingMode = replRepeatingMode;
    }
}

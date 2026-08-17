package org.jetbrains.kotlin.cli.common.repl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\r\u001a\u001a\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\nH\u0016J$\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\u001b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001c0\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J2\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u001b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001c0\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/HistoryActionsForNoRepeat;", Argument.Delimiters.none, "state", "Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;)V", "getState", "()Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;", "effectiveHistory", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/repl/EvalClassWithInstanceAndLoader;", "getEffectiveHistory", "()Ljava/util/List;", "firstMismatch", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "other", "Lkotlin/sequences/Sequence;", "addPlaceholder", Argument.Delimiters.none, "lineId", "value", "removePlaceholder", Argument.Delimiters.none, "addFinal", "processClasses", "Ljava/lang/ClassLoader;", "Ljava/lang/Class;", "compileResult", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "prependClassLoaderWithNewClasses", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
class HistoryActionsForNoRepeat {
    private final GenericReplEvaluatorState state;

    public HistoryActionsForNoRepeat(GenericReplEvaluatorState genericReplEvaluatorState) {
        genericReplEvaluatorState.getClass();
        this.state = genericReplEvaluatorState;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0091  */
    private final Pair<ClassLoader, Class<? extends Object>> prependClassLoaderWithNewClasses(List<EvalClassWithInstanceAndLoader> effectiveHistory, ReplCompileResult.CompiledClasses compileResult) throws Exception {
        ClassLoader topClassLoader;
        EvalClassWithInstanceAndLoader evalClassWithInstanceAndLoader = (EvalClassWithInstanceAndLoader) CollectionsKt.lastOrNull(effectiveHistory);
        if (evalClassWithInstanceAndLoader == null || (topClassLoader = evalClassWithInstanceAndLoader.getClassLoader()) == null) {
            topClassLoader = this.state.getTopClassLoader();
        }
        ReplClassLoader replClassLoaderMakeReplClassLoader = GenericEvaluatorStateKt.makeReplClassLoader(topClassLoader, compileResult.getClasspathAddendum());
        String mainClassName = compileResult.getMainClassName();
        List<CompiledClassData> classes = compileResult.getClasses();
        ArrayList<CompiledClassData> arrayList = new ArrayList();
        for (Object obj : classes) {
            if (StringsKt.endsWith$default(((CompiledClassData) obj).getPath(), ".class", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        String strReplace$default = null;
        for (CompiledClassData compiledClassData : arrayList) {
            JvmClassName jvmClassNamePrependClassLoaderWithNewClasses$classNameFromPath = prependClassLoaderWithNewClasses$classNameFromPath(compiledClassData.getPath());
            if (Intrinsics.areEqual(jvmClassNamePrependClassLoaderWithNewClasses$classNameFromPath.getInternalName(), mainClassName)) {
                String internalName = jvmClassNamePrependClassLoaderWithNewClasses$classNameFromPath.getInternalName();
                internalName.getClass();
                strReplace$default = StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null);
            } else {
                String internalName2 = jvmClassNamePrependClassLoaderWithNewClasses$classNameFromPath.getInternalName();
                internalName2.getClass();
                if (StringsKt.endsWith$default(internalName2, "/" + mainClassName, false, 2, (Object) null)) {
                    String internalName3 = jvmClassNamePrependClassLoaderWithNewClasses$classNameFromPath.getInternalName();
                    internalName3.getClass();
                    strReplace$default = StringsKt.replace$default(internalName3, '/', '.', false, 4, (Object) null);
                }
            }
            replClassLoaderMakeReplClassLoader.addClass(jvmClassNamePrependClassLoaderWithNewClasses$classNameFromPath, compiledClassData.getBytes());
        }
        try {
            strReplace$default.getClass();
            return new Pair<>(replClassLoaderMakeReplClassLoader, replClassLoaderMakeReplClassLoader.loadClass(strReplace$default));
        } catch (Throwable th) {
            throw new Exception("Error loading class " + strReplace$default + ": known classes: " + prependClassLoaderWithNewClasses$compiledClassesNames(compileResult), th);
        }
    }

    private static final JvmClassName prependClassLoaderWithNewClasses$classNameFromPath(String str) {
        JvmClassName jvmClassNameByInternalName = JvmClassName.byInternalName(StringsKt.removeSuffix(str, ".class"));
        jvmClassNameByInternalName.getClass();
        return jvmClassNameByInternalName;
    }

    private static final List<String> prependClassLoaderWithNewClasses$compiledClassesNames(ReplCompileResult.CompiledClasses compiledClasses) {
        List<CompiledClassData> classes = compiledClasses.getClasses();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(classes, 10));
        Iterator<T> it = classes.iterator();
        while (it.hasNext()) {
            String internalName = prependClassLoaderWithNewClasses$classNameFromPath(((CompiledClassData) it.next()).getPath()).getInternalName();
            internalName.getClass();
            arrayList.add(StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null));
        }
        return arrayList;
    }

    public void addFinal(ILineId lineId, EvalClassWithInstanceAndLoader value) {
        lineId.getClass();
        value.getClass();
        this.state.getHistory().push(lineId, value);
    }

    public void addPlaceholder(ILineId lineId, EvalClassWithInstanceAndLoader value) {
        lineId.getClass();
        value.getClass();
        this.state.getHistory().push(lineId, value);
    }

    public Pair<ReplHistoryRecord<EvalClassWithInstanceAndLoader>, ILineId> firstMismatch(Sequence<? extends ILineId> other) {
        other.getClass();
        return ReplStateKt.firstMismatch(this.state.getHistory(), other);
    }

    public List<EvalClassWithInstanceAndLoader> getEffectiveHistory() {
        IReplStageHistory<EvalClassWithInstanceAndLoader> history = this.state.getHistory();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(history, 10));
        Iterator<EvalClassWithInstanceAndLoader> it = history.iterator();
        while (it.hasNext()) {
            arrayList.add((EvalClassWithInstanceAndLoader) ((ReplHistoryRecord) it.next()).getItem());
        }
        return arrayList;
    }

    public final GenericReplEvaluatorState getState() {
        return this.state;
    }

    public Pair<ClassLoader, Class<? extends Object>> processClasses(ReplCompileResult.CompiledClasses compileResult) {
        compileResult.getClass();
        return prependClassLoaderWithNewClasses(getEffectiveHistory(), compileResult);
    }

    public boolean removePlaceholder(ILineId lineId) {
        lineId.getClass();
        return this.state.getHistory().verifiedPop(lineId) != null;
    }
}

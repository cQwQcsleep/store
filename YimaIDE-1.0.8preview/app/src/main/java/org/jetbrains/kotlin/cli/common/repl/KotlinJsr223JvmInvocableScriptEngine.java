package org.jetbrains.kotlin.cli.common.repl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.script.Invocable;
import javax.script.ScriptException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.full.KClasses;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.common.repl.EvalClassWithInstanceAndLoader;
import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmInvocableScriptEngine;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J&\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J1\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0011\"\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\u0012J;\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0011\"\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\u0015J7\u0010\u0016\u001a\u0004\u0018\u00010\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0011H\u0002¢\u0006\u0002\u0010\u0018J)\u0010\u0019\u001a\u0004\u0018\u0001H\u001a\"\b\b\u0000\u0010\u001a*\u00020\f2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJ3\u0010\u0019\u001a\u0004\u0018\u0001H\u001a\"\b\b\u0000\u0010\u001a*\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\f2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001eJ3\u0010\u001f\u001a\u0004\u0018\u0001H\u001a\"\b\b\u0000\u0010\u001a*\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\f2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u001cH\u0002¢\u0006\u0002\u0010\u001eR\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006 À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmInvocableScriptEngine;", "Ljavax/script/Invocable;", "state", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "getState", "()Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "prioritizedHistory", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/repl/EvalClassWithInstanceAndLoader;", "receiverClass", "Lkotlin/reflect/KClass;", "receiverInstance", Argument.Delimiters.none, "invokeFunction", ModuleXmlParser.NAME, Argument.Delimiters.none, "args", Argument.Delimiters.none, "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", "invokeMethod", "thiz", "(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", "invokeImpl", "prioritizedCallOrder", "(Ljava/util/List;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", "getInterface", "T", "clasz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "proxyInterface", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface KotlinJsr223JvmInvocableScriptEngine extends Invocable {
    static Triple a(Object[] objArr, String str, EvalClassWithInstanceAndLoader evalClassWithInstanceAndLoader) {
        evalClassWithInstanceAndLoader.getClass();
        KClass<?> kClassComponent1 = evalClassWithInstanceAndLoader.component1();
        Object evalClassWithInstanceAndLoader2 = evalClassWithInstanceAndLoader.getInstance();
        InvokeWrapper invokeWrapper = evalClassWithInstanceAndLoader.getInvokeWrapper();
        Collection functions = KClasses.getFunctions(kClassComponent1);
        ArrayList arrayList = new ArrayList();
        for (Object obj : functions) {
            if (Intrinsics.areEqual(((KFunction) obj).getName(), str)) {
                arrayList.add(obj);
            }
        }
        Pair pairAccess$findMapping = KotlinJsr223JvmInvocableScriptEngineKt.access$findMapping(arrayList, CollectionsKt.plus(CollectionsKt.listOf(evalClassWithInstanceAndLoader2), objArr));
        if (pairAccess$findMapping != null) {
            return new Triple(pairAccess$findMapping.getFirst(), pairAccess$findMapping.getSecond(), invokeWrapper);
        }
        return null;
    }

    static Object b(KotlinJsr223JvmInvocableScriptEngine kotlinJsr223JvmInvocableScriptEngine, List list, Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        name.getClass();
        if (objArr == null) {
            objArr = new Object[0];
        }
        return kotlinJsr223JvmInvocableScriptEngine.invokeImpl(list, name, objArr);
    }

    static Object c(KFunction kFunction, Map map) {
        return kFunction.callBy(map);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.script.ScriptException */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v6, types: [kotlin.reflect.KCallable, kotlin.reflect.KFunction] */
    private default Object invokeImpl(List<EvalClassWithInstanceAndLoader> prioritizedCallOrder, final String name, final Object[] args) throws NoSuchMethodException, ScriptException {
        Triple triple = (Triple) SequencesKt.firstOrNull(SequencesKt.filterNotNull(SequencesKt.map(CollectionsKt.asSequence(prioritizedCallOrder), new Function1() { // from class: jc8
            public final Object invoke(Object obj) {
                return KotlinJsr223JvmInvocableScriptEngine.a(args, name, (EvalClassWithInstanceAndLoader) obj);
            }
        })));
        if (triple == null) {
            throw new NoSuchMethodException("no suitable function '" + name + "' found");
        }
        final KCallable kCallableAreEqual = (KFunction) triple.component1();
        final Map map = (Map) triple.component2();
        InvokeWrapper invokeWrapper = (InvokeWrapper) triple.component3();
        try {
            Object objInvoke = invokeWrapper != null ? invokeWrapper.invoke(new Function0() { // from class: kc8
                public final Object invoke() {
                    return KotlinJsr223JvmInvocableScriptEngine.c(kCallableAreEqual, map);
                }
            }) : kCallableAreEqual.callBy(map);
            kCallableAreEqual = Intrinsics.areEqual(kCallableAreEqual.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(Unit.class));
            return kCallableAreEqual != 0 ? Unit.INSTANCE : objInvoke;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            cause.getClass();
            throw new ScriptException(ReplUtilKt.renderReplStackTrace(cause, kCallableAreEqual.getName()));
        }
    }

    private default List<EvalClassWithInstanceAndLoader> prioritizedHistory(KClass<?> receiverClass, Object receiverInstance) {
        Object next;
        IReplStageHistory<EvalClassWithInstanceAndLoader> history = ((GenericReplEvaluatorState) getState().asState(GenericReplEvaluatorState.class)).getHistory();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(history, 10));
        Iterator<EvalClassWithInstanceAndLoader> it = history.iterator();
        while (it.hasNext()) {
            arrayList.add((EvalClassWithInstanceAndLoader) ((ReplHistoryRecord) it.next()).getItem());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((EvalClassWithInstanceAndLoader) obj).getInstance() != null) {
                arrayList2.add(obj);
            }
        }
        List<EvalClassWithInstanceAndLoader> listEnsureNotEmpty = ReplUtilKt.ensureNotEmpty(CollectionsKt.reversed(arrayList2), "no script ");
        if (receiverInstance == null) {
            return listEnsureNotEmpty;
        }
        if (receiverClass == null) {
            receiverClass = Reflection.getOrCreateKotlinClass(receiverInstance.getClass());
        }
        List<EvalClassWithInstanceAndLoader> list = listEnsureNotEmpty;
        Iterator<T> it2 = list.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (!Intrinsics.areEqual(((EvalClassWithInstanceAndLoader) next).getInstance(), receiverInstance));
        EvalClassWithInstanceAndLoader evalClassWithInstanceAndLoader = (EvalClassWithInstanceAndLoader) next;
        if (evalClassWithInstanceAndLoader == null) {
            ClassLoader classLoader = JvmClassMappingKt.getJavaClass(receiverClass).getClassLoader();
            classLoader.getClass();
            evalClassWithInstanceAndLoader = new EvalClassWithInstanceAndLoader(receiverClass, receiverInstance, classLoader, ((EvalClassWithInstanceAndLoader) CollectionsKt.first(listEnsureNotEmpty)).getInvokeWrapper());
        }
        List listListOf = CollectionsKt.listOf(evalClassWithInstanceAndLoader);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : list) {
            if (!Intrinsics.areEqual((EvalClassWithInstanceAndLoader) obj2, evalClassWithInstanceAndLoader)) {
                arrayList3.add(obj2);
            }
        }
        return CollectionsKt.plus(listListOf, arrayList3);
    }

    private default <T> T proxyInterface(Object thiz, Class<T> clasz) {
        if (getState().getHistory().size() == 0) {
            k2d.a("no script");
            return null;
        }
        final List<EvalClassWithInstanceAndLoader> listPrioritizedHistory = prioritizedHistory(thiz != null ? JvmClassMappingKt.getKotlinClass(thiz.getClass()) : null, thiz);
        if (clasz == null) {
            w01.a("class object cannot be null");
            return null;
        }
        if (clasz.isInterface()) {
            return (T) KClasses.safeCast(JvmClassMappingKt.getKotlinClass(clasz), Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clasz}, new InvocationHandler() { // from class: lc8
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return KotlinJsr223JvmInvocableScriptEngine.b(this.b, listPrioritizedHistory, obj, method, objArr);
                }
            }));
        }
        w01.a("expecting interface");
        return null;
    }

    default <T> T getInterface(Object thiz, Class<T> clasz) {
        if (thiz != null) {
            return (T) proxyInterface(thiz, clasz);
        }
        w01.a("object cannot be null");
        return null;
    }

    IReplStageState<?> getState();

    default Object invokeFunction(String name, Object... args) {
        args.getClass();
        if (name != null) {
            return invokeImpl(prioritizedHistory(null, null), name, args);
        }
        x0e.a("function name cannot be null");
        return null;
    }

    default Object invokeMethod(Object thiz, String name, Object... args) {
        args.getClass();
        if (name == null) {
            x0e.a("method name cannot be null");
            return null;
        }
        if (thiz != null) {
            return invokeImpl(prioritizedHistory(Reflection.getOrCreateKotlinClass(thiz.getClass()), thiz), name, args);
        }
        w01.a("cannot invoke method on the null object");
        return null;
    }

    default <T> T getInterface(Class<T> clasz) {
        return (T) proxyInterface(null, clasz);
    }
}

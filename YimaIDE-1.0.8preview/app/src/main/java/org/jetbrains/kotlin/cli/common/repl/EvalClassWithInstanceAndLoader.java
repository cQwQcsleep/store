package org.jetbrains.kotlin.cli.common.repl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003J9\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/EvalClassWithInstanceAndLoader;", Argument.Delimiters.none, "klass", "Lkotlin/reflect/KClass;", "instance", "classLoader", "Ljava/lang/ClassLoader;", "invokeWrapper", "Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "<init>", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Ljava/lang/ClassLoader;Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;)V", "getKlass", "()Lkotlin/reflect/KClass;", "getInstance", "()Ljava/lang/Object;", "getClassLoader", "()Ljava/lang/ClassLoader;", "getInvokeWrapper", "()Lorg/jetbrains/kotlin/cli/common/repl/InvokeWrapper;", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class EvalClassWithInstanceAndLoader {
    private final ClassLoader classLoader;
    private final Object instance;
    private final InvokeWrapper invokeWrapper;
    private final KClass<?> klass;

    public EvalClassWithInstanceAndLoader(KClass<?> kClass, Object obj, ClassLoader classLoader, InvokeWrapper invokeWrapper) {
        kClass.getClass();
        classLoader.getClass();
        this.klass = kClass;
        this.instance = obj;
        this.classLoader = classLoader;
        this.invokeWrapper = invokeWrapper;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EvalClassWithInstanceAndLoader copy$default(EvalClassWithInstanceAndLoader evalClassWithInstanceAndLoader, KClass kClass, Object obj, ClassLoader classLoader, InvokeWrapper invokeWrapper, int i, Object obj2) {
        if ((i & 1) != 0) {
            kClass = evalClassWithInstanceAndLoader.klass;
        }
        if ((i & 2) != 0) {
            obj = evalClassWithInstanceAndLoader.instance;
        }
        if ((i & 4) != 0) {
            classLoader = evalClassWithInstanceAndLoader.classLoader;
        }
        if ((i & 8) != 0) {
            invokeWrapper = evalClassWithInstanceAndLoader.invokeWrapper;
        }
        return evalClassWithInstanceAndLoader.copy(kClass, obj, classLoader, invokeWrapper);
    }

    public final KClass<?> component1() {
        return this.klass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Object getInstance() {
        return this.instance;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final InvokeWrapper getInvokeWrapper() {
        return this.invokeWrapper;
    }

    public final EvalClassWithInstanceAndLoader copy(KClass<?> klass, Object instance, ClassLoader classLoader, InvokeWrapper invokeWrapper) {
        klass.getClass();
        classLoader.getClass();
        return new EvalClassWithInstanceAndLoader(klass, instance, classLoader, invokeWrapper);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EvalClassWithInstanceAndLoader)) {
            return false;
        }
        EvalClassWithInstanceAndLoader evalClassWithInstanceAndLoader = (EvalClassWithInstanceAndLoader) other;
        return Intrinsics.areEqual(this.klass, evalClassWithInstanceAndLoader.klass) && Intrinsics.areEqual(this.instance, evalClassWithInstanceAndLoader.instance) && Intrinsics.areEqual(this.classLoader, evalClassWithInstanceAndLoader.classLoader) && Intrinsics.areEqual(this.invokeWrapper, evalClassWithInstanceAndLoader.invokeWrapper);
    }

    public final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public final Object getInstance() {
        return this.instance;
    }

    public final InvokeWrapper getInvokeWrapper() {
        return this.invokeWrapper;
    }

    public final KClass<?> getKlass() {
        return this.klass;
    }

    public int hashCode() {
        int iHashCode = this.klass.hashCode() * 31;
        Object obj = this.instance;
        int iHashCode2 = (((iHashCode + (obj == null ? 0 : obj.hashCode())) * 31) + this.classLoader.hashCode()) * 31;
        InvokeWrapper invokeWrapper = this.invokeWrapper;
        return iHashCode2 + (invokeWrapper != null ? invokeWrapper.hashCode() : 0);
    }

    public String toString() {
        return "EvalClassWithInstanceAndLoader(klass=" + this.klass + ", instance=" + this.instance + ", classLoader=" + this.classLoader + ", invokeWrapper=" + this.invokeWrapper + ')';
    }
}

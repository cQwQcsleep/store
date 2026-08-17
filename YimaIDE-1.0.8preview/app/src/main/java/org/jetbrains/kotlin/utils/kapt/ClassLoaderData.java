package org.jetbrains.kotlin.utils.kapt;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001f\u0010\u0006\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00030\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/utils/kapt/ClassLoaderData;", "", "classLoader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "ref", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getRef", "()Ljava/lang/ref/WeakReference;", "age", "", "getAge", "()I", "setAge", "(I)V", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
final class ClassLoaderData {
    private volatile int age;
    private final WeakReference<ClassLoader> ref;

    public ClassLoaderData(ClassLoader classLoader) {
        classLoader.getClass();
        this.ref = new WeakReference<>(classLoader);
    }

    public final int getAge() {
        return this.age;
    }

    public final WeakReference<ClassLoader> getRef() {
        return this.ref;
    }

    public final void setAge(int i) {
        this.age = i;
    }
}

package org.jetbrains.kotlin.psi;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/psi/ArgumentType;", "T", "", "klass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getKlass", "()Ljava/lang/Class;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class ArgumentType<T> {
    private final Class<T> klass;

    public ArgumentType(Class<T> cls) {
        cls.getClass();
        this.klass = cls;
    }

    public final Class<T> getKlass() {
        return this.klass;
    }
}

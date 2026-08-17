package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.PrimitiveType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ\u0015\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ\u0015\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmTypeFactory;", "T", "", "boxType", "possiblyPrimitiveType", "(Ljava/lang/Object;)Ljava/lang/Object;", "createFromString", "representation", "", "(Ljava/lang/String;)Ljava/lang/Object;", "createPrimitiveType", "primitiveType", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "(Lorg/jetbrains/kotlin/builtins/PrimitiveType;)Ljava/lang/Object;", "createObjectType", "internalName", "toString", "type", "(Ljava/lang/Object;)Ljava/lang/String;", "javaLangClassType", "getJavaLangClassType", "()Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface JvmTypeFactory<T> {
    T boxType(T possiblyPrimitiveType);

    T createFromString(String representation);

    T createObjectType(String internalName);

    T createPrimitiveType(PrimitiveType primitiveType);

    T getJavaLangClassType();

    String toString(T type);
}

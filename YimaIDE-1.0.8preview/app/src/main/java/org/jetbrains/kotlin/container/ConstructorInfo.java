package org.jetbrains.kotlin.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/container/ConstructorInfo;", Argument.Delimiters.none, "constructor", "Ljava/lang/reflect/Constructor;", "parameters", Argument.Delimiters.none, "Ljava/lang/reflect/Type;", "<init>", "(Ljava/lang/reflect/Constructor;Ljava/util/List;)V", "getConstructor", "()Ljava/lang/reflect/Constructor;", "getParameters", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ConstructorInfo {
    private final Constructor<?> constructor;
    private final List<Type> parameters;

    /* JADX WARN: Multi-variable type inference failed */
    public ConstructorInfo(Constructor<?> constructor, List<? extends Type> list) {
        constructor.getClass();
        list.getClass();
        this.constructor = constructor;
        this.parameters = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConstructorInfo copy$default(ConstructorInfo constructorInfo, Constructor constructor, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            constructor = constructorInfo.constructor;
        }
        if ((i & 2) != 0) {
            list = constructorInfo.parameters;
        }
        return constructorInfo.copy(constructor, list);
    }

    public final Constructor<?> component1() {
        return this.constructor;
    }

    public final List<Type> component2() {
        return this.parameters;
    }

    public final ConstructorInfo copy(Constructor<?> constructor, List<? extends Type> parameters) {
        constructor.getClass();
        parameters.getClass();
        return new ConstructorInfo(constructor, parameters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConstructorInfo)) {
            return false;
        }
        ConstructorInfo constructorInfo = (ConstructorInfo) other;
        return Intrinsics.areEqual(this.constructor, constructorInfo.constructor) && Intrinsics.areEqual(this.parameters, constructorInfo.parameters);
    }

    public final Constructor<?> getConstructor() {
        return this.constructor;
    }

    public final List<Type> getParameters() {
        return this.parameters;
    }

    public int hashCode() {
        return (this.constructor.hashCode() * 31) + this.parameters.hashCode();
    }

    public String toString() {
        return "ConstructorInfo(constructor=" + this.constructor + ", parameters=" + this.parameters + ')';
    }
}

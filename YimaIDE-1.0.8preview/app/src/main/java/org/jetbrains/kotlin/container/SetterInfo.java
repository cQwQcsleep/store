package org.jetbrains.kotlin.container;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/container/SetterInfo;", Argument.Delimiters.none, "method", "Ljava/lang/reflect/Method;", "parameters", Argument.Delimiters.none, "Ljava/lang/reflect/Type;", "<init>", "(Ljava/lang/reflect/Method;Ljava/util/List;)V", "getMethod", "()Ljava/lang/reflect/Method;", "getParameters", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SetterInfo {
    private final Method method;
    private final List<Type> parameters;

    /* JADX WARN: Multi-variable type inference failed */
    public SetterInfo(Method method, List<? extends Type> list) {
        method.getClass();
        list.getClass();
        this.method = method;
        this.parameters = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SetterInfo copy$default(SetterInfo setterInfo, Method method, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            method = setterInfo.method;
        }
        if ((i & 2) != 0) {
            list = setterInfo.parameters;
        }
        return setterInfo.copy(method, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Method getMethod() {
        return this.method;
    }

    public final List<Type> component2() {
        return this.parameters;
    }

    public final SetterInfo copy(Method method, List<? extends Type> parameters) {
        method.getClass();
        parameters.getClass();
        return new SetterInfo(method, parameters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SetterInfo)) {
            return false;
        }
        SetterInfo setterInfo = (SetterInfo) other;
        return Intrinsics.areEqual(this.method, setterInfo.method) && Intrinsics.areEqual(this.parameters, setterInfo.parameters);
    }

    public final Method getMethod() {
        return this.method;
    }

    public final List<Type> getParameters() {
        return this.parameters;
    }

    public int hashCode() {
        return (this.method.hashCode() * 31) + this.parameters.hashCode();
    }

    public String toString() {
        return "SetterInfo(method=" + this.method + ", parameters=" + this.parameters + ')';
    }
}

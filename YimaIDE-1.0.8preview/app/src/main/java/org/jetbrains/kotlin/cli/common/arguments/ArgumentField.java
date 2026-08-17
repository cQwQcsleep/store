package org.jetbrains.kotlin.cli.common.arguments;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\bHÆ\u0003JG\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bHÆ\u0001J\u0014\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentField;", Argument.Delimiters.none, "getter", "Ljava/lang/reflect/Method;", "setter", "argument", "Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "enablesAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/Enables;", "disablesAnnotations", "Lorg/jetbrains/kotlin/cli/common/arguments/Disables;", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lorg/jetbrains/kotlin/cli/common/arguments/Argument;Ljava/util/List;Ljava/util/List;)V", "getGetter", "()Ljava/lang/reflect/Method;", "getSetter", "getArgument", "()Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "getEnablesAnnotations", "()Ljava/util/List;", "getDisablesAnnotations", "changesLanguageFeatures", Argument.Delimiters.none, "getChangesLanguageFeatures", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ArgumentField {
    private final Argument argument;
    private final List<Disables> disablesAnnotations;
    private final List<Enables> enablesAnnotations;
    private final Method getter;
    private final Method setter;

    /* JADX WARN: Multi-variable type inference failed */
    public ArgumentField(Method method, Method method2, Argument argument, List<? extends Enables> list, List<? extends Disables> list2) {
        method.getClass();
        method2.getClass();
        argument.getClass();
        list.getClass();
        list2.getClass();
        this.getter = method;
        this.setter = method2;
        this.argument = argument;
        this.enablesAnnotations = list;
        this.disablesAnnotations = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArgumentField copy$default(ArgumentField argumentField, Method method, Method method2, Argument argument, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            method = argumentField.getter;
        }
        if ((i & 2) != 0) {
            method2 = argumentField.setter;
        }
        if ((i & 4) != 0) {
            argument = argumentField.argument;
        }
        if ((i & 8) != 0) {
            list = argumentField.enablesAnnotations;
        }
        if ((i & 16) != 0) {
            list2 = argumentField.disablesAnnotations;
        }
        List list3 = list2;
        Argument argument2 = argument;
        return argumentField.copy(method, method2, argument2, list, list3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Method getGetter() {
        return this.getter;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Method getSetter() {
        return this.setter;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Argument getArgument() {
        return this.argument;
    }

    public final List<Enables> component4() {
        return this.enablesAnnotations;
    }

    public final List<Disables> component5() {
        return this.disablesAnnotations;
    }

    public final ArgumentField copy(Method getter, Method setter, Argument argument, List<? extends Enables> enablesAnnotations, List<? extends Disables> disablesAnnotations) {
        getter.getClass();
        setter.getClass();
        argument.getClass();
        enablesAnnotations.getClass();
        disablesAnnotations.getClass();
        return new ArgumentField(getter, setter, argument, enablesAnnotations, disablesAnnotations);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArgumentField)) {
            return false;
        }
        ArgumentField argumentField = (ArgumentField) other;
        return Intrinsics.areEqual(this.getter, argumentField.getter) && Intrinsics.areEqual(this.setter, argumentField.setter) && Intrinsics.areEqual(this.argument, argumentField.argument) && Intrinsics.areEqual(this.enablesAnnotations, argumentField.enablesAnnotations) && Intrinsics.areEqual(this.disablesAnnotations, argumentField.disablesAnnotations);
    }

    public final Argument getArgument() {
        return this.argument;
    }

    public final boolean getChangesLanguageFeatures() {
        return (this.enablesAnnotations.isEmpty() && this.disablesAnnotations.isEmpty()) ? false : true;
    }

    public final List<Disables> getDisablesAnnotations() {
        return this.disablesAnnotations;
    }

    public final List<Enables> getEnablesAnnotations() {
        return this.enablesAnnotations;
    }

    public final Method getGetter() {
        return this.getter;
    }

    public final Method getSetter() {
        return this.setter;
    }

    public int hashCode() {
        return (((((((this.getter.hashCode() * 31) + this.setter.hashCode()) * 31) + this.argument.hashCode()) * 31) + this.enablesAnnotations.hashCode()) * 31) + this.disablesAnnotations.hashCode();
    }

    public String toString() {
        return "ArgumentField(getter=" + this.getter + ", setter=" + this.setter + ", argument=" + this.argument + ", enablesAnnotations=" + this.enablesAnnotations + ", disablesAnnotations=" + this.disablesAnnotations + ')';
    }
}

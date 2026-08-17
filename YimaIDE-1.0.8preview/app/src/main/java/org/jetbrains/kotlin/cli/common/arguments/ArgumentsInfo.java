package org.jetbrains.kotlin.cli.common.arguments;

import java.lang.reflect.Constructor;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentsInfo;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0015\u001a\u00020\u0005J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007HÆ\u0003J/\u0010\u0018\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0004HÖ\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentsInfo;", Argument.Delimiters.none, "cliArgNameToArguments", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentField;", "defaultArgsConstructor", "Ljava/lang/reflect/Constructor;", "<init>", "(Ljava/util/Map;Ljava/lang/reflect/Constructor;)V", "getCliArgNameToArguments", "()Ljava/util/Map;", "getDefaultArgsConstructor", "()Ljava/lang/reflect/Constructor;", "defaultArgs", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "getDefaultArgs", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "defaultArgs$delegate", "Lkotlin/Lazy;", "getDefaultValue", "argumentField", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ArgumentsInfo {
    private final Map<String, ArgumentField> cliArgNameToArguments;

    /* JADX INFO: renamed from: defaultArgs$delegate, reason: from kotlin metadata */
    private final Lazy defaultArgs;
    private final Constructor<?> defaultArgsConstructor;

    public ArgumentsInfo(Map<String, ArgumentField> map, Constructor<?> constructor) {
        map.getClass();
        this.cliArgNameToArguments = map;
        this.defaultArgsConstructor = constructor;
        this.defaultArgs = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: ye0
            public final Object invoke() {
                return ArgumentsInfo.a(this.b);
            }
        });
    }

    public static CommonToolArguments a(ArgumentsInfo argumentsInfo) {
        Constructor<?> constructor = argumentsInfo.defaultArgsConstructor;
        Object objNewInstance = constructor != null ? constructor.newInstance(null) : null;
        CommonToolArguments commonToolArguments = objNewInstance instanceof CommonToolArguments ? (CommonToolArguments) objNewInstance : null;
        if (commonToolArguments != null) {
            return commonToolArguments;
        }
        k2d.a("Missing empty constructor");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArgumentsInfo copy$default(ArgumentsInfo argumentsInfo, Map map, Constructor constructor, int i, Object obj) {
        if ((i & 1) != 0) {
            map = argumentsInfo.cliArgNameToArguments;
        }
        if ((i & 2) != 0) {
            constructor = argumentsInfo.defaultArgsConstructor;
        }
        return argumentsInfo.copy(map, constructor);
    }

    private final CommonToolArguments getDefaultArgs() {
        return (CommonToolArguments) this.defaultArgs.getValue();
    }

    public final Map<String, ArgumentField> component1() {
        return this.cliArgNameToArguments;
    }

    public final Constructor<?> component2() {
        return this.defaultArgsConstructor;
    }

    public final ArgumentsInfo copy(Map<String, ArgumentField> cliArgNameToArguments, Constructor<?> defaultArgsConstructor) {
        cliArgNameToArguments.getClass();
        return new ArgumentsInfo(cliArgNameToArguments, defaultArgsConstructor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArgumentsInfo)) {
            return false;
        }
        ArgumentsInfo argumentsInfo = (ArgumentsInfo) other;
        return Intrinsics.areEqual(this.cliArgNameToArguments, argumentsInfo.cliArgNameToArguments) && Intrinsics.areEqual(this.defaultArgsConstructor, argumentsInfo.defaultArgsConstructor);
    }

    public final Map<String, ArgumentField> getCliArgNameToArguments() {
        return this.cliArgNameToArguments;
    }

    public final Constructor<?> getDefaultArgsConstructor() {
        return this.defaultArgsConstructor;
    }

    public final Object getDefaultValue(ArgumentField argumentField) {
        argumentField.getClass();
        return argumentField.getGetter().invoke(getDefaultArgs(), null);
    }

    public int hashCode() {
        int iHashCode = this.cliArgNameToArguments.hashCode() * 31;
        Constructor<?> constructor = this.defaultArgsConstructor;
        return iHashCode + (constructor == null ? 0 : constructor.hashCode());
    }

    public String toString() {
        return "ArgumentsInfo(cliArgNameToArguments=" + this.cliArgNameToArguments + ", defaultArgsConstructor=" + this.defaultArgsConstructor + ')';
    }
}

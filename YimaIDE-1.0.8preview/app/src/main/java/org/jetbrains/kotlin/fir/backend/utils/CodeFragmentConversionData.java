package org.jetbrains.kotlin.fir.backend.utils;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/utils/CodeFragmentConversionData;", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "methodName", "Lorg/jetbrains/kotlin/name/Name;", "injectedValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/utils/InjectedValue;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/name/Name;Ljava/util/List;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getMethodName", "()Lorg/jetbrains/kotlin/name/Name;", "getInjectedValues", "()Ljava/util/List;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CodeFragmentConversionData {
    private final ClassId classId;
    private final List<InjectedValue> injectedValues;
    private final Name methodName;

    public CodeFragmentConversionData(ClassId classId, Name name, List<InjectedValue> list) {
        classId.getClass();
        name.getClass();
        list.getClass();
        this.classId = classId;
        this.methodName = name;
        this.injectedValues = list;
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final List<InjectedValue> getInjectedValues() {
        return this.injectedValues;
    }

    public final Name getMethodName() {
        return this.methodName;
    }
}

package org.jetbrains.kotlin.constant;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\t\u001a\u0002H\n\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u000e\u001a\u0002H\u000bH&¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\b\u0010\u0017\u001a\u00020\u0016H\u0016R\u0016\u0010\u0003\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\f\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/constant/ConstantValue;", "T", Argument.Delimiters.none, "value", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/constant/AnnotationArgumentVisitor;", "data", "(Lorg/jetbrains/kotlin/constant/AnnotationArgumentVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "stringTemplateValue", "Lorg/jetbrains/kotlin/constant/AnnotationValue;", "Lorg/jetbrains/kotlin/constant/ArrayValue;", "Lorg/jetbrains/kotlin/constant/BooleanValue;", "Lorg/jetbrains/kotlin/constant/DoubleValue;", "Lorg/jetbrains/kotlin/constant/EnumValue;", "Lorg/jetbrains/kotlin/constant/ErrorValue;", "Lorg/jetbrains/kotlin/constant/FloatValue;", "Lorg/jetbrains/kotlin/constant/IntegerValueConstant;", "Lorg/jetbrains/kotlin/constant/KClassValue;", "Lorg/jetbrains/kotlin/constant/NullValue;", "Lorg/jetbrains/kotlin/constant/StringValue;", "Lorg/jetbrains/kotlin/constant/UnsignedValueConstant;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConstantValue<T> {
    private final T value;

    private ConstantValue(T t) {
        this.value = t;
    }

    public abstract <R, D> R accept(AnnotationArgumentVisitor<R, D> visitor, D data);

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        T value = getValue();
        ConstantValue constantValue = other instanceof ConstantValue ? (ConstantValue) other : null;
        return Intrinsics.areEqual(value, constantValue != null ? constantValue.getValue() : null);
    }

    public T getValue() {
        return this.value;
    }

    public int hashCode() {
        T value = getValue();
        if (value != null) {
            return value.hashCode();
        }
        return 0;
    }

    public String stringTemplateValue() {
        return String.valueOf(getValue());
    }

    public String toString() {
        return String.valueOf(getValue());
    }

    public /* synthetic */ ConstantValue(Object obj, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj);
    }
}

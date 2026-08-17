package org.jetbrains.kotlin.constant;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00122\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0013J\u001d\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00152\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00182\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020!2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020$2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020'2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020*2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010+J\u001d\u0010,\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020-2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002002\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u00101J\u001d\u00102\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002032\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u00104J\u001d\u00105\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002062\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u00107J\u001d\u00108\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002092\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010:J\u001d\u0010;\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020<2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010=J\u001d\u0010>\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020?2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010@¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/constant/AnnotationArgumentVisitor;", "R", "D", Argument.Delimiters.none, "<init>", "()V", "visitLongValue", "value", "Lorg/jetbrains/kotlin/constant/LongValue;", "data", "(Lorg/jetbrains/kotlin/constant/LongValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitIntValue", "Lorg/jetbrains/kotlin/constant/IntValue;", "(Lorg/jetbrains/kotlin/constant/IntValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorValue", "Lorg/jetbrains/kotlin/constant/ErrorValue;", "(Lorg/jetbrains/kotlin/constant/ErrorValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitShortValue", "Lorg/jetbrains/kotlin/constant/ShortValue;", "(Lorg/jetbrains/kotlin/constant/ShortValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitByteValue", "Lorg/jetbrains/kotlin/constant/ByteValue;", "(Lorg/jetbrains/kotlin/constant/ByteValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitDoubleValue", "Lorg/jetbrains/kotlin/constant/DoubleValue;", "(Lorg/jetbrains/kotlin/constant/DoubleValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitFloatValue", "Lorg/jetbrains/kotlin/constant/FloatValue;", "(Lorg/jetbrains/kotlin/constant/FloatValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanValue", "Lorg/jetbrains/kotlin/constant/BooleanValue;", "(Lorg/jetbrains/kotlin/constant/BooleanValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitCharValue", "Lorg/jetbrains/kotlin/constant/CharValue;", "(Lorg/jetbrains/kotlin/constant/CharValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitStringValue", "Lorg/jetbrains/kotlin/constant/StringValue;", "(Lorg/jetbrains/kotlin/constant/StringValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitNullValue", "Lorg/jetbrains/kotlin/constant/NullValue;", "(Lorg/jetbrains/kotlin/constant/NullValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnumValue", "Lorg/jetbrains/kotlin/constant/EnumValue;", "(Lorg/jetbrains/kotlin/constant/EnumValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitArrayValue", "Lorg/jetbrains/kotlin/constant/ArrayValue;", "(Lorg/jetbrains/kotlin/constant/ArrayValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotationValue", "Lorg/jetbrains/kotlin/constant/AnnotationValue;", "(Lorg/jetbrains/kotlin/constant/AnnotationValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitKClassValue", "Lorg/jetbrains/kotlin/constant/KClassValue;", "(Lorg/jetbrains/kotlin/constant/KClassValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitUByteValue", "Lorg/jetbrains/kotlin/constant/UByteValue;", "(Lorg/jetbrains/kotlin/constant/UByteValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitUShortValue", "Lorg/jetbrains/kotlin/constant/UShortValue;", "(Lorg/jetbrains/kotlin/constant/UShortValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitUIntValue", "Lorg/jetbrains/kotlin/constant/UIntValue;", "(Lorg/jetbrains/kotlin/constant/UIntValue;Ljava/lang/Object;)Ljava/lang/Object;", "visitULongValue", "Lorg/jetbrains/kotlin/constant/ULongValue;", "(Lorg/jetbrains/kotlin/constant/ULongValue;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AnnotationArgumentVisitor<R, D> {
    public abstract R visitAnnotationValue(AnnotationValue value, D data);

    public abstract R visitArrayValue(ArrayValue value, D data);

    public abstract R visitBooleanValue(BooleanValue value, D data);

    public abstract R visitByteValue(ByteValue value, D data);

    public abstract R visitCharValue(CharValue value, D data);

    public abstract R visitDoubleValue(DoubleValue value, D data);

    public abstract R visitEnumValue(EnumValue value, D data);

    public abstract R visitErrorValue(ErrorValue value, D data);

    public abstract R visitFloatValue(FloatValue value, D data);

    public abstract R visitIntValue(IntValue value, D data);

    public abstract R visitKClassValue(KClassValue value, D data);

    public abstract R visitLongValue(LongValue value, D data);

    public abstract R visitNullValue(NullValue value, D data);

    public abstract R visitShortValue(ShortValue value, D data);

    public abstract R visitStringValue(StringValue value, D data);

    public abstract R visitUByteValue(UByteValue value, D data);

    public abstract R visitUIntValue(UIntValue value, D data);

    public abstract R visitULongValue(ULongValue value, D data);

    public abstract R visitUShortValue(UShortValue value, D data);
}

package org.jetbrains.kotlin.constant;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\n2\u0006\u0010\u000b\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\b\u0010\u000f\u001a\u00020\u000eH\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/constant/ULongValue;", "Lorg/jetbrains/kotlin/constant/UnsignedValueConstant;", Argument.Delimiters.none, "longValue", "<init>", "(J)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/constant/AnnotationArgumentVisitor;", "data", "(Lorg/jetbrains/kotlin/constant/AnnotationArgumentVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "toString", Argument.Delimiters.none, "stringTemplateValue", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ULongValue extends UnsignedValueConstant<Long> {
    public ULongValue(long j) {
        super(Long.valueOf(j));
    }

    @Override // org.jetbrains.kotlin.constant.ConstantValue
    public <R, D> R accept(AnnotationArgumentVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return visitor.visitULongValue(this, data);
    }

    @Override // org.jetbrains.kotlin.constant.ConstantValue
    public String stringTemplateValue() {
        if (getValue().longValue() >= 0) {
            return String.valueOf(getValue().longValue());
        }
        long jLongValue = (getValue().longValue() >>> 1) / 5;
        long jLongValue2 = getValue().longValue() - (10 * jLongValue);
        StringBuilder sb = new StringBuilder();
        sb.append(jLongValue);
        sb.append(jLongValue2);
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.constant.ConstantValue
    public String toString() {
        return getValue().longValue() + ".toULong()";
    }
}

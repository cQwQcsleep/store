package org.jetbrains.kotlin.contracts.model.structure;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\u0006\u0010\u0019\u001a\u00020\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "Lorg/jetbrains/kotlin/contracts/model/structure/AbstractESValue;", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;Lorg/jetbrains/kotlin/contracts/model/structure/ESType;)V", "getConstantReference", "()Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "getType", "()Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "accept", "T", "visitor", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "(Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;)Ljava/lang/Object;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "isNullConstant", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESConstant extends AbstractESValue {
    private final ConstantReference constantReference;
    private final ESType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESConstant(ConstantReference constantReference, ESType eSType) {
        super(eSType);
        constantReference.getClass();
        eSType.getClass();
        this.constantReference = constantReference;
        this.type = eSType;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpression
    public <T> T accept(ESExpressionVisitor<? extends T> visitor) {
        visitor.getClass();
        return visitor.visitConstant(this);
    }

    public boolean equals(Object other) {
        return (other instanceof ESConstant) && Intrinsics.areEqual(this.constantReference, ((ESConstant) other).constantReference);
    }

    public final ConstantReference getConstantReference() {
        return this.constantReference;
    }

    @Override // org.jetbrains.kotlin.contracts.model.structure.AbstractESValue, org.jetbrains.kotlin.contracts.model.Computation
    public ESType getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hashCode(this.constantReference);
    }

    public final boolean isNullConstant() {
        ConstantReference constantReference = this.constantReference;
        ConstantReference.Companion companion = ConstantReference.INSTANCE;
        return Intrinsics.areEqual(constantReference, companion.getNULL()) || Intrinsics.areEqual(this.constantReference, companion.getNOT_NULL());
    }

    public String toString() {
        return this.constantReference.getName();
    }
}

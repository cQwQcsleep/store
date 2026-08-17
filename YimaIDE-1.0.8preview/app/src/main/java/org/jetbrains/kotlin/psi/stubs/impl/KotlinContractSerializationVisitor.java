package org.jetbrains.kotlin.psi.stubs.impl;

import com.intellij.psi.stubs.StubOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBinaryLogicExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalReturnsDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtIsNullPredicate;
import org.jetbrains.kotlin.contracts.description.KtLogicalNot;
import org.jetbrains.kotlin.contracts.description.KtReturnsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtReturnsResultOfDeclaration;
import org.jetbrains.kotlin.contracts.description.KtValueParameterReference;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.psi.stubs.elements.TypeBeanSerializationKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ(\u0010\u000b\u001a\u00020\u00022\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u000f\u001a\u00020\u00022\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0011\u001a\u00020\u00022\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0014\u001a\u00020\u00022\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00162\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0017\u001a\u00020\u00022\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u001a\u001a\u00020\u00022\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001c2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u001d\u001a\u00020\u00022\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010 \u001a\u00020\u00022\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\"2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010#\u001a\u00020\u00022\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030%2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010&\u001a\u00020\u00022\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030(2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010)\u001a\u00020\u00022\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030+2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010,\u001a\u00020\u00022\u0014\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030.2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010/\u001a\u00020\u00022\u0014\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0003012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u00062"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinContractSerializationVisitor;", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;", "dataStream", "Lcom/intellij/psi/stubs/StubOutputStream;", "<init>", "(Lcom/intellij/psi/stubs/StubOutputStream;)V", "getDataStream", "()Lcom/intellij/psi/stubs/StubOutputStream;", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalEffectDeclaration;", "data", "visitConditionalReturnsDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;", "visitHoldsInEffectDeclaration", "holdsInEffect", "Lorg/jetbrains/kotlin/contracts/description/KtHoldsInEffectDeclaration;", "visitReturnsEffectDeclaration", "returnsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsEffectDeclaration;", "visitCallsEffectDeclaration", "callsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtCallsEffectDeclaration;", "visitReturnsResultOfEffectDeclaration", "returnsResultOfEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsResultOfDeclaration;", "visitLogicalBinaryOperationContractExpression", "binaryLogicExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/KtLogicalNot;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsNullPredicate;", "visitConstantDescriptor", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "visitValueParameterReference", "valueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "visitBooleanValueParameterReference", "booleanValueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanValueParameterReference;", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinContractSerializationVisitor extends KtContractDescriptionVisitor {
    private final StubOutputStream dataStream;

    public KotlinContractSerializationVisitor(StubOutputStream stubOutputStream) {
        stubOutputStream.getClass();
        this.dataStream = stubOutputStream;
    }

    public final StubOutputStream getDataStream() {
        return this.dataStream;
    }

    public void visitBooleanValueParameterReference(KtBooleanValueParameterReference booleanValueParameterReference, Void data) {
        booleanValueParameterReference.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.BOOLEAN_PARAMETER_REFERENCE.ordinal());
        this.dataStream.writeVarInt(booleanValueParameterReference.getParameterIndex());
    }

    public void visitCallsEffectDeclaration(KtCallsEffectDeclaration callsEffect, Void data) {
        callsEffect.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.CALLS.ordinal());
        this.dataStream.writeVarInt(callsEffect.getValueParameterReference().getParameterIndex());
        this.dataStream.writeVarInt(callsEffect.getKind().ordinal());
    }

    public void visitConditionalEffectDeclaration(KtConditionalEffectDeclaration conditionalEffect, Void data) {
        conditionalEffect.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.CONDITIONAL.ordinal());
        conditionalEffect.getEffect().accept(this, data);
        conditionalEffect.getCondition().accept(this, data);
    }

    public void visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration conditionalEffect, Void data) {
        conditionalEffect.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.CONDITIONAL_RETURNS.ordinal());
        conditionalEffect.getArgumentsCondition().accept(this, data);
        conditionalEffect.getReturnsEffect().accept(this, data);
    }

    public void visitConstantDescriptor(KtConstantReference constantReference, Void data) {
        constantReference.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.CONSTANT.ordinal());
        this.dataStream.writeName(constantReference.getName());
    }

    public void visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration holdsInEffect, Void data) {
        holdsInEffect.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.HOLDS_IN.ordinal());
        holdsInEffect.getArgumentsCondition().accept(this, data);
        this.dataStream.writeVarInt(holdsInEffect.getValueParameterReference().getParameterIndex());
    }

    public void visitIsInstancePredicate(KtIsInstancePredicate isInstancePredicate, Void data) throws IOException {
        isInstancePredicate.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.IS_INSTANCE.ordinal());
        this.dataStream.writeVarInt(isInstancePredicate.getArg().getParameterIndex());
        TypeBeanSerializationKt.serializeTypeBean(this.dataStream, (KotlinTypeBean) isInstancePredicate.getType());
        this.dataStream.writeBoolean(isInstancePredicate.getIsNegated());
    }

    public void visitIsNullPredicate(KtIsNullPredicate isNullPredicate, Void data) throws IOException {
        isNullPredicate.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.IS_NULL.ordinal());
        this.dataStream.writeVarInt(isNullPredicate.getArg().getParameterIndex());
        this.dataStream.writeBoolean(isNullPredicate.getIsNegated());
    }

    public void visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression binaryLogicExpression, Void data) throws IOException {
        binaryLogicExpression.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.BOOLEAN_LOGIC.ordinal());
        this.dataStream.writeBoolean(binaryLogicExpression.getKind() == LogicOperationKind.AND);
        binaryLogicExpression.getLeft().accept(this, data);
        binaryLogicExpression.getRight().accept(this, data);
    }

    public void visitLogicalNot(KtLogicalNot logicalNot, Void data) {
        logicalNot.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.NOT.ordinal());
        logicalNot.getArg().accept(this, data);
    }

    public void visitReturnsEffectDeclaration(KtReturnsEffectDeclaration returnsEffect, Void data) {
        returnsEffect.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.RETURNS.ordinal());
        this.dataStream.writeName(returnsEffect.getValue().getName());
    }

    public void visitReturnsResultOfEffectDeclaration(KtReturnsResultOfDeclaration returnsResultOfEffect, Void data) {
        returnsResultOfEffect.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.RETURNS_RESULT_OF.ordinal());
        this.dataStream.writeVarInt(returnsResultOfEffect.getValueParameterReference().getParameterIndex());
    }

    public void visitValueParameterReference(KtValueParameterReference valueParameterReference, Void data) {
        valueParameterReference.getClass();
        this.dataStream.writeVarInt(KotlinContractEffectType.PARAMETER_REFERENCE.ordinal());
        this.dataStream.writeVarInt(valueParameterReference.getParameterIndex());
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitLogicalNot(KtLogicalNot ktLogicalNot, Object obj) {
        visitLogicalNot(ktLogicalNot, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitBooleanValueParameterReference(KtBooleanValueParameterReference ktBooleanValueParameterReference, Object obj) {
        visitBooleanValueParameterReference(ktBooleanValueParameterReference, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitConstantDescriptor(KtConstantReference ktConstantReference, Object obj) {
        visitConstantDescriptor(ktConstantReference, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitValueParameterReference(KtValueParameterReference ktValueParameterReference, Object obj) {
        visitValueParameterReference(ktValueParameterReference, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitReturnsEffectDeclaration(KtReturnsEffectDeclaration ktReturnsEffectDeclaration, Object obj) {
        visitReturnsEffectDeclaration(ktReturnsEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitReturnsResultOfEffectDeclaration(KtReturnsResultOfDeclaration ktReturnsResultOfDeclaration, Object obj) {
        visitReturnsResultOfEffectDeclaration(ktReturnsResultOfDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitConditionalEffectDeclaration(KtConditionalEffectDeclaration ktConditionalEffectDeclaration, Object obj) {
        visitConditionalEffectDeclaration(ktConditionalEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration, Object obj) {
        visitConditionalReturnsDeclaration(ktConditionalReturnsDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration ktHoldsInEffectDeclaration, Object obj) {
        visitHoldsInEffectDeclaration(ktHoldsInEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitIsNullPredicate(KtIsNullPredicate ktIsNullPredicate, Object obj) throws IOException {
        visitIsNullPredicate(ktIsNullPredicate, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitCallsEffectDeclaration(KtCallsEffectDeclaration ktCallsEffectDeclaration, Object obj) {
        visitCallsEffectDeclaration(ktCallsEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression ktBinaryLogicExpression, Object obj) throws IOException {
        visitLogicalBinaryOperationContractExpression(ktBinaryLogicExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitIsInstancePredicate(KtIsInstancePredicate ktIsInstancePredicate, Object obj) throws IOException {
        visitIsInstancePredicate(ktIsInstancePredicate, (Void) obj);
        return Unit.INSTANCE;
    }
}

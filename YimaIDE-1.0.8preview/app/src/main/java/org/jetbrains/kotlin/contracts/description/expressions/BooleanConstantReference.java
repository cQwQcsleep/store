package org.jetbrains.kotlin.contracts.description.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.BooleanExpression;
import org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J5\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u0002H\tH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Companion", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BooleanConstantReference extends ConstantReference implements BooleanExpression {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final BooleanConstantReference TRUE = new BooleanConstantReference("TRUE");
    private static final BooleanConstantReference FALSE = new BooleanConstantReference("FALSE");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BooleanConstantReference(String str) {
        super(str);
        str.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.expressions.ConstantReference, org.jetbrains.kotlin.contracts.description.expressions.ContractDescriptionValue, org.jetbrains.kotlin.contracts.description.ContractDescriptionElement
    public <R, D> R accept(ContractDescriptionVisitor<? extends R, ? super D> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitBooleanConstantDescriptor(this, data);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference$Companion;", Argument.Delimiters.none, "<init>", "()V", "TRUE", "Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;", "getTRUE", "()Lorg/jetbrains/kotlin/contracts/description/expressions/BooleanConstantReference;", "FALSE", "getFALSE", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BooleanConstantReference getFALSE() {
            return BooleanConstantReference.FALSE;
        }

        public final BooleanConstantReference getTRUE() {
            return BooleanConstantReference.TRUE;
        }

        private Companion() {
        }
    }
}

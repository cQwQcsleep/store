package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtErroneousContractElement;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeContractDescriptionError;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0012\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0012\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "()V", "IllegalElement", "UnresolvedCall", "NoReceiver", "NoArgument", "NotAConstant", "IllegalConst", "NotAParameterReference", "IllegalParameter", "UnresolvedThis", "IllegalThis", "UnresolvedInvocationKind", "NotABooleanExpression", "NotContractDsl", "IllegalEqualityOperator", "NotSelfTypeParameter", "NotReifiedTypeParameter", "ErasedIsCheck", "RequiresLanguageFeature", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$ErasedIsCheck;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalConst;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalElement;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalEqualityOperator;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalParameter;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalThis;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NoArgument;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NoReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotABooleanExpression;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotAConstant;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotAParameterReference;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotContractDsl;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotReifiedTypeParameter;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotSelfTypeParameter;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$RequiresLanguageFeature;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$UnresolvedCall;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$UnresolvedInvocationKind;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$UnresolvedThis;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeContractDescriptionError implements ConeDiagnostic {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$ErasedIsCheck;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "<init>", "()V", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ErasedIsCheck extends ConeContractDescriptionError {
        public static final ErasedIsCheck INSTANCE = new ErasedIsCheck();

        private ErasedIsCheck() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "instance check for erased type";
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalConst;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "element", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "onlyNullAllowed", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;Z)V", "getElement", "()Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "getOnlyNullAllowed", "()Z", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IllegalConst extends ConeContractDescriptionError {
        private final FirLiteralExpression element;
        private final boolean onlyNullAllowed;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IllegalConst(FirLiteralExpression firLiteralExpression, boolean z) {
            super(null);
            firLiteralExpression.getClass();
            this.element = firLiteralExpression;
            this.onlyNullAllowed = z;
        }

        public final FirLiteralExpression getElement() {
            return this.element;
        }

        public final boolean getOnlyNullAllowed() {
            return this.onlyNullAllowed;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            StringBuilder sb = new StringBuilder();
            sb.append(UtilsKt.render(this.element));
            sb.append("is not a null");
            if (!this.onlyNullAllowed) {
                sb.append(", true or false");
            }
            return sb.toString();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalElement;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "getElement", "()Lorg/jetbrains/kotlin/fir/FirElement;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IllegalElement extends ConeContractDescriptionError {
        private final FirElement element;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IllegalElement(FirElement firElement) {
            super(null);
            firElement.getClass();
            this.element = firElement;
        }

        public final FirElement getElement() {
            return this.element;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "illegal element in contract description";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalEqualityOperator;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirOperation;)V", "getOperation", "()Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IllegalEqualityOperator extends ConeContractDescriptionError {
        private final FirOperation operation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IllegalEqualityOperator(FirOperation firOperation) {
            super(null);
            firOperation.getClass();
            this.operation = firOperation;
        }

        public final FirOperation getOperation() {
            return this.operation;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "'" + this.operation + "' operator call is illegal in contract descriptions";
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalParameter;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "reason", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/lang/String;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IllegalParameter extends ConeContractDescriptionError {
        private final String reason;
        private final FirCallableSymbol<?> symbol;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IllegalParameter(FirCallableSymbol<?> firCallableSymbol, String str) {
            super(null);
            firCallableSymbol.getClass();
            str.getClass();
            this.symbol = firCallableSymbol;
            this.reason = str;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return this.reason;
        }

        public final FirCallableSymbol<?> getSymbol() {
            return this.symbol;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$IllegalThis;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IllegalThis extends ConeContractDescriptionError {
        private final FirThisReceiverExpression expression;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IllegalThis(FirThisReceiverExpression firThisReceiverExpression) {
            super(null);
            firThisReceiverExpression.getClass();
            this.expression = firThisReceiverExpression;
        }

        public final FirThisReceiverExpression getExpression() {
            return this.expression;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "'this' can only be a qualified reference to the extension receiver of contract owner.";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NoArgument;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NoArgument extends ConeContractDescriptionError {
        private final Name name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoArgument(Name name) {
            super(null);
            name.getClass();
            this.name = name;
        }

        public final Name getName() {
            return this.name;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "no argument for call '" + this.name + "' found";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NoReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NoReceiver extends ConeContractDescriptionError {
        private final Name name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoReceiver(Name name) {
            super(null);
            name.getClass();
            this.name = name;
        }

        public final Name getName() {
            return this.name;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "no receiver for call '" + this.name + "' found";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotABooleanExpression;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "element", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractDescriptionElement;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;)V", "getElement", "()Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotABooleanExpression extends ConeContractDescriptionError {
        private final KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> element;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotABooleanExpression(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement) {
            super(null);
            ktContractDescriptionElement.getClass();
            this.element = ktContractDescriptionElement;
        }

        public final KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> getElement() {
            return this.element;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "'" + this.element + "' is not a boolean expression";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotAConstant;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "element", Argument.Delimiters.none, "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getElement", "()Ljava/lang/Object;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotAConstant extends ConeContractDescriptionError {
        private final Object element;

        public NotAConstant(Object obj) {
            super(null);
            this.element = obj;
        }

        public final Object getElement() {
            return this.element;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "'" + this.element + "' is not a constant reference";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotAParameterReference;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "element", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;)V", "getElement", "()Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotAParameterReference extends ConeContractDescriptionError {
        private final KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> element;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotAParameterReference(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement) {
            super(null);
            ktContractDescriptionElement.getClass();
            this.element = ktContractDescriptionElement;
        }

        public final KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> getElement() {
            return this.element;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement = this.element;
            return ktContractDescriptionElement instanceof KtErroneousContractElement ? ((ConeDiagnostic) ((KtErroneousContractElement) ktContractDescriptionElement).getDiagnostic()).getReason() : "element is not a parameter or receiver reference";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotContractDsl;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;)V", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotContractDsl extends ConeContractDescriptionError {
        private final CallableId callableId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotContractDsl(CallableId callableId) {
            super(null);
            callableId.getClass();
            this.callableId = callableId;
        }

        public final CallableId getCallableId() {
            return this.callableId;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "'" + this.callableId + "' is not part of the contracts DSL";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotReifiedTypeParameter;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotReifiedTypeParameter extends ConeContractDescriptionError {
        private final FirTypeParameterSymbol symbol;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotReifiedTypeParameter(FirTypeParameterSymbol firTypeParameterSymbol) {
            super(null);
            firTypeParameterSymbol.getClass();
            this.symbol = firTypeParameterSymbol;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "type parameter '" + this.symbol.getName() + "' is not reified";
        }

        public final FirTypeParameterSymbol getSymbol() {
            return this.symbol;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$NotSelfTypeParameter;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotSelfTypeParameter extends ConeContractDescriptionError {
        private final FirTypeParameterSymbol symbol;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotSelfTypeParameter(FirTypeParameterSymbol firTypeParameterSymbol) {
            super(null);
            firTypeParameterSymbol.getClass();
            this.symbol = firTypeParameterSymbol;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "type parameter '" + this.symbol.getName() + "' does not belong to contract owner";
        }

        public final FirTypeParameterSymbol getSymbol() {
            return this.symbol;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000b\u001a\u00020\u0003H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$RequiresLanguageFeature;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "featureName", Argument.Delimiters.none, "featureNames", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;[Ljava/lang/String;)V", Argument.Delimiters.none, "getFeatureNames", "()Ljava/util/List;", "renderFeatures", "reason", "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RequiresLanguageFeature extends ConeContractDescriptionError {
        private final List<String> featureNames;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RequiresLanguageFeature(String str, String... strArr) {
            super(null);
            str.getClass();
            strArr.getClass();
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            listCreateListBuilder.add(str);
            CollectionsKt.addAll(listCreateListBuilder, strArr);
            this.featureNames = CollectionsKt.build(listCreateListBuilder);
        }

        public static CharSequence a(String str) {
            str.getClass();
            return "'" + str + '\'';
        }

        private final String renderFeatures() {
            int size = this.featureNames.size();
            List<String> list = this.featureNames;
            if (size != 1) {
                return "features " + CollectionsKt.joinToString$default(list, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: np2
                    public final Object invoke(Object obj) {
                        return ConeContractDescriptionError.RequiresLanguageFeature.a((String) obj);
                    }
                }, 30, (Object) null);
            }
            return "feature '" + list.get(0) + '\'';
        }

        public final List<String> getFeatureNames() {
            return this.featureNames;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "requires language " + renderFeatures() + " to be enabled";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$UnresolvedCall;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UnresolvedCall extends ConeContractDescriptionError {
        private final Name name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnresolvedCall(Name name) {
            super(null);
            name.getClass();
            this.name = name;
        }

        public final Name getName() {
            return this.name;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "unresolved call '" + this.name + "' in contract description";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$UnresolvedInvocationKind;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "getElement", "()Lorg/jetbrains/kotlin/fir/FirElement;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UnresolvedInvocationKind extends ConeContractDescriptionError {
        private final FirElement element;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnresolvedInvocationKind(FirElement firElement) {
            super(null);
            firElement.getClass();
            this.element = firElement;
        }

        public final FirElement getElement() {
            return this.element;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "'" + UtilsKt.render(this.element) + "' is not a valid invocation kind";
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError$UnresolvedThis;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeContractDescriptionError;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UnresolvedThis extends ConeContractDescriptionError {
        private final FirThisReceiverExpression expression;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnresolvedThis(FirThisReceiverExpression firThisReceiverExpression) {
            super(null);
            firThisReceiverExpression.getClass();
            this.expression = firThisReceiverExpression;
        }

        public final FirThisReceiverExpression getExpression() {
            return this.expression;
        }

        @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
        public String getReason() {
            return "can't resolve 'this' reference";
        }
    }

    public /* synthetic */ ConeContractDescriptionError(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ConeContractDescriptionError() {
    }
}

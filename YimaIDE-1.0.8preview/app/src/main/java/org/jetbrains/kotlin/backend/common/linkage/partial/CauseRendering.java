package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0002\u0006\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering;", "", "Standalone", "UsedFromSomewhere", "UsedFromDeclaration", "UsedFromExpression", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$Standalone;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromSomewhere;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
interface CauseRendering {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$Standalone;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Standalone implements CauseRendering {
        public static final Standalone INSTANCE = new Standalone();

        private Standalone() {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromDeclaration;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromSomewhere;", "objectText", "", "objectSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)V", "getObjectText", "()Ljava/lang/String;", "getObjectSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UsedFromDeclaration implements UsedFromSomewhere {
        private final IrSymbol objectSymbol;
        private final String objectText;

        public UsedFromDeclaration(String str, IrSymbol irSymbol) {
            str.getClass();
            irSymbol.getClass();
            this.objectText = str;
            this.objectSymbol = irSymbol;
        }

        @Override // org.jetbrains.kotlin.backend.common.linkage.partial.CauseRendering.UsedFromSomewhere
        public IrSymbol getObjectSymbol() {
            return this.objectSymbol;
        }

        @Override // org.jetbrains.kotlin.backend.common.linkage.partial.CauseRendering.UsedFromSomewhere
        public String getObjectText() {
            return this.objectText;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0001\u0002\n\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromSomewhere;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering;", "objectText", "", "getObjectText", "()Ljava/lang/String;", "objectSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "getObjectSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromDeclaration;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromExpression;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface UsedFromSomewhere extends CauseRendering {
        IrSymbol getObjectSymbol();

        String getObjectText();
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromExpression;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/CauseRendering$UsedFromSomewhere;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "objectText", "", "getObjectText", "()Ljava/lang/String;", "objectSymbol", "", "getObjectSymbol", "()Ljava/lang/Void;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UsedFromExpression implements UsedFromSomewhere {
        private static final Void objectSymbol = null;
        public static final UsedFromExpression INSTANCE = new UsedFromExpression();
        private static final String objectText = "Expression";

        private UsedFromExpression() {
        }

        @Override // org.jetbrains.kotlin.backend.common.linkage.partial.CauseRendering.UsedFromSomewhere
        public String getObjectText() {
            return objectText;
        }

        @Override // org.jetbrains.kotlin.backend.common.linkage.partial.CauseRendering.UsedFromSomewhere
        public Void getObjectSymbol() {
            return objectSymbol;
        }
    }
}

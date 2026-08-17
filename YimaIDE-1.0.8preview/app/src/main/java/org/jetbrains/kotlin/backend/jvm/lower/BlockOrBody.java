package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBody;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", "getElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "Body", "Block", "Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody$Block;", "Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody$Body;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class BlockOrBody {
    public /* synthetic */ BlockOrBody(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract IrElement getElement();

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody$Block;", "Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody;", "block", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrBlock;)V", "getBlock", "()Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "getElement", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Block extends BlockOrBody {
        private final IrBlock block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Block(IrBlock irBlock) {
            super(null);
            irBlock.getClass();
            this.block = irBlock;
        }

        public static /* synthetic */ Block copy$default(Block block, IrBlock irBlock, int i, Object obj) {
            if ((i & 1) != 0) {
                irBlock = block.block;
            }
            return block.copy(irBlock);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrBlock getBlock() {
            return this.block;
        }

        public final Block copy(IrBlock block) {
            block.getClass();
            return new Block(block);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Block) && Intrinsics.areEqual(this.block, ((Block) other).block);
        }

        public final IrBlock getBlock() {
            return this.block;
        }

        public int hashCode() {
            return this.block.hashCode();
        }

        public String toString() {
            return "Block(block=" + this.block + Util.C_PARAM_END;
        }

        @Override // org.jetbrains.kotlin.backend.jvm.lower.BlockOrBody
        public IrBlock getElement() {
            return this.block;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody$Body;", "Lorg/jetbrains/kotlin/backend/jvm/lower/BlockOrBody;", "body", "Lorg/jetbrains/kotlin/ir/expressions/IrBody;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrBody;)V", "getBody", "()Lorg/jetbrains/kotlin/ir/expressions/IrBody;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "getElement", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Body extends BlockOrBody {
        private final IrBody body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Body(IrBody irBody) {
            super(null);
            irBody.getClass();
            this.body = irBody;
        }

        public static /* synthetic */ Body copy$default(Body body, IrBody irBody, int i, Object obj) {
            if ((i & 1) != 0) {
                irBody = body.body;
            }
            return body.copy(irBody);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrBody getBody() {
            return this.body;
        }

        public final Body copy(IrBody body) {
            body.getClass();
            return new Body(body);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Body) && Intrinsics.areEqual(this.body, ((Body) other).body);
        }

        public final IrBody getBody() {
            return this.body;
        }

        public int hashCode() {
            return this.body.hashCode();
        }

        public String toString() {
            return "Body(body=" + this.body + Util.C_PARAM_END;
        }

        @Override // org.jetbrains.kotlin.backend.jvm.lower.BlockOrBody
        public IrBody getElement() {
            return this.body;
        }
    }

    private BlockOrBody() {
    }
}

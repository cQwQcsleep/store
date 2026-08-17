package org.jetbrains.kotlin.ir;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.ir.visitors.IrTransformer;
import org.jetbrains.kotlin.ir.visitors.IrVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J5\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00142\u0006\u0010\u0015\u001a\u0002H\u0012H&¢\u0006\u0002\u0010\u0016J)\u0010\u0017\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00192\u0006\u0010\u0015\u001a\u0002H\u0012H&¢\u0006\u0002\u0010\u001aJ/\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u0002H\u00120\u00142\u0006\u0010\u0015\u001a\u0002H\u0012H&¢\u0006\u0002\u0010\u001dJ)\u0010\u001e\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00192\u0006\u0010\u0015\u001a\u0002H\u0012H&¢\u0006\u0002\u0010\u001fR\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\u0000X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006 À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/IrElement;", "", "startOffset", "", "getStartOffset", "()I", "setStartOffset", "(I)V", "endOffset", "getEndOffset", "setEndOffset", "attributeOwnerId", "getAttributeOwnerId", "()Lorg/jetbrains/kotlin/ir/IrElement;", "setAttributeOwnerId", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitor;", "data", "(Lorg/jetbrains/kotlin/ir/visitors/IrVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "transformer", "Lorg/jetbrains/kotlin/ir/visitors/IrTransformer;", "(Lorg/jetbrains/kotlin/ir/visitors/IrTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/ir/IrElement;", "acceptChildren", "", "(Lorg/jetbrains/kotlin/ir/visitors/IrVisitor;Ljava/lang/Object;)V", "transformChildren", "(Lorg/jetbrains/kotlin/ir/visitors/IrTransformer;Ljava/lang/Object;)V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrElement {
    <R, D> R accept(IrVisitor<? extends R, ? super D> visitor, D data);

    <D> void acceptChildren(IrVisitor<Unit, ? super D> visitor, D data);

    IrElement getAttributeOwnerId();

    int getEndOffset();

    int getStartOffset();

    void setAttributeOwnerId(IrElement irElement);

    void setEndOffset(int i);

    void setStartOffset(int i);

    <D> IrElement transform(IrTransformer<? super D> transformer, D data);

    <D> void transformChildren(IrTransformer<? super D> transformer, D data);
}

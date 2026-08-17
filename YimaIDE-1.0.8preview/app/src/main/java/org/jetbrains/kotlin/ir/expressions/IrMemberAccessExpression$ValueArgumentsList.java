package org.jetbrains.kotlin.ir.expressions;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002`\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002J\u001d\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0086\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrMemberAccessExpression$ValueArgumentsList;", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "Lkotlin/collections/ArrayList;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrMemberAccessExpression;)V", "get", "parameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "set", "value", "checkIndexingByParameter", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrMemberAccessExpression$ValueArgumentsList extends ArrayList<IrExpression> {
    final /* synthetic */ IrMemberAccessExpression<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrMemberAccessExpression$ValueArgumentsList(IrMemberAccessExpression irMemberAccessExpression) {
        super(0);
        this.this$0 = irMemberAccessExpression;
    }

    private final void checkIndexingByParameter(IrValueParameter parameter) {
        boolean zAreEqual = Intrinsics.areEqual(parameter.getParent(), this.this$0.getSymbol().getOwner());
        IrMemberAccessExpression<S> irMemberAccessExpression = this.this$0;
        if (zAreEqual) {
            return;
        }
        rza.a("Attempting to access argument corresponding to a parameter of different function.\nThis IR element references ", RenderIrElementKt.render$default(irMemberAccessExpression.getSymbol().getOwner(), (DumpIrTreeOptions) null, 1, (Object) null), ", while asking about a parameter of ", RenderIrElementKt.render$default(parameter.getParent(), (DumpIrTreeOptions) null, 1, (Object) null));
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof IrExpression) {
            return contains((IrExpression) obj);
        }
        return false;
    }

    public final IrExpression get(IrValueParameter parameter) {
        parameter.getClass();
        checkIndexingByParameter(parameter);
        return get(parameter.getIndexInParameters());
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj == null ? true : obj instanceof IrExpression) {
            return indexOf((IrExpression) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj == null ? true : obj instanceof IrExpression) {
            return lastIndexOf((IrExpression) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj == null ? true : obj instanceof IrExpression) {
            return remove((IrExpression) obj);
        }
        return false;
    }

    public /* bridge */ IrExpression removeAt(int i) {
        return (IrExpression) super.remove(i);
    }

    public final IrExpression set(IrValueParameter parameter, IrExpression value) {
        parameter.getClass();
        checkIndexingByParameter(parameter);
        return set(parameter.getIndexInParameters(), value);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    public /* bridge */ boolean contains(IrExpression irExpression) {
        return super.contains((Object) irExpression);
    }

    public /* bridge */ int indexOf(IrExpression irExpression) {
        return super.indexOf((Object) irExpression);
    }

    public /* bridge */ int lastIndexOf(IrExpression irExpression) {
        return super.lastIndexOf((Object) irExpression);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ IrExpression remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(IrExpression irExpression) {
        return super.remove((Object) irExpression);
    }
}

package org.jetbrains.kotlin.ir.builders;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.BuildersKt;
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\f\u001a\u00020\u000bH\u0002J\u001c\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000eJ\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0002JH\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u0017JX\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/ir/builders/Scope;", "", "scopeOwnerSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)V", "getScopeOwnerSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "getLocalDeclarationParent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "lastTemporaryIndex", "", "nextTemporaryIndex", "inventNameForTemporary", "", "prefix", "nameHint", "getNameForTemporary", "createTemporaryVariableDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "irType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "isMutable", "", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "startOffset", "endOffset", "inventUniqueName", "createTemporaryVariable", "irExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Scope {
    private int lastTemporaryIndex;
    private final IrSymbol scopeOwnerSymbol;

    public Scope(IrSymbol irSymbol) {
        irSymbol.getClass();
        this.scopeOwnerSymbol = irSymbol;
    }

    public static /* synthetic */ IrVariable createTemporaryVariable$default(Scope scope, IrExpression irExpression, String str, boolean z, IrDeclarationOrigin irDeclarationOrigin, IrType irType, int i, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str = null;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            irDeclarationOrigin = IrDeclarationOrigin.INSTANCE.getIR_TEMPORARY_VARIABLE();
        }
        if ((i3 & 16) != 0) {
            irType = null;
        }
        if ((i3 & 32) != 0) {
            i = irExpression.getStartOffset();
        }
        if ((i3 & 64) != 0) {
            i2 = irExpression.getEndOffset();
        }
        if ((i3 & 128) != 0) {
            z2 = true;
        }
        return scope.createTemporaryVariable(irExpression, str, z, irDeclarationOrigin, irType, i, i2, z2);
    }

    public static /* synthetic */ IrVariable createTemporaryVariableDeclaration$default(Scope scope, IrType irType, String str, boolean z, IrDeclarationOrigin irDeclarationOrigin, int i, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str = null;
        }
        String str2 = str;
        if ((i3 & 4) != 0) {
            z = false;
        }
        boolean z3 = z;
        if ((i3 & 8) != 0) {
            irDeclarationOrigin = IrDeclarationOrigin.INSTANCE.getIR_TEMPORARY_VARIABLE();
        }
        return scope.createTemporaryVariableDeclaration(irType, str2, z3, irDeclarationOrigin, i, i2, (i3 & 64) != 0 ? true : z2);
    }

    private final String getNameForTemporary(String nameHint) {
        return inventNameForTemporary("tmp", nameHint);
    }

    public static /* synthetic */ String inventNameForTemporary$default(Scope scope, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return scope.inventNameForTemporary(str, str2);
    }

    private final int nextTemporaryIndex() {
        int i = this.lastTemporaryIndex;
        this.lastTemporaryIndex = i + 1;
        return i;
    }

    public final IrVariable createTemporaryVariable(IrExpression irExpression, String nameHint, boolean isMutable, IrDeclarationOrigin origin, IrType irType, int startOffset, int endOffset, boolean inventUniqueName) {
        irExpression.getClass();
        origin.getClass();
        if (irType == null) {
            irType = irExpression.getType();
        }
        IrVariable irVariableCreateTemporaryVariableDeclaration = createTemporaryVariableDeclaration(irType, nameHint, isMutable, origin, startOffset, endOffset, inventUniqueName);
        irVariableCreateTemporaryVariableDeclaration.setInitializer(irExpression);
        return irVariableCreateTemporaryVariableDeclaration;
    }

    public final IrVariable createTemporaryVariableDeclaration(IrType irType, String nameHint, boolean isMutable, IrDeclarationOrigin origin, int startOffset, int endOffset, boolean inventUniqueName) {
        irType.getClass();
        origin.getClass();
        if (inventUniqueName) {
            nameHint = getNameForTemporary(nameHint);
        } else if (nameHint == null) {
            nameHint = "tmp";
        }
        Name nameIdentifier = Name.identifier(nameHint);
        nameIdentifier.getClass();
        IrVariableImpl IrVariableImpl = BuildersKt.IrVariableImpl(startOffset, endOffset, origin, new IrVariableSymbolImpl((VariableDescriptor) null, 1, (DefaultConstructorMarker) null), nameIdentifier, irType, isMutable, false, false);
        IrVariableImpl.setParent(getLocalDeclarationParent());
        return IrVariableImpl;
    }

    public final IrDeclarationParent getLocalDeclarationParent() {
        boolean zIsBound = this.scopeOwnerSymbol.isBound();
        IrSymbol irSymbol = this.scopeOwnerSymbol;
        if (!zIsBound) {
            s22.a("Unbound symbol: ", irSymbol);
            return null;
        }
        IrDeclarationParent owner = irSymbol.getOwner();
        if (owner instanceof IrDeclarationParent) {
            return owner;
        }
        if (owner instanceof IrDeclaration) {
            return ((IrDeclaration) owner).getParent();
        }
        s22.a("Not a declaration: ", owner);
        return null;
    }

    public final IrSymbol getScopeOwnerSymbol() {
        return this.scopeOwnerSymbol;
    }

    public final String inventNameForTemporary(String prefix, String nameHint) {
        prefix.getClass();
        int iNextTemporaryIndex = nextTemporaryIndex();
        if (nameHint == null) {
            return prefix + iNextTemporaryIndex;
        }
        return prefix + iNextTemporaryIndex + '_' + nameHint;
    }
}

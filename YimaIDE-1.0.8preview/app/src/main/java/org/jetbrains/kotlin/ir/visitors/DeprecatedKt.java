package org.jetbrains.kotlin.ir.visitors;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*n\b\u0007\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B<\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\"\b\u0007\u0012\u001e\b\u000bB\u001a\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\f\u0012\n\b\r\u0012\u0006\b\n0\u000e8\u000f*J\b\u0007\u0010\u0010\"\u00020\u00112\u00020\u0011B<\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0012\u0012\"\b\u0007\u0012\u001e\b\u000bB\u001a\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0013\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\u0014\u0012\n\b\r\u0012\u0006\b\n0\u000e8\u000f¨\u0006\u0015"}, d2 = {"IrElementVisitor", "R", "D", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitor;", "Lkotlin/Deprecated;", "message", "Use the IrVisitor abstract class instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "IrVisitor<R, D>", "imports", "org.jetbrains.kotlin.ir.visitors.IrVisitor", "level", "Lkotlin/DeprecationLevel;", "ERROR", "IrElementVisitorVoid", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitorVoid;", "Use the IrVisitorVoid abstract class instead", "IrVisitorVoid", "org.jetbrains.kotlin.ir.visitors.IrVisitorVoid", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class DeprecatedKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the IrVisitor abstract class instead", replaceWith = @ReplaceWith(expression = "IrVisitor<R, D>", imports = {"org.jetbrains.kotlin.ir.visitors.IrVisitor"}))
    public static /* synthetic */ void IrElementVisitor$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the IrVisitorVoid abstract class instead", replaceWith = @ReplaceWith(expression = "IrVisitorVoid", imports = {"org.jetbrains.kotlin.ir.visitors.IrVisitorVoid"}))
    public static /* synthetic */ void IrElementVisitorVoid$annotations() {
    }
}

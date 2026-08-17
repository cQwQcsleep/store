package org.jetbrains.kotlin.ir.visitors;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "Use the IrTransformer abstract class instead", replaceWith = @ReplaceWith(expression = "IrTransformer<D>", imports = {"org.jetbrains.kotlin.ir.visitors.IrTransformer"}))
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002Ê\u0001<\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\"\b\u0007\u0012\u001e\b\u000bB\u001a\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\f\u0012\n\b\r\u0012\u0006\b\n0\u000e8\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/visitors/IrElementTransformer;", "D", "", "org.jetbrains.kotlin:ir.tree", "Lkotlin/Deprecated;", "message", "Use the IrTransformer abstract class instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "IrTransformer<D>", "imports", "org.jetbrains.kotlin.ir.visitors.IrTransformer", "level", "Lkotlin/DeprecationLevel;", "ERROR"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrElementTransformer<D> {
}

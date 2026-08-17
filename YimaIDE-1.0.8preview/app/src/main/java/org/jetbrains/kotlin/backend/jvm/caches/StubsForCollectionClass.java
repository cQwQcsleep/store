package org.jetbrains.kotlin.backend.jvm.caches;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/caches/StubsForCollectionClass;", "", "readOnlyClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getReadOnlyClass", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "mutableClass", "getMutableClass", "candidatesForStubs", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getCandidatesForStubs", "()Ljava/util/Collection;", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface StubsForCollectionClass {
    Collection<IrSimpleFunction> getCandidatesForStubs();

    IrClassSymbol getMutableClass();

    IrClassSymbol getReadOnlyClass();
}

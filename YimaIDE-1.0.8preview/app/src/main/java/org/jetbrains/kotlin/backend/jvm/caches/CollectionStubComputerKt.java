package org.jetbrains.kotlin.backend.jvm.caches;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"?\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00042\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<set-?>", "", "Lorg/jetbrains/kotlin/backend/jvm/caches/StubsForCollectionClass;", "cachedStubsForCollectionClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getCachedStubsForCollectionClass", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", "setCachedStubsForCollectionClass", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/List;)V", "cachedStubsForCollectionClass$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CollectionStubComputerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute cachedStubsForCollectionClass$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(CollectionStubComputerKt.class, "cachedStubsForCollectionClass", "getCachedStubsForCollectionClass(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", 1)};
        $$delegatedProperties = kPropertyArr;
        cachedStubsForCollectionClass$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<StubsForCollectionClass> getCachedStubsForCollectionClass(IrClass irClass) {
        return (List) IrAttributeKt.get(irClass, cachedStubsForCollectionClass$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setCachedStubsForCollectionClass(IrClass irClass, List<? extends StubsForCollectionClass> list) {
        IrAttributeKt.set(irClass, cachedStubsForCollectionClass$delegate, list);
    }
}

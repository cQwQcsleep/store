package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.properties.ReadWriteProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClassesKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aS\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00030\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00030\n¢\u0006\u0002\b\u000b\u001a:\u0010\f\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\b\u001a:\u0010\u000f\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\b¨\u0006\u0011"}, d2 = {"lazyMappedVar", "Lkotlin/properties/ReadWriteProperty;", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "initializer", "Lkotlin/Function0;", "mapFunction", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "lazyMappedFunctionListVar", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "lazyMappedPropertyListVar", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrSymbolsMappingForLazyClassesKt {
    public static List a(Fir2IrSymbolsMappingForLazyClasses fir2IrSymbolsMappingForLazyClasses, List list) {
        fir2IrSymbolsMappingForLazyClasses.getClass();
        list.getClass();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(fir2IrSymbolsMappingForLazyClasses.remapFunctionSymbol((IrSimpleFunctionSymbol) it.next()));
        }
        return arrayList;
    }

    public static List b(Fir2IrSymbolsMappingForLazyClasses fir2IrSymbolsMappingForLazyClasses, List list) {
        fir2IrSymbolsMappingForLazyClasses.getClass();
        list.getClass();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(fir2IrSymbolsMappingForLazyClasses.remapPropertySymbol((IrPropertySymbol) it.next()));
        }
        return arrayList;
    }

    public static final ReadWriteProperty<Object, List<IrSimpleFunctionSymbol>> lazyMappedFunctionListVar(Fir2IrSymbolsMappingForLazyClasses fir2IrSymbolsMappingForLazyClasses, IrLock irLock, Function0<? extends List<? extends IrSimpleFunctionSymbol>> function0) {
        fir2IrSymbolsMappingForLazyClasses.getClass();
        irLock.getClass();
        function0.getClass();
        return lazyMappedVar(fir2IrSymbolsMappingForLazyClasses, irLock, function0, new Function2() { // from class: pw4
            public final Object invoke(Object obj, Object obj2) {
                return Fir2IrSymbolsMappingForLazyClassesKt.a((Fir2IrSymbolsMappingForLazyClasses) obj, (List) obj2);
            }
        });
    }

    public static final ReadWriteProperty<Object, List<IrPropertySymbol>> lazyMappedPropertyListVar(Fir2IrSymbolsMappingForLazyClasses fir2IrSymbolsMappingForLazyClasses, IrLock irLock, Function0<? extends List<? extends IrPropertySymbol>> function0) {
        fir2IrSymbolsMappingForLazyClasses.getClass();
        irLock.getClass();
        function0.getClass();
        return lazyMappedVar(fir2IrSymbolsMappingForLazyClasses, irLock, function0, new Function2() { // from class: ow4
            public final Object invoke(Object obj, Object obj2) {
                return Fir2IrSymbolsMappingForLazyClassesKt.b((Fir2IrSymbolsMappingForLazyClasses) obj, (List) obj2);
            }
        });
    }

    public static final <T> ReadWriteProperty<Object, T> lazyMappedVar(Fir2IrSymbolsMappingForLazyClasses fir2IrSymbolsMappingForLazyClasses, IrLock irLock, Function0<? extends T> function0, Function2<? super Fir2IrSymbolsMappingForLazyClasses, ? super T, ? extends T> function2) {
        fir2IrSymbolsMappingForLazyClasses.getClass();
        irLock.getClass();
        function0.getClass();
        function2.getClass();
        return new MappedLazyVar(irLock, function0, fir2IrSymbolsMappingForLazyClasses, function2);
    }
}

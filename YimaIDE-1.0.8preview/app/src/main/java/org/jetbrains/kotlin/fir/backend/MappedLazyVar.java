package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002BD\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u001d\u0010\n\u001a\u0019\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0002\b\f¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004J%\u0010\u001a\u001a\u00028\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0096\u0082\u0004¢\u0006\u0002\u0010\u001eJ-\u0010\u001f\u001a\u00020 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d2\u0006\u0010!\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R(\u0010\n\u001a\u0019\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0002\b\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/MappedLazyVar;", "T", "Lkotlin/properties/ReadWriteProperty;", Argument.Delimiters.none, "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "initializer", "Lkotlin/Function0;", "map", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "mapperFun", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lorg/jetbrains/kotlin/ir/IrLock;Lkotlin/jvm/functions/Function0;Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;Lkotlin/jvm/functions/Function2;)V", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "getMap", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getMapperFun", "()Lkotlin/jvm/functions/Function2;", "lazy", "isRemapped", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", Argument.Delimiters.none, "value", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MappedLazyVar<T> implements ReadWriteProperty<Object, T> {
    private volatile boolean isRemapped;
    private final ReadWriteProperty<Object, T> lazy;
    private final IrLock lock;
    private final Fir2IrSymbolsMappingForLazyClasses map;
    private final Function2<Fir2IrSymbolsMappingForLazyClasses, T, T> mapperFun;

    /* JADX WARN: Multi-variable type inference failed */
    public MappedLazyVar(IrLock irLock, Function0<? extends T> function0, Fir2IrSymbolsMappingForLazyClasses fir2IrSymbolsMappingForLazyClasses, Function2<? super Fir2IrSymbolsMappingForLazyClasses, ? super T, ? extends T> function2) {
        irLock.getClass();
        function0.getClass();
        fir2IrSymbolsMappingForLazyClasses.getClass();
        function2.getClass();
        this.lock = irLock;
        this.map = fir2IrSymbolsMappingForLazyClasses;
        this.mapperFun = function2;
        this.lazy = LazyUtilKt.lazyVar(irLock, function0);
    }

    public final IrLock getLock() {
        return this.lock;
    }

    public final Fir2IrSymbolsMappingForLazyClasses getMap() {
        return this.map;
    }

    public final Function2<Fir2IrSymbolsMappingForLazyClasses, T, T> getMapperFun() {
        return this.mapperFun;
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public T getValue(Object thisRef, KProperty<?> property) {
        T value;
        property.getClass();
        synchronized (this.lock) {
            try {
                if (!this.isRemapped && this.map.getIsRemapperEnabled()) {
                    ReadWriteProperty<Object, T> readWriteProperty = this.lazy;
                    readWriteProperty.setValue(thisRef, property, (T) this.mapperFun.invoke(this.map, readWriteProperty.getValue(thisRef, property)));
                    this.isRemapped = true;
                }
                value = this.lazy.getValue(thisRef, property);
            } catch (Throwable th) {
                throw th;
            }
        }
        return value;
    }

    @Override // kotlin.properties.ReadWriteProperty
    public void setValue(Object thisRef, KProperty<?> property, T value) {
        property.getClass();
        synchronized (this.lock) {
            try {
                this.lazy.setValue(thisRef, property, value);
                if (this.map.getIsRemapperEnabled()) {
                    this.isRemapped = true;
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        return this.lazy.toString();
    }
}

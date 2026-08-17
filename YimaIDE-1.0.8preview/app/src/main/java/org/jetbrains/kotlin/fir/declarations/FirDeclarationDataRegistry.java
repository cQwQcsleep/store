package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.util.ConeTypeRegistry;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u0013\u0014\u0015B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\u00022\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\b*\u00020\u00022\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u000f\"\b\b\u0000\u0010\b*\u00020\u0002\"\b\b\u0001\u0010\u0011*\u00020\u00032\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\u0012¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry;", "Lorg/jetbrains/kotlin/fir/util/ConeTypeRegistry;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;", Argument.Delimiters.none, "<init>", "()V", "data", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "K", "key", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;)Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "symbolAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$SymbolDataAccessor;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;)Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$SymbolDataAccessor;", "attributesAccessor", "Lkotlin/properties/ReadWriteProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "V", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;)Lkotlin/properties/ReadWriteProperty;", "DeclarationDataAccessor", "SymbolDataAccessor", "AttributeDataAccessor", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationDataRegistry extends ConeTypeRegistry<FirDeclarationDataKey, Object> {
    public static final FirDeclarationDataRegistry INSTANCE = new FirDeclarationDataRegistry();

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u00030\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0006¢\u0006\u0004\b\u0007\u0010\bJ*\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0086\u0002¢\u0006\u0002\u0010\u0011J2\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\b\u0010\u0014\u001a\u0004\u0018\u0001H\fH\u0086\u0002¢\u0006\u0002\u0010\u0015R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", Argument.Delimiters.none, "dataAccessor", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;", "key", "Lkotlin/reflect/KClass;", "<init>", "(Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;Lkotlin/reflect/KClass;)V", "getKey", "()Lkotlin/reflect/KClass;", "getValue", "V", "thisRef", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "property", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", Argument.Delimiters.none, "value", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DeclarationDataAccessor {
        private final NullableArrayMapAccessor<FirDeclarationDataKey, Object, ?> dataAccessor;
        private final KClass<? extends FirDeclarationDataKey> key;

        public DeclarationDataAccessor(NullableArrayMapAccessor<FirDeclarationDataKey, Object, ?> nullableArrayMapAccessor, KClass<? extends FirDeclarationDataKey> kClass) {
            nullableArrayMapAccessor.getClass();
            kClass.getClass();
            this.dataAccessor = nullableArrayMapAccessor;
            this.key = kClass;
        }

        public final KClass<? extends FirDeclarationDataKey> getKey() {
            return this.key;
        }

        public final <V> V getValue(FirDeclaration thisRef, KProperty<?> property) {
            thisRef.getClass();
            property.getClass();
            V v = (V) this.dataAccessor.getValue(thisRef.getAttributes(), property);
            if (v == null) {
                return null;
            }
            return v;
        }

        public final <V> void setValue(FirDeclaration thisRef, KProperty<?> property, V value) {
            thisRef.getClass();
            property.getClass();
            thisRef.getAttributes().set$org_jetbrains_kotlin_tree(this.key, value);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u00030\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0006¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0086\u0002¢\u0006\u0002\u0010\u0011R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$SymbolDataAccessor;", Argument.Delimiters.none, "dataAccessor", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;", "key", "Lkotlin/reflect/KClass;", "<init>", "(Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;Lkotlin/reflect/KClass;)V", "getKey", "()Lkotlin/reflect/KClass;", "getValue", "V", "thisRef", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "property", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SymbolDataAccessor {
        private final NullableArrayMapAccessor<FirDeclarationDataKey, Object, ?> dataAccessor;
        private final KClass<? extends FirDeclarationDataKey> key;

        public SymbolDataAccessor(NullableArrayMapAccessor<FirDeclarationDataKey, Object, ?> nullableArrayMapAccessor, KClass<? extends FirDeclarationDataKey> kClass) {
            nullableArrayMapAccessor.getClass();
            kClass.getClass();
            this.dataAccessor = nullableArrayMapAccessor;
            this.key = kClass;
        }

        public final KClass<? extends FirDeclarationDataKey> getKey() {
            return this.key;
        }

        public final <V> V getValue(FirBasedSymbol<?> thisRef, KProperty<?> property) {
            thisRef.getClass();
            property.getClass();
            V v = (V) this.dataAccessor.getValue(thisRef.getFir().getAttributes(), property);
            if (v == null) {
                return null;
            }
            return v;
        }
    }

    private FirDeclarationDataRegistry() {
    }

    public final <K extends FirDeclarationDataKey, V> ReadWriteProperty<FirDeclarationAttributes, V> attributesAccessor(K key) {
        key.getClass();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(key.getClass());
        return new AttributeDataAccessor(generateNullableAccessor(orCreateKotlinClass), orCreateKotlinClass);
    }

    public final <K extends FirDeclarationDataKey> DeclarationDataAccessor data(K key) {
        key.getClass();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(key.getClass());
        return new DeclarationDataAccessor(generateAnyNullableAccessor(orCreateKotlinClass), orCreateKotlinClass);
    }

    public final <K extends FirDeclarationDataKey> SymbolDataAccessor symbolAccessor(K key) {
        key.getClass();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(key.getClass());
        return new SymbolDataAccessor(generateAnyNullableAccessor(orCreateKotlinClass), orCreateKotlinClass);
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003B1\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\t¢\u0006\u0004\b\n\u0010\u000bJ%\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0014J-\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\b\u0010\u0017\u001a\u0004\u0018\u00018\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0018R#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$AttributeDataAccessor;", "V", Argument.Delimiters.none, "Lkotlin/properties/ReadWriteProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "dataAccessor", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;", "key", "Lkotlin/reflect/KClass;", "<init>", "(Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;Lkotlin/reflect/KClass;)V", "getDataAccessor", "()Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "getKey", "()Lkotlin/reflect/KClass;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", Argument.Delimiters.none, "value", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AttributeDataAccessor<V> implements ReadWriteProperty<FirDeclarationAttributes, V> {
        private final NullableArrayMapAccessor<FirDeclarationDataKey, Object, V> dataAccessor;
        private final KClass<? extends FirDeclarationDataKey> key;

        public AttributeDataAccessor(NullableArrayMapAccessor<FirDeclarationDataKey, Object, V> nullableArrayMapAccessor, KClass<? extends FirDeclarationDataKey> kClass) {
            nullableArrayMapAccessor.getClass();
            kClass.getClass();
            this.dataAccessor = nullableArrayMapAccessor;
            this.key = kClass;
        }

        public final NullableArrayMapAccessor<FirDeclarationDataKey, Object, V> getDataAccessor() {
            return this.dataAccessor;
        }

        public final KClass<? extends FirDeclarationDataKey> getKey() {
            return this.key;
        }

        public V getValue(FirDeclarationAttributes thisRef, KProperty<?> property) {
            thisRef.getClass();
            property.getClass();
            return (V) this.dataAccessor.getValue(thisRef, property);
        }

        /* JADX INFO: renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(FirDeclarationAttributes thisRef, KProperty<?> property, V value) {
            thisRef.getClass();
            property.getClass();
            thisRef.set$org_jetbrains_kotlin_tree(this.key, value);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.properties.ReadWriteProperty
        public /* bridge */ /* synthetic */ void setValue(FirDeclarationAttributes firDeclarationAttributes, KProperty kProperty, Object obj) {
            setValue2(firDeclarationAttributes, (KProperty<?>) kProperty, obj);
        }

        @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
        public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
            return getValue((FirDeclarationAttributes) obj, (KProperty<?>) kProperty);
        }
    }
}

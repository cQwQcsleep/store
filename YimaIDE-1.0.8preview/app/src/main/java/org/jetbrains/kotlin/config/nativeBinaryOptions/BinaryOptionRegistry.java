package org.jetbrains.kotlin.config.nativeBinaryOptions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.PropertyDelegateProvider;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptionRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0004J\u0014\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u0006J*\u0010\r\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f0\u000eH\u0004J*\u0010\u0012\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\u000f0\u000eH\u0004J*\u0010\u0014\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00100\u000f0\u000eH\u0004JH\u0010\u0015\u001a*\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u001e\u0012\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u00160\u00100\u000f0\u000e\"\b\b\u0000\u0010\u0017*\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0004Jn\u0010\n\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u00100\u000f0\u000e\"\u0010\b\u0000\u0010\u0017\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u0016\b\n\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u0002H\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u001c2\u0014\b\n\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u00020\u00110\u001cH\u0084\bø\u0001\u0000R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOptionRegistry;", Argument.Delimiters.none, "<init>", "()V", "registeredOptionsByName", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption;", "register", Argument.Delimiters.none, "option", "getByName", ModuleXmlParser.NAME, "booleanOption", "Lkotlin/properties/PropertyDelegateProvider;", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, "uintOption", "Lkotlin/UInt;", "stringOption", "listOption", Argument.Delimiters.none, "T", "elementValueParser", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", Argument.Delimiters.none, "shortcut", "Lkotlin/Function1;", "hideValue", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class BinaryOptionRegistry {
    private final Map<String, BinaryOption<?>> registeredOptionsByName = new LinkedHashMap();

    public static ReadOnlyProperty b(BinaryOptionRegistry binaryOptionRegistry, Object obj, KProperty kProperty) {
        kProperty.getClass();
        final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), StringValueParser.INSTANCE, null, 4, null);
        binaryOptionRegistry.register(binaryOption);
        return new ReadOnlyProperty() { // from class: xu0
            @Override // kotlin.properties.ReadOnlyProperty
            public final Object getValue(Object obj2, KProperty kProperty2) {
                return BinaryOptionRegistry.stringOption$lambda$0$0(binaryOption, obj2, kProperty2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompilerConfigurationKey booleanOption$lambda$0$0(BinaryOption binaryOption, Object obj, KProperty kProperty) {
        kProperty.getClass();
        return binaryOption.getCompilerConfigurationKey();
    }

    public static ReadOnlyProperty d(BinaryOption.ValueParser valueParser, BinaryOptionRegistry binaryOptionRegistry, Object obj, KProperty kProperty) {
        kProperty.getClass();
        final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), new ListValueParser(valueParser), null, 4, null);
        binaryOptionRegistry.register(binaryOption);
        return new ReadOnlyProperty() { // from class: av0
            @Override // kotlin.properties.ReadOnlyProperty
            public final Object getValue(Object obj2, KProperty kProperty2) {
                return BinaryOptionRegistry.listOption$lambda$0$0(binaryOption, obj2, kProperty2);
            }
        };
    }

    public static ReadOnlyProperty g(BinaryOptionRegistry binaryOptionRegistry, Object obj, KProperty kProperty) {
        kProperty.getClass();
        final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), BooleanValueParser.INSTANCE, null, 4, null);
        binaryOptionRegistry.register(binaryOption);
        return new ReadOnlyProperty() { // from class: yu0
            @Override // kotlin.properties.ReadOnlyProperty
            public final Object getValue(Object obj2, KProperty kProperty2) {
                return BinaryOptionRegistry.booleanOption$lambda$0$0(binaryOption, obj2, kProperty2);
            }
        };
    }

    public static ReadOnlyProperty h(BinaryOptionRegistry binaryOptionRegistry, Object obj, KProperty kProperty) {
        kProperty.getClass();
        final BinaryOption<?> binaryOption = new BinaryOption<>(kProperty.getName(), UIntValueParser.INSTANCE, null, 4, null);
        binaryOptionRegistry.register(binaryOption);
        return new ReadOnlyProperty() { // from class: cv0
            @Override // kotlin.properties.ReadOnlyProperty
            public final Object getValue(Object obj2, KProperty kProperty2) {
                return BinaryOptionRegistry.uintOption$lambda$0$0(binaryOption, obj2, kProperty2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompilerConfigurationKey listOption$lambda$0$0(BinaryOption binaryOption, Object obj, KProperty kProperty) {
        kProperty.getClass();
        return binaryOption.getCompilerConfigurationKey();
    }

    public static /* synthetic */ PropertyDelegateProvider option$default(BinaryOptionRegistry binaryOptionRegistry, Function1 function1, Function1 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: option");
            return null;
        }
        if ((i & 1) != 0) {
            Intrinsics.needClassReification();
            function1 = AnonymousClass1.INSTANCE;
        }
        if ((i & 2) != 0) {
            Intrinsics.needClassReification();
            function2 = AnonymousClass2.INSTANCE;
        }
        function1.getClass();
        function2.getClass();
        Intrinsics.needClassReification();
        return new AnonymousClass3(function1, function2, binaryOptionRegistry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompilerConfigurationKey stringOption$lambda$0$0(BinaryOption binaryOption, Object obj, KProperty kProperty) {
        kProperty.getClass();
        return binaryOption.getCompilerConfigurationKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompilerConfigurationKey uintOption$lambda$0$0(BinaryOption binaryOption, Object obj, KProperty kProperty) {
        kProperty.getClass();
        return binaryOption.getCompilerConfigurationKey();
    }

    public final PropertyDelegateProvider<Object, ReadOnlyProperty<Object, CompilerConfigurationKey<Boolean>>> booleanOption() {
        return new PropertyDelegateProvider() { // from class: dv0
            @Override // kotlin.properties.PropertyDelegateProvider
            public final Object provideDelegate(Object obj, KProperty kProperty) {
                return BinaryOptionRegistry.g(this.a, obj, kProperty);
            }
        };
    }

    public final BinaryOption<?> getByName(String name) {
        name.getClass();
        return this.registeredOptionsByName.get(name);
    }

    public final <T> PropertyDelegateProvider<Object, ReadOnlyProperty<Object, CompilerConfigurationKey<List<T>>>> listOption(final BinaryOption.ValueParser<T> elementValueParser) {
        elementValueParser.getClass();
        return new PropertyDelegateProvider() { // from class: bv0
            @Override // kotlin.properties.PropertyDelegateProvider
            public final Object provideDelegate(Object obj, KProperty kProperty) {
                return BinaryOptionRegistry.d(elementValueParser, this, obj, kProperty);
            }
        };
    }

    public final /* synthetic */ <T extends Enum<T>> PropertyDelegateProvider<Object, ReadOnlyProperty<Object, CompilerConfigurationKey<T>>> option(Function1<? super T, String> shortcut, Function1<? super T, Boolean> hideValue) {
        shortcut.getClass();
        hideValue.getClass();
        Intrinsics.needClassReification();
        return new AnonymousClass3(shortcut, hideValue, this);
    }

    public final void register(BinaryOption<?> option) {
        option.getClass();
        if (this.registeredOptionsByName.get(option.getName()) == null) {
            this.registeredOptionsByName.put(option.getName(), option);
        } else {
            b88.a("option '", option.getName(), "' is registered twice");
        }
    }

    public final PropertyDelegateProvider<Object, ReadOnlyProperty<Object, CompilerConfigurationKey<String>>> stringOption() {
        return new PropertyDelegateProvider() { // from class: zu0
            @Override // kotlin.properties.PropertyDelegateProvider
            public final Object provideDelegate(Object obj, KProperty kProperty) {
                return BinaryOptionRegistry.b(this.a, obj, kProperty);
            }
        };
    }

    public final PropertyDelegateProvider<Object, ReadOnlyProperty<Object, CompilerConfigurationKey<UInt>>> uintOption() {
        return new PropertyDelegateProvider() { // from class: wu0
            @Override // kotlin.properties.PropertyDelegateProvider
            public final Object provideDelegate(Object obj, KProperty kProperty) {
                return BinaryOptionRegistry.h(this.a, obj, kProperty);
            }
        };
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptionRegistry$option$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
    public static final class AnonymousClass3<T, D> implements PropertyDelegateProvider {
        final /* synthetic */ Function1<T, Boolean> $hideValue;
        final /* synthetic */ Function1<T, String> $shortcut;
        final /* synthetic */ BinaryOptionRegistry this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function1<? super T, String> function1, Function1<? super T, Boolean> function2, BinaryOptionRegistry binaryOptionRegistry) {
            this.$shortcut = function1;
            this.$hideValue = function2;
            this.this$0 = binaryOptionRegistry;
        }

        @Override // kotlin.properties.PropertyDelegateProvider
        public final ReadOnlyProperty<Object, CompilerConfigurationKey<T>> provideDelegate(Object obj, KProperty<?> kProperty) {
            kProperty.getClass();
            String name = kProperty.getName();
            Intrinsics.reifiedOperationMarker(5, "T");
            final BinaryOption<?> binaryOption = new BinaryOption<>(name, new EnumValueParser(ArraysKt.toList(new Enum[0]), this.$shortcut, this.$hideValue), null, 4, null);
            this.this$0.register(binaryOption);
            return new ReadOnlyProperty() { // from class: org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOptionRegistry.option.3.1
                @Override // kotlin.properties.ReadOnlyProperty
                public final CompilerConfigurationKey<T> getValue(Object obj2, KProperty<?> kProperty2) {
                    kProperty2.getClass();
                    return binaryOption.getCompilerConfigurationKey();
                }

                @Override // kotlin.properties.ReadOnlyProperty
                public /* bridge */ /* synthetic */ Object getValue(Object obj2, KProperty kProperty2) {
                    return getValue(obj2, (KProperty<?>) kProperty2);
                }
            };
        }

        @Override // kotlin.properties.PropertyDelegateProvider
        public /* bridge */ /* synthetic */ Object provideDelegate(Object obj, KProperty kProperty) {
            return provideDelegate(obj, (KProperty<?>) kProperty);
        }
    }
}

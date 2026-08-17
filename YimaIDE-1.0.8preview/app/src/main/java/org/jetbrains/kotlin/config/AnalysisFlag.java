package org.jetbrains.kotlin.config;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0011\u0012B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0004H\u0096\u0080\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag;", "T", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "defaultValue", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getDefaultValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "Delegate", "Delegates", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnalysisFlag<T> {
    private final T defaultValue;
    private final String name;

    public AnalysisFlag(String str, T t) {
        str.getClass();
        this.name = str;
        this.defaultValue = t;
    }

    public boolean equals(Object other) {
        return (other instanceof AnalysisFlag) && Intrinsics.areEqual(((AnalysisFlag) other).name, this.name);
    }

    public final T getDefaultValue() {
        return this.defaultValue;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getName() {
        return this.name;
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates;", Argument.Delimiters.none, "<init>", "()V", "Boolean", "ApiModeDisabledByDefault", "ReturnValueCheckerDisabledByDefault", "HeaderModeTypeAnyByDefault", "ListOfStrings", "WarningLevelMap", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Delegates {
        public static final Delegates INSTANCE = new Delegates();

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$ApiModeDisabledByDefault;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "Lorg/jetbrains/kotlin/config/ExplicitApiMode;", "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ApiModeDisabledByDefault {
            public static final ApiModeDisabledByDefault INSTANCE = new ApiModeDisabledByDefault();

            private ApiModeDisabledByDefault() {
            }

            public final Delegate<ExplicitApiMode> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new Delegate<>(property.getName(), ExplicitApiMode.DISABLED);
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$HeaderModeTypeAnyByDefault;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "Lorg/jetbrains/kotlin/config/HeaderMode;", "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class HeaderModeTypeAnyByDefault {
            public static final HeaderModeTypeAnyByDefault INSTANCE = new HeaderModeTypeAnyByDefault();

            private HeaderModeTypeAnyByDefault() {
            }

            public final Delegate<HeaderMode> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new Delegate<>(property.getName(), HeaderMode.ANY);
            }
        }

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0086\u0002¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$ListOfStrings;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", Argument.Delimiters.none, Argument.Delimiters.none, "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ListOfStrings {
            public static final ListOfStrings INSTANCE = new ListOfStrings();

            private ListOfStrings() {
            }

            public final Delegate<List<String>> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new Delegate<>(property.getName(), CollectionsKt.emptyList());
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$ReturnValueCheckerDisabledByDefault;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "Lorg/jetbrains/kotlin/config/ReturnValueCheckerMode;", "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ReturnValueCheckerDisabledByDefault {
            public static final ReturnValueCheckerDisabledByDefault INSTANCE = new ReturnValueCheckerDisabledByDefault();

            private ReturnValueCheckerDisabledByDefault() {
            }

            public final Delegate<ReturnValueCheckerMode> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new Delegate<>(property.getName(), ReturnValueCheckerMode.DISABLED);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u00012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0086\u0002¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$WarningLevelMap;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/WarningLevel;", "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class WarningLevelMap {
            public static final WarningLevelMap INSTANCE = new WarningLevelMap();

            private WarningLevelMap() {
            }

            public final Delegate<Map<String, WarningLevel>> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new Delegate<>(property.getName(), MapsKt.emptyMap());
            }
        }

        private Delegates() {
        }

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$Boolean;", Argument.Delimiters.none, "defaultValue", Argument.Delimiters.none, "<init>", "(Z)V", "getDefaultValue", "()Z", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "instance", "property", "Lkotlin/reflect/KProperty;", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static class Boolean {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final boolean defaultValue;

            public Boolean(boolean z) {
                this.defaultValue = z;
            }

            public final boolean getDefaultValue() {
                return this.defaultValue;
            }

            public final Delegate<java.lang.Boolean> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new Delegate<>(property.getName(), java.lang.Boolean.valueOf(this.defaultValue));
            }

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$Boolean$Companion;", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegates$Boolean;", "<init>", "()V", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public static final class Companion extends Boolean {
                private Companion() {
                    super(false);
                }

                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0002B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\b\u0010\tJ&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0096\u0082\u0004R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "T", "Lkotlin/properties/ReadOnlyProperty;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", ModuleXmlParser.NAME, Argument.Delimiters.none, "defaultValue", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "flag", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Delegate<T> implements ReadOnlyProperty<Object, AnalysisFlag<? extends T>> {
        private final AnalysisFlag<T> flag;

        public Delegate(String str, T t) {
            str.getClass();
            this.flag = new AnalysisFlag<>(str, t);
        }

        @Override // kotlin.properties.ReadOnlyProperty
        public AnalysisFlag<T> getValue(Object thisRef, KProperty<?> property) {
            property.getClass();
            return this.flag;
        }

        @Override // kotlin.properties.ReadOnlyProperty
        public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
            return getValue(obj, (KProperty<?>) kProperty);
        }
    }
}

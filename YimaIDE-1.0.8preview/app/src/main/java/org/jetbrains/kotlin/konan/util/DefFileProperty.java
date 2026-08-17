package org.jetbrains.kotlin.konan.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.konan.library.KonanLibraryKt;
import org.jetbrains.kotlin.library.KotlinLibraryKt;
import org.jetbrains.kotlin.util.UtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \u000b*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004\n\u000b\f\rJ\u0017\u0010\u0007\u001a\u00028\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0004H&¢\u0006\u0002\u0010\tR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u000e\u000f\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFileProperty;", "T", "", "propertyName", "", "getPropertyName", "()Ljava/lang/String;", "parse", "rawValue", "(Ljava/lang/String;)Ljava/lang/Object;", "BooleanProperty", "Companion", "NullableStringProperty", "StringListProperty", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty$BooleanProperty;", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty$NullableStringProperty;", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty$StringListProperty;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DefFileProperty<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFileProperty$BooleanProperty;", "", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty;", "", "propertyName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getPropertyName", "()Ljava/lang/String;", "parse", "rawValue", "(Ljava/lang/String;)Ljava/lang/Boolean;", "ExcludeSystemLibs", "ExcludeDependentModules", "DisableDesignatedInitializerChecks", "AllowIncludingObjCCategoriesFromDefFile", "SkipNonImportableModules", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum BooleanProperty implements DefFileProperty<Boolean> {
        ExcludeSystemLibs("excludeSystemLibs"),
        ExcludeDependentModules("excludeDependentModules"),
        DisableDesignatedInitializerChecks("disableDesignatedInitializerChecks"),
        AllowIncludingObjCCategoriesFromDefFile("allowIncludingObjCCategoriesFromDefFile"),
        SkipNonImportableModules("skipNonImportableModules");

        private final String propertyName;

        BooleanProperty(String str) {
            this.propertyName = str;
        }

        @Override // org.jetbrains.kotlin.konan.util.DefFileProperty
        public String getPropertyName() {
            return this.propertyName;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.jetbrains.kotlin.konan.util.DefFileProperty
        public Boolean parse(String rawValue) {
            return Boolean.valueOf(Boolean.parseBoolean(rawValue));
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFileProperty$Companion;", "", "()V", "substitutablePropertyNames", "", "", "getSubstitutablePropertyNames", "()Ljava/util/Set;", "substitutablePropertyNames$delegate", "Lkotlin/Lazy;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: renamed from: substitutablePropertyNames$delegate, reason: from kotlin metadata */
        private static final Lazy<Set<String>> substitutablePropertyNames = LazyKt.lazy(new Function0<Set<? extends String>>() { // from class: org.jetbrains.kotlin.konan.util.DefFileProperty$Companion$substitutablePropertyNames$2
            public final Set<String> invoke() {
                DefFileProperty.StringListProperty[] stringListPropertyArrValues = DefFileProperty.StringListProperty.values();
                ArrayList arrayList = new ArrayList();
                for (DefFileProperty.StringListProperty stringListProperty : stringListPropertyArrValues) {
                    if (stringListProperty.getIsSubstitutable()) {
                        arrayList.add(stringListProperty);
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((DefFileProperty.StringListProperty) it.next()).getPropertyName());
                }
                return CollectionsKt.toSet(arrayList2);
            }
        });

        private Companion() {
        }

        public final Set<String> getSubstitutablePropertyNames() {
            return (Set) substitutablePropertyNames.getValue();
        }
    }

    String getPropertyName();

    T parse(String rawValue);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002B\u001b\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFileProperty$NullableStringProperty;", "", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty;", "", "propertyName", "defaultValue", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDefaultValue", "()Ljava/lang/String;", "getPropertyName", "parse", "rawValue", "Language", "Linker", "PackageName", "ForeignExceptionMode", "UserSetupHint", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum NullableStringProperty implements DefFileProperty<String> {
        Language("language", null, 2, null),
        Linker("linker", "clang"),
        PackageName(KotlinLibraryKt.KLIB_PROPERTY_PACKAGE, null, 2, null),
        ForeignExceptionMode("foreignExceptionMode", null, 2, null),
        UserSetupHint("userSetupHint", null, 2, null);

        private final String defaultValue;
        private final String propertyName;

        /* synthetic */ NullableStringProperty(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2);
        }

        public final String getDefaultValue() {
            return this.defaultValue;
        }

        @Override // org.jetbrains.kotlin.konan.util.DefFileProperty
        public String getPropertyName() {
            return this.propertyName;
        }

        @Override // org.jetbrains.kotlin.konan.util.DefFileProperty
        public String parse(String rawValue) {
            return rawValue == null ? this.defaultValue : rawValue;
        }

        NullableStringProperty(String str, String str2) {
            this.propertyName = str;
            this.defaultValue = str2;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EntryPoints' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002B\u0019\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFileProperty$StringListProperty;", "", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty;", "", "", "propertyName", "isSubstitutable", "", "(Ljava/lang/String;ILjava/lang/String;Z)V", "()Z", "getPropertyName", "()Ljava/lang/String;", "parse", "rawValue", "Headers", "Modules", "CompilerOpts", "EntryPoints", "LinkerOpts", "ExcludedFunctions", "ExcludedMacros", "StaticLibraries", "LibraryPaths", "HeaderFilter", "ExcludeFilter", "StrictEnums", "NonStrictEnums", "NoStringConversion", "Depends", "ExportForwardDeclarations", "AllowedOverloadsForCFunctions", "ObjcClassesIncludingCategories", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class StringListProperty implements DefFileProperty<List<? extends String>> {
        public static final StringListProperty EntryPoints;
        public static final StringListProperty ExcludedMacros;
        public static final StringListProperty HeaderFilter;
        private final boolean isSubstitutable;
        private final String propertyName;
        public static final StringListProperty Headers = new StringListProperty("Headers", 0, "headers", true);
        public static final StringListProperty Modules = new StringListProperty("Modules", 1, "modules", true);
        public static final StringListProperty CompilerOpts = new StringListProperty("CompilerOpts", 2, "compilerOpts", true);
        public static final StringListProperty LinkerOpts = new StringListProperty("LinkerOpts", 4, KonanLibraryKt.KLIB_PROPERTY_LINKED_OPTS, true);
        public static final StringListProperty ExcludedFunctions = new StringListProperty("ExcludedFunctions", 5, "excludedFunctions", true);
        public static final StringListProperty StaticLibraries = new StringListProperty("StaticLibraries", 7, "staticLibraries", true);
        public static final StringListProperty LibraryPaths = new StringListProperty("LibraryPaths", 8, "libraryPaths", true);
        public static final StringListProperty ExcludeFilter = new StringListProperty("ExcludeFilter", 10, "excludeFilter", false, 2, null);
        public static final StringListProperty StrictEnums = new StringListProperty("StrictEnums", 11, "strictEnums", false, 2, null);
        public static final StringListProperty NonStrictEnums = new StringListProperty("NonStrictEnums", 12, "nonStrictEnums", false, 2, null);
        public static final StringListProperty NoStringConversion = new StringListProperty("NoStringConversion", 13, "noStringConversion", false, 2, null);
        public static final StringListProperty Depends = new StringListProperty("Depends", 14, KotlinLibraryKt.KLIB_PROPERTY_DEPENDS, false, 2, null);
        public static final StringListProperty ExportForwardDeclarations = new StringListProperty("ExportForwardDeclarations", 15, KotlinLibraryKt.KLIB_PROPERTY_EXPORT_FORWARD_DECLARATIONS, false, 2, null);
        public static final StringListProperty AllowedOverloadsForCFunctions = new StringListProperty("AllowedOverloadsForCFunctions", 16, "allowedOverloadsForCFunctions", false, 2, null);
        public static final StringListProperty ObjcClassesIncludingCategories = new StringListProperty("ObjcClassesIncludingCategories", 17, "objcClassesIncludingCategories", false, 2, null);
        private static final /* synthetic */ StringListProperty[] $VALUES = $values();

        private static final /* synthetic */ StringListProperty[] $values() {
            return new StringListProperty[]{Headers, Modules, CompilerOpts, EntryPoints, LinkerOpts, ExcludedFunctions, ExcludedMacros, StaticLibraries, LibraryPaths, HeaderFilter, ExcludeFilter, StrictEnums, NonStrictEnums, NoStringConversion, Depends, ExportForwardDeclarations, AllowedOverloadsForCFunctions, ObjcClassesIncludingCategories};
        }

        static {
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            boolean z = false;
            EntryPoints = new StringListProperty("EntryPoints", 3, "entryPoint", z, i, defaultConstructorMarker);
            ExcludedMacros = new StringListProperty("ExcludedMacros", 6, "excludedMacros", z, i, defaultConstructorMarker);
            HeaderFilter = new StringListProperty("HeaderFilter", 9, "headerFilter", z, i, defaultConstructorMarker);
        }

        public /* synthetic */ StringListProperty(String str, int i, String str2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, str2, (i2 & 2) != 0 ? false : z);
        }

        public static StringListProperty valueOf(String str) {
            return (StringListProperty) Enum.valueOf(StringListProperty.class, str);
        }

        public static StringListProperty[] values() {
            return (StringListProperty[]) $VALUES.clone();
        }

        @Override // org.jetbrains.kotlin.konan.util.DefFileProperty
        public String getPropertyName() {
            return this.propertyName;
        }

        /* JADX INFO: renamed from: isSubstitutable, reason: from getter */
        public final boolean getIsSubstitutable() {
            return this.isSubstitutable;
        }

        @Override // org.jetbrains.kotlin.konan.util.DefFileProperty
        public List<? extends String> parse(String rawValue) {
            List<String> spaceSeparatedArgs;
            return (rawValue == null || (spaceSeparatedArgs = UtilKt.parseSpaceSeparatedArgs(rawValue)) == null) ? CollectionsKt.emptyList() : spaceSeparatedArgs;
        }

        private StringListProperty(String str, int i, String str2, boolean z) {
            super(str, i);
            this.propertyName = str2;
            this.isSubstitutable = z;
        }
    }
}

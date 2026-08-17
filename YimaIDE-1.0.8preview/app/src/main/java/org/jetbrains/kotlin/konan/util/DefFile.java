package org.jetbrains.kotlin.konan.util;

import java.io.File;
import java.util.List;
import java.util.Properties;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.konan.library.KonanLibraryKt;
import org.jetbrains.kotlin.library.KotlinLibraryKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0019B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFile;", "", "file", "Ljava/io/File;", "config", "Lorg/jetbrains/kotlin/konan/util/DefFile$DefFileConfig;", "manifestAddendProperties", "Ljava/util/Properties;", "defHeaderLines", "", "", "(Ljava/io/File;Lorg/jetbrains/kotlin/konan/util/DefFile$DefFileConfig;Ljava/util/Properties;Ljava/util/List;)V", "getConfig", "()Lorg/jetbrains/kotlin/konan/util/DefFile$DefFileConfig;", "getDefHeaderLines", "()Ljava/util/List;", "getFile", "()Ljava/io/File;", "getManifestAddendProperties", "()Ljava/util/Properties;", "name", "getName", "()Ljava/lang/String;", "name$delegate", "Lkotlin/Lazy;", "DefFileConfig", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefFile {
    private final DefFileConfig config;
    private final List<String> defHeaderLines;
    private final File file;
    private final Properties manifestAddendProperties;

    /* JADX INFO: renamed from: name$delegate, reason: from kotlin metadata */
    private final Lazy name;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\bT\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010`\u001a\u0002Ha\"\u0004\b\u0000\u0010a*\b\u0012\u0004\u0012\u0002Ha0b2\u0006\u0010c\u001a\u00020\u00002\n\u0010d\u001a\u0006\u0012\u0002\b\u00030eH\u0082\u0002¢\u0006\u0002\u0010fR\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\t\u0010\n*\u0004\b\u0007\u0010\bR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010*\u0004\b\u000e\u0010\bR!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\u0013\u0010\u0010*\u0004\b\u0012\u0010\bR!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\u0016\u0010\u0010*\u0004\b\u0015\u0010\bR\u001b\u0010\u0017\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\u0019\u0010\n*\u0004\b\u0018\u0010\bR!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\u001c\u0010\u0010*\u0004\b\u001b\u0010\bR\u001b\u0010\u001d\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\u001f\u0010\n*\u0004\b\u001e\u0010\bR!\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\"\u0010\u0010*\u0004\b!\u0010\bR\u001b\u0010#\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b%\u0010\n*\u0004\b$\u0010\bR!\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b(\u0010\u0010*\u0004\b'\u0010\bR!\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b+\u0010\u0010*\u0004\b*\u0010\bR!\u0010,\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b.\u0010\u0010*\u0004\b-\u0010\bR\u001d\u0010/\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b1\u00102*\u0004\b0\u0010\bR!\u00103\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b5\u0010\u0010*\u0004\b4\u0010\bR!\u00106\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b8\u0010\u0010*\u0004\b7\u0010\bR\u001d\u00109\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b;\u00102*\u0004\b:\u0010\bR!\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b>\u0010\u0010*\u0004\b=\u0010\bR\u001d\u0010?\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bA\u00102*\u0004\b@\u0010\bR!\u0010B\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bD\u0010\u0010*\u0004\bC\u0010\bR!\u0010E\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bG\u0010\u0010*\u0004\bF\u0010\bR!\u0010H\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bJ\u0010\u0010*\u0004\bI\u0010\bR!\u0010K\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bM\u0010\u0010*\u0004\bL\u0010\bR!\u0010N\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bP\u0010\u0010*\u0004\bO\u0010\bR\u001d\u0010Q\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bS\u00102*\u0004\bR\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010T\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bV\u0010\n*\u0004\bU\u0010\bR!\u0010W\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\bY\u0010\u0010*\u0004\bX\u0010\bR!\u0010Z\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b\\\u0010\u0010*\u0004\b[\u0010\bR\u001d\u0010]\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b_\u00102*\u0004\b^\u0010\b¨\u0006g"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DefFile$DefFileConfig;", "", "properties", "Ljava/util/Properties;", "(Ljava/util/Properties;)V", "allowIncludingObjCCategoriesFromDefFile", "", "getAllowIncludingObjCCategoriesFromDefFile$delegate", "(Lorg/jetbrains/kotlin/konan/util/DefFile$DefFileConfig;)Ljava/lang/Object;", "getAllowIncludingObjCCategoriesFromDefFile", "()Z", "allowedOverloadsForCFunctions", "", "", "getAllowedOverloadsForCFunctions$delegate", "getAllowedOverloadsForCFunctions", "()Ljava/util/List;", "compilerOpts", "getCompilerOpts$delegate", "getCompilerOpts", KotlinLibraryKt.KLIB_PROPERTY_DEPENDS, "getDepends$delegate", "getDepends", "disableDesignatedInitializerChecks", "getDisableDesignatedInitializerChecks$delegate", "getDisableDesignatedInitializerChecks", "entryPoints", "getEntryPoints$delegate", "getEntryPoints", "excludeDependentModules", "getExcludeDependentModules$delegate", "getExcludeDependentModules", "excludeFilter", "getExcludeFilter$delegate", "getExcludeFilter", "excludeSystemLibs", "getExcludeSystemLibs$delegate", "getExcludeSystemLibs", "excludedFunctions", "getExcludedFunctions$delegate", "getExcludedFunctions", "excludedMacros", "getExcludedMacros$delegate", "getExcludedMacros", KotlinLibraryKt.KLIB_PROPERTY_EXPORT_FORWARD_DECLARATIONS, "getExportForwardDeclarations$delegate", "getExportForwardDeclarations", "foreignExceptionMode", "getForeignExceptionMode$delegate", "getForeignExceptionMode", "()Ljava/lang/String;", "headerFilter", "getHeaderFilter$delegate", "getHeaderFilter", "headers", "getHeaders$delegate", "getHeaders", "language", "getLanguage$delegate", "getLanguage", "libraryPaths", "getLibraryPaths$delegate", "getLibraryPaths", "linker", "getLinker$delegate", "getLinker", KonanLibraryKt.KLIB_PROPERTY_LINKED_OPTS, "getLinkerOpts$delegate", "getLinkerOpts", "modules", "getModules$delegate", "getModules", "noStringConversion", "getNoStringConversion$delegate", "getNoStringConversion", "nonStrictEnums", "getNonStrictEnums$delegate", "getNonStrictEnums", "objcClassesIncludingCategories", "getObjcClassesIncludingCategories$delegate", "getObjcClassesIncludingCategories", "packageName", "getPackageName$delegate", "getPackageName", "skipNonImportableModules", "getSkipNonImportableModules$delegate", "getSkipNonImportableModules", "staticLibraries", "getStaticLibraries$delegate", "getStaticLibraries", "strictEnums", "getStrictEnums$delegate", "getStrictEnums", "userSetupHint", "getUserSetupHint$delegate", "getUserSetupHint", "getValue", "T", "Lorg/jetbrains/kotlin/konan/util/DefFileProperty;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/konan/util/DefFileProperty;Lorg/jetbrains/kotlin/konan/util/DefFile$DefFileConfig;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DefFileConfig {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "headers", "getHeaders()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "modules", "getModules()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "language", "getLanguage()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "compilerOpts", "getCompilerOpts()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "excludeSystemLibs", "getExcludeSystemLibs()Z", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "excludeDependentModules", "getExcludeDependentModules()Z", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "entryPoints", "getEntryPoints()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, KonanLibraryKt.KLIB_PROPERTY_LINKED_OPTS, "getLinkerOpts()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "linker", "getLinker()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "excludedFunctions", "getExcludedFunctions()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "excludedMacros", "getExcludedMacros()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "staticLibraries", "getStaticLibraries()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "libraryPaths", "getLibraryPaths()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "packageName", "getPackageName()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "headerFilter", "getHeaderFilter()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "excludeFilter", "getExcludeFilter()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "strictEnums", "getStrictEnums()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "nonStrictEnums", "getNonStrictEnums()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "noStringConversion", "getNoStringConversion()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, KotlinLibraryKt.KLIB_PROPERTY_DEPENDS, "getDepends()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, KotlinLibraryKt.KLIB_PROPERTY_EXPORT_FORWARD_DECLARATIONS, "getExportForwardDeclarations()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "allowedOverloadsForCFunctions", "getAllowedOverloadsForCFunctions()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "disableDesignatedInitializerChecks", "getDisableDesignatedInitializerChecks()Z", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "foreignExceptionMode", "getForeignExceptionMode()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "objcClassesIncludingCategories", "getObjcClassesIncludingCategories()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "allowIncludingObjCCategoriesFromDefFile", "getAllowIncludingObjCCategoriesFromDefFile()Z", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "userSetupHint", "getUserSetupHint()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(DefFileConfig.class, "skipNonImportableModules", "getSkipNonImportableModules()Z", 0))};
        private final Properties properties;

        public DefFileConfig(Properties properties) {
            properties.getClass();
            this.properties = properties;
            DefFileProperty.StringListProperty stringListProperty = DefFileProperty.StringListProperty.Headers;
            DefFileProperty.NullableStringProperty nullableStringProperty = DefFileProperty.NullableStringProperty.Language;
            DefFileProperty.BooleanProperty booleanProperty = DefFileProperty.BooleanProperty.ExcludeSystemLibs;
        }

        private final <T> T getValue(DefFileProperty<T> defFileProperty, DefFileConfig defFileConfig, KProperty<?> kProperty) {
            defFileProperty.getClass();
            return defFileProperty.parse(defFileConfig.properties.getProperty(defFileProperty.getPropertyName()));
        }

        public final boolean getAllowIncludingObjCCategoriesFromDefFile() {
            return ((Boolean) getValue(DefFileProperty.BooleanProperty.AllowIncludingObjCCategoriesFromDefFile, this, $$delegatedProperties[25])).booleanValue();
        }

        public final List<String> getAllowedOverloadsForCFunctions() {
            return (List) getValue(DefFileProperty.StringListProperty.AllowedOverloadsForCFunctions, this, $$delegatedProperties[21]);
        }

        public final List<String> getCompilerOpts() {
            return (List) getValue(DefFileProperty.StringListProperty.CompilerOpts, this, $$delegatedProperties[3]);
        }

        public final List<String> getDepends() {
            return (List) getValue(DefFileProperty.StringListProperty.Depends, this, $$delegatedProperties[19]);
        }

        public final boolean getDisableDesignatedInitializerChecks() {
            return ((Boolean) getValue(DefFileProperty.BooleanProperty.DisableDesignatedInitializerChecks, this, $$delegatedProperties[22])).booleanValue();
        }

        public final List<String> getEntryPoints() {
            return (List) getValue(DefFileProperty.StringListProperty.EntryPoints, this, $$delegatedProperties[6]);
        }

        public final boolean getExcludeDependentModules() {
            return ((Boolean) getValue(DefFileProperty.BooleanProperty.ExcludeDependentModules, this, $$delegatedProperties[5])).booleanValue();
        }

        public final List<String> getExcludeFilter() {
            return (List) getValue(DefFileProperty.StringListProperty.ExcludeFilter, this, $$delegatedProperties[15]);
        }

        public final boolean getExcludeSystemLibs() {
            return ((Boolean) getValue(DefFileProperty.BooleanProperty.ExcludeSystemLibs, this, $$delegatedProperties[4])).booleanValue();
        }

        public final List<String> getExcludedFunctions() {
            return (List) getValue(DefFileProperty.StringListProperty.ExcludedFunctions, this, $$delegatedProperties[9]);
        }

        public final List<String> getExcludedMacros() {
            return (List) getValue(DefFileProperty.StringListProperty.ExcludedMacros, this, $$delegatedProperties[10]);
        }

        public final List<String> getExportForwardDeclarations() {
            return (List) getValue(DefFileProperty.StringListProperty.ExportForwardDeclarations, this, $$delegatedProperties[20]);
        }

        public final String getForeignExceptionMode() {
            return (String) getValue(DefFileProperty.NullableStringProperty.ForeignExceptionMode, this, $$delegatedProperties[23]);
        }

        public final List<String> getHeaderFilter() {
            return (List) getValue(DefFileProperty.StringListProperty.HeaderFilter, this, $$delegatedProperties[14]);
        }

        public final List<String> getHeaders() {
            return (List) getValue(DefFileProperty.StringListProperty.Headers, this, $$delegatedProperties[0]);
        }

        public final String getLanguage() {
            return (String) getValue(DefFileProperty.NullableStringProperty.Language, this, $$delegatedProperties[2]);
        }

        public final List<String> getLibraryPaths() {
            return (List) getValue(DefFileProperty.StringListProperty.LibraryPaths, this, $$delegatedProperties[12]);
        }

        public final String getLinker() {
            return (String) getValue(DefFileProperty.NullableStringProperty.Linker, this, $$delegatedProperties[8]);
        }

        public final List<String> getLinkerOpts() {
            return (List) getValue(DefFileProperty.StringListProperty.LinkerOpts, this, $$delegatedProperties[7]);
        }

        public final List<String> getModules() {
            return (List) getValue(DefFileProperty.StringListProperty.Modules, this, $$delegatedProperties[1]);
        }

        public final List<String> getNoStringConversion() {
            return (List) getValue(DefFileProperty.StringListProperty.NoStringConversion, this, $$delegatedProperties[18]);
        }

        public final List<String> getNonStrictEnums() {
            return (List) getValue(DefFileProperty.StringListProperty.NonStrictEnums, this, $$delegatedProperties[17]);
        }

        public final List<String> getObjcClassesIncludingCategories() {
            return (List) getValue(DefFileProperty.StringListProperty.ObjcClassesIncludingCategories, this, $$delegatedProperties[24]);
        }

        public final String getPackageName() {
            return (String) getValue(DefFileProperty.NullableStringProperty.PackageName, this, $$delegatedProperties[13]);
        }

        public final boolean getSkipNonImportableModules() {
            return ((Boolean) getValue(DefFileProperty.BooleanProperty.SkipNonImportableModules, this, $$delegatedProperties[27])).booleanValue();
        }

        public final List<String> getStaticLibraries() {
            return (List) getValue(DefFileProperty.StringListProperty.StaticLibraries, this, $$delegatedProperties[11]);
        }

        public final List<String> getStrictEnums() {
            return (List) getValue(DefFileProperty.StringListProperty.StrictEnums, this, $$delegatedProperties[16]);
        }

        public final String getUserSetupHint() {
            return (String) getValue(DefFileProperty.NullableStringProperty.UserSetupHint, this, $$delegatedProperties[26]);
        }
    }

    public DefFile(File file, DefFileConfig defFileConfig, Properties properties, List<String> list) {
        defFileConfig.getClass();
        properties.getClass();
        list.getClass();
        this.file = file;
        this.config = defFileConfig;
        this.manifestAddendProperties = properties;
        this.defHeaderLines = list;
        this.name = LazyKt.lazy(new Function0<String>() { // from class: org.jetbrains.kotlin.konan.util.DefFile$name$2
            {
                super(0);
            }

            public final String invoke() {
                String nameWithoutExtension;
                File file2 = this.this$0.getFile();
                return (file2 == null || (nameWithoutExtension = FilesKt.getNameWithoutExtension(file2)) == null) ? "" : nameWithoutExtension;
            }
        });
    }

    public final DefFileConfig getConfig() {
        return this.config;
    }

    public final List<String> getDefHeaderLines() {
        return this.defHeaderLines;
    }

    public final File getFile() {
        return this.file;
    }

    public final Properties getManifestAddendProperties() {
        return this.manifestAddendProperties;
    }

    public final String getName() {
        return (String) this.name.getValue();
    }
}

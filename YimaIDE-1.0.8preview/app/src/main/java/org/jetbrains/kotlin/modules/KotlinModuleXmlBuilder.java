package org.jetbrains.kotlin.modules;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.jetbrains.kotlin.build.JvmSourceRoot;
import org.jetbrains.kotlin.config.IncrementalCompilation;
import org.jetbrains.kotlin.konan.library.NativeLibraryConstantsKt;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;
import org.jetbrains.kotlin.modules.KotlinModuleXmlBuilder;
import org.jetbrains.kotlin.utils.Printer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0086\u0001\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0007J\u008c\u0001\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001c\u001a\u00020\nJ,\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a2\u0006\u0010\u001c\u001a\u00020\nH\u0002J\u0016\u0010 \u001a\u00020\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010%\u001a\u00020\rH\u0002J\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010%\u001a\u00020\rH\u0002J\u0010\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u0011H\u0002R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/modules/KotlinModuleXmlBuilder;", "", "<init>", "()V", "xml", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "p", "Lorg/jetbrains/kotlin/utils/Printer;", "done", "", "addModule", "moduleName", "", "outputDir", "sourceFiles", "", "Ljava/io/File;", "javaSourceRoots", "Lorg/jetbrains/kotlin/build/JvmSourceRoot;", "classpathRoots", "commonSourceFiles", "modularJdkRoot", "targetTypeId", "isTests", "directoriesToFilterOut", "", "friendDirs", "isIncrementalCompilation", "processClasspath", "", "files", "processJavaSourceRoots", "roots", "asText", "", "openTag", "tag", "closeTag", "getEscapedPath", "sourceFile", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KotlinModuleXmlBuilder {
    private static final Companion Companion = new Companion(null);
    private static final Regex xmlEscapeRegex;
    private static final Map<String, String> xmlEscapeReplacement;
    private boolean done;
    private final Printer p;
    private final StringBuilder xml;

    static {
        Map<String, String> mapMapOf = MapsKt.mapOf(new Pair[]{TuplesKt.to("<", "&lt;"), TuplesKt.to(">", "&gt;"), TuplesKt.to("&", "&amp;"), TuplesKt.to("'", "&#39;"), TuplesKt.to("\"", "&quot;")});
        xmlEscapeReplacement = mapMapOf;
        xmlEscapeRegex = new Regex(CollectionsKt.joinToString$default(mapMapOf.keySet(), "|", "(?:", ")", 0, (CharSequence) null, new Function1() { // from class: xc8
            public final Object invoke(Object obj) {
                return KotlinModuleXmlBuilder.a((String) obj);
            }
        }, 24, (Object) null));
    }

    public KotlinModuleXmlBuilder() {
        StringBuilder sb = new StringBuilder();
        this.xml = sb;
        Printer printer = new Printer(sb, 0, (String) null, 6, (DefaultConstructorMarker) null);
        this.p = printer;
        openTag(printer, "modules");
    }

    public static CharSequence a(String str) {
        str.getClass();
        return Regex.Companion.escape(str);
    }

    private final void closeTag(Printer p, String tag) {
        p.popIndent();
        p.println(new Object[]{"</" + tag + '>'});
    }

    private final String getEscapedPath(File sourceFile) {
        return Companion.escapeXml(FilesKt.getInvariantSeparatorsPath(sourceFile));
    }

    private final void openTag(Printer p, String tag) {
        p.println(new Object[]{"<" + tag + '>'});
        p.pushIndent();
    }

    private final void processClasspath(Iterable<? extends File> files, Set<? extends File> directoriesToFilterOut, boolean isIncrementalCompilation) {
        this.p.println(new Object[]{"<!-- Classpath -->"});
        for (File file : files) {
            boolean z = directoriesToFilterOut.contains(file) && !isIncrementalCompilation;
            if (z) {
                this.p.println(new Object[]{"<!-- Output directory, commented out -->"});
                this.p.println(new Object[]{"<!-- "});
                this.p.pushIndent();
            }
            this.p.println(new Object[]{"<", "classpath", " ", "path", "=\"", getEscapedPath(file), "\"/>"});
            if (z) {
                this.p.popIndent();
                this.p.println(new Object[]{"-->"});
            }
        }
    }

    private final void processJavaSourceRoots(Iterable<JvmSourceRoot> roots) {
        this.p.println(new Object[]{"<!-- Java source roots -->"});
        for (JvmSourceRoot jvmSourceRoot : roots) {
            this.p.print(new Object[]{"<"});
            this.p.printWithNoIndent(new Object[]{"javaSourceRoots", " ", "path", "=\"", getEscapedPath(jvmSourceRoot.getFile()), "\""});
            if (jvmSourceRoot.getPackagePrefix() != null) {
                this.p.printWithNoIndent(new Object[]{" ", "packagePrefix", "=\"", jvmSourceRoot.getPackagePrefix(), "\""});
            }
            this.p.printWithNoIndent(new Object[]{"/>"});
            this.p.println(new Object[0]);
        }
    }

    public final KotlinModuleXmlBuilder addModule(String moduleName, String outputDir, Iterable<? extends File> sourceFiles, Iterable<JvmSourceRoot> javaSourceRoots, Iterable<? extends File> classpathRoots, Iterable<? extends File> commonSourceFiles, File modularJdkRoot, String targetTypeId, boolean isTests, Set<? extends File> directoriesToFilterOut, Iterable<? extends File> friendDirs, boolean isIncrementalCompilation) {
        moduleName.getClass();
        outputDir.getClass();
        sourceFiles.getClass();
        javaSourceRoots.getClass();
        classpathRoots.getClass();
        commonSourceFiles.getClass();
        targetTypeId.getClass();
        directoriesToFilterOut.getClass();
        friendDirs.getClass();
        Printer printer = this.p;
        StringBuilder sb = new StringBuilder("<!-- Module script for ");
        sb.append(isTests ? "tests" : "production");
        sb.append(" -->");
        printer.println(new Object[]{sb.toString()});
        Printer printer2 = this.p;
        Companion companion = Companion;
        printer2.println(new Object[]{"<", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, " ", "name", "=\"", companion.escapeXml(moduleName), "\" ", "type", "=\"", companion.escapeXml(targetTypeId), "\" ", "outputDir", "=\"", getEscapedPath(new File(outputDir)), "\">"});
        this.p.pushIndent();
        Iterator<? extends File> it = friendDirs.iterator();
        while (it.hasNext()) {
            this.p.println(new Object[]{"<", "friendDir", " ", "path", "=\"", getEscapedPath(it.next()), "\"/>"});
        }
        Iterator<? extends File> it2 = sourceFiles.iterator();
        while (it2.hasNext()) {
            this.p.println(new Object[]{"<", NativeLibraryConstantsKt.KONAN_DISTRIBUTION_SOURCES_DIR, " ", "path", "=\"", getEscapedPath(it2.next()), "\"/>"});
        }
        Iterator<? extends File> it3 = commonSourceFiles.iterator();
        while (it3.hasNext()) {
            this.p.println(new Object[]{"<", "commonSources", " ", "path", "=\"", getEscapedPath(it3.next()), "\"/>"});
        }
        processJavaSourceRoots(javaSourceRoots);
        processClasspath(classpathRoots, directoriesToFilterOut, isIncrementalCompilation);
        if (modularJdkRoot != null) {
            this.p.println(new Object[]{"<", "modularJdkRoot", " ", "path", "=\"", getEscapedPath(modularJdkRoot), "\"/>"});
        }
        closeTag(this.p, KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME);
        return this;
    }

    public final CharSequence asText() {
        if (!this.done) {
            closeTag(this.p, "modules");
            this.done = true;
        }
        return this.xml;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/modules/KotlinModuleXmlBuilder$Companion;", "", "<init>", "()V", "xmlEscapeReplacement", "", "", "xmlEscapeRegex", "Lkotlin/text/Regex;", "escapeXml", "string", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static CharSequence a(MatchResult matchResult) {
            matchResult.getClass();
            return (CharSequence) MapsKt.getValue(KotlinModuleXmlBuilder.xmlEscapeReplacement, matchResult.getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String escapeXml(String string) {
            return KotlinModuleXmlBuilder.xmlEscapeRegex.replace(string, new Function1() { // from class: org.jetbrains.kotlin.modules.a
                public final Object invoke(Object obj) {
                    return KotlinModuleXmlBuilder.Companion.a((MatchResult) obj);
                }
            });
        }

        private Companion() {
        }
    }

    @Deprecated(message = "State of IC should be set explicitly")
    public final KotlinModuleXmlBuilder addModule(String moduleName, String outputDir, Iterable<? extends File> sourceFiles, Iterable<JvmSourceRoot> javaSourceRoots, Iterable<? extends File> classpathRoots, Iterable<? extends File> commonSourceFiles, File modularJdkRoot, String targetTypeId, boolean isTests, Set<? extends File> directoriesToFilterOut, Iterable<? extends File> friendDirs) {
        moduleName.getClass();
        outputDir.getClass();
        sourceFiles.getClass();
        javaSourceRoots.getClass();
        classpathRoots.getClass();
        commonSourceFiles.getClass();
        targetTypeId.getClass();
        directoriesToFilterOut.getClass();
        friendDirs.getClass();
        return addModule(moduleName, outputDir, sourceFiles, javaSourceRoots, classpathRoots, commonSourceFiles, modularJdkRoot, targetTypeId, isTests, directoriesToFilterOut, friendDirs, IncrementalCompilation.isEnabledForJvm());
    }
}

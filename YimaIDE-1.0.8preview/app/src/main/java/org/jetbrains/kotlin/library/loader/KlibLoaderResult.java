package org.jetbrains.kotlin.library.loader;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.library.KotlinAbiVersion;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.KotlinLibraryVersioning;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0003\u000f\u0010\u0011B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult;", "", "librariesStdlibFirst", "", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "problematicLibraries", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblematicLibrary;", "(Ljava/util/List;Ljava/util/List;)V", "hasProblems", "", "getHasProblems", "()Z", "getLibrariesStdlibFirst", "()Ljava/util/List;", "getProblematicLibraries", "ProblemCase", "ProblemSeverity", "ProblematicLibrary", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KlibLoaderResult {
    private final List<KotlinLibrary> librariesStdlibFirst;
    private final List<ProblematicLibrary> problematicLibraries;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblematicLibrary;", "", "libraryPath", "", "problemCase", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;", "(Ljava/lang/String;Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;)V", "getLibraryPath", "()Ljava/lang/String;", "getProblemCase", "()Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ProblematicLibrary {
        private final String libraryPath;
        private final ProblemCase problemCase;

        public ProblematicLibrary(String str, ProblemCase problemCase) {
            str.getClass();
            problemCase.getClass();
            this.libraryPath = str;
            this.problemCase = problemCase;
        }

        public final String getLibraryPath() {
            return this.libraryPath;
        }

        public final ProblemCase getProblemCase() {
            return this.problemCase;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public KlibLoaderResult(List<? extends KotlinLibrary> list, List<ProblematicLibrary> list2) {
        list.getClass();
        list2.getClass();
        this.librariesStdlibFirst = list;
        this.problematicLibraries = list2;
    }

    public final boolean getHasProblems() {
        return !this.problematicLibraries.isEmpty();
    }

    public final List<KotlinLibrary> getLibrariesStdlibFirst() {
        return this.librariesStdlibFirst;
    }

    public final List<ProblematicLibrary> getProblematicLibraries() {
        return this.problematicLibraries;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0006\u0006\u0007\b\t\n\u000bR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0003\f\r\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;", "", "defaultSeverity", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "getDefaultSeverity", "()Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "ExistingKlibProblem", "IncompatibleAbiVersion", "InvalidLibraryFormat", "LibraryNotFound", "OtherCheckMismatch", "PlatformCheckMismatch", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$ExistingKlibProblem;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$InvalidLibraryFormat;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$LibraryNotFound;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ProblemCase {

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$IncompatibleAbiVersion;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$ExistingKlibProblem;", "libraryVersions", "Lorg/jetbrains/kotlin/library/KotlinLibraryVersioning;", "minPermittedAbiVersion", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "maxPermittedAbiVersion", "(Lorg/jetbrains/kotlin/library/KotlinLibraryVersioning;Lorg/jetbrains/kotlin/library/KotlinAbiVersion;Lorg/jetbrains/kotlin/library/KotlinAbiVersion;)V", "getLibraryVersions", "()Lorg/jetbrains/kotlin/library/KotlinLibraryVersioning;", "getMaxPermittedAbiVersion", "()Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "getMinPermittedAbiVersion", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class IncompatibleAbiVersion extends ExistingKlibProblem {
            private final KotlinLibraryVersioning libraryVersions;
            private final KotlinAbiVersion maxPermittedAbiVersion;
            private final KotlinAbiVersion minPermittedAbiVersion;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public IncompatibleAbiVersion(KotlinLibraryVersioning kotlinLibraryVersioning, KotlinAbiVersion kotlinAbiVersion, KotlinAbiVersion kotlinAbiVersion2) {
                super(null);
                kotlinLibraryVersioning.getClass();
                this.libraryVersions = kotlinLibraryVersioning;
                this.minPermittedAbiVersion = kotlinAbiVersion;
                this.maxPermittedAbiVersion = kotlinAbiVersion2;
            }

            public final KotlinLibraryVersioning getLibraryVersions() {
                return this.libraryVersions;
            }

            public final KotlinAbiVersion getMaxPermittedAbiVersion() {
                return this.maxPermittedAbiVersion;
            }

            public final KotlinAbiVersion getMinPermittedAbiVersion() {
                return this.minPermittedAbiVersion;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$InvalidLibraryFormat;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;", "()V", "defaultSeverity", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "getDefaultSeverity", "()Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class InvalidLibraryFormat implements ProblemCase {
            public static final InvalidLibraryFormat INSTANCE = new InvalidLibraryFormat();

            private InvalidLibraryFormat() {
            }

            @Override // org.jetbrains.kotlin.library.loader.KlibLoaderResult.ProblemCase
            public ProblemSeverity getDefaultSeverity() {
                return ProblemSeverity.INFO;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$LibraryNotFound;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;", "()V", "defaultSeverity", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "getDefaultSeverity", "()Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class LibraryNotFound implements ProblemCase {
            public static final LibraryNotFound INSTANCE = new LibraryNotFound();

            private LibraryNotFound() {
            }

            @Override // org.jetbrains.kotlin.library.loader.KlibLoaderResult.ProblemCase
            public ProblemSeverity getDefaultSeverity() {
                return ProblemSeverity.INFO;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$OtherCheckMismatch;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$ExistingKlibProblem;", "()V", "caption", "", "getCaption", "()Ljava/lang/String;", "details", "getDetails", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static abstract class OtherCheckMismatch extends ExistingKlibProblem {
            public OtherCheckMismatch() {
                super(null);
            }

            public abstract String getCaption();

            public abstract String getDetails();
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$PlatformCheckMismatch;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$ExistingKlibProblem;", "property", "", "expected", "actual", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActual", "()Ljava/lang/String;", "getExpected", "getProperty", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class PlatformCheckMismatch extends ExistingKlibProblem {
            private final String actual;
            private final String expected;
            private final String property;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PlatformCheckMismatch(String str, String str2, String str3) {
                super(null);
                str.getClass();
                str2.getClass();
                str3.getClass();
                this.property = str;
                this.expected = str2;
                this.actual = str3;
            }

            public final String getActual() {
                return this.actual;
            }

            public final String getExpected() {
                return this.expected;
            }

            public final String getProperty() {
                return this.property;
            }
        }

        ProblemSeverity getDefaultSeverity();

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$ExistingKlibProblem;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase;", "()V", "defaultSeverity", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "getDefaultSeverity", "()Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemSeverity;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$IncompatibleAbiVersion;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$OtherCheckMismatch;", "Lorg/jetbrains/kotlin/library/loader/KlibLoaderResult$ProblemCase$PlatformCheckMismatch;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static abstract class ExistingKlibProblem implements ProblemCase {
            public /* synthetic */ ExistingKlibProblem(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Override // org.jetbrains.kotlin.library.loader.KlibLoaderResult.ProblemCase
            public final ProblemSeverity getDefaultSeverity() {
                return ProblemSeverity.WARNING;
            }

            private ExistingKlibProblem() {
            }
        }
    }
}

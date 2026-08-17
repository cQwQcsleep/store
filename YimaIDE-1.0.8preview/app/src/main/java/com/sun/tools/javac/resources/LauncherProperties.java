package com.sun.tools.javac.resources;

import com.sun.tools.javac.util.JCDiagnostic;
import java.nio.file.Path;
import java.util.EnumSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LauncherProperties {

    public static class Errors {
        public static final JCDiagnostic.Error CompilationFailed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "compilation.failed", new Object[0]);
        public static final JCDiagnostic.Error MainNotPublicStatic = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "main.not.public.static", new Object[0]);
        public static final JCDiagnostic.Error MainNotVoid = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "main.not.void", new Object[0]);
        public static final JCDiagnostic.Error NoArgs = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "no.args", new Object[0]);
        public static final JCDiagnostic.Error NoClass = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "no.class", new Object[0]);
        public static final JCDiagnostic.Error UnnamedPkgNotAllowedNamedModules = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "unnamed.pkg.not.allowed.named.modules", new Object[0]);

        public static JCDiagnostic.Error CantAccessConstructor(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.access.constructor", str);
        }

        public static JCDiagnostic.Error CantAccessMainMethod(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.access.main.method", str);
        }

        public static JCDiagnostic.Error CantFindClass(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.find.class", str);
        }

        public static JCDiagnostic.Error CantFindConstructor(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.find.constructor", str);
        }

        public static JCDiagnostic.Error CantFindMainMethod(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.find.main.method", str);
        }

        public static JCDiagnostic.Error CantInstantiate(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.instantiate", str);
        }

        public static JCDiagnostic.Error CantReadFile(Path path, Object obj) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.read.file", path, obj);
        }

        public static JCDiagnostic.Error CantUsePrivateConstructor(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "cant.use.private.constructor", str);
        }

        public static JCDiagnostic.Error FileNotFound(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "file.not.found", path);
        }

        public static JCDiagnostic.Error InvalidFilename(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "invalid.filename", str);
        }

        public static JCDiagnostic.Error InvalidValueForSource(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "invalid.value.for.source", str);
        }

        public static JCDiagnostic.Error MismatchEndOfPathAndPackageName(String str, Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "mismatch.end.of.path.and.package.name", str, path);
        }

        public static JCDiagnostic.Error NoValueForOption(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "launcher", "no.value.for.option", str);
        }
    }
}

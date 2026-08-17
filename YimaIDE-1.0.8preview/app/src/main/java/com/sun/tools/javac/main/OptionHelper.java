package com.sun.tools.javac.main;

import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Log;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class OptionHelper {

    public static class GrumpyHelper extends OptionHelper {
        private final Log log;

        public GrumpyHelper(Log log) {
            this.log = log;
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void addClassName(String str) {
            throw new IllegalArgumentException(str);
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void addFile(Path path) {
            throw new IllegalArgumentException(path.toString());
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public String get(Option option) {
            throw new IllegalArgumentException();
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public Log getLog() {
            return this.log;
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public String getOwnName() {
            throw new IllegalStateException();
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public boolean handleFileManagerOption(Option option, String str) {
            throw new IllegalArgumentException();
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void initialize() {
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void put(String str, String str2) {
            throw new IllegalArgumentException();
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void remove(String str) {
            throw new IllegalArgumentException();
        }
    }

    public abstract void addClassName(String str);

    public abstract void addFile(Path path);

    public abstract String get(Option option);

    public abstract Log getLog();

    public abstract String getOwnName();

    public abstract boolean handleFileManagerOption(Option option, String str);

    public abstract void initialize();

    public Option.InvalidValueException newInvalidValueException(JCDiagnostic.Error error) {
        return new Option.InvalidValueException(getLog().localize(error));
    }

    public abstract void put(String str, String str2);

    public abstract void remove(String str);
}

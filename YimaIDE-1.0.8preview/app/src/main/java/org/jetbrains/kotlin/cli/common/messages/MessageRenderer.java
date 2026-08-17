package org.jetbrains.kotlin.cli.common.messages;

import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import org.jetbrains.kotlin.utils.fileUtils.FileUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface MessageRenderer {
    public static final String PROPERTY_KEY = "org.jetbrains.kotlin.cliMessageRenderer";
    public static final MessageRenderer XML = new XmlMessageRenderer();
    public static final MessageRenderer WITHOUT_PATHS = new PlainTextMessageRenderer() { // from class: org.jetbrains.kotlin.cli.common.messages.MessageRenderer.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "org/jetbrains/kotlin/cli/common/messages/MessageRenderer$1", "getPath"));
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
        public String getName() {
            return "Pathless";
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.PlainTextMessageRenderer
        public String getPath(CompilerMessageSourceLocation compilerMessageSourceLocation) {
            if (compilerMessageSourceLocation != null) {
                return null;
            }
            $$$reportNull$$$0(0);
            return null;
        }
    };
    public static final MessageRenderer PLAIN_FULL_PATHS = new PlainTextMessageRenderer() { // from class: org.jetbrains.kotlin.cli.common.messages.MessageRenderer.2
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "location";
            } else {
                objArr[0] = "org/jetbrains/kotlin/cli/common/messages/MessageRenderer$2";
            }
            if (i != 1) {
                objArr[1] = "org/jetbrains/kotlin/cli/common/messages/MessageRenderer$2";
            } else {
                objArr[1] = "getPath";
            }
            if (i != 1) {
                objArr[2] = "getPath";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
        public String getName() {
            return "FullPath";
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.PlainTextMessageRenderer
        public String getPath(CompilerMessageSourceLocation compilerMessageSourceLocation) {
            if (compilerMessageSourceLocation == null) {
                $$$reportNull$$$0(0);
            }
            String path = compilerMessageSourceLocation.getPath();
            if (path == null) {
                $$$reportNull$$$0(1);
            }
            return path;
        }
    };
    public static final MessageRenderer PLAIN_RELATIVE_PATHS = new PlainTextMessageRenderer() { // from class: org.jetbrains.kotlin.cli.common.messages.MessageRenderer.3
        private final File cwd = new File(".").getAbsoluteFile();

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "location";
            } else {
                objArr[0] = "org/jetbrains/kotlin/cli/common/messages/MessageRenderer$3";
            }
            if (i != 1) {
                objArr[1] = "org/jetbrains/kotlin/cli/common/messages/MessageRenderer$3";
            } else {
                objArr[1] = "getPath";
            }
            if (i != 1) {
                objArr[2] = "getPath";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
        public String getName() {
            return "RelativePath";
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.PlainTextMessageRenderer
        public String getPath(CompilerMessageSourceLocation compilerMessageSourceLocation) {
            if (compilerMessageSourceLocation == null) {
                $$$reportNull$$$0(0);
            }
            String path = FileUtilsKt.descendantRelativeTo(new File(compilerMessageSourceLocation.getPath()), this.cwd).getPath();
            if (path == null) {
                $$$reportNull$$$0(1);
            }
            return path;
        }
    };
    public static final MessageRenderer SYSTEM_INDEPENDENT_RELATIVE_PATHS = new PlainTextMessageRenderer() { // from class: org.jetbrains.kotlin.cli.common.messages.MessageRenderer.4
        private final File cwd = new File(".").getAbsoluteFile();

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "org/jetbrains/kotlin/cli/common/messages/MessageRenderer$4", "getPath"));
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
        public String getName() {
            return "SystemIndependentRelativePath";
        }

        @Override // org.jetbrains.kotlin.cli.common.messages.PlainTextMessageRenderer
        public String getPath(CompilerMessageSourceLocation compilerMessageSourceLocation) {
            if (compilerMessageSourceLocation == null) {
                $$$reportNull$$$0(0);
            }
            return FileUtil.toSystemIndependentName(FileUtilsKt.descendantRelativeTo(new File(compilerMessageSourceLocation.getPath()), this.cwd).getPath());
        }
    };
    public static final MessageRenderer GRADLE_STYLE = new GradleStyleMessageRenderer();
    public static final MessageRenderer XCODE_STYLE = new XcodeStyleMessageRenderer();

    String getName();

    String render(CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation);

    String renderConclusion();

    String renderPreamble();

    String renderUsage(String str);
}

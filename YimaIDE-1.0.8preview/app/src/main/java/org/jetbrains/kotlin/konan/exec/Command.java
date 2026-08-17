package org.jetbrains.kotlin.konan.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.konan.KonanExternalToolFailure;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001:\u0001/B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0007B\u001f\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u001f\u001a\u00020\u0017H\u0016J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010!\u001a\u00020\"J\u0018\u0010#\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"J \u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0002J\b\u0010*\u001a\u00020\u0017H\u0002J \u0010+\u001a\u00020\u00002\u0018\u0010,\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0012\u0004\u0012\u00020\u00170\u0015J\b\u0010-\u001a\u00020(H\u0016J\r\u0010.\u001a\u00020\u0000*\u00020\u0003H\u0086\u0002J\u0013\u0010.\u001a\u00020\u0000*\b\u0012\u0004\u0012\u00020\u00030\tH\u0086\u0002R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\t8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR.\u0010\u0014\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/konan/exec/Command;", "", "tool", "", "(Ljava/lang/String;)V", "command", "", "([Ljava/lang/String;)V", "initialCommand", "", "redirectInputFile", "Ljava/io/File;", "(Ljava/util/List;Ljava/io/File;)V", "args", "getArgs", "()Ljava/util/List;", "argsWithExecutable", "getArgsWithExecutable", "", "getCommand", "logger", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "getLogger", "()Lkotlin/jvm/functions/Function1;", "setLogger", "(Lkotlin/jvm/functions/Function1;)V", "getRedirectInputFile", "()Ljava/io/File;", "stdError", "execute", "getOutputLines", "withErrors", "", "getResult", "Lorg/jetbrains/kotlin/konan/exec/Command$Result;", "handleError", "handleExitCode", "code", "", "output", "log", "logWith", "newLogger", "runProcess", "unaryPlus", "Result", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class Command {
    private final List<String> argsWithExecutable;
    private final List<String> command;
    private Function1<? super Function0<String>, Unit> logger;
    private final File redirectInputFile;
    private List<String> stdError;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/konan/exec/Command$Result;", "", "exitCode", "", "outputLines", "", "", "(ILjava/util/List;)V", "getExitCode", "()I", "getOutputLines", "()Ljava/util/List;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Result {
        private final int exitCode;
        private final List<String> outputLines;

        public Result(int i, List<String> list) {
            list.getClass();
            this.exitCode = i;
            this.outputLines = list;
        }

        public final int getExitCode() {
            return this.exitCode;
        }

        public final List<String> getOutputLines() {
            return this.outputLines;
        }
    }

    public Command(List<String> list, File file) {
        list.getClass();
        this.redirectInputFile = file;
        List<String> mutableList = CollectionsKt.toMutableList(list);
        this.command = mutableList;
        this.argsWithExecutable = mutableList;
        this.stdError = CollectionsKt.emptyList();
    }

    public static /* synthetic */ List getOutputLines$default(Command command, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getOutputLines");
            return null;
        }
        if ((i & 1) != 0) {
            z = false;
        }
        return command.getOutputLines(z);
    }

    public static /* synthetic */ Result getResult$default(Command command, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getResult");
            return null;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        return command.getResult(z, z2);
    }

    private final void handleExitCode(int code, List<String> output) throws KonanExternalToolFailure {
        Unit unit;
        if (code != 0) {
            throw new KonanExternalToolFailure(StringsKt.trimIndent("\n            The " + this.command.get(0) + " command returned non-zero exit code: " + code + ".\n            output:\n            ") + '\n' + CollectionsKt.joinToString$default(output, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), this.command.get(0), null, 4, null);
        }
        if (this.stdError.isEmpty()) {
            return;
        }
        final String strJoinToString$default = CollectionsKt.joinToString$default(this.stdError, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        Function1<? super Function0<String>, Unit> function1 = this.logger;
        if (function1 != null) {
            function1.invoke(new Function0<String>() { // from class: org.jetbrains.kotlin.konan.exec.Command$handleExitCode$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                public final String invoke() {
                    return strJoinToString$default;
                }
            });
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            System.out.println((Object) strJoinToString$default);
        }
    }

    private final void log() {
        Function1<? super Function0<String>, Unit> function1 = this.logger;
        if (function1 != null) {
            function1.getClass();
            function1.invoke(new Function0<String>() { // from class: org.jetbrains.kotlin.konan.exec.Command.log.1
                {
                    super(0);
                }

                public final String invoke() {
                    return CollectionsKt.joinToString$default(Command.this.getCommand(), " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
                }
            });
        }
    }

    public void execute() throws KonanExternalToolFailure {
        log();
        handleExitCode(runProcess(), this.stdError);
    }

    public final List<String> getArgs() {
        return CollectionsKt.drop(this.command, 1);
    }

    public final List<String> getArgsWithExecutable() {
        return this.argsWithExecutable;
    }

    public final List<String> getCommand() {
        return this.command;
    }

    public final Function1<Function0<String>, Unit> getLogger() {
        return this.logger;
    }

    public final List<String> getOutputLines(boolean withErrors) {
        return getResult(withErrors, true).getOutputLines();
    }

    public final File getRedirectInputFile() {
        return this.redirectInputFile;
    }

    public final Result getResult(boolean withErrors, boolean handleError) {
        log();
        File file = Files.createTempFile(null, null, new FileAttribute[0]).toFile();
        file.deleteOnExit();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(this.command);
            File file2 = this.redirectInputFile;
            if (file2 == null) {
                processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
            } else {
                processBuilder.redirectInput(file2);
            }
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.to(file)).redirectErrorStream(withErrors);
            int iWaitFor = processBuilder.start().waitFor();
            if (handleError) {
                handleExitCode(iWaitFor, FilesKt.readLines$default(file, (Charset) null, 1, (Object) null));
            }
            return new Result(iWaitFor, FilesKt.readLines$default(file, (Charset) null, 1, (Object) null));
        } finally {
            file.delete();
        }
    }

    public final Command logWith(Function1<? super Function0<String>, Unit> newLogger) {
        newLogger.getClass();
        this.logger = newLogger;
        return this;
    }

    public int runProcess() throws IOException {
        this.stdError = CollectionsKt.emptyList();
        ProcessBuilder processBuilder = new ProcessBuilder(this.command);
        ProcessBuilder.Redirect redirect = ProcessBuilder.Redirect.INHERIT;
        processBuilder.redirectOutput(redirect);
        File file = this.redirectInputFile;
        if (file == null) {
            processBuilder.redirectInput(redirect);
        } else {
            processBuilder.redirectInput(file);
        }
        Process processStart = processBuilder.start();
        this.stdError = TextStreamsKt.readLines(new BufferedReader(new InputStreamReader(processStart.getErrorStream())));
        return processStart.waitFor();
    }

    public final void setLogger(Function1<? super Function0<String>, Unit> function1) {
        this.logger = function1;
    }

    public final Command unaryPlus(String str) {
        str.getClass();
        this.command.add(str);
        return this;
    }

    public final Command unaryPlus(List<String> list) {
        list.getClass();
        this.command.addAll(list);
        return this;
    }

    public /* synthetic */ Command(List list, File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : file);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Command(String str) {
        str.getClass();
        File file = null;
        this(CollectionsKt.listOf(str), file, 2, file);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Command(String... strArr) {
        strArr.getClass();
        File file = null;
        this(ArraysKt.toList(strArr), file, 2, file);
    }
}

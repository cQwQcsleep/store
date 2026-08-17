package org.jetbrains.kotlin.cli.common.profiling;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/profiling/ProfilingCompilerPerformanceManager;", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "profilerPath", Argument.Delimiters.none, "command", ModuleXmlParser.OUTPUT_DIR, "Ljava/io/File;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V", "getCommand", "()Ljava/lang/String;", "getOutputDir", "()Ljava/io/File;", "profiler", "Lorg/jetbrains/kotlin/cli/common/profiling/AsyncProfilerReflected;", "runDate", "Ljava/util/Date;", "formatter", "Ljava/text/SimpleDateFormat;", "active", Argument.Delimiters.none, "startProfiling", Argument.Delimiters.none, "stopProfiling", "restartProfiling", "dumpProfile", "postfix", "notifyCompilationFinished", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ProfilingCompilerPerformanceManager extends PerformanceManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean active;
    private final String command;
    private final SimpleDateFormat formatter;
    private final File outputDir;
    private final AsyncProfilerReflected profiler;
    private final Date runDate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilingCompilerPerformanceManager(String str, String str2, File file) {
        super(JvmPlatforms.INSTANCE.getDefaultJvmPlatform(), "Profiling");
        str.getClass();
        str2.getClass();
        file.getClass();
        this.command = str2;
        this.outputDir = file;
        this.profiler = AsyncProfilerHelper.INSTANCE.getInstance(str);
        this.runDate = new Date();
        this.formatter = new SimpleDateFormat("yyyy-MM-dd__HH-mm");
        startProfiling();
    }

    private final void dumpProfile(String postfix) {
        this.outputDir.mkdirs();
        File fileResolve = FilesKt.resolve(this.outputDir, "snapshot-" + this.formatter.format(this.runDate) + '-' + postfix + ".collapsed");
        String strExecute = this.profiler.execute("collapsed");
        FileOutputStream fileOutputStream = new FileOutputStream(fileResolve);
        try {
            Iterator it = StringsKt.chunkedSequence(strExecute, 1048576).iterator();
            while (it.hasNext()) {
                byte[] bytes = ((String) it.next()).getBytes(Charsets.UTF_8);
                bytes.getClass();
                fileOutputStream.write(bytes);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
            this.active = false;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    private final void startProfiling() {
        this.profiler.execute(this.command);
        this.active = true;
    }

    private final void stopProfiling() {
        if (this.active) {
            this.profiler.stop();
        }
        this.active = false;
    }

    public final String getCommand() {
        return this.command;
    }

    public final File getOutputDir() {
        return this.outputDir;
    }

    public void notifyCompilationFinished() {
        dumpProfile("final");
        stopProfiling();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/profiling/ProfilingCompilerPerformanceManager$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/cli/common/profiling/ProfilingCompilerPerformanceManager;", "profileCompilerArgument", Argument.Delimiters.none, "detailedPerf", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ProfilingCompilerPerformanceManager create(String profileCompilerArgument, boolean detailedPerf) {
            profileCompilerArgument.getClass();
            String str = File.pathSeparator;
            str.getClass();
            List listSplit$default = StringsKt.split$default(profileCompilerArgument, new String[]{str}, false, 3, 2, (Object) null);
            ProfilingCompilerPerformanceManager profilingCompilerPerformanceManager = new ProfilingCompilerPerformanceManager((String) listSplit$default.get(0), (String) listSplit$default.get(1), new File((String) listSplit$default.get(2)));
            profilingCompilerPerformanceManager.setDetailedPerf(detailedPerf);
            return profilingCompilerPerformanceManager;
        }

        private Companion() {
        }
    }
}

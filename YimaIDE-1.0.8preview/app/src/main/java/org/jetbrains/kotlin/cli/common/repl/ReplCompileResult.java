package org.jetbrains.kotlin.cli.common.repl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult;", "Ljava/io/Serializable;", "<init>", "()V", "CompiledClasses", "Incomplete", "Error", "Companion", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$Error;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$Incomplete;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ReplCompileResult implements Serializable {
    private static final long serialVersionUID = 8228307678L;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0011\u0018\u0000 !2\u00020\u0001:\u0001!B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$CompiledClasses;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult;", "lineId", "Lorg/jetbrains/kotlin/cli/common/repl/LineId;", "previousLines", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "mainClassName", Argument.Delimiters.none, "classes", "Lorg/jetbrains/kotlin/cli/common/repl/CompiledClassData;", "hasResult", Argument.Delimiters.none, "classpathAddendum", "Ljava/io/File;", ModuleXmlParser.TYPE, "data", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/LineId;Ljava/util/List;Ljava/lang/String;Ljava/util/List;ZLjava/util/List;Ljava/lang/String;Ljava/lang/Object;)V", "getLineId", "()Lorg/jetbrains/kotlin/cli/common/repl/LineId;", "getPreviousLines", "()Ljava/util/List;", "getMainClassName", "()Ljava/lang/String;", "getClasses", "getHasResult", "()Z", "getClasspathAddendum", "getType", "getData", "()Ljava/lang/Object;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CompiledClasses extends ReplCompileResult {
        private static final long serialVersionUID = 2;
        private final List<CompiledClassData> classes;
        private final List<File> classpathAddendum;
        private final Object data;
        private final boolean hasResult;
        private final LineId lineId;
        private final String mainClassName;
        private final List<ILineId> previousLines;
        private final String type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public CompiledClasses(LineId lineId, List<? extends ILineId> list, String str, List<CompiledClassData> list2, boolean z, List<? extends File> list3, String str2, Object obj) {
            super(null);
            lineId.getClass();
            list.getClass();
            str.getClass();
            list2.getClass();
            list3.getClass();
            this.lineId = lineId;
            this.previousLines = list;
            this.mainClassName = str;
            this.classes = list2;
            this.hasResult = z;
            this.classpathAddendum = list3;
            this.type = str2;
            this.data = obj;
        }

        public final List<CompiledClassData> getClasses() {
            return this.classes;
        }

        public final List<File> getClasspathAddendum() {
            return this.classpathAddendum;
        }

        public final Object getData() {
            return this.data;
        }

        public final boolean getHasResult() {
            return this.hasResult;
        }

        public final LineId getLineId() {
            return this.lineId;
        }

        public final String getMainClassName() {
            return this.mainClassName;
        }

        public final List<ILineId> getPreviousLines() {
            return this.previousLines;
        }

        public final String getType() {
            return this.type;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$Incomplete;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Incomplete extends ReplCompileResult {
        private static final long serialVersionUID = 1;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Incomplete(String str) {
            super(null);
            str.getClass();
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    public /* synthetic */ ReplCompileResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ReplCompileResult() {
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$Error;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;)V", "getMessage", "()Ljava/lang/String;", "getLocation", "()Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "toString", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Error extends ReplCompileResult {
        private static final long serialVersionUID = 1;
        private final CompilerMessageLocation location;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(String str, CompilerMessageLocation compilerMessageLocation) {
            super(null);
            str.getClass();
            this.message = str;
            this.location = compilerMessageLocation;
        }

        public final CompilerMessageLocation getLocation() {
            return this.location;
        }

        public final String getMessage() {
            return this.message;
        }

        public String toString() {
            return "Error(message = \"" + this.message + '\"';
        }

        public /* synthetic */ Error(String str, CompilerMessageLocation compilerMessageLocation, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : compilerMessageLocation);
        }
    }
}

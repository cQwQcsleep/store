package org.jetbrains.kotlin.wasm.ir.source.location;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation;", "", "<init>", "()V", "WithFileAndLineNumberInformation", "NoLocation", "NextLocation", "IgnoredLocation", "DefinedLocation", "Companion", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$DefinedLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$IgnoredLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$NextLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$NoLocation;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class SourceLocation {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0004HÖ\u0081\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$DefinedLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$WithFileAndLineNumberInformation;", "file", "", "line", "", "column", "<init>", "(Ljava/lang/String;II)V", "getFile", "()Ljava/lang/String;", "getLine", "()I", "getColumn", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final /* data */ class DefinedLocation extends SourceLocation implements WithFileAndLineNumberInformation {
        private final int column;
        private final String file;
        private final int line;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefinedLocation(String str, int i, int i2) {
            super(null);
            str.getClass();
            this.file = str;
            this.line = i;
            this.column = i2;
        }

        public static /* synthetic */ DefinedLocation copy$default(DefinedLocation definedLocation, String str, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = definedLocation.file;
            }
            if ((i3 & 2) != 0) {
                i = definedLocation.line;
            }
            if ((i3 & 4) != 0) {
                i2 = definedLocation.column;
            }
            return definedLocation.copy(str, i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getLine() {
            return this.line;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getColumn() {
            return this.column;
        }

        public final DefinedLocation copy(String file, int line, int column) {
            file.getClass();
            return new DefinedLocation(file, line, column);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DefinedLocation)) {
                return false;
            }
            DefinedLocation definedLocation = (DefinedLocation) other;
            return Intrinsics.areEqual(this.file, definedLocation.file) && this.line == definedLocation.line && this.column == definedLocation.column;
        }

        @Override // org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation.WithFileAndLineNumberInformation
        public int getColumn() {
            return this.column;
        }

        @Override // org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation.WithFileAndLineNumberInformation
        public String getFile() {
            return this.file;
        }

        @Override // org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation.WithFileAndLineNumberInformation
        public int getLine() {
            return this.line;
        }

        public int hashCode() {
            return (((this.file.hashCode() * 31) + Integer.hashCode(this.line)) * 31) + Integer.hashCode(this.column);
        }

        public String toString() {
            return "DefinedLocation(file=" + this.file + ", line=" + this.line + ", column=" + this.column + ')';
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$IgnoredLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$WithFileAndLineNumberInformation;", "<init>", "()V", "file", "", "getFile", "()Ljava/lang/String;", "line", "", "getLine", "()I", "column", "getColumn", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class IgnoredLocation extends SourceLocation implements WithFileAndLineNumberInformation {
        private static final int column = 0;
        private static final int line = 0;
        public static final IgnoredLocation INSTANCE = new IgnoredLocation();
        private static final String file = "NATIVE_IMPLEMENTATIONS.kt";

        private IgnoredLocation() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation.WithFileAndLineNumberInformation
        public int getColumn() {
            return column;
        }

        @Override // org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation.WithFileAndLineNumberInformation
        public String getFile() {
            return file;
        }

        @Override // org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation.WithFileAndLineNumberInformation
        public int getLine() {
            return line;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$NextLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class NextLocation extends SourceLocation {
        public static final NextLocation INSTANCE = new NextLocation();

        private NextLocation() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$NoLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class NoLocation extends SourceLocation {
        public static final NoLocation INSTANCE = new NoLocation();

        private NoLocation() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\u0082\u0001\u0002\f\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$WithFileAndLineNumberInformation;", "", "file", "", "getFile", "()Ljava/lang/String;", "line", "", "getLine", "()I", "column", "getColumn", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$DefinedLocation;", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$IgnoredLocation;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public interface WithFileAndLineNumberInformation {
        int getColumn();

        String getFile();

        int getLine();
    }

    public /* synthetic */ SourceLocation(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation$Companion;", "", "<init>", "()V", "NoLocation", "Lorg/jetbrains/kotlin/wasm/ir/source/location/SourceLocation;", "description", "", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SourceLocation NoLocation(String description) {
            description.getClass();
            return NoLocation.INSTANCE;
        }

        private Companion() {
        }
    }

    private SourceLocation() {
    }
}

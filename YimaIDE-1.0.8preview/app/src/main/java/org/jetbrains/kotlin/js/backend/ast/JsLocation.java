package org.jetbrains.kotlin.js.backend.ast;

import java.io.Reader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.js.backend.ast.JsLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u001a\u0002\b\n¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u001a\u001a\u00020\u0000H\u0016J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/JsLocation;", "Lorg/jetbrains/kotlin/js/backend/ast/JsLocationWithSource;", "file", "", "startLine", "", "startChar", "name", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "Lkotlin/jvm/JvmOverloads;", "getFile", "()Ljava/lang/String;", "getStartLine", "()I", "getStartChar", "getName", "fileIdentity", "", "getFileIdentity", "()Ljava/lang/Object;", "sourceProvider", "Lkotlin/Function0;", "Ljava/io/Reader;", "getSourceProvider", "()Lkotlin/jvm/functions/Function0;", "asSimpleLocation", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsLocation implements JsLocationWithSource {
    private final String file;
    private final String name;
    private final int startChar;
    private final int startLine;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JsLocation(String str, int i, int i2) {
        this(str, i, i2, null, 8, null);
        str.getClass();
    }

    public static Reader a() {
        return null;
    }

    public static /* synthetic */ JsLocation copy$default(JsLocation jsLocation, String str, int i, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = jsLocation.file;
        }
        if ((i3 & 2) != 0) {
            i = jsLocation.startLine;
        }
        if ((i3 & 4) != 0) {
            i2 = jsLocation.startChar;
        }
        if ((i3 & 8) != 0) {
            str2 = jsLocation.name;
        }
        return jsLocation.copy(str, i, i2, str2);
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public JsLocation asSimpleLocation() {
        return this;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getStartLine() {
        return this.startLine;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getStartChar() {
        return this.startChar;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final JsLocation copy(String file, int startLine, int startChar, String name) {
        file.getClass();
        return new JsLocation(file, startLine, startChar, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsLocation)) {
            return false;
        }
        JsLocation jsLocation = (JsLocation) other;
        return Intrinsics.areEqual(this.file, jsLocation.file) && this.startLine == jsLocation.startLine && this.startChar == jsLocation.startChar && Intrinsics.areEqual(this.name, jsLocation.name);
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public String getFile() {
        return this.file;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public Object getFileIdentity() {
        return null;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public String getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public Function0<Reader> getSourceProvider() {
        return new Function0() { // from class: lt7
            public final Object invoke() {
                return JsLocation.a();
            }
        };
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public int getStartChar() {
        return this.startChar;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public int getStartLine() {
        return this.startLine;
    }

    public int hashCode() {
        int iHashCode = ((((this.file.hashCode() * 31) + Integer.hashCode(this.startLine)) * 31) + Integer.hashCode(this.startChar)) * 31;
        String str = this.name;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "JsLocation(file=" + this.file + ", startLine=" + this.startLine + ", startChar=" + this.startChar + ", name=" + this.name + ')';
    }

    public JsLocation(String str, int i, int i2, String str2) {
        str.getClass();
        this.file = str;
        this.startLine = i;
        this.startChar = i2;
        this.name = str2;
    }

    public /* synthetic */ JsLocation(String str, int i, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 8) != 0 ? null : str2);
    }
}

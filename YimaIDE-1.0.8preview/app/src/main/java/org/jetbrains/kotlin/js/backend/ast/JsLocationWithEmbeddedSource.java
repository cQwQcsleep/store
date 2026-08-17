package org.jetbrains.kotlin.js.backend.ast;

import java.io.Reader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u0010\u001a\u00020\u0011X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0011X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0012\u0010\u0016\u001a\u00020\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/JsLocationWithEmbeddedSource;", "Lorg/jetbrains/kotlin/js/backend/ast/JsLocationWithSource;", "location", "Lorg/jetbrains/kotlin/js/backend/ast/JsLocation;", "fileIdentity", "", "sourceProvider", "Lkotlin/Function0;", "Ljava/io/Reader;", "<init>", "(Lorg/jetbrains/kotlin/js/backend/ast/JsLocation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)V", "getFileIdentity", "()Ljava/lang/Object;", "getSourceProvider", "()Lkotlin/jvm/functions/Function0;", "asSimpleLocation", "file", "", "getFile", "()Ljava/lang/String;", "name", "getName", "startChar", "", "getStartChar", "()I", "startLine", "getStartLine", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsLocationWithEmbeddedSource implements JsLocationWithSource {
    private final Object fileIdentity;
    private final JsLocation location;
    private final Function0<Reader> sourceProvider;

    /* JADX WARN: Multi-variable type inference failed */
    public JsLocationWithEmbeddedSource(JsLocation jsLocation, Object obj, Function0<? extends Reader> function0) {
        jsLocation.getClass();
        function0.getClass();
        this.location = jsLocation;
        this.fileIdentity = obj;
        this.sourceProvider = function0;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public JsLocation asSimpleLocation() {
        return this.location.asSimpleLocation();
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public String getFile() {
        return this.location.getFile();
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public Object getFileIdentity() {
        return this.fileIdentity;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public String getName() {
        return this.location.getName();
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public Function0<Reader> getSourceProvider() {
        return this.sourceProvider;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public int getStartChar() {
        return this.location.getStartChar();
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource
    public int getStartLine() {
        return this.location.getStartLine();
    }
}

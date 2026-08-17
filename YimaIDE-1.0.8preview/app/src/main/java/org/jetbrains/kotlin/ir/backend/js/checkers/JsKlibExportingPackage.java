package org.jetbrains.kotlin.ir.backend.js.checkers;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\u0003H\u0016R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/checkers/JsKlibExportingPackage;", "Lorg/jetbrains/kotlin/ir/backend/js/checkers/JsKlibExport;", "containingFile", "", "fqName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFqName", "()Ljava/lang/String;", "render", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsKlibExportingPackage extends JsKlibExport {
    private final String fqName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsKlibExportingPackage(String str, String str2) {
        super(str);
        str.getClass();
        str2.getClass();
        this.fqName = str2;
    }

    @Override // org.jetbrains.kotlin.ir.backend.js.checkers.JsKlibExport
    public String getFqName() {
        return this.fqName;
    }

    @Override // org.jetbrains.kotlin.ir.backend.js.checkers.JsKlibExport
    public String render() {
        return "package '" + getFqName() + "' from file '" + getContainingFile() + '\'';
    }
}

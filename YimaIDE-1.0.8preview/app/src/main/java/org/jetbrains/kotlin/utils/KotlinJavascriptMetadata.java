package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/utils/KotlinJavascriptMetadata;", "", "version", "Lorg/jetbrains/kotlin/utils/JsMetadataVersion;", "moduleName", "", "body", "", "<init>", "(Lorg/jetbrains/kotlin/utils/JsMetadataVersion;Ljava/lang/String;[B)V", "getVersion", "()Lorg/jetbrains/kotlin/utils/JsMetadataVersion;", "getModuleName", "()Ljava/lang/String;", "getBody", "()[B", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class KotlinJavascriptMetadata {
    private final byte[] body;
    private final String moduleName;
    private final JsMetadataVersion version;

    public KotlinJavascriptMetadata(JsMetadataVersion jsMetadataVersion, String str, byte[] bArr) {
        jsMetadataVersion.getClass();
        str.getClass();
        bArr.getClass();
        this.version = jsMetadataVersion;
        this.moduleName = str;
        this.body = bArr;
    }

    public final byte[] getBody() {
        return this.body;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final JsMetadataVersion getVersion() {
        return this.version;
    }
}

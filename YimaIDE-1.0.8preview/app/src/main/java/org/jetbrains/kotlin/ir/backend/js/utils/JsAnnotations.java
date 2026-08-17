package org.jetbrains.kotlin.ir.backend.js.utils;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/utils/JsAnnotations;", "", "<init>", "()V", "jsModuleFqn", "Lorg/jetbrains/kotlin/name/FqName;", "getJsModuleFqn", "()Lorg/jetbrains/kotlin/name/FqName;", "jsNonModuleFqn", "getJsNonModuleFqn", "jsNameFqn", "getJsNameFqn", "jsStatic", "getJsStatic", "jsSymbolFqn", "getJsSymbolFqn", "jsFileNameFqn", "getJsFileNameFqn", "jsQualifierFqn", "getJsQualifierFqn", "jsExportFqn", "getJsExportFqn", "jsExportDefaultFqn", "getJsExportDefaultFqn", "jsImplicitExportFqn", "getJsImplicitExportFqn", "jsExportIgnoreFqn", "getJsExportIgnoreFqn", "jsNativeGetter", "getJsNativeGetter", "jsNativeSetter", "getJsNativeSetter", "jsNativeInvoke", "getJsNativeInvoke", "JsPolyfillFqn", "getJsPolyfillFqn", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsAnnotations {
    public static final JsAnnotations INSTANCE = new JsAnnotations();
    private static final FqName jsModuleFqn = new FqName("kotlin.js.JsModule");
    private static final FqName jsNonModuleFqn = new FqName("kotlin.js.JsNonModule");
    private static final FqName jsNameFqn = new FqName("kotlin.js.JsName");
    private static final FqName jsStatic = new FqName("kotlin.js.JsStatic");
    private static final FqName jsSymbolFqn = new FqName("kotlin.js.JsSymbol");
    private static final FqName jsFileNameFqn = new FqName("kotlin.js.JsFileName");
    private static final FqName jsQualifierFqn = new FqName("kotlin.js.JsQualifier");
    private static final FqName jsExportFqn = new FqName("kotlin.js.JsExport");
    private static final FqName jsExportDefaultFqn = new FqName("kotlin.js.JsExport.Default");
    private static final FqName jsImplicitExportFqn = new FqName("kotlin.js.JsImplicitExport");
    private static final FqName jsExportIgnoreFqn = new FqName("kotlin.js.JsExport.Ignore");
    private static final FqName jsNativeGetter = new FqName("kotlin.js.nativeGetter");
    private static final FqName jsNativeSetter = new FqName("kotlin.js.nativeSetter");
    private static final FqName jsNativeInvoke = new FqName("kotlin.js.nativeInvoke");
    private static final FqName JsPolyfillFqn = new FqName("kotlin.js.JsPolyfill");

    private JsAnnotations() {
    }

    public final FqName getJsExportDefaultFqn() {
        return jsExportDefaultFqn;
    }

    public final FqName getJsExportFqn() {
        return jsExportFqn;
    }

    public final FqName getJsExportIgnoreFqn() {
        return jsExportIgnoreFqn;
    }

    public final FqName getJsFileNameFqn() {
        return jsFileNameFqn;
    }

    public final FqName getJsImplicitExportFqn() {
        return jsImplicitExportFqn;
    }

    public final FqName getJsModuleFqn() {
        return jsModuleFqn;
    }

    public final FqName getJsNameFqn() {
        return jsNameFqn;
    }

    public final FqName getJsNativeGetter() {
        return jsNativeGetter;
    }

    public final FqName getJsNativeInvoke() {
        return jsNativeInvoke;
    }

    public final FqName getJsNativeSetter() {
        return jsNativeSetter;
    }

    public final FqName getJsNonModuleFqn() {
        return jsNonModuleFqn;
    }

    public final FqName getJsPolyfillFqn() {
        return JsPolyfillFqn;
    }

    public final FqName getJsQualifierFqn() {
        return jsQualifierFqn;
    }

    public final FqName getJsStatic() {
        return jsStatic;
    }

    public final FqName getJsSymbolFqn() {
        return jsSymbolFqn;
    }
}

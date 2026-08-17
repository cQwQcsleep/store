package org.jetbrains.kotlin.diagnostics;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0\u001d0\u0005\"\u0004\b\u0000\u0010\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u0005R\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\bR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\b¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderers;", Argument.Delimiters.none, "<init>", "()V", "NULLABLE_STRING", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", Argument.Delimiters.none, "getNULLABLE_STRING", "()Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "TO_STRING", "getTO_STRING", "OPTIONAL_COLON_TO_STRING", "getOPTIONAL_COLON_TO_STRING", "EMPTY", "getEMPTY", "VISIBILITY", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVISIBILITY", "NOT_RENDERED", "getNOT_RENDERED", "FUNCTION_PARAMETERS", Argument.Delimiters.none, "getFUNCTION_PARAMETERS", "CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getCLASS_ID", "CLASS_ID_RELATIVE_NAME_ONLY", "getCLASS_ID_RELATIVE_NAME_ONLY", "COLLECTION", Argument.Delimiters.none, "T", "renderer", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticRenderers {
    public static final KtDiagnosticRenderers INSTANCE = new KtDiagnosticRenderers();
    private static final ContextIndependentParameterRenderer<String> NULLABLE_STRING = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: oe8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.c((String) obj);
        }
    });
    private static final ContextIndependentParameterRenderer<Object> TO_STRING = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: pe8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.i(obj);
        }
    });
    private static final ContextIndependentParameterRenderer<Object> OPTIONAL_COLON_TO_STRING = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: qe8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.k(obj);
        }
    });
    private static final ContextIndependentParameterRenderer<Object> EMPTY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: re8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.b(obj);
        }
    });
    private static final ContextIndependentParameterRenderer<Visibility> VISIBILITY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: se8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.e((Visibility) obj);
        }
    });
    private static final ContextIndependentParameterRenderer<Object> NOT_RENDERED = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: te8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.h(obj);
        }
    });
    private static final ContextIndependentParameterRenderer<Boolean> FUNCTION_PARAMETERS = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: ue8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.a(((Boolean) obj).booleanValue());
        }
    });
    private static final ContextIndependentParameterRenderer<ClassId> CLASS_ID = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: ve8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.g((ClassId) obj);
        }
    });
    private static final ContextIndependentParameterRenderer<ClassId> CLASS_ID_RELATIVE_NAME_ONLY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: me8
        public final Object invoke(Object obj) {
            return KtDiagnosticRenderers.d((ClassId) obj);
        }
    });

    private KtDiagnosticRenderers() {
    }

    public static String a(boolean z) {
        return z ? "..." : Argument.Delimiters.none;
    }

    public static String b(Object obj) {
        return Argument.Delimiters.none;
    }

    public static String c(String str) {
        return str == null ? "null" : str;
    }

    public static String d(ClassId classId) {
        classId.getClass();
        return classId.getRelativeClassName().asString();
    }

    public static String e(Visibility visibility) {
        visibility.getClass();
        return visibility.getExternalDisplayName();
    }

    public static String g(ClassId classId) {
        classId.getClass();
        return classId.asFqNameString();
    }

    public static String h(Object obj) {
        return Argument.Delimiters.none;
    }

    public static String i(Object obj) {
        return String.valueOf(obj);
    }

    public static String j(final ContextIndependentParameterRenderer contextIndependentParameterRenderer, Collection collection) {
        collection.getClass();
        return CollectionsKt.joinToString(collection, ", ", "[", "]", 3, "...", new Function1() { // from class: ne8
            public final Object invoke(Object obj) {
                return contextIndependentParameterRenderer.render(obj);
            }
        });
    }

    public static String k(Object obj) {
        String strValueOf = String.valueOf(obj);
        return strValueOf.length() > 0 ? ": ".concat(strValueOf) : Argument.Delimiters.none;
    }

    public final <T> ContextIndependentParameterRenderer<Collection<? extends T>> COLLECTION(final ContextIndependentParameterRenderer<? super T> renderer) {
        renderer.getClass();
        return DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: le8
            public final Object invoke(Object obj) {
                return KtDiagnosticRenderers.j(renderer, (Collection) obj);
            }
        });
    }

    public final ContextIndependentParameterRenderer<ClassId> getCLASS_ID() {
        return CLASS_ID;
    }

    public final ContextIndependentParameterRenderer<ClassId> getCLASS_ID_RELATIVE_NAME_ONLY() {
        return CLASS_ID_RELATIVE_NAME_ONLY;
    }

    public final ContextIndependentParameterRenderer<Object> getEMPTY() {
        return EMPTY;
    }

    public final ContextIndependentParameterRenderer<Boolean> getFUNCTION_PARAMETERS() {
        return FUNCTION_PARAMETERS;
    }

    public final ContextIndependentParameterRenderer<Object> getNOT_RENDERED() {
        return NOT_RENDERED;
    }

    public final ContextIndependentParameterRenderer<String> getNULLABLE_STRING() {
        return NULLABLE_STRING;
    }

    public final ContextIndependentParameterRenderer<Object> getOPTIONAL_COLON_TO_STRING() {
        return OPTIONAL_COLON_TO_STRING;
    }

    public final ContextIndependentParameterRenderer<Object> getTO_STRING() {
        return TO_STRING;
    }

    public final ContextIndependentParameterRenderer<Visibility> getVISIBILITY() {
        return VISIBILITY;
    }
}

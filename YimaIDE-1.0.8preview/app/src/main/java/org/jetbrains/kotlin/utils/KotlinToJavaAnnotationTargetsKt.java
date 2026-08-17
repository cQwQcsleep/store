package org.jetbrains.kotlin.utils;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.eclipse.jdt.internal.compiler.util.SuffixConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u001d\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"KOTLIN_TO_JAVA_ANNOTATION_TARGETS", "", "", "getKOTLIN_TO_JAVA_ANNOTATION_TARGETS", "()Ljava/util/Map;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KotlinToJavaAnnotationTargetsKt {
    private static final Map<String, String> KOTLIN_TO_JAVA_ANNOTATION_TARGETS = MapsKt.mapOf(new Pair[]{TuplesKt.to(SuffixConstants.EXTENSION_CLASS, "TYPE"), TuplesKt.to("ANNOTATION_CLASS", "ANNOTATION_TYPE"), TuplesKt.to("FIELD", "FIELD"), TuplesKt.to("LOCAL_VARIABLE", "LOCAL_VARIABLE"), TuplesKt.to("VALUE_PARAMETER", "PARAMETER"), TuplesKt.to("CONSTRUCTOR", "CONSTRUCTOR"), TuplesKt.to("FUNCTION", "METHOD"), TuplesKt.to("PROPERTY_GETTER", "METHOD"), TuplesKt.to("PROPERTY_SETTER", "METHOD"), TuplesKt.to("TYPE_PARAMETER", "TYPE_PARAMETER"), TuplesKt.to("TYPE", "TYPE_USE")});

    public static final Map<String, String> getKOTLIN_TO_JAVA_ANNOTATION_TARGETS() {
        return KOTLIN_TO_JAVA_ANNOTATION_TARGETS;
    }
}

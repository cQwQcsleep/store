package org.jetbrains.kotlin.fir.serialization;

import defpackage.f2f;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.AnnotationValue;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.constant.ErrorValue;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.serialization.constant.FirToConstantValueTransformerKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0018J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e0\u001cH\u0002J\u0019\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0000¢\u0006\u0002\b\"R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "localClassIdOracle", "Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getStringTable$org_jetbrains_kotlin_fir_serialization", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "getLocalClassIdOracle$org_jetbrains_kotlin_fir_serialization", "()Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "serializeAnnotation", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/constant/AnnotationValue;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "argumentsMapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/constant/ConstantValue;", "valueProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation$Argument$Value$Builder;", "constant", "valueProto$org_jetbrains_kotlin_fir_serialization", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationSerializer implements SessionAndScopeSessionHolder {
    private final LocalClassIdOracle localClassIdOracle;
    private final ScopeSession scopeSession;
    private final FirSession session;
    private final FirElementAwareStringTable stringTable;

    public FirAnnotationSerializer(FirSession firSession, ScopeSession scopeSession, FirElementAwareStringTable firElementAwareStringTable, LocalClassIdOracle localClassIdOracle) {
        firSession.getClass();
        scopeSession.getClass();
        firElementAwareStringTable.getClass();
        localClassIdOracle.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.stringTable = firElementAwareStringTable;
        this.localClassIdOracle = localClassIdOracle;
    }

    private final ProtoBuf.Annotation serializeAnnotation(ClassId classId, Map<Name, ? extends ConstantValue<?>> argumentsMapping) {
        String message;
        ProtoBuf.Annotation.Builder builderNewBuilder = ProtoBuf.Annotation.newBuilder();
        builderNewBuilder.setId(this.stringTable.getQualifiedClassNameIndex(classId));
        for (Map.Entry<Name, ? extends ConstantValue<?>> entry : argumentsMapping.entrySet()) {
            Name key = entry.getKey();
            ConstantValue<?> value = entry.getValue();
            if (!(value instanceof ErrorValue)) {
                serializeAnnotation$lambda$0$addArgument(this, builderNewBuilder, value, key);
            } else if (!((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getMetadataCompilation())).booleanValue()) {
                ErrorValue.ErrorValueWithMessage errorValueWithMessage = value instanceof ErrorValue.ErrorValueWithMessage ? (ErrorValue.ErrorValueWithMessage) value : null;
                if (errorValueWithMessage == null || (message = errorValueWithMessage.getMessage()) == null) {
                    message = "Error value after conversion of expression of " + key + " argument";
                }
                mx5.a(message);
                return null;
            }
        }
        ProtoBuf.Annotation annotationBuild = builderNewBuilder.build();
        annotationBuild.getClass();
        return annotationBuild;
    }

    private static final void serializeAnnotation$lambda$0$addArgument(FirAnnotationSerializer firAnnotationSerializer, ProtoBuf.Annotation.Builder builder, ConstantValue<?> constantValue, Name name) {
        ProtoBuf.Annotation.Argument.Builder builderNewBuilder = ProtoBuf.Annotation.Argument.newBuilder();
        FirElementAwareStringTable firElementAwareStringTable = firAnnotationSerializer.stringTable;
        String strAsString = name.asString();
        strAsString.getClass();
        builderNewBuilder.setNameId(firElementAwareStringTable.getStringIndex(strAsString));
        builderNewBuilder.setValue(firAnnotationSerializer.valueProto$org_jetbrains_kotlin_fir_serialization(constantValue));
        builder.addArgument(builderNewBuilder);
    }

    /* JADX INFO: renamed from: getLocalClassIdOracle$org_jetbrains_kotlin_fir_serialization, reason: from getter */
    public final LocalClassIdOracle getLocalClassIdOracle() {
        return this.localClassIdOracle;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: getStringTable$org_jetbrains_kotlin_fir_serialization, reason: from getter */
    public final FirElementAwareStringTable getStringTable() {
        return this.stringTable;
    }

    public final ProtoBuf.Annotation.Argument.Value.Builder valueProto$org_jetbrains_kotlin_fir_serialization(ConstantValue<?> constant) {
        constant.getClass();
        ProtoBuf.Annotation.Argument.Value.Builder builderNewBuilder = ProtoBuf.Annotation.Argument.Value.newBuilder();
        FirAnnotationArgumentVisitor firAnnotationArgumentVisitor = FirAnnotationArgumentVisitor.INSTANCE;
        builderNewBuilder.getClass();
        constant.accept(firAnnotationArgumentVisitor, new FirAnnotationArgumentVisitorData(this, builderNewBuilder));
        builderNewBuilder.getClass();
        return builderNewBuilder;
    }

    public final ProtoBuf.Annotation serializeAnnotation(AnnotationValue annotation) {
        annotation.getClass();
        return serializeAnnotation(annotation.getValue().getClassId(), annotation.getValue().getArgumentsMapping());
    }

    public final ProtoBuf.Annotation serializeAnnotation(FirAnnotation annotation) {
        annotation.getClass();
        FirClassLikeSymbol<?> annotationClassLikeSymbol = FirAnnotationUtilsKt.toAnnotationClassLikeSymbol(annotation, getSession());
        if (annotationClassLikeSymbol != null && FirAnnotationUtilsKt.hasAnnotation(annotationClassLikeSymbol, StandardClassIds$Annotations.INSTANCE.getOptionalExpectation(), getSession())) {
            return null;
        }
        AnnotationValue annotationValueEvaluateToAnnotationValue = FirToConstantValueTransformerKt.evaluateToAnnotationValue(this, annotation);
        if (annotationValueEvaluateToAnnotationValue == null) {
            annotationValueEvaluateToAnnotationValue = null;
        }
        if (annotationValueEvaluateToAnnotationValue != null) {
            return serializeAnnotation(annotationValueEvaluateToAnnotationValue);
        }
        f2f.a("Cannot serialize annotation ", UtilsKt.render(annotation));
        return null;
    }
}

package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 /2\u00020\u0001:\u0001/B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J \u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0010J(\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\tJ\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001dJ\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0012J\u000e\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u001aJ8\u0010\u0019\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006J'\u0010%\u001a\u0002H&\"\b\b\u0000\u0010&*\u00020\u00062\u0006\u0010'\u001a\u0002H&2\u0006\u0010(\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010)J\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001a0+J\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060+J\u0006\u0010-\u001a\u00020.R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ParametersBuilder;", Argument.Delimiters.none, "<init>", "()V", "params", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "Lkotlin/collections/ArrayList;", "value", Argument.Delimiters.none, "nextParameterOffset", "getNextParameterOffset", "()I", "nextValueParameterIndex", "addThis", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "skipped", Argument.Delimiters.none, "addNextParameter", "typeOnStack", "addNextValueParameter", "remapValue", "Lorg/jetbrains/kotlin/codegen/StackValue;", "parameterIndex", "addCapturedParam", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "original", "newFieldName", Argument.Delimiters.none, "desc", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "skipInConstructor", "addCapturedParamCopy", "copyFrom", "containingLambdaType", "fieldName", "addParameter", "T", "info", "isValueParameter", "(Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;Z)Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "listCaptured", Argument.Delimiters.none, "listAllParams", "buildParameters", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ParametersBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int nextParameterOffset;
    private int nextValueParameterIndex;
    private final ArrayList<ParameterInfo> params;

    private ParametersBuilder() {
        this.params = new ArrayList<>();
    }

    public static /* synthetic */ ParameterInfo addNextParameter$default(ParametersBuilder parametersBuilder, Type type, boolean z, Type type2, int i, Object obj) {
        if ((i & 4) != 0) {
            type2 = type;
        }
        return parametersBuilder.addNextParameter(type, z, type2);
    }

    private final <T extends ParameterInfo> T addParameter(T info, boolean isValueParameter) {
        this.params.add(info);
        this.nextParameterOffset += info.getType().getSize();
        if (!(info instanceof CapturedParamInfo)) {
            this.nextValueParameterIndex++;
        }
        return info;
    }

    @JvmStatic
    public static final ParametersBuilder newBuilder() {
        return INSTANCE.newBuilder();
    }

    public final CapturedParamInfo addCapturedParam(Type containingLambdaType, String fieldName, String newFieldName, Type type, boolean skipped, ParameterInfo original) {
        containingLambdaType.getClass();
        fieldName.getClass();
        newFieldName.getClass();
        type.getClass();
        CapturedParamInfo capturedParamInfo = new CapturedParamInfo(new CapturedParamDesc(containingLambdaType, fieldName, type, false, 8, null), newFieldName, skipped, this.nextParameterOffset, original != null ? original.getIndex() : -1, false);
        if (original != null) {
            capturedParamInfo.setFunctionalArgument(original.getFunctionalArgument());
        }
        return (CapturedParamInfo) addParameter(capturedParamInfo, false);
    }

    public final CapturedParamInfo addCapturedParamCopy(CapturedParamInfo copyFrom) {
        copyFrom.getClass();
        return (CapturedParamInfo) addParameter(copyFrom.cloneWithNewDeclarationIndex(-1), false);
    }

    public final ParameterInfo addNextParameter(Type type, boolean skipped, Type typeOnStack) {
        type.getClass();
        typeOnStack.getClass();
        return addParameter(new ParameterInfo(type, skipped, this.nextParameterOffset, null, this.nextValueParameterIndex, typeOnStack), false);
    }

    public final ParameterInfo addNextValueParameter(Type type, boolean skipped, StackValue remapValue, int parameterIndex) {
        type.getClass();
        return addParameter(new ParameterInfo(type, skipped, this.nextParameterOffset, remapValue, parameterIndex, null, 32, null), true);
    }

    public final ParameterInfo addThis(Type type, boolean skipped) {
        type.getClass();
        return addParameter(new ParameterInfo(type, skipped, this.nextParameterOffset, -1, this.nextValueParameterIndex), false);
    }

    public final Parameters buildParameters() {
        Integer num;
        Iterator<T> it = this.params.iterator();
        if (it.hasNext()) {
            Integer numValueOf = Integer.valueOf(((ParameterInfo) it.next()).getDeclarationIndex());
            while (it.hasNext()) {
                Integer numValueOf2 = Integer.valueOf(((ParameterInfo) it.next()).getDeclarationIndex());
                if (numValueOf.compareTo(numValueOf2) < 0) {
                    numValueOf = numValueOf2;
                }
            }
            num = numValueOf;
        } else {
            num = null;
        }
        int iIntValue = (num != null ? num.intValue() : -1) + 1;
        ArrayList<ParameterInfo> arrayList = this.params;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (Object objCloneWithNewDeclarationIndex : arrayList) {
            if (objCloneWithNewDeclarationIndex instanceof CapturedParamInfo) {
                objCloneWithNewDeclarationIndex = ((CapturedParamInfo) objCloneWithNewDeclarationIndex).cloneWithNewDeclarationIndex(iIntValue);
                iIntValue++;
            }
            arrayList2.add(objCloneWithNewDeclarationIndex);
        }
        return new Parameters(arrayList2);
    }

    public final int getNextParameterOffset() {
        return this.nextParameterOffset;
    }

    public final List<ParameterInfo> listAllParams() {
        return this.params;
    }

    public final List<CapturedParamInfo> listCaptured() {
        ArrayList<ParameterInfo> arrayList = this.params;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (obj instanceof CapturedParamInfo) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005H\u0007b\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ParametersBuilder$Companion;", Argument.Delimiters.none, "<init>", "()V", "newBuilder", "Lorg/jetbrains/kotlin/codegen/inline/ParametersBuilder;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ParametersBuilder newBuilder() {
            return new ParametersBuilder(null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ ParametersBuilder(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final CapturedParamInfo addCapturedParam(CapturedParamDesc desc, String newFieldName, boolean skipInConstructor) {
        desc.getClass();
        newFieldName.getClass();
        return (CapturedParamInfo) addParameter(new CapturedParamInfo(desc, newFieldName, false, this.nextParameterOffset, -1, skipInConstructor), false);
    }

    public final CapturedParamInfo addCapturedParam(CapturedParamInfo original, String newFieldName) {
        original.getClass();
        newFieldName.getClass();
        CapturedParamInfo capturedParamInfo = new CapturedParamInfo(original.getDesc(), newFieldName, original.getIsSkipped(), this.nextParameterOffset, original.getIndex(), original.getIsSkipInConstructor());
        capturedParamInfo.setFunctionalArgument(original.getFunctionalArgument());
        return (CapturedParamInfo) addParameter(capturedParamInfo, false);
    }
}

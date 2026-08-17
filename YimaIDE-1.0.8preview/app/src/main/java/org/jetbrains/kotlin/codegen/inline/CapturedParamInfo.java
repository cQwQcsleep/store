package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000  2\u00020\u0001:\u0001 B9\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rBC\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0004\b\f\u0010\u0010J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0016\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u001c\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0014¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "desc", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "newFieldName", Argument.Delimiters.none, "skipped", Argument.Delimiters.none, "index", Argument.Delimiters.none, "remapIndex", "skipInConstructor", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;Ljava/lang/String;ZIIZ)V", "Lorg/jetbrains/kotlin/codegen/StackValue;", "declarationIndex", "(Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;Ljava/lang/String;ZILorg/jetbrains/kotlin/codegen/StackValue;ZI)V", "getDesc", "()Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "getNewFieldName", "()Ljava/lang/String;", "isSkipInConstructor", "()Z", "isSynthetic", "setSynthetic", "(Z)V", "originalFieldName", "getOriginalFieldName", "containingLambdaName", "getContainingLambdaName", "cloneWithNewDeclarationIndex", "newDeclarationIndex", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CapturedParamInfo extends ParameterInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CapturedParamDesc desc;
    private final boolean isSkipInConstructor;
    private boolean isSynthetic;
    private final String newFieldName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapturedParamInfo(CapturedParamDesc capturedParamDesc, String str, boolean z, int i, int i2, boolean z2) {
        super(capturedParamDesc.getType(), z, i, i2, -1);
        capturedParamDesc.getClass();
        str.getClass();
        this.desc = capturedParamDesc;
        this.newFieldName = str;
        this.isSkipInConstructor = z2;
    }

    public final CapturedParamInfo cloneWithNewDeclarationIndex(int newDeclarationIndex) {
        CapturedParamInfo capturedParamInfo = new CapturedParamInfo(this.desc, this.newFieldName, getIsSkipped(), getIndex(), getRemapValue(), this.isSkipInConstructor, newDeclarationIndex);
        capturedParamInfo.setFunctionalArgument(getFunctionalArgument());
        capturedParamInfo.isSynthetic = this.isSynthetic;
        return capturedParamInfo;
    }

    public final String getContainingLambdaName() {
        return this.desc.getContainingLambdaName();
    }

    public final CapturedParamDesc getDesc() {
        return this.desc;
    }

    public final String getNewFieldName() {
        return this.newFieldName;
    }

    public final String getOriginalFieldName() {
        return this.desc.getFieldName();
    }

    /* JADX INFO: renamed from: isSkipInConstructor, reason: from getter */
    public final boolean getIsSkipInConstructor() {
        return this.isSkipInConstructor;
    }

    /* JADX INFO: renamed from: isSynthetic, reason: from getter */
    public final boolean getIsSynthetic() {
        return this.isSynthetic;
    }

    public final void setSynthetic(boolean z) {
        this.isSynthetic = z;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo$Companion;", Argument.Delimiters.none, "<init>", "()V", "isSynthetic", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isSynthetic(ParameterInfo info) {
            info.getClass();
            return (info instanceof CapturedParamInfo) && ((CapturedParamInfo) info).getIsSynthetic();
        }

        private Companion() {
        }
    }

    private CapturedParamInfo(CapturedParamDesc capturedParamDesc, String str, boolean z, int i, StackValue stackValue, boolean z2, int i2) {
        super(capturedParamDesc.getType(), z, i, stackValue, i2, null, 32, null);
        this.desc = capturedParamDesc;
        this.newFieldName = str;
        this.isSkipInConstructor = z2;
    }
}

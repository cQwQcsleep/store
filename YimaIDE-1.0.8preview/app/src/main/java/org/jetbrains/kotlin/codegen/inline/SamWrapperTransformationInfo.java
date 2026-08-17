package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\b\u0010\u0014\u001a\u00020\u0007H\u0016J\"\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SamWrapperTransformationInfo;", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "oldClassName", Argument.Delimiters.none, "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "alreadyRegenerated", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/inline/InliningContext;Z)V", "getOldClassName", "()Ljava/lang/String;", "nameGenerator", "Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "getNameGenerator", "()Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "newClassName", "getNewClassName", "shouldRegenerate", "sameModule", "canRemoveAfterTransformation", "createTransformer", "Lorg/jetbrains/kotlin/codegen/inline/SamWrapperTransformer;", "continuationClassName", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SamWrapperTransformationInfo implements TransformationInfo {
    private final boolean alreadyRegenerated;
    private final InliningContext inliningContext;
    private final String oldClassName;

    public SamWrapperTransformationInfo(String str, InliningContext inliningContext, boolean z) {
        str.getClass();
        inliningContext.getClass();
        this.oldClassName = str;
        this.inliningContext = inliningContext;
        this.alreadyRegenerated = z;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public boolean canRemoveAfterTransformation() {
        return false;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public SamWrapperTransformer createTransformer(InliningContext inliningContext, boolean sameModule, String continuationClassName) {
        inliningContext.getClass();
        return new SamWrapperTransformer(this, inliningContext);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public NameGenerator getNameGenerator() {
        return new NameGenerator() { // from class: org.jetbrains.kotlin.codegen.inline.SamWrapperTransformationInfo$nameGenerator$1
            {
                super("stub");
            }

            @Override // org.jetbrains.kotlin.codegen.inline.NameGenerator
            public String getGeneratorClass() {
                throw new IllegalStateException(("Shouldn't be called on " + this.this$0.getOldClassName() + " transformation").toString());
            }

            @Override // org.jetbrains.kotlin.codegen.inline.NameGenerator
            public NameGenerator subGenerator(String inliningMethod) {
                throw new IllegalStateException(("Shouldn't be called on " + this.this$0.getOldClassName() + " transformation").toString());
            }

            @Override // org.jetbrains.kotlin.codegen.inline.NameGenerator
            public NameGenerator subGenerator(boolean lambdaNoWhen, String nameSuffix) {
                throw new IllegalStateException(("Shouldn't be called on " + this.this$0.getOldClassName() + " transformation").toString());
            }
        };
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public String getNewClassName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.inliningContext.getRoot().getCallSiteInfo().getOwnerClassName());
        sb.append(InlineCodegenUtilsKt.INLINE_TRANSFORMATION_SUFFIX);
        sb.append("$sam$" + StringsKt.substringAfter$default(getOldClassName(), "$sam$", (String) null, 2, (Object) null));
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public String getOldClassName() {
        return this.oldClassName;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public boolean shouldRegenerate(boolean sameModule) {
        return (sameModule || this.alreadyRegenerated) ? false : true;
    }
}

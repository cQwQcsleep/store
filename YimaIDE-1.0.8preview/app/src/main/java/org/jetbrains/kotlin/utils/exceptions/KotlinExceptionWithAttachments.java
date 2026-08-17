package org.jetbrains.kotlin.utils.exceptions;

import com.intellij.openapi.diagnostic.Attachment;
import com.intellij.openapi.diagnostic.ExceptionWithAttachments;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0016¢\u0006\u0002\u0010\tJ\u001a\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/utils/exceptions/KotlinExceptionWithAttachments;", "Lcom/intellij/openapi/diagnostic/ExceptionWithAttachments;", "mutableAttachments", "", "Lcom/intellij/openapi/diagnostic/Attachment;", "getMutableAttachments", "()Ljava/util/List;", "getAttachments", "", "()[Lcom/intellij/openapi/diagnostic/Attachment;", "withAttachment", "name", "", "content", "", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface KotlinExceptionWithAttachments extends ExceptionWithAttachments {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\b\tJ\u0014\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/utils/exceptions/KotlinExceptionWithAttachments$Companion;", "", "<init>", "()V", "withAttachmentsFrom", "", "Lorg/jetbrains/kotlin/utils/exceptions/KotlinExceptionWithAttachments;", "from", "", "withAttachmentsFrom$org_jetbrains_kotlin_util", "copyWithNewName", "Lcom/intellij/openapi/diagnostic/Attachment;", "newName", "", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        private final Attachment copyWithNewName(Attachment attachment, String str) {
            byte[] bytes = attachment.getBytes();
            bytes.getClass();
            Charset charset = StandardCharsets.UTF_8;
            charset.getClass();
            return new Attachment(str, new String(bytes, charset));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void withAttachmentsFrom$org_jetbrains_kotlin_util(KotlinExceptionWithAttachments kotlinExceptionWithAttachments, Throwable th) {
            kotlinExceptionWithAttachments.getClass();
            if (th instanceof KotlinExceptionWithAttachments) {
                List<Attachment> mutableAttachments = ((KotlinExceptionWithAttachments) th).getMutableAttachments();
                List<Attachment> mutableAttachments2 = kotlinExceptionWithAttachments.getMutableAttachments();
                for (Attachment attachment : mutableAttachments) {
                    mutableAttachments2.add($$INSTANCE.copyWithNewName(attachment, "case_" + attachment.getPath()));
                }
            }
            if (th != 0) {
                kotlinExceptionWithAttachments.withAttachment("causeThrowable", ExceptionsKt.stackTraceToString(th));
            }
        }
    }

    default Attachment[] getAttachments() {
        return (Attachment[]) getMutableAttachments().toArray(new Attachment[0]);
    }

    List<Attachment> getMutableAttachments();

    default KotlinExceptionWithAttachments withAttachment(String name, Object content) {
        String string;
        name.getClass();
        List<Attachment> mutableAttachments = getMutableAttachments();
        if (content == null || (string = content.toString()) == null) {
            string = "<null>";
        }
        mutableAttachments.add(new Attachment(name, string));
        return this;
    }
}

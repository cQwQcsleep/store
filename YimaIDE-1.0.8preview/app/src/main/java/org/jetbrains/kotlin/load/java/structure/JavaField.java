package org.jetbrains.kotlin.load.java.structure;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/JavaField;", "Lorg/jetbrains/kotlin/load/java/structure/JavaMember;", "isEnumEntry", "", "()Z", "type", "Lorg/jetbrains/kotlin/load/java/structure/JavaType;", "getType", "()Lorg/jetbrains/kotlin/load/java/structure/JavaType;", "initializerValue", "", "getInitializerValue", "()Ljava/lang/Object;", "hasConstantNotNullInitializer", "getHasConstantNotNullInitializer", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface JavaField extends JavaMember {
    /* JADX INFO: renamed from: getHasConstantNotNullInitializer */
    boolean mo511getHasConstantNotNullInitializer();

    Object getInitializerValue();

    /* JADX INFO: renamed from: getType */
    JavaType mo513getType();

    /* JADX INFO: renamed from: isEnumEntry */
    boolean mo515isEnumEntry();
}

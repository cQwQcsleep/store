package org.jetbrains.kotlin.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/resolve/CollectionNames$Factories;", Argument.Delimiters.none, "<init>", "()V", "LIST_OF", "Lorg/jetbrains/kotlin/name/Name;", "getLIST_OF", "()Lorg/jetbrains/kotlin/name/Name;", "MUTABLE_LIST_OF", "getMUTABLE_LIST_OF", "SET_OF", "getSET_OF", "MUTABLE_SET_OF", "getMUTABLE_SET_OF", "SEQUENCE_OF", "getSEQUENCE_OF", "NAMES", Argument.Delimiters.none, "getNAMES", "()Ljava/util/List;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CollectionNames$Factories {
    public static final CollectionNames$Factories INSTANCE = new CollectionNames$Factories();
    private static final Name LIST_OF;
    private static final Name MUTABLE_LIST_OF;
    private static final Name MUTABLE_SET_OF;
    private static final List<Name> NAMES;
    private static final Name SEQUENCE_OF;
    private static final Name SET_OF;

    static {
        Name nameIdentifier = Name.identifier("listOf");
        nameIdentifier.getClass();
        LIST_OF = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("mutableListOf");
        nameIdentifier2.getClass();
        MUTABLE_LIST_OF = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("setOf");
        nameIdentifier3.getClass();
        SET_OF = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("mutableSetOf");
        nameIdentifier4.getClass();
        MUTABLE_SET_OF = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("sequenceOf");
        nameIdentifier5.getClass();
        SEQUENCE_OF = nameIdentifier5;
        NAMES = CollectionsKt.listOf(new Name[]{nameIdentifier, nameIdentifier2, nameIdentifier3, nameIdentifier4, nameIdentifier5});
    }

    private CollectionNames$Factories() {
    }

    public final Name getLIST_OF() {
        return LIST_OF;
    }

    public final Name getMUTABLE_LIST_OF() {
        return MUTABLE_LIST_OF;
    }

    public final Name getMUTABLE_SET_OF() {
        return MUTABLE_SET_OF;
    }

    public final List<Name> getNAMES() {
        return NAMES;
    }

    public final Name getSEQUENCE_OF() {
        return SEQUENCE_OF;
    }

    public final Name getSET_OF() {
        return SET_OF;
    }
}

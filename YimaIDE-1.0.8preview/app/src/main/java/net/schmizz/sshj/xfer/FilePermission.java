package net.schmizz.sshj.xfer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'USR_RWX' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class FilePermission {
    private static final /* synthetic */ FilePermission[] $VALUES;
    public static final FilePermission GRP_R;
    public static final FilePermission GRP_RWX;
    public static final FilePermission GRP_W;
    public static final FilePermission GRP_X;
    public static final FilePermission OTH_R;
    public static final FilePermission OTH_RWX;
    public static final FilePermission OTH_W;
    public static final FilePermission OTH_X;
    public static final FilePermission SGID;
    public static final FilePermission STICKY;
    public static final FilePermission SUID;
    public static final FilePermission USR_R;
    public static final FilePermission USR_RWX;
    public static final FilePermission USR_W;
    public static final FilePermission USR_X;
    private final int val;

    static {
        FilePermission filePermission = new FilePermission("USR_R", 0, 256);
        USR_R = filePermission;
        FilePermission filePermission2 = new FilePermission("USR_W", 1, 128);
        USR_W = filePermission2;
        FilePermission filePermission3 = new FilePermission("USR_X", 2, 64);
        USR_X = filePermission3;
        FilePermission filePermission4 = new FilePermission("GRP_R", 3, 32);
        GRP_R = filePermission4;
        FilePermission filePermission5 = new FilePermission("GRP_W", 4, 16);
        GRP_W = filePermission5;
        FilePermission filePermission6 = new FilePermission("GRP_X", 5, 8);
        GRP_X = filePermission6;
        FilePermission filePermission7 = new FilePermission("OTH_R", 6, 4);
        OTH_R = filePermission7;
        FilePermission filePermission8 = new FilePermission("OTH_W", 7, 2);
        OTH_W = filePermission8;
        FilePermission filePermission9 = new FilePermission("OTH_X", 8, 1);
        OTH_X = filePermission9;
        FilePermission filePermission10 = new FilePermission("SUID", 9, PKIFailureInfo.wrongIntegrity);
        SUID = filePermission10;
        FilePermission filePermission11 = new FilePermission("SGID", 10, 1024);
        SGID = filePermission11;
        FilePermission filePermission12 = new FilePermission("STICKY", 11, 512);
        STICKY = filePermission12;
        FilePermission filePermission13 = new FilePermission("USR_RWX", 12, filePermission, filePermission2, filePermission3);
        USR_RWX = filePermission13;
        FilePermission filePermission14 = new FilePermission("GRP_RWX", 13, filePermission4, filePermission5, filePermission6);
        GRP_RWX = filePermission14;
        FilePermission filePermission15 = new FilePermission("OTH_RWX", 14, filePermission7, filePermission8, filePermission9);
        OTH_RWX = filePermission15;
        $VALUES = new FilePermission[]{filePermission, filePermission2, filePermission3, filePermission4, filePermission5, filePermission6, filePermission7, filePermission8, filePermission9, filePermission10, filePermission11, filePermission12, filePermission13, filePermission14, filePermission15};
    }

    private FilePermission(String str, int i, FilePermission... filePermissionArr) {
        super(str, i);
        int i2 = 0;
        for (FilePermission filePermission : filePermissionArr) {
            i2 |= filePermission.val;
        }
        this.val = i2;
    }

    public static Set<FilePermission> fromMask(int i) {
        LinkedList linkedList = new LinkedList();
        for (FilePermission filePermission : values()) {
            if (filePermission.isIn(i)) {
                linkedList.add(filePermission);
            }
        }
        return new HashSet(linkedList);
    }

    public static int toMask(Set<FilePermission> set) {
        Iterator<FilePermission> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= it.next().val;
        }
        return i;
    }

    public static FilePermission valueOf(String str) {
        return (FilePermission) Enum.valueOf(FilePermission.class, str);
    }

    public static FilePermission[] values() {
        return (FilePermission[]) $VALUES.clone();
    }

    public boolean isIn(int i) {
        int i2 = this.val;
        return (i & i2) == i2;
    }

    private FilePermission(String str, int i, int i2) {
        super(str, i);
        this.val = i2;
    }
}

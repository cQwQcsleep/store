package net.schmizz.sshj.connection.channel.direct;

import java.util.Map;
import net.schmizz.sshj.common.Buffer;
import org.jetbrains.kotlin.backend.wasm.serialization.ImmediateTags;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum PTYMode {
    VINTR(1),
    VQUIT(2),
    VERASE(3),
    VKILL(4),
    VEOF(5),
    VEOL(6),
    VEOL2(7),
    VSTART(8),
    VSTOP(9),
    VSUSP(10),
    VDSUSP(11),
    VREPRINT(12),
    VWERASE(13),
    VLNEXT(14),
    VFLUSH(15),
    VSWTCH(16),
    VSTATUS(17),
    VDISCARD(18),
    IGNPAR(30),
    PARMRK(31),
    INPCK(32),
    ISTRIP(33),
    INLCR(34),
    IGNCR(35),
    ICRNL(36),
    IUCLC(37),
    IXON(38),
    IXANY(39),
    IXOFF(40),
    IMAXBEL(41),
    ISIG(50),
    ICANON(51),
    XCASE(52),
    ECHO(53),
    ECHOE(54),
    ECHOK(55),
    ECHONL(56),
    NOFLSH(57),
    TOSTOP(58),
    IEXTEN(59),
    ECHOCTL(60),
    ECHOKE(61),
    PENDIN(62),
    OPOST(70),
    OLCUC(71),
    ONLCR(72),
    OCRNL(73),
    ONOCR(74),
    ONLRET(75),
    CS7(90),
    CS8(91),
    PARENB(92),
    PARODD(93),
    TTY_OP_ISPEED(128),
    TTY_OP_OSPEED(ImmediateTags.BLOCK_TYPE_NULL_VALUE);

    private final byte opcode;

    PTYMode(int i) {
        this.opcode = (byte) i;
    }

    public static byte[] encode(Map<PTYMode, Integer> map) {
        Buffer.PlainBuffer plainBuffer = new Buffer.PlainBuffer();
        for (Map.Entry<PTYMode, Integer> entry : map.entrySet()) {
            plainBuffer.putByte(entry.getKey().getOpcode());
            plainBuffer.putUInt32(entry.getValue().intValue());
        }
        plainBuffer.putByte((byte) 0);
        return plainBuffer.getCompactData();
    }

    public byte getOpcode() {
        return this.opcode;
    }
}

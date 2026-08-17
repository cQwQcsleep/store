package org.jline.terminal;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jline.terminal.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Attributes {
    final EnumSet<InputFlag> iflag = EnumSet.noneOf(InputFlag.class);
    final EnumSet<OutputFlag> oflag = EnumSet.noneOf(OutputFlag.class);
    final EnumSet<ControlFlag> cflag = EnumSet.noneOf(ControlFlag.class);
    final EnumSet<LocalFlag> lflag = EnumSet.noneOf(LocalFlag.class);
    final EnumMap<ControlChar, Integer> cchars = new EnumMap<>(ControlChar.class);

    public enum ControlChar {
        VEOF,
        VEOL,
        VEOL2,
        VERASE,
        VWERASE,
        VKILL,
        VREPRINT,
        VINTR,
        VQUIT,
        VSUSP,
        VDSUSP,
        VSTART,
        VSTOP,
        VLNEXT,
        VDISCARD,
        VMIN,
        VTIME,
        VSTATUS
    }

    public enum ControlFlag {
        CIGNORE,
        CS5,
        CS6,
        CS7,
        CS8,
        CSTOPB,
        CREAD,
        PARENB,
        PARODD,
        HUPCL,
        CLOCAL,
        CCTS_OFLOW,
        CRTS_IFLOW,
        CDTR_IFLOW,
        CDSR_OFLOW,
        CCAR_OFLOW
    }

    public enum InputFlag {
        IGNBRK,
        BRKINT,
        IGNPAR,
        PARMRK,
        INPCK,
        ISTRIP,
        INLCR,
        IGNCR,
        ICRNL,
        IXON,
        IXOFF,
        IXANY,
        IMAXBEL,
        IUTF8
    }

    public enum LocalFlag {
        ECHOKE,
        ECHOE,
        ECHOK,
        ECHO,
        ECHONL,
        ECHOPRT,
        ECHOCTL,
        ISIG,
        ICANON,
        ALTWERASE,
        IEXTEN,
        EXTPROC,
        TOSTOP,
        FLUSHO,
        NOKERNINFO,
        PENDIN,
        NOFLSH
    }

    public enum OutputFlag {
        OPOST,
        ONLCR,
        OXTABS,
        ONOEOT,
        OCRNL,
        ONOCR,
        ONLRET,
        OFILL,
        NLDLY,
        TABDLY,
        CRDLY,
        FFDLY,
        BSDLY,
        VTDLY,
        OFDEL
    }

    public Attributes(Attributes attributes) {
        copy(attributes);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends Enum<T>> String append(EnumSet<T> enumSet, Function<T, String> function) {
        return (String) enumSet.stream().map(function).collect(Collectors.joining(" "));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String display(ControlChar controlChar) {
        String string;
        int controlChar2 = getControlChar(controlChar);
        if (controlChar == ControlChar.VMIN || controlChar == ControlChar.VTIME) {
            string = Integer.toString(controlChar2);
        } else if (controlChar2 < 0) {
            string = "<undef>";
        } else if (controlChar2 < 32) {
            string = "^" + ((char) (controlChar2 + 64));
        } else if (controlChar2 == 127) {
            string = "^?";
        } else {
            string = controlChar2 >= 128 ? String.format("\\u%04x", Integer.valueOf(controlChar2)) : String.valueOf((char) controlChar2);
        }
        return controlChar.name().toLowerCase().substring(1) + "=" + string;
    }

    public void copy(Attributes attributes) {
        setControlFlags(attributes.getControlFlags());
        setInputFlags(attributes.getInputFlags());
        setLocalFlags(attributes.getLocalFlags());
        setOutputFlags(attributes.getOutputFlags());
        setControlChars(attributes.getControlChars());
    }

    public int getControlChar(ControlChar controlChar) {
        Integer num = this.cchars.get(controlChar);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public EnumMap<ControlChar, Integer> getControlChars() {
        return this.cchars;
    }

    public EnumSet<ControlFlag> getControlFlags() {
        return this.cflag;
    }

    public boolean getInputFlag(InputFlag inputFlag) {
        return this.iflag.contains(inputFlag);
    }

    public EnumSet<InputFlag> getInputFlags() {
        return this.iflag;
    }

    public boolean getLocalFlag(LocalFlag localFlag) {
        return this.lflag.contains(localFlag);
    }

    public EnumSet<LocalFlag> getLocalFlags() {
        return this.lflag;
    }

    public EnumSet<OutputFlag> getOutputFlags() {
        return this.oflag;
    }

    public void setControlChar(ControlChar controlChar, int i) {
        this.cchars.put(controlChar, Integer.valueOf(i));
    }

    public void setControlChars(EnumMap<ControlChar, Integer> enumMap) {
        this.cchars.clear();
        this.cchars.putAll(enumMap);
    }

    public void setControlFlags(EnumSet<ControlFlag> enumSet) {
        this.cflag.clear();
        this.cflag.addAll(enumSet);
    }

    public void setInputFlag(InputFlag inputFlag, boolean z) {
        EnumSet<InputFlag> enumSet = this.iflag;
        if (z) {
            enumSet.add(inputFlag);
        } else {
            enumSet.remove(inputFlag);
        }
    }

    public void setInputFlags(EnumSet<InputFlag> enumSet, boolean z) {
        EnumSet<InputFlag> enumSet2 = this.iflag;
        if (z) {
            enumSet2.addAll(enumSet);
        } else {
            enumSet2.removeAll(enumSet);
        }
    }

    public void setLocalFlags(EnumSet<LocalFlag> enumSet, boolean z) {
        EnumSet<LocalFlag> enumSet2 = this.lflag;
        if (z) {
            enumSet2.addAll(enumSet);
        } else {
            enumSet2.removeAll(enumSet);
        }
    }

    public void setOutputFlags(EnumSet<OutputFlag> enumSet) {
        this.oflag.clear();
        this.oflag.addAll(enumSet);
    }

    public String toString() {
        return "Attributes[lflags: " + append(this.lflag) + ", iflags: " + append(this.iflag) + ", oflags: " + append(this.oflag) + ", cflags: " + append(this.cflag) + ", cchars: " + append(EnumSet.allOf(ControlChar.class), new Function() { // from class: hj0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.display((Attributes.ControlChar) obj);
            }
        }) + "]";
    }

    public void setInputFlags(EnumSet<InputFlag> enumSet) {
        this.iflag.clear();
        this.iflag.addAll(enumSet);
    }

    public void setLocalFlags(EnumSet<LocalFlag> enumSet) {
        this.lflag.clear();
        this.lflag.addAll(enumSet);
    }

    private <T extends Enum<T>> String append(EnumSet<T> enumSet) {
        return append(enumSet, new Function() { // from class: gj0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Enum) obj).name().toLowerCase();
            }
        });
    }

    public Attributes() {
    }
}

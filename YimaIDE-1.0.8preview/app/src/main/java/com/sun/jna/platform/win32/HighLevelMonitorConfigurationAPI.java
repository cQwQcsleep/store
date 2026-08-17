package com.sun.jna.platform.win32;

import com.sun.jna.platform.EnumUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface HighLevelMonitorConfigurationAPI {

    public enum MC_CAPS implements FlagEnum {
        MC_CAPS_NONE(0),
        MC_CAPS_MONITOR_TECHNOLOGY_TYPE(1),
        MC_CAPS_BRIGHTNESS(2),
        MC_CAPS_CONTRAST(4),
        MC_CAPS_COLOR_TEMPERATURE(8),
        MC_CAPS_RED_GREEN_BLUE_GAIN(16),
        MC_CAPS_RED_GREEN_BLUE_DRIVE(32),
        MC_CAPS_DEGAUSS(64),
        MC_CAPS_DISPLAY_AREA_POSITION(128),
        MC_CAPS_DISPLAY_AREA_SIZE(256),
        MC_CAPS_RESTORE_FACTORY_DEFAULTS(1024),
        MC_CAPS_RESTORE_FACTORY_COLOR_DEFAULTS(2048),
        MC_RESTORE_FACTORY_DEFAULTS_ENABLES_MONITOR_SETTINGS(4096);

        private int flag;

        MC_CAPS(int i) {
            this.flag = i;
        }

        @Override // com.sun.jna.platform.win32.FlagEnum
        public int getFlag() {
            return this.flag;
        }
    }

    public enum MC_DRIVE_TYPE {
        MC_RED_DRIVE,
        MC_GREEN_DRIVE,
        MC_BLUE_DRIVE
    }

    public enum MC_GAIN_TYPE {
        MC_RED_GAIN,
        MC_GREEN_GAIN,
        MC_BLUE_GAIN
    }

    public enum MC_POSITION_TYPE {
        MC_HORIZONTAL_POSITION,
        MC_VERTICAL_POSITION
    }

    public enum MC_SIZE_TYPE {
        MC_WIDTH,
        MC_HEIGHT
    }

    public enum MC_SUPPORTED_COLOR_TEMPERATURE implements FlagEnum {
        MC_SUPPORTED_COLOR_TEMPERATURE_NONE(0),
        MC_SUPPORTED_COLOR_TEMPERATURE_4000K(1),
        MC_SUPPORTED_COLOR_TEMPERATURE_5000K(2),
        MC_SUPPORTED_COLOR_TEMPERATURE_6500K(4),
        MC_SUPPORTED_COLOR_TEMPERATURE_7500K(8),
        MC_SUPPORTED_COLOR_TEMPERATURE_8200K(16),
        MC_SUPPORTED_COLOR_TEMPERATURE_9300K(32),
        MC_SUPPORTED_COLOR_TEMPERATURE_10000K(64),
        MC_SUPPORTED_COLOR_TEMPERATURE_11500K(128);

        private int flag;

        MC_SUPPORTED_COLOR_TEMPERATURE(int i) {
            this.flag = i;
        }

        @Override // com.sun.jna.platform.win32.FlagEnum
        public int getFlag() {
            return this.flag;
        }
    }

    public enum MC_COLOR_TEMPERATURE {
        MC_COLOR_TEMPERATURE_UNKNOWN,
        MC_COLOR_TEMPERATURE_4000K,
        MC_COLOR_TEMPERATURE_5000K,
        MC_COLOR_TEMPERATURE_6500K,
        MC_COLOR_TEMPERATURE_7500K,
        MC_COLOR_TEMPERATURE_8200K,
        MC_COLOR_TEMPERATURE_9300K,
        MC_COLOR_TEMPERATURE_10000K,
        MC_COLOR_TEMPERATURE_11500K;

        public static class ByReference extends com.sun.jna.ptr.ByReference {
            public ByReference() {
                super(4);
                getPointer().setInt(0L, -1);
            }

            public MC_COLOR_TEMPERATURE getValue() {
                return (MC_COLOR_TEMPERATURE) EnumUtils.fromInteger(getPointer().getInt(0L), MC_COLOR_TEMPERATURE.class);
            }

            public void setValue(MC_COLOR_TEMPERATURE mc_color_temperature) {
                getPointer().setInt(0L, EnumUtils.toInteger(mc_color_temperature));
            }

            public ByReference(MC_COLOR_TEMPERATURE mc_color_temperature) {
                super(4);
                setValue(mc_color_temperature);
            }
        }
    }

    public enum MC_DISPLAY_TECHNOLOGY_TYPE {
        MC_SHADOW_MASK_CATHODE_RAY_TUBE,
        MC_APERTURE_GRILL_CATHODE_RAY_TUBE,
        MC_THIN_FILM_TRANSISTOR,
        MC_LIQUID_CRYSTAL_ON_SILICON,
        MC_PLASMA,
        MC_ORGANIC_LIGHT_EMITTING_DIODE,
        MC_ELECTROLUMINESCENT,
        MC_MICROELECTROMECHANICAL,
        MC_FIELD_EMISSION_DEVICE;

        public static class ByReference extends com.sun.jna.ptr.ByReference {
            public ByReference() {
                super(4);
                getPointer().setInt(0L, -1);
            }

            public MC_DISPLAY_TECHNOLOGY_TYPE getValue() {
                return (MC_DISPLAY_TECHNOLOGY_TYPE) EnumUtils.fromInteger(getPointer().getInt(0L), MC_DISPLAY_TECHNOLOGY_TYPE.class);
            }

            public void setValue(MC_DISPLAY_TECHNOLOGY_TYPE mc_display_technology_type) {
                getPointer().setInt(0L, EnumUtils.toInteger(mc_display_technology_type));
            }

            public ByReference(MC_DISPLAY_TECHNOLOGY_TYPE mc_display_technology_type) {
                super(4);
                setValue(mc_display_technology_type);
            }
        }
    }
}

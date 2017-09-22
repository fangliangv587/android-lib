package com.xz.momo.common.date;

/**
     * 枚举类型，所有的时间格式
     */
    public enum Format {

        YMD("yyyy-MM-dd"),
        HMS("HH:mm:ss"),
        YMDHM("yyyy-MM-dd HH:mm"),
        YMDHMS("yyyy-MM-dd HH:mm:ss");

        private String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
package com.maoyupeng.common.base.exception;


/**
 * @author mao
 */
@SuppressWarnings("unused")
public interface ResultErrorType {
    final class System {
        private System() {
            throw new IllegalStateException("Utility System class");
        }

        public static final ResultErrorCodeI BAD_REQUEST = new ResultErrorCodeImpl(400, "Bad Request.");
        public static final ResultErrorCodeI UNAUTHORIZED = new ResultErrorCodeImpl(401, "Unauthorized.");
        public static final ResultErrorCodeI FORBIDDEN = new ResultErrorCodeImpl(403, "Forbidden.");
        public static final ResultErrorCodeI INTERNAL_SERVER_ERROR = new ResultErrorCodeImpl(500, "Internal Server Error.");
        public static final ResultErrorCodeI SUCCESS = new ResultErrorCodeImpl(200, "Success.");
    }

    final class Param {
        private Param() {
            throw new IllegalStateException("Utility Param class");
        }

        public static final ResultErrorCodeI PAGE_PARAMETER_FAIL = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.SYSTEM, ErrorTypeEnum.PARAMETER_FAIL), "Missing paging parameter.");
        public static final ResultErrorCodeI PARAMETER_VERIFICATION_FAIL = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.SYSTEM, ErrorTypeEnum.VERIFICATION_FAIL), "Parameter verification fail.");
    }

    final class Jwt {
        private Jwt() {
            throw new IllegalStateException("Utility Jwt class");
        }

        public static final ResultErrorCodeI JWT_EXPIRED = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.SYSTEM_TOKEN, ErrorTypeEnum.EXPIRED), "Token expired.");
    }

    final class Org {
        private Org() {
            throw new IllegalStateException("Utility Org class");
        }

        public static final ResultErrorCodeI ORG_NOT_FOUND = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.ORG, ErrorTypeEnum.NOT_FOUND), "Not found organization.");
        public static final ResultErrorCodeI ORG_DISABLED = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.ORG, ErrorTypeEnum.DISABLED), "Organization has been disabled.");
    }

    final class Common {
        private Common() {
            throw new IllegalStateException("Utility Common class");
        }

        public static final ResultErrorCodeI NOT_FOUND = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.COMMON, ErrorTypeEnum.NOT_FOUND), "Not found exception.");
        public static final ResultErrorCodeI ALREADY_EXIST = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.COMMON, ErrorTypeEnum.EXIST), "Record exist.");
        public static final ResultErrorCodeI DISABLED = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.COMMON, ErrorTypeEnum.DISABLED), "Target has been disabled.");
        public static final ResultErrorCodeI STATUS_FAIL = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.COMMON, ErrorTypeEnum.STATUS), "Target has been disabled.");
        public static final ResultErrorCodeI IMMUTABLE_FAIL = new ResultErrorCodeImpl(codeBuilder(ErrorModuleEnum.COMMON, ErrorTypeEnum.IMMUTABLE), "Target immutable.");
    }

    enum ErrorModuleEnum {
        /**
         * ??????
         */
        SYSTEM("100"),
        /**
         * ??????token
         */
        SYSTEM_TOKEN("110"),
        /**
         * ??????
         */
        COMMON("200"),
        /**
         * ??????
         */
        ORG("300"),
        /**
         * ??????
         */
        ORG_PROJECT("301"),

        ;

        private final String value;

        ErrorModuleEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum ErrorTypeEnum {
        /**
         * ???????????????
         */
        EXPIRED("01"),
        /**
         * ????????????
         */
        STATUS("03"),
        /**
         * ???????????????
         */
        NOT_FOUND("04"),
        /**
         * ?????????
         */
        EXIST("05"),
        /**
         * ???????????????
         */
        DISABLED("06"),
        /**
         * ????????????
         */
        IMMUTABLE("07"),
        /**
         * ????????????
         */
        PARAMETER_FAIL("08"),
        /**
         * ??????????????????
         */
        VERIFICATION_FAIL("09"),
        ;

        private final String value;

        ErrorTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * ??????????????????
     *
     * @param module ??????
     * @param type   ??????
     * @return ?????????
     */
    static Integer codeBuilder(ErrorModuleEnum module, ErrorTypeEnum type) {
        String result = module.getValue() + type.getValue();
        return Integer.valueOf(result);
    }
}

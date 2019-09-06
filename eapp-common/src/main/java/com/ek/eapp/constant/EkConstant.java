package com.ek.eapp.constant;

/**
 * @ClassName: EkConstant
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-06
 * @Version: V2.0
 **/

public class EkConstant {

    public static final String STATUS_INIT = "00";   // 初始化已提交
    public static final String STATUS_AGREE = "10";  // 同意
    public static final String STATUS_REFUSE = "20"; // 拒绝
    public static final String STATUS_OTHER = "99";  // 其他

    public static final String DD_STATUS_AGREE = "agree";   //钉钉的审批状态-同意
    public static final String DD_STATUS_REFUSE = "refuse"; //钉钉的审批状态-拒绝

    public enum EappStaus {
        Agree(EkConstant.DD_STATUS_AGREE),
        Refuse(EkConstant.DD_STATUS_REFUSE);

        EappStaus(String status) {
            this.status = status.equals(EkConstant.DD_STATUS_AGREE)
                    ? EkConstant.STATUS_AGREE : status.equals(EkConstant.DD_STATUS_REFUSE)
                    ? EkConstant.STATUS_REFUSE : EkConstant.STATUS_OTHER;
        }

        private final String status;

        public String getStatus() {
            return this.status;
        }
    }

}

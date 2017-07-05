package io.happylrd.youbo.common;

/**
 * Principle: convention over configuration
 */
public class ModelConst {

    /**
     * just for User
     */
    public interface Role {
        int NORMAL = 0;
        int ADMIN = 1;
    }

    /**
     * NORMAL for 普通成员
     * ADMIN for 管理员
     * ADMIN_SU for 创建者
     */
    public interface GroupMemberRole {
        int NORMAL = 0;
        int ADMIN = 1;
        int ADMIN_SU = 100;
    }

    public interface Gender {
        int UNKNOWN = 0;
        int MALE = 1;
        int FEMALE = 2;
    }

    public interface FragmentType {
        int IMAGE = 1;
        int AUDIO = 2;
        int VIDEO = 3;
    }
}

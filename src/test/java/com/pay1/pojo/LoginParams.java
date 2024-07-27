package com.pay1.pojo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginParams {
    private final String app_name;
    private final String app_type;
    private final String password;
    private final String mobile;
    private final String app_version;
    private final String version_code;
    private final String uuid;
    private final String method;

    public LoginParams(String app_name, String app_type, String password, String mobile, String app_version, String version_code, String uuid, String method) {
        this.app_name = app_name;
        this.app_type = app_type;
        this.password = password;
        this.mobile = mobile;
        this.app_version = app_version;
        this.version_code = version_code;
        this.uuid = uuid;
        this.method = method;
    }

}

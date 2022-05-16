package com.senko.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类
 * @author senko
 * @date 2022/5/16 10:51
 */
@Deprecated
//@Component
//@ConfigurationProperties(prefix = "senko")
public class SenkoBlogConfiguration {
    private final Upload upload = new Upload();

    public Upload getUpload() {
        return upload;
    }

    public static class Upload {
        /**
         * 上传模式：[local | oss]
         */
        private String mode = "local";

        private final Local local = new Local();

        public Local getLocal() {
            return local;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public class Local {
            /**
             * Nginx地址
             */
            private String url;

            /**
             * 文件存储路径
             */
            private String path;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

}

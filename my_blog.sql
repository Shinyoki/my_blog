/*
 Navicat Premium Data Transfer

 Source Server         : local'
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : my_blog

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 18/04/2023 19:51:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '作者',
  `category_id` int NULL DEFAULT NULL COMMENT '文章分类',
  `article_cover` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章缩略图',
  `article_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `article_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '文章类型 1原创 2转载 3翻译',
  `original_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原文链接',
  `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶 0否 1是',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态值 1公开 2私密 3评论可见',
  `create_time` datetime NOT NULL COMMENT '发表时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2088804355 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES (-2034233343, 1, NULL, '', '2022-05-17', 'csa', 1, '', 0, 0, 3, '2022-05-17 15:55:35', NULL);
INSERT INTO `tb_article` VALUES (-1484742654, 1, 163581953, 'http://localhost:8444/街道.png', '2022-05-19 awd', 'das', 1, '', 0, 0, 1, '2022-05-19 10:27:41', NULL);
INSERT INTO `tb_article` VALUES (-1451188223, 1, 163581953, 'http://localhost:8444/风车.png', '2022-05-19 dsa432', 'adwdawd23r', 1, '', 0, 0, 1, '2022-05-19 10:28:15', NULL);
INSERT INTO `tb_article` VALUES (-1073700862, 1, 163581953, 'http://localhost:8444/街道.png', '2022-05-19 dsa432', 'adwdawd23r', 1, '', 0, 0, 1, '2022-05-19 10:28:17', NULL);
INSERT INTO `tb_article` VALUES (-507494399, 1, 188, 'http://localhost:8444/欧村.png', '2022-05-19', 'vsvsd', 1, '', 0, 1, 1, '2022-05-19 15:46:43', '2022-05-24 07:30:42');
INSERT INTO `tb_article` VALUES (-150953982, 1, 163581953, 'http://localhost:8444/山.png', '2022-05-19 1', '1', 1, '', 1, 1, 1, '2022-05-19 10:27:09', '2022-05-19 16:18:28');
INSERT INTO `tb_article` VALUES (-50290687, 1, 163581953, 'http://localhost:8444/水稻.png', '2022-05-19 awd1', 'das', 1, '', 0, 0, 1, '2022-05-19 10:27:46', NULL);
INSERT INTO `tb_article` VALUES (54, 1, 187, 'http://localhost:8444/雪山.png', '测试文章', '恭喜你成功运行博客，开启你的文章之旅吧。', 2, '', 0, 0, 1, '2022-01-24 23:33:56', '2022-05-19 07:11:23');
INSERT INTO `tb_article` VALUES (56, 1, 187, 'http://localhost:8444/街道.png', '测试文章3', '233，开启你的文章之旅吧。', 3, '', 1, 0, 1, '2022-05-09 15:48:58', '2022-05-18 18:55:41');
INSERT INTO `tb_article` VALUES (62, 1, 188, 'http://localhost:8444/风车.png', '2022-05-17', 'sdsadas', 2, '', 0, 0, 1, '2022-05-17 13:46:55', '2022-05-19 07:34:27');
INSERT INTO `tb_article` VALUES (163581954, 1, 163581953, 'http://localhost:8444/街道.png', '2022-05-17 今日测试', '别报错了，呜呜呜', 1, '', 0, 0, 1, '2022-05-17 14:42:11', '2022-05-19 07:34:21');
INSERT INTO `tb_article` VALUES (595611649, 1, 188, 'http://localhost:8444/欧村.png', '2022-05-19', 'jinrifen', 1, '', 1, 1, 1, '2022-05-19 07:33:35', '2022-05-19 10:25:35');
INSERT INTO `tb_article` VALUES (717266945, 1, 163581953, 'http://localhost:8444/山.png', '2022-05-19 awd1231', 'das', 2, '', 0, 0, 1, '2022-05-19 10:27:51', NULL);
INSERT INTO `tb_article` VALUES (1904226305, 1, 188, 'http://localhost:8444/水稻.png', '2022-05-19', 'cascascas', 1, '', 1, 0, 2, '2022-05-19 15:50:54', NULL);
INSERT INTO `tb_article` VALUES (2088804353, 1, 163581953, 'http://localhost:8444/雪山.png', '测试文章233', '# [Vuetify](https://vuetifyjs.com/zh-Hans/getting-started/installation/)\n\n## 安装：\n\n- vuetify依赖\n\n```js\n// dependency\n\"vuetify\": \"^2.6.4\",\n```\n\n- vuetify-loader依赖\n\n```js\n// dev dependency\n\"vuetify-loader\": \"^1.7.3\"\n```\n\n- vue cli vuetify依赖\n\n```json\n// dev dependency\n\"vue-cli-plugin-vuetify\": \"^2.5.1\",\n```\n\n- sass依赖\n\n```bash\nnpm install sass@~1.32 sass-loader deepmerge -D\n```\n\n- 新建`plugins`文件夹并创建`vuetify.js`\n\n```js\n// src/plugins/vuetify.js\n\nimport Vue from \'vue\'\nimport Vuetify from \'vuetify/lib\'\n\nVue.use(Vuetify)\n\nconst opts = {}\n\nexport default new Vuetify(opts)\n```\n\n- 挂载给Vue\n\n```js\n// src/main.js\n\nimport Vue from \'vue\'\nimport vuetify from \'@/plugins/vuetify\' // path to vuetify export\n\nnew Vue({\n  vuetify,\n}).$mount(\'#app\')\n```\n\n- 安装字体\n\n```html\n<!-- index.html -->\n\n<link href=\"https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900\" rel=\"stylesheet\">\n<link href=\"https://cdn.jsdelivr.net/npm/@mdi/font@6.x/css/materialdesignicons.min.css\" rel=\"stylesheet\">\n```\n\n- 可能丢失的material 图标\n\n```bash\nnpm install @mdi/font -D\n```\n\n```js\nimport \'@mdi/font/css/materialdesignicons.css\'\n```\n\n## 推荐\n\n- 一个非常好看的滚动栏\n\n```css\n::-webkit-scrollbar {\n    width: 8px;\n    height: 8px;\n}\n\n::-webkit-scrollbar-track {\n    background-cfolor: rgba(73, 177, 245, 0.2);\n    border-radius: 2em;\n}\n\n::-webkit-scrollbar-thumb {\n    background-color: #49b1f5;\n    background-image: -webkit-linear-gradient(\n            45deg,\n            rgba(255, 255, 255, 0.4) 25%,\n            transparent 25%,\n            transparent 50%,\n            rgba(255, 255, 255, 0.4) 50%,\n            rgba(255, 255, 255, 0.4) 75%,\n            transparent 75%,\n            transparent\n    );\n    border-radius: 2em;\n}\n\n::-webkit-scrollbar-corner {\n    background-color: transparent;\n}\n\n::-moz-selection {\n    color: #fff;\n    background-color: #49b1f5;\n}\n```\n\n- 非常好看的鼠标样式\n\n```css\nbody {\n    cursor: url(https://static.talkxj.com/css/default.cur), default;\n}\n\na, button, img {\n    cursor: url(https://static.talkxj.com/css/pointer.cur), default;\n}\n```\n\n## App.vue\n\n```vue\n<template>\n	<!-- 由v-app包裹 -->\n  <v-app>\n   \n    <!-- 内容写在main里-->\n    <v-main>\n      <router-view :key=\"this.$route.fullPath\" />\n    </v-main>\n      \n  </v-app>\n</template>\n```\n\n# 基础\n\n## 字体和背景[颜色](https://vuetifyjs.com/zh-Hans/styles/colors/)\n\n在各种组件或dom元素种，`class`属性里写 `red`这样的颜色，会导致背景发生变化，并且可以通过`lighten-数字`来改变层级。\n\n```html\n<div class=\"purple darken-2\"></div> //改变背景颜色\n```\n\n同样可以写`颜色--text`来改变文本颜色。\n\n```html\n<div class=\"white--text\"></div>		//改变文本颜色\n```\n\n## [主题](https://vuetifyjs.com/zh-Hans/features/theme/#section-6d458272548c6df18272)\n\n主题可以在一开始就设置。\n\n```js\nexport default new Vuetify({\n  theme: { \n      dark: true \n  },\n})\n```\n\n并且可以具体细分某个主题的各类色彩。\n\n```js\ntheme: {\n    themes: {\n      light: {\n     	  primary: \'#1976D2\',\n          secondary: \'#424242\',\n          accent: \'#82B1FF\',\n          error: \'#FF5252\',\n          info: \'#2196F3\',\n          success: \'#4CAF50\',\n          warning: \'#FFC107\',\n      },\n    },\n  },\n```\n\nMaterialDesign的默认色存储在指定文件中，可以拿来用\n\n```js\nimport colors from \'vuetify/lib/util/colors\'\n```\n\n可以通过`$vuetify`锚点来动态修改主题\n\n```js\n// Light theme\nthis.$vuetify.theme.themes.light.primary = \'#4caf50\'\n\n// Dark theme\nthis.$vuetify.theme.themes.dark.primary = \'#4caf50\'\n\nthis.$vuetify.theme = {dark: true}\n```\n\n## [响应式显示](https://vuetifyjs.com/zh-Hans/features/breakpoints/)\n\n可以在`class`属性中，以一定的格式来指定哪些设备可以显示该组件。\n\n![](https://gcore.jsdelivr.net/gh/Shinyoki/images_repository/blog_images/20220708215839.png)\n\n### 辅助类\n\n`d`也就是`device`\n\n- `.d-{value}` 用于 `xs`\n- `.d-{breakpoint}-{value}` 用于 `sm`, `md`, `lg` 和 `xl`\n\n该 value 属性的值是以下之一：\n\n- `none`\n- `inline`\n- `inline-block`\n- `block`\n- `table`\n- `table-cell`\n- `table-row`\n- `flex`\n- `inline-flex`\n\n此外, 您还可以使用横向显示辅助类基于当前 **viewport** 宽度上限来显示元素。 这些类可以使用以下格式 `hidden-{breakpoint}-{condition}` 使用。\n\n基于以下 条件 应用类:\n\n- `only` - 只在 `xs` 至 `xl` 断点隐藏元素\n- `and-down` - 在指定的断点和以下隐藏元素, 从 `sm` 到 `lg` 断点\n- `and-up` - 在指定的断点和以上隐藏元素, 从 `sm` 到 `lg` 断点\n\n此外, 可以使用 `only` 条件确定目标 **媒体类型** 。 目前支持 `hidden-screen-only` 和 `hidden-print-only` 。\n\n```html\n<!-- 中等屏幕及以下才显示 -->\n<v-btn\n       class=\"hidden-md-and-up\"> \n	点我\n</v-btn>\n```\n\n> 补充：\n>\n> ```html\n> <v-col class=\"d-md-block d-none\"/>\n> ```\n>\n> 可以在小于md设备时将这个col隐藏\n\n## [空间间距类](https://codingbeautydev.com/blog/vuetify-margin-and-padding/)\n\n\n\n## [过渡动画](https://vuetifyjs.com/zh-Hans/styles/transitions/#section-4f7f7528)\n\n加上一个`transition=\"slide-x-transition\"`属性就好了。\n\n\n\n# 国际化\n\n## 自定义映射文本\n\n- en.js\n\n```js\nexport default {\n    application: \'application\',\n    help: \'help\',\n    aaa: \'aaa\'\n}\n```\n\n- zhHans\n\n```\nexport default {\n    application: \'应用\',\n    help: \'帮助\'\n}\n```\n\n- 公共引用\n\n```js\nimport en from \"@/locale/en\"\nimport zhHans from \"@/locale/zhHans\";\n\nexport {en, zhHans}\n```\n\n- vuetify.js\n\n```js\nimport { en, zhHans } from \'@/locale/locales\'\n\nexport default createVuetify({\n    locale: {\n        locale: \'zhHans\',\n        fallback: \'sv\',\n        messages: { zhHans, en }\n    }\n})\n```\n\n- 使用\n\n```js\n// 取值\n{{ this.$vuetify.locale.t(\"$vuetify.help\") }}\n\n// 切换\nthis.$vuetify.locale.current = \'en\'\n```\n\n\n\n# 技巧\n\n- `fill-height`： 子元素填充父级\n\n---\n\n### [点击动画](https://vuetifyjs.com/zh-Hans/directives/ripple/#section-81ea5b9a4e4982725f69)\n\n- v-card元素加上@click监听事件后，就可以自带点击动画\n- 还有就是给标签加上`  v-ripple=\"{}\"`\n\n> 中间迸发的：v-ripple=\"{center: true}\"\n>\n> 带有颜色的：v-ripple=\"{class: \"blue--text\"}\"\n\n---\n\n### 点击外部\n\n给标签加上 `v-click-outside=\"method\"`就可以开启`监听点击外部元素`事件。\n\n---\n\n### 视野之内\n\n给标签加上`v-intersect=\"method\"`就可以`监听是否在视野之内`\n\n---\n\n### 发生变化\n\n有点类似于`@input`，这里是给标签加上`v-mutate=\"method\"`来监听当元素发生变化时。不过可惜只能是监听发生了变化。\n\n---\n\n### 移动端滑动\n\n需要在标签上`v-touch=\"{left=>{}, right.....}\"`来分别监听对应的滑动方向。\n\n```vue\n v-touch=\"{\n  left: () => callback(\'Left\'),\n  right: () => callback(\'Right\'),\n  up: () => callback(\'Up\'),\n  down: () => callback(\'Down\')\n}\"\n```\n\n# 布局\n\n## [顶端栏 top nav bar](https://vuetifyjs.com/zh-Hans/components/app-bars/#v-app-bar-nav-icon)\n\n```html\n<v-app-bar\n    height=\"120\"\n    absolute\n    shrink-on-scroll\n    class=\"light-blue lighten-2\"\n    fade-img-on-scroll\n    src=\"https://picsum.photos/1920/1080?random\"\n>\n```\n\n上面的是基础的，分别是`absolute（一般是用app）`、`height高度`、`hide-on-scroll当滑动滚轮时消失`、` fade-img-on-scroll当滑动时隐藏背景图片`、`src添加背景图片`、`shrink-on-scroll当下滑时缩放回正常大小（配合背景图片挺好）`\n\n> **高度**\n>\n> shrink-on-scroll是在有高度的时候缩放，\n>\n> `extension-height=\"13px\"`属性可以直接增大高度，`:extend=\"true\"`也可以。\n>\n> 但是与 **extension-height** 属性，以及其他会影响工具栏高度的其他属性，例如 **prominent**, **dense** 等配合使用，**height** **除外**。\n>\n> ---\n>\n> 也可以使用**extension**插槽来填充内容。\n>\n> ```html\n> 	<template v-slot:extension>\n>      <v-tabs align-with-title>\n>        <v-tab>Tab 1</v-tab>\n>        <v-tab>Tab 2</v-tab>\n>        <v-tab>Tab 3</v-tab>\n>      </v-tabs>\n>    </template>\n> ```\n', 2, '', 0, 0, 1, '2022-05-19 10:27:48', NULL);
INSERT INTO `tb_article` VALUES (2088804354, 1, 188, 'http://localhost:8444/articles/1d1791d0fe66adc626eba50507b0b2ea.png', '2023-04-15', '可能就可能才能解决阿布ui 哇', 1, '', 1, 1, 1, '2023-04-15 14:47:17', '2023-04-15 14:47:45');

-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL COMMENT '文章id',
  `tag_id` int NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_article_tag_1`(`article_id` ASC) USING BTREE,
  INDEX `fk_article_tag_2`(`tag_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1937780739 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------
INSERT INTO `tb_article_tag` VALUES (-2139054078, 2088804353, 31);
INSERT INTO `tb_article_tag` VALUES (-2139054077, 2088804353, 230690817);
INSERT INTO `tb_article_tag` VALUES (-1601470463, 2088804354, 29);
INSERT INTO `tb_article_tag` VALUES (-1601470462, 2088804354, 30);
INSERT INTO `tb_article_tag` VALUES (-1601470461, 2088804354, 31);
INSERT INTO `tb_article_tag` VALUES (-1421828094, -1484742654, 31);
INSERT INTO `tb_article_tag` VALUES (-1421828093, -1484742654, 230690817);
INSERT INTO `tb_article_tag` VALUES (-1384079358, -1451188223, 31);
INSERT INTO `tb_article_tag` VALUES (-947871743, -1073700862, 31);
INSERT INTO `tb_article_tag` VALUES (-935313406, -507494399, 30);
INSERT INTO `tb_article_tag` VALUES (-868204542, -507494399, 31);
INSERT INTO `tb_article_tag` VALUES (-20930558, -150953982, 30);
INSERT INTO `tb_article_tag` VALUES (-20930557, -150953982, 31);
INSERT INTO `tb_article_tag` VALUES (857, 54, 29);
INSERT INTO `tb_article_tag` VALUES (859, 56, 30);
INSERT INTO `tb_article_tag` VALUES (860, 54, 30);
INSERT INTO `tb_article_tag` VALUES (861, 54, 31);
INSERT INTO `tb_article_tag` VALUES (862, 62, 30);
INSERT INTO `tb_article_tag` VALUES (863, 62, 31);
INSERT INTO `tb_article_tag` VALUES (864, 63, 30);
INSERT INTO `tb_article_tag` VALUES (865, 63, 31);
INSERT INTO `tb_article_tag` VALUES (866, 64, 30);
INSERT INTO `tb_article_tag` VALUES (867, 64, 31);
INSERT INTO `tb_article_tag` VALUES (12623874, -50290687, 31);
INSERT INTO `tb_article_tag` VALUES (79732737, -50290687, 230690817);
INSERT INTO `tb_article_tag` VALUES (780181505, 717266945, 31);
INSERT INTO `tb_article_tag` VALUES (780181506, 717266945, 230690817);
INSERT INTO `tb_article_tag` VALUES (918573057, 595611649, 31);
INSERT INTO `tb_article_tag` VALUES (1480654850, 163581954, 30);
INSERT INTO `tb_article_tag` VALUES (1480654851, 163581954, 31);
INSERT INTO `tb_article_tag` VALUES (1480654852, 163581954, 230690817);
INSERT INTO `tb_article_tag` VALUES (1937780737, 1904226305, 31);
INSERT INTO `tb_article_tag` VALUES (1937780738, 1904226305, 230690817);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2046836739 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (187, '测试分类', '2022-01-24 23:33:56', NULL);
INSERT INTO `tb_category` VALUES (188, 'Spigot教程', '2022-05-09 15:20:13', '2022-05-09 15:20:15');
INSERT INTO `tb_category` VALUES (163581953, 'test', '2022-05-17 14:42:11', NULL);

-- ----------------------------
-- Table structure for tb_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_chat_record`;
CREATE TABLE `tb_chat_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '聊天内容',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip来源',
  `type` tinyint NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2990 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_chat_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '评论用户Id',
  `topic_id` int NULL DEFAULT NULL COMMENT '评论主题id',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `reply_user_id` int NULL DEFAULT NULL COMMENT '回复用户id',
  `parent_id` int NULL DEFAULT NULL COMMENT '父评论id',
  `type` tinyint NOT NULL COMMENT '评论类型 1.文章 2.友链 3.说说',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
  `is_review` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_comment_user`(`user_id` ASC) USING BTREE,
  INDEX `fk_comment_parent`(`parent_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1653301251 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (-1953791999, -1920262142, -1198526463, 'i的奶奶不到半年挖的😂🤥', NULL, NULL, 1, 0, 1, '2023-04-15 14:38:54', NULL);
INSERT INTO `tb_comment` VALUES (-318107646, -1920262142, 2088804353, '写的什么东西，看着难受的要*哎😅', NULL, NULL, 1, 0, 1, '2023-04-15 09:10:27', NULL);
INSERT INTO `tb_comment` VALUES (-297041919, -1920262142, NULL, '回复 @Shinyoki：满分十分捏*你是粉丝😅😅😅', -1920262142, -1953791999, 1, 0, 1, '2023-04-15 14:39:04', NULL);
INSERT INTO `tb_comment` VALUES (729, 1, NULL, '测试评论', NULL, NULL, 2, 0, 1, '2022-03-11 22:52:53', '2022-05-24 19:27:05');
INSERT INTO `tb_comment` VALUES (730, 1, NULL, '测试回复', 1, 729, 2, 0, 1, '2022-03-11 22:52:58', '2022-05-24 19:19:14');
INSERT INTO `tb_comment` VALUES (731, 1, 54, '测试回复2', 1, 726, 1, 0, 1, '2022-05-24 14:52:34', '2022-05-24 19:02:19');
INSERT INTO `tb_comment` VALUES (732, 1, 2088804353, 'Com1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnawCom1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnawCom1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnawCom1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnawCom1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnaw', NULL, NULL, 1, 0, 1, '2022-05-24 15:04:07', '2022-05-24 19:19:14');
INSERT INTO `tb_comment` VALUES (733, 1, 2088804353, 'Com1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnaw', 1, 732, 1, 0, 1, '2022-05-24 15:04:40', NULL);
INSERT INTO `tb_comment` VALUES (734, 1, 2088804353, 'dCom1312j nawjknbdjklawniodhnawiondilwandlnawndoawndjkowandjkonwaodnawondowandoinawndwioandoawndkonwaodnwandnaw', 1, 732, 1, 0, 1, '2022-05-24 15:04:40', '2022-05-24 19:34:12');
INSERT INTO `tb_comment` VALUES (735, 1, 2088804353, 'Rep1', 1, 732, 1, 0, 1, '2022-05-24 15:04:40', '2022-05-24 19:34:12');
INSERT INTO `tb_comment` VALUES (736, 1, 2088804353, 'Rep1', 1, 732, 1, 0, 1, '2022-05-24 15:04:40', '2022-05-24 19:34:12');
INSERT INTO `tb_comment` VALUES (1653301250, 1, -1144000511, '😭😭😭', NULL, NULL, 1, 0, 1, '2023-04-11 19:30:01', NULL);

-- ----------------------------
-- Table structure for tb_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend_link`;
CREATE TABLE `tb_friend_link`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `link_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接名',
  `link_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接头像',
  `link_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接地址',
  `link_intro` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_friend_link_user`(`link_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_friend_link
-- ----------------------------
INSERT INTO `tb_friend_link` VALUES (26, '风丶宇大佬的个人博客', 'https://static.talkxj.com/photos/b553f564f81a80dc338695acb1b475d2.jpg', 'https://www.talkxj.com', '往事不随风', '2022-01-18 00:26:46', NULL);

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单icon',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `order_num` tinyint(1) NOT NULL COMMENT '排序',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `is_hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏  0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1149337604 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '首页', '/', '/home/Home', 'el-icon-shouyeshouye', '2021-01-26 17:06:51', '2021-01-26 17:06:53', 1, NULL, 0);
INSERT INTO `tb_menu` VALUES (2, '文章管理', '/article-submenu', 'Layout', 'el-icon-fuzhi', '2021-01-25 20:43:07', '2021-01-25 20:43:09', 2, NULL, 0);
INSERT INTO `tb_menu` VALUES (3, '消息管理', '/message-submenu', 'Layout', 'el-icon-xiaoxi', '2021-01-25 20:44:17', '2021-01-25 20:44:20', 3, NULL, 0);
INSERT INTO `tb_menu` VALUES (4, '系统管理', '/system-submenu', 'Layout', 'el-icon-shezhi', '2021-01-25 20:45:57', '2021-01-25 20:45:59', 5, NULL, 0);
INSERT INTO `tb_menu` VALUES (5, '个人中心', '/setting', '/setting/Setting', 'el-icon-user', '2021-01-26 17:22:38', '2021-01-26 17:22:41', 7, NULL, 0);
INSERT INTO `tb_menu` VALUES (6, '发布文章', '/articles', '/article/Article', 'el-icon-fabiaowenzhang', '2021-01-26 14:30:48', '2021-01-26 14:30:51', 1, 2, 0);
INSERT INTO `tb_menu` VALUES (7, '修改文章', '/articles/*', '/article/Article', 'el-icon-wenzhangliebiao', '2021-01-26 14:31:32', '2022-05-26 07:58:53', 2, 2, 1);
INSERT INTO `tb_menu` VALUES (8, '文章列表', '/article-list', '/article/ArticleList', 'el-icon-fenlei', '2021-01-26 14:32:13', '2021-01-26 14:32:16', 3, 2, 0);
INSERT INTO `tb_menu` VALUES (9, '分类管理', '/categories', '/category/Category', 'el-icon-fenlei', '2021-01-26 14:33:42', '2021-01-26 14:33:43', 4, 2, 0);
INSERT INTO `tb_menu` VALUES (10, '标签管理', '/tags', '/tag/Tag', 'el-icon-biaoqian', '2021-01-26 14:34:33', '2021-01-26 14:34:36', 5, 2, 0);
INSERT INTO `tb_menu` VALUES (11, '评论管理', '/comments', '/comment/Comment', 'el-icon-pinglun', '2021-01-26 14:35:31', '2021-01-26 14:35:34', 1, 3, 0);
INSERT INTO `tb_menu` VALUES (12, '留言管理', '/messages', '/message/Message', 'el-icon-liuyan', '2021-01-26 14:36:09', '2021-01-26 14:36:13', 2, 3, 0);
INSERT INTO `tb_menu` VALUES (13, '用户列表', '/users', '/user/User', 'el-icon-jiaoseliebiao', '2021-01-26 14:38:09', '2021-01-26 14:38:12', 1, 202, 0);
INSERT INTO `tb_menu` VALUES (14, '角色管理', '/roles', '/role/Role', 'el-icon-jiaoseliebiao', '2021-01-26 14:39:01', '2021-01-26 14:39:03', 2, 213, 0);
INSERT INTO `tb_menu` VALUES (15, '接口管理', '/resources', '/resource/Resource', 'el-icon-APIjiekouguanli', '2021-01-26 14:40:14', '2021-08-07 20:00:28', 2, 213, 0);
INSERT INTO `tb_menu` VALUES (16, '菜单管理', '/menus', '/menu/Menu', 'el-icon-caidan', '2021-01-26 14:40:54', '2021-08-07 10:18:49', 2, 213, 0);
INSERT INTO `tb_menu` VALUES (17, '友链管理', '/links', '/friendLink/FriendLink', 'el-icon-yidongyunkongzhitaiicon58', '2021-01-26 14:41:35', '2021-01-26 14:41:37', 3, 4, 0);
INSERT INTO `tb_menu` VALUES (18, '关于我', '/about', '/about/About', 'el-icon-guanyuwomen', '2021-01-26 14:42:05', '2021-01-26 14:42:10', 4, 4, 0);
INSERT INTO `tb_menu` VALUES (19, '日志管理', '/log-submenu', 'Layout', 'el-icon-guanyuwomen', '2021-01-31 21:33:56', '2021-01-31 21:33:59', 6, NULL, 0);
INSERT INTO `tb_menu` VALUES (20, '操作日志', '/operation/log', '/log/Operation', 'el-icon-guanyuwomen', '2021-01-31 15:53:21', '2021-01-31 15:53:25', 1, 19, 0);
INSERT INTO `tb_menu` VALUES (201, '在线用户', '/online/users', '/user/Online', 'el-icon-jiaoseliebiao', '2021-02-05 14:59:51', '2021-02-05 14:59:53', 7, 202, 0);
INSERT INTO `tb_menu` VALUES (202, '用户管理', '/users-submenu', 'Layout', 'el-icon-jiaoseliebiao', '2021-02-06 23:44:59', '2021-02-06 23:45:03', 4, NULL, 0);
INSERT INTO `tb_menu` VALUES (205, '相册管理', '/album-submenu', 'Layout', 'el-icon-image-fill', '2021-08-03 15:10:54', '2021-08-07 20:02:06', 5, NULL, 0);
INSERT INTO `tb_menu` VALUES (206, '相册列表', '/albums', '/album/Album', 'el-icon-zhaopian', '2021-08-03 20:29:19', '2021-08-04 11:45:47', 1, 205, 0);
INSERT INTO `tb_menu` VALUES (208, '照片管理', '/albums/:albumId', '/album/Photo', 'el-icon-zhaopian', '2021-08-03 21:37:47', '2021-08-05 10:24:08', 1, 205, 1);
INSERT INTO `tb_menu` VALUES (209, '页面管理', '/pages', '/page/Page', 'el-icon-yemianpeizhi', '2021-08-04 11:36:27', '2021-08-07 20:01:26', 2, 4, 0);
INSERT INTO `tb_menu` VALUES (210, '照片回收站', '/photos/delete', '/album/Delete', 'el-icon-huishouzhan', '2021-08-05 13:55:19', NULL, 3, 205, 1);
INSERT INTO `tb_menu` VALUES (213, '权限管理', '/permission-submenu', 'Layout', 'el-icon-caidan', '2021-08-07 19:56:55', '2021-08-07 19:59:40', 4, NULL, 0);
INSERT INTO `tb_menu` VALUES (214, '网站管理', '/website', '/website/Website', 'el-icon-xitong', '2021-08-07 20:06:41', NULL, 1, 4, 0);
INSERT INTO `tb_menu` VALUES (215, '说说管理', '/talk-submenu', 'Layout', 'el-icon-pinglun', '2022-01-23 20:17:59', '2022-01-23 20:38:06', 5, NULL, 0);
INSERT INTO `tb_menu` VALUES (216, '发布说说', '/talks', '/talk/Talk', 'el-icon-fabusekuai', '2022-01-23 20:18:43', '2022-01-23 20:38:19', 1, 215, 0);
INSERT INTO `tb_menu` VALUES (217, '说说列表', '/talk-list', '/talk/TalkList', 'el-icon-iconfontdongtaidianji', '2022-01-23 23:28:24', '2022-01-24 00:02:48', 2, 215, 0);
INSERT INTO `tb_menu` VALUES (218, '修改说说', '/talks/:talkId', '/talk/Talk', 'el-icon-shouyeshouye', '2022-01-24 00:10:44', NULL, 3, 215, 1);
INSERT INTO `tb_menu` VALUES (219, '测试', '/test1', '/test/TestProxy', 'el-icon-shezhi', '2022-05-05 15:21:27', '2022-05-05 15:21:32', 1, 2, 0);

-- ----------------------------
-- Table structure for tb_menu_copy1
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_copy1`;
CREATE TABLE `tb_menu_copy1`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单icon',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `order_num` tinyint(1) NOT NULL COMMENT '排序',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `is_hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏  0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 219 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_menu_copy1
-- ----------------------------
INSERT INTO `tb_menu_copy1` VALUES (1, '首页', '/', '/home/Home.vue', 'el-icon-myshouye', '2021-01-26 17:06:51', '2021-01-26 17:06:53', 1, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (2, '文章管理', '/article-submenu', 'Layout', 'el-icon-mywenzhang-copy', '2021-01-25 20:43:07', '2021-01-25 20:43:09', 2, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (3, '消息管理', '/message-submenu', 'Layout', 'el-icon-myxiaoxi', '2021-01-25 20:44:17', '2021-01-25 20:44:20', 3, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (4, '系统管理', '/system-submenu', 'Layout', 'el-icon-myshezhi', '2021-01-25 20:45:57', '2021-01-25 20:45:59', 5, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (5, '个人中心', '/setting', '/setting/Setting.vue', 'el-icon-myuser', '2021-01-26 17:22:38', '2021-01-26 17:22:41', 7, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (6, '发布文章', '/articles', '/article/Article.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:30:48', '2021-01-26 14:30:51', 1, 2, 0);
INSERT INTO `tb_menu_copy1` VALUES (7, '修改文章', '/articles/*', '/article/Article.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:31:32', '2021-01-26 14:31:34', 2, 2, 1);
INSERT INTO `tb_menu_copy1` VALUES (8, '文章列表', '/article-list', '/article/ArticleList.vue', 'el-icon-mywenzhangliebiao', '2021-01-26 14:32:13', '2021-01-26 14:32:16', 3, 2, 0);
INSERT INTO `tb_menu_copy1` VALUES (9, '分类管理', '/categories', '/category/Category.vue', 'el-icon-myfenlei', '2021-01-26 14:33:42', '2021-01-26 14:33:43', 4, 2, 0);
INSERT INTO `tb_menu_copy1` VALUES (10, '标签管理', '/tags', '/tag/Tag.vue', 'el-icon-myicontag', '2021-01-26 14:34:33', '2021-01-26 14:34:36', 5, 2, 0);
INSERT INTO `tb_menu_copy1` VALUES (11, '评论管理', '/comments', '/comment/Comment.vue', 'el-icon-mypinglunzu', '2021-01-26 14:35:31', '2021-01-26 14:35:34', 1, 3, 0);
INSERT INTO `tb_menu_copy1` VALUES (12, '留言管理', '/messages', '/message/Message.vue', 'el-icon-myliuyan', '2021-01-26 14:36:09', '2021-01-26 14:36:13', 2, 3, 0);
INSERT INTO `tb_menu_copy1` VALUES (13, '用户列表', '/users', '/user/User.vue', 'el-icon-myyonghuliebiao', '2021-01-26 14:38:09', '2021-01-26 14:38:12', 1, 202, 0);
INSERT INTO `tb_menu_copy1` VALUES (14, '角色管理', '/roles', '/role/Role.vue', 'el-icon-myjiaoseliebiao', '2021-01-26 14:39:01', '2021-01-26 14:39:03', 2, 213, 0);
INSERT INTO `tb_menu_copy1` VALUES (15, '接口管理', '/resources', '/resource/Resource.vue', 'el-icon-myjiekouguanli', '2021-01-26 14:40:14', '2021-08-07 20:00:28', 2, 213, 0);
INSERT INTO `tb_menu_copy1` VALUES (16, '菜单管理', '/menus', '/menu/Menu.vue', 'el-icon-mycaidan', '2021-01-26 14:40:54', '2021-08-07 10:18:49', 2, 213, 0);
INSERT INTO `tb_menu_copy1` VALUES (17, '友链管理', '/links', '/friendLink/FriendLink.vue', 'el-icon-mydashujukeshihuaico-', '2021-01-26 14:41:35', '2021-01-26 14:41:37', 3, 4, 0);
INSERT INTO `tb_menu_copy1` VALUES (18, '关于我', '/about', '/about/About.vue', 'el-icon-myguanyuwo', '2021-01-26 14:42:05', '2021-01-26 14:42:10', 4, 4, 0);
INSERT INTO `tb_menu_copy1` VALUES (19, '日志管理', '/log-submenu', 'Layout', 'el-icon-myguanyuwo', '2021-01-31 21:33:56', '2021-01-31 21:33:59', 6, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (20, '操作日志', '/operation/log', '/log/Operation.vue', 'el-icon-myguanyuwo', '2021-01-31 15:53:21', '2021-01-31 15:53:25', 1, 19, 0);
INSERT INTO `tb_menu_copy1` VALUES (201, '在线用户', '/online/users', '/user/Online.vue', 'el-icon-myyonghuliebiao', '2021-02-05 14:59:51', '2021-02-05 14:59:53', 7, 202, 0);
INSERT INTO `tb_menu_copy1` VALUES (202, '用户管理', '/users-submenu', 'Layout', 'el-icon-myyonghuliebiao', '2021-02-06 23:44:59', '2021-02-06 23:45:03', 4, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (205, '相册管理', '/album-submenu', 'Layout', 'el-icon-myimage-fill', '2021-08-03 15:10:54', '2021-08-07 20:02:06', 5, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (206, '相册列表', '/albums', '/album/Album.vue', 'el-icon-myzhaopian', '2021-08-03 20:29:19', '2021-08-04 11:45:47', 1, 205, 0);
INSERT INTO `tb_menu_copy1` VALUES (208, '照片管理', '/albums/:albumId', '/album/Photo.vue', 'el-icon-myzhaopian', '2021-08-03 21:37:47', '2021-08-05 10:24:08', 1, 205, 1);
INSERT INTO `tb_menu_copy1` VALUES (209, '页面管理', '/pages', '/page/Page.vue', 'el-icon-myyemianpeizhi', '2021-08-04 11:36:27', '2021-08-07 20:01:26', 2, 4, 0);
INSERT INTO `tb_menu_copy1` VALUES (210, '照片回收站', '/photos/delete', '/album/Delete.vue', 'el-icon-myhuishouzhan', '2021-08-05 13:55:19', NULL, 3, 205, 1);
INSERT INTO `tb_menu_copy1` VALUES (213, '权限管理', '/permission-submenu', 'Layout', 'el-icon-mydaohanglantubiao_quanxianguanli', '2021-08-07 19:56:55', '2021-08-07 19:59:40', 4, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (214, '网站管理', '/website', '/website/Website.vue', 'el-icon-myxitong', '2021-08-07 20:06:41', NULL, 1, 4, 0);
INSERT INTO `tb_menu_copy1` VALUES (215, '说说管理', '/talk-submenu', 'Layout', 'el-icon-mypinglun', '2022-01-23 20:17:59', '2022-01-23 20:38:06', 5, NULL, 0);
INSERT INTO `tb_menu_copy1` VALUES (216, '发布说说', '/talks', '/talk/Talk.vue', 'el-icon-myfabusekuai', '2022-01-23 20:18:43', '2022-01-23 20:38:19', 1, 215, 0);
INSERT INTO `tb_menu_copy1` VALUES (217, '说说列表', '/talk-list', '/talk/TalkList.vue', 'el-icon-myiconfontdongtaidianji', '2022-01-23 23:28:24', '2022-01-24 00:02:48', 2, 215, 0);
INSERT INTO `tb_menu_copy1` VALUES (218, '修改说说', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myshouye', '2022-01-24 00:10:44', NULL, 3, 215, 1);

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `message_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ip',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户地址',
  `time` tinyint(1) NULL DEFAULT NULL COMMENT '弹幕速度',
  `is_review` tinyint NOT NULL DEFAULT 1 COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3942 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES (3938, '管理员', 'https://static.talkxj.com/avatar/user.png', '测试留言', '127.0.0.1', '', 9, 1, '2022-01-24 23:34:41', NULL);
INSERT INTO `tb_message` VALUES (3939, '管理员', 'https://static.talkxj.com/avatar/user.png', '测试留言2', '127.0.0.1', '', 9, 1, '2022-01-24 23:34:41', '2022-05-25 08:24:25');

-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `opt_module` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作模块',
  `opt_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作类型',
  `opt_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作url',
  `opt_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作方法',
  `opt_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作描述',
  `request_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求参数',
  `request_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求方式',
  `response_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '返回数据',
  `user_id` int NOT NULL COMMENT '用户id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作ip',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2118221828 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------
INSERT INTO `tb_operation_log` VALUES (-2009051134, '说说Controller', '新增或修改', '/admin/talks', 'com.senko.controller.common.TalkControllersaveOrUpdateTalk', '保存或修改说说', '[{\"content\":\"这是一条动态😄，希望没啥问题👍\",\"images\":\"[\\\"http://localhost:83/talks/8854ae40d3adc8ee0187896bc7e72254.png\\\",\\\"http://localhost:83/talks/67617cd9d4072bf70cc45ceabe4e1ba9.png\\\"]\",\"isTop\":1,\"status\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-30 21:55:31', NULL);
INSERT INTO `tb_operation_log` VALUES (-2009038846, '资源API', '新增或修改', '/admin/resources', 'com.senko.controller.system.SysResourceControllersaveOrUpdateResource', '新增或修改资源', '[{\"resourceName\":\"23\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 13:48:20', NULL);
INSERT INTO `tb_operation_log` VALUES (-1983868927, '角色模块', '新增或修改', '/admin/role', 'com.senko.controller.system.SysRoleControllersaveOrUpdateRole', '新增或修改角色', '[{\"menuIdList\":[2,6,219,7,8],\"roleLabel\":\"dd\",\"roleName\":\"dd\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 11:00:06', NULL);
INSERT INTO `tb_operation_log` VALUES (-1929273342, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[728],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:38:42', NULL);
INSERT INTO `tb_operation_log` VALUES (-1916698622, '文章模块', '删除', '/admin/articles', 'com.senko.controller.common.ArticleControllerdeleteArticles', '完全删除文章', '[[692101122]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:31:12', NULL);
INSERT INTO `tb_operation_log` VALUES (-1887412222, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-679350270]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:55:58', NULL);
INSERT INTO `tb_operation_log` VALUES (-1841201150, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"gfdgdf\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:13:37', NULL);
INSERT INTO `tb_operation_log` VALUES (-1765703678, '菜单模块', '新增或修改', '/admin/menus', 'com.senko.controller.system.SysMenuControllersaveOrUpdateMenu', '更新或新增菜单', '[{\"component\":\"/T2\",\"icon\":\"el-icon-shouyeshouye\",\"isHidden\":0,\"name\":\"T2\",\"orderNum\":1,\"parentId\":1149337601,\"path\":\"/t22\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 20:20:35', NULL);
INSERT INTO `tb_operation_log` VALUES (-1761562623, '标签模块', '新增或修改', '/admin/tags', 'com.senko.controller.common.TagControllersaveOrUpdateTag', '添加或修改标签', '[{\"tagName\":\"2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 11:14:51', NULL);
INSERT INTO `tb_operation_log` VALUES (-1753194494, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[1128292353]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 13:05:47', NULL);
INSERT INTO `tb_operation_log` VALUES (-1707081726, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[736],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:29:13', NULL);
INSERT INTO `tb_operation_log` VALUES (-1694400511, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:36:08', NULL);
INSERT INTO `tb_operation_log` VALUES (-1665093631, '标签模块', '删除', '/admin/tags', 'com.senko.controller.common.TagControllerdeleteTag', '删除标签', '[[-230641663,-2101301247]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 11:14:55', NULL);
INSERT INTO `tb_operation_log` VALUES (-1648267263, '留言模块', '修改', '/admin/messages/review', 'com.senko.controller.common.MessageControllerupdateMessagesReview', '更新留言审核状态', '[{\"idList\":[3941,3940],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 08:27:56', NULL);
INSERT INTO `tb_operation_log` VALUES (-1539289086, '用户信息模块', '修改', '/admin/users/role', 'com.senko.controller.common.UserInfoControllerupdateUserRole', '更新用户的角色', '[{\"nickname\":\"测试用户1\",\"roleIdList\":[1,2],\"userInfoId\":1006}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-28 19:39:57', NULL);
INSERT INTO `tb_operation_log` VALUES (-1522425855, '标签模块', '新增或修改', '/admin/tags', 'com.senko.controller.common.TagControllersaveOrUpdateTag', '添加或修改标签', '[{\"tagName\":\"da\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:40:11', NULL);
INSERT INTO `tb_operation_log` VALUES (-1501466623, '留言模块', '修改', '/admin/messages/review', 'com.senko.controller.common.MessageControllerupdateMessagesReview', '更新留言审核状态', '[{\"idList\":[3939],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 08:24:25', NULL);
INSERT INTO `tb_operation_log` VALUES (-1476288511, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[731,730],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:02:19', NULL);
INSERT INTO `tb_operation_log` VALUES (-1459519487, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:27:52', NULL);
INSERT INTO `tb_operation_log` VALUES (-1455325183, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-197033983]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:28:42', NULL);
INSERT INTO `tb_operation_log` VALUES (-1442729983, '菜单模块', '新增或修改', '/admin/menus', 'com.senko.controller.system.SysMenuControllersaveOrUpdateMenu', '更新或新增菜单', '[{\"component\":\"/article/Article\",\"icon\":\"el-icon-wenzhangliebiao\",\"id\":7,\"isHidden\":0,\"name\":\"修改文章\",\"orderNum\":2,\"path\":\"/articles/*\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 07:58:51', NULL);
INSERT INTO `tb_operation_log` VALUES (-1421123582, '', '新增', '/comments', 'com.senko.controller.common.CommentController.saveComment', '添加评论', '[{\"commentContent\":\"😭😭😭\",\"topicId\":-1144000511,\"type\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-11 19:30:01', NULL);
INSERT INTO `tb_operation_log` VALUES (-1409187839, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:28', NULL);
INSERT INTO `tb_operation_log` VALUES (-1346355198, '资源API', '新增或修改', '/admin/resources', 'com.senko.controller.system.SysResourceControllersaveOrUpdateResource', '新增或修改资源', '[{\"id\":964706306,\"isAnonymous\":0,\"requestMethod\":\"PUT\",\"resourceName\":\"t2\",\"url\":\"/t233\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 09:04:43', NULL);
INSERT INTO `tb_operation_log` VALUES (-1307869182, '', '新增', '/comments', 'com.senko.controller.common.CommentController.saveComment', '添加评论', '[{\"commentContent\":\"i的奶奶不到半年挖的😂🤥\",\"topicId\":-1198526463,\"type\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', -1920262142, 'Shinyoki', '127.0.0.1', '未知', '2023-04-15 14:38:55', NULL);
INSERT INTO `tb_operation_log` VALUES (-1262440446, '标签模块', '删除', '/admin/tags', 'com.senko.controller.common.TagControllerdeleteTag', '删除标签', '[[-494882814]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 11:14:46', NULL);
INSERT INTO `tb_operation_log` VALUES (-1186951167, '角色模块', '新增或修改', '/admin/role', 'com.senko.controller.system.SysRoleControllersaveOrUpdateRole', '新增或修改角色', '[{\"menuIdList\":[1,2,6],\"roleLabel\":\"user2\",\"roleName\":\"测试角色2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 10:55:10', NULL);
INSERT INTO `tb_operation_log` VALUES (-1174384639, '用户信息模块', '修改', '/admin/users/disable', 'com.senko.controller.common.UserInfoControllerupdateUserIsDisable', '更新用户的禁用状态', '[{\"id\":1006,\"isDisable\":0}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-28 21:25:12', NULL);
INSERT INTO `tb_operation_log` VALUES (-1115664382, '用户信息模块', '修改', '/admin/users/disable', 'com.senko.controller.common.UserInfoControllerupdateUserIsDisable', '更新用户的禁用状态', '[{\"id\":1006,\"isDisable\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-28 21:25:11', NULL);
INSERT INTO `tb_operation_log` VALUES (-1061134334, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[830599169]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:55:50', NULL);
INSERT INTO `tb_operation_log` VALUES (-1006567423, '说说Controller', '删除', '/admin/talks', 'com.senko.controller.common.TalkControllerdeleteTalks', '删除说说 集合', '[[51]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-29 11:04:20', NULL);
INSERT INTO `tb_operation_log` VALUES (-935231487, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-549355519,-1002340350]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:38', NULL);
INSERT INTO `tb_operation_log` VALUES (-918454270, '菜单模块', '删除', '/admin/menus/-2092859391', 'com.senko.controller.system.SysMenuControllerdeleteMenu', '删除菜单', '[-2092859391]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 20:33:38', NULL);
INSERT INTO `tb_operation_log` VALUES (-842956798, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa2\",\"id\":-67010558}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:44:04', NULL);
INSERT INTO `tb_operation_log` VALUES (-805208063, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:34', NULL);
INSERT INTO `tb_operation_log` VALUES (-742293503, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-67010558]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:06', NULL);
INSERT INTO `tb_operation_log` VALUES (-704598015, '标签模块', '新增或修改', '/admin/tags', 'com.senko.controller.common.TagControllersaveOrUpdateTag', '添加或修改标签', '[{\"id\":31,\"tagName\":\"minecraft1\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 11:13:12', NULL);
INSERT INTO `tb_operation_log` VALUES (-696143871, '菜单模块', '新增或修改', '/admin/menus', 'com.senko.controller.system.SysMenuControllersaveOrUpdateMenu', '更新或新增菜单', '[{\"component\":\"/article/Article\",\"icon\":\"el-icon-wenzhangliebiao\",\"id\":7,\"isHidden\":1,\"name\":\"修改文章\",\"orderNum\":2,\"path\":\"/articles/*\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 07:58:53', NULL);
INSERT INTO `tb_operation_log` VALUES (-682627071, '', '新增或修改', '/admin/talks', 'com.senko.controller.common.TalkController.saveOrUpdateTalk', '保存或修改说说', '[{\"content\":\"关注永雏塔菲喵🥰🥰🥰\",\"images\":\"[\\\"http://localhost:8444/talks/e733f86f4523895e46fade42bdca7baf.png\\\"]\",\"isTop\":1,\"status\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-10 15:51:09', NULL);
INSERT INTO `tb_operation_log` VALUES (-641634302, '留言模块', '删除', '/admin/messages', 'com.senko.controller.common.MessageControllerdeleteMessages', '删除留言', '[[3941]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 08:28:01', NULL);
INSERT INTO `tb_operation_log` VALUES (-545153022, '标签模块', '删除', '/admin/tags', 'com.senko.controller.common.TagControllerdeleteTag', '删除标签', '[[-2042519551,717332482]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:40:18', NULL);
INSERT INTO `tb_operation_log` VALUES (-540966911, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:26:53', NULL);
INSERT INTO `tb_operation_log` VALUES (-486440958, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-2088665087]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:36:11', NULL);
INSERT INTO `tb_operation_log` VALUES (-456425471, '', '新增或修改', '/admin/articles', 'com.senko.controller.common.ArticleController.saveOrUpdateArticle', '添加/修改文章', '[{\"articleContent\":\"可能就可能才能解决阿布ui 哇\",\"articleCover\":\"http://localhost:8444/articles/1d1791d0fe66adc626eba50507b0b2ea.png\",\"articleTitle\":\"2023-04-15\",\"categoryName\":\"Spigot教程\",\"isTop\":1,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-15 14:47:18', NULL);
INSERT INTO `tb_operation_log` VALUES (-394158079, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[728],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:38:18', NULL);
INSERT INTO `tb_operation_log` VALUES (-359665663, '', '新增或修改', '/admin/talks', 'com.senko.controller.common.TalkController.saveOrUpdateTalk', '保存或修改说说', '[{\"content\":\"今天明天后天都不是什么好日子🤗\",\"images\":\"[\\\"http://localhost:8444/talks/e733f86f4523895e46fade42bdca7baf.png\\\"]\",\"isTop\":0,\"status\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-10 15:50:40', NULL);
INSERT INTO `tb_operation_log` VALUES (-352227326, '留言模块', '删除', '/admin/messages', 'com.senko.controller.common.MessageControllerdeleteMessages', '删除留言', '[[3940]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 08:28:05', NULL);
INSERT INTO `tb_operation_log` VALUES (-339701758, '角色模块', '删除', '/admin/roles', 'com.senko.controller.system.SysRoleControllerdeleteRoles', '删除角色', '[[1921028098]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 11:00:15', NULL);
INSERT INTO `tb_operation_log` VALUES (-301891582, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:29:33', NULL);
INSERT INTO `tb_operation_log` VALUES (-293502974, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"1\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:26', NULL);
INSERT INTO `tb_operation_log` VALUES (-285085695, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"test2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:43:04', NULL);
INSERT INTO `tb_operation_log` VALUES (-259129343, '', '新增', '/admin/photos', 'com.senko.controller.common.PhotoController.uploadPhotos', '保存照片', '[{\"albumId\":55443458,\"photoUrlList\":[\"http://localhost:8444/photos/f5e4dd1cab623f8ba1e1b2a2b18d28d6.png\",\"http://localhost:8444/photos/03c3b09692988a3f20932492649125ce.png\",\"http://localhost:8444/photos/e733f86f4523895e46fade42bdca7baf.png\",\"http://localhost:8444/photos/fea88129629a0c54cbbb7ca841c51afa.png\",\"http://localhost:8444/photos/1295afa7781ad3a2a42c63d606004fe0.png\",\"http://localhost:8444/photos/fc629ba1d968b0ac912f6c411aaa5ce8.png\"]}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-13 13:48:23', NULL);
INSERT INTO `tb_operation_log` VALUES (-247443454, '用户信息模块', '修改', '/admin/users/role', 'com.senko.controller.common.UserInfoControllerupdateUserRole', '更新用户的角色', '[{\"nickname\":\"测试用户1\",\"roleIdList\":[2],\"userInfoId\":1006}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-28 19:40:08', NULL);
INSERT INTO `tb_operation_log` VALUES (-180318207, '角色模块', '新增或修改', '/admin/role', 'com.senko.controller.system.SysRoleControllersaveOrUpdateRole', '新增或修改角色', '[{\"id\":1291882497,\"menuIdList\":[1,2,6,219],\"roleLabel\":\"user2\",\"roleName\":\"测试角色2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 10:56:16', NULL);
INSERT INTO `tb_operation_log` VALUES (-171921407, '标签模块', '新增或修改', '/admin/tags', 'com.senko.controller.common.TagControllersaveOrUpdateTag', '添加或修改标签', '[{\"tagName\":\"minecraft2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 11:14:42', NULL);
INSERT INTO `tb_operation_log` VALUES (-163553279, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"das\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:56:35', NULL);
INSERT INTO `tb_operation_log` VALUES (-100638719, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"fsdfs\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:55:54', NULL);
INSERT INTO `tb_operation_log` VALUES (1083, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"恭喜你成功运行博客，开启你的文章之旅吧。\",\"articleCover\":\"https://static.talkxj.com/articles/3dffb2fcbd541886616ab54c92570de3.jpg\",\"articleTitle\":\"测试文章\",\"categoryName\":\"测试分类\",\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[\"测试标签\"],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2022-01-24 23:33:57', NULL);
INSERT INTO `tb_operation_log` VALUES (1084, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"# 标题\\n内容\\n2333\\n> dasdas\\n\\n```java\\npublic class A{\\n\\n}\\n```\",\"articleCover\":\"\",\"articleTitle\":\"2022-05-14\",\"isTop\":0,\"originalUrl\":\"\",\"status\":3,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.8', '本地局域网', '2022-05-14 23:00:13', NULL);
INSERT INTO `tb_operation_log` VALUES (1085, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"# 32323\\nDADAWDADAWDA\",\"articleCover\":\"\",\"articleTitle\":\"2022-05-14\",\"isTop\":0,\"originalUrl\":\"\",\"status\":3,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2022-05-14 23:02:23', NULL);
INSERT INTO `tb_operation_log` VALUES (1086, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"# 32323\\nDADAWDADAWDA\",\"articleCover\":\"\",\"articleTitle\":\"2022-05-14\",\"isTop\":0,\"originalUrl\":\"\",\"status\":3,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2022-05-14 23:04:06', NULL);
INSERT INTO `tb_operation_log` VALUES (1087, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"# 32323\\nDADAWDADAWDA\",\"articleCover\":\"\",\"articleTitle\":\"2022-05-14\",\"isTop\":0,\"originalUrl\":\"\",\"status\":3,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2022-05-14 23:06:50', NULL);
INSERT INTO `tb_operation_log` VALUES (1088, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"sdsadas\",\"articleCover\":\"http://47.96.151.26:83/articles/a82d085f7cc7fd92d384f8af06763a82.png\",\"articleTitle\":\"2022-05-17\",\"categoryName\":\"Spigot教程\",\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.7', '本地局域网', '2022-05-17 13:46:55', NULL);
INSERT INTO `tb_operation_log` VALUES (1089, '文章模块', '修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.updateArticleDelete', '恢复或删除文章', '[{\"idList\":[62],\"isDelete\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.7', '本地局域网', '2022-05-17 13:49:50', NULL);
INSERT INTO `tb_operation_log` VALUES (1090, '文章模块', '修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.updateArticleDelete', '恢复或删除文章', '[{\"idList\":[61],\"isDelete\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.7', '本地局域网', '2022-05-17 13:49:55', NULL);
INSERT INTO `tb_operation_log` VALUES (1091, '文章模块', '修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.updateArticleDelete', '恢复或删除文章', '[{\"idList\":[60],\"isDelete\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.7', '本地局域网', '2022-05-17 13:49:56', NULL);
INSERT INTO `tb_operation_log` VALUES (1092, '文章模块', '修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.updateArticleDelete', '恢复或删除文章', '[{\"idList\":[59],\"isDelete\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.7', '本地局域网', '2022-05-17 13:49:58', NULL);
INSERT INTO `tb_operation_log` VALUES (1093, '文章模块', '修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.updateArticleDelete', '恢复或删除文章', '[{\"idList\":[58],\"isDelete\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.7', '本地局域网', '2022-05-17 13:50:01', NULL);
INSERT INTO `tb_operation_log` VALUES (1094, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"szczc\",\"articleCover\":\"http://47.96.151.26:83/articles/237bdacdd6ee1bf1dfefa6d80cb6bf04.jpg\",\"articleTitle\":\"2022-05-17\",\"categoryName\":\"t1\",\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2022-05-17 14:17:54', NULL);
INSERT INTO `tb_operation_log` VALUES (1095, '文章模块', '新增或修改', '/admin/articles', 'com.minzheng.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"szczc\",\"articleCover\":\"http://47.96.151.26:83/articles/237bdacdd6ee1bf1dfefa6d80cb6bf04.jpg\",\"articleTitle\":\"2022-05-17\",\"categoryName\":\"t1\",\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2022-05-17 14:20:17', NULL);
INSERT INTO `tb_operation_log` VALUES (29405186, '标签模块', '新增或修改', '/admin/tags', 'com.senko.controller.common.TagControllersaveOrUpdateTag', '添加或修改标签', '[{\"tagName\":\"1\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 11:14:48', NULL);
INSERT INTO `tb_operation_log` VALUES (34308097, '', '新增或修改', '/admin/talks', 'com.senko.controller.common.TalkController.saveOrUpdateTalk', '保存或修改说说', '[{\"content\":\"个你能😘🤩🤭\",\"images\":\"[\\\"http://localhost:8444/talks/a3f704e212e4f9ce9641dd99e17bdf65.png\\\"]\",\"isTop\":1,\"status\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-15 14:48:49', NULL);
INSERT INTO `tb_operation_log` VALUES (50331650, '评论Controller', '删除', '/admin/comments', 'com.senko.controller.common.CommentControllerdeleteComments', '删除评论', '[[726,725]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:26:45', NULL);
INSERT INTO `tb_operation_log` VALUES (71303170, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[734],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:32:36', NULL);
INSERT INTO `tb_operation_log` VALUES (71401473, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:28:38', NULL);
INSERT INTO `tb_operation_log` VALUES (79691777, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[735],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:31:06', NULL);
INSERT INTO `tb_operation_log` VALUES (88080385, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[736],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:31:23', NULL);
INSERT INTO `tb_operation_log` VALUES (109051905, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[734,736,735],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:34:12', NULL);
INSERT INTO `tb_operation_log` VALUES (160137217, '', '新增', '/comments', 'com.senko.controller.common.CommentController.saveComment', '添加评论', '[{\"commentContent\":\"回复 @Shinyoki：满分十分捏*你是粉丝😅😅😅\",\"parentId\":-1953791999,\"replyUserId\":-1920262142,\"type\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', -1920262142, 'Shinyoki', '127.0.0.1', '未知', '2023-04-15 14:39:04', NULL);
INSERT INTO `tb_operation_log` VALUES (176820226, '', '新增', '/comments', 'com.senko.controller.common.CommentController.saveComment', '添加评论', '[{\"commentContent\":\"写的什么东西，看着难受的要*哎😅\",\"topicId\":2088804353,\"type\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', -1920262142, 'Shinyoki', '127.0.0.1', '未知', '2023-04-15 09:10:27', NULL);
INSERT INTO `tb_operation_log` VALUES (193036290, '菜单模块', '删除', '/admin/menus/1149337601', 'com.senko.controller.system.SysMenuControllerdeleteMenu', '删除菜单', '[1149337601]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 20:33:40', NULL);
INSERT INTO `tb_operation_log` VALUES (205533186, '文章模块', '修改', '/admin/articles', 'com.senko.controller.common.ArticleControllerupdateArticleDelete', '恢复/删除文章', '[{\"idList\":[-150953982],\"isDelete\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-19 16:18:28', NULL);
INSERT INTO `tb_operation_log` VALUES (264339457, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:43:00', NULL);
INSERT INTO `tb_operation_log` VALUES (306208770, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-356491262]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:56:17', NULL);
INSERT INTO `tb_operation_log` VALUES (318803969, '角色模块', '新增或修改', '/admin/role', 'com.senko.controller.system.SysRoleControllersaveOrUpdateRole', '新增或修改角色', '[{\"id\":1291882497,\"menuIdList\":[1,2,6,219],\"roleLabel\":\"user2\",\"roleName\":\"测试角色2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 10:55:36', NULL);
INSERT INTO `tb_operation_log` VALUES (322998274, '角色模块', '删除', '/admin/roles', 'com.senko.controller.system.SysRoleControllerdeleteRoles', '删除角色', '[[1291882497]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 10:59:04', NULL);
INSERT INTO `tb_operation_log` VALUES (390070273, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[735],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:29:14', NULL);
INSERT INTO `tb_operation_log` VALUES (390168577, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[1673625601]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:19', NULL);
INSERT INTO `tb_operation_log` VALUES (448888834, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:35:45', NULL);
INSERT INTO `tb_operation_log` VALUES (490770433, '角色模块', '新增或修改', '/admin/role', 'com.senko.controller.system.SysRoleControllersaveOrUpdateRole', '新增或修改角色', '[{\"id\":1291882497,\"resourceIdList\":[166,181,182,183,184,246,247,274,275],\"roleLabel\":\"user2\",\"roleName\":\"测试角色2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 10:57:15', NULL);
INSERT INTO `tb_operation_log` VALUES (520192002, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-1669234686]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:31', NULL);
INSERT INTO `tb_operation_log` VALUES (562073602, '角色模块', '新增或修改', '/admin/role', 'com.senko.controller.system.SysRoleControllersaveOrUpdateRole', '新增或修改角色', '[{\"id\":1291882497,\"menuIdList\":[1,2,6,219],\"roleLabel\":\"user2\",\"roleName\":\"测试角色2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-27 10:56:23', NULL);
INSERT INTO `tb_operation_log` VALUES (641835010, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[730],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:01:26', NULL);
INSERT INTO `tb_operation_log` VALUES (755073026, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-452960255]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:27:02', NULL);
INSERT INTO `tb_operation_log` VALUES (788627457, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-805208062]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:27:56', NULL);
INSERT INTO `tb_operation_log` VALUES (817901569, '文章模块', '修改', '/admin/articles', 'com.senko.controller.common.ArticleControllerupdateArticleDelete', '恢复/删除文章', '[{\"idList\":[692101122],\"isDelete\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-19 16:17:43', NULL);
INSERT INTO `tb_operation_log` VALUES (897613826, '资源API', '删除', '/admin/resources/1891663873', 'com.senko.controller.system.SysResourceControllerdeleteResource', '删除资源', '[1891663873]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 13:48:24', NULL);
INSERT INTO `tb_operation_log` VALUES (910237698, '资源API', '删除', '/admin/resources/469778434', 'com.senko.controller.system.SysResourceControllerdeleteResource', '删除资源', '[469778434]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 09:08:44', NULL);
INSERT INTO `tb_operation_log` VALUES (985661441, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[735],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:33:47', NULL);
INSERT INTO `tb_operation_log` VALUES (1073758209, '资源API', '新增或修改', '/admin/resources', 'com.senko.controller.system.SysResourceControllersaveOrUpdateResource', '新增或修改资源', '[{\"resourceName\":\"T\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 08:58:10', NULL);
INSERT INTO `tb_operation_log` VALUES (1086431233, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[726],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:38:40', NULL);
INSERT INTO `tb_operation_log` VALUES (1107402754, '标签模块', '新增或修改', '/admin/tags', 'com.senko.controller.common.TagControllersaveOrUpdateTag', '添加或修改标签', '[{\"tagName\":\"da2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:40:14', NULL);
INSERT INTO `tb_operation_log` VALUES (1291862017, '资源API', '新增或修改', '/admin/resources', 'com.senko.controller.system.SysResourceControllersaveOrUpdateResource', '新增或修改资源', '[{\"parentId\":469778434,\"requestMethod\":\"POST\",\"resourceName\":\"t2\",\"url\":\"/t233\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 09:04:35', NULL);
INSERT INTO `tb_operation_log` VALUES (1350692866, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"test1\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:42:57', NULL);
INSERT INTO `tb_operation_log` VALUES (1367449602, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[732,729],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 18:59:51', NULL);
INSERT INTO `tb_operation_log` VALUES (1379934209, '菜单模块', '新增或修改', '/admin/menus', 'com.senko.controller.system.SysMenuControllersaveOrUpdateMenu', '更新或新增菜单', '[{\"component\":\"Layout\",\"icon\":\"el-icon-shouyeshouye\",\"isHidden\":0,\"name\":\"T111\",\"orderNum\":1,\"path\":\"/T\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 21:48:34', NULL);
INSERT INTO `tb_operation_log` VALUES (1455448066, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"aa\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-23 12:56:12', NULL);
INSERT INTO `tb_operation_log` VALUES (1476395010, '评论Controller', '删除', '/admin/comments', 'com.senko.controller.common.CommentControllerdeleteComments', '删除评论', '[[728,727]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:26:54', NULL);
INSERT INTO `tb_operation_log` VALUES (1535213569, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"sd2\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:15', NULL);
INSERT INTO `tb_operation_log` VALUES (1535213570, '菜单模块', '新增或修改', '/admin/menus', 'com.senko.controller.system.SysMenuControllersaveOrUpdateMenu', '更新或新增菜单', '[{\"component\":\"Layout\",\"icon\":\"el-icon-shouyeshouye\",\"isHidden\":0,\"name\":\"t\",\"orderNum\":1,\"path\":\"/T\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-25 20:20:10', NULL);
INSERT INTO `tb_operation_log` VALUES (1631584257, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[729],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:27:06', NULL);
INSERT INTO `tb_operation_log` VALUES (1715470337, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[729],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:26:58', NULL);
INSERT INTO `tb_operation_log` VALUES (1716387841, '', '新增或修改', '/admin/photos/albums', 'com.senko.controller.common.PhotoAlbumController.saveOrUpdatePhotoAlbum', '保存或更新相册', '[{\"albumCover\":\"http://localhost:8444/photos/e733f86f4523895e46fade42bdca7baf.png\",\"albumDesc\":\"记录悲惨生活喵\",\"albumName\":\"唯美图片\",\"status\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"保存或更新成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-13 13:48:04', NULL);
INSERT INTO `tb_operation_log` VALUES (1720418305, '', '修改', '/admin/articles', 'com.senko.controller.common.ArticleController.updateArticleDelete', '恢复/删除文章', '[{\"idList\":[2088804354],\"isDelete\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2023-04-15 14:47:45', NULL);
INSERT INTO `tb_operation_log` VALUES (1740636162, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[736],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:33:46', NULL);
INSERT INTO `tb_operation_log` VALUES (1757511681, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[1921089538]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:26:39', NULL);
INSERT INTO `tb_operation_log` VALUES (1811955713, '资源API', '新增或修改', '/admin/resources', 'com.senko.controller.system.SysResourceControllersaveOrUpdateResource', '新增或修改资源', '[{\"id\":964706306,\"isAnonymous\":0,\"requestMethod\":\"GET\",\"resourceName\":\"t2\",\"url\":\"/t233\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-26 09:04:46', NULL);
INSERT INTO `tb_operation_log` VALUES (1883340802, '文章模块', '修改', '/admin/articles', 'com.senko.controller.common.ArticleControllerupdateArticleDelete', '恢复/删除文章', '[{\"idList\":[-507494399],\"isDelete\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:30:42', NULL);
INSERT INTO `tb_operation_log` VALUES (1904312322, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[1203863554]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:22', NULL);
INSERT INTO `tb_operation_log` VALUES (1921003521, '文章模块', '新增或修改', '/admin/articles', 'com.senko.controller.common.ArticleControllersaveOrUpdateArticle', '添加/修改文章', '[{\"articleContent\":\"cascascas\",\"articleCover\":\"http://localhost:83/articles/237bdacdd6ee1bf1dfefa6d80cb6bf04.jpg\",\"articleTitle\":\"2022-05-19\",\"categoryName\":\"Spigot教程\",\"isTop\":1,\"originalUrl\":\"\",\"status\":2,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-19 15:51:10', NULL);
INSERT INTO `tb_operation_log` VALUES (1937866754, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-691961854]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:29:39', NULL);
INSERT INTO `tb_operation_log` VALUES (1996587010, '分类模块', '新增或修改', '/admin/categories', 'com.senko.controller.common.CategoryControllersaveOrUpdateCategory', '添加或修改分类', '[{\"categoryName\":\"dsa\"}]', 'POST', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:49:11', NULL);
INSERT INTO `tb_operation_log` VALUES (2114035714, '评论Controller', '修改', '/admin/comments/review', 'com.senko.controller.common.CommentControllerupdateCommentsIsReview', '更新审核状态', '[{\"idList\":[729],\"isReview\":1}]', 'PUT', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 19:14:17', NULL);
INSERT INTO `tb_operation_log` VALUES (2118221826, '分类模块', '删除', '/admin/categories', 'com.senko.controller.common.CategoryControllerdeleteCategories', '删除分类', '[[-1795063807,184647681]]', 'DELETE', '{\"flag\":true,\"code\":200,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '未知', '2022-05-24 07:35:49', NULL);
INSERT INTO `tb_operation_log` VALUES (2118221827, '评论模块', '修改', '/admin/comments/review', 'com.minzheng.blog.controller.CommentController.updateCommentsReview', '审核评论', '[{\"idList\":[732,730],\"isReview\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '192.168.1.6', '本地局域网', '2022-05-24 19:19:14', NULL);

-- ----------------------------
-- Table structure for tb_page
-- ----------------------------
DROP TABLE IF EXISTS `tb_page`;
CREATE TABLE `tb_page`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '页面id',
  `page_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '页面名',
  `page_label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '页面标签',
  `page_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '页面封面',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 905 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '页面' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_page
-- ----------------------------
INSERT INTO `tb_page` VALUES (1, '首页', 'home', 'http://localhost:8444/街道.png', '2021-08-07 10:32:36', '2021-12-27 12:19:01');
INSERT INTO `tb_page` VALUES (2, '归档', 'archive', 'http://localhost:8444/风车.png', '2021-08-07 10:32:36', '2021-10-04 15:43:14');
INSERT INTO `tb_page` VALUES (3, '分类', 'category', 'http://localhost:8444/街道.png', '2021-08-07 10:32:36', '2021-10-04 15:43:31');
INSERT INTO `tb_page` VALUES (4, '标签', 'tag', 'http://localhost:8444/欧村.png', '2021-08-07 10:32:36', '2021-10-04 15:43:38');
INSERT INTO `tb_page` VALUES (5, '相册', 'album', 'http://localhost:8444/山.png', '2021-08-07 10:32:36', '2021-12-27 12:23:12');
INSERT INTO `tb_page` VALUES (6, '友链', 'link', 'http://localhost:8444/水稻.png', '2021-08-07 10:32:36', '2021-10-04 15:44:02');
INSERT INTO `tb_page` VALUES (7, '关于', 'about', 'http://localhost:8444/雪山.png', '2021-08-07 10:32:36', '2021-10-04 15:44:08');
INSERT INTO `tb_page` VALUES (8, '留言', 'message', 'http://localhost:8444/街道.png', '2021-08-07 10:32:36', '2021-10-04 16:11:45');
INSERT INTO `tb_page` VALUES (9, '个人中心', 'user', 'http://localhost:8444/风车.png', '2021-08-07 10:32:36', '2021-10-04 15:45:17');
INSERT INTO `tb_page` VALUES (10, '文章列表', 'articleList', 'http://localhost:8444/街道.png', '2021-08-10 15:36:19', '2021-10-04 15:45:45');
INSERT INTO `tb_page` VALUES (904, '说说', 'talk', 'http://localhost:8444/水稻.png', '2022-01-23 00:51:24', '2022-01-23 03:01:21');

-- ----------------------------
-- Table structure for tb_photo
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo`;
CREATE TABLE `tb_photo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` int NOT NULL COMMENT '相册id',
  `photo_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '照片名',
  `photo_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '照片描述',
  `photo_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '照片地址',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '照片' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_photo
-- ----------------------------
INSERT INTO `tb_photo` VALUES (-846331903, 55443458, '1646389847026171906', NULL, 'http://localhost:8444/photos/f5e4dd1cab623f8ba1e1b2a2b18d28d6.png', 0, '2023-04-13 13:48:23', NULL);
INSERT INTO `tb_photo` VALUES (-846331902, 55443458, '1646389847026171907', NULL, 'http://localhost:8444/photos/03c3b09692988a3f20932492649125ce.png', 0, '2023-04-13 13:48:23', NULL);
INSERT INTO `tb_photo` VALUES (-846331901, 55443458, '1646389847026171908', NULL, 'http://localhost:8444/photos/e733f86f4523895e46fade42bdca7baf.png', 0, '2023-04-13 13:48:23', NULL);
INSERT INTO `tb_photo` VALUES (-846331900, 55443458, '1646389847026171909', NULL, 'http://localhost:8444/photos/fea88129629a0c54cbbb7ca841c51afa.png', 0, '2023-04-13 13:48:23', NULL);
INSERT INTO `tb_photo` VALUES (-846331899, 55443458, '1646389847026171910', NULL, 'http://localhost:8444/photos/1295afa7781ad3a2a42c63d606004fe0.png', 0, '2023-04-13 13:48:23', NULL);
INSERT INTO `tb_photo` VALUES (-846331898, 55443458, '1646389847026171911', NULL, 'http://localhost:8444/photos/fc629ba1d968b0ac912f6c411aaa5ce8.png', 0, '2023-04-13 13:48:23', NULL);

-- ----------------------------
-- Table structure for tb_photo_album
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo_album`;
CREATE TABLE `tb_photo_album`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册名',
  `album_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册描述',
  `album_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册封面',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态值 1公开 2私密',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55443459 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '相册' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_photo_album
-- ----------------------------
INSERT INTO `tb_photo_album` VALUES (55443458, '唯美图片', '记录悲惨生活喵', 'http://localhost:8444/photos/e733f86f4523895e46fade42bdca7baf.png', 0, 1, '2023-04-13 13:48:03', NULL);

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限路径',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `parent_id` int NULL DEFAULT NULL COMMENT '父权限id',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名访问 0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1891663874 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES (165, '分类模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (166, '博客信息模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (167, '友链模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (168, '文章模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (169, '日志模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (170, '标签模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (171, '照片模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (172, '用户信息模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (173, '用户账号模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (174, '留言模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (175, '相册模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (176, '菜单模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (177, '角色模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (178, '评论模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (179, '资源模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (180, '页面模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` VALUES (181, '查看博客信息', '/', 'GET', 166, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:29');
INSERT INTO `tb_resource` VALUES (182, '查看关于我信息', '/about', 'GET', 166, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:29');
INSERT INTO `tb_resource` VALUES (183, '查看后台信息', '/admin', 'GET', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (184, '修改关于我信息', '/admin/about', 'PUT', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (185, '查看后台文章', '/admin/articles', 'GET', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (186, '添加或修改文章', '/admin/articles', 'POST', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (187, '恢复或删除文章', '/admin/articles', 'PUT', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (188, '物理删除文章', '/admin/articles', 'DELETE', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (189, '上传文章图片', '/admin/articles/images', 'POST', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (190, '修改文章置顶', '/admin/articles/top', 'PUT', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (191, '根据id查看后台文章', '/admin/articles/*', 'GET', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (192, '查看后台分类列表', '/admin/categories', 'GET', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (193, '添加或修改分类', '/admin/categories', 'POST', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (194, '删除分类', '/admin/categories', 'DELETE', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (195, '搜索文章分类', '/admin/categories/search', 'GET', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (196, '查询后台评论', '/admin/comments', 'GET', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (197, '删除评论', '/admin/comments', 'DELETE', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (198, '审核评论', '/admin/comments/review', 'PUT', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (199, '查看后台友链列表', '/admin/links', 'GET', 167, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (200, '保存或修改友链', '/admin/links', 'POST', 167, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (201, '删除友链', '/admin/links', 'DELETE', 167, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (202, '查看菜单列表', '/admin/menus', 'GET', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (203, '新增或修改菜单', '/admin/menus', 'POST', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (204, '删除菜单', '/admin/menus/*', 'DELETE', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (205, '查看后台留言列表', '/admin/messages', 'GET', 174, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (206, '删除留言', '/admin/messages', 'DELETE', 174, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (207, '审核留言', '/admin/messages/review', 'PUT', 174, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (208, '查看操作日志', '/admin/operation/logs', 'GET', 169, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (209, '删除操作日志', '/admin/operation/logs', 'DELETE', 169, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (210, '获取页面列表', '/admin/pages', 'GET', 180, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (211, '保存或更新页面', '/admin/pages', 'POST', 180, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (212, '删除页面', '/admin/pages/*', 'DELETE', 180, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (213, '根据相册id获取照片列表', '/admin/photos', 'GET', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (214, '保存照片', '/admin/photos', 'POST', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (215, '更新照片信息', '/admin/photos', 'PUT', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (216, '删除照片', '/admin/photos', 'DELETE', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (217, '移动照片相册', '/admin/photos/album', 'PUT', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (218, '查看后台相册列表', '/admin/photos/albums', 'GET', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (219, '保存或更新相册', '/admin/photos/albums', 'POST', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (220, '上传相册封面', '/admin/photos/albums/cover', 'POST', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (221, '获取后台相册列表信息', '/admin/photos/albums/info', 'GET', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (222, '根据id删除相册', '/admin/photos/albums/*', 'DELETE', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (223, '根据id获取后台相册信息', '/admin/photos/albums/*/info', 'GET', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (224, '更新照片删除状态', '/admin/photos/delete', 'PUT', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (225, '查看资源列表', '/admin/resources', 'GET', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (226, '新增或修改资源', '/admin/resources', 'POST', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (227, '导入swagger接口', '/admin/resources/import/swagger', 'GET', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (228, '删除资源', '/admin/resources/*', 'DELETE', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (229, '保存或更新角色', '/admin/role', 'POST', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (230, '查看角色菜单选项', '/admin/role/menus', 'GET', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (231, '查看角色资源选项', '/admin/role/resources', 'GET', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (232, '查询角色列表', '/admin/roles', 'GET', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (233, '删除角色', '/admin/roles', 'DELETE', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (234, '查询后台标签列表', '/admin/tags', 'GET', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (235, '添加或修改标签', '/admin/tags', 'POST', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (236, '删除标签', '/admin/tags', 'DELETE', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (237, '搜索文章标签', '/admin/tags/search', 'GET', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (238, '查看当前用户菜单', '/admin/user/menus', 'GET', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (239, '查询后台用户列表', '/admin/users', 'GET', 173, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (240, '修改用户禁用状态', '/admin/users/disable', 'PUT', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (241, '查看在线用户', '/admin/users/online', 'GET', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (242, '修改管理员密码', '/admin/users/password', 'PUT', 173, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (243, '查询用户角色选项', '/admin/users/role', 'GET', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (244, '修改用户角色', '/admin/users/role', 'PUT', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (245, '下线用户', '/admin/users/*/online', 'DELETE', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (246, '获取网站配置', '/admin/website/config', 'GET', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (247, '更新网站配置', '/admin/website/config', 'PUT', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (248, '根据相册id查看照片列表', '/albums/*/photos', 'GET', 171, 1, '2021-08-11 21:04:22', '2021-08-11 21:06:35');
INSERT INTO `tb_resource` VALUES (249, '查看首页文章', '/articles', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:45');
INSERT INTO `tb_resource` VALUES (250, '查看文章归档', '/articles/archives', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:47');
INSERT INTO `tb_resource` VALUES (251, '根据条件查询文章', '/articles/condition', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:47');
INSERT INTO `tb_resource` VALUES (252, '搜索文章', '/articles/search', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:48');
INSERT INTO `tb_resource` VALUES (253, '根据id查看文章', '/articles/*', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:49');
INSERT INTO `tb_resource` VALUES (254, '点赞文章', '/articles/*/like', 'POST', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (255, '查看分类列表', '/categories', 'GET', 165, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:26');
INSERT INTO `tb_resource` VALUES (256, '查询评论', '/comments', 'GET', 178, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:33');
INSERT INTO `tb_resource` VALUES (257, '添加评论', '/comments', 'POST', 178, 0, '2021-08-11 21:04:22', '2021-08-11 21:10:05');
INSERT INTO `tb_resource` VALUES (258, '评论点赞', '/comments/*/like', 'POST', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (259, '查询评论下的回复', '/comments/*/replies', 'GET', 178, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:30');
INSERT INTO `tb_resource` VALUES (260, '查看友链列表', '/links', 'GET', 167, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:41');
INSERT INTO `tb_resource` VALUES (261, '查看留言列表', '/messages', 'GET', 174, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:14');
INSERT INTO `tb_resource` VALUES (262, '添加留言', '/messages', 'POST', 174, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:15');
INSERT INTO `tb_resource` VALUES (263, '获取相册列表', '/photos/albums', 'GET', 175, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:20');
INSERT INTO `tb_resource` VALUES (264, '用户注册', '/register', 'POST', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:01');
INSERT INTO `tb_resource` VALUES (265, '查询标签列表', '/tags', 'GET', 170, 1, '2021-08-11 21:04:22', '2021-08-11 21:06:30');
INSERT INTO `tb_resource` VALUES (267, '更新用户头像', '/users/avatar', 'POST', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (268, '发送邮箱验证码', '/users/code', 'GET', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:02');
INSERT INTO `tb_resource` VALUES (269, '绑定用户邮箱', '/users/email', 'POST', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (270, '更新用户信息', '/users/info', 'PUT', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` VALUES (271, 'qq登录', '/users/oauth/qq', 'POST', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:06');
INSERT INTO `tb_resource` VALUES (272, '微博登录', '/users/oauth/weibo', 'POST', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:06');
INSERT INTO `tb_resource` VALUES (273, '修改密码', '/users/password', 'PUT', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:09');
INSERT INTO `tb_resource` VALUES (274, '上传语音', '/voice', 'POST', 166, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:33');
INSERT INTO `tb_resource` VALUES (275, '上传访客信息', '/report', 'POST', 166, 1, '2021-08-24 00:32:05', '2021-08-24 00:32:07');
INSERT INTO `tb_resource` VALUES (276, '获取用户区域分布', '/admin/users/area', 'GET', 173, 0, '2021-08-24 00:32:35', '2021-09-24 16:25:34');
INSERT INTO `tb_resource` VALUES (278, '说说模块', NULL, NULL, NULL, 0, '2022-01-24 01:29:13', NULL);
INSERT INTO `tb_resource` VALUES (279, '查看首页说说', '/home/talks', 'GET', 278, 1, '2022-01-24 01:29:29', '2022-01-24 01:31:56');
INSERT INTO `tb_resource` VALUES (280, '查看说说列表', '/talks', 'GET', 278, 1, '2022-01-24 01:29:52', '2022-01-24 01:31:56');
INSERT INTO `tb_resource` VALUES (281, '根据id查看说说', '/talks/*', 'GET', 278, 1, '2022-01-24 01:30:10', '2022-01-24 01:31:57');
INSERT INTO `tb_resource` VALUES (282, '点赞说说', '/talks/*/like', 'POST', 278, 0, '2022-01-24 01:30:30', NULL);
INSERT INTO `tb_resource` VALUES (283, '上传说说图片', '/admin/talks/images', 'POST', 278, 0, '2022-01-24 01:30:46', NULL);
INSERT INTO `tb_resource` VALUES (284, '保存或修改说说', '/admin/talks', 'POST', 278, 0, '2022-01-24 01:31:04', NULL);
INSERT INTO `tb_resource` VALUES (285, '删除说说', '/admin/talks', 'DELETE', 278, 0, '2022-01-24 01:31:22', NULL);
INSERT INTO `tb_resource` VALUES (286, '查看后台说说', '/admin/talks', 'GET', 278, 0, '2022-01-24 01:31:38', NULL);
INSERT INTO `tb_resource` VALUES (287, '根据id查看后台说说', '/admin/talks/*', 'GET', 278, 0, '2022-01-24 01:31:53', '2022-01-24 01:33:14');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `role_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用  0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1929420803 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '管理员', 'admin', 0, '2021-03-22 14:10:21', '2022-01-24 01:32:26');
INSERT INTO `tb_role` VALUES (2, '用户', 'user', 0, '2021-03-22 14:25:25', '2022-01-24 01:32:21');
INSERT INTO `tb_role` VALUES (3, '测试2', 'test', 0, '2021-03-22 14:42:23', '2022-01-24 01:32:59');
INSERT INTO `tb_role` VALUES (302067714, '测试', 'test', 0, '2021-03-22 14:42:23', '2022-01-24 01:32:59');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2059440133 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES (1397, 8, 1);
INSERT INTO `tb_role_menu` VALUES (1398, 8, 2);
INSERT INTO `tb_role_menu` VALUES (1399, 8, 6);
INSERT INTO `tb_role_menu` VALUES (1400, 8, 7);
INSERT INTO `tb_role_menu` VALUES (1401, 8, 8);
INSERT INTO `tb_role_menu` VALUES (1402, 8, 9);
INSERT INTO `tb_role_menu` VALUES (1403, 8, 10);
INSERT INTO `tb_role_menu` VALUES (1404, 8, 3);
INSERT INTO `tb_role_menu` VALUES (1405, 8, 11);
INSERT INTO `tb_role_menu` VALUES (1406, 8, 12);
INSERT INTO `tb_role_menu` VALUES (1407, 8, 202);
INSERT INTO `tb_role_menu` VALUES (1408, 8, 13);
INSERT INTO `tb_role_menu` VALUES (1409, 8, 14);
INSERT INTO `tb_role_menu` VALUES (1410, 8, 201);
INSERT INTO `tb_role_menu` VALUES (1411, 8, 4);
INSERT INTO `tb_role_menu` VALUES (1412, 8, 16);
INSERT INTO `tb_role_menu` VALUES (1413, 8, 15);
INSERT INTO `tb_role_menu` VALUES (1414, 8, 17);
INSERT INTO `tb_role_menu` VALUES (1415, 8, 18);
INSERT INTO `tb_role_menu` VALUES (1416, 8, 19);
INSERT INTO `tb_role_menu` VALUES (1417, 8, 20);
INSERT INTO `tb_role_menu` VALUES (1418, 8, 5);
INSERT INTO `tb_role_menu` VALUES (1595, 9, 1);
INSERT INTO `tb_role_menu` VALUES (1596, 9, 2);
INSERT INTO `tb_role_menu` VALUES (1597, 9, 6);
INSERT INTO `tb_role_menu` VALUES (1598, 9, 7);
INSERT INTO `tb_role_menu` VALUES (1599, 9, 8);
INSERT INTO `tb_role_menu` VALUES (1600, 9, 9);
INSERT INTO `tb_role_menu` VALUES (1601, 9, 10);
INSERT INTO `tb_role_menu` VALUES (1602, 9, 3);
INSERT INTO `tb_role_menu` VALUES (1603, 9, 11);
INSERT INTO `tb_role_menu` VALUES (1604, 9, 12);
INSERT INTO `tb_role_menu` VALUES (1605, 9, 202);
INSERT INTO `tb_role_menu` VALUES (1606, 9, 13);
INSERT INTO `tb_role_menu` VALUES (1607, 9, 14);
INSERT INTO `tb_role_menu` VALUES (1608, 9, 201);
INSERT INTO `tb_role_menu` VALUES (1609, 9, 4);
INSERT INTO `tb_role_menu` VALUES (1610, 9, 16);
INSERT INTO `tb_role_menu` VALUES (1611, 9, 15);
INSERT INTO `tb_role_menu` VALUES (1612, 9, 17);
INSERT INTO `tb_role_menu` VALUES (1613, 9, 18);
INSERT INTO `tb_role_menu` VALUES (1614, 9, 19);
INSERT INTO `tb_role_menu` VALUES (1615, 9, 20);
INSERT INTO `tb_role_menu` VALUES (1616, 9, 5);
INSERT INTO `tb_role_menu` VALUES (1639, 13, 2);
INSERT INTO `tb_role_menu` VALUES (1640, 13, 6);
INSERT INTO `tb_role_menu` VALUES (1641, 13, 7);
INSERT INTO `tb_role_menu` VALUES (1642, 13, 8);
INSERT INTO `tb_role_menu` VALUES (1643, 13, 9);
INSERT INTO `tb_role_menu` VALUES (1644, 13, 10);
INSERT INTO `tb_role_menu` VALUES (1645, 13, 3);
INSERT INTO `tb_role_menu` VALUES (1646, 13, 11);
INSERT INTO `tb_role_menu` VALUES (1647, 13, 12);
INSERT INTO `tb_role_menu` VALUES (2366, 14, 1);
INSERT INTO `tb_role_menu` VALUES (2367, 14, 2);
INSERT INTO `tb_role_menu` VALUES (2461, 1, 1);
INSERT INTO `tb_role_menu` VALUES (2462, 1, 2);
INSERT INTO `tb_role_menu` VALUES (2463, 1, 6);
INSERT INTO `tb_role_menu` VALUES (2464, 1, 7);
INSERT INTO `tb_role_menu` VALUES (2465, 1, 8);
INSERT INTO `tb_role_menu` VALUES (2466, 1, 9);
INSERT INTO `tb_role_menu` VALUES (2467, 1, 10);
INSERT INTO `tb_role_menu` VALUES (2468, 1, 3);
INSERT INTO `tb_role_menu` VALUES (2469, 1, 11);
INSERT INTO `tb_role_menu` VALUES (2470, 1, 12);
INSERT INTO `tb_role_menu` VALUES (2471, 1, 202);
INSERT INTO `tb_role_menu` VALUES (2472, 1, 13);
INSERT INTO `tb_role_menu` VALUES (2473, 1, 201);
INSERT INTO `tb_role_menu` VALUES (2474, 1, 213);
INSERT INTO `tb_role_menu` VALUES (2475, 1, 14);
INSERT INTO `tb_role_menu` VALUES (2476, 1, 15);
INSERT INTO `tb_role_menu` VALUES (2477, 1, 16);
INSERT INTO `tb_role_menu` VALUES (2478, 1, 4);
INSERT INTO `tb_role_menu` VALUES (2479, 1, 214);
INSERT INTO `tb_role_menu` VALUES (2480, 1, 209);
INSERT INTO `tb_role_menu` VALUES (2481, 1, 17);
INSERT INTO `tb_role_menu` VALUES (2482, 1, 18);
INSERT INTO `tb_role_menu` VALUES (2483, 1, 205);
INSERT INTO `tb_role_menu` VALUES (2484, 1, 206);
INSERT INTO `tb_role_menu` VALUES (2485, 1, 208);
INSERT INTO `tb_role_menu` VALUES (2486, 1, 210);
INSERT INTO `tb_role_menu` VALUES (2487, 1, 215);
INSERT INTO `tb_role_menu` VALUES (2488, 1, 216);
INSERT INTO `tb_role_menu` VALUES (2489, 1, 217);
INSERT INTO `tb_role_menu` VALUES (2490, 1, 218);
INSERT INTO `tb_role_menu` VALUES (2491, 1, 19);
INSERT INTO `tb_role_menu` VALUES (2492, 1, 20);
INSERT INTO `tb_role_menu` VALUES (2493, 1, 5);
INSERT INTO `tb_role_menu` VALUES (2494, 3, 1);
INSERT INTO `tb_role_menu` VALUES (2495, 3, 2);
INSERT INTO `tb_role_menu` VALUES (2496, 3, 6);
INSERT INTO `tb_role_menu` VALUES (2497, 3, 7);
INSERT INTO `tb_role_menu` VALUES (2498, 3, 8);
INSERT INTO `tb_role_menu` VALUES (2499, 3, 9);
INSERT INTO `tb_role_menu` VALUES (2500, 3, 10);
INSERT INTO `tb_role_menu` VALUES (2501, 3, 3);
INSERT INTO `tb_role_menu` VALUES (2502, 3, 11);
INSERT INTO `tb_role_menu` VALUES (2503, 3, 12);
INSERT INTO `tb_role_menu` VALUES (2504, 3, 202);
INSERT INTO `tb_role_menu` VALUES (2505, 3, 13);
INSERT INTO `tb_role_menu` VALUES (2506, 3, 201);
INSERT INTO `tb_role_menu` VALUES (2507, 3, 213);
INSERT INTO `tb_role_menu` VALUES (2508, 3, 14);
INSERT INTO `tb_role_menu` VALUES (2509, 3, 15);
INSERT INTO `tb_role_menu` VALUES (2510, 3, 16);
INSERT INTO `tb_role_menu` VALUES (2511, 3, 4);
INSERT INTO `tb_role_menu` VALUES (2512, 3, 214);
INSERT INTO `tb_role_menu` VALUES (2513, 3, 209);
INSERT INTO `tb_role_menu` VALUES (2514, 3, 17);
INSERT INTO `tb_role_menu` VALUES (2515, 3, 18);
INSERT INTO `tb_role_menu` VALUES (2516, 3, 205);
INSERT INTO `tb_role_menu` VALUES (2517, 3, 206);
INSERT INTO `tb_role_menu` VALUES (2518, 3, 208);
INSERT INTO `tb_role_menu` VALUES (2519, 3, 210);
INSERT INTO `tb_role_menu` VALUES (2520, 3, 215);
INSERT INTO `tb_role_menu` VALUES (2521, 3, 216);
INSERT INTO `tb_role_menu` VALUES (2522, 3, 217);
INSERT INTO `tb_role_menu` VALUES (2523, 3, 218);
INSERT INTO `tb_role_menu` VALUES (2524, 3, 19);
INSERT INTO `tb_role_menu` VALUES (2525, 3, 20);
INSERT INTO `tb_role_menu` VALUES (2526, 3, 5);
INSERT INTO `tb_role_menu` VALUES (2527, 1, 219);

-- ----------------------------
-- Table structure for tb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` int NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4886 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES (4750, 14, 238);
INSERT INTO `tb_role_resource` VALUES (4751, 2, 254);
INSERT INTO `tb_role_resource` VALUES (4752, 2, 267);
INSERT INTO `tb_role_resource` VALUES (4753, 2, 269);
INSERT INTO `tb_role_resource` VALUES (4754, 2, 270);
INSERT INTO `tb_role_resource` VALUES (4755, 2, 257);
INSERT INTO `tb_role_resource` VALUES (4756, 2, 258);
INSERT INTO `tb_role_resource` VALUES (4757, 2, 282);
INSERT INTO `tb_role_resource` VALUES (4758, 1, 165);
INSERT INTO `tb_role_resource` VALUES (4759, 1, 192);
INSERT INTO `tb_role_resource` VALUES (4760, 1, 193);
INSERT INTO `tb_role_resource` VALUES (4761, 1, 194);
INSERT INTO `tb_role_resource` VALUES (4762, 1, 195);
INSERT INTO `tb_role_resource` VALUES (4763, 1, 166);
INSERT INTO `tb_role_resource` VALUES (4764, 1, 183);
INSERT INTO `tb_role_resource` VALUES (4765, 1, 184);
INSERT INTO `tb_role_resource` VALUES (4766, 1, 246);
INSERT INTO `tb_role_resource` VALUES (4767, 1, 247);
INSERT INTO `tb_role_resource` VALUES (4768, 1, 167);
INSERT INTO `tb_role_resource` VALUES (4769, 1, 199);
INSERT INTO `tb_role_resource` VALUES (4770, 1, 200);
INSERT INTO `tb_role_resource` VALUES (4771, 1, 201);
INSERT INTO `tb_role_resource` VALUES (4772, 1, 168);
INSERT INTO `tb_role_resource` VALUES (4773, 1, 185);
INSERT INTO `tb_role_resource` VALUES (4774, 1, 186);
INSERT INTO `tb_role_resource` VALUES (4775, 1, 187);
INSERT INTO `tb_role_resource` VALUES (4776, 1, 188);
INSERT INTO `tb_role_resource` VALUES (4777, 1, 189);
INSERT INTO `tb_role_resource` VALUES (4778, 1, 190);
INSERT INTO `tb_role_resource` VALUES (4779, 1, 191);
INSERT INTO `tb_role_resource` VALUES (4780, 1, 254);
INSERT INTO `tb_role_resource` VALUES (4781, 1, 169);
INSERT INTO `tb_role_resource` VALUES (4782, 1, 208);
INSERT INTO `tb_role_resource` VALUES (4783, 1, 209);
INSERT INTO `tb_role_resource` VALUES (4784, 1, 170);
INSERT INTO `tb_role_resource` VALUES (4785, 1, 234);
INSERT INTO `tb_role_resource` VALUES (4786, 1, 235);
INSERT INTO `tb_role_resource` VALUES (4787, 1, 236);
INSERT INTO `tb_role_resource` VALUES (4788, 1, 237);
INSERT INTO `tb_role_resource` VALUES (4789, 1, 171);
INSERT INTO `tb_role_resource` VALUES (4790, 1, 213);
INSERT INTO `tb_role_resource` VALUES (4791, 1, 214);
INSERT INTO `tb_role_resource` VALUES (4792, 1, 215);
INSERT INTO `tb_role_resource` VALUES (4793, 1, 216);
INSERT INTO `tb_role_resource` VALUES (4794, 1, 217);
INSERT INTO `tb_role_resource` VALUES (4795, 1, 224);
INSERT INTO `tb_role_resource` VALUES (4796, 1, 172);
INSERT INTO `tb_role_resource` VALUES (4797, 1, 240);
INSERT INTO `tb_role_resource` VALUES (4798, 1, 241);
INSERT INTO `tb_role_resource` VALUES (4799, 1, 244);
INSERT INTO `tb_role_resource` VALUES (4800, 1, 245);
INSERT INTO `tb_role_resource` VALUES (4801, 1, 267);
INSERT INTO `tb_role_resource` VALUES (4802, 1, 269);
INSERT INTO `tb_role_resource` VALUES (4803, 1, 270);
INSERT INTO `tb_role_resource` VALUES (4804, 1, 173);
INSERT INTO `tb_role_resource` VALUES (4805, 1, 239);
INSERT INTO `tb_role_resource` VALUES (4806, 1, 242);
INSERT INTO `tb_role_resource` VALUES (4807, 1, 276);
INSERT INTO `tb_role_resource` VALUES (4808, 1, 174);
INSERT INTO `tb_role_resource` VALUES (4809, 1, 205);
INSERT INTO `tb_role_resource` VALUES (4810, 1, 206);
INSERT INTO `tb_role_resource` VALUES (4811, 1, 207);
INSERT INTO `tb_role_resource` VALUES (4812, 1, 175);
INSERT INTO `tb_role_resource` VALUES (4813, 1, 218);
INSERT INTO `tb_role_resource` VALUES (4814, 1, 219);
INSERT INTO `tb_role_resource` VALUES (4815, 1, 220);
INSERT INTO `tb_role_resource` VALUES (4816, 1, 221);
INSERT INTO `tb_role_resource` VALUES (4817, 1, 222);
INSERT INTO `tb_role_resource` VALUES (4818, 1, 223);
INSERT INTO `tb_role_resource` VALUES (4819, 1, 176);
INSERT INTO `tb_role_resource` VALUES (4820, 1, 202);
INSERT INTO `tb_role_resource` VALUES (4821, 1, 203);
INSERT INTO `tb_role_resource` VALUES (4822, 1, 204);
INSERT INTO `tb_role_resource` VALUES (4823, 1, 230);
INSERT INTO `tb_role_resource` VALUES (4824, 1, 238);
INSERT INTO `tb_role_resource` VALUES (4825, 1, 177);
INSERT INTO `tb_role_resource` VALUES (4826, 1, 229);
INSERT INTO `tb_role_resource` VALUES (4827, 1, 232);
INSERT INTO `tb_role_resource` VALUES (4828, 1, 233);
INSERT INTO `tb_role_resource` VALUES (4829, 1, 243);
INSERT INTO `tb_role_resource` VALUES (4830, 1, 178);
INSERT INTO `tb_role_resource` VALUES (4831, 1, 196);
INSERT INTO `tb_role_resource` VALUES (4832, 1, 197);
INSERT INTO `tb_role_resource` VALUES (4833, 1, 198);
INSERT INTO `tb_role_resource` VALUES (4834, 1, 257);
INSERT INTO `tb_role_resource` VALUES (4835, 1, 258);
INSERT INTO `tb_role_resource` VALUES (4836, 1, 179);
INSERT INTO `tb_role_resource` VALUES (4837, 1, 225);
INSERT INTO `tb_role_resource` VALUES (4838, 1, 226);
INSERT INTO `tb_role_resource` VALUES (4839, 1, 227);
INSERT INTO `tb_role_resource` VALUES (4840, 1, 228);
INSERT INTO `tb_role_resource` VALUES (4841, 1, 231);
INSERT INTO `tb_role_resource` VALUES (4842, 1, 180);
INSERT INTO `tb_role_resource` VALUES (4843, 1, 210);
INSERT INTO `tb_role_resource` VALUES (4844, 1, 211);
INSERT INTO `tb_role_resource` VALUES (4845, 1, 212);
INSERT INTO `tb_role_resource` VALUES (4846, 1, 278);
INSERT INTO `tb_role_resource` VALUES (4847, 1, 282);
INSERT INTO `tb_role_resource` VALUES (4848, 1, 283);
INSERT INTO `tb_role_resource` VALUES (4849, 1, 284);
INSERT INTO `tb_role_resource` VALUES (4850, 1, 285);
INSERT INTO `tb_role_resource` VALUES (4851, 1, 286);
INSERT INTO `tb_role_resource` VALUES (4852, 1, 287);
INSERT INTO `tb_role_resource` VALUES (4853, 3, 192);
INSERT INTO `tb_role_resource` VALUES (4854, 3, 195);
INSERT INTO `tb_role_resource` VALUES (4855, 3, 183);
INSERT INTO `tb_role_resource` VALUES (4856, 3, 246);
INSERT INTO `tb_role_resource` VALUES (4857, 3, 199);
INSERT INTO `tb_role_resource` VALUES (4858, 3, 185);
INSERT INTO `tb_role_resource` VALUES (4859, 3, 191);
INSERT INTO `tb_role_resource` VALUES (4860, 3, 254);
INSERT INTO `tb_role_resource` VALUES (4861, 3, 208);
INSERT INTO `tb_role_resource` VALUES (4862, 3, 234);
INSERT INTO `tb_role_resource` VALUES (4863, 3, 237);
INSERT INTO `tb_role_resource` VALUES (4864, 3, 213);
INSERT INTO `tb_role_resource` VALUES (4865, 3, 241);
INSERT INTO `tb_role_resource` VALUES (4866, 3, 239);
INSERT INTO `tb_role_resource` VALUES (4867, 3, 276);
INSERT INTO `tb_role_resource` VALUES (4868, 3, 205);
INSERT INTO `tb_role_resource` VALUES (4869, 3, 218);
INSERT INTO `tb_role_resource` VALUES (4870, 3, 221);
INSERT INTO `tb_role_resource` VALUES (4871, 3, 223);
INSERT INTO `tb_role_resource` VALUES (4872, 3, 202);
INSERT INTO `tb_role_resource` VALUES (4873, 3, 230);
INSERT INTO `tb_role_resource` VALUES (4874, 3, 238);
INSERT INTO `tb_role_resource` VALUES (4875, 3, 232);
INSERT INTO `tb_role_resource` VALUES (4876, 3, 243);
INSERT INTO `tb_role_resource` VALUES (4877, 3, 196);
INSERT INTO `tb_role_resource` VALUES (4878, 3, 257);
INSERT INTO `tb_role_resource` VALUES (4879, 3, 258);
INSERT INTO `tb_role_resource` VALUES (4880, 3, 225);
INSERT INTO `tb_role_resource` VALUES (4881, 3, 231);
INSERT INTO `tb_role_resource` VALUES (4882, 3, 210);
INSERT INTO `tb_role_resource` VALUES (4883, 3, 282);
INSERT INTO `tb_role_resource` VALUES (4884, 3, 286);
INSERT INTO `tb_role_resource` VALUES (4885, 3, 287);

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 717332483 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
INSERT INTO `tb_tag` VALUES (29, '测试标签', '2022-01-24 23:33:57', NULL);
INSERT INTO `tb_tag` VALUES (30, 'java', '2022-05-15 14:17:16', NULL);
INSERT INTO `tb_tag` VALUES (31, 'minecraft1', '2022-05-15 14:17:26', '2022-05-24 11:13:12');
INSERT INTO `tb_tag` VALUES (230690817, 'spigot', '2022-05-17 14:42:11', NULL);

-- ----------------------------
-- Table structure for tb_talk
-- ----------------------------
DROP TABLE IF EXISTS `tb_talk`;
CREATE TABLE `tb_talk`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '说说id',
  `user_id` int NOT NULL COMMENT '用户id',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '说说内容',
  `images` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1.公开 2.私密',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1992314883 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_talk
-- ----------------------------
INSERT INTO `tb_talk` VALUES (-1198526463, 1, '关注永雏塔菲喵🥰🥰🥰', '[\"http://localhost:8444/talks/e733f86f4523895e46fade42bdca7baf.png\"]', 1, 1, '2023-04-10 15:51:09', NULL);
INSERT INTO `tb_talk` VALUES (-1144000511, 1, '今天明天后天都不是什么好日子🤗', '[\"http://localhost:8444/talks/e733f86f4523895e46fade42bdca7baf.png\"]', 0, 1, '2023-04-10 15:50:40', NULL);
INSERT INTO `tb_talk` VALUES (-414482431, 1, '个你能😘🤩🤭', '[\"http://localhost:8444/talks/a3f704e212e4f9ce9641dd99e17bdf65.png\"]', 1, 1, '2023-04-15 14:48:49', NULL);

-- ----------------------------
-- Table structure for tb_unique_view
-- ----------------------------
DROP TABLE IF EXISTS `tb_unique_view`;
CREATE TABLE `tb_unique_view`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `views_count` int NOT NULL COMMENT '访问量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 537 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_unique_view
-- ----------------------------
INSERT INTO `tb_unique_view` VALUES (534, 23, '2022-05-09 11:09:42', NULL);
INSERT INTO `tb_unique_view` VALUES (535, 11, '2022-05-08 11:09:54', NULL);
INSERT INTO `tb_unique_view` VALUES (536, 44, '2022-05-07 11:09:59', NULL);

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_info_id` int NOT NULL COMMENT '用户信息id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `login_type` tinyint(1) NOT NULL COMMENT '登录类型',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1019944963 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------
INSERT INTO `tb_user_auth` VALUES (1, 1, 'admin@qq.com', '$2a$10$.JizovEWNzlKpJ0Il5psK.bHE22qm/AA9veErg1.9Hmv8rjCwAjQm', 1, '127.0.0.1', '未知', '2021-08-12 15:43:18', '2023-04-15 14:46:27', '2023-04-15 14:46:27');
INSERT INTO `tb_user_auth` VALUES (995, 1005, 'xiaainy@163.com', '$2a$10$.JizovEWNzlKpJ0Il5psK.bHE22qm/AA9veErg1.9Hmv8rjCwAjQm', 1, '61.222.202.239', '台湾省', '2022-01-27 10:58:11', '2022-01-27 10:58:12', '2022-01-27 10:58:12');
INSERT INTO `tb_user_auth` VALUES (996, 1006, 'xxx@qq.com', '$2a$10$.JizovEWNzlKpJ0Il5psK.bHE22qm/AA9veErg1.9Hmv8rjCwAjQm', 1, '255.3.3.3', NULL, '2022-05-28 19:39:38', '2022-05-28 19:39:43', '2022-05-28 19:39:42');
INSERT INTO `tb_user_auth` VALUES (1019944962, -1920262142, '56620507', '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', 3, '127.0.0.1', '未知', '2023-04-10 21:21:58', '2023-04-15 14:38:26', '2023-04-15 14:38:26');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱号',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户简介',
  `web_site` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人网站',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES (-1920262142, NULL, 'Shinyoki', 'https://avatars.githubusercontent.com/u/56620507?v=4', NULL, NULL, 0, '2023-04-10 21:21:57', NULL);
INSERT INTO `tb_user_info` VALUES (1, 'admin@qq.com', '管理员', 'http://localhost:8444/头像.png', 'admin@qq.com', NULL, 0, '2021-08-12 15:43:17', '2021-08-12 15:51:42');
INSERT INTO `tb_user_info` VALUES (1005, 'xiaainy@163.com', '用户1486533968342859778', 'http://localhost:8444/头像.png', NULL, NULL, 0, '2022-01-27 10:58:11', NULL);
INSERT INTO `tb_user_info` VALUES (1006, 'xxx@qq.com', '测试用户1', 'http://localhost:8444/头像.png', '233', NULL, 0, '2022-05-28 09:59:56', '2022-05-28 21:25:12');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1082859522 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (-440381438, 1006, 2);
INSERT INTO `tb_user_role` VALUES (1001, 1, 1);
INSERT INTO `tb_user_role` VALUES (1002, 1005, 2);
INSERT INTO `tb_user_role` VALUES (1082859521, -1920262142, 2);

-- ----------------------------
-- Table structure for tb_website_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_website_config`;
CREATE TABLE `tb_website_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `config` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_website_config
-- ----------------------------
INSERT INTO `tb_website_config` VALUES (1, '{\"alipayQRCode\":\"http://localhost:8444/街道.png\",\"gitee\":\"\",\"github\":\"\",\"isChatRoom\":1,\"isCommentReview\":0,\"isEmailNotice\":1,\"isMessageReview\":0,\"isMusicPlayer\":1,\"isReward\":1,\"qq\":\"\",\"socialLoginList\":[\"github\"],\"socialUrlList\":[\"qq\",\"github\",\"gitee\"],\"touristAvatar\":\"http://localhost:8444/头像.png\",\"userAvatar\":\"http://localhost:8444/头像.png\",\"websiteAuthor\":\"Senko\",\"websiteAvatar\":\"http://localhost:8444/blog.png\",\"websiteCreateTime\":\"2019-12-10\",\"websiteIntro\":\"No Girl No Life\",\"websiteName\":\"个人博客\",\"websiteNotice\":\"我不会闲的没事就发公告的\",\"websiteRecordNo\":\"这里填备案号\",\"websocketUrl\":\"ws://127.0.0.1:8080/websocket\",\"weixinQRCode\":\"http://localhost:8444/呀哈哈.png\"}', '2021-08-09 19:37:30', '2023-04-15 09:22:35');

SET FOREIGN_KEY_CHECKS = 1;

-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 12, 2024 lúc 03:45 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tintucspringboot`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `adminmenu_role`
--

CREATE TABLE `adminmenu_role` (
  `adminmenu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `adminmenu_role`
--

INSERT INTO `adminmenu_role` (`adminmenu_id`, `role_id`) VALUES
(1, 3),
(1, 4),
(17, 3),
(17, 4),
(18, 3),
(19, 3),
(19, 4),
(20, 3),
(21, 3),
(21, 4),
(22, 3),
(23, 3),
(24, 3),
(25, 3),
(26, 3),
(27, 3),
(28, 3),
(29, 3),
(30, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin_menu`
--

CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `id_name` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `menu_order` bigint(20) DEFAULT NULL,
  `menu_target` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `admin_menu`
--

INSERT INTO `admin_menu` (`id`, `active`, `class_name`, `icon`, `id_name`, `link`, `menu_order`, `menu_target`, `name`, `parent_id`) VALUES
(1, b'1', '', 'bi bi-card-list', '', '', 1, 'forms-category', 'Quản lý thể loại', NULL),
(4, b'1', '', '', '', '/admin/categories', 1, '', 'Danh sách thể loại', 1),
(7, b'1', '', '', '', '/admin/categories/create', 2, '', 'Thêm thể loại', 1),
(17, b'1', '', 'bi bi-newspaper', '', '', 2, 'forms-post', 'Quản lý bài viết', NULL),
(18, b'1', '', '', '', '/admin/posts', 1, '', 'Danh sách bài viết', 17),
(19, b'1', '', '', '', '/admin/posts/create', 2, '', 'Thêm bài viết', 17),
(20, b'1', '', '', '', '/admin/posts/approve', 3, '', 'Duyệt bài viết', 17),
(21, b'1', '', '', '', '/admin/posts', 4, '', 'Bài viết của bạn', 17),
(22, b'1', '', 'bi bi-people', '', '', 3, 'forms-user', 'Quản lý người dùng', NULL),
(23, b'1', '', '', '', '/admin/users', 1, '', 'Danh sách người dùng', 22),
(24, b'1', '', '', '', '/admin/users/create', 2, '', 'Thêm người dùng mới', 22),
(25, b'1', '', 'bi bi-diagram-3', '', '', 5, 'forms-role', 'Quản lý chức vụ', NULL),
(26, b'1', '', '', '', '/admin/roles', 1, '', 'Danh sách chức vụ', 25),
(27, b'1', '', '', '', '/admin/roles/create', 2, '', 'Thêm chức vụ mới', 25),
(28, b'1', '', 'bi bi-list-check', '', '', 6, 'forms-admin-menu', 'Quản lý Admin Menu', NULL),
(29, b'1', '', '', '', '/admin/menus-aside', 1, '', 'Danh sách Admin Menu', 28),
(30, b'1', '', '', '', '/admin/menus-aside/create', 2, '', 'Thêm Admin Menu mới', 28);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `description`, `name`, `parent_id`, `slug`) VALUES
(1, '', 'Thế giới', NULL, 'The-gioi'),
(2, '', 'Đời sống', NULL, 'Đoi-song'),
(10, 'nhịp sống trong đời sống', 'Nhịp sống', 2, 'Nhip-song'),
(12, 'tư liệu', 'Tư liệu', 1, 'Tu-lieu'),
(13, 'Thời sự', 'Thời sự', NULL, 'Thoi-su'),
(14, 'Kinh doanh', 'Kinh doanh', NULL, 'Kinh-doanh'),
(15, 'sức khoẻ', 'Sức khoẻ', NULL, 'Suc-khoe'),
(16, 'thuộc sức khoẻ', 'Dinh dưỡng', 15, 'Dinh-duong'),
(17, 'quân sự', 'Quân sự', 1, 'Quan-su'),
(18, 'phân tích', 'Phân tích', 1, 'Phan-tich'),
(19, 'giải trí', 'Giải trí', 2, 'Giai-tri'),
(20, 'thể thao', 'Thể thao', 2, 'The-thao');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `comment`
--

INSERT INTO `comment` (`id`, `comment`, `created_date`, `user_name`, `post_id`) VALUES
(2, 'vãi ò', '2024-01-11 00:04:26.284000', 'Em Lý', 9),
(3, 'haha', '2024-01-11 00:17:17.609000', 'Ly', 9);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `details` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pulish_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `view` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `post`
--

INSERT INTO `post` (`id`, `active`, `description`, `details`, `image`, `pulish_date`, `title`, `view`, `author_id`, `category_id`) VALUES
(1, b'1', 'vak', '<p>lol&aacute;ccs</p>', '3e9bdaa7-4cb3-41a7-b2e0-d59db401e730.jpg', '2024-01-03 13:38:11.651000', ' nến và hoa', 0, NULL, 2),
(2, b'1', 'àddđ', '<p>sdafffffffff</p>', '48f58e78-bc33-4199-a921-7a700daf1e40.jpg', '2023-12-21 15:08:41.729000', 'áddd', 0, NULL, 1),
(6, b'1', 'hành chính', '<p>&aacute;dfsadf</p>', 'dc020527-0105-43e9-b559-d8b6e5cdf6ed.png', '2023-12-23 17:16:19.622000', 'sdafsafd', 0, NULL, 1),
(7, b'1', 'hành chính', '<p>sdfafdasd</p>', '1055d7a0-02d2-40c3-bb92-a35e8c59306f.png', '2024-01-03 14:29:51.287000', ' Bệnh nhân ung thư ngày càng tăng và trẻ hóa', 8, 6, 1),
(8, b'1', 'hành chính', '<p>saddsa</p>', 'edb56da9-ceeb-42de-b31e-ddbfb2526728.jpg', '2024-01-03 13:40:54.862000', ' test dien', 6, 6, 1),
(9, b'1', 'Ca sĩ Chi Pu, 31 tuổi, vượt bốn người đẹp đoạt giải Mỹ nhân - tôn vinh hình ảnh và vai trò người phụ nữ hiện đại - ở \"Ngôi sao của năm 2023\".', '<p class=\"Normal\">Lễ trao giải diễn ra tối 6/1 trong gần ba tiếng với nhiều cung bậc cảm x&uacute;c. 11 giải thưởng chia đều cho hai nh&oacute;m hạng mục Nghệ thuật - Giải tr&iacute; đ&atilde; t&igrave;m ra chủ nh&acirc;n xứng đ&aacute;ng, với sự đồng thuận của hội đồng chuy&ecirc;n m&ocirc;n lẫn b&igrave;nh chọn từ kh&aacute;n giả.</p>\r\n<p class=\"Normal\">Thu h&uacute;t hơn 262 ngh&igrave;n lượt b&igrave;nh chọn, Chi Pu được vinh danh ở hạng mục quan trọng - Mỹ nh&acirc;n của năm. Người đẹp ph&aacute;t biểu cảm x&uacute;c: \"2023 l&agrave; năm nhiều thử th&aacute;ch, tr&agrave;n đầy th&uacute; vị, đ&uacute;ng nghĩa phải đạp gi&oacute; rẽ s&oacute;ng mới đạt được những th&agrave;nh tựu m&agrave; Chi Pu mơ ước. Mở đầu năm mới 2024 với giải thưởng Mỹ nh&acirc;n của năm l&agrave; t&iacute;n hiệu cực tốt với t&ocirc;i. Mỗi thời kỳ, ch&uacute;ng ta sẽ c&oacute; quan niệm kh&aacute;c về c&aacute;i đẹp. Với Chi, người phụ nữ đẹp l&agrave; người mạnh mẽ, tự tin, kh&ocirc;ng ngừng nỗ lực, nắm được cuộc đời m&igrave;nh trong tay, chinh phục ước mơ, theo đuổi đam m&ecirc; của m&igrave;nh. Tất cả đề cử năm nay đều v&ocirc; c&ugrave;ng xứng đ&aacute;ng, đặc biệt l&agrave; người t&ocirc;i v&ocirc; c&ugrave;ng ngưỡng mộ - chị Hương Giang. T&ocirc;i chỉ may mắn hơn ch&uacute;t x&iacute;u nhờ nhận được t&igrave;nh y&ecirc;u của kh&aacute;n giả. V&ocirc; c&ugrave;ng tr&acirc;n trọng giải thưởng đ&atilde; tạo n&ecirc;n một s&acirc;n chơi v&ocirc; c&ugrave;ng nh&acirc;n văn, gi&aacute; trị\". Nhiều fan của c&ocirc; c&oacute; mặt ở sự kiện, li&ecirc;n tục vỗ tay, reo h&ograve;.</p>\r\n<figure class=\"tplCaption action_thumb_added\" data-size=\"true\">\r\n<div class=\"fig-picture\"><picture><source srcset=\"https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=-xleq4KbpTJY4ec3CMuRww 1x, https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=2K--1G9RWNuAUBW3WGDkJw 1.5x, https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=2&amp;fit=crop&amp;s=x5cXX9LZvPs-rOY7KT5tKA 2x\" data-srcset=\"https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=-xleq4KbpTJY4ec3CMuRww 1x, https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=2K--1G9RWNuAUBW3WGDkJw 1.5x, https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=2&amp;fit=crop&amp;s=x5cXX9LZvPs-rOY7KT5tKA 2x\"><img class=\"lazy lazied\" src=\"https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=-xleq4KbpTJY4ec3CMuRww\" alt=\"Chi Pu nhận giải Mỹ nh&acirc;n của năm.\" loading=\"lazy\" data-src=\"https://i1-giaitri.vnecdn.net/2024/01/06/Chi-Pu-1-jpeg-8799-1704556726.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=-xleq4KbpTJY4ec3CMuRww\" data-ll-status=\"loaded\"></picture></div>\r\n<figcaption>\r\n<p class=\"Image\">Chi Pu nhận giải \"Mỹ nh&acirc;n của năm\".</p>\r\n</figcaption>\r\n</figure>\r\n<p class=\"Normal\">Trước đ&oacute;, L&yacute; Nh&atilde; Kỳ l&agrave; người trao giải cho Chi Pu. C&ocirc; cảm ơn chương tr&igrave;nh v&igrave; cơ hội được nằm trong th&agrave;nh phần ban gi&aacute;m khảo. C&ocirc; n&oacute;i: \"Giải thưởng mang nhiều &yacute; nghĩa, t&ocirc;n vinh vẻ đẹp, t&agrave;i năng, bản lĩnh của ph&aacute;i đẹp. Bất kỳ mỹ nh&acirc;n n&agrave;o đạt giải cũng l&agrave; người t&agrave;i hoa, phải \'đạp gi&oacute;, rẽ s&oacute;ng\' mới c&oacute; được ng&agrave;y h&ocirc;m nay\".</p>\r\n<p class=\"Normal\"><strong>Lễ trao giải c&oacute; nhiều khoảnh khắc x&uacute;c động.</strong>&nbsp;C&aacute;c gi&aacute;m khảo, nghệ sĩ, ban tổ chức, ho&agrave;i niệm về một năm nhiều dấu ấn của l&agrave;ng nghệ thuật, giải tr&iacute;, c&ugrave;ng truyền th&ocirc;ng điệp về th&aacute;i độ sống t&iacute;ch cực.</p>\r\n<p class=\"Normal\">Mỹ T&acirc;m - người thắng giải Nghệ sĩ truyền cảm hứng - kh&ocirc;ng thể dự sự kiện v&igrave; lịch tr&igrave;nh từ trước, gửi đến chương tr&igrave;nh video ph&aacute;t biểu cảm nghĩ: \"T&acirc;m nghĩ truyền cảm hứng mạnh nhất l&agrave; từ bộ phim&nbsp;<em>Người giữ thời gian</em>. N&oacute; kh&ocirc;ng chỉ n&oacute;i về &acirc;m nhạc, nghệ thuật m&agrave; l&agrave; về th&aacute;i độ sống. Đ&oacute; l&agrave; điều khiến T&acirc;m vui nhất trong năm. Sau đ&oacute; l&agrave; Vietnam Idol, một show rất ấm c&uacute;ng. Đ&oacute; l&agrave; những điều T&acirc;m kh&ocirc;ng t&iacute;nh to&aacute;n trước nhưng mọi thứ đến, mang đến năng lượng t&iacute;ch cực\".</p>\r\n<figure class=\"tplCaption action_thumb_added\" data-size=\"true\">\r\n<div class=\"fig-picture\"><picture><source srcset=\"https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=T8ySeKWstQERhFvar_yWBQ 1x, https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=1tG-KgD8ls8bWeWiboooiw 1.5x, https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=2&amp;fit=crop&amp;s=WnFh0BzspdDiyz327GvJGA 2x\" data-srcset=\"https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=T8ySeKWstQERhFvar_yWBQ 1x, https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=1tG-KgD8ls8bWeWiboooiw 1.5x, https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=2&amp;fit=crop&amp;s=WnFh0BzspdDiyz327GvJGA 2x\"><img class=\"lazy lazied\" src=\"https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=T8ySeKWstQERhFvar_yWBQ\" alt=\"H&agrave; Tr&iacute; Quang (phải) b&ecirc;n bạn đời - Thanh Đo&agrave;n.\" loading=\"lazy\" data-src=\"https://i1-giaitri.vnecdn.net/2024/01/06/ha-tri-quang-jpeg-8354-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=T8ySeKWstQERhFvar_yWBQ\" data-ll-status=\"loaded\"></picture></div>\r\n<figcaption>\r\n<p class=\"Image\">Diễn vi&ecirc;n H&agrave; Tr&iacute; Quang (phải) b&ecirc;n bạn đời - Thanh Đo&agrave;n.</p>\r\n</figcaption>\r\n</figure>\r\n<p class=\"Normal\">Chương tr&igrave;nh lần đầu d&agrave;nh giải Cặp đ&ocirc;i của năm để vinh danh cho cặp bạn đời thuộc về cộng đồng LGBT, diễn vi&ecirc;n H&agrave; Tr&iacute; Quang, Thanh Đo&agrave;n. H&agrave; Tr&iacute; Quang rưng rưng hồi tưởng l&uacute;c mới c&ocirc;ng khai l&agrave; người đồng t&iacute;nh. Khi ấy, anh đang l&agrave; diễn vi&ecirc;n truyền h&igrave;nh được ch&uacute; &yacute;, chấp nhận đ&aacute;nh đổi nhiều cơ hội khi tuy&ecirc;n bố giới t&iacute;nh thật.</p>\r\n<p class=\"Normal\">\"Ng&agrave;y h&ocirc;m nay, Quang xin cảm ơn tất cả bạn b&egrave; đồng nghiệp, ban tổ chức, đ&atilde; vượt qua giới hạn r&agrave;o cản, định kiến x&atilde; hội, cho t&igrave;nh y&ecirc;u của Quang v&agrave; Đo&agrave;n được thăng hoa nhờ giải thưởng Cặp đ&ocirc;i của năm\". Anh x&uacute;c động kể lại chuyện đ&atilde; xin ph&eacute;p bố mẹ được sống thật. Anh v&agrave; bạn đời được vinh danh nhờ 190 ngh&igrave;n lượt b&igrave;nh chọn.</p>\r\n<figure class=\"item_slide_show clearfix\">\r\n<div id=\"video_parent_391339\" class=\"box_embed_video_parent embed_video_new\" data-vcate=\"1003834\" data-vscate=\"1003007\" data-vscaten=\"Giải tr&iacute;\" data-vid=\"391339\" data-ratio=\"2\" data-articleoriginal=\"4697780\" data-ads=\"1\" data-license=\"0\" data-duration=\"325\" data-brandsafe=\"0\" data-type=\"0\" data-play=\"1\" data-frame=\"\" data-aot=\"Chi Pu thắng danh hiệu \'Mỹ nh&acirc;n\' ở Ng&ocirc;i sao của năm 2023\" data-videoclassify=\"None\" data-initdom=\"1\" data-view=\"inview\" data-auto=\"1\" data-status=\"play\">\r\n<div id=\"embed_video_391339\" class=\"box_embed_video\"></div>\r\n</div>\r\n</figure>\r\n<p class=\"Normal\">Cặp sao được ch&uacute; &yacute; với <a href=\"https://vnexpress.net/ha-tri-quang-mang-so-do-2-ty-dong-hoi-cuoi-4666292.html\" target=\"_blank\" rel=\"dofollow noopener\" data-itm-source=\"#vn_source=Detail-GiaiTri_GioiSao_TrongNuoc-4697780&amp;vn_campaign=Box-InternalLink&amp;vn_medium=Link-DamHoi&amp;vn_term=Desktop&amp;vn_thumb=0\" data-itm-added=\"1\">đ&aacute;m hỏi</a>&nbsp;tổ chức long trọng hồi th&aacute;ng 10, nhận được nhiều lời ch&uacute;c ph&uacute;c của kh&aacute;n giả, đồng nghiệp. Trước đ&oacute;, họ đ&oacute;n hai con, một trai, một g&aacute;i bằng phương ph&aacute;p mang thai hộ.</p>\r\n<p class=\"Normal\">Khi nhận giải Hot Mom, Nh&atilde; Phương nhắn nhủ ph&aacute;i đẹp lu&ocirc;n giữ tinh thần thoải m&aacute;i, y&ecirc;u bản th&acirc;n. C&ocirc; gửi lời đến chồng - diễn vi&ecirc;n Trường Giang: \"Cảm ơn ba đ&atilde; l&agrave; chỗ dựa, tạo điều kiện để mẹ l&agrave; ch&iacute;nh m&igrave;nh. Mong ba tiếp tục ph&aacute;t huy nha. M&atilde;i y&ecirc;u\".</p>\r\n<p class=\"Normal\">B&eacute; MyMy - con nu&ocirc;i nh&agrave; thiết kế Đỗ Mạnh Cường - thắng giải Hot Kid. Đỗ Mạnh Cường x&uacute;c động n&oacute;i: \"MyMy được Cường nhận nu&ocirc;i khi ba tuổi, giờ con đ&atilde; bốn tuổi rưỡi. Từ một c&ocirc; b&eacute; thiếu đi t&igrave;nh y&ecirc;u thương của cha mẹ ruột, nay con đ&atilde; c&oacute; t&igrave;nh y&ecirc;u thương của bố, nhiều người mẹ tr&ecirc;n cả nước. Giờ con rất nhỏ, chưa hiểu được &yacute; nghĩa giải thưởng. Nhưng bố Cường tin đ&acirc;y l&agrave; kỷ niệm đẹp với con. Khi lớn l&ecirc;n, con sẽ cảm ơn cuộc đời đ&atilde; tặng cho con điều tuyệt đẹp n&agrave;y\".</p>\r\n<p class=\"Normal\">Ban tổ chức, hội đồng chuy&ecirc;n m&ocirc;n v&agrave; kh&aacute;n giả vinh danh nhiều sản phẩm s&aacute;ng tạo, c&aacute;c gương mặt nổi bật qua giải thưởng mới - Hiện tượng của năm.&nbsp;<em>The New Mentor, Rap Việt, Tết ở l&agrave;ng địa ngục</em>&nbsp;lần lượt được gọi t&ecirc;n l&agrave; hiện tượng ở c&aacute;c hạng mục thời trang, &acirc;m nhạc, phim ảnh.</p>\r\n<figure class=\"tplCaption action_thumb_added\" data-size=\"true\">\r\n<div class=\"fig-picture\"><picture><source srcset=\"https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=Xe8rrmgjfHZ01VsYFt9kOA 1x, https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=0n7ZGn73J5E8cWAHO1OHZw 1.5x, https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=2&amp;fit=crop&amp;s=GBY0ims_u90Au7EZ06Xtkw 2x\" data-srcset=\"https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=Xe8rrmgjfHZ01VsYFt9kOA 1x, https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=0n7ZGn73J5E8cWAHO1OHZw 1.5x, https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=2&amp;fit=crop&amp;s=GBY0ims_u90Au7EZ06Xtkw 2x\"><img class=\"lazy lazied\" src=\"https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=Xe8rrmgjfHZ01VsYFt9kOA\" alt=\"Từ tr&aacute;i sang: &Ecirc;k&iacute;p The New Mentor gồm Hồ Ngọc H&agrave;, Dược Sĩ Tiến, Thanh Hằng, Lan Khu&ecirc; c&ugrave;ng nhận giải Hiện tượng thời trang của năm. Ngo&agrave;i ra, Dược Dĩ Tiến c&ograve;n thắng giải Nh&agrave; sản xuất của năm. Love Songs của Hồ Ngọc H&agrave; được vinh danh l&agrave; Dự &aacute;n nghệ thuật truyền cảm hứng.\" loading=\"lazy\" data-src=\"https://i1-giaitri.vnecdn.net/2024/01/06/ho-ngo-c-ha-jpeg-9307-1704556727.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=Xe8rrmgjfHZ01VsYFt9kOA\" data-ll-status=\"loaded\"></picture></div>\r\n<figcaption>\r\n<p class=\"Image\">Từ tr&aacute;i sang: &Ecirc;k&iacute;p \"The New Mentor\" gồm Hồ Ngọc H&agrave;, Dược Sĩ Tiến, Thanh Hằng, Lan Khu&ecirc; c&ugrave;ng nhận giải Hiện tượng thời trang của năm. Ngo&agrave;i ra, Dược Dĩ Tiến c&ograve;n thắng giải Nh&agrave; sản xuất của năm. Love Songs của Hồ Ngọc H&agrave; được vinh danh l&agrave; Dự &aacute;n nghệ thuật truyền cảm hứng.</p>\r\n</figcaption>\r\n</figure>\r\n<p class=\"Normal\"><em>Love Songs</em>&nbsp;của Hồ Ngọc H&agrave; l&agrave; Dự &aacute;n nghệ thuật truyền cảm hứng. C&ocirc; bền bỉ thực hiện chương tr&igrave;nh &acirc;m nhạc từ năm 2016. Ca sĩ ph&aacute;t biểu: \"Năm vừa rồi l&agrave; một năm v&ocirc; c&ugrave;ng kh&oacute; khăn. &Ecirc;k&iacute;p đ&atilde; cố gắng đến B&agrave; N&agrave; Hill, đưa kh&aacute;n giả đến nơi cao nhất, m&ecirc;nh m&ocirc;ng nhất để thưởng thức&nbsp;<em>Love Songs</em>\". C&ocirc; cảm ơn &ecirc;k&iacute;p, từng vị tr&iacute; &acirc;m thanh, &aacute;nh s&aacute;ng, ban nhạc Ho&agrave;i Sa, đạo diễn Đinh H&agrave; Uy&ecirc;n Thư, đ&atilde; gi&uacute;p c&ocirc; tỏa s&aacute;ng, bay bổng với giấc mơ của m&igrave;nh. C&ocirc; cho biết giải thưởng l&agrave; động lực để hoạt động năng nổ hơn trong năm 2024.</p>\r\n<figure class=\"item_slide_show clearfix\">\r\n<div id=\"video_parent_391336\" class=\"box_embed_video_parent embed_video_new\" data-vcate=\"1002835\" data-vscate=\"1003232\" data-vscaten=\"Video\" data-vid=\"391336\" data-ratio=\"2\" data-articleoriginal=\"4697821\" data-ads=\"1\" data-license=\"0\" data-duration=\"287\" data-brandsafe=\"0\" data-type=\"0\" data-play=\"0\" data-frame=\"\" data-aot=\"\'C&ocirc; đơn tr&ecirc;n sofa\' phi&ecirc;n bản đ&agrave;n, h&aacute;t của Hồ Ngọc H&agrave;\" data-videoclassify=\"None\" data-initdom=\"1\">\r\n<div class=\"box_img_video \" data-vid=\"391336\"><img src=\"https://iv1.vnecdn.net/ngoisao/images/web/2024/01/06/ho-ngoc-ha-vua-dan-hat-co-don-tren-sofa-1704552250.jpg?w=0&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=QPiFRFi6jgJm_W5yH1dqEA\" alt=\"Hồ Ngọc H&agrave; vừa đ&agrave;n, h&aacute;t \'C&ocirc; đơn tr&ecirc;n sofa\'\">\r\n<div class=\"icon_blockvideo\">\r\n<div class=\"img_icon\">&nbsp;</div>\r\n<div class=\"image_icon_center\">&nbsp;</div>\r\n</div>\r\n</div>\r\n</div>\r\n<figcaption class=\"desc_cation\">\r\n<div class=\"inner_caption\">\r\n<p class=\"Image\">Hồ Ngọc H&agrave; vừa đ&agrave;n vừa h&aacute;t \"C&ocirc; đơn tr&ecirc;n sofa\" - tiết mục g&acirc;y đấu ấn với kh&aacute;n giả ở lễ trao giải Ng&ocirc;i Sao của năm 2023.</p>\r\n</div>\r\n</figcaption>\r\n</figure>\r\n<p class=\"Normal\">Dược Sĩ Tiến thắng giải Nh&agrave; sản xuất của năm. Anh n&oacute;i: \"Giải thưởng cho Tiến hiểu ra khi n&agrave;o m&igrave;nh to&agrave;n t&acirc;m to&agrave;n &yacute; x&acirc;y dựng ước mơ cho người kh&aacute;c, mọi người sẽ gi&uacute;p m&igrave;nh đạt được ước mơ m&agrave; m&igrave;nh kh&ocirc;ng thể một m&igrave;nh đạt được\".</p>\r\n<p class=\"Normal\">Trước đ&oacute;, mở đầu chương tr&igrave;nh, nh&agrave; b&aacute;o Nhi&ecirc;u Huy - trưởng ban tổ chức - c&oacute; b&agrave;i ph&aacute;t biểu tổng kết hoạt động của l&agrave;ng giải tr&iacute;, nghệ thuật năm qua.</p>\r\n<p class=\"Normal\">Nh&agrave; b&aacute;o cho rằng 2023 l&agrave; năm kh&ocirc;ng mấy dễ d&agrave;ng bởi kinh tế, x&atilde; hội đầy biến động. Tuy nhi&ecirc;n, c&oacute; một nghịch l&yacute; đ&atilde; xảy ra ở thị trường giải tr&iacute;, trong đ&oacute; hoạt động nghệ thuật rất s&ocirc;i nổi, nhiều cột mốc mới được thiết lập.</p>\r\n<p class=\"Normal\">Hồi đầu năm, phim&nbsp;<em>Nh&agrave; b&agrave; nữ&nbsp;</em>của đạo diễn Trấn Th&agrave;nh lập kỷ lục doanh thu ph&ograve;ng v&eacute; Việt với hơn 480 tỷ đồng, k&eacute;o theo l&agrave; loạt phim trăm tỷ. Ở mảng thời trang, trưởng ban tổ chức nhắc đến sức h&uacute;t của&nbsp;<em>The New Mentor</em>&nbsp;- chương tr&igrave;nh thực tế về người mẫu ho&agrave;n to&agrave;n do &ecirc;k&iacute;p Việt Nam s&aacute;ng tạo, sản xuất. Mỗi tập ph&aacute;t s&oacute;ng thu h&uacute;t h&agrave;ng triệu lượt views tr&ecirc;n c&aacute;c nền tảng. NTK C&ocirc;ng Tr&iacute; t&aacute;i ngộ kh&aacute;n giả trong nước bằng BST m&atilde;n nh&atilde;n sau nhiều năm chinh phục thị trường quốc tế. C&ograve;n NTK Đỗ Mạnh Cường cũng đưa \"đứa con tinh thần\" của m&igrave;nh đến Tuần lễ thời trang New York, Mỹ.</p>\r\n<p class=\"Normal\">Show&nbsp;<em>Chị đẹp đạp gi&oacute;, rẽ s&oacute;ng</em>&nbsp;- chương tr&igrave;nh thực tế về &acirc;m nhạc với sự tham gia của 30 nữ nghệ sĩ - g&acirc;y \"sốt\" tr&ecirc;n c&aacute;c nền tảng. Họ kh&ocirc;ng chỉ khoe chuy&ecirc;n m&ocirc;n, m&agrave; c&ograve;n cho kh&aacute;n giả thấy nỗ lực tập luyện ở những kh&iacute;a cạnh vốn kh&ocirc;ng phải sở trường của m&igrave;nh. Loạt t&acirc;m sự, chia sẻ dần h&eacute; mở cuộc sống thường nhật của nghệ sĩ sau &aacute;nh h&agrave;o quang với đầy tổn thương, hy sinh c&ugrave;ng cố gắng để vừa giữ được h&igrave;nh ảnh trong l&ograve;ng c&ocirc;ng ch&uacute;ng, vừa chu to&agrave;n sự b&igrave;nh y&ecirc;n sau c&aacute;nh cửa nh&agrave;.</p>\r\n<p class=\"Normal\">Năm qua nhiều niềm vui mới đ&atilde; bừng s&aacute;ng. Trưởng ban tổ chức gala nhắc đến c&ocirc; d&acirc;u mới Thanh Hằng - bảy ng&agrave;y trước đ&aacute;m cưới vẫn miệt m&agrave;i huấn luyện, tập luyện cho c&aacute;c th&iacute; sinh v&agrave; vẫn rạng ngời trong ng&agrave;y vui. C&aacute;c b&agrave; mẹ showbiz d&ugrave; sinh nở lần đầu hay v&agrave;i lần đều nhanh ch&oacute;ng lấy lại v&oacute;c d&aacute;ng, trở lại c&ocirc;ng việc. Trong khi đ&oacute;, c&aacute;c \"thi&ecirc;n thần nh&iacute;\" của họ chinh phục truyền th&ocirc;ng, thu h&uacute;t t&igrave;nh cảm của người h&acirc;m mộ kh&ocirc;ng thua k&eacute;m bố mẹ.</p>\r\n<p class=\"Normal\">\"Những niềm vui, cột mốc t&ocirc;i vừa kể c&oacute; được l&agrave; nhờ c&aacute;c sao đ&atilde; &yacute; thức được gi&aacute; trị, tr&aacute;ch nhiệm của m&igrave;nh l&agrave; mang đến giờ ph&uacute;t thư gi&atilde;n, chữa l&agrave;nh cho c&ocirc;ng ch&uacute;ng, phần n&agrave;o gi&uacute;p họ tho&aacute;t khỏi &aacute;p lực từ cuộc sống. Để l&agrave;m được như vậy, bản th&acirc;n nghệ sĩ phải đầy nghị lực, nỗ lực vứt bỏ mọi r&agrave;o cản, kh&oacute; khăn t&aacute;c động từ b&ecirc;n ngo&agrave;i\", &ocirc;ng Nhi&ecirc;u Huy nhấn mạnh. Đ&oacute; cũng l&agrave; cảm hứng gi&uacute;p ban tổ chức x&acirc;y dựng chủ đề Unleashing (Ph&aacute; bỏ r&agrave;o cản) cho giải thưởng năm nay, nhằm tiếp tục ghi nhận những nghệ sĩ được c&ocirc;ng ch&uacute;ng y&ecirc;u mến, c&oacute; đ&oacute;ng g&oacute;p nổi bật cho nghệ thuật năm qua.</p>\r\n<p class=\"Normal\">C&aacute;c hạng mục năm nay lập kỷ lục với gần một triệu lượt b&igrave;nh chọn từ độc giả b&aacute;o Ng&ocirc;i Sao. \"Con số n&agrave;y kh&ocirc;ng chỉ n&oacute;i l&ecirc;n sức h&uacute;t của giải thưởng, m&agrave; phần n&agrave;o cho thấy nỗ lực s&aacute;ng tạo của nghệ sĩ kh&ocirc;ng bị c&ocirc;ng ch&uacute;ng bỏ qu&ecirc;n\", trưởng ban tổ chức cho biết.</p>\r\n<p class=\"Normal\"><strong>Kh&ocirc;ng chỉ l&agrave; lễ trao giải đơn thuần, chương tr&igrave;nh c&ograve;n mang đến bữa tiệc &acirc;m nhạc đầy m&agrave;u sắc qua c&aacute;c phần tr&igrave;nh diễn của d&agrave;n nghệ sĩ đ&igrave;nh đ&aacute;m.</strong>&nbsp;MONO h&aacute;t&nbsp;<em>Em xinh&nbsp;</em>do anh v&agrave; Onionn s&aacute;ng t&aacute;c, khuấy động kh&ocirc;ng kh&iacute; từ những ph&uacute;t mở m&agrave;n. Thu Phương h&aacute;t mash-up&nbsp;<em>Xin lỗi, Lần cuối&nbsp;</em>(hai s&aacute;ng t&aacute;c của Vũ Đinh Trọng Thắng) c&ugrave;ng vũ đo&agrave;n. Phương Mỹ Chi h&aacute;t, nhảy đ&ocirc;i động hit Vũ trụ c&oacute; anh (DTAP s&aacute;ng t&aacute;c). Hồ Ngọc H&agrave; mang đến tiết mục vừa s&acirc;u lắng vừa b&ugrave;ng nổ khi đ&agrave;n h&aacute;t C&ocirc; đơn tr&ecirc;n sofa. Uy&ecirc;n Linh, Ninh Dương Lan Ngọc, Quỳnh Nga, Trang Ph&aacute;p, Diệp L&acirc;m Anh t&aacute;i hiện Chị ngả em n&acirc;ng - ca kh&uacute;c từng g&acirc;y sốt tại show Đạp gi&oacute; th&aacute;ng 12/2023.</p>\r\n<figure class=\"item_slide_show clearfix\">\r\n<div id=\"video_parent_391343\" class=\"box_embed_video_parent embed_video_new\" data-vcate=\"1003834\" data-vscate=\"1003007\" data-vscaten=\"Giải tr&iacute;\" data-vid=\"391343\" data-ratio=\"1\" data-articleoriginal=\"4697780\" data-ads=\"1\" data-license=\"0\" data-duration=\"187\" data-brandsafe=\"0\" data-type=\"0\" data-play=\"0\" data-frame=\"\" data-aot=\"Chi Pu thắng danh hiệu \'Mỹ nh&acirc;n\' ở Ng&ocirc;i sao của năm 2023\" data-videoclassify=\"None\" data-initdom=\"1\">\r\n<div class=\"box_img_video embed-container\" data-vid=\"391343\"><img src=\"https://iv1.vnecdn.net/giaitri/images/web/2024/01/06/mono-hat-em-xinh-1704556818.jpg?w=0&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=X486CGPAkgZLk1xLWG0FRw\" alt=\"MONO h&aacute;t &quot;Em xinh&quot;\">\r\n<div class=\"icon_blockvideo\">\r\n<div class=\"img_icon\">&nbsp;</div>\r\n<div class=\"image_icon_center\">&nbsp;</div>\r\n</div>\r\n</div>\r\n</div>\r\n<figcaption class=\"desc_cation\">\r\n<div class=\"inner_caption\">\r\n<p class=\"Image\">Ca sĩ MONO h&aacute;t \"Em xinh\" mở m&agrave;n đ&ecirc;m trao giải \"Ng&ocirc;i sao của năm 2023\".</p>\r\n</div>\r\n</figcaption>\r\n</figure>\r\n<p class=\"Normal\">C&aacute;c tiết mục đều được phối mới, d&agrave;n dựng với nhiều hiệu ứng &acirc;m thanh, &aacute;nh s&aacute;ng đ&atilde; tai, đ&atilde; mắt. S&acirc;n khấu gala được đầu tư về quy m&ocirc;, d&agrave;n dựng với nhiều đ&egrave;n, m&agrave;n h&igrave;nh. Đạo diễn Trần Th&agrave;nh Trung cho biết anh v&agrave; &ecirc;k&iacute;p đặt mục ti&ecirc;u thực hiện chương tr&igrave;nh mới mẻ, ho&agrave;nh tr&aacute;ng nhưng vẫn phải lắng đọng, nhiều cảm x&uacute;c. MC Quang Bảo, Hoa hậu Ngọc Ch&acirc;u dẫn dắt lưu lo&aacute;t, tạo cơ hội cho c&aacute;c nghệ sĩ, kh&aacute;ch mời tỏa s&aacute;ng đ&uacute;ng thời điểm.</p>', '32baa7e1-82d0-483d-a154-2d4982f00904.jpg', '2024-01-06 23:38:37.238000', 'Chi Pu thắng danh hiệu \'Mỹ nhân\' ở Ngôi sao của năm 2023', 82, 6, 19);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(3, 'admin', 'ROLE_ADMIN'),
(4, 'editor', 'ROLE_EDITOR'),
(5, 'user', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `username`, `role_id`, `name`) VALUES
(2, 'hoanglysn2k2@edu.vn', 'admin2', 'admin2', 4, 'Em Ly'),
(3, 'admin3@gmail.com', '$2a$10$8h7ySsWMmSyilURelSA0PuxcbvxuI1qWm1WhhEaXsBLSuhQccAkCy', 'admin3', 4, 'Ly'),
(5, 'admin4@gmail.com', '$2a$10$FaYxmVby7nvE2Gn47MDDYeyVc/eHmkhL.mXM2nZ7XCKH0DVdx/ZYG', 'admin4', 5, 'Ly'),
(6, 'admin@gmail.com', '$2a$10$qXm.57G3cGXNxm6SgW3ZE.Wh/abSs1/zNFwuB9GA0aPCrVu/RsKye', 'admin', 3, 'Ly Hai'),
(7, 'hoanglysn2k2@gmail.com', '$2a$10$taAuhjcEZQXsXbTLExrcveySwxfbS8HXmea8kJaqLRfJD.E3MO8mW', 'admin5', 5, 'Hoàng Hải Lý');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `adminmenu_role`
--
ALTER TABLE `adminmenu_role`
  ADD PRIMARY KEY (`adminmenu_id`,`role_id`),
  ADD KEY `FKowo9k26fpgo50o53l83cjq79w` (`role_id`);

--
-- Chỉ mục cho bảng `admin_menu`
--
ALTER TABLE `admin_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKck0mctr73qlq7wigmed2qbtxv` (`parent_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2y94svpmqttx80mshyny85wqr` (`parent_id`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`);

--
-- Chỉ mục cho bảng `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK12njtf8e0jmyb45lqfpt6ad89` (`author_id`),
  ADD KEY `FKg6l1ydp1pwkmyj166teiuov1b` (`category_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin_menu`
--
ALTER TABLE `admin_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `adminmenu_role`
--
ALTER TABLE `adminmenu_role`
  ADD CONSTRAINT `FK6nc116evuoj8w2isdwy9knebk` FOREIGN KEY (`adminmenu_id`) REFERENCES `admin_menu` (`id`),
  ADD CONSTRAINT `FKowo9k26fpgo50o53l83cjq79w` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Các ràng buộc cho bảng `admin_menu`
--
ALTER TABLE `admin_menu`
  ADD CONSTRAINT `FKck0mctr73qlq7wigmed2qbtxv` FOREIGN KEY (`parent_id`) REFERENCES `admin_menu` (`id`);

--
-- Các ràng buộc cho bảng `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK2y94svpmqttx80mshyny85wqr` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Các ràng buộc cho bảng `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK12njtf8e0jmyb45lqfpt6ad89` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKg6l1ydp1pwkmyj166teiuov1b` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

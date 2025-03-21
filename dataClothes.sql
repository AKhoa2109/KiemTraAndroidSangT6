use cloth_app;
 
INSERT INTO User (id, name, email, password, image, is_active) VALUES 
(1, 'User One', 'user1@example.com', '123456', 'https://static.vecteezy.com/system/resources/thumbnails/048/216/761/small/modern-male-avatar-with-black-hair-and-hoodie-illustration-free-png.png', true),
(2, 'User Two', 'user2@example.com', '123456', 'https://www.w3schools.com/howto/img_avatar.png', true),
(3, 'User Three', 'user3@example.com', '123456', 'https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg', true),
(4, 'User Four', 'user4@example.com', '123456', 'https://img.freepik.com/premium-vector/avatar-icon002_750950-52.jpg', true),
(5, 'User Five', 'user5@example.com', '123456', 'https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg', true),
(6, 'User Six', 'user6@example.com', '123456', 'user6.jpg', true),
(7, 'User Seven', 'user7@example.com', '123456', 'user7.jpg', true);




INSERT INTO Category (category_id, category_name, image) VALUES 
(1, 'Ao nam', 'https://file.hstatic.net/1000360022/file/2__3__917dd4cc92e94037b3314b0c9e10261b.jpg'),
(2, 'Ao nu', 'https://down-vn.img.susercontent.com/file/vn-11134208-7r98o-ls6skkcp1vvd07'),
(3, 'Quan nam', 'https://product.hstatic.net/200000690725/product/626f76ff-56e5-49d9-a8b8-a4bd91ebba22_6f472dce057545bbaea6342b432f2668_master.jpg'),
(4, 'Ao sing', 'https://product.hstatic.net/200000690725/product/avt_web_1150_x_1475_px___1__1d88fc770c294039bae8e7b9078db7a3.png'),
(5, 'that lưng', 'https://tamanh.net/wp-content/uploads/2016/10/that-lung-nam-mat-khoa-cai-d310t-24-1.jpg'),
(6, 'Phu kien', 'https://pos.nvncdn.com/a11880-4373/art/artCT/20210917_dHqIfreYN9nj6dVxWCkSWEl2.jpg');



INSERT INTO product (id, name, price, created_at, images, category_id) VALUES
(2, 'Áo thun nữ', 180000, '2024-03-02 12:30:45', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 1),
(3, 'Áo khoác nam', 500000, '2024-03-03 14:00:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 1),

(4, 'Quần jean nam', 350000, '2024-03-04 09:45:20', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 2),
(5, 'Quần kaki nữ', 320000, '2024-03-05 11:20:10', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 2),
(6, 'Quần short nam', 200000, '2024-03-06 16:10:05', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 2),

(7, 'Giày sneaker nam', 700000, '2024-03-07 08:55:15', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 3),
(8, 'Giày cao gót nữ', 850000, '2024-03-08 10:25:30', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 3),
(9, 'Dép sandal nam', 150000, '2024-03-09 13:40:25', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 3),

(10, 'Mũ lưỡi trai', 120000, '2024-03-10 07:30:50', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 4),
(11, 'Nón len nữ', 100000, '2024-03-11 10:00:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 4),
(12, 'Mũ bảo hiểm', 350000, '2024-03-12 15:00:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 4),

(13, 'Túi xách nữ', 900000, '2024-03-13 09:00:30', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 5),
(14, 'Balo laptop', 650000, '2024-03-14 14:50:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 5),
(15, 'Ví nam da bò', 400000, '2024-03-15 11:30:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 5),

(16, 'Đồng hồ nam', 2500000, '2024-03-16 11:15:10', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 6),
(17, 'Đồng hồ nữ', 2800000, '2024-03-17 12:20:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 6),
(18, 'Dây chuyền bạc', 1200000, '2024-03-18 14:10:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 6),

(19, 'Áo hoodie nam', 420000, '2024-03-19 08:20:25', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 3),
(20, 'Áo hoodie nữ', 390000, '2024-03-20 09:30:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 4),
(21, 'Quần jogger nam', 310000, '2024-03-21 10:40:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 5),

(22, 'Bộ đồ thể thao nam', 550000, '2024-03-22 10:45:35', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 6),
(23, 'Bộ đồ thể thao nữ', 530000, '2024-03-23 12:00:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 6),
(24, 'Giày chạy bộ', 1200000, '2024-03-24 13:15:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 5),

(25, 'Áo vest nam', 1800000, '2024-03-25 09:55:10', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 4),
(26, 'Quần tây nam', 600000, '2024-03-26 10:30:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 4),
(27, 'Giày tây nam', 950000, '2024-03-27 11:45:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 2),

(28, 'Đầm dạ hội', 2500000, '2024-03-28 11:10:05', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 1),
(29, 'Chân váy nữ', 450000, '2024-03-29 12:20:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 3),
(30, 'Giày búp bê nữ', 720000, '2024-03-30 13:30:00', 'https://bizweb.dktcdn.net/100/415/697/products/ak046.png?v=1701405178907', 5);





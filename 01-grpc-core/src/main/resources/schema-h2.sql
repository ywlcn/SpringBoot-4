-- ユーザーテーブル
create TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- 注文テーブル
create TABLE orders (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 注文商品（中間に1対多を表現）
create TABLE order_items (
--    id INT AUTO_INCREMENT PRIMARY KEY,
    id bigint GENERATED ALWAYS AS IDENTITY ,
    order_id VARCHAR(36) NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    price INT  NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);




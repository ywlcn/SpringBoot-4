insert into users(id, name, email , password , roles) values('001', 'user1' , 'test001@sapmle.com' , 'password' , 'USER' );
insert into users(id, name, email , password , roles) values('002', 'user2' , 'test002@sapmle.com' , 'password' , 'USER' );
insert into users(id, name, email , password , roles) values('003', 'user3' , 'test003@sapmle.com' , 'password' , 'USER' );
insert into users(id, name, email , password , roles) values('004', 'admin' , 'test004@sapmle.com' , 'password' , 'ADMIN' );

insert into orders(id, user_id, total_amount , status) values(1,'001', 432000 , 'created');

insert into order_items(order_id,  item_name , price) values(1, 'item001',22);
insert into order_items(order_id,  item_name , price) values(1, 'item002',33);
insert into order_items(order_id,  item_name , price) values(1, 'item003',44);



insert into orders(id, user_id, total_amount , status) values(2,'002', 86541 , 'created');

insert into order_items(order_id, item_name , price) values(2,  'item001',122);
insert into order_items(order_id, item_name , price) values(2,  'item002',234);
insert into order_items(order_id, item_name , price) values(2,  'item003',43);
insert into order_items(order_id, item_name , price) values(2,  'item007',777);








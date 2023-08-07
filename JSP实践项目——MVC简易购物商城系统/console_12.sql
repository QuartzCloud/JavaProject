create table User
(
    `uname`    varchar(30) not null primary key comment '用户名',
    `password` varchar(30)         not null comment '密码'
);
drop table user;
insert into `user` values ('zql','223456');
select password from user where uname='zql';

create table ShopCart
(
    `uname`   varchar(30) comment '用户名',
    `gname`   varchar(30) comment '商品名称',
    `gprice`  float comment '商品单价',
    `cnum`    int comment '购买数量' not null,
    foreign key (uname) references User (uname)
);
drop table `shopcart`;
insert into `ShopCart` values ('zql','三星  500GB SSD固态硬盘 SATA3.0接口',440,1);
delete from shopcart where gname='三星  500GB SSD固态硬盘 SATA3.0接口';

create table `Goods`
(
    `gname`   varchar(30) primary key comment '商品名',
    `gprice`  float comment '商品单价',
    `gnumber` int comment '商品库存数' not null
);
drop table `Goods`;
insert into goods
values ('林清玄启悟人生系列：愿你，归来仍是少年', 31.90, 996);
insert into goods
values ('平凡的世界：全三册（激励青年的不朽经典）', 103.40, 947);
insert into goods
values ('曾国藩全集（全六卷 绸面精装插盒珍藏版）', 280.50, 998);
insert into goods
values ('中外文化文学经典系列 红岩 导读与赏析', 31.90, 995);
insert into goods
values ('古琴 老杉木乐器伏羲式_七弦琴 ', 3628.90, 19);
insert into goods
values ('专业演奏级乐器洞箫_8孔正手G调', 603.90, 97);
insert into goods
values ('三星  500GB SSD固态硬盘 SATA3.0接口 ', 440.00, 498);

select gname, gprice, cnum, gprice * ShopCart.cnum 'cprice'
from ShopCart
where uname = '朱秦乐';

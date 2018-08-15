CREATE TABLE `teams` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `rating` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

insert into teams (name,rating) values ('Red',1);
insert into teams (name,rating) values ('Blue',2);
insert into teams (name,rating) values ('Green',3);
insert into teams (name,rating) values ('Yellow',1);
insert into teams (name,rating) values ('pink',2);
insert into teams (name,rating) values ('purple',3);
insert into teams (name,rating) values ('gray',1);
insert into teams (name,rating) values ('black',2);
insert into teams (name,rating) values ('white',3);
insert into teams (name,rating) values ('indigo',1);

CREATE TABLE `teams_processed` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `rating` int(6) NOT NULL,
  `color_code` varchar(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
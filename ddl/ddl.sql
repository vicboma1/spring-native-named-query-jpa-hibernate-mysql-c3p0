CREATE DATABASE namednativequeries;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2130114 DEFAULT CHARSET=latin1;

INSERT INTO `user` (`id`, `email`, `name`) VALUES
	(1, 'user@mail.com', 'mike'),
	(2, 'user2@mail.com', 'peter');
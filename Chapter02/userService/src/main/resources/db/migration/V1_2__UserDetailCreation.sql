CREATE TABLE `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(36) NOT NULL,
  `first_name` varchar(250) NOT NULL,
  `middle_name` varchar(250) DEFAULT NULL,
  `last_name` varchar(250) DEFAULT NULL,
  `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `deleted_on` TIMESTAMP DEFAULT NULL,
  `legal_id` varchar(20) NOT NULL,
  `date_of_birth` TIMESTAMP NOT NULL,
  `gender` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `user_details_userId_DeletedOn` (`user_id`,`deleted_on`),
  KEY `user_id_deleted` (`deleted_on`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(36) NOT NULL,
  `city` varchar(25) NOT NULL,
  `address_line_1` varchar(250) NOT NULL,
  `address_line_2` varchar(250) DEFAULT NULL,
  `pincode` char(6) NOT NULL,
  `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `deleted_on` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `addresses_user_id_DeletedOn` (`user_id`,`deleted_on`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
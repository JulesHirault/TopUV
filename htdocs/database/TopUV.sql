CREATE TABLE IF NOT EXISTS `category` (
  `id` int(255) NOT NULL,
  `label` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS ` comment` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `text` varchar(500) NOT NULL,
  `id_student` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `description_uv` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `curricula` varchar(500) NOT NULL,
  `objectives` varchar(500) NOT NULL,
  `type_uv` varchar(500) NOT NULL,
  `credits` varchar(500) NOT NULL,
  `availability` varchar(500) NOT NULL,
  `lectures` varchar(255) NOT NULL,
  `tutorials` varchar(255) NOT NULL,
  `practicals` varchar(255) NOT NULL,
  `personnal` varchar(255) NOT NULL,
  `id_uv` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_uv` (`id_uv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `mark` (
  `value` int(255) NOT NULL,
  `id_student` int(255) NOT NULL,
  `id_uv` varchar(6) NOT NULL,
  KEY `id_student` (`id_student`),
  KEY `id_uv` (`id_uv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `token` varchar(255) NOT NULL,
  `picture` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `student_uv` (
  `id_student` int(255) NOT NULL,
  `id_uv` varchar(6) NOT NULL,
  PRIMARY KEY (`id_student`,`id_uv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `uv` (
  `id` varchar(6) NOT NULL,
  `label` varchar(100) NOT NULL,
  `id_description` int(255) NOT NULL,
  `avg_mark` int(255) NOT NULL,
  `id_category` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_description` (`id_description`),
  KEY `id_category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE ` comment`
  ADD CONSTRAINT `@0020comment_ibfk_1` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`);

ALTER TABLE `description_uv`
  ADD CONSTRAINT `description_uv_ibfk_1` FOREIGN KEY (`id_uv`) REFERENCES `uv` (`id`);

ALTER TABLE `mark`
  ADD CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`id_uv`) REFERENCES `uv` (`id`);

ALTER TABLE `uv`
  ADD CONSTRAINT `uv_ibfk_1` FOREIGN KEY (`id_description`) REFERENCES `description_uv` (`id`);

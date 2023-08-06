    CREATE TABLE IF NOT EXISTS Photo (
        id bigint auto_increment primary key,
        file_name varchar(50),
        content_type varchar(5),
        data binary
    )
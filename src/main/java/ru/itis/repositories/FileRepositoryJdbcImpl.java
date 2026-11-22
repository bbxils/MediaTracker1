package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.sql.SQLException;

public class FileRepositoryJdbcImpl implements  FilesRepository {

    private DataSource dataSource;
    private static final String SQL_INSERT_FILE = "insert into fileinfo(storage_file_name, original_file_name," +
            " type, size) values (?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID = "select * from fileinfo where id = ?";
    private JdbcTemplate jdbcTemplate;

    public FileRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();

    @Override
    public FileInfo findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileInfoRowMapper, id);
    }

    @Override
    public void save(FileInfo entity) throws SQLException {
        jdbcTemplate.update(SQL_INSERT_FILE, entity.getStorageFileName(),
                entity.getOriginalFileName(),
                entity.getType(),
                entity.getSize());
    }
}

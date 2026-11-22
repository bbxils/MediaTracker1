package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.User;
import ru.itis.repositories.FileRepositoryJdbcImpl;
import ru.itis.repositories.FilesRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryJdbcImpl;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "WR3ZJDPX";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/class_db";

    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        FilesRepository filesRepository = new FileRepositoryJdbcImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(userRepository);
        UsersService usersService = new UsersServiceImpl(userRepository);
        FileService fileService = new FileServiceImpl(filesRepository);

        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("fileService", fileService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

package org.zerock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Controller
@ComponentScan(basePackages = { "org.zerock.sample" })
@MapperScan(basePackages = { "org.zerock.mapper" })
public class RootConfig {

    /**
     * Hikari Connection Pool을 사용하면 미리 DB와 연결을 맺어주어 성능향상에 도움이 된다.
     * HikariConfig에 DB 정보 set 후
     * HikariDataSource 생성 후 HikariConfig -> HikariDataSource
     * @return HikariDataSource
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setUsername("C##book_ex");
        hikariConfig.setPassword("book_ex");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    // MyBatis Config
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

}

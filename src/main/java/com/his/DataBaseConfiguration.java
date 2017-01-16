package com.his;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by lwjazy on 2016/10/21.
 */
    @Configuration
    @EnableTransactionManagement
    public class DataBaseConfiguration implements EnvironmentAware {

        private RelaxedPropertyResolver propertyResolver;

        @Override
        public void setEnvironment(Environment env) {
            this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        }

        @Bean(destroyMethod = "close", initMethod = "init")
        public DataSource writeDataSource() {
            System.out.println("注入druid！！！");


            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(propertyResolver.getProperty("url"));
            dataSource.setUsername(propertyResolver.getProperty("username"));//用户名
            dataSource.setPassword(propertyResolver.getProperty("password"));//密码
            dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
            dataSource.setInitialSize(2);
            dataSource.setMaxActive(20);
            dataSource.setMinIdle(0);
            dataSource.setMaxWait(60000);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestOnBorrow(false);
            dataSource.setTestWhileIdle(true);
            dataSource.setPoolPreparedStatements(false);
            return dataSource;
        }

    }

